/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Point;
/**
 * Graphical Kiviat Chart
 *	A chart that looks like a radar.  This chart plots
 *	a single value (the first value added using the addDataset method)
 *	but draws a scale based on all Dataset information.
 * @author Laurent Pouchan
 * @version 1.0
 */

public class KiviatChart extends Chart {

	//Chart package classes
	Kiviat kiviat;
	KiviatAxis axis;
	boolean lineVisible = true;

	/**
	*	constructs a default chart 
	*/

	public KiviatChart() {
		super();
	}
	/**
	*	constructs a default chart 
	*	with the specified name
	*@param	s	chart name
	*/

	public KiviatChart(String s) {
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
		kiviat.draw(g);
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
	*	Returns this Chart's Kiviat element
	*/
	public Kiviat getKiviat() {
		return kiviat;
	}
	//only has one axis - want to get labels for it in applet
	/**
	*	Returns this Chart's KiviatAxis (same as getYAxis())
	*/
	public AxisInterface getXAxis() {
		return axis;
	}
	/**
	*	Returns this Chart's KiviatAxis
	*/
	public AxisInterface getYAxis() {
		return axis;
	}
	protected void initAxes() {
		axis = new KiviatAxis(datasets, false, plotarea);
		yAxis = axis;
	}
	//utility methods
	protected void initChart() {
		initGlobals();
		setPlotarea(new Plotarea());
		setBackground(new Background());
		initDatasets();

		initAxes();
		kiviat = new Kiviat();
		setDataRepresentation(kiviat);
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
	*	Sets the LineVisible property.  If this property is true, a Kiviat line will be 
	*	drawn.  If not, markers alone will be drawn (if defined).
	*/

	public void setLineVisible(boolean onOff) {
		lineVisible = onOff;
		kiviat.setScatterPlot(!onOff);
	}
}