package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.OclEvalImplTemplate;
import uk.ac.kent.cs.kmf.util.*;

public class OclEvaluatorGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected Context context;
    protected String outDirName;
    protected String className;
    protected String browserClassName;
    protected File implDir;
    protected ILog log;
    protected String indent;
    
	/**
	  * Initialize the members
	  */
    public OclEvaluatorGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	className = context.getNaming().getOCLClass(modelName);
		browserClassName = context.getNaming().getFullBrowserClass(modelName);
    	this.indent = context.getIndent();
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
	    // Create the ouput directories
    	implDir = new File(outDirName+"/"+modelName+"/repository");
    	implDir.mkdirs();
	    // Create the instant evaluator implementation
	    createOCLEvaluatorImplementation(implDir);
	}  

	/**
	  * Create the instant evaluator implementation
	  */
	protected void createOCLEvaluatorImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = className+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new OclEvalImplTemplate(model, context, visitor)).generate();
	    visitor.close();
	}
}
