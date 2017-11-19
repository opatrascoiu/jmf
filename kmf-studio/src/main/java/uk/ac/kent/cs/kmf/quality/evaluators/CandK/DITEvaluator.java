package uk.ac.kent.cs.kmf.quality.evaluators.CandK;

import java.util.Iterator;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Generalization;

/**
 * @author Octavian Patrascoiu
 *
 */
public class DITEvaluator
	extends Evaluator 
{
	public DITEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof Class_) {
			return this.depthOfInheritanceTree((Class_)elem, m);
		}
		return null;
	}
	/** Depth of inheritance tree */
	public Metric depthOfInheritanceTree(Class_ cls, Metric m) {
		double value;
		// It's root
		if (cls.getGeneralization().size() != 0) {
			value = depth(cls);
		} else {
			value = 0;
		}
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	protected double depth(GeneralizableElement cls) {
		Iterator i = cls.getGeneralization().iterator();
		double max = 0;
		while (i.hasNext()) {
			Object o = i.next();
			if (o instanceof Generalization) {
				Generalization g = (Generalization)o;
				double h = depth(g.getParent());
				if (h > max) {
					max = h;
				}
			}
		}
		if (cls.getGeneralization().size() == 0)
			return 0;
		else
			return max+1;
	}
}
