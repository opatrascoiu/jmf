package uk.ac.kent.cs.kmf.quality.evaluators.CandK;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;

/**
 * @author Octavian Patrascoiu
 *
 */
public class NOCEvaluator
	extends Evaluator 
{
	public NOCEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof Class_) {
			return this.numberOfChildren((Class_)elem, m);
		}
		return null;
	}
	/** Number of children */
	public Metric numberOfChildren(Class_ cls, Metric m) {
		double value = cls.getSpecialization().size(); 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}

}
