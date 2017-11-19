/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;
/**
 * A special form of axis used for creating Kiviat charts.  These charts
 *	distribute Y values around a KiviatAxis.  By default, the Axis creates
 *	one "spoke" per Y value.  For charts that have many values, you can
 *	override this to create a specified number of spokes.  This Axis also
 *	uses labels differently than a conventional Axis: each label will be drawn
 *	at the end of a spoke if defined.  The vertical axis labels are automatically
 *	determined by the specified linear or log scaling heuristic.
 */

public class KiviatAxis extends Axis implements AxisInterface {

	double centerX, centerY;
	double interval;
	Plotarea fullPlotarea, qtrPlotarea;
	private int numSpoke;
	KiviatCom c;

	private int upperAxisLength;

	public Point center;
	//shouldn't really be public, but works for some contrib items for now
	Transform dataXfm;
	Vector labelList;
	protected boolean manualSpoking = false;

	public KiviatAxis(Dataset dsets[], boolean xAxis, Plotarea plt) {

		super(dsets, false, plt);

	}
	/**
	*	Appends an array of spoke labels to this Axis
	*
	* @param	str	Array of string labels
	*/
	public void addLabels(String str[]) {
		replaceLabels(str);
	}
	private void doTitle(Graphics g, int spoke, Point center, Point edge) {
		String s;
		try {
			s = (String) labelList.elementAt(spoke % labelList.size());
		} catch (Exception e) {
			return;
		}
		int leftRight = edge.x - center.x;
		int topBottom = edge.y - center.y;
		int align;
		if (leftRight == 0) { //vertical
			if (topBottom < 0)
				align = Gc.keepBELOW;
			else
				align = Gc.keepABOVE;
		} else if (topBottom == 0) { //horizontal
			if (leftRight < 0)
				align = Gc.keepLEFT;
			else
				align = Gc.keepRIGHT;
		}
		if (topBottom > 0) {
			align = Gc.keepABOVE;
			edge.translate(0, 3);
		} else {
			align = Gc.keepBELOW;
			edge.translate(0, -3);
		}
		g.setFont(titleFont);
		g.setColor(titleColor);
		lineGc.drawSmartString(
			g,
			edge.x,
			edge.y,
			align,
			0,
			g.getFontMetrics(),
			s);
	}
	/**
	*	Draws this Axis on the specified Graphics class.
	*/
	public synchronized void draw(Graphics g) {
		Point[] originalAxis, rotatedAxis;
		int i, j, numPoints, start;

		double hCircleSize, vCircleSize;

		hCircleSize = plotarea.urX - plotarea.llX;
		vCircleSize = plotarea.urY - plotarea.llY;

		centerX = plotarea.llX + (hCircleSize / 2.0);
		centerY = plotarea.llY + (vCircleSize / 2.0);
		center = plotarea.transform.point(centerX, centerY);

		quarterPlotarea();
		super.draw(g);
		originalAxis = getPoints();
		numPoints = originalAxis.length;

		plotarea = fullPlotarea;
		if (!manualSpoking)
			setInterval();

		rotatedAxis = rotateAxis(originalAxis, (double) numSpoke * interval);
		upperAxisLength = rotatedAxis[1].y - rotatedAxis[0].y;

		drawKiviatCircle(g);

		for (i = 0; i <= numSpoke - 1; i++) {

			rotatedAxis = rotateAxis(originalAxis, (double) i * interval);

			lineGc.drawLine(
				g,
				rotatedAxis[0].x,
				rotatedAxis[0].y,
				rotatedAxis[1].x,
				rotatedAxis[1].y);

			for (j = 2; j < numPoints; j++) {

				tickGc.drawLine(
					g,
					rotatedAxis[j].x,
					rotatedAxis[j++].y,
					rotatedAxis[j].x,
					rotatedAxis[j].y);
			}

			if (labelList != null)
				doTitle(g, i, rotatedAxis[0], rotatedAxis[1]);

		}
	}

	/**
	*	Trace les deux cercles pour un diagramme de kiviat
	*    @author Laurent Pouchan
	*/

	private void drawKiviatCircle(Graphics g) {
		Color jaunePastel = new Color(255, 227, 173);
		Color orangePastel = new Color(255, 201, 178);
		int maxKiviat, minKiviat;
		double[] minmaxkiviat = new double[2];

		minmaxkiviat[0] = upperAxisLength / 3;
		minmaxkiviat[1] = upperAxisLength / 1.2;

		datasets[2].replaceYData(minmaxkiviat);

		lineGc.drawKiviatCircle(
			g,
			center.x,
			center.y,
			(int) minmaxkiviat[1],
			jaunePastel);

		lineGc.drawKiviatCircle(
			g,
			center.x,
			center.y,
			(int) minmaxkiviat[0],
			orangePastel);

	}
	/**
	*	Returns the number of degrees per spoke for this Axis.
	*/
	public double getInterval() {
		return interval;
	}
	/**
	*	Gets this Axis' ManualSpoking property.  If ManualSpoking is set to false,
	*	this Axis will have one spoke for each Datum (in the first Dataset)
	*/
	public boolean getManualSpoking() {
		return manualSpoking;
	}
	/**
	*	Gets the NumSpokes property for this Axis.
	*/
	public int getNumSpokes() {
		return numSpoke;
	}
	private Point[] getPoints() {
		int i, y, tickBase;
		Vector axis;
		Point start, end, array[];

		axis = new Vector();

		start = plotarea.transform.point(plotarea.llX, plotarea.llY);
		end = plotarea.transform.point(plotarea.llX, plotarea.urY);
		startPoint = start;

		axis.insertElementAt(start, 0);
		axis.insertElementAt(end, 1);

		tickBase = start.x;

		if (majTickVis) {
			increment = getIncrement(end.y, start.y, numMajTicks);
			for (i = 1; i < numMajTicks; i++) {
				y = whereOnAxis(i, majTicks);
				axis.addElement(new Point(tickBase, y));
				axis.addElement(new Point(tickBase - majTickLength, y));
			}
			axis.addElement(new Point(tickBase, end.y));
			axis.addElement(new Point(tickBase - majTickLength, end.y));
		}

		if (minTickVis) {
			increment = getIncrement(end.y, start.y, numMinTicks);
			for (i = 0; i < numMinTicks; i++) {
				y = whereOnAxis(i, minTicks);
				axis.addElement(new Point(tickBase, y));
				axis.addElement(new Point(tickBase - minTickLength, y));
			}
			axis.addElement(new Point(tickBase, end.y));
			axis.addElement(new Point(tickBase - minTickLength, end.y));
		}

		array = new Point[axis.size()];
		for (i = 0; i < axis.size(); i++) {
			array[i] = (Point) axis.elementAt(i);
		}

		return array;
	}
	protected void quarterPlotarea() {
		int width, height;

		fullPlotarea = plotarea;

		plotarea = new Plotarea(fullPlotarea.globals);
		plotarea.setLlX(centerX);
		plotarea.setLlY(centerY);
		plotarea.setUrX(fullPlotarea.getUrX());
		plotarea.setUrY(fullPlotarea.getUrY());
		plotarea.setGc(fullPlotarea.getGc());
		plotarea.setUseDisplayList(fullPlotarea.getUseDisplayList());
		plotarea.resize(globals.maxX, globals.maxY);

		qtrPlotarea = plotarea;
	}
	/**
	*	Replaces all spoke labels on this Axis
	*
	* @param	str	The array of new labels
	*/
	public synchronized void replaceLabels(String str[]) {
		int i;

		labelList = new Vector();
		//add strings to Vector, resize if necessary;
		for (i = 0; i < str.length; i++) {
			labelList.addElement((Object) str[i]);
		}

	}
	private Point[] rotateAxis(Point[] oldAxis, double angle) {
		double cosine, sine, oldx, oldy, newx, newy;
		int i;
		Point[] newAxis = new Point[oldAxis.length];

		cosine = Math.cos(Math.PI * angle / 180.0);
		sine = Math.sin(Math.PI * angle / 180.0);

		for (i = 0; i < oldAxis.length; i++) {
			oldx = ((double) oldAxis[i].x) - center.x;
			oldy = ((double) oldAxis[i].y) - center.y;

			newx = (oldx * cosine - oldy * sine) + center.x;
			newy = (oldx * sine + oldy * cosine) + center.y;

			newAxis[i] = new Point((int) newx, (int) newy);
		}
		return newAxis;
	}
	private void setInterval() {
		setNumSpokes(datasets);
		interval = (360.0000 / numSpoke);
	}
	/**
	*	Sets this Axis' ManualSpoking property.  If true, the number of spokes on this
	*	Axis is determined by the NumSpokes property.
	*/
	public void setManualSpoking(boolean manual) {
		manualSpoking = manual;
	}
	private void setNumSpokes(Dataset datasets[]) {
		int i, length, spoke = 0;

		length = 1;

		for (i = 0; i < length; i++) {
			if (spoke < datasets[i].data.size())
				spoke = datasets[i].data.size();
		}

		numSpoke = spoke;
	}

	// Method added by Laurent pouchan 

	/**
	*	Sets this Axis NumSpokes property.  If ManualSpoking is false, this determines
	*	how many "spokes" will appear on this Axis.  If ManualSpoking is true, this
	*	Axis will have one spoke for each Datum.
	*/
	public void setNumSpokes(int num) {
		numSpoke = num;
		interval = (360 / numSpoke);
	}
}