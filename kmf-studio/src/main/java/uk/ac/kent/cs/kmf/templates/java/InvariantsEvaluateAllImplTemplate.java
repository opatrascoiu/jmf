package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.Pair;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class InvariantsEvaluateAllImplTemplate {
	public InvariantsEvaluateAllImplTemplate(Model model, Context context, Map invariants, PrintWriter out) {
		this.model = model;
		this.context = context;
		this.invariants = invariants;
		this.out = out;
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
	  * Print EvaluateAll visitor implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(context.getNaming().getEvaluateAllVisitorInterface(modelName), out);
		// Print the code
		out.println("package "+modelPackage+";");
		out.println();
		out.println("import java.util.*;");
		out.println();
		out.println("import uk.ac.kent.cs.kmf.util.*;");
		out.println("import uk.ac.kent.cs.ocl20.standard.lib.*;");
		out.println("import uk.ac.kent.cs.ocl20.bridge4kmf.*;");
		out.println();
		out.println("public class "+context.getNaming().getEvaluateAllVisitorClass(modelName));
		out.println(indent+"extends "+context.getNaming().getVisitorClass(modelName));
		out.println(indent+"implements "+context.getNaming().getEvaluateAllVisitorInterface(modelName));
		out.println("{");
		// Set log to processor
		context.getOclProcessor().setLog(log);
		// Add factory/class pairs
		Iterator i = context.getClasses().iterator();
		while (i.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier cls = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)i.next();
			if (context.getNaming().isStereotype(cls, "primitive")) {
//			} else if (CodeGenerator.isStereotype(cls, "external")) {
			} else {
				String interfaceName = context.getNaming().getFullInterfaceName(cls);
				log.reportMessage("Generating code for invariants for '"+interfaceName+"' ...");
				int errorNo = log.getErrors();

				out.println(indent+"/** Visit class '"+interfaceName+"' */");
				out.println(indent+"public Object visit("+interfaceName+" self, Object data) {");
				out.println(indent+indent+"//--- Unpack arguments ---");
				out.println(indent+indent+"ILog log = (ILog)((Map)data).get(\"log\");");
				out.println(indent+indent+"//--- Common variables ---");
				out.println(indent+indent+"String invariant;");
				out.println(indent+indent+"log.reportMessage(\"Evaluating invariants for \"+self+\"...\");");
				//--- For each invariant ---
				Iterator j = ((Collection)invariants.get(cls)).iterator();
				while (j.hasNext()) {
					String invariant = (String)j.next();
					log.reportMessage(invariant);
					out.println(indent+indent+"//--- Print invariant ---");
					out.println(indent+indent+"invariant = \""+invariant+"\";");
					out.println(indent+indent+"log.printMessage(\"  \"+invariant);");
					out.println();
					out.println(indent+indent+"try {");
					boolean hasErrors = false;
					try {
						int no1 = log.getErrors();
						List pairs = context.getOclProcessor().generate(invariant, indent+indent+indent, log);
						int no2 = log.getErrors();
						hasErrors = no1 != no2 || pairs == null || pairs.size() == 0;
						if (!hasErrors) {
							Pair pair = (Pair)pairs.get(0);
							String resultVar = pair.getFirst().toString();
							String code = (String)pair.getSecond();
							// Replace model name with root+modelName
							String offset = context.getPackageOffset(); 
							if (offset != null && offset.length() != 0)
								code = code.replaceAll(modelName+".", offset+"."+modelName+"."); 
							out.println(code);
							out.println(indent+indent+indent+"//--- Print result ---");
							out.println(indent+indent+indent+"log.reportMessage(\": \"+"+resultVar+");");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					out.println();
					out.println(indent+indent+"} catch (Exception e) {");
					out.println(indent+indent+indent+"//--- Print result ---");
					out.println(indent+indent+indent+"log.reportMessage(\": undefined\");");
					out.println(indent+indent+"}");
					if (j.hasNext()) out.println();
				}
				out.println(indent+indent+"return null;");
				out.println(indent+"}");

				if (errorNo == log.getErrors()) {
//					log.reportMessage("No errors");
				}
			}
		}
		out.println("}");
	}

	/** Initialize the self  */
	protected uk.ac.kent.cs.ocl20.semantics.bridge.Classifier getSelf(Environment env, String classPathName) {
		if (env == null || classPathName == null || classPathName.length() == 0) return null;
		//--- Make a list of strings ---
		java.util.List names = new Vector();
		int pos;
		classPathName = classPathName.replaceAll("[.]", "::");
		while ((pos = classPathName.indexOf("::")) != -1) {
			names.add(classPathName.substring(0, pos));
			classPathName = classPathName.substring(pos+2, classPathName.length());
		}
		names.add(classPathName);
		//--- Search classifier ---
		uk.ac.kent.cs.ocl20.semantics.bridge.ModelElement self = env.lookupPathName(names);
		if (self instanceof uk.ac.kent.cs.ocl20.semantics.bridge.Classifier) return (uk.ac.kent.cs.ocl20.semantics.bridge.Classifier)self;
		else return null;
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
