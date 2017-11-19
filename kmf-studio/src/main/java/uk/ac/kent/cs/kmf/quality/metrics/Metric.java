package uk.ac.kent.cs.kmf.quality.metrics;

import java.text.NumberFormat;

/**
 * @author Octavian Patrascoiu
 *
 */
public class Metric 
	implements Comparable
{
	//
	// Constructors
	//
	public Metric() {
	}
	public Metric(double value) {
		this.value = value;
	}
	public Metric(String key, double min, double max, double value) {
		this.key = key.toLowerCase();
		this.min = min;
		this.max = max;
		this.value = value;
	}

	/** Check if the metric is good */
	public boolean isContradiction() {
		if (min != Double.MIN_VALUE || min != Double.NEGATIVE_INFINITY) {
			if (min > value)
				return true;
		}
		if (max != Double.MAX_VALUE || max != Double.POSITIVE_INFINITY) {
			if (max < value)
				return true;
		}
		return false;					
	}
	
	/** Compare two metrics */
	public int compareTo(Object obj) {
		if (obj instanceof Metric) {
			Metric m1 = (Metric)obj;
			return key.compareTo(m1.getKey());
		}
		return -1;
	}
	
	//
	// Get/set methods
	//
	public String getNamespace() {
		return namespace;
	}
	public String getName() {
		return name;
	}
	public String getKey() {
		return key;
	}
	public String getType() {
		return type;
	}
	public double getValue() {
		return value;
	}
	public double getMin() {
		return min;
	}
	public double getMax() {
		return max;
	}
	public String getBody() {
		return body;
	}
	public String getDiagnostic() {
		return diagnostic;
	}

	public void setNamespace(String string) {
		namespace = string;
	}
	public void setName(String string) {
		name = string;
	}
	public void setKey(String string) {
		key = string.toLowerCase();
	}
	public void setType(String string) {
		type = string;
	}
	public void setMin(double double1) {
		min = double1;
	}
	public void setMax(double double1) {
		max = double1;
	}
	public void setValue(double double1) {
		value = double1;
	}
	public void setBody(String string) {
		body = string;
	}
	public void setDiagnostic(String string) {
		diagnostic = string;
	}
	
	/** Override toString */
	public String toString() {
		String res = "";
		res += namespace+":"+key+" - "+name;
		return res;
	}
	
	/** Make a clone */
	public Object clone() {
		Metric m1 = new Metric();
		m1.setNamespace(this.namespace);
		m1.setName(this.name);
		m1.setKey(this.key);
		m1.setType(this.type);
		m1.setBody(this.body);
		m1.setDiagnostic(this.diagnostic);
		m1.setMin(this.min);
		m1.setMax(this.max);
		m1.setValue(this.value);
		return m1;
	}

	public static double getDoubleValue(String value) {
		if (value.equals("NEGATIVE_INFINITY")) return Double.NEGATIVE_INFINITY;
		if (value.equals("POSITIVE_INFINITY")) return Double.POSITIVE_INFINITY;
		return Double.parseDouble(value);
	}

	public static String getDisplayValue(double value) {
		if (value == Double.NEGATIVE_INFINITY) return "-oo";
		if (value == Double.MIN_VALUE) return "-oo";
		if (value == Double.POSITIVE_INFINITY) return "+oo";
		if (value == Double.MAX_VALUE) return "+oo";
		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(2);
		return f.format(value);
	}

	//
	// Local properties
	//
	protected String namespace = "";
	protected String name = "";
	protected String key = "";
	protected String type = "";
	protected double min;
	protected double max;
	protected double value;
	protected String body = "";
	protected String diagnostic = "";
}
