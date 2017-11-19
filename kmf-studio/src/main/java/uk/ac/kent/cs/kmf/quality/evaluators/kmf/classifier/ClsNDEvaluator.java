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
 * Number of Descendants
 * 
 * Computes the number of specialized classes counting also
 * multiple inheritances.
 * 
 */
public class ClsNDEvaluator
	extends Evaluator 
{
	public ClsNDEvaluator(QualityModel qModel) {
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
		double value = allSubClasses((GeneralizableElement)cls).size()-1;
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	
	/** Compute all sub classes of a class, including class */
	protected List allSubClasses(GeneralizableElement cls) {
		// Initialize the list
		List classes = new Vector();
		if (cls == null) return classes;
		classes.add(cls);
		int first = 0;
		int last = 1;
		// While there are unvisited nodes
		while (first < last) {
			// Get current class
			GeneralizableElement crtCls = (GeneralizableElement)classes.get(first++);
			// Scan all direct superclasses   	
			if (crtCls != null) {
				Iterator it = crtCls.getSpecialization().iterator();
				while (it.hasNext()) {
					Generalization gen = (Generalization)it.next();
					Object child = gen.getChild();
					if (child != null) {
						last++;
						classes.add(child);
					}
				}
			}
		}
		return classes;
	}
}
