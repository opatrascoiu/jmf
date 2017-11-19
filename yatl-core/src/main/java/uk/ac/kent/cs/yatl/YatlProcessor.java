package uk.ac.kent.cs.yatl;

import java.io.Reader;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.yatl.evaluation.YatlInterpreter;
import uk.ac.kent.cs.yatl.semantics.YatlSemanticAnalyser;
import uk.ac.kent.cs.yatl.syntax.parser.YatlParser;
import uk.ac.kent.cs.yatl.syntax.transformations.Unit;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.bridge.BridgeFactory;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.standard.lib.StdLibAdapter;
import uk.ac.kent.cs.ocl20.synthesis.ModelImplementationAdapter;
import uk.ac.kent.cs.ocl20.synthesis.RuntimeEnvironment;

/**
 * @author Octavian Patrascoiu
 *
 */
public interface YatlProcessor {
	/**
	 * Parse the passed input (string, reader) and build a Unit.
	 * A non null valued result implies a successful (error free) parse.
	 * 
	 */
	public Unit parse(String str);
	public Unit parse(String str, ILog log);
	public Unit parse(String str, ILog log, boolean debug);
	public Unit parse(Reader input);
	public Unit parse(Reader input, ILog log);
	public Unit parse(Reader input, ILog log, boolean debug);

	/**
	 * Semantic analysis of the passed Unit, with output going to the log.
	 * 
	 */
	public Unit analyse(Unit u);
	public Unit analyse(Unit u, ILog log);
	public Unit analyse(Unit u, ILog log, boolean debug);
	public Unit analyse(Unit u, Environment env, ILog log);
	public Unit analyse(Unit u, Environment env, ILog log, boolean debug);
	public Unit analyse(String str);
	public Unit analyse(String str, ILog log);
	public Unit analyse(String str, ILog log, boolean debug);
	public Unit analyse(String str, Environment env, ILog log);
	public Unit analyse(String str, Environment env, ILog log, boolean debug);
	public Unit analyse(Reader reader);
	public Unit analyse(Reader reader, ILog log);
	public Unit analyse(Reader reader, ILog log, boolean debug);
	public Unit analyse(Reader reader, Environment env, ILog log);
	public Unit analyse(Reader reader, Environment env, ILog log, boolean debug);

	/**
	 * Interpret the passed string as an ktl program.
	 * 
	 */
	public void interpret(String str);
	public void interpret(String str, ILog log);
	public void interpret(Reader reader);
	public void interpret(Reader reader, ILog log);

//	/**
//	 * Generate code for an input passed as a string 
//	 * with provided indentation
//	 * 
//	 * The result contains the list of Pairs.
//	 * 
//	 */
//	public List generate(String str, String indent);
//	public List generate(String str, String indent, ILog log);
//	public List generate(String str, String indent, Environment env, ILog log);
//		
//	public List generate(Reader reader, String indent);
//	public List generate(Reader reader, String indent, ILog log);
//	public List generate(Reader reader, String indent, Environment env, ILog log);
//		
//	public void generate(Reader input, PrintWriter output, String pkgName, ILog log);

	/** Ocl processor */
	public OclProcessor getOclProcessor();
	public void setOclProcessor(OclProcessor processor);
	
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
	public YatlParser getParser();
	public YatlSemanticAnalyser getAnalyser();
	public YatlInterpreter getInterpreter();
//	public KtlCodeGenerator getGenerator();

	/** Models */
	public java.util.Set getModels();
	public void setModels(java.util.Set models);
	public void addModel(Object model);

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