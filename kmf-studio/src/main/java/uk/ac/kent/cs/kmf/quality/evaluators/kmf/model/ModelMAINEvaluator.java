package uk.ac.kent.cs.kmf.quality.evaluators.kmf.model;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 * Model maintainability: MODEL-MAIN = MODEL-CHAN + MODEL-TEST
 * 
 * Computes the maintainability of a model.
 * 
 */
public class ModelMAINEvaluator
	extends Evaluator 
{
	public ModelMAINEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof Model) {
			return this.computeMetric((Model)elem, m);
		}
		return null;
	}

	/** Compute metric */
	protected Metric computeMetric(Model model, Metric m) {
		double value = 0;
		Metric modelCHAN = QualityModel.factory.buildMetric("model-chan");
		modelCHAN = qModel.evaluate(model, modelCHAN);
		Metric modelTEST = QualityModel.factory.buildMetric("model-test");
		modelTEST = qModel.evaluate(model, modelTEST);
		value = modelCHAN.getValue() + modelTEST.getValue();

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
