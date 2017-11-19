package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class StartupImplTemplate {
	public StartupImplTemplate(Model model, Context context, PrintWriter out) {
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
	  * Print startup implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(modelName+"Startup", out);
		// Print the code
		// Print header
		out.println("package "+modelPackage+".startup;");
		out.println();
		out.println("public class "+startupClass);
		out.println(indent+"implements "+startupInterface);
		out.println("{");
		out.println(indent+"//--- The repository ---");
		out.println(indent+"protected "+repositoryInterface+" rep;");
		out.println(indent+"public "+repositoryInterface+" getRepository() {");
		out.println(indent+indent+"return rep;");
		out.println(indent+"}");
		out.println(indent+"public void setRepository("+repositoryInterface+" rep) {");
		out.println(indent+indent+"this.rep = rep;");
		out.println(indent+"}");
		out.println();
		out.println(indent+"//--- Replace the default lifecycle handlers ---");
		out.println(indent+"protected void replaceDefaultLifecycles() {");
		out.println(indent+"}");
		out.println();
		out.println(indent+"//--- Initialise population ---");
		out.println(indent+"protected void initialisePopulation() {");
		out.println(indent+"}");
		out.println();
		out.println(indent+"//--- Start the '"+modelName+"' browser ---");
		out.println(indent+"protected void startBrowser() {");
		String cleanName = context.getXMIFileName().replace('\\', '/');
		out.println(indent+indent+"new "+browserClass+"(\""+cleanName+"\");");
		out.println(indent+indent+""+browserClass+" browser = new "+browserClass+"(\""+cleanName+"\");");
		out.println(indent+indent+"browser.setRep(rep);");
		out.println(indent+indent+"browser.show();");
		out.println(indent+"}");
		out.println();
		out.println(indent+"//--- run ---");
		out.println(indent+"public void run("+repositoryInterface+" rep) {");
		out.println(indent+indent+"setRepository(rep);");
		out.println(indent+indent+"replaceDefaultLifecycles();");
		out.println(indent+indent+"initialisePopulation();");
		out.println(indent+indent+"startBrowser();");
		out.println(indent+"}");
		out.println();
		out.println(indent+"//--- Main ---");
		out.println(indent+"public static void main(String args[]) {");
		out.println(indent+indent+"(new "+startupClass+"()).run(new "+repositoryClass+"());");
		out.println(indent+"}");
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
