package uk.ac.kent.cs.kmf.quality.evaluators.kmf.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 * 
 * Average Method Complexity
 * 
 * Computes the complexity for each method and 
 * than computes the arithmetic average.
 * 
 */
public class ModelAMCEvaluator
	extends Evaluator 
{
	public ModelAMCEvaluator(QualityModel qModel) {
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
		double value = 0;
		double no = 0;
		// Scan all classes        
		Set classes = context.getClasses();
		Iterator c = classes.iterator();
		while (c.hasNext()) {
			Classifier cls = (Classifier)c.next();
			//--- For each invariant ---
			Collection invs = (Collection)context.getInvariants().get(cls);
			if (invs != null) {
				Iterator j = invs.iterator();
				while (j.hasNext()) {
					String invariant = (String)j.next();
					// Compute complexity of invariant
					Metric oclMcc = QualityModel.factory.buildMetric("ocl-mcc");
					Metric m1 = qModel.evaluate(invariant, oclMcc);
					no++;
					value += m1.getValue();
				}        
			}
		}        

		// Compute
		if (no != 0)
			value = value/no;
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}
	
	//
	// Local properties
	//
	protected double no;
}
