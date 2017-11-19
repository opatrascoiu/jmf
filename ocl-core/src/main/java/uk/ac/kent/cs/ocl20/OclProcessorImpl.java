package uk.ac.kent.cs.ocl20;

import java.io.Reader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;
import uk.ac.kent.cs.ocl20.semantics.bridge.Namespace;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ContextDeclaration;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ContextDeclarationAS;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.PackageDeclarationAS;
import uk.ac.kent.cs.ocl20.synthesis.RuntimeEnvironment;
import uk.ac.kent.cs.ocl20.synthesis.RuntimeEnvironmentImpl;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */

// OclProcessorImpl is abstract becuase implementation TypeFactory, Evaluator, Generator etc 
// dependens (directly or indirectly) on the BridgeFactory which is bridge specific.
public abstract class OclProcessorImpl
	implements OclProcessor
{
	/** Constructors */	
	public OclProcessorImpl(ILog log) {
		this.log = log;
		this.models = new HashSet();
	}
	public OclProcessorImpl(Set models, ILog log) {
		this.log = log;
		this.models = models;
	}
	
	/**
	 * Parse the passed input (string, or reader) and 
	 * build the abstract syntax tree: PackageDeclarationAS.
	 * Errors are reported to log. A debug/release flag is provided.
	 * If the context is not specified it is assumed to be the OclVoid
	 * 
	 * A null valued result ie returned if an error is found.
	 * 
	 */
	public PackageDeclarationAS parse(String str) {
		return parse(str, getLog());
	}
	public PackageDeclarationAS parse(String str, ILog log) {
		return parse(str, log, this.getDebug().booleanValue());
	}
	public PackageDeclarationAS parse(String str, ILog log, boolean debug) {
		if (!str.startsWith("context")) str = "context OclVoid inv: "+str;
		log.resetErrors();
		PackageDeclarationAS pd = this.getParser().parse(str, log, debug);
		if (log.hasErrors()) return null;
		return pd;
	}
	public PackageDeclarationAS parse(Reader input) {
		return parse(input, getLog());
	}
	public PackageDeclarationAS parse(Reader input, ILog log) {
		return this.getParser().parse(input, log, this.getDebug().booleanValue());
	}
	public PackageDeclarationAS parse(Reader input, ILog log, boolean debug) {
		PackageDeclarationAS pd = this.getParser().parse(input, log, debug);
		log.resetErrors();
		if (log.hasErrors()) return null;
		if (log.hasErrors()) return null;
		return pd;
	}

	/**
	 * Semantic analysis of input (AST, string, or reader)
	 *  
	 * The result contains a list of ContextDeclaration.
	 * 
	 */
	public List analyse(PackageDeclarationAS pd) {
		return analyse(pd, getLog());
	}
	public List analyse(PackageDeclarationAS pd, ILog log) {
		//--- Create an environment ---
		Environment env = this.environment();
		return analyse(pd, env, log);
	}
	public  List analyse(PackageDeclarationAS pd, Environment env, ILog log) {
		return analyse(pd, env, log, getDebug().booleanValue());
	}
	public  List analyse(PackageDeclarationAS pd, Environment env, ILog log, boolean debug) {
		if (pd == null) return null;
		//--- For each class context ---
		Iterator i = pd.getContextDecls().iterator();
		List contextDecls = new Vector();
		while (i.hasNext()) {
			ContextDeclarationAS contextDeclAS = (ContextDeclarationAS)i.next();
			//--- Semantic analysis
			ContextDeclaration contextDecl = getAnalyser().analyse(contextDeclAS, env, log, getDebug().booleanValue());
			contextDecls.add(contextDecl);
		}
		if (log.hasErrors()) return null;
		return contextDecls;
	}
	public List analyse(String str) {
		return analyse(str, getLog());
	}
	public List analyse(String str, ILog log) {
		//--- Create an environment ---
		Environment env = this.environment();
		return analyse(str, env, log);
	}
	public List analyse(String str, Environment env, ILog log) {
		return analyse(str, env, log, getDebug().booleanValue());
	}
	public List analyse(String str, Environment env, ILog log, boolean debug) {
		PackageDeclarationAS pd = parse(str, log, debug);
		return analyse(pd, env, log, debug);
	}
	public List analyse(Reader reader) {
		return analyse(reader, getLog());
	}
	public List analyse(Reader reader, ILog log) {
		//--- Create an environment ---
		Environment env = this.environment();
		return analyse(reader, env, log);
	}
	public List analyse(Reader reader, Environment env, ILog log) {
		return analyse(reader, env, log, getDebug().booleanValue());
	}
	public List analyse(Reader reader, Environment env, ILog log, boolean debug) {
		PackageDeclarationAS pd = parse(reader, log, debug);
		return analyse(pd, env, log, debug);
	}

	/**
	 * Evaluate the passed input into the self context.
	 * If self is missing it is assumed to me Undefined
	 * 
	 * The result contains the value of the evaluated expression.
	 * 
	 */
	public List evaluate(String str) {
		return evaluate(str, this.getStdLibAdapter().Undefined());
	}
	public List evaluate(String str, Object self) {
		return evaluate(str, self, getLog());
	}
	public List evaluate(String str, Object self, ILog log) {
		List cs = analyse(str, log);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getEvaluator().evaluate(decl, this.runtimeEnvironment("self", self), log); 
			result.addAll(declRes);
		}
		return result;
	}
	public List evaluate(String str, Environment env, RuntimeEnvironment renv, ILog log) {
		List cs = analyse(str, env, log);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getEvaluator().evaluate(decl, renv, log); 
			result.addAll(declRes);
		}
		return result;
	}
	public List evaluate(Reader reader, Object self) {
		ILog log = getLog();
		return evaluate(reader, log);
	}
	public List evaluate(Reader reader, Object self, ILog log) {
		List cs = analyse(reader, log);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getEvaluator().evaluate(decl, this.runtimeEnvironment("self", self), log); 
			result.addAll(declRes);
		}
		return result;
	}
	public List evaluate(Reader reader, Environment env, RuntimeEnvironment renv, ILog log) {
		List cs = analyse(reader, env, log);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getEvaluator().evaluate(decl, renv, log); 
			result.addAll(declRes);
		}
		return result;
	}

	/**
	 * Generate code for an input passed as a string 
	 * with provided indentation
	 * 
	 * The result contains the list of Pairs.
	 * 
	 */
	public List generate(String str, String indent) {
		ILog log = getLog();
		List cs = analyse(str);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getGenerator().generate(decl, indent, this); 
			result.addAll(declRes);
		}
		return result;
	}
	public List generate(String str, String indent, ILog log) {
		List cs = analyse(str, log);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getGenerator().generate(decl, indent, this); 
			result.addAll(declRes);
		}
		return result;
	}
	public List generate(String str, String indent, Environment env, ILog log) {
		return generate(str, indent, env, log, getDebug().booleanValue());
	}
	public List generate(String str, String indent, Environment env, ILog log, boolean debug) {
		List cs = analyse(str, env, log, debug);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getGenerator().generate(decl, indent, this); 
			result.addAll(declRes);
		}
		return result;
	}
	public List generate(Reader reader, String indent) {
		ILog log = getLog();
		List cs = analyse(reader, log);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getGenerator().generate(decl, indent, this); 
			result.addAll(declRes);
		}
		return result;
	}
	public List generate(Reader reader, String indent, ILog log) {
		List cs = analyse(reader, log);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getGenerator().generate(decl, indent, this); 
			result.addAll(declRes);
		}
		return result;
	}
	public List generate(Reader reader, String indent, Environment env, ILog log) {
		return generate(reader, indent, env, log, getDebug().booleanValue());
	}
	public List generate(Reader reader, String indent, Environment env, ILog log, boolean debug) {
		List cs = analyse(reader, env, log);
		if (cs == null) return null;
		List result = new Vector();
		Iterator i = cs.iterator();
		while (i.hasNext()) {
			ContextDeclaration decl = (ContextDeclaration)i.next();
			List declRes = this.getGenerator().generate(decl, indent, this); 
			result.addAll(declRes);
		}
		return result;
	}

	/** Debug/release flag */
	protected Boolean debug = Boolean.FALSE;
	public void setDebug(Boolean d) {debug = d;}
	public Boolean getDebug() { return debug; }
	
	/** Log */
	protected ILog log = new OutputStreamLog(System.out);
	public ILog getLog() { 
		if (log == null) log = new OutputStreamLog(System.out);
		return log; 
	}
	public void setLog(ILog l) { log = l; }

	/** 
	 * Initialise the models
	 * */
	protected Set models = new HashSet();
	public Set getModels() {return models;}
	public void setModels(Set models) { this.models = models; }
	public void addModel(Object model) { this.models.add(model); }

	/**
	 * Creates an environment for 'self'  
	 * ( additional objects can be added using 'setValue("name", object)' )
	 */
	public Environment environment(String selfName, Object selfType) {
		Classifier bridgeType = getBridgeFactory().buildClassifier(selfType);
		Environment env = this.environment();
		return env.addVariableDeclaration(selfName, bridgeType, Boolean.TRUE);
	}

	public Environment environment() {
		Environment env = getBridgeFactory().buildEnvironment();
		// Add basicTypes;
		env = env.addElement("OclAny", getTypeFactory().buildOclAnyType(), Boolean.FALSE);
		env = env.addElement("OclVoid", getTypeFactory().buildVoidType(), Boolean.FALSE);
		env = env.addElement("Real", getTypeFactory().buildRealType(), Boolean.FALSE);
		env = env.addElement("Integer", getTypeFactory().buildIntegerType(), Boolean.FALSE);
		env = env.addElement("String", getTypeFactory().buildStringType(), Boolean.FALSE);
		env = env.addElement("Boolean", getTypeFactory().buildBooleanType(), Boolean.FALSE);
		Iterator i = getModels().iterator();
		while (i.hasNext()) {
			Namespace ns = getBridgeFactory().buildNamespace(i.next());
			env = env.addNamespace(ns);
		}
		return env;
	}

	/**
	 * Create a runtime environment
	 * that includes the passed EObject as the passed name.
	 * ( additional objects can be added using 'setValue("name", object)' )
	 * 
	 */
	public RuntimeEnvironment runtimeEnvironment(String name, Object eobj) {
		RuntimeEnvironment renv = new RuntimeEnvironmentImpl();
		renv.setValue(name, eobj);
		return renv;
	}
	public RuntimeEnvironment runtimeEnvironment() {
		RuntimeEnvironment renv = new RuntimeEnvironmentImpl();
		return renv;
	}
}
