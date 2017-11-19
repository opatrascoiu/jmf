package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import java.util.Iterator;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Local Operations
 * 
 * Counts the operations that are defined in a Class 
 * without considering the inherited operations.
 * 
 */
public class ClsNLOEvaluator
	extends Evaluator 
{
	public ClsNLOEvaluator(QualityModel qModel) {
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
		// Add operations
		Iterator k = cls.getFeature().iterator();
		while (k.hasNext()) {
			Feature f = (Feature)k.next();
			if (f instanceof Operation) {
				value++;
			}
		}        

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
