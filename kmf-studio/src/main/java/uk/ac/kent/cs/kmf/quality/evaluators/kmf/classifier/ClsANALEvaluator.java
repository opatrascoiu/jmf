package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import java.util.Iterator;
import java.util.Set;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement;

/**
 * @author Octavian Patrascoiu
 * 
 * Class analisability: CLS-ANAL = CLS-LC + CLS-NA + Sum(CLS-ANAL(c)) where c is a referred class. 
 * 
 * Computes the analisability of a class. 
 *  
 */
public class ClsANALEvaluator
	extends Evaluator 
{
	public ClsANALEvaluator(QualityModel qModel) {
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
		Metric clsLC = QualityModel.factory.buildMetric("cls-lc");
		clsLC = qModel.evaluate(cls, clsLC);
		Metric clsNA = QualityModel.factory.buildMetric("cls-na");
		clsNA = qModel.evaluate(cls, clsNA);
		value = clsLC.getValue() + clsNA.getValue();
		// Get referred classes
		Set set = ClsNRDCEvaluator.getReferredClasses(cls, context);
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Object obj = i.next();
			if (obj instanceof GeneralizableElement) {
				GeneralizableElement aCls = (GeneralizableElement)i.next();
				Metric clsANAL = QualityModel.factory.buildMetric("cls-anal");
				clsANAL = qModel.evaluate(cls, clsANAL);
				value += clsANAL.getValue();
			}
		}
		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
