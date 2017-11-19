package uk.ac.kent.cs.kmf.quality;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement;

/**
 * @author Octavian Patrascoiu
 *
 */
public class PieGenerator
	extends HtmlGenerator
{
	/** Constructor */
	public PieGenerator(QualityModel qualityModel, String pathname, ILog log) {
		super(qualityModel, pathname, log);
	}
	
	/** Make the html string */
	protected String html(Object obj) {
		// Compute the excellent, good, acceptable, and poor elements
		computeParams(obj);
		// Make html
		String res = htmlHeader();			
		res += "<body>\n";
		// Add pie applet
		res += generatePieApplet(labels, values, links);
		res +="<br>\n";
		res +="<hr>\n";
		// Add links to subsequent sections
		res += link2section("Excellent", excellentElems);
		res += link2section("Good", goodElems);
		res += link2section("Acceptable", acceptableElems);
		res += link2section("Poor", poorElems);
		res +="<br>\n";
		res +="<hr>\n";
		// Add sections for excellent, good, acceptable, and poor
		res += section("Excellent", excellentElems);
		res += section("Good", goodElems);
		res += section("Acceptable", acceptableElems);
		res += section("Poor", poorElems);
		res += "</body>\n";			
		return res;
	}

	/** Compute the excellent, good, acceptable, and poor elements */
	protected void computeParams(Object obj) {
		// Make a list of single objects
		if (obj instanceof ModelElement) {
			Object temp = obj;
			obj = new Vector();
			((Collection)obj).add(temp);
		}
		// Partition a collection
		if (obj instanceof Collection) {
			Collection col = (Collection)obj;
			Iterator i = col.iterator();
			while (i.hasNext()) {
				ModelElement e = (ModelElement)i.next();
				// Get metrics and count violations
				List metrics = qualityModel.getMetrics(e);
				if (metrics.size() != 0) {
					int no = 0;
					for(int j=0; j<metrics.size(); j++) {
						Metric m = (Metric)metrics.get(j);
						if (m.isContradiction())
							no++;
					}
					// Add element to partition
					double ratio = 1.0*no/metrics.size();
					if (ratio == 0) 
						excellentElems.add(e);
					else if (ratio < 0.1)
						goodElems.add(e);
					else if (ratio < 0.4)
						acceptableElems.add(e);
					else
						poorElems.add(e);
				}
			}			
			// Compute labels and values
			addLabelAndValue("Excellent", excellentElems, col.size());
			addLabelAndValue("Good", goodElems, col.size());
			addLabelAndValue("Acceptable", acceptableElems, col.size());
			addLabelAndValue("Poor", poorElems, col.size());
		}
	}
	protected void addLabelAndValue(String label, List set, int total) {
		if (set.size() != 0) {
			labels.add(label);
			values.add(new Double(100.0*set.size()/total));				
		}

	}
	
	/** Make the html link */
	protected String link2section(String label, List elements) {
		String res = "";
		if (elements.size() == 0) return res;
		res += "<h1><a href=#"+label+">"+label+"</a><h1>\n";
		return res;		
	}

	/** Make the html section */
	protected String section(String label, List elements) {
		String res = "";
		if (elements.size() == 0) return res;
		res += "<p><h1><a name="+label+">"+label+"</a></h1></p>\n";
		for(int i=0; i<elements.size(); i++) {
			Object elem = elements.get(i);
			res += htmlBody(elem);
		}
		return res;		
	}
	
	/** Makes the header */
	protected String htmlHeader() {
		String res = "";
		res += "<html>\n";
		res += "<head>\n";
		res += "<title>Quality Report</title>\n";
		res += "<center>\n";
		res += "  <h1>Quality Report</h1>\n";
		res += "</center>\n";
		res += "</head>\n";
		return res;
	}

	/** Makes the body */
	protected String htmlBody(Object obj) {
		String res = "";
		// Compute title
		String title = "<h2>Kiviat Diagram for '"+getObjectName(obj)+"'</h2>\n";
		// Compute the list of metrics available
		List metrics = qualityModel.getMetrics(obj);
		// Add metrics table header
		String sp = "&nbsp;";
		res += "<center>\n";
		res += title;
		res += "</center>\n";
		res +="<center>\n";
		res +="<table border=\"1\">\n";
		res +="  <tr>\n";
		res +="     <td align=\"center\">"+sp+"Metric namespace"+sp+"</td>\n";
		res +="     <td align=\"center\">"+sp+"Metric name"+sp+"</td>\n";
		res +="     <td align=\"center\">"+sp+"Metric key"+sp+"</td>\n";
		res +="     <td align=\"center\">"+sp+"Minimum value"+sp+"</td>\n";
		res +="     <td align=\"center\">"+sp+"Maximum value"+sp+"</td>\n";
		res +="     <td align=\"center\">"+sp+"Value"+sp+"</td>\n";
		res +="  </tr>\n";
		// Compute applet's labels and values
		List labels = new Vector();
		List minMaxValues = new Vector();
		List values = new Vector(); 
		boolean valueOk = true;
		for(int i=0; i<metrics.size(); i++) {
			Metric metric = (Metric)metrics.get(i);
			double min = metric.getMin();
			double max = metric.getMax();
			double value = metric.getValue();
			// Add metrics
			res +="  <tr>\n";
			res +="     <td>"+sp+metric.getNamespace()+sp+"</td>\n";
			res +="     <td>"+sp+metric.getName()+sp+"</td>\n";
			res +="     <td>"+sp+metric.getKey()+sp+"</td>\n";
			res +="     <td>"+sp+Metric.getDisplayValue(min)+sp+"</td>\n";
			res +="     <td>"+sp+Metric.getDisplayValue(max)+sp+"</td>\n";
			res +="     <td>"+sp+Metric.getDisplayValue(value)+sp+"</td>\n";
			res +="  </tr>\n";
			min = convertValue(min);
			max = convertValue(max);
			value = convertValue(value);
			labels.add(metric.getKey()); 
			minMaxValues.add(new Double(min)); 
			minMaxValues.add(new Double(max));
			values.add(new Double(value));
		}
		// Generate applet
		res +="  <tr>\n";
		res += generateKiviatApplet(labels, minMaxValues, values);
		res +="  </tr>\n";
		// Close table
		res +="</table>\n";
		res +="</center>\n";
		res +="<br>\n";
		res +="<hr>\n";
		return res;
	}

	//
	// Local properties
	//
	protected List excellentElems = new Vector();
	protected List goodElems = new Vector();
	protected List acceptableElems = new Vector();
	protected List poorElems = new Vector();
	protected List labels = new Vector();
	protected List values = new Vector();
	protected List links = new Vector();
}
