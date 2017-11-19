package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;

/**
 * @author Octavian Patrascoiu
 * 
 * Class Stability CLS-STAB = CLS-ND + CLS-NRE
 * 
 * Computes the stability of a class. 
 *  
 */
public class ClsSTABEvaluator
	extends Evaluator 
{
	public ClsSTABEvaluator(QualityModel qModel) {
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
		Metric clsND = QualityModel.factory.buildMetric("cls-nd");
		clsND = qModel.evaluate(cls, clsND);
		Metric clsNRE = QualityModel.factory.buildMetric("cls-nre");
		clsNRE = qModel.evaluate(cls, clsNRE);
		value = clsND.getValue() + clsNRE.getValue();

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
