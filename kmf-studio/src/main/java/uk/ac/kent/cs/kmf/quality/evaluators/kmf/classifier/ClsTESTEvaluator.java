package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;

/**
 * @author Octavian Patrascoiu
 * 
 * Class Testability: CLS-TEST = CLS-LC
 * 
 * Computes the testability of a class. 
 *  
 */
public class ClsTESTEvaluator
	extends Evaluator 
{
	public ClsTESTEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof Class_) {
			return this.computeMetric((Class_)elem, m);
		}
		return null;
	}

	/** Compute metric */
	protected Metric computeMetric(Class_ cls, Metric m) {
		double value = 0;
		Metric clsLC = QualityModel.factory.buildMetric("cls-lc");
		clsLC = qModel.evaluate(cls, clsLC);
		value = clsLC.getValue();

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
