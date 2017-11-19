package uk.ac.kent.cs.kmf.quality.metrics;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @author Octavian Patrascoiu
 *
 */
public class MetricSet {
	//
	// Constructors
	//
	public MetricSet() {
	}
	
	/** Get a metric of a given key */
	public Metric getMetric(String key) {
		Iterator i = namespaces.keySet().iterator();
		while (i.hasNext()) {
			Object namespace = i.next();
			Map metrics = (Map)namespaces.get(namespace);
			Metric m = (Metric)metrics.get(key);
			if (m != null) {
				return m;
			}
		}
		return null;
	}
	
	/** Add a metric */
	public void addMetric(Metric m) {
		String namespace = m.getNamespace(); 
		if (getMetric(m.getKey()) == null) {
			Map metrics = (Map)namespaces.get(namespace);
			if (metrics == null) {
				metrics = new LinkedHashMap();
				namespaces.put(namespace, metrics);
			} 
			metrics.put(m.getKey(), m);
		}
	}

	/** Remove a metric */
	public void removeMetric(Metric m) {
		String namespace = m.getNamespace();
		Map metrics = (Map)namespaces.get(namespace);
		metrics.remove(m.getKey());
	}
	
	//
	// Get/set methods
	//
	public List getMetrics() {
		List res = new Vector();
		Iterator i = namespaces.keySet().iterator();
		while (i.hasNext()) {
			Object namespace = i.next();
			Map metrics = (Map)namespaces.get(namespace);
			res.addAll(metrics.values());
		}
		return res;
	}

	/** Override toString */
	public String toString() {
		return getMetrics().toString();
	}
	
	//
	// Local properties
	//
	protected Map namespaces = new LinkedHashMap();
}
