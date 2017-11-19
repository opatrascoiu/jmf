package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class StartupInterfTemplate {
	public StartupInterfTemplate(Model model, Context context, PrintWriter out) {
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
		this.startupInterface = context.getNaming().getStartupInterface(modelName);
		this.startupClass = context.getNaming().getStartupClass(modelName);
		this.repositoryInterface = context.getNaming().getFullRepositoryInterface(modelName);
		this.repositoryClass = context.getNaming().getFullRepositoryClass(modelName);
		this.browserClass = context.getNaming().getFullBrowserClass(modelName);
	}

	/**
	  * Print startup interface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(modelName+"Startup", out);
		// Print the code
		// Print header
		out.println("package "+modelPackage+".startup;");
		out.println();
		out.println("public interface "+startupInterface);
		out.println("{");
		out.println(indent+"/** run the startup */");
		out.println(indent+"public void run("+repositoryInterface+" rep);");
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
	protected String startupInterface;
	protected String startupClass;
	protected String repositoryInterface;
	protected String repositoryClass;
	protected String browserClass;
}
