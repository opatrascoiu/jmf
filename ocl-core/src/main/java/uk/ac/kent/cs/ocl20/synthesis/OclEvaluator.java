package uk.ac.kent.cs.ocl20.synthesis;

import java.util.List;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ContextDeclaration;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public interface OclEvaluator {
	/** Evaluate contexts into a specific environment */
	public List evaluate(ContextDeclaration context, RuntimeEnvironment renv);
	public List evaluate(ContextDeclaration context, RuntimeEnvironment renv, ILog log);
}
