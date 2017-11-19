package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.Iterator;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class VisitorInterfTemplate {
	public VisitorInterfTemplate(Model model, Context context, PrintWriter out) {
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
	  * Print Visitor interface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(context.getNaming().getVisitorInterface(modelName), out);
		// Print the code
		out.println("package "+modelPackage+";");
		out.println();
		out.println("public interface "+context.getNaming().getVisitorInterface(modelName)+" {");
		// Add default factory
		if (context.isGenerateFactory()) {
			out.println(indent+"/** Visit '"+modelPackage+"."+context.getNaming().getFactoryInterface(modelName)+"' */");
			out.println(indent+"public Object visit("+modelPackage+"."+context.getNaming().getFactoryInterface(modelName)+" host, Object data);");
		}
		Iterator i = context.getClasses().iterator();
		// Add factory/class pairs
		while (i.hasNext()) {
			Classifier cls = (Classifier)i.next();
			if (context.getNaming().isStereotype(cls, "primitive")) {
//			} else if (CodeGenerator.isStereotype(cls, "external")) {
			} else {
				if (cls.getIsAbstract().booleanValue() || !context.isGenerateFactory()) {
				} else {
					out.println(indent+"/** Visit factory for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
					out.println(indent+"public Object visit("+context.getNaming().getFullFactoryInterfaceName(cls)+" host, Object data);");
				}
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
	protected PrintWriter out;
	
	protected ILog log;
	protected String indent;
	protected String modelName;
	protected String modelPackage;
	protected String interfaceName;
}
