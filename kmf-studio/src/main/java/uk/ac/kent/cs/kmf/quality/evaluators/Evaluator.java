package uk.ac.kent.cs.kmf.quality.evaluators;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;

/**
 * @author Octavian Patrascoiu
 *
 */
public abstract class Evaluator {
	//
	// Constructors
	//
	public Evaluator() {
	}
	public Evaluator(QualityModel qModel) {
		this.qModel = qModel;
		this.context = qModel.getContext();
		
	}
	
	/** Computes the metric on the model element */ 
	public abstract Metric evaluate(Object element, Metric m);
	
	//
	// Local properties
	//
	protected Context context;
	protected QualityModel qModel;
}
