package uk.ac.kent.cs.kmf.quality.evaluators.CandK;

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
 */
public class RFCEvaluator 
	extends Evaluator 
{
	public RFCEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof Class_) {
			return this.responseForClass((Class_)elem, m);
		}
		return null;
	}
	
	protected Metric responseForClass(Class_ cls, Metric m) {
		double value = 0;
		Iterator i = cls.getFeature().iterator();
		while (i.hasNext()) {
			Feature f = (Feature)i.next();
			if (f instanceof Operation) {
				Operation op = (Operation)f;
				value += 1;
			}
		}
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
