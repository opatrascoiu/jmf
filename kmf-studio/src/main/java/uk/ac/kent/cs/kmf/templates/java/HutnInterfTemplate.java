package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class HutnInterfTemplate {
	public HutnInterfTemplate(Model model, Context context, PrintWriter out) {
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
		this.interfaceName = context.getNaming().getHUTNVisitorInterface(modelName);
	}

	/**
	  * Print HUTNVisitor interface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(interfaceName, out);
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import "+modelPackage+".*;");
		out.println();
		out.println("public interface "+interfaceName);
		out.println(indent+"extends "+context.getNaming().getVisitorInterface(modelName));
		out.println("{");
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
