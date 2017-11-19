package uk.ac.kent.cs.yatl.semantics;

import uk.ac.kent.cs.kmf.util.*;

import uk.ac.kent.cs.yatl.syntax.transformations.Unit;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public interface YatlSemanticAnalyser {
	/** Semantic analysis */
	public Unit analyse(Unit decl, Environment env);

	/** Semantic analysis */
	public Unit analyse(Unit decl, Environment env, ILog log);

	/** Semantic analysis */
	public Unit analyse(Unit decl, Environment env, ILog log, boolean debugFlag);

	/** Check if the analyse detected errors */
	public boolean hasErrors();
}
