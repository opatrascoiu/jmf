package uk.ac.kent.cs.ocl20;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.ocl20.semantics.analyser.OclSemanticAnalyser;
import uk.ac.kent.cs.ocl20.semantics.bridge.BridgeFactory;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.standard.lib.StdLibAdapter;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.PackageDeclarationAS;
import uk.ac.kent.cs.ocl20.syntax.parser.OclParser;
import uk.ac.kent.cs.ocl20.synthesis.ModelImplementationAdapter;
import uk.ac.kent.cs.ocl20.synthesis.OclCodeGenerator;
import uk.ac.kent.cs.ocl20.synthesis.OclEvaluator;
import uk.ac.kent.cs.ocl20.synthesis.RuntimeEnvironment;

/**
 * @author dha
 *
 */
public interface OclProcessor {
	/**
	 * Parse the passed input (string or reader) and build the AST.
	 * 
	 * A non null valued result implies a successful (error free) parse.
	 * 
	 */
	public PackageDeclarationAS parse(String str);
	public PackageDeclarationAS parse(String str, ILog log);
	public PackageDeclarationAS parse(String str, ILog log, boolean debug);
	public PackageDeclarationAS parse(Reader input);
	public PackageDeclarationAS parse(Reader input, ILog log);
	public PackageDeclarationAS parse(Reader input, ILog log, boolean debug);

	/**
	 * Semantic analysis of the passed input (AST, string, or reader)
	 * 
	 * The result contains a list of ContextDeclaration.
	 */
	public List analyse(PackageDeclarationAS pd);
	public List analyse(PackageDeclarationAS pd, ILog log);
	public List analyse(PackageDeclarationAS pd, Environment env, ILog log);
	public List analyse(PackageDeclarationAS pd, Environment env, ILog log, boolean debug);
	public List analyse(String str);
	public List analyse(String str, ILog log);
	public List analyse(String str, Environment env, ILog log);
	public List analyse(String str, Environment env, ILog log, boolean debug);
	public List analyse(Reader reader);
	public List analyse(Reader reader, ILog log);
	public List analyse(Reader reader, Environment env, ILog log);
	public List analyse(Reader reader, Environment env, ILog log, boolean debug);

	/**
	 * Evaluate the passed input as an ocl expression.
	 * 
	 * The result contains the value of the evaluated expression.
	 * 
	 */
	public List evaluate(String str);
	public List evaluate(String str, Object self);
	public List evaluate(String str, Object self, ILog log);
	public List evaluate(String str, Environment env, RuntimeEnvironment renv, ILog log);		
	public List evaluate(Reader reader, Object self);
	public List evaluate(Reader reader, Object self, ILog log);
	public List evaluate(Reader reader, Environment env, RuntimeEnvironment renv, ILog log);

	/**
	 * Generate code for an passed input (string or reader) 
	 * using the provided indentation
	 * 
	 * The result contains the list of Pairs (name of the variable which
	 * hold the result and the generated code).
	 * 
	 */
	public List generate(String str, String indent);
	public List generate(String str, String indent, ILog log);
	public List generate(String str, String indent, Environment env, ILog log);
	public List generate(String str, String indent, Environment env, ILog log, boolean debug);
	public List generate(Reader reader, String indent);
	public List generate(Reader reader, String indent, ILog log);
	public List generate(Reader reader, String indent, Environment env, ILog log);
	public List generate(Reader reader, String indent, Environment env, ILog log, boolean debug);
	
	/** Debug flag */
	public Boolean getDebug();
	public void setDebug(Boolean d);
	
	/** Log */
	public ILog getLog();
	public void setLog(ILog log);

	/** Adapters: standard library and bridge */
	public StdLibAdapter getStdLibAdapter();
	public String getStdLibAdapterName();
	public ModelImplementationAdapter getModelImplAdapter();
	public String getModelImplAdapterName();

	/** Factories: type and bridge */
	public TypeFactory getTypeFactory();
	public BridgeFactory getBridgeFactory();

	/** Processing components: parser, semantic analyser, evaluator and code generator */
	public OclParser getParser();
	public OclSemanticAnalyser getAnalyser();
	public OclEvaluator getEvaluator();
	public OclCodeGenerator getGenerator();

	/** Models */
	public java.util.Set getModels();
	public void setModels(java.util.Set models);
	public void addModel(Object model);

	/** 
	 * Generate the code for a class into a given file 
	 * 
	 * */	
	public void generate(Reader input, PrintWriter output, String pkgName, ILog log);
	/**
	 * Create a type environment
	 * that includes the passed Object as the passed name.
	 * ( additional objects can be added using 'setValue("name", object)' )
	 */
	public Environment environment();
	public Environment environment(String name, Object obj);

	/**
	 * Create a runtime environment
	 * that includes the passed Object as the passed name.
	 * ( additional objects can be added using 'setValue("name", object)' )
	 * 
	 */
	public RuntimeEnvironment runtimeEnvironment();
	public RuntimeEnvironment runtimeEnvironment(String name, Object obj);
}
