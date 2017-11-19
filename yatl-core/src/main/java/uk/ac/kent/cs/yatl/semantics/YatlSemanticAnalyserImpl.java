package uk.ac.kent.cs.yatl.semantics;

import java.util.*;

import uk.ac.kent.cs.yatl.syntax.transformations.Unit;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;
import uk.ac.kent.cs.kmf.util.*;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public class YatlSemanticAnalyserImpl
	implements YatlSemanticAnalyser 
{
	/** Constructor */
	public YatlSemanticAnalyserImpl(OclProcessor proc) {
		this.processor = proc;
	}
	public YatlSemanticAnalyserImpl(OclProcessor proc, ILog log) {
		this.processor = proc;
		this.log = log;
	}
	protected OclProcessor processor;
	
	
	/** Input ast */
	protected Unit unit;
	
	/** Output log */
	protected ILog log;
	
	/** Environment */
	protected Environment env;
	
	/** Semantic analysis */
	protected Unit analyse() {
		//--- Check context
		if (unit == null) return null;
		if (env == null) env = processor.getBridgeFactory().buildEnvironment();
		if (log == null) log = new OutputStreamLog(System.out);
		
		//--- Store the no of errors  ---
		int errNo = log.getErrors();

		//--- Semantic analysis ---
		Map context = new HashMap();
		context.put("env", env);
		context.put("log", log);
		YatlSemanticVisitorImpl semanticVisitor = new YatlSemanticVisitorImpl(processor);
		Unit contextDecl = (Unit)unit.accept(semanticVisitor, context);

		//--- Set hasErrors flag --
		hasErrors = log.getErrors() > errNo && !log.tooManyViolations();
		
		//--- Return semantic tree ---		
		return contextDecl; 
	}

	/** Semantic analysis */
	public Unit analyse(Unit unit, Environment env) {
		//--- Set local variables ---
		this.unit = unit;
		this.env = env;
		this.log = new OutputStreamLog(System.out);

		//--- Analyse the input ---
		return analyse();
	}

	/** Semantic analysis */
	public Unit analyse(Unit unit, Environment env, ILog log) {
		//--- Set local variables ---
		this.unit = unit;
		this.env = env;
		this.log = log;

		//--- Analyse the input ---
		return analyse();
	}

	/** Semantic analysis */
	public Unit analyse(Unit unit, Environment env, ILog log, boolean debugFlag) {
		//--- Set local variables ---
		this.unit = unit;
		this.env = env;
		this.log = log;
		
		//--- Analyse the input ---
		Unit res = analyse();

		//--- Debug info ---
		if (debugFlag) {
			// Display the unit
			YatlDebugVisitorImpl debug = new YatlDebugVisitorImpl();
			String resStr = "null";
			if (res != null) {
				resStr = (String)res.accept(debug, "");
			}
			//--- Report message ---
			log.reportMessage("KTL Model:");
			if (debugFlag) log.reportMessage(resStr);
		}
		return res;
	}

	/** Check if the analyser encountered errors */
	protected boolean hasErrors;
	public boolean hasErrors() {
		return hasErrors;
	}
}
