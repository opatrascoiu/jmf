package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class ElementInterfTemplate {
	public ElementInterfTemplate(Model model, Context context, PrintWriter out) {
		this.model = model;
		this.context = context;
		this.out = out;
		initLocalProperties();
	}

	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.modelName = context.getNaming().getModelName();
		this.modelName = context.getNaming().getModelName();
		this.modelPackage = context.getNaming().getModelPackage();
		this.elementInterface = context.getNaming().getElementInterface(modelName);
	}
	
	/**
	  * Print model elemnt interface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(elementInterface, out);
		// Print the code
		// Print header
		out.println("package "+modelPackage+";");
		out.println();
		out.println("public interface "+elementInterface);
		if (context.isGenerateVisitor()) {
			out.println(indent+"extends "+context.getNaming().getVisitableInterface(modelName));
		}
		out.println("{");
		if (context.isGenerateID()) {
			out.println(indent+"/** Get the id */");
			out.println(indent+"public String getId();");
			out.println(indent+"/** Set the id */");
			out.println(indent+"public void setId(String id);");
			out.println();
		}
		if (context.isGenerateBidirectional()) {
			out.println(indent+"/** Delete */");
			out.println(indent+"public void delete();");
		}
		out.println(indent+"/** Override toString */");
		out.println(indent+"public String toString();");
//		if (context.isGenerateInvariant()) {
//			out.println();
//			out.println(indent+"/** Parse all invariants */");
//			out.println(indent+"public Boolean parseInvariants(ILog log);");
//			out.println(indent+"/** Evaluate all invariants */");
//			out.println(indent+"public Boolean evaluateInvariants(ILog log);");
//		}
		out.println("}");
	}

	//
	// Local properties
	//
	protected Model model;
	protected Context context;
	protected PrintWriter out;

	protected String indent;
	protected String modelName;
	protected String modelPackage;
	protected String elementInterface;
}
