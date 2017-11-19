package uk.ac.kent.cs.kmf.quality.evaluators;

import java.util.List;

import uk.ac.kent.cs.kmf.kmfstudio.gui.StudioGUI;
import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.standard.lib.OclReal;

/**
 * @author Octavian Patrascoiu
 *
 */
public class OclEvaluator 
	extends Evaluator
{
	public OclEvaluator(QualityModel qModel) {
		super(qModel);
	}
	/** Computes the metrics assciated with modele element */ 
	public Metric evaluate(Object element, Metric m) {
		String oclString = m.getBody();
		if (oclString != null && oclString.length() != 0) {
			// Filter elements
			if (filter(element, m)) {
				// Set OclProcessor
				OclProcessor oclProcessor = StudioGUI.metaOclProcessor;
				List list = ((List)oclProcessor.evaluate(oclString, element));
				if (list != null && list.size() != 0) {
					Object obj = list.get(0);
					if (obj instanceof OclReal) {
						double value = Double.parseDouble(((OclReal)obj).toString());
						Metric res = (Metric)m.clone();
						res.setValue(value);
						return res;	
					}
				}
			}
		}
		return null;
	}
	protected boolean filter(Object element, Metric m) {
		// Get class pathname from element
		String classFromElement = element.toString();
		int pos = classFromElement.indexOf("'");		if (pos != -1) classFromElement = classFromElement.substring(0, pos-1);
		if (classFromElement.endsWith("Class_")) classFromElement = classFromElement.substring(0, classFromElement.length()-1);
		classFromElement = classFromElement.replaceAll("[.]", "::"); 
		// Get class path name from metric
		String classFromMetric = m.getBody().trim();
		if (classFromMetric.startsWith("context")) classFromMetric = classFromMetric.substring(7, classFromMetric.length()).trim();
		pos = classFromMetric.indexOf("inv");
		if (pos != -1) classFromMetric = classFromMetric.substring(0, pos-1);
		if (classFromElement.equals(classFromMetric))
			return true;
		else
			return false;	
	}
}
