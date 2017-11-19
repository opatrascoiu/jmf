package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.AdapterFactoryImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.ReaderAdapterImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.XmiImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.XmiInterfTemplate;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

public class XmiGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected Context context;
    protected String outDirName;
    protected String interfaceName;
    protected String className;
    protected String readerAdapterClass;
    protected String adapterFactoryClass;
    protected ILog log;
    protected File implDir;
    protected File interfDir;
    protected String indent;

	/**
	  * Initialize the members
	  */
    public XmiGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	this.log = log;
    	interfaceName = context.getNaming().getXMIVisitorInterface(modelName);
    	className = context.getNaming().getXMIVisitorClass(modelName);
    	adapterFactoryClass = context.getNaming().getAdapterFactoryClass(modelName);
    	readerAdapterClass = context.getNaming().getReaderAdapterClass(modelName);
    	this.indent = context.getIndent();
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
	    // Create the ouput directories
    	interfDir = new File(outDirName+"/"+modelName+"/repository");
    	interfDir.mkdirs();
    	implDir = new File(outDirName+"/"+modelName+"/repository");
    	implDir.mkdirs();
	    // Create the Reader Adapter implementation
	    createReaderAdapterImplementation(implDir);
	    // Create the Adapter Factory implementation
	    createAdapterFactoryImplementation(implDir);
	    // Create the XMI visitor
	    createXMIVisitorInterface(interfDir);
	    createXMIVisitorImplementation(implDir);
    }

	/**
	  * Create the ReaderAdapter implementation
	  */
	protected void createAdapterFactoryImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = adapterFactoryClass+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new AdapterFactoryImplTemplate(model, context, visitor)).generate();
	    visitor.close();
	}

	/**
	  * Create the XMI reader modelImplAdapter implementation
	  */
	protected void createReaderAdapterImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = readerAdapterClass+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'", e);
	    }
	    // Call the output procedure
		(new ReaderAdapterImplTemplate(model, context, visitor)).generate();
		visitor.close();
	}

	/**
	  * Create the XMI visitor interface
	  */
	protected void createXMIVisitorInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = interfaceName+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new XmiInterfTemplate(model, context, visitor)).generate();
		visitor.close();
	}

	/**
	  * Create the XMI visitor implementation
	  */
	protected void createXMIVisitorImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = className+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new XmiImplTemplate(model, context, visitor)).generate();
		visitor.close();
	}
}
