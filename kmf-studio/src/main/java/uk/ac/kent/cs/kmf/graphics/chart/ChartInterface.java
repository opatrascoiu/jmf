/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 	A uniform interface for dealing with all sorts of charts.  
 *	Primarily intended for use with the standard set of KavaChart 
 *	predefined charts and applets.
 *@see	javachart.chart.Chart
 */

public interface ChartInterface {
	public void addDataset(String s, double y[]);
	public void addDataset(String s, double x[], double y[]);
	public void addDataset(
		String s,
		double x[],
		double y[],
		double y2[],
		double y3[]);
	public void addDataset(String s, double x[], double y[], String labels[]);
	public void addDataset(String s, double y[], String labels[]);
	public void addDataset(Dataset dataset);
	public void deleteDataset(String s);
	public void deleteDataset(Dataset dataset);
	public void drawGraph();
	public void drawGraph(Graphics g);
	public Background getBackground();
	public Graphics getCanvas();
	/**
	 * Returns a DataRepresentation (Bar, Line, Area, Pie, etc.)
	 * @return javachart.chart.DataRepresentation
	 */
	DataRepresentation getDataRepresentation();
	public Dataset[] getDatasets();
	public DisplayList getDisplayList();
	public Globals getGlobals();
	public int getHeight();
	public Image getImage();
	public LegendInterface getLegend();
	public String getName();
	public int getNumDatasets();
	public Plotarea getPlotarea();
	public RotateString getStringRotator();
	public boolean getUseDisplayList();
	public int getWidth();
	public AxisInterface getXAxis();
	public int getXOffset();
	public AxisInterface getYAxis();
	public int getYOffset();
	public boolean isLegendVisible();
	public boolean isThreeD();
	public boolean isXAxisVisible();
	public boolean isYAxisVisible();
	/**
	 * Convenience method for automatic double buffering and string rotation.
	 * @param c java.awt.Component
	 * @param g java.awt.Graphics
	 */
	void paint(java.awt.Component c, Graphics g);
	/**
	 * Equivalent to drawGraph.
	 * @param g java.awt.Graphics
	 */
	void paint(Graphics g);
	public void resize(int newWidth, int newHeight);
	/**
	 * Installs a DataRepresentation (Bar, Area, Pie, etc.) into this Chart.
	 * @param d javachart.chart.DataRepresentation
	 */
	void setDataRepresentation(DataRepresentation d);
	public void setDisplayList(DisplayList d);
	public void setHeight(int h);
	public void setImage(Image i);
	public void setLegendVisible(boolean v);
	public void setName(String s);
	public void setStringRotator(RotateString i);
	public void setThreeD(boolean b);
	public void setUseDisplayList(boolean yesNo);
	public void setWidth(int w);
	public void setXAxis(AxisInterface a);
	public void setXAxisVisible(boolean vis);
	public void setXOffset(int i);
	public void setYAxis(AxisInterface a);
	public void setYAxisVisible(boolean vis);
	public void setYOffset(int i);
}