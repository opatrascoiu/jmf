package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.FactoryImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.FactoryInterfTemplate;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

public class FactoryGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected Context context;
    protected String outDirName;
    protected File interfDir;
    protected File implDir;
    protected ILog log;
    protected String interfaceName;
    protected String className;
    protected String indent;
     
	/**
	  * Initialize the members
	  */
    public FactoryGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	this.log = log;
	    this.interfaceName = context.getNaming().getFactoryInterface(modelName);
	    this.className = context.getNaming().getFactoryClass(modelName);
	    this.indent = context.getIndent();
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
	    // Create the ouput directories
	    interfDir = new File(outDirName+"/"+modelName);
	    interfDir.mkdirs();
	    implDir = new File(outDirName+"/"+modelName);
	    implDir.mkdirs();
	    // Create the top level interface
	    createFactoryInterface(interfDir);
	    // Create the top level class
	    createFactoryClass(implDir);
    }

	/**
	  * Create the top level factory interface
	  */
	protected void createFactoryInterface(File interfDir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter factory=null;
	    String fileName = interfaceName+".java";
	    try {
	        factory = new PrintWriter(new FileWriter(new File(interfDir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new FactoryInterfTemplate(model, context, factory)).generate();
	    factory.close();
	}

	/**
	  * Create the top level factory interface
	  */
	protected void createFactoryClass(File interfDir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter factory=null;
	    String fileName = className+".java";
	    try {
	        factory = new PrintWriter(new FileWriter(new File(interfDir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new FactoryImplTemplate(model, context, factory)).generate();
		factory.close();
	}
}
