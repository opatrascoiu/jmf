package uk.ac.kent.cs.ocl20.bridge4kmf;

import java.io.*;
import java.util.*;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.OclProcessorImpl;
import uk.ac.kent.cs.ocl20.semantics.analyser.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.*;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.standard.lib.*;
import uk.ac.kent.cs.ocl20.standard.types.TypeFactoryImpl;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.*;
import uk.ac.kent.cs.ocl20.syntax.parser.*;
import uk.ac.kent.cs.ocl20.synthesis.*;
import uk.ac.kent.cs.kmf.util.*;
import uk.ac.kent.cs.kmf.util.XMIToUMLLoader;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */

public class KmfOclProcessorImpl
	extends OclProcessorImpl
	implements OclProcessor
{
	/** Constructors */
	public KmfOclProcessorImpl() {
		super(new HashSet(), new OutputStreamLog(System.out));
		// Set factories
		bridgeFactory = new KmfBridgeFactoryImpl(this);
		typeFactory = new TypeFactoryImpl(this);
	}
	public KmfOclProcessorImpl(ILog log) {
		super(new HashSet(), log);
		// Set factories
		bridgeFactory = new KmfBridgeFactoryImpl(this);
		typeFactory = new TypeFactoryImpl(this);
	}
	public KmfOclProcessorImpl(Set models, ILog log) {
		super(models, log);
		// Set factories
		bridgeFactory = new KmfBridgeFactoryImpl(this);
		typeFactory = new TypeFactoryImpl(this);
	}
	public KmfOclProcessorImpl(String xmiFileName, ILog log) {
		super(new HashSet(), log);
		// Set factories
		bridgeFactory = new KmfBridgeFactoryImpl(this);
		typeFactory = new TypeFactoryImpl(this);
		// Add model
		initModel(xmiFileName, log);
	}
	public KmfOclProcessorImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model model, ILog log) {
		super(new HashSet(), log);
		// Set factories
		bridgeFactory = new KmfBridgeFactoryImpl(this);
		typeFactory = new TypeFactoryImpl(this);
		// Add model and add missing links 
		super.getModels().add(model);		
		computeAssociations(model);
	}

	/** Factories: bridge and type */
	protected KmfBridgeFactoryImpl bridgeFactory;
	public BridgeFactory getBridgeFactory() {return bridgeFactory;}
	public void setBridgeFactory(BridgeFactory bridgeFactory) {this.bridgeFactory = (KmfBridgeFactoryImpl)bridgeFactory;}
	protected TypeFactory typeFactory;
	public TypeFactory getTypeFactory() {return typeFactory;}
	
	/** Adapters: standard library and model */
	protected StdLibAdapter stdLibAdapter = new StdLibAdapterImpl();
	public StdLibAdapter getStdLibAdapter() {return stdLibAdapter;	}
	public String stdLibAdapterName = "StdLibAdapterImpl.INSTANCE";
	public String getStdLibAdapterName() {return stdLibAdapterName; }
	protected ModelImplementationAdapter modelImplAdapter = new KmfImplementationAdapterImpl();
	public ModelImplementationAdapter getModelImplAdapter() {return modelImplAdapter; }
	public String modelImplAdapterName = "KmfImplementationAdapterImpl.INSTANCE";
	public String getModelImplAdapterName() { return modelImplAdapterName;}

	/** Processing members: parser, semantic analyser, evaluator, and code generator */	
	public OclParser parser = new OclParserImpl();
	public OclParser getParser() {return parser;}
	public OclSemanticAnalyser analyser = new OclSemanticAnalyserImpl(this, new OclSemanticAnalyserVisitorImpl(this), new OclDebugVisitorImpl(), null);
	public OclSemanticAnalyser getAnalyser() {return analyser;}
	public OclEvaluator evaluator = new OclEvaluatorImpl(this, new OclEvaluatorVisitorImpl(this));
	public OclEvaluator getEvaluator() {return evaluator;}
	public OclCodeGenerator generator = new OclCodeGeneratorImpl();
	public OclCodeGenerator getGenerator() {return generator;}
	
	/** 
	 * Initialise the model 
	 * */
	public List associations = new Vector();
	public void initModel(String xmiFileName, ILog log) {
		loadModel(xmiFileName, log);
	}
	public Pair loadModel(String xmiFileName, ILog log) {
		Pair pair = null;
		try {
			//--- Load the model ---
			pair = (new XMIToUMLLoader()).loadModel(xmiFileName, log);
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model model = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model)pair.getFirst();
			getModels().add(model);
			//--- Compute associations ---
			computeAssociations((uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model)model);
		} catch (Exception e) {
		}
		return pair;
	}
	public void addModel(Object model) {
		getModels().add(model);
		//--- Compute associations ---
		computeAssociations((uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model)model);
	}

	/** Compute associations */
	public void computeAssociations(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace pkg) {
		// For each element within a package
		Iterator i = pkg.getOwnedElement().iterator();
		while (i.hasNext()) {
			Object o = i.next();
			if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association) {
				associations.add(o);
			}
			else if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package) {
				// Link to father for XMI 1.2
				((uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package)o).setNamespace_(pkg); 
				// Process the nested package	        	
				computeAssociations((uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package)o);
			}
		}
	}

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
	 * generate code for the constraints stored into the input file 
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
			output.println("import uk.ac.kent.cs.ocl20.bridge4kmf.*;");
			output.println();
			output.println("public class Invariants {");			
			int invCount = 0;
			String tab = "\t";
			try {
				// analyse input
				List cs = this.analyse(input, log);
				if (cs != null) {
					// Group all context declarations according to context name
					Map contexts = new HashMap();
					Iterator i = cs.iterator();
					// For each context declaration
					while (i.hasNext()) {
						// generate code
						ContextDeclaration decl = (ContextDeclaration)i.next();
						// For each context at class level
						if (decl != null && decl instanceof ClassifierContextDecl) {
							String selfType = decl.getReferredNamespace().getName();
							if (contexts.get(selfType) == null) contexts.put(selfType, new Vector());
							((List)contexts.get(selfType)).add(decl);
						}
					}
					// generate code key in contexts
					i = contexts.keySet().iterator();
					while (i.hasNext()) {
						String selfType = (String)i.next();
						// Compute class name
						int pos = selfType.lastIndexOf('.');
						String className = selfType;
						if (pos != -1) className = selfType.substring(pos+1, selfType.length());
						// Store the name of all invariants
						List invNames = new Vector();
						// generate class container
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
								output.println(tab+tab+tab+tab+"else return "+this.stdLibAdapterName+".Undefined();");
								output.println(tab+tab+tab+"} catch (Exception e) {");
								output.println(tab+tab+tab+tab+"return "+this.stdLibAdapterName+".Undefined();");
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

	/**
	 *  Get the UML Class_
	 */	
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier GetUMLClassifier(uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package pkg, String classPathName) {
		//--- Create an Environment ---
		Environment env = bridgeFactory.buildEnvironment(pkg);
		return GetUMLClassifier(env, classPathName);	
	}

	/**
	 *  Get the UML Class_
	 */	
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier GetUMLClassifier(uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model model, List classPathName) {
		//--- Create an Environment ---
		Environment env = bridgeFactory.buildEnvironment(model);
		return GetUMLClassifier(env, classPathName);	
	}
	
	/**
	 * Get the UML Class_
	 */
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier GetUMLClassifier(Environment env, String classPathName) {
		//--- Make a list of strings ---
		String names[] = null;
		if (classPathName.indexOf("::") != -1) names = classPathName.split("::");
		else if (classPathName.indexOf(".") != -1) names = classPathName.split("\\.");
		else names = new String[] { classPathName };
		List list = new Vector();
		for(int i=0; i<names.length; i++) list.add(names[i]);
		return GetUMLClassifier(env, list);
	}
	
	/**
	 * Get the UML Class_
	 */
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier GetUMLClassifier(Environment env, List list) {
		//--- Search in the environment ---
		uk.ac.kent.cs.ocl20.semantics.bridge.Classifier cls = (Classifier)env.lookupPathName(list);
		//--- Return the wrapped UML element ---
		if (cls != null) {
			if (cls instanceof OclModelElementTypeImpl) 
				return ((OclModelElementTypeImpl)cls).getImpl();
			if (cls instanceof EnumerationImpl) 
				return ((EnumerationImpl)cls).getImpl();
			
		}
		return null;
	}
}
