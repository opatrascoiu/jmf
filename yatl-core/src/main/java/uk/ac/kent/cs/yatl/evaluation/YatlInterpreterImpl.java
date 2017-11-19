package uk.ac.kent.cs.yatl.evaluation;

import java.util.*;

import uk.ac.kent.cs.yatl.syntax.transformations.Unit;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.kmf.util.*;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */

public class YatlInterpreterImpl
	implements YatlInterpreter 
{
	/** Constructor */
	public YatlInterpreterImpl(OclProcessor proc) {
		this.processor = proc;
	}
	public YatlInterpreterImpl(OclProcessor proc, ILog log) {
		this.processor = proc;
		this.log = log;
	}
	
	/** Evaluate contexts into the semantic and runtime environment */
	public void interpret(Unit unit) {
		//--- Set local variables ---
		this.unit = unit;
		this.log = new OutputStreamLog(System.out);
		
		//--- Interpret ---
		interpret(false);
	}

	/** Evaluate contexts into the semantic and runtime environment */
	public void interpret(Unit unit, ILog log) {
		//--- Set local variables ---
		this.unit = unit;
		this.log = log;
		
		//--- Interpret ---
		interpret(false);
	}

	/** Evaluate the unit using a given log and a debug/release flag */
	public void interpret(Unit unit, ILog log, boolean debug) {
		//--- Set local variables ---
		this.unit = unit;
		this.log = log;
		
		//--- Evaluate ---
		interpret(debug);
	}

	/** Evaluate contexts into the semantic and runtime environment */
	protected void interpret(boolean debug) {
		//--- Check evaluation context ---
		if (unit == null) return;
		if (log == null) log = new OutputStreamLog(System.out);
		
		//--- Create the context for the evalution --- 
		Map data = new HashMap();
		data.put("log", log);
		data.put("env", processor.runtimeEnvironment());

		//--- Evaluate the input ---
		YatlInterpreterVisitorImpl evaluator = new YatlInterpreterVisitorImpl(processor);
		unit.accept(evaluator, data);
	}

	/** OCL processor */
	protected OclProcessor processor;

	/** Input semantic tree */
	protected Unit unit;
	
	/** Log file */
	protected ILog log;
	
}
