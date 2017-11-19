package uk.ac.kent.cs.kmf.quality.evaluators.kmf.model;

import java.util.Iterator;
import java.util.Set;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Generalization;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 * 
 * Height of Inheritance Graph
 * 
 * Computes the height of the tree that describes 
 * the nesting relation between namespaces. 
 * The height of a node associated to a namespace that 
 * does not include another namespace is 0.
 * 
 */
public class ModelHIGEvaluator
	extends Evaluator 
{
	public ModelHIGEvaluator(QualityModel qModel) {
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
		double value = height();
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	protected double height() {
		Set classes = context.getClasses();
		Iterator i = classes.iterator();
		double max = 0;
		while (i.hasNext()) {
			Object obj = i.next();
			if (obj instanceof GeneralizableElement) {
				GeneralizableElement gen = (GeneralizableElement)obj;
				double height = height(gen);
				if (height > max) {
					max = height;
				}
			}
		}
		return max;
	}
	protected double height(GeneralizableElement cls) {
		double max = -1;
		Iterator i = cls.getGeneralization().iterator();
		while (i.hasNext()) {
			Generalization gen = (Generalization)i.next();
			GeneralizableElement parent = gen.getParent();
			double height = height(parent); 
			if (height > max) {
				max = height;
			}
		}
		return max+1;
	}
}