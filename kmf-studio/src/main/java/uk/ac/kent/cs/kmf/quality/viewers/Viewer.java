package uk.ac.kent.cs.kmf.quality.viewers;

import uk.ac.kent.cs.kmf.quality.BrowserLauncher;
import uk.ac.kent.cs.kmf.quality.HtmlGenerator;
import uk.ac.kent.cs.kmf.quality.KiviatGenerator;
import uk.ac.kent.cs.kmf.quality.PieGenerator;
import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;

/**
 * @author Octavian Patrascoiu
 *
 */
public class Viewer 
{
	/** 
	 * Constructor
	 * obj can be either a model element or
	 * a list of model elements 
	 * */
	public Viewer(QualityModel qualityModel, Object obj, int kind) {
		this.qualityModel = qualityModel;
		this.obj = obj;
		this.kind = kind;
	}

	public void view(boolean onlyViolations) {
		// Generate html
		ILog log = new OutputStreamLog(System.out);
		if (kind == KIVIAT) {
			HtmlGenerator gen = new KiviatGenerator(qualityModel, htmlFile, log, onlyViolations);
			gen.generate(obj);
		} else {
			HtmlGenerator gen = new PieGenerator(qualityModel, htmlFile, log);
			gen.generate(obj);
		}
		try {
			BrowserLauncher.openURL(htmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//
	// Local properties
	//
	public static int KIVIAT = 1;
	public static int PIE = 2;
	protected QualityModel qualityModel;
	protected int kind = KIVIAT;
	protected Object obj;
	protected String htmlFile = "Quality.html";
}
