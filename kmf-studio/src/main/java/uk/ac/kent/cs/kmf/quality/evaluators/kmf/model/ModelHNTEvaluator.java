package uk.ac.kent.cs.kmf.quality.evaluators.kmf.model;

import java.util.Iterator;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 * 
 * Height of Nesting Tree
 * 
 * Computes the height of the tree that describes 
 * the nesting relation between namespaces. 
 * The height of a node associated to a namespace that 
 * does not include another namespace is 0.
 * 
 */
public class ModelHNTEvaluator
	extends Evaluator 
{
	public ModelHNTEvaluator(QualityModel qModel) {
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
		double value = height(model);
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	protected double height(uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package pkg) {
		Iterator i = pkg.getOwnedElement().iterator();
		double max = -1;
		while (i.hasNext()) {
			Object o = i.next();
			if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package) { 
				double h = height((uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package)o);
				if (h > max)
					max = h;
			}
		}
		return max+1;
	}	
}
