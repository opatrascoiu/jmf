package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.JTreeImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.JTreeInterfTemplate;
import uk.ac.kent.cs.kmf.util.*;

public class JtreeVisitorGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected Context context;
    protected String outDirName;
    protected String jtreeInterface;
    protected String jtreeClass;
    protected ILog log;
    protected File interfDir;
    protected File implDir;
    protected String indent;
    
	/**
	  * Initialize the members
	  */
    public JtreeVisitorGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.log = log;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	jtreeInterface = context.getNaming().getJTreeVisitorInterface(modelName);
    	jtreeClass = context.getNaming().getJTreeVisitorClass(modelName);
    	this.indent = context.getIndent();
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
	    // Create the ouput directories
    	interfDir = new File(outDirName+"/"+modelName+"/repository");
    	implDir = new File(outDirName+"/"+modelName+"/repository");
    	interfDir.mkdirs();
    	implDir.mkdirs();
	    // Create the XMI visitor interface
	    createJTreeVisitorInterface(interfDir);
	    // Create the XMI vistor implementation
	    createJTreeVisitorImplementation(implDir);
	}  

	/**
	  * Create the JTree visitor interface
	  */
	protected void createJTreeVisitorInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = jtreeInterface+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new JTreeInterfTemplate(model, context, visitor)).generate();
	    visitor.close();
	}

	/**
	  * Create the JTree visitor implementation
	  */
	protected void createJTreeVisitorImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = jtreeClass+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new JTreeImplTemplate(model, context, visitor)).generate();
		visitor.close();
	}
}
