package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Properties
 * 
 * Counts the attributes and the associated ends that are 
 * defined in a Class considering also the inherited 
 * properties.
 * 
 */
public class ClsNPEvaluator
	extends Evaluator 
{
	public ClsNPEvaluator(QualityModel qModel) {
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
		Set addedProp = new LinkedHashSet();
		List classes = context.getNaming().allSuperClasses((GeneralizableElement)cls, false);
		for(int i = classes.size()-1; i>=0; i--) {
			Classifier a_cls = (Classifier)classes.get(i);
			String a_clsName = context.getNaming().getClassifierName(a_cls);
			Iterator j = a_cls.getFeature().iterator();
			while (j.hasNext()) {
				Feature f = (Feature)j.next();
				if (f instanceof Attribute) {
					Attribute attrib = (Attribute)f;
					String attribName = context.getNaming().getPropertyName(attrib);
					if (!addedProp.contains(attribName)) {
						// Add it
						addedProp.add(attribName);
					} else {
					}
				}
			}
			Iterator k = context.getAssociations().iterator();
			while (k.hasNext()) {
				Association assoc = (Association)k.next();
				AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
				AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
				if (context.getNaming().add(aend1, aend2, a_cls)) {
					String assocName = context.getNaming().getPropertyName(aend2);
					if (!addedProp.contains(assocName)) {
						// Add it
						addedProp.add(assocName);                            
					} else {
					}
				}
				if (context.getNaming().add(aend2, aend1, a_cls)) {
					String assocName = context.getNaming().getPropertyName(aend1);
					if (!addedProp.contains(assocName)) {
						// Add it
						addedProp.add(assocName);                            
					} else {
					}
				}
			}
		}

		// Compute 
		double value = addedProp.size();
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}	
}
