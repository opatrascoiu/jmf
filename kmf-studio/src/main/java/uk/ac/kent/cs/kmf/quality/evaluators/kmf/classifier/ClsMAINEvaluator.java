package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;

/**
 * @author Octavian Patrascoiu
 * 
 * Class Maintainbility: CLS-MAIN = CLS-ANAL + CLS-CHAN + CLS-STAB + CLS-TEST
 * 
 * Computes the maintainability of a class. 
 *  
 */
public class ClsMAINEvaluator
	extends Evaluator 
{
	public ClsMAINEvaluator(QualityModel qModel) {
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
		Metric clsANAL = QualityModel.factory.buildMetric("cls-anal");
		clsANAL = qModel.evaluate(cls, clsANAL);
		Metric clsCHAN = QualityModel.factory.buildMetric("cls-chan");
		clsCHAN = qModel.evaluate(cls, clsCHAN);
		Metric clsSTAB = QualityModel.factory.buildMetric("cls-stab");
		clsSTAB = qModel.evaluate(cls, clsSTAB);
		Metric clsTEST = QualityModel.factory.buildMetric("cls-test");
		clsTEST = qModel.evaluate(cls, clsTEST);
		value = clsANAL.getValue() + clsCHAN.getValue() + clsSTAB.getValue() + clsTEST.getValue();

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
