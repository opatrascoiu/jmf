package uk.ac.kent.cs.kmf.quality.metrics;

import java.util.List;

import uk.ac.kent.cs.kmf.kmfstudio.gui.StudioGUI;

/**
 * @author Octavian Patrascoiu
 *
 */
public class MetricFactory
{
	/** Metric set */
	public MetricSet buildMetricSet() {
		return new MetricSet();
	}
	public MetricSet buildMetricSet(String name, List metrics) {
		return new MetricSet();
	}

	/** Metric */
	public Metric buildMetric() {
		Metric res = new Metric();
		return res;			
	}
	public Metric buildMetric(
			String name, String key, String type,
			String body, String message,
			double min, double max) {
		Metric res = buildMetric();
		res.setName(name);
		res.setKey(key);
		res.setType(type);
		res.setBody(body);
		res.setDiagnostic(message);
		res.setMin(min);
		res.setMax(max);
		return res;
	}
	public Metric buildMetric(
			String name, String key, String type,
			String body, String message,
			double min, double max, double value) {
		Metric res = buildMetric(name, key, type, body, message, min, max);
		res.setValue(value);
		return res;			
	}
	public Metric buildMetric(String key) {
		MetricSet mSet = StudioGUI.allMetricSet;
		Metric m = mSet.getMetric(key);
		if (m == null) {
			System.out.println("Cannot build metric '"+key+"'");
		}
		return (Metric)m.clone();
	}
}
