package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;

/**
 * @author Octavian Patrascoiu
 *
 */
public class ClassVisitorInterfTemplate {
	public ClassVisitorInterfTemplate(Classifier cls, Context context, PrintWriter out) {
		this.cls = cls;
		this.context = context;
		this.out = out;
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.modelName = context.getNaming().getModelName();
		this.fullPkgName = context.getNaming().getFullPackageName(cls);
		this.visitorInterfaceName = context.getNaming().getFactoryVisitorInterfaceName(cls);
	}

	/**
	  * Print a visitor interface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(visitorInterfaceName, out);
		// Print the code
		// Print header
		out.println("package "+fullPkgName+";");
		out.println();
		// Compute extended interfaces
		List interfaces = new Vector();
		interfaces.add(context.getNaming().getFactoryInterface(modelName));
		// Normal type
		out.println("public interface "+visitorInterfaceName+"\n{");
		out.println(indent+"/** Visit '"+context.getNaming().getFullInterfaceName(cls)+"' */");
		out.println(indent+"public Object visit("+context.getNaming().getFullInterfaceName(cls)+" host, Object data);");
		out.println("}");
	}

	//
	// Local properties
	//
	protected Classifier cls;
	protected Context context;
	protected PrintWriter out;

	protected String indent;
	protected String modelName;
	protected String fullPkgName;
	protected String visitorInterfaceName;
}
