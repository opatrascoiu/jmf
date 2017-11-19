package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Direct Descendants
 * 
 * Computes the number of directly specialized classes.
 * 
 */
public class ClsNDDEvaluator
	extends Evaluator 
{
	public ClsNDDEvaluator(QualityModel qModel) {
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
		// Compute 
		double value = cls.getSpecialization().size();
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
