/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.*;
import java.io.Serializable;

/**
 *	A Polar element draws a line and/or markers to represent each Y value in
 *	an array of Dataset classes.  These markers or vertices are distributed 
 *	about the center of a Plotarea class, with the Y values determining how
 *	far from the center each point will appear.  Together with a PolarAxis,
 *	this class creates a PolarChart that can be used to compare a variety of
 *	data in an intuitive way.
 */

public class Polar extends DataRepresentation implements Serializable {

	//utility vars
	protected PolarAxis axis;
	protected Plotarea plotarea;
	protected Transform dataXfm;
	boolean scatterPlot = false;
	/**
	 * Creates a Polar component without assigning Globals, Plotarea, or Datasets.
	 */
	public Polar() {
	}
	/**
	 *	Constructs a Polar chart from the specified array of Dataset classes,
	 *	PolarAxis and Plotarea.  
	 *
	 * @param	d	Array of Datasets to consider
	 * @param	ax	PolarAxis scaling this data
	 * @param	subplot	Plotarea containing this gauge
	 * @see		javachart.chart.PolarAxis
	 */
	public Polar(Dataset d[], PolarAxis ax, Plotarea subplot) {

		datasets = d;
		axis = ax;
		plotarea = subplot;
		globals = plotarea.globals;
	}
	/**
	 *	Used by the doLabels method to draw a single data label
	 */
	protected void doElementLabel(
		Graphics g,
		Point loc,
		int whichSet,
		int whichElement) {

		String str;
		Datum element = datasets[whichSet].getDataElementAt(whichElement);

		//has labels defined
		if (element.label != null)
			str = element.label;
		//doesn't have labels defined
		else {
			str = formatLabel(element.y);
		}

		FontMetrics fm = g.getFontMetrics();
		datasets[whichSet].gc.drawSmartString(
			g,
			loc.x,
			loc.y + 2,
			Gc.keepABOVE,
			labelAngle,
			fm,
			str);
	}
	protected void doLabels(Graphics g, int whichSet, Point[] location) {
		g.setFont(datasets[whichSet].labelFont);
		g.setColor(datasets[whichSet].labelColor);
		for (int i = 0; i < location.length; i++) {
			doElementLabel(g, location[i], whichSet, i);
		}
	}
	protected void doMarkers(Graphics g, int whichSet, Point[] location) {

		int i;
		Point ll, ur;

		if (datasets[whichSet].gc.image == null)
			return;

		for (i = 0; i < location.length; i++) {
			datasets[whichSet].gc.drawImage(g, location[i]);
			if (useDisplayList && globals.useDisplayList) {
				ur = location[i];
				int width = datasets[whichSet].gc.image.getWidth(null);
				int height = datasets[whichSet].gc.image.getHeight(null);
				ur.translate(width / 2, height / 2);
				ll = new Point(ur.x - width, ur.y - height);
				globals.displayList.addRectangle(
					datasets[whichSet].getDataElementAt(i),
					ll,
					ur);
				globals.displayList.addRectangle(datasets[whichSet], ll, ur);
				globals.displayList.addRectangle(this, ll, ur);
			}
		}
	}
	/**
	 *	Draws a radar style display on the specified Graphics.
	 * @param	g	Graphics to draw
	 */
	protected void doPolar(Graphics g, boolean markersOnly) {
		int i, j, length;
		double arr[];
		Point[] xfmData;
		Point[] rotatedData;

		if (axis == null) {
			if (yAxis instanceof PolarAxis)
				axis = (PolarAxis) yAxis;
			else
				return;
		}
		length = axis.datasetsInUse();

		dataXfm =
			new Transform(
				0.0,
				axis.axisStart,
				0.1,
				axis.axisEnd,
				axis.qtrPlotarea.transform.point(
					axis.qtrPlotarea.llX,
					axis.qtrPlotarea.llY),
				axis.qtrPlotarea.transform.point(
					axis.qtrPlotarea.urX,
					axis.qtrPlotarea.urY));
		dataXfm.logYScaling = axis.getLogScaling();

		for (i = 0; i < length; i++) {
			arr = datasets[i].getYValues();

			xfmData = new Point[arr.length];
			for (j = 0; j < arr.length; j++) {
				xfmData[j] = dataXfm.point(0.0, arr[j]);
			}

			rotatedData = rotateData(xfmData, datasets[i]);

			if (markersOnly)
				doMarkers(g, i, rotatedData);
			else {
				datasets[i].gc.drawPolyline(g, rotatedData);
				datasets[i].gc.drawLine(
					g,
					rotatedData[arr.length - 1],
					rotatedData[0]);
				doMarkers(g, i, rotatedData);
			}
			if (labelsOn)
				doLabels(g, i, rotatedData);
		}
	}
	/**
	 *	Draws this as currently defined on the specified Graphics
	 *
	 * @param	g	Graphics class to draw
	 */
	public synchronized void draw(Graphics g) {
		if (g == null) {
			return;
		}
		doPolar(g, scatterPlot);
	}
	/**
	 *	Returns the ScatterPlot property.
	 */
	public boolean isScatterPlot() {
		return scatterPlot;
	}
	protected Point[] rotateData(Point[] arr, Dataset d) {
		double cosine, sine, oldx, oldy, newx, newy, angle;
		int i, length;
		Point[] data;

		//angle = axis.getInterval();
		length = arr.length;
		angle = 360 / length;
		data = new Point[length];

		for (i = 0; i < length; i++) {
			oldx = (double) (arr[i].x - axis.center.x);
			oldy = (double) (arr[i].y - axis.center.y);

			cosine = Math.cos(Math.PI * (double) i * angle / 180.0);
			sine = Math.sin(Math.PI * (double) i * angle / 180.0);

			newx = (oldx * cosine - oldy * sine) + axis.center.x;
			newy = (oldx * sine + oldy * cosine) + axis.center.y;

			data[i] = new Point((int) newx, (int) newy);
		}
		return data;
	}
	/**
	 *	Sets the ScatterPlot property.  If ScatterPlot is true, this Polar will use
	 *	markers only, and not show lines.
	 */
	public void setScatterPlot(boolean b) {
		scatterPlot = b;
	}
}