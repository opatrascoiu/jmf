/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.synthesis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ContextDeclaration;

public class OclEvaluatorImpl
implements OclEvaluator
{
	/** Constructor */
	public OclEvaluatorImpl(OclProcessor processor, OclEvaluatorVisitorImpl evaluator) {
		this.processor = processor;
		this.evaluator = evaluator;
	}

	/** Evaluate contexts into the semantic and runtime environment */
	public List evaluate(ContextDeclaration decl) {
		// Create an empty runtime environment
		RuntimeEnvironment renv = new RuntimeEnvironmentImpl();
		renv.setValue("self", null);
		
		//--- Evaluate ---
		return evaluate(decl, renv);
	}

	/** Evaluate contexts into the semantic and runtime environment */
	public List evaluate(ContextDeclaration decl, RuntimeEnvironment renv) {
		//--- Evaluate ---
		return evaluate(decl, renv, new OutputStreamLog(System.out));
	}

	/** Evaluate contexts into the semantic and runtime environment */
	public List evaluate(ContextDeclaration decl, RuntimeEnvironment renv, ILog log) {
		//--- Set local variables ---
		this.decl = decl;
		this.renv = renv;
		this.log = log;
		
		//--- Evaluate ---
		return evaluate();
	}

	/** Evaluate contexts into the semantic and runtime environment */
	public List evaluate(ContextDeclaration decl, RuntimeEnvironment renv, ILog log, boolean debug) {
		//--- Set local variables ---
		this.decl = decl;
		this.renv = renv;
		this.log = log;
		
		//--- Evaluate ---
		return evaluate();
	}

	/** Evaluate contexts into the semantic and runtime environment */
	protected List evaluate() {
		//--- Check evaluation context ---
		if (decl == null) return null;
		if (renv == null) {
			renv = new RuntimeEnvironmentImpl();
		}
		if (log == null) log = new OutputStreamLog(System.out);
		
		//--- Create the context for the evalution --- 
		Map data = new HashMap();
		data.put("env", renv);
		data.put("log", log);

		//--- Evaluate the input ---
		List result = (List)decl.accept(evaluator, data);
		
		//--- Return result ---
		return result;
	}
	
	/** OCL processor and evaluator visitor */
	protected OclProcessor processor;
	protected OclEvaluatorVisitorImpl evaluator;
	
	/** Input semantic tree */
	protected ContextDeclaration decl;
	
	/** Runtime environment */
	protected RuntimeEnvironment renv;
	
	/** Log file */
	protected ILog log;
	
}
