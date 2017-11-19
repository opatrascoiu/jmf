package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class InvariantsParseAllImplTemplate {
	public InvariantsParseAllImplTemplate(Model model, Context context, Map invariants, PrintWriter out) {
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
		this.interfaceName = context.getNaming().getParseAllVisitorInterface(modelName);
		this.className = context.getNaming().getParseAllVisitorClass(modelName);
	}

	/**
	  * Print ParseAll visitor implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(context.getNaming().getParseAllVisitorInterface(modelName), out);
		// Print the code
		out.println("package "+modelPackage+";");
		out.println();
		out.println("import java.util.*;");
		out.println();
		out.println("import uk.ac.kent.cs.kmf.util.*;");
		out.println("import uk.ac.kent.cs.ocl20.syntax.ast.contexts.*;");
		out.println("import uk.ac.kent.cs.ocl20.syntax.parser.*;");
		out.println();
		out.println("public class "+context.getNaming().getParseAllVisitorClass(modelName));
		out.println(indent+"extends "+context.getNaming().getVisitorClass(modelName));
		out.println(indent+"implements "+context.getNaming().getParseAllVisitorInterface(modelName));
		out.println("{");
		out.println(indent+"// Create an OCL parser");
		out.println(indent+"protected OclParser oclParser = new OclParserImpl();");
		// Add factory/class pairs
		Iterator i = context.getClasses().iterator();
		while (i.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier cls = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)i.next();
			if (context.getNaming().isStereotype(cls, "primitive")) {
//			} else if (CodeGenerator.isStereotype(cls, "external")) {
			} else {
				out.println(indent+"/** Visit class '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"//--- Unpack arguments ---");
				out.println(indent+indent+"ILog log = (ILog)((Map)data).get(\"log\");");
				out.println(indent+indent+"//--- Common variables ---");
				out.println(indent+indent+"String invariant;");
				out.println(indent+indent+"int errorNo;");
				out.println(indent+indent+"PackageDeclarationAS pkgDeclAS;");
				out.println(indent+indent+"log.reportMessage(\"Parsing invariants for \"+host+\"...\");");
				out.println(indent+indent+"try {");
				//--- For each invariant ---
				Iterator j = ((Collection)invariants.get(cls)).iterator();
				out.println(indent+indent+indent+"errorNo = log.getErrors();");
				while (j.hasNext()) {
					String invariant = (String)j.next();
					out.println(indent+indent+indent+"//--- Print invariant ---");
					out.println(indent+indent+indent+"invariant = \""+invariant+"\";");
					out.println(indent+indent+indent+"log.reportMessage(invariant);");
					out.println(indent+indent+indent+"pkgDeclAS = oclParser.parse(invariant, log);");
					out.println(indent+indent+indent+"if (pkgDeclAS == null) errorNo--;");
				}
				out.println(indent+indent+indent+"if (errorNo == log.getErrors()) {");
				out.println(indent+indent+indent+"log.reportMessage(\"No errors\");");
				out.println(indent+indent+indent+"}");
				out.println(indent+indent+"} catch (Exception e1) {");
				out.println(indent+indent+indent+"log.reportError(\"Runtime error: \", e1);");
				out.println(indent+indent+indent+"e1.printStackTrace(System.out);");
				out.println(indent+indent+"}");
				out.println(indent+indent+"return null;");
				out.println(indent+"}");
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
