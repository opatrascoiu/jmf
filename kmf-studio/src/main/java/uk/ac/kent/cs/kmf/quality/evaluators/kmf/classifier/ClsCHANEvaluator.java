package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;

/**
 * @author Octavian Patrascoiu
 * 
 * Class changeability CLS-CHAN = CLS-USAB + CLS-SPEC
 * 
 * Computes the changeabilty of a class. 
 *  
 */
public class ClsCHANEvaluator
	extends Evaluator 
{
	public ClsCHANEvaluator(QualityModel qModel) {
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
		Metric clsUSAB = QualityModel.factory.buildMetric("cls-usab");
		clsUSAB = qModel.evaluate(cls, clsUSAB);
		Metric clsSPEC = QualityModel.factory.buildMetric("cls-spec");
		clsSPEC = qModel.evaluate(cls, clsSPEC);
		value = clsUSAB.getValue() + clsSPEC.getValue();

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
