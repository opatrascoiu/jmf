package uk.ac.kent.cs.yatl.evaluation;

import uk.ac.kent.cs.yatl.syntax.transformations.Unit;
import uk.ac.kent.cs.kmf.util.ILog;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public interface YatlInterpreter {
	/** Evaluate the unit */
	public void interpret(Unit unit);

	/** Evaluate the unit using a given log */
	public void interpret(Unit unit, ILog log);

	/** Evaluate the unit using a given log a debug/release flag */
	public void interpret(Unit unit, ILog log, boolean debug);
}
