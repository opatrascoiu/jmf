package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.StartupImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.StartupInterfTemplate;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

public class StartupGenerator
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
    protected String startupInterface;
    protected String startupClass;
    protected String repositoryInterface;
    protected String repositoryClass;
    protected String browserClass;
    protected File interfDir;
    protected File implDir;
    protected ILog log;
    protected String indent;
    
	/**
	  * Initialize the members
	  */
    public StartupGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	this.log = log;
	    // Init interface/class name
	    startupInterface = context.getNaming().getStartupInterface(modelName);
	    startupClass = context.getNaming().getStartupClass(modelName);
	    repositoryInterface = context.getNaming().getFullRepositoryInterface(modelName);
	    repositoryClass = context.getNaming().getFullRepositoryClass(modelName);
	    browserClass = context.getNaming().getFullBrowserClass(modelName);
	    indent = context.getIndent();
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
	    // Create the ouput directories
    	interfDir = new File(outDirName+"/"+modelName+"/startup");
    	implDir = new File(outDirName+"/"+modelName+"/startup");
    	interfDir.mkdirs();
    	implDir.mkdirs();
	    // Create the startup interface
	    createStartupInterface(interfDir);
	    // Create the startup implementation
	    createStartupImplementation(implDir);
    }

	/**
	  * Create the starter interface
	  */
	protected void createStartupInterface(File interfDir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter out=null;
	    String fileName = startupInterface+".java";
	    try {
	        out = new PrintWriter(new FileWriter(new File(interfDir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new StartupInterfTemplate(model, context, out)).generate();
		out.close();
	}

	/**
	  * Create the starter implementation
	  */
	protected void createStartupImplementation(File implDir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter out=null;
	    String fileName = startupClass+".java";
	    try {
	        out = new PrintWriter(new FileWriter(new File(implDir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new StartupImplTemplate(model, context, out)).generate();
		out.close();
	}
}
