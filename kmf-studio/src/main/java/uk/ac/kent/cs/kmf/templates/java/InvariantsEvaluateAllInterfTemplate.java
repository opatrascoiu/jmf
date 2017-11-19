package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class InvariantsEvaluateAllInterfTemplate {
	public InvariantsEvaluateAllInterfTemplate(Model model, Context context, Map invariants, PrintWriter out) {
		this.model = model;
		this.context = context;
		this.invariants = invariants;
		this.out = out;
		this.log = context.getLog();
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.log = context.getLog();
		this.modelName = context.getNaming().getModelName();
		this.modelPackage = context.getNaming().getModelPackage();
		this.interfaceName = context.getNaming().getEvaluateAllVisitorInterface(modelName);
		this.className = context.getNaming().getEvaluateAllVisitorClass(modelName);
	}

	/**
	  * Print EvaluateAll visitor interface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(context.getNaming().getEvaluateAllVisitorInterface(modelName), out);
		// Print the code
		out.println("package "+modelPackage+";");
		out.println();
		out.println("public interface "+context.getNaming().getEvaluateAllVisitorInterface(modelName));
		out.println("	extends "+context.getNaming().getVisitorInterface(modelName));
		out.println("{");
		Iterator i = context.getClasses().iterator();
		// Add factory/class pairs
		while (i.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier cls = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)i.next();
			if (context.getNaming().isStereotype(cls, "primitive")) {
//			} else if (CodeGenerator.isStereotype(cls, "external")) {
			} else {
				out.println(indent+"/** Visit class '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullInterfaceName(cls)+" host, Object data);");
			}
		}
		out.println("}");
	}


	//
	// Local properties
	//
	protected Model model;
	protected Context context;
	protected Map invariants;
	protected PrintWriter out;
	
	protected ILog log;
	protected String indent;
	protected String modelName;
	protected String modelPackage;
	protected String interfaceName;
	protected String className;
}
