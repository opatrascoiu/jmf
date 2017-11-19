package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.HutnImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.HutnInterfTemplate;
import uk.ac.kent.cs.kmf.util.*;

public class HutnGenerator implements Runnable {
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
    protected ILog log;
    protected File interfDir;
    protected File implDir;
    protected String indent;
    
	/**
	  * Initialize the members
	  */
    public HutnGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	this.log = log;
    	interfaceName = context.getNaming().getHUTNVisitorInterface(modelName);
    	className = context.getNaming().getHUTNVisitorClass(modelName);
    	indent = context.getIndent();
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
	    createHUTNVisitorInterface(interfDir);
	    // Create the XMI vistor implementation
	    createHUTNVisitorImplementation(implDir);
	}  

	/**
	  * Create the HUTN visitor interface
	  */
	protected void createHUTNVisitorInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = interfaceName+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new HutnInterfTemplate(model, context, visitor)).generate();
	    visitor.close();
	}

	/**
	  * Create the HUTN visitor implementation
	  */
	protected void createHUTNVisitorImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = className+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new HutnImplTemplate(model, context, visitor)).generate();
		visitor.close();
	}

}
