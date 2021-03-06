package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Generalization;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Multiple Inheritances 
 * 
 */
public class ClsNMIEvaluator
	extends Evaluator 
{
	public ClsNMIEvaluator(QualityModel qModel) {
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
		// Compute 
		scan((GeneralizableElement)cls);
		double value = noMI;
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	
	/** Scan all inheritance relations */
	protected void scan(GeneralizableElement cls) {
		// Initialize the list
		List classes = new Vector();
		if (cls == null) return;
		classes.add(cls);
		int first = 0;
		int last = 1;
		// While there are unvisited nodes
		while (first < last) {
			// Get current class
			GeneralizableElement crtCls = (GeneralizableElement)classes.get(first++);
			// Scan all direct superclasses   	
			if (crtCls != null) {
				Iterator it = crtCls.getGeneralization().iterator();
				while (it.hasNext()) {
					Generalization gen = (Generalization)it.next();
					Object parent = gen.getParent();
					if (parent != null) {
						last++;
						if (classes.contains(parent)) {
							noMI++;
						}
						classes.add(parent);
					}
				}
			}
		}
	}

	//
	// Local properties
	//
	double noMI = 0;
}
