package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class FactoryImplTemplate {
	public FactoryImplTemplate(Model model, Context context, PrintWriter out) {
		this.model = model;
		this.context = context;
		this.out = out;
		this.log = context.getLog();
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.log = context.getLog();
		this.modelName = context.getNaming().getModelName();
		this.modelPackage = context.getNaming().getModelPackage();
		this.interfaceName = context.getNaming().getFactoryInterface(modelName);
		this.className = context.getNaming().getFactoryClass(modelName);
	}

	/**
	  * Print top-level factory class
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(className, out);
		// Print the code
		// Print header
		out.println("package "+modelPackage+";");
		out.println();
		out.println("public class "+className);
		out.println(indent+"implements "+interfaceName);
		out.println("{");
		// Add constructor
		out.println(indent+"/** Default constructor */");
		out.println(indent+"public "+className+"() {");
		out.println(indent+"}");
		if (context.isGenerateRepository()) {
			String repInterface = context.getNaming().getFullRepositoryInterface(modelName);
			out.println(indent+"/** Specialized constructor */");
			out.println(indent+"public "+className+"("+repInterface+" repository) {");
			out.println(indent+indent+"this.repository = repository;");
			out.println(indent+"}");
		}
		// Add build
		out.println(indent+"/** Build object */");
		out.println(indent+"public Object build() {");
		out.println(indent+indent+"return null;");
		out.println(indent+"}");
		// Add product id
		if (context.isGenerateID()) {
			out.println();
			out.println(indent+"/** The id */");
			out.println(indent+"private static int objId = 0;");
			out.println(indent+"/** Reset the id */");
			out.println(indent+"public static void resetId() {");
			out.println(indent+indent+"objId = 0;");
			out.println(indent+"}");
			out.println(indent+"/** Get a new id */");
			out.println(indent+"public static String newId() {");
			out.println(indent+indent+"return \"\"+ ++objId;");
			out.println(indent+"}");
		}
		// Add repository id
		if (context.isGenerateRepository()) {
			String repInterface = context.getNaming().getFullRepositoryInterface(modelName);
			out.println();
			out.println(indent+"/** The repository */");
			out.println(indent+"protected "+repInterface+" repository;" );
			out.println(indent+"/** Get the repository */");
			out.println(indent+"public "+repInterface+" "+context.getNaming().getGetter("repository")+"() {");
			out.println(indent+indent+"return repository;");
			out.println(indent+"}");
			out.println(indent+"/** Set the repository */");
			out.println(indent+"public void "+context.getNaming().getSetter("repository")+"("+repInterface+" repository) {");
			out.println(indent+indent+"this.repository = repository;");
			out.println(indent+"}");
		}
		// Add toString
		out.println();
		out.println(indent+"/** Override toString */");
		out.println(indent+"public String toString() {");
		out.println(indent+indent+"return \""+interfaceName+"\";");
		out.println(indent+"}");
		// Output accept
		if (context.isGenerateVisitor()) {
			out.println();
			out.println(indent+"/** Accept '"+context.getNaming().getVisitorInterface(modelName)+"' */");
			out.println(indent+"public Object accept("+context.getNaming().getVisitorInterface(modelName)+" v, Object data) {");
			out.println(indent+indent+"return v.visit(this, data);");
			out.println(indent+"}");
		}
		out.println("}");
	}

	//
	// Local properties
	//
	protected Model model;
	protected Context context;
	protected PrintWriter out;
	
	protected ILog log;
	protected String indent;
	protected String modelName;
	protected String modelPackage;
	protected String interfaceName;
	protected String className;
}
