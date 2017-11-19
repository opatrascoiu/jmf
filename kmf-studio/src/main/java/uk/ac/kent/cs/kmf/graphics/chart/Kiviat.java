/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.*;
import java.io.Serializable;

/**
 *	A Kiviat element draws a line and/or markers to represent each Y value in
 *	an array of Dataset classes.  These markers or vertices are distributed 
 *	about the center of a Plotarea class, with the Y values determining how
 *	far from the center each point will appear.  Together with a KiviatAxis,
 *	this class creates a KiviatChart that can be used to compare a variety of
 *	data in an intuitive way.
 */

public class Kiviat extends DataRepresentation implements Serializable {

	//utility vars
	protected KiviatAxis axis;
	protected KiviatCom c;
	protected Plotarea plotarea;
	protected Transform dataXfm;
	boolean scatterPlot = false;
	boolean first = true;

	/**
	* Creates a Kiviat component without assigning Globals, Plotarea, or Datasets.
	*/
	public Kiviat() {
	}
	/**
	*	Constructs a Kiviat chart from the specified array of Dataset classes,
	*	KiviatAxis and Plotarea.  
	*
	* @param	d	Array of Datasets to consider
	* @param	ax	KiviatAxis scaling this data
	* @param	subplot	Plotarea containing this gauge
	* @see		javachart.chart.KiviatAxis
	*/
	public Kiviat(Dataset d[], KiviatAxis ax, Plotarea subplot) {

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

		Color Vert = new Color(58, 155, 1);
		//Dataset valueOkDataset;
		Datum d;
		int i;

		Point ul = new Point();
		Point lr = new Point();

		//valueOkDataset=(Chart)this.getDataset("valueOk");

		for (i = 0; i < location.length; i++) {
			ul.x = location[i].x + 3;
			ul.y = location[i].y + 3;
			lr.x = location[i].x - 3;
			lr.y = location[i].y - 3;

			d = datasets[3].getDataElementAt(i);

			switch ((int) d.getY()) {
				case 0 :
					datasets[whichSet].gc.setFillColor(Color.red);
					break;
				case 1 :
					datasets[whichSet].gc.setFillColor(Vert);
					break;
				case -1 :
					datasets[whichSet].gc.setFillColor(Color.blue);
					break;
			}

			datasets[whichSet].gc.fillRect(g, ul, lr);

		}
	}

	/**
	*	Draws a radar style display on the specified Graphics.
	* @param	g	Graphics to draw
	*/
	protected void doKiviat(Graphics g, boolean markersOnly) {
		int i, j, length;
		double arr[];
		Point[] xfmData;
		Point[] rotatedData;

		if (axis == null) {
			if (yAxis instanceof KiviatAxis)
				axis = (KiviatAxis) yAxis;
			else
				return;
		}
		//length = axis.datasetsInUse ();

		axis.setAxisEnd(datasets[2].maxY() * 1.2);
		axis.setAxisStart(0.0);

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
		//axis.scale();
		dataXfm.logYScaling = axis.getLogScaling();

		//for (i=0; i<length; i++) {
		//arr = datasets[i].getYValues ();
		arr = datasets[0].getYValues();

		xfmData = new Point[arr.length];
		for (j = 0; j < arr.length; j++) {

			xfmData[j] = dataXfm.point(0.0, arr[j]);

		}

		rotatedData = rotateData(xfmData, datasets[0]);

		if (markersOnly)
			doMarkers(g, 0, rotatedData);
		else {
			datasets[0].gc.drawPolyline(g, rotatedData);

			datasets[0].gc.drawLine(
				g,
				rotatedData[arr.length - 1],
				rotatedData[0]);

			doMarkers(g, 0, rotatedData);
		}
		if (labelsOn)
			doLabels(g, 0, rotatedData);
	}
	//}
	/**
	*	Draws this as currently defined on the specified Graphics
	*
	* @param	g	Graphics class to draw
	*/
	public synchronized void draw(Graphics g) {
		if (g == null) {
			return;
		}
		doKiviat(g, scatterPlot);
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

		angle = axis.getInterval();
		length = arr.length;
		//   angle = 360 / length;
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
	*	Sets the ScatterPlot property.  If ScatterPlot is true, this Kiviat will use
	*	markers only, and not show lines.
	*/

	public void setScatterPlot(boolean b) {
		scatterPlot = b;
	}

}