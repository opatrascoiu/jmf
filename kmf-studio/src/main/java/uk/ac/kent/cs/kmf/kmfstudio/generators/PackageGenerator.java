package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.*;
import java.util.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.*;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.util.*;

public class PackageGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected Context context;
    protected String outDirName;
    protected ILog log;
    
	/**
	  * Initialize the members
	  */
    public PackageGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	this.log = log;
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
        // Create packages
        createPackage(model, context, outDirName);
    }

	/**
	  * Create a package
	  */
	protected void createPackage(Package pkg, Context context, String outDirName) {    
	    // Get the model name
	    String pkgName = context.getNaming().getPackageName(pkg);
	    if (pkgName.equals("null")) {
	        log.reportMessage("Ignoring content of package 'null'.");
	    } else if (context.getNaming().isStereotype(pkg, "external")) {
	        log.reportMessage("Ignoring content of <<external>> package '"+pkgName+"'.");
	    } else {
	        log.reportMessage("Generating code for package '"+pkgName+"'.");
		    // Create the ouput directories
		    File interfDir = new File(outDirName+"/"+pkgName);
		    File implDir = new File(outDirName+"/"+pkgName);
		    interfDir.mkdirs();
		    implDir.mkdirs();
		
		    // Create containers for local classes, packages, ties, associations, and stereotypes
		    List ties = new Vector();
		    List classes = new Vector();
		    List packages = new Vector();
		    List stereotypes = new Vector();
		    
		    // Add each package element into an appropriate container
		    Iterator i = pkg.getOwnedElement().iterator();
		    while (i.hasNext()) {
		        Object o = i.next();
		        if (o instanceof Class_ || o instanceof Enumeration_) classes.add(o);
		        else if (o instanceof Tie) ties.add(o);
		        else if (o instanceof Package) packages.add(o);
		        else if (o instanceof Stereotype) stereotypes.add(o);
		    }
		
		    // Create each included package 
		    Iterator ip = packages.iterator();
		    while(ip.hasNext()) {
		        Package p = (Package)ip.next();
		        String pname = context.getNaming().getPackageName(p);
		        createPackage(p, context, interfDir.getPath());
		    }
		    
		    // Create all the other elements
	        Iterator io = classes.iterator();
	        Vector threads = new Vector();
	        while (io.hasNext()) {
	            Classifier cls = (Classifier)io.next();
	            // Create LifecycleInterface, LifecycleClass, Class, and ClassInterface
	            // Primitive stereotypes
	            if (context.getNaming().isStereotype(cls, "primitive")) {
	                log.reportMessage("No visitor, factory, or implementation for <<primitive>> class '"+cls.getName().getBody_()+"'");
	        	// External types
	            } else if (context.getNaming().isStereotype(cls, "external")) {
	                log.reportMessage("No visitor, factory, or implementation for <<external>> class '"+cls.getName().getBody_()+"'");
	        	// Abstract types
	            } else if (cls.getIsAbstract().booleanValue()) {
	            	// Create class generator thread
	                Thread classThread = new Thread(new ClassGenerator(pkg, context, cls, interfDir, implDir, log));
	            	threads.add(classThread);
	                classThread.start();
	            	// Create class visitor generator thread
	            	Thread visitorThread = new Thread(new ClassVisitorGenerator(pkg, context, cls, interfDir, implDir, log));
	            	if (context.isGenerateVisitor()) {
	            		threads.add(visitorThread);
//	            		visitorThread.start();
	            	}
	                log.reportMessage("No factory for \"abstract\" class '"+cls.getName().getBody_()+"'");
	            // Normal classes
	            } else {
	            	// Create class generator thread
	                Thread classThread = new Thread(new ClassGenerator(pkg, context, cls, interfDir, implDir, log));
	            	threads.add(classThread);
	                classThread.start();
	            	// Create class visitor generator thread
	            	Thread visitorThread = new Thread(new ClassVisitorGenerator(pkg, context, cls, interfDir, implDir, log));
	            	if (context.isGenerateVisitor()) {
	            		threads.add(visitorThread);
//	            		visitorThread.start();
	            	}
	            	// Create class factory generator thread
	            	Thread lifecycleThread = new Thread(new ClassFactoryGenerator(pkg, context, cls, interfDir, implDir, log));
	            	if (context.isGenerateFactory()) {
	            		threads.add(lifecycleThread);
	            		lifecycleThread.start();
	            	}
	            	// Create factory visitor generator thread
	            	Thread factoryVisitorThread = new Thread(new ClassFactoryVisitorGenerator(pkg, context, cls, interfDir, implDir, log));
	            	if (context.isGenerateVisitor() && context.isGenerateFactory()) {
	            		threads.add(factoryVisitorThread);
//	            		factoryVisitorThread.start();
	            	}
	            }
	        }
	        // Join all the threads
	        try {
	        	Iterator it = threads.iterator();
	        	while (it.hasNext()) {
	        		((Thread)(it.next())).join();
	        	}
	        } catch (Exception e) {
	        	log.reportError("Cannot join threades for classes from package '"+pkgName+"'", e);
	        }	
	    }
	}
}

