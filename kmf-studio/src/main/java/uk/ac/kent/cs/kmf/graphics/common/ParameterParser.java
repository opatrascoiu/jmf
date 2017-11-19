/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.common;

import java.awt.*;
import java.io.*;
import java.net.*;
import uk.ac.kent.cs.kmf.graphics.chart.*;

import java.util.*;
import java.text.*;
/**
 * A shared parameter parser for applets and servlets.
 */
public class ParameterParser {
	protected ChartInterface chart;
	protected GetParam getter;
	protected static String delimiter = ",";
	//static so we can get a defaultFont before instantiating, minor danger that diff applets will want diff delimiter
	protected double yArr[] = { 10, 15, 25, 50};
	boolean gotDatasets = false;
	protected URL myUrl;
	protected Format defaultNumberFormat = NumberFormat.getInstance();
	protected Format dataRepFormat = null;
	protected Format yAxisFormat = null;
	protected Format xAxisFormat = null;
	/**
	 * Constructor that installs a chart and parameter getter.
	 * @param c javachart.chart.Chart
	 */
	public ParameterParser(ChartInterface c, GetParam g) {
		chart = c;
		getter = g;
	}
	public boolean closeURL(InputStream myInputStream) {
		try {
			myInputStream.close();
		} catch (java.io.IOException e) {
			System.out.println("can't close URL");
			return false;
		}
		return true;
	}
	public void getAxisOptions() {
		parseAxOptions("yAxis", chart.getYAxis());
		parseAxOptions("xAxis", chart.getXAxis());
		String str = getParameter("xAxisLabels");
		if (str != null)
			getXAxisLabels(str);
	}
	public void getAxisOptions(AxisInterface ax, String s) {
		if (s.indexOf("logScaling") != -1)
			ax.setLogScaling(true);
		if (s.indexOf("autoScale") != -1)
			ax.setAutoScale(true);
		if (s.indexOf("noAutoScale") != -1)
			ax.setAutoScale(false);
		if (s.indexOf("lineOn") != -1)
			ax.setLineVis(true);
		if (s.indexOf("lineOff") != -1)
			ax.setLineVis(false);
		if (s.indexOf("labelsOn") != -1)
			ax.setLabelVis(true);
		if (s.indexOf("labelsOff") != -1)
			ax.setLabelVis(false);
		if (s.indexOf("gridOn") != -1)
			ax.setGridVis(true);
		if (s.indexOf("gridOff") != -1)
			ax.setGridVis(false);
		if (s.indexOf("tickOn") != -1)
			ax.setMajTickVis(true);
		if (s.indexOf("tickOff") != -1)
			ax.setMajTickVis(false);
		if (s.indexOf("minTickOn") != -1)
			ax.setMinTickVis(true);
		if (s.indexOf("minTickOff") != -1)
			ax.setMinTickVis(false);
		if (s.indexOf("rightAxis") != -1)
			ax.setSide(3);
		if (s.indexOf("leftAxis") != -1)
			ax.setSide(1);
		if (s.indexOf("topAxis") != -1)
			ax.setSide(2);
		if (s.indexOf("bottomAxis") != -1)
			ax.setSide(0);
		if (s.indexOf("rotateTitle") != -1)
			ax.setTitleRotated(true);
	}
	public Color getColor(String s) {
		if (s.equalsIgnoreCase("black"))
			return Color.black;
		if (s.equalsIgnoreCase("white"))
			return Color.white;
		if (s.equalsIgnoreCase("lightGray"))
			return Color.lightGray;
		if (s.equalsIgnoreCase("gray"))
			return Color.gray;
		if (s.equalsIgnoreCase("darkGray"))
			return Color.darkGray;
		if (s.equalsIgnoreCase("red"))
			return Color.red;
		if (s.equalsIgnoreCase("pink"))
			return Color.pink;
		if (s.equalsIgnoreCase("orange"))
			return Color.orange;
		if (s.equalsIgnoreCase("yellow"))
			return Color.yellow;
		if (s.equalsIgnoreCase("green"))
			return Color.green;
		if (s.equalsIgnoreCase("magenta"))
			return Color.magenta;
		if (s.equalsIgnoreCase("cyan"))
			return Color.cyan;
		if (s.equalsIgnoreCase("blue"))
			return Color.blue;
		if (s.equalsIgnoreCase("transparent"))
			return Gc.TRANSPARENT;
		try {
			return new Color(Integer.parseInt(s, 16));
		} catch (NumberFormatException e) {
		}
		return Color.black;
	}
	public boolean getDataset(int which) {
		double xArr[] = null;
		double yArr[] = null;
		double y2Arr[] = null;
		double y3Arr[] = null;
		String str;

		/**** get data for dataset "which" *******/
		str = getParameter("dataset" + which + "xValues");
		if (str != null) {
			xArr = getVals(str);
		}

		str = getParameter("dataset" + which + "dateValues");
		if (str != null) {
			xArr = getDateVals(str);
		}

		str = getParameter("dataset" + which + "yValues");
		if (str != null) {
			yArr = getVals(str);
		}

		str = getParameter("dataset" + which + "y2Values");
		if (str != null) {
			y2Arr = getVals(str);
		}

		str = getParameter("dataset" + which + "y3Values");
		if (str != null) {
			y3Arr = getVals(str);
		}

		str = getParameter("dataset" + which + "xyValues");
		if (str != null) {
			StringTokenizer st = new StringTokenizer(str, delimiter);
			xArr = new double[st.countTokens() / 2];
			yArr = new double[xArr.length];
			int i = 0;
			while (st.hasMoreTokens()) {
				try {
					xArr[i] =
						Double.valueOf((st.nextToken()).trim()).doubleValue();
					yArr[i] =
						Double.valueOf((st.nextToken()).trim()).doubleValue();
				} catch (Exception e) {
					xArr[i] = Double.NEGATIVE_INFINITY;
					yArr[i] = Double.NEGATIVE_INFINITY;
				}
				i++;
			}
		}

		return (getDatasetParameters(which, xArr, yArr, y2Arr, y3Arr));
	}
	public boolean getDatasetParameters(
		int which,
		double xArr[],
		double yArr[],
		double y2Arr[],
		double y3Arr[]) {
		String labels[] = null;
		Dataset dataset;
		String str;
		String setName;
		int i;
		InputStream myInputStream;

		/*** get dataset name ***/
		str = getParameter("dataset" + which + "Name");
		if (str != null)
			setName = new String(str);
		else
			setName = new String("dataset" + which);

		/**** get labels for dataset "which" ******/
		str = getParameter("dataset" + which + "Labels");
		if (str != null) {
			labels = getLabels(str);
		}

		/*** check for URL labels ****/
		str = getParameter("dataset" + which + "URLLabels");
		if (str != null) {
			if ((myInputStream = openURL(str)) != null) {
				str = getLineFromURL(myInputStream);
				labels = getLabels(str);
			}
			closeURL(myInputStream);
		}

		/***HLOC chart***/
		if ((y3Arr != null) && (y2Arr != null) && (yArr != null)) {
			chart.addDataset(setName, xArr, yArr, y2Arr, y3Arr);
		}
		/*** add dataset based on whether got xvals,yvals,labels ****/
		/*** a hilo chart's data **/
		else if ((y2Arr != null) && (yArr != null) && (labels != null)) {
			if (xArr == null)
				chart.addDataset(setName, yArr, y2Arr, labels);
			else
				chart.addDataset(setName, xArr, yArr, labels);
		} else if ((y2Arr != null) && (yArr != null) && (labels == null))
			if (xArr == null)
				chart.addDataset(setName, yArr, y2Arr);
			else
				chart.addDataset(setName, xArr, yArr);
		/*** every other kind of chart ***/
		else if (yArr != null)
			if (labels != null)
				if (xArr == null)
					chart.addDataset(setName, yArr, labels);
				else
					chart.addDataset(setName, xArr, yArr, labels);
			else if (xArr == null)
				chart.addDataset(setName, yArr);
			else
				chart.addDataset(setName, xArr, yArr);

		if (yArr == null) // we've got *no* data so return
			return false;

		str = getParameter("dataset" + which + "Color");
		if (str != null) {
			chart.getDatasets()[which].getGc().setFillColor(getColor(str));
			chart.getDatasets()[which].getGc().setLineColor(getColor(str));
		}

		/*** get individual colors for dataset "which" *********/
		str = getParameter("dataset" + which + "Colors");
		if (str != null) {
			labels = getLabels(str);
			dataset = chart.getDatasets()[which];
			for (i = 0; i < labels.length; i++) {
				dataset.getDataElementAt(i).getGc().setFillColor(
					getColor(labels[i]));
			}
		}

		str = getParameter("dataset" + which + "LineWidth");
		if (str != null) {
			chart.getDatasets()[which].getGc().setLineWidth(
				Integer.parseInt(str));
		}
		str = getParameter("dataset" + which + "LabelFont");
		if (str != null) {
			chart.getDatasets()[which].setLabelFont(getFont(str));
		}
		str = getParameter("dataset" + which + "LabelColor");
		if (str != null) {
			chart.getDatasets()[which].setLabelColor(getColor(str));
		}
		str = getParameter("dataset" + which + "Image");
		if (str != null) {
			chart.getDatasets()[which].getGc().setImage(makeURLImage(str));
		}
		return true;
	}
	public void getDatasets() {
		int i = 0;

		if (gotDatasets)
			return;
		gotDatasets = true;
		while (getDataset(i))
			i++;
	}
	//do-nothing version.  DateStreamReader implements for DateApp applets and servlets
	protected double[] getDateVals(String s) {
		return null;
	}
	public static Font getFont(String str) {
		String fontName;
		int size, style, i, j;
		String s;
		Font f;

		i = str.indexOf(delimiter, 0);
		fontName = str.substring(0, i);
		j = str.indexOf(delimiter, i + 1);
		size = (Integer.valueOf(str.substring(i + 1, j))).intValue();
		style = (Integer.valueOf(str.substring(j + 1))).intValue();
		f = new Font(fontName, style, size);
		if (f != null)
			return f;
		else
			return Gc.defaultFont;
	}
	public String[] getLabels(String s) {
		StringTokenizer st = new StringTokenizer(s, delimiter);
		String labels[] = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			try {
				labels[i] = st.nextToken().trim();
			} catch (Exception e) {
				labels[i] = " "; //default value
			}
			i++;
		}
		return labels;
	}
	//slightly shorter StringBuffer version
	public synchronized String getLineFromURL(InputStream myInputStream) {
		int ch = -1;
		StringBuffer sb = new StringBuffer(256);

		while (true) {
			try {
				ch = myInputStream.read();
			} catch (java.io.IOException e) {
				System.out.println("bad i/o operation");
				return null;
			}
			if (ch == -1) {
				if (sb.length() == 0)
					return null;
				else
					return (sb.toString());
			} else if ((ch == '\n') || (ch == '\r')) {
				if (sb.length() > 0)
					return (sb.toString());
			} else {
				sb.append((char) ch);
			}
		}
	}
	public void getOptions() {
		String str;

		str = getParameter("delimiter");
		if (str != null)
			delimiter = new String(str);
		str = getParameter("URLDataBlock");
		if (str != null)
			getURLDataBlock(str);
		str = getParameter("URLXYDataRows");
		if (str != null)
			getURLXYDataRows(str);
		str = getParameter("URLXYDataColumns");
		if (str != null)
			getURLXYDataColumns(str);
		str = getParameter("dataset0xURL");
		if (str != null)
			getURLDatasets();
		str = getParameter("dataset0yURL");
		if (str != null)
			getURLDatasets();
		str = getParameter("dataset0xyURL");
		if (str != null)
			getURLDatasets();
		str = getParameter("dataset0xValues");
		if (str != null)
			getDatasets();
		str = getParameter("dataset0yValues");
		if (str != null)
			getDatasets();
		str = getParameter("dataset0xyValues");
		if (str != null)
			getDatasets();
		str = getParameter("customDatasetHandler");
		if (str != null)
			getter.getMyDatasets(str);
		str = getParameter("legendOn");
		if (str != null)
			chart.setLegendVisible(true);
		str = getParameter("legendOff");
		if (str != null)
			chart.setLegendVisible(false);
		str = getParameter("legendColor");
		if (str != null) {
			chart.getLegend().getBackgroundGc().setFillColor(getColor(str));
			chart.getLegend().getBackgroundGc().setLineColor(getColor(str));
		}
		str = getParameter("legendVertical");
		if (str != null)
			chart.getLegend().setVerticalLayout(true);
		str = getParameter("legendHorizontal");
		if (str != null)
			chart.getLegend().setVerticalLayout(false);
		str = getParameter("legendLabelColor");
		if (str != null)
			chart.getLegend().setLabelColor(getColor(str));
		str = getParameter("legendLabelFont");
		if (str != null)
			chart.getLegend().setLabelFont(getFont(str));
		str = getParameter("legendllX");
		if (str != null)
			chart.getLegend().setLlX((Double.valueOf(str)).doubleValue());
		str = getParameter("legendllY");
		if (str != null)
			chart.getLegend().setLlY((Double.valueOf(str)).doubleValue());
		str = getParameter("iconWidth");
		if (str != null)
			chart.getLegend().setIconWidth(
				Double.valueOf((str.trim())).doubleValue());
		str = getParameter("iconHeight");
		if (str != null)
			chart.getLegend().setIconHeight(
				Double.valueOf((str.trim())).doubleValue());
		str = getParameter("iconGap");
		if (str != null)
			chart.getLegend().setIconGap(
				Double.valueOf((str.trim())).doubleValue());

		str = getParameter("plotAreaTop");
		if (str != null)
			chart.getPlotarea().setUrY((Double.valueOf(str)).doubleValue());
		str = getParameter("plotAreaBottom");
		if (str != null)
			chart.getPlotarea().setLlY((Double.valueOf(str)).doubleValue());
		str = getParameter("plotAreaLeft");
		if (str != null)
			chart.getPlotarea().setLlX((Double.valueOf(str)).doubleValue());
		str = getParameter("plotAreaRight");
		if (str != null)
			chart.getPlotarea().setUrX((Double.valueOf(str)).doubleValue());
		str = getParameter("plotAreaColor");
		if (str != null)
			chart.getPlotarea().getGc().setFillColor(getColor(str));
		str = getParameter("backgroundImage");
		if (str != null)
			chart.getBackground().getGc().setImage(makeURLImage(str));
		str = getParameter("backgroundColor");
		if (str != null)
			chart.getBackground().getGc().setFillColor(getColor(str));
		str = getParameter("titleColor");
		if (str != null)
			chart.getBackground().setTitleColor(getColor(str));
		str = getParameter("titleFont");
		if (str != null)
			chart.getBackground().setTitleFont(getFont(str));
		str = getParameter("titleString");
		if (str != null)
			chart.getBackground().setTitleString(str);
		str = getParameter("subTitleColor");
		if (str != null)
			chart.getBackground().setSubTitleColor(getColor(str));
		str = getParameter("subTitleFont");
		if (str != null)
			chart.getBackground().setSubTitleFont(getFont(str));
		str = getParameter("subTitleString");
		if (str != null)
			chart.getBackground().setSubTitleString(str);
		str = getParameter("3D");
		if (str != null)
			chart.setThreeD(true);
		str = getParameter("2D");
		if (str != null)
			chart.setThreeD(false);
		str = getParameter("XDepth");
		if (str != null)
			chart.setXOffset(Integer.parseInt(str));
		str = getParameter("YDepth");
		if (str != null)
			chart.setYOffset(Integer.parseInt(str));
		str = getParameter("labelsOn");
		if (str != null)
			chart.getDataRepresentation().setLabelsOn(true);
		str = getParameter("labelAngle");
		if (str != null)
			chart.getDataRepresentation().setLabelAngle(Integer.parseInt(str));
		str = getParameter("labelFormat");
		if (str != null)
			chart.getDataRepresentation().setLabelFormat(Integer.parseInt(str));
		str = getParameter("labelPrecision");
		if (str != null)
			chart.getDataRepresentation().setLabelPrecision(
				Integer.parseInt(str));
		getAxisOptions();

		//add dummy data if nothing's been defined yet
		if (chart.getNumDatasets() < 1) {
			chart.addDataset("dummy", yArr);
			chart.getBackground().setTitleString("Sample Data");
			chart.getBackground().setSubTitleString("(no data available)");
		}
	}
	/**
	 * Substitute for getParameter in Applet or Servlet.
	 * @return java.lang.String
	 * @param s java.lang.String
	 */
	public String getParameter(String s) {
		return getter.getParameter(s);
	}
	protected boolean getURLDataBlock(String urlStr) {
		double xArr[] = null;
		double yArr[] = null;
		String str;
		int i = 0;
		InputStream myInputStream;

		if (urlStr != null) {
			if ((myInputStream = openURL(urlStr)) == null)
				return false;
		} else
			return false;

		gotDatasets = true;
		while ((str = getLineFromURL(myInputStream)) != null) {
			yArr = getVals(str);
			getDatasetParameters(i, xArr, yArr, null, null);
			i++;
		}
		closeURL(myInputStream);
		return true;
	}
	protected boolean getURLDataset(int which) {
		double xArr[] = null;
		double yArr[] = null;
		double y2Arr[] = null;
		String str;
		String urlStr;
		int j;
		InputStream myInputStream;

		/**** get data for dataset "which" *******/
		str = getParameter("dataset" + which + "xURL");
		if (str != null) {
			if ((myInputStream = openURL(str)) != null) {
				urlStr = getLineFromURL(myInputStream);
				xArr = getVals(urlStr);
				closeURL(myInputStream);
			}
		}

		str = getParameter("dataset" + which + "yURL");
		if (str != null) {
			if (str.equalsIgnoreCase("fake")) {
				//fake some data between 1 * 10
				yArr = new double[5];
				for (j = 0; j < 5; j++)
					yArr[j] = 10.0 * Math.random();
			} else if ((myInputStream = openURL(str)) != null) {
				urlStr = getLineFromURL(myInputStream);
				yArr = getVals(urlStr);
				closeURL(myInputStream);
			}
		}

		str = getParameter("dataset" + which + "y2URL");
		if (str != null) {
			if (str.equalsIgnoreCase("fake")) {
				//fake some data between 1 * 10
				yArr = new double[5];
				for (j = 0; j < 5; j++)
					y2Arr[j] = 10.0 * Math.random();
			} else if ((myInputStream = openURL(str)) != null) {
				urlStr = getLineFromURL(myInputStream);
				y2Arr = getVals(urlStr);
				closeURL(myInputStream);
			}
		}

		str = getParameter("dataset" + which + "xyURL");
		if (str != null) {
			System.out.println("xyVals not supported yet");
		}

		return (getDatasetParameters(which, xArr, yArr, y2Arr, null));
	}
	protected void getURLDatasets() {
		int i = 0;
		if (gotDatasets)
			return;
		gotDatasets = true;
		while (getURLDataset(i))
			i++;
	}
	protected boolean getURLXYDataColumns(String urlStr) {
		double xArr[] = null;
		double yArr[] = null;
		String myStr;
		int i, j;
		int nrows, ncolumns;
		double dataBlock[][];
		InputStream myInputStream;

		if (urlStr != null) {
			if ((myInputStream = openURL(urlStr)) == null)
				return false;
		} else
			return false;

		gotDatasets = true;
		myStr = getLineFromURL(myInputStream); //number of rows
		nrows = Integer.parseInt(myStr.trim());
		dataBlock = new double[nrows][];
		xArr = new double[nrows];
		yArr = new double[nrows];
		for (i = 0; i < nrows; i++) {
			myStr = getLineFromURL(myInputStream);
			dataBlock[i] = getVals(myStr);
		}
		closeURL(myInputStream);
		ncolumns = dataBlock[0].length; //assumes rectangular array

		for (i = 0; i < ncolumns; i += 2) {
			for (j = 0; j < nrows; j++) {
				try {
					xArr[j] = dataBlock[j][i];
					yArr[j] = dataBlock[j][i + 1];
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(
						"need same number of x & y observations in column "
							+ i
							+ " and row "
							+ j);
					return false;
				}
			}
			getDatasetParameters(i / 2, xArr, yArr, null, null);
		}
		return true;
	}
	protected boolean getURLXYDataRows(String urlStr) {
		double xyArr[] = null;
		double xArr[] = null;
		double yArr[] = null;
		String str;
		int i = 0;
		int j, k;
		InputStream myInputStream;

		if (urlStr != null) {
			myInputStream = openURL(urlStr);
			if (myInputStream == null)
				return false;
		} else
			return false;

		gotDatasets = true;
		while ((str = getLineFromURL(myInputStream)) != null) {
			xyArr = getVals(str);
			xArr = new double[xyArr.length / 2];
			yArr = new double[xArr.length];
			k = 0;
			for (j = 0; j < xyArr.length; j++) {
				try {
					xArr[k] = xyArr[j];
					j++;
					yArr[k] = xyArr[j];
					k++;
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(
						"need same number of x & y observations in dataset "
							+ i);
					return false;
				}
			}
			getDatasetParameters(i, xArr, yArr, null, null);
			i++;
		}
		closeURL(myInputStream);
		return true;
	}
	public double[] getVals(String s) {

		StringTokenizer st = new StringTokenizer(s, delimiter);
		double vals[] = new double[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			try {
				vals[i] = Double.valueOf((st.nextToken()).trim()).doubleValue();
			} catch (Exception e) {
				vals[i] = Double.NEGATIVE_INFINITY;
			}
			i++;
		}
		return vals;
	}
	protected void getXAxisLabels(String s) {
		String axisLabels[];
		int i, j;
		int start, end;

		i = 0;
		j = 0;
		while (i != -1) {
			i = s.indexOf(delimiter, i + 1);
			j++;
		}
		axisLabels = new String[j];

		start = 0;
		for (i = 0; i < j - 1; i++) {
			end = s.indexOf(delimiter, start);
			axisLabels[i] = s.substring(start, end);
			start = end + 1;
		}
		axisLabels[i] = s.substring(start);
		chart.getXAxis().addLabels(axisLabels);
	}
	public Image makeURLImage(String s) {
		return getter.makeURLImage(s);
	}
	//URL convenience methods
	public InputStream openURL(String s) {
		return getter.openURL(s);
	}
	public void parseAxOptions(String s, AxisInterface ax) {
		String str;

		str = getParameter(s + "Options");
		if (str != null)
			getAxisOptions(ax, str);
		str = getParameter(s + "Start");
		if (str != null)
			ax.setAxisStart((Double.valueOf(str)).doubleValue());
		str = getParameter(s + "End");
		if (str != null)
			ax.setAxisEnd((Double.valueOf(str)).doubleValue());
		str = getParameter(s + "LabelFont");
		if (str != null)
			ax.setLabelFont(getFont(str));
		str = getParameter(s + "LabelAngle");
		if (str != null)
			ax.setLabelAngle(Integer.parseInt(str));
		str = getParameter(s + "LabelPrecision");
		if (str != null)
			ax.setLabelPrecision(Integer.parseInt(str));
		str = getParameter(s + "LabelFormat");
		if (str != null)
			ax.setLabelFormat(Integer.parseInt(str));
		str = getParameter(s + "Color");
		if (str != null) {
			ax.setLabelColor(getColor(str));
			ax.getLineGc().setLineColor(getColor(str));
			ax.getGridGc().setLineColor(getColor(str));
			ax.getTickGc().setLineColor(getColor(str));
		}
		str = getParameter(s + "LabelColor");
		if (str != null)
			ax.setLabelColor(getColor(str));
		str = getParameter(s + "LineColor");
		if (str != null)
			ax.getLineGc().setLineColor(getColor(str));
		str = getParameter(s + "GridColor");
		if (str != null)
			ax.getGridGc().setLineColor(getColor(str));
		str = getParameter(s + "TickColor");
		if (str != null)
			ax.getTickGc().setLineColor(getColor(str));
		str = getParameter(s + "TickLength");
		if (str != null)
			ax.setMajTickLength(Integer.parseInt(str));
		str = getParameter(s + "MinTickLength");
		if (str != null)
			ax.setMinTickLength(Integer.parseInt(str));
		str = getParameter(s + "TickCount");
		if (str != null)
			ax.setNumMajTicks(Integer.parseInt(str));
		str = getParameter(s + "MinTickCount");
		if (str != null)
			ax.setNumMinTicks(Integer.parseInt(str));
		str = getParameter(s + "GridCount");
		if (str != null)
			ax.setNumGrids(Integer.parseInt(str));
		str = getParameter(s + "LabelCount");
		if (str != null)
			ax.setNumLabels(Integer.parseInt(str));
		str = getParameter(s + "Title");
		if (str != null)
			ax.setTitleString(str);
		str = getParameter(s + "TitleColor");
		if (str != null)
			ax.setTitleColor(getColor(str));
		str = getParameter(s + "TitleFont");
		if (str != null)
			ax.setTitleFont(getFont(str));
	}
	public void replaceDataLabels(int whichSet, String[] labels) {
		int i;
		Vector myData;
		Datum myDatum;

		myData = chart.getDatasets()[whichSet].getData();
		for (i = 0; i < myData.size(); i++) {
			myDatum = (Datum) (myData.elementAt(i));
			try {
				myDatum.setLabel(labels[i]);
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
	}
	public void reReadURLDatasets() {
		int i, j;
		String str, urlStr;
		double xArr[];
		double yArr[];
		String labels[];
		InputStream myInputStream;

		str = getParameter("customDatasetHandler");
		if (str != null) {
			getter.getMyDatasets(str);
		}

		str = getParameter("URLDataBlock");
		if (str != null) {
			if ((myInputStream = openURL(str)) != null) {
				for (i = 0; i < chart.getNumDatasets(); i++) {
					urlStr = getLineFromURL(myInputStream);
					yArr = getVals(urlStr);
					chart.getDatasets()[i].replaceYData(yArr);
				}
			}
			closeURL(myInputStream);
		}

		str = getParameter("URLXYDataRows");
		if (str != null) {
			int n, k;
			double xyArr[];

			if ((myInputStream = openURL(str)) != null) {
				for (i = 0; i < chart.getNumDatasets(); i++) {
					urlStr = getLineFromURL(myInputStream);
					xyArr = getVals(urlStr);
					xArr = new double[xyArr.length / 2];
					yArr = new double[xArr.length];
					k = 0;
					for (n = 0; n < xyArr.length; n++) {
						try {
							xArr[k] = xyArr[n];
							n++;
							yArr[k] = xyArr[n];
							k++;
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println(
								"need same number of x & y observations in dataset "
									+ i);
							return;
						}
					}
					chart.getDatasets()[i].replaceYData(yArr);
					chart.getDatasets()[i].replaceXData(xArr);
				}
			}
			closeURL(myInputStream);
		}

		str = getParameter("URLXYDataColumns");
		if (str != null) {
			int nrows, ncolumns;
			double dataBlock[][];

			if ((myInputStream = openURL(str)) != null) {
				urlStr = getLineFromURL(myInputStream); //number of rows
				nrows = Integer.parseInt(urlStr.trim());
				ncolumns = chart.getNumDatasets() * 2;
				//already calc'd # datasets
				dataBlock = new double[nrows][];
				xArr = new double[nrows];
				yArr = new double[nrows];
				for (i = 0; i < nrows; i++) {
					urlStr = getLineFromURL(myInputStream);
					dataBlock[i] = getVals(urlStr);
				}
			} else
				return;
			closeURL(myInputStream);

			for (i = 0; i < ncolumns; i += 2) {
				for (j = 0; j < nrows; j++) {
					try {
						xArr[j] = dataBlock[j][i];
						yArr[j] = dataBlock[j][i + 1];
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println(
							"need same number of x & y observations in column "
								+ i
								+ " and row "
								+ j);
						return;
					}
				}
				chart.getDatasets()[i / 2].replaceYData(yArr);
				chart.getDatasets()[i / 2].replaceXData(xArr);
			}
		} //end of URLXYDataColumns block

		for (i = 0; i < chart.getNumDatasets(); i++) {
			str = getParameter("dataset" + i + "xURL");
			if (str != null) {
				if ((myInputStream = openURL(str)) != null) {
					urlStr = getLineFromURL(myInputStream);
					xArr = getVals(urlStr);
					chart.getDatasets()[i].replaceXData(xArr);
				}
				closeURL(myInputStream);
			}

			str = getParameter("dataset" + i + "yURL");
			if (str != null) {
				//fake some data between 1 * 10
				if (str.equalsIgnoreCase("fake")) {
					yArr = new double[5];
					for (j = 0; j < 5; j++)
						yArr[j] = 10.0 * Math.random();
					chart.getDatasets()[i].replaceYData(yArr);
				} else if ((myInputStream = openURL(str)) != null) {
					urlStr = getLineFromURL(myInputStream);
					yArr = getVals(urlStr);
					chart.getDatasets()[i].replaceYData(yArr);
					closeURL(myInputStream);
				}
			}

			/*** check for URL labels ****/
			str = getParameter("dataset" + i + "URLLabels");
			if (str != null) {
				if ((myInputStream = openURL(str)) != null) {
					urlStr = getLineFromURL(myInputStream);
					labels = getLabels(urlStr);
					replaceDataLabels(i, labels);
					closeURL(myInputStream);
				}
			}
		}
	}
	/**
	 * Changes the default Locale for the applet or servlet using this parser.
	 * @param s java.lang.String
	 */
	public static void setLocale(String s) {
		if (s == null)
			return;
		if (s.equalsIgnoreCase("canada"))
			Locale.setDefault(Locale.CANADA);
		else if (s.equalsIgnoreCase("canada_french"))
			Locale.setDefault(Locale.CANADA_FRENCH);
		else if (s.equalsIgnoreCase("china"))
			Locale.setDefault(Locale.CHINA);
		else if (s.equalsIgnoreCase("chinese"))
			Locale.setDefault(Locale.CHINESE);
		else if (s.equalsIgnoreCase("english"))
			Locale.setDefault(Locale.ENGLISH);
		else if (s.equalsIgnoreCase("france"))
			Locale.setDefault(Locale.FRANCE);
		else if (s.equalsIgnoreCase("french"))
			Locale.setDefault(Locale.FRENCH);
		else if (s.equalsIgnoreCase("german"))
			Locale.setDefault(Locale.GERMAN);
		else if (s.equalsIgnoreCase("germany"))
			Locale.setDefault(Locale.GERMANY);
		else if (s.equalsIgnoreCase("italian"))
			Locale.setDefault(Locale.ITALIAN);
		else if (s.equalsIgnoreCase("italy"))
			Locale.setDefault(Locale.ITALY);
		else if (s.equalsIgnoreCase("japan"))
			Locale.setDefault(Locale.JAPAN);
		else if (s.equalsIgnoreCase("japanese"))
			Locale.setDefault(Locale.JAPANESE);
		else if (s.equalsIgnoreCase("korea"))
			Locale.setDefault(Locale.KOREA);
		else if (s.equalsIgnoreCase("korean"))
			Locale.setDefault(Locale.KOREAN);
		else if (s.equalsIgnoreCase("PRC"))
			Locale.setDefault(Locale.PRC);
		else if (s.equalsIgnoreCase("simplified_chinese"))
			Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
		else if (s.equalsIgnoreCase("taiwan"))
			Locale.setDefault(Locale.TAIWAN);
		else if (s.equalsIgnoreCase("traditional_chinese"))
			Locale.setDefault(Locale.TRADITIONAL_CHINESE);
		else if (s.equalsIgnoreCase("UK"))
			Locale.setDefault(Locale.UK);
		else if (s.equalsIgnoreCase("US"))
			Locale.setDefault(Locale.US);
	}
}