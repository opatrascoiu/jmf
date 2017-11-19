package uk.ac.kent.cs.kmf.quality.evaluators.kmf.classifier;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import uk.ac.kent.cs.kmf.kmfstudio.generators.ClassGenerator;
import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;

/**
 * @author Octavian Patrascoiu
 * 
 * Average Complexity of Operations
 * 
 * Computes the ratio of the sum of complexity for every 
 * operation and the number of operations. 
 *  
 */
public class ClsACOEvaluator
	extends Evaluator 
{
	public ClsACOEvaluator(QualityModel qModel) {
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
		double operNo = 0;
		// Add Properties        
		List classes = context.getNaming().allSuperClasses((GeneralizableElement)cls, false);
		Iterator c = classes.iterator();
		Set addedOper = new LinkedHashSet();
		while (c.hasNext()) {
			Classifier a_cls = (Classifier)c.next();
			String a_clsName = context.getNaming().getClassifierName(a_cls);
			// Add operations
			Iterator k = a_cls.getFeature().iterator();
			while (k.hasNext()) {
				Feature f = (Feature)k.next();
				if (f instanceof Operation) {
					Operation oper = (Operation)f;
					String opName = context.getNaming().getOperationName(oper);
					String opKey = ClassGenerator.getOperationSignature(context, oper, (Classifier)oper.getOwner(), cls, true);
					if (!addedOper.contains(opKey)) {
						addedOper.add(opKey);
						Metric operMcc = QualityModel.factory.buildMetric("oper-mcc");
						Metric m1 = qModel.evaluate(oper, operMcc);
						operNo++;
						value += m1.getValue();
					} else {
					}
				}
			}
		}        

		// Compute 
		if (operNo != 0)
			value = value/operNo;
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
}
