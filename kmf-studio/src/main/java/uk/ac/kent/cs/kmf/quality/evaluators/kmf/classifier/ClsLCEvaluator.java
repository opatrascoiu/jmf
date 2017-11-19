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
 * Local Complexity
 * 
 * 2*CLS-NLP + SMCC(o) where o is a local operation. 
 * 
 * For each property both a getter and a setter is 
 * considered.
 * 
 */
public class ClsLCEvaluator
	extends Evaluator 
{
	public ClsLCEvaluator(QualityModel qModel) {
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
		double operNo = 0;
		// Add properties
		Metric clsNLP = QualityModel.factory.buildMetric("cls-nlp");
		Metric m1 = qModel.evaluate(cls, clsNLP);
		value += 2*m1.getValue();		
		// Add operations
		Iterator k = cls.getFeature().iterator();
		while (k.hasNext()) {
			Feature f = (Feature)k.next();
			if (f instanceof Operation) {
				Operation oper = (Operation)f;
				Metric operMcc = QualityModel.factory.buildMetric("oper-mcc");
				Metric m2 = qModel.evaluate(oper, operMcc);
				operNo++;
				value += m2.getValue();
			}
		}        

		// Compute 
		if (operNo != 0)
			value = value/operNo;
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
