package uk.ac.kent.cs.kmf.graphics.applets.pie;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.NumberFormat;

import uk.ac.kent.cs.kmf.graphics.chart.Datum;
import uk.ac.kent.cs.kmf.graphics.chart.PieChart;
import uk.ac.kent.cs.kmf.graphics.common.ChartAppShell;
import uk.ac.kent.cs.kmf.graphics.common.ParameterParser;

public class PieApp
	extends ChartAppShell 
{
	protected void drawDataPopup(Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		if (useLabel) {
			g.setColor(Color.white);
			g.fillRect(5, 5, (message.length() * 11) / 2, 20);
			g.setColor(Color.black);
			g.drawString(message, 10, 20);
			g.drawRect(5, 5, (message.length() * 11) / 2, 20);
			g.drawLine(6, 26, 6 + ((message.length() * 11) / 2), 26);
			g.drawLine(
				6 + ((message.length() * 11) / 2),
				6,
				6 + ((message.length() * 11) / 2),
				26);
		}
	}

	protected String getDwellLabelXString(Datum d) {
		String s;
		if (dwellLabelFormat instanceof NumberFormat) {
			s = ((NumberFormat) dwellLabelFormat).format(d.getX());
			sliceToExplode = (Integer.valueOf(s)).intValue();
		} else {
			return dwellLabelFormat.format(new Double(d.getX()));
		}
		int where = dwellXString.indexOf("#");
		//return dwellXString.substring(0,where) + s 
		//+ dwellXString.substring(where + 1);
		return dwellXString;

	}

	protected void getOptions() {
		String str;

		//all subclasses override init, so we'll put this here
		installMouseAdapter();

		if (parser == null)
			//initialize alternate parser subclasses in child init methods
			parser = new ParameterParser(chart, this);

		//applet specific params...
		if ((getCodeBase().getHost()).equals("www.ve.com"))
			home = true;
		else {
			str = getParameter("CopyrightNotification");
			if (str != null)
				if (str.equals("JavaChart is a copyrighted work, and subject to full legal protection"))
					home = true;
				else if (str.equals("JavaChart is a copyrighted work, and subject to full legal protection"))
					home = true;
		}
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

		// dwell Label is a simple popup with a string
		// if no parameter is given , it draw "Quality Report" string 
		str = getParameter("message");
		if (str != null)
			message = str;
		else
			message = "Quality Report";
		useLabel = true;

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
			System.out.println("out of memory, no label rotation or double-buffering");
			showStatus("low memory");
			chart.setStringRotator(null);
			chart.setImage(null);
			chart.drawGraph(g);
		}
		drawMyStuff(g);
		if (showDataPopup) {
			p = (PieChart) chart;
			p.getPie().setExplosion(sliceToExplode, 0.05);
			p.drawGraph(g);
			drawDataPopup(g);
			dwellLabelVisible = true;
		} else
			dwellLabelVisible = false;
		if (sliceToExplode != -1) {
			p.getPie().setExplosion(sliceToExplode, 0.00);
		}
	}

	public void getMyOptions() {
		String str;
		PieChart p;

		p = (PieChart) chart;
		str = getParameter("explodeSlice");
		if (str != null)
			p.getPie().setExplosion((Integer.valueOf(str)).intValue(), 0.05);
		str = getParameter("textLabelsOn");
		if (str != null) {
			p.getPie().setTextLabelsOn(true);
		}
		str = getParameter("textLabelsOff");
		if (str != null) {
			p.getPie().setTextLabelsOn(false);
		}
		str = getParameter("valueLabelsOn");
		if (str != null) {
			p.getPie().setValueLabelsOn(true);
		}
		str = getParameter("valueLabelsOff");
		if (str != null) {
			p.getPie().setValueLabelsOn(false);
		}
		str = getParameter("percentLabelsOn");
		if (str != null) {
			p.getPie().setPercentLabelsOn(true);
		}
		str = getParameter("percentLabelsOff");
		if (str != null) {
			p.getPie().setPercentLabelsOn(false);
		}
		str = getParameter("labelPosition");
		if (str != null) {
			p.getPie().setLabelPosition(Integer.parseInt(str));
		}
		str = getParameter("startDegrees");
		if (str != null) {
			p.getPie().setStartDegrees(Integer.parseInt(str));
		}
		str = getParameter("pieWidth");
		if (str != null) {
			p.getPie().setWidth((Double.valueOf(str)).doubleValue());
		}
		str = getParameter("pieHeight");
		if (str != null) {
			p.getPie().setHeight((Double.valueOf(str)).doubleValue());
		}
		str = getParameter("xLoc");
		if (str != null) {
			p.getPie().setXLoc((Double.valueOf(str)).doubleValue());
		}
		str = getParameter("yLoc");
		if (str != null) {
			p.getPie().setYLoc((Double.valueOf(str)).doubleValue());
		}
		str = getParameter("labelFont");
		if (str != null) {
			p.getPie().setLabelFont(ParameterParser.getFont(str));
		}
		str = getParameter("labelColor");
		if (str != null) {
			p.getPie().setLabelColor(parser.getColor(str));
		}
	}
	public void init() {
		initLocale();
		chart = new PieChart();
		getOptions();
	}

	//
	// Local attributes
	//
	protected String message;
	protected boolean useLabel;
	public int sliceToExplode = -1;
	public PieChart p;
}