package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.RepositoryImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.RepositoryInterfTemplate;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

public class RepositoryGenerator implements Runnable {
	/**
	  * Class members
	  */
    protected Model model;
    protected String modelName;
    protected String modelPackage;
    protected Context context;
    protected String outDirName;
    protected String repositoryInterface;
    protected String repositoryClass;
    protected String elementInterface;
    protected String factoryInterface;
    protected File interfDir;
    protected File implDir;
    protected ILog log;
    protected String indent;
    
	/**
	  * Initialize the members
	  */
    public RepositoryGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
    	this.log = log;
	    // Init interface/class name
	    repositoryInterface = context.getNaming().getRepositoryInterface(modelName);
	    repositoryClass = context.getNaming().getRepositoryClass(modelName);
	    elementInterface = context.getNaming().getElementInterface(modelName);
	    factoryInterface = context.getNaming().getFactoryInterface(modelName);
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
	    // Create the repository interface
	    createRepositoryInterface(interfDir);
	    // Create the repository implementation
	    createRepositoryImplementation(implDir, outDirName);
    }

	/**
	  * Create the RepositoryInterface
	  */
	protected void createRepositoryInterface(File interfDir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter out = null;
	    String fileName = repositoryInterface+".java";
	    try {
	        out = new PrintWriter(new FileWriter(new File(interfDir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Print the repository interface
	    (new RepositoryInterfTemplate(model, context, out)).generate();
	    out.close();
	}

	/**
	  * Create the Repository implementation
	  */
	protected void createRepositoryImplementation(File implDir, String outDirName) {
	    // Prepare the arguments for the output procedure
	    PrintWriter out = null;
	    String fileName = repositoryClass+".java";
	    try {
	        out = new PrintWriter(new FileWriter(new File(implDir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Print the repository implemenentation
		(new RepositoryImplTemplate(model, context, out)).generate();
		out.close();
	}
}
