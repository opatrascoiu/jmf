package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.*;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.ClassFactoryImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.ClassFactoryInterfTemplate;
import uk.ac.kent.cs.kmf.util.*;

public class ClassFactoryGenerator implements Runnable {
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
	protected String factoryInterfaceName;
	protected String factoryClassName;
	protected File interfDir;
	protected File implDir;
	protected ILog log;
	protected String indent;
	    
	/**
	  * Initialize the members
	  */
    public ClassFactoryGenerator(Package pkg, Context context, Classifier cls, File interfDir, File implDir, ILog log) {
    	this.pkg = pkg;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
	    pkgName = context.getNaming().getPackageName(pkg);
        fullPkgName = context.getNaming().getFullPackageName(pkg);
    	this.context = context;
    	this.cls = cls;
	    factoryInterfaceName = context.getNaming().getFactoryInterfaceName(cls);
	    factoryClassName = context.getNaming().getFactoryClassName(cls);
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
	    createClassFactoryInterface(interfDir);
    	// Create the class
	    createClassFactoryImplementation(implDir);
    }

	/**
	  * Create an object's factory
	  */
	protected void createClassFactoryInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter factory=null;
	    String fileName = factoryInterfaceName+".java";
	    try {
	        factory = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot find file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new ClassFactoryInterfTemplate(cls, context, factory)).generate();
	    factory.close();
	}

	/**
	  * Create an objects's factory interface
	  */
	protected void createClassFactoryImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter factory=null;
	    String fileName = factoryClassName+".java";
	    try {
	        factory = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot find file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new ClassFactoryImplTemplate(cls, context, factory)).generate();
	    factory.close();
	}
}
