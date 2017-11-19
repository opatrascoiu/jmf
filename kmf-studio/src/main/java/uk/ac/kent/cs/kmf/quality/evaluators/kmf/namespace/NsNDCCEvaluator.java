package uk.ac.kent.cs.kmf.quality.evaluators.kmf.namespace;

import java.util.Iterator;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Directly Contained Classes
 * 
 * Computes the number of classes defined inside the 
 * namespace.
 * 
 */
public class NsNDCCEvaluator
	extends Evaluator 
{
	public NsNDCCEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package) {
			return this.computeMetric((uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package)elem, m);
		}
		return null;
	}

	/** Compute metric */
	protected Metric computeMetric(uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package pkg, Metric m) {
		// Compute 
		double value = 0;
		Iterator i = pkg.getOwnedElement().iterator();
		while (i.hasNext()) {
			Object o = i.next();
			if (o instanceof Class_) 
				value++;
		}
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
