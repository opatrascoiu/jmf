package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.*;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.ClassVisitorInterfTemplate;
import uk.ac.kent.cs.kmf.util.*;

public class ClassVisitorGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package pkg;
    protected String modelName;
    protected String modelPackage;
    protected String pkgName;
    protected String fullPkgName;
    protected Context context;
	protected Classifier cls;
	protected String visitorInterfaceName;
	protected File interfDir;
	protected File implDir;
	protected ILog log;
	protected String indent;
	    
	/**
	  * Initialize the members
	  */
    public ClassVisitorGenerator(Package pkg, Context context, Classifier cls, File interfDir, File implDir, ILog log) {
    	this.pkg = pkg;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
	    pkgName = context.getNaming().getPackageName(pkg);
        fullPkgName = context.getNaming().getFullPackageName(pkg);
    	this.context = context;
    	this.cls = cls;
	    visitorInterfaceName = context.getNaming().getVisitorInterfaceName(cls);
    	this.interfDir = interfDir;
    	this.implDir = implDir;
    	this.log = log;
    	this.indent = context.getIndent();
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
    	// Create the interface
	    createClassVisitorInterface(interfDir);
    }

	/**
	  * Create an object's visitor
	  */
	protected void createClassVisitorInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter factory=null;
	    String fileName = visitorInterfaceName+".java";
	    try {
	        factory = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot find file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new ClassVisitorInterfTemplate(cls, context, factory)).generate();
	    factory.close();
	}
}
