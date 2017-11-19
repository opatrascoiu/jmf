package uk.ac.kent.cs.kmf.quality;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;

/**
 * @author Octavian Patrascoiu
 *
 */
public abstract class HtmlGenerator {
	/** Constructor */
	public HtmlGenerator(QualityModel qualityModel, String pathname, ILog log) {
		this.qualityModel = qualityModel; 
		this.pathname = pathname;
		this.log = log;
		this.context = qualityModel.getContext();
	}

	/** Generate the html file */
	public void generate(Object obj) {
		try {
			// Create file
			File f = new File(pathname);
			PrintWriter out = new PrintWriter(new FileOutputStream(f));
			// Print out
			out.println(html(obj));
			out.close();
		} catch (Exception e) {
			log.reportError("Cannot find file '"+pathname+"'");
		}		
	}

	/** Generate the html file */
	protected abstract String html(Object obj);
	
	protected String generateKiviatApplet(List labels, List minMaxValues, List values) {
		String strValues = makeStr(values, ",");
		String strMinMax = makeStr(minMaxValues, ",");
		String strLabels = makeStr(labels, ",");
		String res = "";
		res += "<center>\n";
		res += "<applet code=uk.ac.kent.cs.kmf.graphics.applets.kiviat.KiviatApp.class archive=quality.jar width=600 height=500>\n";
		res += "<param name=dataset0yValues value='"+strValues+"'>\n";
		res += "<param name=MinMaxValues value='"+strMinMax+"'>\n";
		res += "<param name=yAxisLabelFormat value=0>\n";
		res += "<param name=yAxisoptions value='tickOff,labelsOff,lineOff'>\n";
		res += "<param name=plotAreaTop value=0.9>\n";
		res += "<param name=plotAreaBottom value=0.1>\n";
		res += "<param name=plotAreaLeft value=0.1>\n";
		res += "<param name=plotAreaLeft value=0.1>\n";
		res += "<param name=xAxisLabels value='"+strLabels+"'>\n";
		res += "</applet>\n";
		res += "</center>\n";
		return res;
	}

	protected String generatePieApplet(List labels, List values, List links) {
		String res = "";
		// Add constant part
		res += "<center>\n";
		res += "<applet code=uk.ac.kent.cs.kmf.graphics.applets.pie.KmfPie.class archive=quality.jar width=600 height=300>\n";
		res += "<param name=pieWidth value='0.3'>\n";
		res += "<param name=2D value='true'>\n";
		res += "<param name=yDepth value='8'>\n";
		res += "<param name=yLoc value='0.60'>\n";
		res += "<param name=legendOn value='true'>\n";
		res += "<param name=legendVertical value='true'>\n";
		res += "<param name=legendllX value='0.2'>\n";
		res += "<param name=legendllY value='0.01'>\n";
		res += "<param name=labelsOff value='true'>\n";
		res += "<param name=percentLabelsOn value='true'>\n";
		res += "<param name=textLabelsOff value='true'>\n";
		res += "<param name=labelFormat value='1'>\n";
		res += "<param name=message value='Click on the links to retrieve items that belong to this category'>\n";
		// Add variable parameters
		String appletLabel = makeStr(labels, ",");
		String appletValue = makeStr(values, ",");
		String appletLink = makeStr(links, ",");
		int nbZero = 0;
		for(int i=0; i<values.size(); i++) {
			Double value = (Double)values.get(i);
			if (value.doubleValue() == 0)
				nbZero++;
		}
		int nbLabel = labels.size();
		if (nbZero == nbLabel) {
			appletLabel = "";
			appletValue = "";
			appletLink = "";
			res += "<param name=dataset0Colors value=''>\n";
			res += "<param name=titleString value='Cannot Draw Pie - Only zeros values'>\n";
		} else {
			switch (nbLabel) {
				case 4:
					res += "<param name=dataset0Colors value='0080FF,1FE0A6,FAF030,red'>\n";
					break;
				case 3:
					res += "<param name=dataset0Colors value='0080FF,1FE0A6,FAF030'>\n";
					break;
				case 2:
					res += "<param name=dataset0Colors value='0080FF,1FE0A6'>\n";
					break;
				case 1:
					if (!appletValue.equals("")) {
						res += "<param name=dataset0Colors value='0080FF'>\n";
					} else {
						appletLabel = "";
						appletValue = "";
						appletLink = "";
						res += "<param name=dataset0Colors value=''>\n";
						res += "<param name=titleString value='Cannot Draw Pie - No values specified'>\n";
					}
					break;
			}
			res += "<param name=dataset0Links value='"+appletLink+"'>\n";
			res += "<param name=dataset0Labels value='"+appletLabel+"'>\n";
			res += "<param name=dataset0yValues value='"+appletValue+"'>\n";
			res += "<param name=target value='_self'>\n";
			res += "<param name=labelPosition value='2'>\n";
			res += "<param name=legendHorizontal value='true'>\n";
			res += "<param name=legendllY value='0.0'>\n";
		}			
		res += "</applet>\n";
		res += "</center>\n";
		return res;
	}
	
	/** Get the name of a model element */
	protected String getObjectName(Object obj) {
		if (obj == null) return "null";
		if (obj instanceof Model) {
			return context.getNaming().getFullPackageName((Package)obj);
		} else	if (obj instanceof Package) {
			return context.getNaming().getFullPackageName((Package)obj);
		} else if (obj instanceof Classifier) {
			return context.getNaming().getFullClassifierName((Classifier)obj);
		} else if (obj instanceof Operation) {
			return context.getNaming().getOperationName((Operation)obj);
		} else {
			return "";
		}
	}

	/** Convert +oo and -oo to maximum and minimum values */
	protected double convertValue(double value) {
		if (value == Double.NEGATIVE_INFINITY)
			return Double.MIN_VALUE;
		if (value == Double.POSITIVE_INFINITY)
			return Double.MAX_VALUE;
		return value;
	}

	/** Make a string out of a list */
	protected String makeStr(List values, String sep) {
		String res = "";
		for(int i=0; i<values.size(); i++) {
			if (i != 0)
				res += ",";
			res += values.get(i).toString();
		}
		return res;
	}

	//
	// Local properies
	//
	protected QualityModel qualityModel;
	protected String pathname;
	protected ILog log;
	protected Context context;
}
