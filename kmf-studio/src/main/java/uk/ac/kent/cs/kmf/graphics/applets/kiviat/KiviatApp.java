/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.applets.kiviat;

import uk.ac.kent.cs.kmf.graphics.chart.*;
import uk.ac.kent.cs.kmf.graphics.common.*;
import uk.ac.kent.cs.kmf.graphics.common.ParameterParser;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.util.*;

public class KiviatApp extends ChartAppShell {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double[] OriginalMinMaxValues;

	protected void drawDataPopup(Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int width1 = 0;
		StringTokenizer labelToks = null;
		int numLabelLines = 0;
		if (dwellLabelLabelString != null) {
			if (dwellUseString) {
				if (dwellLabelLabelString.indexOf("|") == -1) {
					width1 = fm.stringWidth(dwellLabelLabelString) + 6;
					numLabelLines = 1;
				} else { //handle multi-line dwell labels
					int maxWid = 0;
					int thisWid = 0;
					labelToks = new StringTokenizer(dwellLabelLabelString, "|");
					while (labelToks.hasMoreTokens()) {
						thisWid = fm.stringWidth(labelToks.nextToken());
						if (thisWid > maxWid)
							maxWid = thisWid;
						numLabelLines++;
					}
					width1 = maxWid + 6;
				}
			}
		}
		int width2;
		if (dwellUseXValue)
			width2 = fm.stringWidth(dwellLabelXString) + 6;
		else
			width2 = 0;
		int width3;
		if (dwellUseYValue)
			width3 = fm.stringWidth(dwellLabelYString) + 6;
		else
			width3 = 0;
		int width;
		if (width1 > width2)
			width = width1;
		else
			width = width2;
		if (width3 > width)
			width = width3;
		int lineHeight = fm.getHeight() + 4;
		int overallHeight = 4;
		if (dwellUseXValue)
			overallHeight += lineHeight;
		if (dwellUseYValue)
			overallHeight += lineHeight;
		if (dwellUseString)
			overallHeight += numLabelLines * lineHeight;

		//if((popupX + width) > getSize().width)
		if ((popupX + width) > getSize().width)
			//popupX = getSize().width - width - 10;
			popupX = getSize().width - width - 10;
		popupY = popupY - overallHeight; //shift up away from cursor
		if (popupY < 0)
			popupY = 0;

		g.setColor(Color.white);
		g.fillRect(popupX, popupY, width, overallHeight);
		g.setColor(Color.black);
		g.drawRect(popupX, popupY, width, overallHeight);
		g.drawLine(
			popupX + 1,
			popupY + overallHeight + 1,
			popupX + 1 + width,
			popupY + 1 + overallHeight);
		g.drawLine(
			popupX + width + 1,
			popupY + 1,
			popupX + 1 + width,
			popupY + 1 + overallHeight);

		int h = lineHeight;
		if ((dwellUseString) && (dwellLabelLabelString != null)) {
			if (labelToks == null) {
				g.drawString(dwellLabelLabelString, popupX + 3, popupY + h);
				h += lineHeight;
			} else { //handle multi-line dwell labels
				StringTokenizer st =
					new StringTokenizer(dwellLabelLabelString, "|");
				while (st.hasMoreTokens()) {
					g.drawString(st.nextToken(), popupX + 3, popupY + h);
					h += lineHeight;
				}
			}
		}
		if (dwellUseXValue) {
			g.drawString(dwellLabelXString, popupX + 3, popupY + h);
			h += lineHeight;
		}
		if (dwellUseYValue)
			g.drawString(dwellLabelYString, popupX + 3, popupY + h);
		popupY = popupY + overallHeight;
		//shift back down to be at original event location
	}

	protected String getDwellLabelXString(Datum d) {
		String s;
		if (dwellLabelFormat instanceof NumberFormat)
			s = ((NumberFormat) dwellLabelFormat).format(d.getX());
		else
			return dwellLabelFormat.format(new Double(d.getX()));
		int where = dwellXString.indexOf("#");
		return dwellXString.substring(0, where)
			+ s
			+ dwellXString.substring(where + 1);
	}

	protected void getOptions() {
		String str;

		//all subclasses override init, so we'll put this here
		installMouseAdapter();

		if (parser == null)
			//initialize alternate parser subclasses in child init methods
			parser = new ParameterParser(chart, this);

		//applet specific params...
		str = getParameter("networkInterval");
		if (str != null)
			networkInterval = Integer.parseInt(str);
		str = getParameter("dwellLabelsOn");
		if ((str != null) && (str.equalsIgnoreCase("false")))
			useDwellLabel = false;
		str = getParameter("dwellUseLabelString");
		if ((str != null) && (str.equalsIgnoreCase("true")))
			dwellUseString = true;
		str = getParameter("dwellUseXValue");
		if ((str != null) && (str.equalsIgnoreCase("false")))
			dwellUseXValue = false;
		str = getParameter("dwellUseYValue");
		if ((str != null) && (str.equalsIgnoreCase("false")))
			dwellUseYValue = false;
		str = getParameter("dwellXString");
		if (str != null)
			dwellXString = str;
		else
			dwellXString = "X: #";
		str = getParameter("dwellYString");
		if (str != null)
			dwellYString = str;
		else
			dwellYString = "Y: #";

		parser.getOptions();
		getMyOptions();

		if (dwellLabelFormat == null)
			//we've initialized the Locale in parser.getOptions()
			dwellLabelFormat = NumberFormat.getInstance();

		str = getParameter("dwellLabelPrecision");
		if (str != null)
			dwellLabelFormat.setMaximumFractionDigits(Integer.parseInt(str));
	}

	public void paint(Graphics g) {
		if (gotImages)
			try {
				imageTracker.waitForID(0);
			} catch (InterruptedException e) {
				return;
			}

		try {
			chart.paint(this, g);
		} catch (OutOfMemoryError e) {
			System.out.println(
				"out of memory, no label rotation or double-buffering");
			showStatus("low memory");
			chart.setStringRotator(null);
			chart.setImage(null);
			chart.drawGraph(g);
		}
		drawMyStuff(g);
		if (showDataPopup) {
			drawDataPopup(g);
			dwellLabelVisible = true;
		} else
			dwellLabelVisible = false;
	}

	/**
	  *  Get the arguments passed by the html launcher file 
	  */
	public void getMyOptions() {
		double[] minmaxkiviat = new double[2];
		String str;
		KiviatChart kiviat;

		kiviat = (KiviatChart) chart;
		str = getParameter("plotLinesOn");
		if (str != null)
			kiviat.setLineVisible(true);
		str = getParameter("plotLinesOff");
		if (str != null)
			kiviat.setLineVisible(false);
		KiviatAxis p = (KiviatAxis) (kiviat.getYAxis());
		str = getParameter("manualSpoking");
		if (str != null)
			p.setManualSpoking(true);
		str = getParameter("numSpokes");
		if (str != null)
			p.setNumSpokes(Integer.parseInt(str));

		str = getParameter("MinMaxValues");
		if (str != null)
			kiviat.addDataset("MinMaxValues", setOriginalMinMaxValues(str));

		str = getParameter("height");
		if (str != null)
			// set default plotarea values for min and max 
			minmaxkiviat[0] = (double) (Integer.parseInt(str) / 7);
		minmaxkiviat[1] = (double) (Integer.parseInt(str) / 3);

		// add our minmax datasets
		kiviat.addDataset("minmaxkiviat", minmaxkiviat);

		// Transform the original values to tailor the kiviat
		KiviatCom c = new KiviatCom();
		c.transformValues(kiviat);
	}

	/**
	*  Affect the Original min and max values
	*/
	public double[] setOriginalMinMaxValues(String s) {
		int i = 0;
		StringTokenizer st = new StringTokenizer(s, ",");
		int nbValues = st.countTokens();
		OriginalMinMaxValues = new double[nbValues];

		while (st.hasMoreTokens()) {
			String leToken = st.nextToken();
			OriginalMinMaxValues[i] =
				java.lang.Double.valueOf(leToken).doubleValue();
			i++;

		}
		return OriginalMinMaxValues;
	}

	public void init() {
		initLocale();
		chart = new KiviatChart();

		getOptions();
	}
}