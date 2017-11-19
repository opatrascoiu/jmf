package uk.ac.kent.cs.yatl;

import java.io.Reader;
import java.util.Set;

import uk.ac.kent.cs.kmf.util.ILog;
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
public abstract class YatlProcessorImpl
	implements YatlProcessor
{
	/** Constructors */
	public YatlProcessorImpl(OclProcessor oclProcessor) {
		setOclProcessor(oclProcessor);
		setModels(oclProcessor.getModels());
	}
	public YatlProcessorImpl(OclProcessor oclProcessor, ILog log) {
		setOclProcessor(oclProcessor);
		setLog(log);
		setModels(oclProcessor.getModels());
	}
	public YatlProcessorImpl(OclProcessor oclProcessor, Set models, ILog log) {
		setOclProcessor(oclProcessor);
		setLog(log);
		setModels(models);
	}

	/**
	 * Parse the passed input (string, reader) and build a Unit.
	 * A non null valued result implies a successful (error free) parse.
	 * 
	 */
	public Unit parse(String input) {
		return parse(input, getLog());
	}
	public Unit parse(String input, ILog log) {
		return parse(input, log, getDebug().booleanValue());
	}
	public Unit parse(String input, ILog log, boolean debug) {
		log.resetErrors();
		Unit pd = getParser().parse(input, log, debug);
		if (log.hasErrors()) return null;
		return pd;
	}
	public Unit parse(Reader input) {
		return parse(input, getLog());
	}
	public Unit parse(Reader input, ILog log) {
		return parse(input, log, getDebug().booleanValue());
	}
	public Unit parse(Reader input, ILog log, boolean debug) {
		Unit pd = this.getParser().parse(input, log, debug);
		if (log.hasErrors()) return null;
		return pd;
	}
	
	/**
	 * Semantic analysis of the passed Unit, with output going to the log.
	 * 
	 */
	public Unit analyse(Unit u) {
		return analyse(u, getLog());
	}
	public Unit analyse(Unit u, ILog log) {
		//--- Create an environment ---
		Environment env = this.environment();
		return analyse(u, env, log, getDebug().booleanValue());
	}
	public Unit analyse(Unit u, ILog log, boolean debug) {
		//--- Create an environment ---
		Environment env = this.environment();
		return analyse(u, env, log, debug);
	}
	public Unit analyse(Unit u, Environment env, ILog log) {
		return analyse(u, env, log, getDebug().booleanValue());
	}
	public Unit analyse(Unit u, Environment env, ILog log, boolean debug) {
		if (u == null) return null;
		u = getAnalyser().analyse(u, env, log, debug);
		if (log.hasErrors()) return null;
		return u;
	}
	public Unit analyse(String str) {
		return analyse(str, getLog());
	}
	public Unit analyse(String str, ILog log) {
		return analyse(str, log, getDebug().booleanValue());
	}
	public Unit analyse(String str, ILog log, boolean debug) {
		//--- Parse the input ---
		Unit u = parse(str, log);
		Environment env = this.environment();
		return analyse(u, env, log, debug);
	}
	public Unit analyse(String str, Environment env, ILog log) {
		return analyse(str, env, log, getDebug().booleanValue());
	}
	public Unit analyse(String str, Environment env, ILog log, boolean debug) {
		Unit u = parse(str, log);
		return analyse(u, env, log, debug);
	}
	public Unit analyse(Reader reader) {
		return analyse(reader, getLog());
	}
	public Unit analyse(Reader reader, ILog log) {
		return analyse(reader, log, getDebug().booleanValue());
	}
	public Unit analyse(Reader reader, ILog log, boolean debug) {
		Environment env = this.environment();
		return analyse(reader, env, log, debug);
	}
	public Unit analyse(Reader reader, Environment env, ILog log) {
		return analyse(reader, env, log, getDebug().booleanValue());
	}
	public Unit analyse(Reader reader, Environment env, ILog log, boolean debug) {
		Unit u = parse(reader, log);
		return analyse(u, env, log, debug);
	}
	
	/**
	 * Interpret the passed string as an ktl program.
	 * 
	 */
	public void interpret(String input) {
		interpret(input, this.getLog());
	}
	public void interpret(String input, ILog log) {
		Unit u = analyse(input, log);
		this.getInterpreter().interpret(u, log); 
	}
	public void interpret(Reader input) {
		interpret(input, this.getLog());
	}
	public void interpret(Reader input, ILog log) {
		Unit u = analyse(input, log);
		this.getInterpreter().interpret(u, log); 
	}

	/** Ocl processor */
	protected OclProcessor oclProcessor; 
	public OclProcessor getOclProcessor() { return oclProcessor; }
	public void setOclProcessor(OclProcessor processor) { this.oclProcessor = processor;}
	
	/** Debug/release flag */
	public void setDebug(Boolean debug) { oclProcessor.setDebug(debug); }
	public Boolean getDebug() { return oclProcessor.getDebug(); }
	
	/** Log file */
	public ILog getLog() { return oclProcessor.getLog(); }
	public void setLog(ILog log) { oclProcessor.setLog(log); }
	
	/** UML models **/
	public Set getModels() {return oclProcessor.getModels();}
	public void setModels(Set models) { oclProcessor.setModels(models); }
	public void addModel(Object model) { oclProcessor.getModels().add(model); }

	/** Adapters: standard library and model */
	public StdLibAdapter getStdLibAdapter() {
		return oclProcessor.getStdLibAdapter();
	}
	public String getStdLibAdapterName() {
		return oclProcessor.getStdLibAdapterName();
	}
	public ModelImplementationAdapter getModelImplAdapter() {
		return oclProcessor.getModelImplAdapter();
	}
	public String getModelImplAdapterName() {
		return oclProcessor.getModelImplAdapterName();
	}

	/** Factories: type and bridge */
	public TypeFactory getTypeFactory() {
		return oclProcessor.getTypeFactory();
	}
	public BridgeFactory getBridgeFactory() {
		return oclProcessor.getBridgeFactory();
	}

	/** 
	 * Creates an environment for 'self'  
	 * ( additional objects can be added using 'setValue("name", object)' )
	 */
	public Environment environment(String selfName, Object selfType) {
		return oclProcessor.environment(selfName, selfType);
	}

	public Environment environment() {
		return oclProcessor.environment();
	}

	/**
	 * Create a runtime environment
	 * that includes the passed EObject as the passed name.
	 * ( additional objects can be added using 'setValue("name", object)' )
	 * 
	 */
	public RuntimeEnvironment runtimeEnvironment(String name, Object obj) {
		return oclProcessor.runtimeEnvironment(name, obj);
	}
	public RuntimeEnvironment runtimeEnvironment() {
		return oclProcessor.runtimeEnvironment();
	}
}
