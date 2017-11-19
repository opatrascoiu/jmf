package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.ElementInterfTemplate;
import uk.ac.kent.cs.kmf.util.*;

public class ElementGenerator 
    implements Runnable
{
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected Context context;
    protected String outDirName;
    protected ILog log;
    protected File interfDir;
    protected String elementInterface;
    protected String elementClass; 
    protected String indent;
    
	/**
	  * Initialize the members
	  */
    public ElementGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.log = log;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	elementInterface = context.getNaming().getElementInterface(modelName);
    	elementClass = context.getNaming().getElementClass(modelName);
    	this.indent = context.getIndent();
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
    	// Create directories
	    interfDir = new File(outDirName+"/"+modelName);
	    interfDir.mkdirs();
	    // Create the interface
	    createElementInterface(interfDir);
    }

	/**
	  * Create the top level repository element interface
	  */
	protected void createElementInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter out=null;
	    String fileName = elementInterface+".java";
	    try {
	        out = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new ElementInterfTemplate(model, context, out)).generate();
	    out.close();
	}

}
