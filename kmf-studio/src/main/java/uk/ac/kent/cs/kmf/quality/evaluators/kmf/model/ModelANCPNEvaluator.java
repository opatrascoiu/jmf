package uk.ac.kent.cs.kmf.quality.evaluators.kmf.model;

import java.util.Iterator;

import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.evaluators.Evaluator;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 * 
 * Average Number of Classes Per Namespace
 * 
 * Computes the number of classes for each namespace and 
 * than computes the arithmetic average.
 * 
 */
public class ModelANCPNEvaluator
	extends Evaluator 
{
	public ModelANCPNEvaluator(QualityModel qModel) {
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
		// Compute
		double value = 0;
		double no = 0;
		Iterator i = context.getPackages().iterator();
		while (i.hasNext()) {
			Object o = i.next();
			if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package) {
				// Compute complexity of invariant
				Metric nsNDCC = QualityModel.factory.buildMetric("ns-ndcc");
				Metric m1 = qModel.evaluate(o, nsNDCC);
				no++;
				value += m1.getValue();
			}
		}
		if (no != 0)
			value = value/no;
		Metric res = (Metric)m.clone();
		res.setValue(value);
		return res;	
	}

	//
	// Local properties
	//
	double no = 0;
}
