package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;

/**
 * @author Octavian Patrascoiu
 * 
 * Class Specializability: CLS-SPEC = CLS-NLP + CLS-NLO + 10*CLS-NA
 * 
 * Computes the specializabilty of a class. 
 *  
 */
public class ClsSPECEvaluator
	extends Evaluator 
{
	public ClsSPECEvaluator(QualityModel qModel) {
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
		Metric clsNLP = QualityModel.factory.buildMetric("cls-nlp");
		clsNLP = qModel.evaluate(cls, clsNLP);
		Metric clsNLO = QualityModel.factory.buildMetric("cls-nlo");
		clsNLO = qModel.evaluate(cls, clsNLO);
		Metric clsNA = QualityModel.factory.buildMetric("cls-na");
		clsNA = qModel.evaluate(cls, clsNA);
		value = clsNLP.getValue() + clsNLO.getValue() + 10*clsNA.getValue();

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
