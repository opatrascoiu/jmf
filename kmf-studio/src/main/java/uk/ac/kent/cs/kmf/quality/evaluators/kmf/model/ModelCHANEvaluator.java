package uk.ac.kent.cs.kmf.quality.evaluators.kmf.model;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 * Model Changeability: MODEL-CHAN = MODEL-HIG + MODEL-ACC + MODEL-AOCC 
 * 
 * Computes the changeability of a model.
 * 
 */
public class ModelCHANEvaluator
	extends Evaluator 
{
	public ModelCHANEvaluator(QualityModel qModel) {
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
		Metric modelHIG = QualityModel.factory.buildMetric("model-hig");
		modelHIG = qModel.evaluate(model, modelHIG);
		Metric modelACC = QualityModel.factory.buildMetric("model-acc");
		modelACC = qModel.evaluate(model, modelACC);
		Metric modelAOCC = QualityModel.factory.buildMetric("model-aocc");
		modelAOCC = qModel.evaluate(model, modelAOCC);
		value = modelHIG.getValue() + modelACC.getValue() + modelAOCC.getValue();

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
