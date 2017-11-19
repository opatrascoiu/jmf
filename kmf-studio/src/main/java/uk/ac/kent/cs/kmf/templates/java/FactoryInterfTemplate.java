package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class FactoryInterfTemplate {
	public FactoryInterfTemplate(Model model, Context context, PrintWriter out) {
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
	}

	/**
	  * Print top-level factory interface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(interfaceName, out);
		// Print the code
		// Print header
		out.println("package "+modelPackage+";");
		out.println();
		out.println("public interface "+interfaceName);
		if (context.isGenerateVisitor()) {
			out.println(indent+"extends "+context.getNaming().getVisitableInterface(modelName));
		}
		out.println("{");
		out.println(indent+"/** Build an object */");
		out.println(indent+"public Object build();");
		if (context.isGenerateRepository()) {
			String repInterface = context.getNaming().getFullRepositoryInterface(modelName);
			out.println();
			out.println(indent+"/** Get the repository */");
			out.println(indent+"public "+repInterface+" "+context.getNaming().getGetter("repository")+"();");
			out.println(indent+"/** Set the repository */");
			out.println(indent+"public void "+context.getNaming().getSetter("repository")+"("+repInterface+" repository);");
		}
		out.println();
		out.println(indent+"/** Override toString */");
		out.println(indent+"public String toString();");
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
}
