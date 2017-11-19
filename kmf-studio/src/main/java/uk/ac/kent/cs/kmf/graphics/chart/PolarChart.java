/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Point;

/**
 *	A chart that looks like a radar.  This chart plots
 *	a single value (the first value added using the addDataset method)
 *	but draws a scale based on all Dataset information.
 */

public class PolarChart extends Chart {

	//Chart package classes
	Polar polar;
	PolarAxis axis;
	boolean lineVisible = true;

	/**
	 *	constructs a default chart 
	 */

	public PolarChart() {
		super();
	}
	/**
	 *	constructs a default chart 
	 *	with the specified name
	 *@param	s	chart name
	 */

	public PolarChart(String s) {
		super(s);
	}
	//draw routines
	public synchronized void drawGraph() {
		if (canvas == null)
			return;
		drawGraph(canvas);
	}
	public synchronized void drawGraph(Graphics g) {
		if (g == null)
			return;
		super.drawGraph(g);
		background.draw(g);
		plotarea.draw(g);
		axis.draw(g);
		polar.draw(g);
		if (legendVisible)
			legend.draw(g);
	}
	/**
	 *	Gets the LineVisible property.
	 */
	public boolean getLineVisible() {
		return lineVisible;
	}
	//accessors
	/**
	 *	Returns this Chart's Polar element
	 */
	public Polar getPolar() {
		return polar;
	}
	//only has one axis - want to get labels for it in applet
	/**
	 *	Returns this Chart's PolarAxis (same as getYAxis())
	 */
	public AxisInterface getXAxis() {
		return axis;
	}
	/**
	 *	Returns this Chart's PolarAxis
	 */
	public AxisInterface getYAxis() {
		return axis;
	}
	protected void initAxes() {
		axis = new PolarAxis(datasets, false, plotarea);
		yAxis = axis;
	}
	//utility methods
	protected void initChart() {
		initGlobals();
		setPlotarea(new Plotarea());
		setBackground(new Background());
		initDatasets();
		initAxes();
		polar = new Polar();
		setDataRepresentation(polar);
		setLegend(new LineLegend());
		resize(640, 480);
	}
	/**
	 *	Resizes this Chart
	 */
	public void resize(int newWidth, int newHeight) {
		super.resize(newWidth, newHeight);
		axis.center = new Point((int) (newWidth / 2), (int) (newHeight / 2));
	}
	/**
	 *	Sets the LineVisible property.  If this property is true, a Polar line will be 
	 *	drawn.  If not, markers alone will be drawn (if defined).
	 */
	public void setLineVisible(boolean onOff) {
		lineVisible = onOff;
		polar.setScatterPlot(!onOff);
	}
}