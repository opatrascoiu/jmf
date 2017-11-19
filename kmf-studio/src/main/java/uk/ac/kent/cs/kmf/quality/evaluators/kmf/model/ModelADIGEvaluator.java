package uk.ac.kent.cs.kmf.quality.evaluators.kmf.model;

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
 * Average Depth of Inheritance Graph
 * 
 * Computes the depth of inheritance for each class and 
 * than computes the arithmetic average.
 * 
 */
public class ModelADIGEvaluator
	extends Evaluator 
{
	public ModelADIGEvaluator(QualityModel qModel) {
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
			Classifier a_cls = (Classifier)c.next();
			// Compute depth of inheritance
			Metric clsDIT = QualityModel.factory.buildMetric("cls-dig");
			clsDIT = qModel.evaluate(a_cls, clsDIT);
			if (clsDIT != null) {
				value += clsDIT.getValue();
				no++;
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
