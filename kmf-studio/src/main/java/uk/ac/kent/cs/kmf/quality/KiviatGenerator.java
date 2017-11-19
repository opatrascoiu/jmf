package uk.ac.kent.cs.kmf.quality;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository$Class;

/**
 * @author Octavian Patrascoiu
 *
 */
public class KiviatGenerator 
	extends HtmlGenerator
{
	/** Constructor */
	public KiviatGenerator(QualityModel qualityModel, String pathname, ILog log, boolean onlyViolations) {
		super(qualityModel, pathname, log);
		this.onlyViolations = onlyViolations;
	}

	/** Make the html string */
	protected String html(Object obj) {
		String res = htmlHeader();	
		res += "<body>\n";
		if (obj instanceof ModelElement) {
			res += htmlBody(obj);
		} else if (obj instanceof Collection) {
			Collection col = (Collection)obj;
			Iterator i = col.iterator();
			while (i.hasNext()) {
				ModelElement e = (ModelElement)i.next();
				res += htmlBody(e);
			}			
		}
		res += "</body>\n";			
		return res;
	}
		
	//
	// Local methods
	//
	/** Makes the header */
	protected String htmlHeader() {
		String res = "";
		res += "<html>\n";
		res += "<head>\n";
		res += "<title>Kiviat Diagrams</title>\n";
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
		boolean isViolation = isViolation(metrics); 		
		if (isViolation) {
			title = "<h2><blink><font color=\"#800000\"><u>Kiviat Diagram for '"+getObjectName(obj)+"'</u></font></blink></h2>\n";
		}
		// Add metrics table header
		boolean add = true;
		if (onlyViolations) {
			if (isViolation == false)
				add = false;
		}
		if (add) {
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
		}
		return res;
	}
	protected boolean isViolation(List metrics) {
		for(int i=0; i<metrics.size(); i++) {
			Metric m = (Metric)metrics.get(i);
			if (m.isContradiction())
				return true;
		}
		return false;
	}
		
	//
	// Local properties
	//
	protected boolean onlyViolations;
	
	//
	// Test
	//
	public static void main(String args[]) {
		// Create metrics
		List metrics = new Vector();
		Metric m1 = new Metric("m1", 1, 4, 3.5);
		metrics.add(m1);
		Metric m2 = new Metric("m2", Double.MIN_VALUE, 8, Double.POSITIVE_INFINITY);
		metrics.add(m2);
		Metric m3 = new Metric("m3", 1, Double.POSITIVE_INFINITY, 3.5);
		metrics.add(m3);
		// Create a quality model
		QualityModel qModel = new QualityModel();
		UmlRepository rep = new UmlRepository$Class();
		Classifier cls = (Classifier)rep.buildElement("uml.Foundation.Core.Classifier");
		Name name = (Name)rep.buildElement("uml.Foundation.Data_Types.Name");
		name.setBody_("A");
		cls.setName(name);
		qModel.setMetrics(cls, metrics);
		// Generate html
		ILog log = new OutputStreamLog(System.out);
		KiviatGenerator gen = new KiviatGenerator(qModel, "test.html", log, false);
		gen.generate(cls);
		try {
			BrowserLauncher.openURL("test.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
