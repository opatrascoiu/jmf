package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.ViewEditFrameImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.ViewEditImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.ViewEditInterfTemplate;
import uk.ac.kent.cs.kmf.templates.java.ViewEditInvokeMethodImplTemplate;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

public class ViewEditVisitorGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected Context context;
    protected String outDirName;
    protected String viewEditFrameClass;
    protected String invokeMethodClass;
    protected String viewInterface;
    protected String viewClass;
    protected String editInterface;
    protected String editClass;
    protected File interfDir;
    protected File implDir;
    protected ILog log;
    protected String indent;
    
	/**
	  * Initialize the members
	  */
    public ViewEditVisitorGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	this.log = log;
    	viewEditFrameClass = context.getNaming().getViewEditFrameClass(modelName);
    	invokeMethodClass = context.getNaming().getInvokeMethodClass(modelName);
    	viewInterface = context.getNaming().getViewVisitorInterface(modelName);
		viewClass = context.getNaming().getViewVisitorClass(modelName);
    	editInterface = context.getNaming().getEditVisitorInterface(modelName);
		editClass = context.getNaming().getEditVisitorClass(modelName);
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
        // Create the View/Edit frame - edit repository
	    createViewEditFrameImplementation(implDir);
	    // Create the instant invoker implementation
	    createInvokeMethodImplementation(implDir);
	    // Create the VIEW visitor
	    createViewVisitorInterface(interfDir);
	    createViewVisitorImplementation(implDir);
	    // Create the EDIT visitor
	    createEditVisitorInterface(interfDir);
	    createEditVisitorImplementation(implDir);
    }

	/**
	  * Create the EDIT visitor implementation
	  */
	protected void createViewEditFrameImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = viewEditFrameClass+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new ViewEditFrameImplTemplate(model, context, visitor)).generate();
	    visitor.close();
	}

	/**
	  * Create the instant invoker implementation
	  */
	protected void createInvokeMethodImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = invokeMethodClass+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new ViewEditInvokeMethodImplTemplate(model, context, visitor)).generate();
		visitor.close();
	}

	/**
	  * Create the View visitor interface
	  */
	protected void createViewVisitorInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = viewInterface+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new ViewEditInterfTemplate(model, context, false, visitor)).generate();
		visitor.close();
	}

	/**
	  * Create the View visitor implementation
	  */
	protected void createViewVisitorImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = viewClass+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new ViewEditImplTemplate(model, context, false, visitor)).generate();
		visitor.close();
	}

	/**
	  * Create the Edit visitor interface
	  */
	protected void createEditVisitorInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = editInterface+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new ViewEditInterfTemplate(model, context, true, visitor)).generate();
		visitor.close();
	}

	/**
	  * Create the Edit visitor implementation
	  */
	protected void createEditVisitorImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = editClass+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new ViewEditImplTemplate(model, context, true, visitor)).generate();
		visitor.close();
	}
}
