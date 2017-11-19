package uk.ac.kent.cs.kmf.quality.evaluators.kmf.operation;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;

/**
 * @author Octavian Patrascoiu
 * 
 * McCabe Complexity
 * 
 * Computes the McCabe metric 
 *  
 */
public class OperMCCEvaluator
	extends Evaluator 
{
	public OperMCCEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof Operation) {
			return this.computeMetric((Operation)elem, m);
		}
		return null;
	}

	/** Compute metric */
	protected Metric computeMetric(Operation op, Metric m) {
		// Compute
		double value = op.getParameter().size();
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
