package uk.ac.kent.cs.ocl20.bridge4java;

import java.util.*;
import java.io.*;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.OclProcessorImpl;
import uk.ac.kent.cs.ocl20.syntax.parser.*;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.*;

import uk.ac.kent.cs.ocl20.semantics.analyser.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.semantics.model.types.*;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.*;

import uk.ac.kent.cs.ocl20.standard.types.*;
import uk.ac.kent.cs.ocl20.standard.lib.*;

import uk.ac.kent.cs.ocl20.synthesis.*;

import uk.ac.kent.cs.kmf.util.*;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */

public class JavaOclProcessorImpl
	extends OclProcessorImpl
	implements OclProcessor
{
	JavaBridgeFactory bridgeFactory = new JavaBridgeFactory(this);
	public BridgeFactory getBridgeFactory() {return bridgeFactory;}

	TypeFactory typeFactory = new TypeFactoryImpl(this);
	public TypeFactory getTypeFactory() {return typeFactory;}
	
	ModelImplementationAdapter modelImplAdapter = new JavaImplementationAdapter();
	public ModelImplementationAdapter getModelImplAdapter() {return modelImplAdapter; }

	StdLibAdapter stdLibAdapter = new StdLibAdapterImpl();
	public StdLibAdapter getStdLibAdapter() {return stdLibAdapter;	}
	
	public String getStdLibAdapterName() {return "StdLibAdapterImpl.INSTANCE"; }
	public String getModelImplAdapterName() {return "JavaImplementationAdapter.INSTANCE";}
	
	public OclParser parser = new OclParserImpl();
	public OclParser getParser() {return parser;}
	
	public OclSemanticAnalyser analyser = new OclSemanticAnalyserImpl(this, new OclSemanticAnalyserVisitorImpl(this), new OclDebugVisitorImpl(), null);
	public OclSemanticAnalyser getAnalyser() {return analyser;}
	
	public OclEvaluatorImpl evaluator = new OclEvaluatorImpl(this, new OclEvaluatorVisitorImpl(this));
	public OclEvaluator getEvaluator() {return evaluator;}
	
	public OclCodeGeneratorImpl generator = new OclCodeGeneratorImpl();
	public OclCodeGenerator getGenerator() {return generator;}
	
	public JavaOclProcessorImpl(Set ps, ILog log) {
		super(ps, log);
	}
	
	public JavaOclProcessorImpl(String pkgName, ILog log) {
		super(null, log);
		try {
			super.models = new HashSet();
			models.add(Package.getPackage(pkgName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Initialise the model 
	 * */
	public List associations = new Vector();

	/**
	 * Semantic analysis of the passed package declaration from ast
	 * with output going to the passed log object.
	 * 
	 * The result contains a list of ContextDeclaration.
	 */
	public List analyse(PackageDeclarationAS pd, Environment env, ILog log) {
		if (pd == null) return null;
		// Check model
		if (getModels() == null || getModels().isEmpty()) {
			log.reportWarning("Model is not loaded");
		}
		//--- For each class context ---
		Iterator i = pd.getContextDecls().iterator();
		List contextDecls = new Vector();
		while (i.hasNext()) {
			ContextDeclarationAS contextDeclAS = (ContextDeclarationAS)i.next();
			if (contextDeclAS instanceof ClassifierContextDeclAS) {
				//--- Get context name from constraint ---
				List context = ((ClassifierContextDeclAS)contextDeclAS).getPathName();
				ModelElement obj = env.lookupPathName(context);
				if (obj != null && obj instanceof OclModelElementType ) {
					OclModelElementType selfType = (OclModelElementType)obj;
					env = env.addVariableDeclaration("self", selfType, Boolean.TRUE);
				} else {
					if (getDebug().booleanValue()) {
						int n = context.size();
						String contextName = "";
						for(int j=0; j<n-1; j++) {
							contextName += (String)context.get(j);
							contextName += "::"; 
						}
						contextName += context.get(n-1);
						log.reportError("Cannot find context '"+contextName+"'");				
					}
				}
				//--- Semantic analysis
				ContextDeclaration contextDecl = analyser.analyse(contextDeclAS, env, log, getDebug().booleanValue());
				contextDecls.add(contextDecl);
			}
		}
		if (log.hasErrors()) return null;
		return contextDecls;
	}

	/**
	 * Generate code for the constraints stored into the input file 
	 * into the output file and given package 
	 * using the provided log and
	 * a given layout
	 * 
	 */
	public void generate(Reader input, PrintWriter output, String pkgName, ILog log) {
		try {
			//--- Print class header ---
			output.println("package "+pkgName+";");
			output.println();
			output.println("import java.util.*;");
			output.println();
			output.println("import uk.ac.kent.cs.ocl20.standard.lib.*;");
			output.println("import uk.ac.kent.cs.ocl20.bridge4java.*;");
			output.println();
			output.println("public class Invariants {");			
			int invCount = 0;
			String tab = "\t";
			try {
				// Analyse input
				List cs = this.analyse(input, log);
				if (cs != null) {
					// Group all context declarations according to context name
					Map contexts = new HashMap();
					Iterator i = cs.iterator();
					// For each context declaration
					while (i.hasNext()) {
						// Generate code
						ContextDeclaration decl = (ContextDeclaration)i.next();
						// For each context at class level
						if (decl != null && decl instanceof ClassifierContextDecl) {
							String selfType = decl.getReferredNamespace().getFullName(".");
							if (contexts.get(selfType) == null) contexts.put(selfType, new Vector());
							((List)contexts.get(selfType)).add(decl);
						}
					}
					// Generate code key in contexts
					i = contexts.keySet().iterator();
					while (i.hasNext()) {
						String selfType = (String)i.next();
						// Compute class name
						int pos = selfType.lastIndexOf('.');
						String className = selfType;
						if (pos != -1) className = selfType.substring(pos+1, selfType.length());
						// Store the name of all invariants
						List invNames = new Vector();
						// Generate class container
						output.println(tab+"public static class " + className + " {");
						// For each classifier context declaration 				
						List list = (List)contexts.get(selfType);
						Iterator j = list.iterator();
						while (j.hasNext()) {
							ContextDeclaration decl = (ContextDeclaration)j.next();
							// For each invariant
							List cons = decl.getConstraint();
							Iterator k = cons.iterator();
							while (k.hasNext()) {
								// Compute code and result
								Constraint con = (Constraint)k.next();
								Pair pair = this.getGenerator().generate(con, tab+tab+tab+tab, this);
								String result = (String)pair.getFirst();
								String code = (String)pair.getSecond();
								// Compute invariant name
								String invName = con.getName();
								if (invName == null || invName.length() == 0) invName = "inv$"+invCount++;
								// Store name
								invNames.add(invName);
								// outputput the corresponding method
								output.println(tab+tab+"public static Object "+ invName +"("+selfType+" self) {");
								output.println(tab+tab+tab+"try {");
								output.print(code);				
								output.println(tab+tab+tab+tab+"// return result");
								output.println(tab+tab+tab+tab+"if ("+result+" != null) return "+result+";");
								output.println(tab+tab+tab+tab+"else return "+this.getStdLibAdapterName()+".Undefined();");
								output.println(tab+tab+tab+"} catch (Exception e) {");
								output.println(tab+tab+tab+tab+"return "+this.getStdLibAdapterName()+".Undefined();");
								output.println(tab+tab+tab+"}");
								output.println(tab+tab+"}");
							}	
						}
						// Output the evaluate all method
						output.println(tab+tab+"public static List evaluateAll("+selfType+" self) {");
						output.println(tab+tab+tab+"List result = new Vector();");
						j = invNames.iterator();
						while (j.hasNext()) {
							String invName = (String)j.next();
							output.println(tab+tab+tab+"result.add("+invName+"(self));");
						}
						output.println(tab+tab+tab+"return result;");
						output.println(tab+tab+"}");
						// Close class
						output.println(tab+"}");
					}					
				}
			} catch (Exception e) {
				if (this.getDebug().booleanValue()) e.printStackTrace();
			}
			//--- Close class ---
			output.println("}");
		} catch (Exception e) {
			if (this.getDebug().booleanValue()) e.printStackTrace();
		}
	}
}
