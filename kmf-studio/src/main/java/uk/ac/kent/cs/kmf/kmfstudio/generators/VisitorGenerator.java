package uk.ac.kent.cs.kmf.kmfstudio.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.templates.java.VisitableInterfTemplate;
import uk.ac.kent.cs.kmf.templates.java.VisitorImplTemplate;
import uk.ac.kent.cs.kmf.templates.java.VisitorInterfTemplate;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

public class VisitorGenerator 
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
    protected String interfaceName;
    protected String implName;
    protected String className;
    protected File interfDir;
    protected File implDir;
    protected ILog log;
    protected String indent;
    
	/**
	  * Initialize the members
	  */
    public VisitorGenerator(Model model, Context context, String outDirName, ILog log) {
    	this.model = model;
    	this.context = context;
    	this.outDirName = outDirName;
    	this.modelName = context.getNaming().getModelName();
    	this.modelPackage = context.getNaming().getModelPackage();
	    // Set the log
	    this.log = log;
	    this.indent = context.getIndent();
    }
    
	/**
	  * Generate the Java code
	  */
    public void run() {
	    // Create the ouput directories
	    interfDir = new File(outDirName+"/"+modelName);
	    interfDir.mkdirs();
	    implDir = new File(outDirName+"/"+modelName);
	    implDir.mkdirs();
	    // Create the top-level visitor interface
	    createVisitorInterface(interfDir);
	    // Create the top-level visitor implementation
	    createVisitorImplementation(implDir);
	    // Create the top-level visitable interface
	    createVisitableInterface(interfDir);
	}  

	/**
	  * Create the top level visitor interface
	  */
	protected void createVisitorInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = context.getNaming().getVisitorInterface(modelName)+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
	    (new VisitorInterfTemplate(model, context, visitor)).generate();
	    visitor.close();
	}

	/**
	  * Create the top level visitor implementation
	  */
	protected void createVisitorImplementation(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitor=null;
	    String fileName = context.getNaming().getVisitorClass(modelName)+".java";
	    try {
	        visitor = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new VisitorImplTemplate(model, context, visitor)).generate();
		visitor.close();
	}

	/**
	  * Create the top-level visitable interface
	  */
	protected void createVisitableInterface(File dir) {
	    // Prepare the arguments for the output procedure
	    PrintWriter visitable=null;
	    String fileName = context.getNaming().getVisitableInterface(modelName)+".java";
	    try {
	        visitable = new PrintWriter(new FileWriter(new File(dir, fileName)), true);
	    } catch (Exception e) {
	        log.reportError("Cannot open file '"+fileName+"'.", e);
	    }
	    // Call the output procedure
		(new VisitableInterfTemplate(model, context, visitable)).generate();
		visitable.close();
	}

//	/**
//	  * Print Visitor interface
//	  */
//	protected void outputVisitorInterface(PrintWriter out) {
//    	// Print generation stamp
//    	CodeGenerator.putStamp(CodeGenerator.getVisitorInterface(modelName), out);
//    	// Compute the extended interfaces
//    	List extensions = new Vector();
//	    Iterator i = context.getClasses().iterator();
//	    // Add factory/class pairs
//	    while (i.hasNext()) {
//	        Classifier cls = (Classifier)i.next();
//	        // Primitive
//	        if (CodeGenerator.isStereotype(cls, "primitive")) {
//	        // Externals
//	        } else if (CodeGenerator.isStereotype(cls, "external")) {
//	        // Others
//            } else {
//	        	// Factory
//	            if (!cls.getIsAbstract().booleanValue() && context.isGenerateFactory()) {
//            		extensions.add(indent+indent+CodeGenerator.getFullFactoryVisitorInterfaceName(cls));
//	            }
//	            // Class
//            	extensions.add(indent+indent+CodeGenerator.getFullVisitorInterfaceName(cls));
//	        }
//	    }
//    	// Print the code
//	    out.println("package "+modelPackage+";");
//	    out.println();
//	    out.println("public interface "+CodeGenerator.getVisitorInterface(modelName));
//	    out.println(indent+"extends");
//	    i = extensions.iterator();
//	    while (i.hasNext()) {
//	    	String str = (String)i.next();
//	    	out.print(str);
//	    	if (i.hasNext()) out.print(",");
//	    	out.println();
//	    }
//	    out.println("{");
//	    // Add default factory
//	    if (context.isGenerateFactory()) {
//	    	out.println(indent+"/** Visit '"+modelPackage+"."+CodeGenerator.getFactoryInterface(modelName)+"' */");
//	    	out.println(indent+"public Object visit("+modelPackage+"."+CodeGenerator.getFactoryInterface(modelName)+" host, Object data);");
//	    }
//	    out.println("}");
//	}
}
