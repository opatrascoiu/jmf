package uk.ac.kent.cs.kmf.quality.evaluators.kmf.namespace;

import java.util.Iterator;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Contained Namespaces
 * 
 * Computes the number of owned namespaces, regardless the
 * nesting level.
 * 
 */
public class NsNCNEvaluator
	extends Evaluator 
{
	public NsNCNEvaluator(QualityModel qModel) {
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
		no = 0;
		scan(pkg);
		double value = no;
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	protected void scan(uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package pkg) {
		Iterator i = pkg.getOwnedElement().iterator();
		while (i.hasNext()) {
			Object o = i.next();
			if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package) { 
				no++;
				scan((uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package)o);
			}
		}
	}	
	
	//
	// Local properties
	//
	double no = 0;
}
