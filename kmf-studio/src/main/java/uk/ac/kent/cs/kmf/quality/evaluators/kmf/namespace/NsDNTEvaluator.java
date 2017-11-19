package uk.ac.kent.cs.kmf.quality.evaluators.kmf.namespace;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace;

/**
 * @author Octavian Patrascoiu
 * 
 * Depth of Nesting Tree
 * 
 * Computes the level of the namespace in the tree that 
 * describes the nesting relation between namespaces. 
 * 
 * The height of a node associated to a namespace that 
 * does not include another namespace is 0.
 * 
 */
public class NsDNTEvaluator
	extends Evaluator 
{
	public NsDNTEvaluator(QualityModel qModel) {
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
		double value = depth(pkg);
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	protected double depth(Namespace pkg) {
		Namespace parent = pkg.getNamespace_();
		if (parent == null) {
			return 0;
		}
		else {
			return 1+depth(parent);
		}
	}
}
