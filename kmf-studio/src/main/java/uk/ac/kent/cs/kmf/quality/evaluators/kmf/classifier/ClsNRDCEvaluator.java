package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Referred Classes
 * 
 * Computes the number of classes that are used directly 
 * as attributes and association ends types,
 * and inside operations. 
 * 
 * Operations signature and body are both checked for
 * appearances. 
 * 
 * Primitive data types are not considered. *
 *  
 */
public class ClsNRDCEvaluator
	extends Evaluator 
{
	public ClsNRDCEvaluator(QualityModel qModel) {
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
		// Compute the refered objects
		Set set = getReferredClasses(cls, context);
		// Compute
		double value = set.size();
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	/** Get/put referrred classes */
	public static Set getReferredClasses(Class_ cls, Context context){
		Set set = (Set)cache.get(cls);
		if (set == null) {
			set = new HashSet();
			set.addAll(couplingThroughAttributes(cls, context));
			set.addAll(couplingThroughAssociations(cls, context));
			set.addAll(couplingThroughMethods(cls, context));
			// Store them
			putReferredClasses(cls, set);
		}
		return set;		
	}
	public static void putReferredClasses(Class_ cls, Set classes) {
		cache.put(cls, classes);
	}
	
	protected static Set couplingThroughAttributes(Class_ cls, Context context) {
		Set res = new HashSet();
		Iterator i = cls.getFeature().iterator();
		while (i.hasNext()) {
			Feature f = (Feature)i.next();
			if (f instanceof Attribute) {
				Attribute attrib = (Attribute)f;
				if (attrib.getType() != null)
					res.add(context.getNaming().getFullClassifierName(attrib.getType()));
			}
		}
		return res;
	}
	protected static Set couplingThroughAssociations(Class_ cls, Context context) {
		Set res = new HashSet();
		Iterator j = context.getAssociations().iterator();
		while (j.hasNext()) {
			Association assoc = (Association)j.next();
			AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
			AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
			if (aend1.getType() == cls) {
				if (aend1.getType() != null)
					res.add(context.getNaming().getFullClassifierName(aend1.getType()));
			} else {
				if (aend2.getType() != null)
					res.add(context.getNaming().getFullClassifierName(aend2.getType()));
			}
		}
		return res;
	}
	protected static Set couplingThroughMethods(Class_ cls, Context context) {
		Set res = new HashSet();
		Iterator i = cls.getFeature().iterator();
		while (i.hasNext()) {
			Feature f = (Feature)i.next();
			if (f instanceof Operation) {
				Operation op = (Operation)f;
				Iterator j = op.getParameter().iterator();
				while (j.hasNext()) {
					Parameter param = (Parameter)j.next(); 
					if (param.getType() != null)
						res.add(context.getNaming().getFullClassifierName(param.getType()));
				}
			}
		}
		return res;
	}
	
	//
	// Local properties
	//
	protected static Map cache = new LinkedHashMap();
}
