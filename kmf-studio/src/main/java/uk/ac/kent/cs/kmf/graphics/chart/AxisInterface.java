/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

/**
 *	Defines a standard set of methods for manipulating axis sytems.  
 *	Every chart that contains an Axis uses an AxisInterface method 
 *	to change values and attributes for specific Axis implementations.  
 *	AxisInterface also provides an abstract means for dealing with 
 *	Chart axes within user interface code.
 *@see	javachart.chart.Axis
 *@see	javachart.chart.LabelAxis
 *@see	javachart.chart.DateAxis
 *@see	javachart.chart.StackAxis
 *@see	javachart.chart.SpeedoAxis
 *@see	javachart.chart.HiLoAxis
 */

public interface AxisInterface {

	public void addLabels(String str[]);
	public void draw(Graphics g);
	public boolean getAutoScale();
	public double getAxisEnd();
	public double getAxisStart();
	public boolean getBarScaling();
	public Dataset[] getDatasets();
	/**
	 * Returns this Axis' Globals instance.  All components of a chart use a common
	 * Globals class to maintain DisplayLists, chart size, etc.
	 * @return javachart.chart.Globals
	 */
	public Globals getGlobals();
	public Gc getGridGc();
	public boolean getGridVis();
	public int getLabelAngle();
	public Color getLabelColor();
	public Font getLabelFont();
	public java.text.Format getLabelFormat();
	public int getLabelPrecision();
	public boolean getLabelVis();
	public Gc getLineGc();
	public boolean getLineVis();
	public boolean getLogScaling();
	public int getMajTickLength();
	public boolean getMajTickVis();
	public int getMinTickLength();
	public boolean getMinTickVis();
	public int getNumGrids();
	public int getNumLabels();
	public int getNumMajTicks();
	public int getNumMinTicks();
	/**
	 * Returns this Axis' Plotarea.  Axes draw at the edge of a Plotarea object.
	 * @return javachart.chart.Plotarea
	 */
	public Plotarea getPlotarea();
	public int getSide();
	public Gc getTickGc();
	public Color getTitleColor();
	public Font getTitleFont();
	public String getTitleString();
	public boolean getUseDisplayList();
	public boolean isTitleRotated();
	/**
	 * Does this Axis examine X or Y values.
	 * @return boolean
	 */
	public boolean isXAxis();
	public void scale();
	public void setAutoScale(boolean yesNo);
	public void setAxisEnd(double num);
	public void setAxisStart(double num);
	public void setBarScaling(boolean yesNo);
	public void setDatasets(Dataset datasets[]);
	/**
	 * Installs a Globals class for this Axis.  Globals classes are shared by all
	 * objects in a Chart, and provide information about DisplayLists, size, etc.
	 * @param g javachart.chart.Globals
	 */
	public void setGlobals(Globals g);
	public void setGridGc(Gc g);
	public void setGridVis(boolean onOff);
	/**
	 * Determines whether this axis will scale against X or Y values.
	 * @param xAx boolean
	 */
	public void setIsXAxis(boolean xAx);
	public void setLabelAngle(int num);
	public void setLabelColor(Color c);
	public void setLabelFont(Font f);
	/**
	 *@deprecated. replaced by setLabelFormat(Format)
	 */
	public void setLabelFormat(int num);
	public void setLabelFormat(java.text.Format f);
	public void setLabelPrecision(int num);
	public void setLabelVis(boolean onOff);
	public void setLineGc(Gc g);
	public void setLineVis(boolean onOff);
	public void setLogScaling(boolean yesNo);
	public void setMajTickLength(int num);
	public void setMajTickVis(boolean onOff);
	public void setMinTickLength(int num);
	public void setMinTickVis(boolean onOff);
	public void setNumGrids(int num);
	public void setNumLabels(int num);
	public void setNumMajTicks(int num);
	public void setNumMinTicks(int num);
	/**
	 * Sets the Plotarea bounded by this Axis.  Axes always draw along the edge of
	 * a Plotarea object.  This sets the Plotarea object used by this Axis for
	 * sizing and location.
	 * @param p javachart.chart.Plotarea
	 */
	public void setPlotarea(Plotarea p);
	public void setSide(int num);
	public void setTickGc(Gc g);
	public void setTitleColor(Color c);
	public void setTitleFont(Font f);
	public void setTitleRotated(boolean onOff);
	public void setTitleString(String s);
	public void setUseDisplayList(boolean onOff);
}