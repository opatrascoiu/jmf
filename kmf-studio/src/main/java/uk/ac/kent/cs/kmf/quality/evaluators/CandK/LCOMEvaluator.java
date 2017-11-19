package uk.ac.kent.cs.kmf.quality.evaluators.CandK;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter;

/**
 * @author Octavian Patrascoiu
 *
 */
public class LCOMEvaluator
	extends Evaluator 
{
	public LCOMEvaluator(QualityModel qModel) {
		super(qModel);
	}
	
	public Metric evaluate(Object elem, Metric m) {
		if (elem instanceof Class_) {
			return this.lackOfCohesionInMethods((Class_)elem, m);
		}
		return null;
	}
	/** Lack of cohesion in methods */
	public Metric lackOfCohesionInMethods(Class_ cls, Metric m) {
		// Compute the types of parameters for each method
		List types = new Vector();
		Iterator i = cls.getFeature().iterator();
		while (i.hasNext()) {
			Feature f = (Feature)i.next();
			Set methodTypes = new HashSet();
			if (f instanceof Operation) {
				Operation op = (Operation)f;
				Iterator j = op.getParameter().iterator();
				while (j.hasNext()) {
					Parameter param = (Parameter)j.next(); 
					methodTypes.add(context.getNaming().getFullClassifierName(param.getType()));
				}
			}
		}
		// Compute the number of null and non-null intersections
		int p = 0;
		int q = 0;
		for(int j=0; j<types.size(); j++) {
			Set types1 = (HashSet)types.get(j);
			for(int k=j+1; k<types.size(); k++) {
				Set types2 = (Set)types.get(k);
				// Check if the intersection is empty
				Iterator l = types2.iterator();
				boolean isEmpty = true;
				while (l.hasNext()) {
					Object elem = l.next();
					if (types1.contains(elem)) {
						isEmpty = false;
						break; 
					}
				}
				if (isEmpty) {
					p++;
				} else {
					q++;
				}
			}
		}
		p = Math.abs(p);
		q = Math.abs(q);
		double value;
		if (p > q) value = p-q;
		else value = 0;
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
