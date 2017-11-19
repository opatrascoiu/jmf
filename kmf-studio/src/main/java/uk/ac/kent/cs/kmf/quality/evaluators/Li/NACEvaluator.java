package uk.ac.kent.cs.kmf.quality.evaluators.Li;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement;

/**
 * @author Octavian Patrascoiu
 *
 */
public class NACEvaluator
	extends Evaluator 
{
	public NACEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof Class_) {
			return this.numberOfAscendants((Class_)elem, m);
		}
		return null;
	}
	/** Number of ascendants */
	protected Metric numberOfAscendants(Class_ cls, Metric m) {
		double value = context.getNaming().allSuperClasses((GeneralizableElement)cls, false).size()-1; 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
