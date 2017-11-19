package uk.ac.kent.cs.kmf.quality.evaluators.kmf.model;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 * Model Testability: MODEL-TEST = MODEL-AMC + MODEL-AOCC
 * 
 * Computes the testability of a model.
 * 
 */
public class ModelTESTEvaluator
	extends Evaluator 
{
	public ModelTESTEvaluator(QualityModel qModel) {
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
		Metric modelAMC = QualityModel.factory.buildMetric("model-amc");
		modelAMC = qModel.evaluate(model, modelAMC);
		Metric modelAOCC = QualityModel.factory.buildMetric("model-aocc");
		modelAOCC = qModel.evaluate(model, modelAOCC);
		value = modelAMC.getValue() + modelAOCC.getValue();

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
