package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class RepositoryInterfTemplate {
	public RepositoryInterfTemplate(Model model, Context context, PrintWriter out) {
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
		this.repositoryInterface = context.getNaming().getRepositoryInterface(modelName);
		this.repositoryClass = context.getNaming().getRepositoryClass(modelName);
		this.elementInterface = context.getNaming().getElementInterface(modelName);
		this.factoryInterface = context.getNaming().getFactoryInterface(modelName);
	}

	/**
	  * Print RepositoryInterface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(repositoryInterface, out);
		// Print the code
		// Print header
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("public interface "+repositoryInterface);
		out.println(indent+"extends uk.ac.kent.cs.kmf.common.Repository");
		out.println("{");
		out.println(indent+"///--- FACTORIES ---");
		out.println(indent+"/** Get a specific factory */");
		out.println(indent+"public "+context.getNaming().getFullFactoryInterface(modelName)+" getFactory(String fullClassName);");
		out.println(indent+"/** Register a factory */");
		out.println(indent+"public void registerFactory(String fullClassName, "+context.getNaming().getFullFactoryInterface(modelName)+" factory);");
		out.println();
		if (context.isGenerateBrowser()) {
			out.println(indent+"//--- BROWSER ---");
			out.println(indent+"/** Get the content of the repository */");
			out.println(indent+"public javax.swing.tree.DefaultMutableTreeNode toJTree();");
			out.println(indent+"/** Save the repository into an XMI file */");
			out.println(indent+"public void saveXMI(String fileName);");
			out.println(indent+"/** Create a new repository from an XMI file */");
			out.println(indent+"public uk.ac.kent.cs.kmf.common.Repository loadXMI(String fileName);");
			out.println(indent+"/** Get the content of the repository */");
			out.println(indent+"public String toHUTN();");
			out.println(indent+"/** Save the repository into an HUTN file */");
			out.println(indent+"public void saveHUTN(String fileName);");
			out.println(indent+"/** Create a new repository from an HUTN file */");
			out.println(indent+"public "+repositoryInterface+" loadHUTN(String fileName);");
			out.println();
		}
		if (context.isGenerateInvariant()) {
			out.println(indent+"/** Parse all invariants */");
			out.println(indent+"public void parseInvariants();");
			out.println(indent+"/** Evaluate all invariants */");
			out.println(indent+"public void evaluateInvariants();");
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
	protected String repositoryInterface;
	protected String repositoryClass;
	protected String elementInterface;
	protected String factoryInterface;
}
