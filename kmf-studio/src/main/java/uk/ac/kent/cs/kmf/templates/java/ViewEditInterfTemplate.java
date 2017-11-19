package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class ViewEditInterfTemplate {
	public ViewEditInterfTemplate(Model model, Context context, boolean edit, PrintWriter out) {
		this.model = model;
		this.context = context;
		this.edit = edit;
		this.out = out;
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.log = context.getLog();
		this.modelName = context.getNaming().getModelName();
		this.modelPackage = context.getNaming().getModelPackage();
		this.viewEditFrameClass = context.getNaming().getViewEditFrameClass(modelName);
		this.invokeMethodClass = context.getNaming().getInvokeMethodClass(modelName);
		this.viewInterface = context.getNaming().getViewVisitorInterface(modelName);
		this.viewClass = context.getNaming().getViewVisitorClass(modelName);
		this.editInterface = context.getNaming().getEditVisitorInterface(modelName);
		this.editClass = context.getNaming().getEditVisitorClass(modelName);
		this.indent = context.getIndent();
	}

	/**
	  * Print EditVisitor interface
	  */
	public void generate() {
		// Print generation stamp
		if (edit) {
			context.getNaming().putStamp(editInterface, out);
		} else {
			context.getNaming().putStamp(viewInterface, out);
		}
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import "+modelPackage+".*;");
		out.println();
		if (edit) {
			out.println("public interface "+editInterface);
		} else {
			out.println("public interface "+viewInterface);
		}
		out.println("  extends "+context.getNaming().getVisitorInterface(modelName));
		out.println("{");
		out.println("}");
	}

	//
	// Local properties
	//
	protected Model model;
	protected Context context;
	protected boolean edit;
	protected PrintWriter out;
	
	protected ILog log;
	protected String indent;
	protected String modelName;
	protected String modelPackage;
	protected String viewEditFrameClass;
	protected String invokeMethodClass;
	protected String viewInterface;
	protected String viewClass;
	protected String editInterface;
	protected String editClass;
}
