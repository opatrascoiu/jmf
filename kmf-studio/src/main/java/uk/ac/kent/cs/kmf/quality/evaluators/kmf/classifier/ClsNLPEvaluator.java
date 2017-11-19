package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import java.util.Iterator;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Local Properties
 * 
 * Counts the attributes and the associated ends that are 
 * defined in a Class without considering the inherited 
 * properties.
 *  
 */
public class ClsNLPEvaluator
	extends Evaluator 
{
	public ClsNLPEvaluator(QualityModel qModel) {
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
		// Add Properties        
		double value = 0;
		Iterator j = cls.getFeature().iterator();
		while (j.hasNext()) {
			Feature f = (Feature)j.next();
			if (f instanceof Attribute) {
				value++;
			}
			Iterator k = context.getAssociations().iterator();
			while (k.hasNext()) {
				Association assoc = (Association)k.next();
				AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
				AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
				if (context.getNaming().add(aend1, aend2, cls)) {
					value ++;
				}
				if (context.getNaming().add(aend2, aend1, cls)) {
					value++;
				}
			}
		}

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}	
}
