package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement;

/**
 * @author Octavian Patrascoiu
 * 
 * Number of Referees
 * 
 * Computes the number of classes that refer to a class. 
 *  
 */
public class ClsNREEvaluator
	extends Evaluator 
{
	public ClsNREEvaluator(QualityModel qModel) {
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
		double value = 0;
		// Scan all classes        
		List classes = context.getNaming().allSuperClasses((GeneralizableElement)cls, false);
		Iterator c = classes.iterator();
		while (c.hasNext()) {
			Classifier a_cls = (Classifier)c.next();
			// Get the referred classes
			Set refCls = ClsNRDCEvaluator.getReferredClasses(cls, context);
			if (refCls == null) {
				Metric clsNRDC = QualityModel.factory.buildMetric("cls-nrdc");
				clsNRDC = qModel.evaluate(a_cls, clsNRDC);
				refCls = ClsNRDCEvaluator.getReferredClasses(cls, context);
			}
			if (refCls.contains(cls))
				value++;
		}        

		// Compute 
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
