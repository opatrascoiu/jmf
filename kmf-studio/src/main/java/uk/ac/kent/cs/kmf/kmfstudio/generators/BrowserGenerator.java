package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.BrowserImplTemplate;
import uk.ac.kent.cs.kmf.util.*;

public class BrowserGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected String browserInterface;
    protected String browserClass;
    protected String repositoryInterface;
    protected String repositoryClass;
    protected String factoryInterface;
    protected String factoryClass;
    protected String invokeMethodClass;
    protected String elementInterface;
    protected Context context;
    protected ILog log;
    protected String outDirName;
    protected String licenceFileName; 
    protected File implDir;
    protected String indent;
    
	/**
	  * Initialize the members
	  */
    public BrowserGenerator(Model model, Context context, String outDirName, String licenceFileName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.log = log;
		this.licenceFileName = licenceFileName;

    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	browserInterface = context.getNaming().getBrowserInterface(modelName);
    	browserClass = context.getNaming().getBrowserClass(modelName);
    	repositoryInterface = context.getNaming().getFullRepositoryInterface(modelName);
    	repositoryClass = context.getNaming().getFullRepositoryClass(modelName);
    	factoryInterface = context.getNaming().getFactoryInterface(modelName);
    	factoryClass = context.getNaming().getFactoryClass(modelName);
    	invokeMethodClass = context.getNaming().getInvokeMethodClass(modelName);
	    elementInterface = context.getNaming().getElementInterface(modelName);
	    indent = context.getIndent();
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
    	// Create directories
    	implDir = new File(outDirName+"/"+modelName+"/repository");
    	implDir.mkdirs();
	    // Create the browser implementation
	    createBrowserImplementation(implDir);
    }

	/**
	  * Create the browser implementation
	  */
	protected void createBrowserImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = browserClass+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Create template and generate code
	    (new BrowserImplTemplate(model, context, visitor)).generate();
	    visitor.close();
	}
}
