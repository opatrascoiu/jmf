/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;

/**
 *	The superclass for all KavaChart's standard axis classes, Axis 
 *	implements methods for drawing grids, labels and ticks that can 
 *	be easily re-used in Axis subclasses.  In particular, Axis uses 
 *	the method whereOnAxis() to determine where to draw labels, grids, 
 *	and ticks, and uses the method getLabel() to get a specific axis 
 *	label.  This means that you can easily subclass and modify Axis 
 *	behavior by changing getLabel and whereOnAxis.  When used in 
 *	concert with  the class variables numLabels, numTicks and so on, 
 *	these methods can even draw ticks, labels and grids at irregular 
 *	intervals, such as those defined by the DateAxis subclass.
 *@see	javachart.chart.Axis
 *@see	javachart.chart.LabelAxis
 *@see	javachart.chart.DateAxis
 *@see	javachart.chart.StackAxis
 *@see	javachart.chart.SpeedoAxis
 *@see	javachart.chart.HiLoAxis
 */

/*
 * 6/16/98 DRM added label rotation stuff - 11/98 updated title rotation
 */
public class Axis implements AxisInterface, Serializable {

	//package vars
	boolean autoScale = true;
	protected double axisStart = 0.0;
	protected double axisEnd = 10.0;

	boolean majTickVis = true;
	boolean minTickVis = false;
	boolean gridVis = false;
	boolean labelVis = true;
	boolean lineVis = true;
	boolean titleRotation = false;

	String titleString = null;
	Color titleColor;
	Font titleFont = Gc.defaultFont;

	boolean useDisplayList = true;

	Color labelColor;
	Font labelFont = Gc.defaultFont;
	int labelPrecision = 2;
	int labelAngle = 0;
	int labelFormat = Gc.DEFAULT_FORMAT;

	Gc lineGc;
	Gc gridGc;
	Gc tickGc;

	protected int numMajTicks = 5;
	protected int numGrids = 5;
	protected int numMinTicks = 10;
	protected int numLabels = 5;

	int majTickLength = 5;
	int minTickLength = 2;
	int side = 1;

	boolean logScaling = false;
	Format userFormat = null;
	NumberFormat numberFormat = NumberFormat.getInstance();
	/**
	 *	This chart's Globals class
	 */
	protected Globals globals;

	//internals
	boolean isXAxis = false;
	boolean barScaling = false;

	/**
	 *	number of labels on this Axis
	 */
	protected int numAxLabels = 0;
	/**
	 *	the dataset array scaled by this Axis
	 */
	protected Dataset datasets[];
	/**
	 *	general internal increment for this Axis
	 */
	protected double stepSize = 2.0;
	/**
	 *	Plotarea used to determine location and size of Axis
	 */
	protected Plotarea plotarea;

	/**
	 *	Convenience variable to indicate the start or end should be auto-scaled
	 */
	public final static double AUTO_SCALE = Double.NEGATIVE_INFINITY;
	public final static int BOTTOM = 0;
	public final static int LEFT = 1;
	public final static int RIGHT = 3;
	public final static int TOP = 2;

	protected Point startPoint, endPoint;
	protected float increment;

	//which type of element are we working on (used for DateAxis and other irregular intervals)
	/**
	 *	Axis Labels
	 */
	protected final static int axisLabels = 1;
	/**
	 *	Grid Lines
	 */
	protected final static int gridLines = 2;
	/**
	 *	Minor Ticks
	 */
	protected final static int minTicks = 3;
	/**
	 *	Major Ticks
	 */
	protected final static int majTicks = 4;

	/**
	 *	User defined Axis minimum
	 */
	protected Double userAxisStart = null;

	/**
	 *	User defined Axis maximum
	 */
	protected Double userAxisEnd = null;

	//autoscaling stuff
	//
	//
	//
	final double NODIV = 5.0;
	private int axMagnitude;
	static double[] twoFiveSteps = { 0.0d, 3.d * 0.3010d, 3.d * 0.699d };
	static double[] oneTwoSteps =
		{
			0.0d,
			10.d * 0.3010d,
			10.d * 0.4771d,
			10.d * 0.6020d,
			10.d * 0.6989d,
			10.d * 0.7781d,
			10.d * 0.8451d,
			10.d * 0.9031d,
			10.d * 0.9542d,
			10.d };
	/**
	 * Constructs a new Axis without Dataset and Plotarea values.
	 */
	public Axis() {
		this(null, false, null);
	}
	/**
	 *	Constructs an Axis for the specified array of datasets
	 *@param	dsets	Array of Dataset classes to consider
	 *@param	xAxis	true if this is an X axis
	 *@param	plt	The Plotarea bounded by this Axis
	 */
	public Axis(Dataset dsets[], boolean xAxis, Plotarea plt) {

		isXAxis = xAxis;
		if (isXAxis)
			side = 0;
		//default bottom X, left Y
		plotarea = plt;
		datasets = dsets;
		if (plotarea != null)
			globals = plotarea.globals;
		else
			globals = null;
		titleColor = Color.black;
		labelColor = Color.black;
		lineGc = new Gc(globals);
		gridGc = new Gc(globals);
		tickGc = new Gc(globals);
	}
	/**
	 *	Does nothing
	 *@see	javachart.chart.LabelAxis
	 */
	public synchronized void addLabels(String str[]) {
	}
	protected void buildDisplayList() {
		switch (side) {
			case 0 :
				globals.displayList.addRectangle(
					(Object) this,
					plotarea.transform.point(plotarea.llX, plotarea.llY),
					plotarea.transform.point(plotarea.urX, 0.0));
				break;
			case 1 :
				globals.displayList.addRectangle(
					(Object) this,
					plotarea.transform.point(plotarea.llX, plotarea.llY),
					plotarea.transform.point(0.0, plotarea.urY));
				break;
			case 2 :
				globals.displayList.addRectangle(
					(Object) this,
					plotarea.transform.point(plotarea.llX, plotarea.urY),
					plotarea.transform.point(plotarea.urX, 1.0));
				break;
			case 3 :
				globals.displayList.addRectangle(
					(Object) this,
					plotarea.transform.point(plotarea.urX, plotarea.urY),
					plotarea.transform.point(1.0, plotarea.llY));
				break;
		}
	}
	//-----------------------------------------------------------
	//	log scaling logic
	private int checkItemQ(int item, int tens, int threes) {
		if (item >= tens)
			item = tens;
		else if (item >= threes)
			item = threes;
		else
			item = axMagnitude;
		return item;
	}
	//happens if user-scaled and logarithmic
	private void checkLogAx() {
		//make sure we've got the right number of everything - user may have raised quantities
		axisStart = Math.floor(axisStart);
		axisEnd = Math.ceil(axisEnd);
		axMagnitude = (int) (axisEnd - axisStart);
		int tens = axMagnitude * 10;
		int threes = axMagnitude * 3;
		numLabels = checkItemQ(numLabels, tens, threes);
		numGrids = checkItemQ(numGrids, tens, threes);
		numMinTicks = checkItemQ(numMinTicks, tens, threes);
		numMajTicks = checkItemQ(numMajTicks, tens, threes);
	}
	/**
	 *	Returns the number of Datasets actually used in this Axis' Dataset array
	 */
	protected synchronized int datasetsInUse() {
		int i;

		for (i = 0; i < datasets.length; i++)
			if (datasets[i] == null)
				return i;
		return i;
	}
	/**
	 *	Draws this Axis, including grids, ticks and labels.  This method is
	 *	usually invoked automatically by the Chart class containing this Axis.
	 *@param	g	Graphic class to use for drawing
	 */
	public synchronized void draw(Graphics g) {
		if (autoScale)
			scale();
		else if (logScaling) //user scaling and log axis - validate
			checkLogAx();
		if (lineVis)
			drawLine(g);
		drawTicks(g);
		if (gridVis)
			if (!globals.threeD)
				drawGrids(g);
			else
				draw3Dgrids(g);
		if (labelVis)
			drawLabels(g);
		if (useDisplayList && globals.useDisplayList)
			buildDisplayList();
	}
	/**
	 *	Draws 2-D grid lines on the specified Graphics
	 * @param	g	Graphics class to draw
	 */
	protected void draw3Dgrids(Graphics g) {
		int i;

		startPoint = plotarea.transform.point(plotarea.llX, plotarea.llY);
		endPoint = plotarea.transform.point(plotarea.urX, plotarea.urY);
		switch (side) {
			case BOTTOM : //top and bottom
			case TOP :
				increment = getIncrement(endPoint.x, startPoint.x, numGrids);
				for (i = 0; i < numGrids; i++) {
					gridGc.drawLine(
						g,
						whereOnAxis(i, gridLines),
						startPoint.y,
						whereOnAxis(i, gridLines) + globals.xOffset,
						startPoint.y + globals.yOffset);
				}
				gridGc.drawLine(
					g,
					endPoint.x,
					startPoint.y,
					endPoint.x + globals.xOffset,
					startPoint.y + globals.yOffset);
				for (i = 0; i < numGrids; i++) {
					gridGc.drawLine(
						g,
						whereOnAxis(i, gridLines) + globals.xOffset,
						startPoint.y + globals.yOffset,
						whereOnAxis(i, gridLines) + globals.xOffset,
						endPoint.y + globals.yOffset);
				}
				gridGc.drawLine(
					g,
					endPoint.x + globals.xOffset,
					startPoint.y + globals.yOffset,
					endPoint.x + globals.xOffset,
					endPoint.y + globals.yOffset);
				break;
			case LEFT : //left and right
			case RIGHT :
				increment = getIncrement(endPoint.y, startPoint.y, numGrids);
				for (i = 0; i < numGrids; i++) {
					gridGc.drawLine(
						g,
						startPoint.x,
						whereOnAxis(i, gridLines),
						startPoint.x + globals.xOffset,
						whereOnAxis(i, gridLines) + globals.yOffset);
				}
				gridGc.drawLine(
					g,
					startPoint.x,
					endPoint.y,
					startPoint.x + globals.xOffset,
					endPoint.y + globals.yOffset);
				for (i = 0; i < numGrids; i++) {
					gridGc.drawLine(
						g,
						startPoint.x + globals.xOffset,
						whereOnAxis(i, gridLines) + globals.yOffset,
						endPoint.x + globals.xOffset,
						whereOnAxis(i, gridLines) + globals.yOffset);
				}
				gridGc.drawLine(
					g,
					startPoint.x + globals.xOffset,
					endPoint.y + globals.yOffset,
					endPoint.x + globals.xOffset,
					endPoint.y + globals.yOffset);
				break;
			default :
				break;
		}
	}
	/**
	 *	Draws 2-D grid lines on the specified Graphics
	 * @param	g	Graphics class to draw
	 */
	protected void drawGrids(Graphics g) {
		int i;

		startPoint = plotarea.transform.point(plotarea.llX, plotarea.llY);
		endPoint = plotarea.transform.point(plotarea.urX, plotarea.urY);
		switch (side) {
			case TOP :
			case BOTTOM :
				increment = getIncrement(endPoint.x, startPoint.x, numGrids);
				for (i = 0; i < numGrids; i++) {
					gridGc.drawLine(
						g,
						whereOnAxis(i, gridLines),
						startPoint.y,
						whereOnAxis(i, gridLines),
						endPoint.y);
				}
				gridGc.drawLine(
					g,
					endPoint.x,
					startPoint.y,
					endPoint.x,
					endPoint.y);
				break;
			case LEFT :
			case RIGHT :
				increment = getIncrement(endPoint.y, startPoint.y, numGrids);
				for (i = 0; i < numGrids; i++) {
					gridGc.drawLine(
						g,
						startPoint.x,
						whereOnAxis(i, gridLines),
						endPoint.x,
						whereOnAxis(i, gridLines));
				}
				gridGc.drawLine(
					g,
					startPoint.x,
					endPoint.y,
					endPoint.x,
					endPoint.y);
				break;
			default :
				break;
		}
	}
	/**
	 *	Draws axis labels on the specified Graphics class
	 * @param	g	Graphics class to draw
	 */
	protected void drawLabels(Graphics g) {
		int i;
		int strBase;
		double labelIncrement;
		FontMetrics fm;
		String lab;

		g.setFont(labelFont);
		fm = g.getFontMetrics();
		g.setColor(labelColor);

		int longestLabel = 0;

		switch (side) {
			case BOTTOM :
				startPoint =
					plotarea.transform.point(plotarea.llX, plotarea.llY);
				strBase = startPoint.y - majTickLength - 2;
				endPoint = plotarea.transform.point(plotarea.urX, plotarea.llY);
				increment = getIncrement(endPoint.x, startPoint.x, numLabels);
				labelIncrement = (axisEnd - axisStart) / (double) numLabels;
				for (i = 0; i < numLabels; i++) {
					lab =
						getLabel(axisStart + (labelIncrement * (double) i), i);
					lineGc.drawSmartString(
						g,
						whereOnAxis(i, axisLabels),
						strBase,
						side,
						labelAngle,
						fm,
						lab);
					if (labelAngle != 0) {
						int wid = fm.stringWidth(lab);
						if (wid > longestLabel)
							longestLabel = wid;
					}
				}
				if (barScaling && isXAxis)
					break;
				lab = getLabel(axisEnd, i);
				lineGc.drawSmartString(
					g,
					whereOnAxis(i, axisLabels),
					strBase,
					side,
					labelAngle,
					fm,
					lab);
				break;
			case LEFT :
				startPoint =
					plotarea.transform.point(plotarea.llX, plotarea.llY);
				endPoint = plotarea.transform.point(plotarea.llX, plotarea.urY);
				increment = getIncrement(endPoint.y, startPoint.y, numLabels);
				labelIncrement = (axisEnd - axisStart) / (double) numLabels;
				for (i = 0; i < numLabels; i++) {
					lab =
						getLabel(axisStart + (labelIncrement * (double) i), i);
					int wid = fm.stringWidth(lab);
					if (wid > longestLabel)
						longestLabel = wid;
					strBase = startPoint.x - majTickLength - 2;
					lineGc.drawSmartString(
						g,
						strBase,
						whereOnAxis(i, axisLabels),
						side,
						labelAngle,
						fm,
						lab);
				}
				if (barScaling && isXAxis)
					break;
				lab = getLabel(axisEnd, i);
				strBase = startPoint.x - majTickLength - 2;
				lineGc.drawSmartString(
					g,
					strBase,
					whereOnAxis(i, axisLabels),
					side,
					labelAngle,
					fm,
					lab);
				break;
			case TOP :
				startPoint =
					plotarea.transform.point(plotarea.llX, plotarea.urY);
				strBase = startPoint.y + majTickLength + 2 + fm.getMaxDescent();
				endPoint = plotarea.transform.point(plotarea.urX, plotarea.urY);
				increment = getIncrement(endPoint.x, startPoint.x, numLabels);
				labelIncrement = (axisEnd - axisStart) / (double) numLabels;
				for (i = 0; i < numLabels; i++) {
					lab =
						getLabel(axisStart + (labelIncrement * (double) i), i);
					lineGc.drawSmartString(
						g,
						whereOnAxis(i, axisLabels),
						strBase,
						side,
						labelAngle,
						fm,
						lab);
				}
				if (barScaling && isXAxis)
					break;
				lab = getLabel(axisEnd, i);
				lineGc.drawSmartString(
					g,
					whereOnAxis(i, axisLabels),
					strBase,
					side,
					labelAngle,
					fm,
					lab);
				if (labelAngle != 0) {
					int wid = fm.stringWidth(lab);
					if (wid > longestLabel)
						longestLabel = wid;
				}
				break;
			case RIGHT :
				startPoint =
					plotarea.transform.point(plotarea.urX, plotarea.llY);
				endPoint = plotarea.transform.point(plotarea.urX, plotarea.urY);
				increment = getIncrement(endPoint.y, startPoint.y, numLabels);
				labelIncrement = (axisEnd - axisStart) / (double) numLabels;
				strBase = startPoint.x + majTickLength + 2;
				for (i = 0; i < numLabels; i++) {
					lab =
						getLabel(axisStart + (labelIncrement * (double) i), i);
					int wid = fm.stringWidth(lab);
					if (wid > longestLabel)
						longestLabel = wid;
					lineGc.drawSmartString(
						g,
						strBase,
						whereOnAxis(i, axisLabels),
						side,
						labelAngle,
						fm,
						lab);
				}
				if (barScaling && isXAxis)
					break;
				lab = getLabel(axisEnd, i);
				strBase = startPoint.x + majTickLength + 2;
				lineGc.drawSmartString(
					g,
					strBase,
					whereOnAxis(i, axisLabels),
					side,
					labelAngle,
					fm,
					lab);
				break;
			default :
				break;
		}
		/*** does the drawing here because we need tick lengths for
			the X axes ***/
		if (longestLabel == 0)
			longestLabel = fm.getMaxAscent();
		if (titleString != null)
			drawTitle(g, longestLabel);
	}
	/**
	 *	Draws an Axis line for this Axis
	 * @param	g	Graphics class to draw
	 */
	protected void drawLine(Graphics g) {
		switch (side) {
			case BOTTOM :
				lineGc.drawLine(
					g,
					plotarea.transform.point(plotarea.llX, plotarea.llY),
					plotarea.transform.point(plotarea.urX, plotarea.llY));
				break;
			case LEFT :
				lineGc.drawLine(
					g,
					plotarea.transform.point(plotarea.llX, plotarea.llY),
					plotarea.transform.point(plotarea.llX, plotarea.urY));
				break;
			case TOP :
				lineGc.drawLine(
					g,
					plotarea.transform.point(plotarea.llX, plotarea.urY),
					plotarea.transform.point(plotarea.urX, plotarea.urY));
				break;
			case RIGHT :
				lineGc.drawLine(
					g,
					plotarea.transform.point(plotarea.urX, plotarea.urY),
					plotarea.transform.point(plotarea.urX, plotarea.llY));
				break;
		}
	}
	protected void drawTicks(Graphics g) {
		int tickBase;
		int i;

		if (majTickVis) {
			switch (side) {
				case BOTTOM :
					startPoint =
						plotarea.transform.point(plotarea.llX, plotarea.llY);
					tickBase = startPoint.y;
					endPoint =
						plotarea.transform.point(plotarea.urX, plotarea.llY);
					increment =
						getIncrement(endPoint.x, startPoint.x, numMajTicks);
					for (i = 0; i < numMajTicks; i++) {
						tickGc.drawLine(
							g,
							whereOnAxis(i, majTicks),
							tickBase,
							whereOnAxis(i, majTicks),
							tickBase - majTickLength);
					}
					tickGc.drawLine(
						g,
						endPoint.x,
						tickBase,
						endPoint.x,
						tickBase - majTickLength);
					break;
				case LEFT :
					startPoint =
						plotarea.transform.point(plotarea.llX, plotarea.llY);
					tickBase = startPoint.x;
					endPoint =
						plotarea.transform.point(plotarea.llX, plotarea.urY);
					increment =
						getIncrement(endPoint.y, startPoint.y, numMajTicks);
					for (i = 0; i < numMajTicks; i++) {
						tickGc.drawLine(
							g,
							tickBase,
							whereOnAxis(i, majTicks),
							tickBase - majTickLength,
							whereOnAxis(i, majTicks));
					}
					tickGc.drawLine(
						g,
						tickBase,
						endPoint.y,
						tickBase - majTickLength,
						endPoint.y);
					break;
				case TOP :
					startPoint =
						plotarea.transform.point(plotarea.llX, plotarea.urY);
					tickBase = startPoint.y;
					endPoint =
						plotarea.transform.point(plotarea.urX, plotarea.urY);
					increment =
						getIncrement(endPoint.x, startPoint.x, numMajTicks);
					for (i = 0; i <= numMajTicks; i++) {
						tickGc.drawLine(
							g,
							whereOnAxis(i, majTicks),
							tickBase,
							whereOnAxis(i, majTicks),
							tickBase + majTickLength);
					}
					tickGc.drawLine(
						g,
						endPoint.x,
						tickBase,
						endPoint.x,
						tickBase + majTickLength);
					break;
				case RIGHT :
					startPoint =
						plotarea.transform.point(plotarea.urX, plotarea.llY);
					tickBase = startPoint.x;
					endPoint =
						plotarea.transform.point(plotarea.urX, plotarea.urY);
					increment =
						getIncrement(endPoint.y, startPoint.y, numMajTicks);
					for (i = 0; i <= numMajTicks; i++) {
						tickGc.drawLine(
							g,
							tickBase,
							whereOnAxis(i, majTicks),
							tickBase + majTickLength,
							whereOnAxis(i, majTicks));
					}
					tickGc.drawLine(
						g,
						tickBase,
						endPoint.y,
						tickBase + majTickLength,
						endPoint.y);
					break;
				default :
					break;
			}
		}

		if (minTickVis) {
			switch (side) {
				case BOTTOM :
					startPoint =
						plotarea.transform.point(plotarea.llX, plotarea.llY);
					tickBase = startPoint.y;
					endPoint =
						plotarea.transform.point(plotarea.urX, plotarea.llY);
					increment =
						getIncrement(endPoint.x, startPoint.x, numMinTicks);
					for (i = 0; i < numMinTicks; i++) {
						tickGc.drawLine(
							g,
							whereOnAxis(i, minTicks),
							tickBase,
							whereOnAxis(i, minTicks),
							tickBase - minTickLength);
					}
					tickGc.drawLine(
						g,
						endPoint.x,
						tickBase,
						endPoint.x,
						tickBase - minTickLength);
					break;
				case LEFT :
					startPoint =
						plotarea.transform.point(plotarea.llX, plotarea.llY);
					tickBase = startPoint.x;
					endPoint =
						plotarea.transform.point(plotarea.llX, plotarea.urY);
					increment =
						getIncrement(endPoint.y, startPoint.y, numMinTicks);
					for (i = 0; i < numMinTicks; i++) {
						tickGc.drawLine(
							g,
							tickBase,
							whereOnAxis(i, minTicks),
							tickBase - minTickLength,
							whereOnAxis(i, minTicks));
					}
					tickGc.drawLine(
						g,
						tickBase,
						endPoint.y,
						tickBase - minTickLength,
						endPoint.y);
					break;
				case TOP :
					startPoint =
						plotarea.transform.point(plotarea.llX, plotarea.urY);
					tickBase = startPoint.y;
					endPoint =
						plotarea.transform.point(plotarea.urX, plotarea.urY);
					increment =
						getIncrement(endPoint.x, startPoint.x, numMinTicks);
					for (i = 0; i < numMinTicks; i++) {
						tickGc.drawLine(
							g,
							whereOnAxis(i, minTicks),
							tickBase,
							whereOnAxis(i, minTicks),
							tickBase + minTickLength);
					}
					break;
				case RIGHT :
					startPoint =
						plotarea.transform.point(plotarea.urX, plotarea.llY);
					tickBase = startPoint.x;
					endPoint =
						plotarea.transform.point(plotarea.urX, plotarea.urY);
					increment =
						getIncrement(endPoint.y, startPoint.y, numMinTicks);
					for (i = 0; i < numMinTicks; i++) {
						tickGc.drawLine(
							g,
							tickBase,
							whereOnAxis(i, minTicks),
							tickBase + minTickLength,
							whereOnAxis(i, minTicks));
					}
					tickGc.drawLine(
						g,
						tickBase,
						endPoint.y,
						tickBase + minTickLength,
						endPoint.y);
					break;
				default :
					break;
			}
		}
	}
	/**
	 *	Draws an axis title a few pixels from the specified axis
	 * @param	g	Graphics class to draw
	 * @param	labelFudge	pixels away from axis to draw
	 */
	protected void drawTitle(Graphics g, int labelFudge) {
		Point startAxis, endAxis;
		FontMetrics fm;

		g.setFont(titleFont);
		fm = g.getFontMetrics();
		g.setColor(titleColor);

		switch (side) {
			case BOTTOM :
			case TOP :
				{
					if (labelAngle != 0) {
						labelFudge =
							(int) ((double) labelFudge
								* Math.sin(
									((double) labelAngle / 180) * Math.PI));
						if (labelAngle != 90)
							labelFudge += 4; //add some for the char height
					}
				}
				break;
			case LEFT :
			case RIGHT :
				{
					if (labelAngle != 0) {
						labelFudge =
							(int) ((double) labelFudge
								* Math.cos(
									((double) labelAngle / 180) * Math.PI));
						if (labelAngle != 90)
							labelFudge += 4; //add some for the char height
					}
				}
		}

		labelFudge = Math.abs(labelFudge) + majTickLength + 4;

		switch (side) {
			case BOTTOM :
				startAxis =
					plotarea.transform.point(plotarea.llX, plotarea.llY);
				endAxis = plotarea.transform.point(plotarea.urX, plotarea.llY);
				lineGc.drawString(
					g,
					startAxis.x
						+ ((endAxis.x - startAxis.x) / 2)
						- (fm.stringWidth(titleString) / 2),
					startAxis.y - labelFudge - fm.getHeight(),
					titleString);
				break;
			case LEFT :
				startAxis =
					plotarea.transform.point(plotarea.llX, plotarea.llY);
				endAxis = plotarea.transform.point(plotarea.llX, plotarea.urY);
				if (!titleRotation) {
					lineGc.drawString(
						g,
						startAxis.x - (fm.stringWidth(titleString) / 2),
						endAxis.y + 4,
						titleString);
				} else {
					lineGc.drawSmartString(
						g,
						startAxis.x - labelFudge,
						startAxis.y + (endAxis.y - startAxis.y) / 2,
						side,
						90,
						fm,
						titleString);
				}
				break;
			case TOP :
				startAxis =
					plotarea.transform.point(plotarea.llX, plotarea.urY);
				endAxis = plotarea.transform.point(plotarea.urX, plotarea.urY);
				lineGc.drawString(
					g,
					startAxis.x
						+ ((endAxis.x - startAxis.x) / 2)
						- (fm.stringWidth(titleString) / 2),
					startAxis.y + labelFudge + 3,
					titleString);
				break;
			case RIGHT :
				startAxis =
					plotarea.transform.point(plotarea.urX, plotarea.llY);
				endAxis = plotarea.transform.point(plotarea.urX, plotarea.urY);
				if (!titleRotation) {
					lineGc.drawString(
						g,
						startAxis.x - (fm.stringWidth(titleString) / 2),
						endAxis.y + 4,
						titleString);
				} else {
					lineGc.drawSmartString(
						g,
						startAxis.x + labelFudge + fm.getMaxAscent(),
						startAxis.y + (endAxis.y - startAxis.y) / 2,
						side,
						90,
						fm,
						titleString);
				}
				break;
			default :
				break;
		}
	}
	private String fmtLabel(double inVal) {

		if (userFormat != null) {
			return userFormat.format(new Double(inVal));
		}

		return numberFormat.format(inVal);
	}
	/**
	 *      Returns the current autoScale setting
	 *@return       true if this axis scales automatically
	 */
	public boolean getAutoScale() {
		return autoScale;
	}
	/**
	 *      Returns the end point of an axis.  If the LogScaling property
	 *	is true, this value is the power of 10 for the axis end.
	 *@return       End point of an axis
	 */
	public double getAxisEnd() {
		return axisEnd;
	}
	/**
	 *      Returns the starting point of an axis.  If the LogScaling property
	 *	is true, this value is the power of 10 for the axis start.
	 *@return       Starting point of an axis
	 */
	public double getAxisStart() {
		return axisStart;
	}
	/**
	 *      Returns true if this axis is scaled for a bar/column chart.
	 *      Axis for a bar/column chart always starts from zero, or the
	 *      lowest value for negative number.  Axis for charts other
	 *      than bar/column starts from the lowest value of the chart.
	 *@return       Bar/column chart scaling option
	 */
	public boolean getBarScaling() {
		return barScaling;
	}
	//Accessor methods
	/**
	 *      Returns the Dataset classes considered by this Axis
	 *@return       array of Datasets for this Axis
	 */
	public Dataset[] getDatasets() {
		return datasets;
	}
	/**
	 * Returns this Axis' Globals instance.  All components of a chart use a common
	 * Globals class to maintain DisplayLists, chart size, etc.
	 * @return javachart.chart.Globals
	 */
	public Globals getGlobals() {
		return globals;
	}
	/**
	 *      Returns the current grid Gc.
	 *@return       Current grid Gc
	 */
	public Gc getGridGc() {
		return gridGc;
	}
	/**
	 *      Determines whether grid lines will be visible or not.  Default is
	 *	invisible.
	 *@param        onOff     visible or not
	 */
	public boolean getGridVis() {
		return gridVis;
	}
	protected float getIncrement(int end, int start, int num) {
		return (float) (((float) end - (float) start) / (float) num);
	}
	/**
	 *	Returns a label at a particular location
	 * @param	inVal	Label value
	 * @param	count	Position on Axis
	 */
	protected String getLabel(double inVal, int count) {
		if (logScaling)
			return (getLogLabel(inVal, count));
		else
			return (fmtLabel(inVal));
	}
	/**
	 *      Returns the label rotation value. Default is 0.
	 *@return        f       A new float label precision
	 */
	public int getLabelAngle() {
		return labelAngle;
	}
	/**
	 *      Returns the label color.
	 *@return       Label color
	 */
	public Color getLabelColor() {
		return labelColor;
	}
	/**
	 *      Returns the label font.
	 *@return       Label font
	 */
	public Font getLabelFont() {
		return labelFont;
	}
	/**
	 * Returns the user-defined Format (NumberFormat, MessageFormat, etc.).
	 * @return java.text.Format
	 */
	public Format getLabelFormat() {
		return userFormat;
	}
	/**
	 *      Returns the labels' floating point display precision.  A precision of 2 yields
	 *	labels like "1.11".  A precision of 4 makes labels like "1.1111".  A precision of -1
	 *  yields default formatting.
	 *@return       label precision
	 */
	public int getLabelPrecision() {
		return labelPrecision;
	}
	/**
	 *      Determines whether axis labels will be visible or not.  Default is
	 *	visible.
	 *@param        onOff     visible or not
	 */
	public boolean getLabelVis() {
		return labelVis;
	}
	/**
	 *      Returns the current line Gc.
	 *@return       Current line Gc
	 */
	public Gc getLineGc() {
		return lineGc;
	}
	/**
	 *      Returns true if the axis line is set to be visible.
	 *@return       true if the axis line will be visible.
	 */
	public boolean getLineVis() {
		return lineVis;
	}
	private String getLogLabel(double dummy, int index) {
		double val;

		if (numLabels > axMagnitude * 3) { //label every 1,2,3... 10,20,30...
			double thisMag = Math.pow(10, (double) (index / 10) + axisStart);
			int whichStep = index % 10;
			val = thisMag * (whichStep + 1);
		} else if (
			numLabels > axMagnitude) { //labels at 1, 2, 5, 10, 20, 50, 100...
			double thisMag = Math.pow(10, (double) (index / 3) + axisStart);
			int whichStep = index % 3;
			switch (whichStep) {
				case 0 :
					val = thisMag;
					break;
				case 1 :
					val = thisMag * 2;
					break;
				case 2 :
					val = thisMag * 5;
					break;
				default :
					val = thisMag;
			}
		} else
			val = Math.pow(10, axisStart + (double) index);
		return fmtLabel(val);
	}
	/**
	 *      Returns the current logScaling setting
	 *@return       true if this axis will use a log10 scale
	 */
	public boolean getLogScaling() {
		return logScaling;
	}
	/**
	 *      Returns the length in pixels for major tick marks.  Default is 5.
	 *@return       Current major tick length
	 */
	public int getMajTickLength() {
		return majTickLength;
	}
	/**
	 *      Returns true if major tick marks are turned on.
	 *@return       true if major tick marks will be drawn.
	 */
	public boolean getMajTickVis() {
		return majTickVis;
	}
	/**
	 *	Returns the maximum value of this Axis' Datasets
	 */
	protected double getMaxValsFromData(int nmsets) {
		int i;
		double hi = -9e35;

		if (userAxisEnd != null)
			return userAxisEnd.doubleValue();

		if (!isXAxis)
			for (i = 0; i < nmsets; i++)
				hi = Math.max(hi, datasets[i].maxY());
		else
			for (i = 0; i < nmsets; i++)
				hi = Math.max(hi, datasets[i].maxX());
		return hi;
	}
	/**
	 *      Returns the length in pixels for major tick marks.  Default is 5.
	 *@return       Current major tick length
	 */
	public int getMinTickLength() {
		return minTickLength;
	}
	/**
	 *      Returns true if minor tick marks are turned on.
	 *@return       true if minor tick marks are visible.
	 */
	public boolean getMinTickVis() {
		return minTickVis;
	}
	/**
	 *	Returns the minimum value of this Axis' Datasets
	 */
	protected double getMinValsFromData(int nmsets) {
		int i;
		double low = 9e35;

		if (userAxisStart != null)
			return userAxisStart.doubleValue();

		if (!isXAxis)
			for (i = 0; i < nmsets; i++)
				low = Math.min(low, datasets[i].minY());
		else
			for (i = 0; i < nmsets; i++)
				low = Math.min(low, datasets[i].minX());
		return low;
	}
	//calculate step increment from normalized length of axis and incr
	private synchronized double getNormalizedIncrement(
		double rough,
		double tenMag) {
		if (inRange(rough, 0.0, 0.1)) {
			return (0.1 * tenMag);
		}
		if (inRange(rough, 0.1, 0.25)) {
			return (0.25 * tenMag); /* quarters for small diffs */
		}
		if (inRange(rough, 0.25, 0.5)) {
			return (0.5 * tenMag); /* 5's*/
		}
		if (inRange(rough, 0.5, 1.0)) {
			return (tenMag); /*10's*/
		}
		if (inRange(rough, 1.0, 9.0)) {
			return (2.0 * tenMag); /* 2's*/
		} else
			return (tenMag); /* THIS SHOULD NEVER HAPPEN */

	}
	/**
	 *      Returns the number of grid lines.  Usually this number is set
	 *	automatically by the axis scaling methods.
	 *@return       Number of grid lines
	 */
	public int getNumGrids() {
		return numGrids;
	}
	/**
	 *      Returns the number of axis labels.  Usually this number is set
	 *	automatically by the axis scaling methods.
	 *@return       Number of minor ticks
	 */
	public int getNumLabels() {
		return numLabels;
	}
	/**
	 *      Returns the number of major ticks.  Usually this number is set
	 *	automatically by the axis scaling methods.
	 *@return       Number of major ticks
	 */
	public int getNumMajTicks() {
		return numMajTicks;
	}
	/**
	 *      Returns the number of minor ticks.  Usually this number is set
	 *	automatically by the axis scaling methods.
	 *@return       Number of minor ticks
	 */
	public int getNumMinTicks() {
		return numMinTicks;
	}
	/**
	 * Returns this Axis' Plotarea.  Axes draw at the edge of a Plotarea object.
	 * @return javachart.chart.Plotarea
	 */
	public Plotarea getPlotarea() {
		return plotarea;
	}
	/**
	 *      Returns the side this axis is currently assigned to.
	 *@return       Current side
	 */
	public int getSide() {
		return side;
	}
	/**
	 *      Returns the current tick Gc.
	 *@return       Current tick Gc
	 */
	public Gc getTickGc() {
		return tickGc;
	}
	/**
	 *      Returns the title color.  Default is black
	 *@return       title color
	 */
	public Color getTitleColor() {
		return titleColor;
	}
	/**
	 *      Returns the title font.  Default is TimesRoman, plain, 12 point.
	 *@return       Title font
	 */
	public Font getTitleFont() {
		return titleFont;
	}
	/**
	 *      Returns the Axis title.
	 *@return       The Axis title
	 */
	public String getTitleString() {
		return titleString;
	}
	/**
	 *      Returns true if this Axis places values into the chart's DisplayList.
	 *@return       Status of display list usage
	 *@see		javachart.chart.DisplayList
	 */
	public boolean getUseDisplayList() {
		return useDisplayList;
	}
	//approx number of steps on axis

	private synchronized boolean inRange(double x, double lo, double hi) {
		return ((((x) <= (hi)) && ((x) >= (lo))));
	}
	/**
	 *      Returns true if vertical axes should have a rotated title.
	 *@return       Title rotation property
	 */
	public boolean isTitleRotated() {
		return titleRotation;
	}
	/**
	 * Does this Axis examine X or Y values.
	 * @return boolean
	 */
	public boolean isXAxis() {
		return isXAxis;
	}
	private synchronized boolean linearScale() {
		double low;
		double hi;
		double dif;
		double norm;
		double normalizedIncrement;
		double inc;
		double step;
		double mn = 9e35;
		double mx = -9e35;
		int i;
		boolean onepoint = true;
		int nmsets;

		nmsets = datasetsInUse();
		if (nmsets == 0)
			return (false);

		hi = getMaxValsFromData(nmsets);
		low = getMinValsFromData(nmsets);

		//replaces code below... 
		if ((nmsets > 1) || (datasets[0].data.size() > 1))
			onepoint = false;

		if (barScaling) {
			//note - this will fail if the bar baseline is set
			if (low > 0.0)
				low = 0.0;
			if (hi < 0.0)
				hi = 0.0;
		}

		if (((onepoint) || (hi == low))
			&& userAxisStart == null
			&& userAxisEnd == null)
			if (low != 0.0)
				dif = Math.abs(low * 2.0);
			else if (hi == 0.0)
				dif = 2.0;
			else
				dif = hi;
		else
			dif = hi - low;

		double mag = Transform.log10(dif);
		if (mag < 0)
			norm = Math.pow(10.0, Math.ceil(mag));
		else
			norm = Math.pow(10.0, Math.floor(mag));
		inc = dif / (NODIV * norm);

		/* CALC STEP SIZE */
		normalizedIncrement = getNormalizedIncrement(inc, norm);
		stepSize = normalizedIncrement;

		/* CALC START VAL */
		mn = Math.floor(low / normalizedIncrement) * normalizedIncrement;
		while ((mn += normalizedIncrement) <= low);
		mn = mn - normalizedIncrement;

		/* CALC END VAL */
		mx = mn;
		while ((mx += normalizedIncrement) < hi);
		axisStart = mn;
		axisEnd = mx;

		numLabels = (int) ((mx - mn) / stepSize);
		//one on the end
		numMajTicks = numLabels;
		numGrids = numLabels;
		numMinTicks = 2 * numMajTicks;

		return (true);
	}
	private synchronized boolean logScale() {
		int nmsets = datasetsInUse();
		if (nmsets == 0)
			return (false);
		double hi = getMaxValsFromData(nmsets);
		double low = getMinValsFromData(nmsets);
		if (hi <= 0.0 || low <= 0.0)
			return false;
		try {
			axisStart = Math.floor(Transform.log10(low));
			axisEnd = Math.ceil(Transform.log10(hi));

			axMagnitude = (int) (axisEnd - axisStart);
			if (axMagnitude < 2)
				numLabels =
					numGrids = numMajTicks = numMinTicks = 10 * axMagnitude;
			else if (axMagnitude < 3) { //we want 1, 2, 5 increments...
				numLabels = numGrids = numMajTicks = 3 * axMagnitude;
				numMinTicks = 10 * axMagnitude;
			} else
				numLabels = numGrids = numMajTicks = numMinTicks = axMagnitude;
		} catch (Exception e) {
			//probably have negative numbers here
			return false;
		}
		return true;
	}
	/**
	 *	Recalculates internal scale values, axis starting & ending values.
	 *	If autoScaling is active, this method is also called by the draw
	 *	method.
	 */
	public void scale() {
		if (!logScaling)
			linearScale();
		else if (
			!logScale()) { //probably trying to scale against 0 or negative numbers
			logScaling = false;
			linearScale();
		}

	}
	/**
	 *      Change the autoScale value.
	 *@param        yesNo   If true, this axis will scale automatically when drawn.
	 *			If false, the axis will use its current scale and
	 *			starting/ending values.
	 */
	public void setAutoScale(boolean yesNo) {
		autoScale = yesNo;
	}
	/**
	 *      Change the end point of an axis.
	 *@param        num     New end point
	 *		For default axes, this is the numeric value that ends this axis.
	 *		If the axis is log scaled, this is the power of 10 that ends this
	 *		axis.
	 */
	public void setAxisEnd(double num) {
		if (num == AUTO_SCALE) {
			userAxisEnd = null;
		} else {
			userAxisEnd = new Double(num);
			axisEnd = num;
		}
	}
	/**
	 *      Change the starting point of an axis.
	 *@param        num     New starting point
	 *		For default axes, this is the numeric value that starts this axis.
	 *		If the axis is log scaled, this is the power of 10 that starts this
	 *		axis.  If the value is AUTO_SCALE, the Axis start point will be automatically
	 *		determined.
	 */
	public void setAxisStart(double num) {
		if (num == AUTO_SCALE) {
			userAxisStart = null;
		} else {
			axisStart = num;
			userAxisStart = new Double(num);
		}
	}
	/**
	 *      Set scaling option to accomodate bar/column charts.
	 *@param        yesNo   whether to scale for bars
	 */
	public void setBarScaling(boolean yesNo) {
		barScaling = yesNo;
	}
	/**
	 *      Install a new array of Datasets for use by this Axis
	 *@param        newDatasets 	Array of datasets to use for scaling.
	 */
	public void setDatasets(Dataset[] newDatasets) {
		datasets = newDatasets;
		if (globals == null)
			return;
		for (int i = 0; i < datasets.length; i++) {
			if (datasets[i] != null) {
				if (datasets[i].globals != globals)
					datasets[i].setGlobals(globals);
			}
		}
	}
	/**
	 * Installs a Globals class for this Axis.  Globals classes are shared by all
	 * objects in a Chart, and provide information about DisplayLists, size, etc.
	 * This method installs a Globals class into this Axis and its constituent
	 * instance variables (plotarea, lineGc, etc.)
	 * @param g javachart.chart.Globals
	 */
	public void setGlobals(Globals g) {
		if (g == null)
			return;
		globals = g;
		lineGc.globals = g;
		tickGc.globals = g;
		gridGc.globals = g;
		plotarea.globals = g;
		if (datasets != null) {
			for (int i = 0; i < datasets.length; i++) {
				if (datasets[i] != null) {
					if (datasets[i].globals != g)
						datasets[i].setGlobals(g);
				}
			}
		}
	}
	/**
	 *      Change the current grid Gc.
	 *@param        g       A new grid Gc
	 */
	public void setGridGc(Gc g) {
		gridGc = g;
		g.globals = globals;
	}
	/**
	 *      Returns true if grid lines are turned on.
	 *@return       true if the grid lines are visible.
	 */
	public void setGridVis(boolean onOff) {
		gridVis = onOff;
	}
	/**
	 * Determines whether this axis will scale against X or Y values.
	 * @param xAx boolean
	 */
	public void setIsXAxis(boolean xAx) {
		isXAxis = xAx;
	}
	/**
	 *      Sets the label rotation value for labels on this Axis.
	 *@param        num       A new label rotation value
	 */
	public void setLabelAngle(int num) {
		labelAngle = num;
	}
	/**
	 *      Set the label color.
	 *@param        c       A new label color
	 */
	public void setLabelColor(Color c) {
		labelColor = c;
	}
	/**
	 *      Set the label font.
	 *@param        f       A new label font
	 */
	public void setLabelFont(Font f) {
		labelFont = f;
	}
	/**
	 *      Determines what format to use for this Axis' labels.  The default
	 *	is Gc.DEFAULT_FORMAT, which is a machine-dependent representation
	 *	for Java 1.0x, and may include Scientific notation.
	 *
	 *	Other possibilities include Gc.COMMA_FORMAT, which formats numbers as
	 *	1,000.00 and Gc.EURO_FORMAT, which formats numbers like 1.000,00.
	 *
	 *	As of release 3.0, this method simply uses Locale.FRANCE with NumberFormat
	 *  for settings of Gc.EURO_FORMAT, and Locale.US for Gc.COMMA_FORMAT,
	 *  and the default Locale for Gc.DEFAULT_FORMAT
	 *@param        i   	label format
	 *@deprecated Replaced by the much more flexible and accurate setFormat
	 */
	public void setLabelFormat(int i) {
		//Note: we're assuming that Locale.FRANCE will always be available...
		if (i == Gc.EURO_FORMAT)
			numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);
		if (i == Gc.COMMA_FORMAT)
			numberFormat = NumberFormat.getNumberInstance(Locale.US);
		if (i == Gc.DEFAULT_FORMAT)
			numberFormat = NumberFormat.getNumberInstance();
		/**
		if ((i<Gc.DEFAULT_FORMAT)||(i>Gc.EURO_FORMAT))
			return;
		else
			labelFormat = i;
			**/
	}
	/**
	 * Installs a user-defined Format for use in drawing Axis labels.  This could be done
	 * to do things like specify a fixed Locale, or create additional formatting, such as
	 * prepending a "$" to each label.  Set format to null to revert to default formatting.
	 * @param format java.text.Format
	 */
	public void setLabelFormat(Format format) {
		userFormat = format;
	}
	/**
	 *      Set the labels' floating point display precision.
	 *@param        num       A new label precision
	 */
	public void setLabelPrecision(int num) {
		labelPrecision = num;
		if (num != -1) {
			numberFormat.setMinimumFractionDigits(num);
			numberFormat.setMaximumFractionDigits(num);
		} else
			numberFormat = NumberFormat.getInstance(); //clear old settings
	}
	/**
	 *      Returns true if labels are turned on.
	 *@return       true if the labels are visible.
	 */
	public void setLabelVis(boolean onOff) {
		labelVis = onOff;
	}
	/**
	 *      Change the current line Gc.
	 *@param        g       A new line Gc
	 */
	public void setLineGc(Gc g) {
		lineGc = g;
		lineGc.globals = globals;
	}
	/**
	 *      Determines whether an axis line will be visible or not.  Default is
	 *	visible.
	 *@param        onOff     visible or not
	 */
	public void setLineVis(boolean onOff) {
		lineVis = onOff;
	}
	/**
	 *      Change the logScaling value.
	 *@param        yesNo   If true, this axis will scale using common logarithms.
	 *				If false, the axis will scale linearly.  Log scaling is restricted
	 *				to positive data values.  This axis style uses a heuristic that
	 *				may or may not use the number of labels, grids, etc. you specify
	 *				when using a user-defined scale.  The axis start and end will
	 *				always be some power of 10, and the frequency and distribution of label, grid
	 *				and tick elements will be based partly upon your requested value,
	 *				and partly upon what is visually reasonable.
	 */
	public void setLogScaling(boolean yesNo) {
		logScaling = yesNo;
	}
	/**
	 *      Sets the length of major tick marks in pixels.
	 *@param        num     A new major tick length
	 */
	public void setMajTickLength(int num) {
		majTickLength = num;
	}
	/**
	 *      Determines whether major tick marks will be visible or not.  Default is
	 *	visible.
	 *@param        onOff     visible or not
	 */
	public void setMajTickVis(boolean onOff) {
		majTickVis = onOff;
	}
	/**
	 *      Sets the length of minor tick marks in pixels.
	 *@param        num     A new minor tick length
	 */
	public void setMinTickLength(int num) {
		minTickLength = num;
	}
	/**
	 *      Determines whether an minor tick marks will be visible or not.  
	 *	Default is invisible.
	 *@param        onOff     visible or not
	 */
	public void setMinTickVis(boolean onOff) {
		minTickVis = onOff;
	}
	/**
	 *      Set the number of grid lines.  This does nothing unless autoScale is 
	 *	false.
	 *@param        num     A new number of grid lines
	 */
	public void setNumGrids(int num) {
		numGrids = num;
	}
	/**
	 *      Set the number of axis labels.  This does nothing unless autoScale is 
	 *	false.
	 *@param        num     A new number of axis labels
	 */
	public void setNumLabels(int num) {
		numLabels = num;
	}
	/**
	 *      Set the number of major ticks.  This does nothing unless autoScale is 
	 *	false.
	 *@param        num     A new number of major ticks
	 */
	public void setNumMajTicks(int num) {
		numMajTicks = num;
	}
	/**
	 *      Set the number of minor ticks.  This does nothing unless autoScale is 
	 *	false.
	 *@param        num     A new number of minor ticks
	 */
	public void setNumMinTicks(int num) {
		numMinTicks = num;
	}
	/**
	 * Sets the Plotarea bounded by this Axis.  Axes always draw along the edge of
	 * a Plotarea object.  This sets the Plotarea object used by this Axis for
	 * sizing and location.
	 * @param p javachart.chart.Plotarea
	 */
	public void setPlotarea(Plotarea p) {
		plotarea = p;
		if (globals == null)
			if (p != null)
				globals = p.globals;
	}
	/**
	 *      Assigns this axis to a particular side of the Plotarea.  Sides are
	 *		BOTTOM, LEFT, TOP, or RIGHT.
	 *@param        num     A new side
	 */
	public void setSide(int num) {
		side = num;
	}
	/**
	 *      Sets the current tick Gc.
	 *@param        g       A new tick Gc
	 */
	public void setTickGc(Gc g) {
		tickGc = g;
		tickGc.globals = globals;
	}
	/**
	 *      Sets the title color.
	 *@param        c       A new title color
	 */
	public void setTitleColor(Color c) {
		titleColor = c;
	}
	/**
	 *      Set the title font.
	 *@param        f       A new title font
	 */
	public void setTitleFont(Font f) {
		titleFont = f;
	}
	/**
	 *	Determines whether vertical axes will have a rotated title
	 *@param        yesNo	true if the title should be rotated
	 */
	public void setTitleRotated(boolean yesNo) {
		titleRotation = yesNo;
	}
	/**
	 *      Sets the Axis title.  The default is no title.
	 *@param        s       A new title
	 */
	public void setTitleString(String s) {
		titleString = s;
	}
	/**
	 *      Determines whether this Axis should add values to the Chart's
	 *	DisplayList.  By default this is true.
	 *@param        onOff   New status of useDisplayList
	 *@see		javachart.chart.DisplayList
	 */
	public void setUseDisplayList(boolean onOff) {
		useDisplayList = onOff;
	}
	/**
	 *	String representations of various Axis information
	 */
	public String toString() {
		return getClass().getName()
			+ "["
			+ " xAxis? "
			+ isXAxis
			+ " stepSize "
			+ stepSize
			+ " axisStart "
			+ axisStart
			+ " axisEnd "
			+ axisEnd
			+ " numMajTicks "
			+ numMajTicks
			+ " numMinTicks "
			+ numMinTicks
			+ " numLabels "
			+ numLabels
			+ " ]";

	}
	/**
	 *	computes the location along an axis of a particular tick, grid, or label
	 * @param	whichStep	how many increments along Axis
	 * @param	whichElement	axisLabels, minTicks, majTicks, or gridLines
	 */

	protected int whereOnAxis(int whichStep, int whichElement) {
		if (logScaling) {
			return (whereOnLogAxis(whichStep, whichElement));
		}
		if ((side == 0) || (side == 2))
			return (startPoint.x + (int) (increment * whichStep));
		else
			return (startPoint.y + (int) (increment * whichStep));
	}
	private int whereOnLogAxis(int whichStep, int whichElement) {

		boolean doTwoFive = false;
		boolean doOneTwo = false;
		int threes = axMagnitude * 3;
		int loc = 0;
		switch (whichElement) {
			case axisLabels :
				if (numLabels > axMagnitude * 3)
					doOneTwo = true;
				else if (numLabels > axMagnitude)
					doTwoFive = true;
				break;
			case gridLines :
				if (numGrids > axMagnitude * 3)
					doOneTwo = true;
				else if (numGrids > axMagnitude)
					doTwoFive = true;
				break;
			case minTicks :
				if (numMinTicks > axMagnitude * 3)
					doOneTwo = true;
				else if (numMinTicks > axMagnitude)
					doTwoFive = true;
				break;
			case majTicks :
				if (numMajTicks > axMagnitude * 3)
					doOneTwo = true;
				else if (numMajTicks > axMagnitude)
					doTwoFive = true;
				break;
			default :
				doTwoFive = false;
		}

		if (doOneTwo) {
			//see doTwoFive for logic - draws at 1,2,3... 10,20,30...
			loc =
				(int) ((whichStep - (whichStep % 10)) * increment
					+ (increment * oneTwoSteps[whichStep % 10]));
		} else if (doTwoFive) {
			/* we know that whichStep/3 will tell us which order of magnitude
			this falls in.  whichStep%3 will tell us how much to add.   
			increment gives us a linear distribution: increment*3 is one
			order of magnitude.  From this we compute magnitude*2 and
			magnitue*5, which are our desired locations 
			*/
			loc =
				(int) ((whichStep - (whichStep % 3)) * increment
					+ (increment * twoFiveSteps[whichStep % 3]));
			/*  here's the logic:
			switch(whichStep%3){
			case 0 : loc = (int)(whichStep * increment);
				break;
			case 1 : loc = (int)((whichStep - 1) * increment + (increment * 3 * Transform.log10(2)));
				break;
			case 2 : loc = (int)((whichStep - 2) * increment + (increment * 3 * Transform.log10(5)));
				break;
			default : loc = (int)(whichStep * increment);
			}
			*/
		} else {
			loc = (int) (increment * whichStep);
		}
		if ((side == 0) || (side == 2))
			return (startPoint.x + loc);
		else
			return (startPoint.y + loc);
	}
}