package uk.ac.kent.cs.kmf.kmfstudio;

import java.io.*;

import uk.ac.kent.cs.kmf.kmfstudio.generators.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.*;

import uk.ac.kent.cs.kmf.util.*;

public class CodeGeneratorImpl 
	implements CodeGenerator
{
    /** 
      * Create packages in the out directory
      */
//    protected static String modelName;
//    protected static String modelPackage;
//    protected String outDirName = new String();
//    protected static String rootOffset = new String();
//    protected String licenceFileName; 
//    
//    // Naming settings 
//    protected static String interfacePrefix = Naming.interfacePrefix;
//    protected static String interfaceSuffix = Naming.interfaceSuffix;
//    protected static String classPrefix = Naming.classPrefix;
//    protected static String classSuffix = Naming.classSuffix;
//	protected static String collectionInterface = Naming.collectionInterface;
//    protected static String listInterface = Naming.listInterface;
//	protected static String setInterface = Naming.setInterface;
//	protected static String collectionClass = Naming.collectionClass;
//	protected static String listClass = Naming.listClass;
//	protected static String setClass = Naming.setClass;
    
	/** Generate the code */    	
	public void generate(Project project, Naming naming, ILog log) {
		// Set project and log
		this.project = project;
		this.log = log;
		this.naming = naming;

    	// Compute the licence file
    	String licenceFileName = project.getLicenseFileName();
		// Compute the output directory 
		String rootOffset = project.getPackageOffset();
		if (rootOffset.length()!=0 && !rootOffset.endsWith(".")) rootOffset += ".";
		String outDirName = project.getProjectPath()+"/src";
    	if (outDirName.endsWith("/")) outDirName = outDirName.substring(0, outDirName.length()-1);
    	if (project.getPackageOffset().length()!=0)
    		outDirName += "/" + rootOffset.replace('.','/').replace('\\','/').substring(0, rootOffset.length()-1);
        // Compute the context
        context = new Context(project, naming, log);
    	// Create the directories
    	(new File(outDirName)).mkdirs();
    	(new File(project.getProjectPath()+"/bin")).mkdirs();

		// Load model
		Model model = project.getModel(log);

		// Start generating the code
		log.reportMessage("Generating Java code to '"+project.getProjectPath()+"/src/' ...");

        //--- Generate the code for PACKAGE ---
        PackageGenerator packageGenerator = new PackageGenerator(model, context, outDirName, log);
        Thread packageThread = new Thread(packageGenerator);
        packageThread.run();

        // Generate the code for ELEMENT
        ElementGenerator elemGenerator = new ElementGenerator(model, context, outDirName, log);
        Thread elemThread = new Thread(elemGenerator);
       	elemThread.start();

        //--- Generate the code for VISITOR ---
    	VisitorGenerator visitorGenerator = new VisitorGenerator(model, context, outDirName, log);
    	Thread visitorThread = new Thread(visitorGenerator);
        if (context.isGenerateVisitor()) {
	        visitorThread.start();
        }

      	//--- Generate the code for FACTORY ---
    	FactoryGenerator factoryGenerator = new FactoryGenerator(model, context, outDirName, log);
    	Thread factoryThread = new Thread(factoryGenerator);
        if (context.isGenerateFactory()) {
        	factoryThread.start();
        }

        //--- Generate the code for REPOSITORY ---
        RepositoryGenerator repGenerator = new RepositoryGenerator(model, context, outDirName, log);
	    Thread repThread = new Thread(repGenerator);
        if (context.isGenerateRepository()) {
        	repThread.start();
        }

        // Generate the code for HUTN save
        HutnGenerator hutnGenerator = new HutnGenerator(model, context, outDirName, log);
        Thread hutnThread = new Thread(hutnGenerator);
        if (context.isGenerateHUTNVisitor()) {
        	hutnThread.start();
        }

        // Generate the code for JTREE visitor
        JtreeVisitorGenerator jtreeGenerator = new JtreeVisitorGenerator(model, context, outDirName, log);
        Thread jtreeThread = new Thread(jtreeGenerator);
        if (context.isGenerateJTreeVisitor()) {
			jtreeThread.start();
        }

        // Generate the code for VIEW/EDIT visitors
        ViewEditVisitorGenerator viewEditVisitorGenerator = new ViewEditVisitorGenerator(model, context, outDirName, log);
        Thread viewEditVisitorThread = new Thread(viewEditVisitorGenerator);
        if (context.isGenerateViewEditVisitor()) {
			viewEditVisitorThread.start();
        }

        // Generate the code for invariants visitors
        InvariantsGenerator invariantsGenerator = new InvariantsGenerator(model, context, outDirName, log);
        Thread invariantsVisitorThread = new Thread(invariantsGenerator);
        if (context.isGenerateInvariant()) {
			invariantsVisitorThread.start();
        }

        // Generate the code for XMI load/save
        XmiGenerator xmiGenerator = new XmiGenerator(model, context, outDirName, log);
        Thread xmiThread = new Thread(xmiGenerator);
        if (context.isGenerateXMIVisitor()) {
        	xmiThread.start();
        }

        // Generate the code for OCLEvalutor
        OclEvaluatorGenerator oclGenerator = new OclEvaluatorGenerator(model, context, outDirName, log);
        Thread oclThread = new Thread(oclGenerator);
        if (context.isGenerateInvariant()) {
        	oclThread.start();
        }

        // Generate the code for BROWSER
        BrowserGenerator browserGenerator = new BrowserGenerator(model, context, outDirName, licenceFileName, log);
        Thread browserThread = new Thread(browserGenerator);
        if (context.isGenerateBrowser()) {
			browserThread.start();
        }

        // Generate the code for STRARTUP
        StartupGenerator startupGenerator = new StartupGenerator(model, context, outDirName, log);
        Thread startupThread = new Thread(startupGenerator);
        if (context.isGenerateBrowser()) {
			startupThread.start();
        }

        //--- Join all the threads
        try {
        	elemThread.join();
            packageThread.join();
            visitorThread.join();
            repThread.join();
            factoryThread.join();
            xmiThread.join();
            hutnThread.join();
            jtreeThread.join();
            viewEditVisitorThread.join();
			invariantsVisitorThread.join();
            oclThread.join();
            browserThread.join();
            startupThread.join();
        } catch (Exception e) {
            log.reportError("Generating threads cannot be joined", e);
        }

		//--- Print the final report
		log.finalReport();
    }

	/** Get the model from an XMI file */
	public Model getModel() {
		return model;
	}
	    
    //
    // Local properties
    //
    protected Project project;
	protected Naming naming;
    protected ILog log;
	protected Model model;
	protected Context context;
}
