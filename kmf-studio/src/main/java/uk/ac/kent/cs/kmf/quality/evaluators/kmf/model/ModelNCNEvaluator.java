package uk.ac.kent.cs.kmf.quality.evaluators.kmf.model;

import java.util.Iterator;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 * Number of Contained Namespaces
 * 
 * Counts the number of all contained namespaces regardless 
 * of the nesting level.
 * 
 */
public class ModelNCNEvaluator
	extends Evaluator 
{
	public ModelNCNEvaluator(QualityModel qModel) {
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
		// Compute
		no = 0;
		scan(model);
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
