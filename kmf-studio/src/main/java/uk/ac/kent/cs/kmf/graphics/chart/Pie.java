package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.text.*;

/**
 *	Creates a single Pie component.  Pies draw a series of slices
 *	proportional to Y data values for a specified Dataset.  Each
 *	slice is drawn with the graphical attributes specified by its
 *	corresponding Datum's Gc class.
 */

public class Pie extends DataRepresentation implements Serializable {

	boolean textLabelsOn = false;
	boolean valueLabelsOn = false;
	boolean percentLabelsOn = true;
	int labelPosition = 2;
	int labelFormat = Gc.DEFAULT_FORMAT;
	int labelPrecision = 0;
	double xLoc = 0.5;
	double yLoc = 0.5;
	int startDegrees = 0;
	double width = 0.6;
	double height = 0.6;
	Font labelFont = Gc.defaultFont;
	Color labelColor = Color.black;

	//utility vars
	Dataset dataset;
	double total = 0.0;
	Gc lineGc = new Gc(Color.black, null);
	private Point widthHeight;
	public String currencyString = null;
	NumberFormat percentFormat = null;

	/**
	 * Create a Pie component without assigning Datasets or Globals.
	 */
	public Pie() {
	}
	/**
	 *	Constructs a new Pie based on the specified dataset, drawn in a size
	 *	relative to the overall chart size defined by this chart's Plotarea,
	 *	and using this chart's Globals class
	 *
	 * @param	d	This Pie's Dataset
	 * @param	p	Plotarea for sizing purposes
	 * @param	g	This chart's Global's class
	 */
	public Pie(Dataset d, Plotarea p, Globals g) {

		dataset = d;
		plotarea = p;
		globals = g;
		lineGc.globals = g;
	}
	/**
	 *	Method for drawing Pie with a 3-D visual effect
	 * @param	g	Graphics to draw
	 * @param	xCenter	X location in percentage coordinates
	 * @param	yCenter Y location in percentage coordinates
	 */
	protected void doDPie(Graphics g, double xCenter, double yCenter) {

		int i;
		int whichSlice;
		int startAngle = startDegrees;
		int arcAngle;
		double explodedX, explodedY;
		double radians;
		Point centerPt, pt[];
		Color saveColor;

		widthHeight = plotarea.transform.point(width, height);
		pt = new Point[4];
		for (i = 0; i < dataset.data.size(); i++) {
			total += dataset.getDataElementAt(i).y;
		}

		//do bottoms
		for (i = 0; i < dataset.data.size(); i++) {
			if (i == dataset.data.size() - 1)
				arcAngle = 360 - startAngle + startDegrees;
			else
				arcAngle =
					(int) Math.round(
						360 * dataset.getDataElementAt(i).y / total);
			/**** took out explosion on 3d pies for now
						radians = (double)(arcAngle/2+startAngle)/180.0*Math.PI;
						explodedX = xLoc + dataset.getDataElementAt(i).y2 * Math.cos(radians);
						explodedY = yLoc + dataset.getDataElementAt(i).y2 * Math.sin(radians);
			******************/
			explodedX = xLoc;
			explodedY = yLoc;

			centerPt = plotarea.transform.point(explodedX, explodedY);
			centerPt.y -= globals.yOffset;
			saveColor = dataset.getDataElementAt(i).gc.fillColor;
			dataset.getDataElementAt(i).gc.fillColor = saveColor.darker();
			dataset.getDataElementAt(i).gc.fillArc(
				g,
				centerPt,
				widthHeight,
				startAngle,
				arcAngle);
			dataset.getDataElementAt(i).gc.fillColor = saveColor;
			startAngle += arcAngle;
		}

		//do backs
		startAngle = startDegrees;
		for (i = 0; i < dataset.data.size(); i++) {
			if (i == dataset.data.size() - 1)
				arcAngle = 360 - startAngle + startDegrees;
			else
				arcAngle =
					(int) Math.round(
						360 * dataset.getDataElementAt(i).y / total);
			/********took out exploded for now
						radians = (double)(arcAngle/2+startAngle)/180.0*Math.PI;
						explodedX = xLoc + dataset.getDataElementAt(i).y2 * Math.cos(radians);
						explodedY = yLoc + dataset.getDataElementAt(i).y2 * Math.sin(radians);
			*********************/

			explodedX = xLoc;
			explodedY = yLoc;
			centerPt = plotarea.transform.point(explodedX, explodedY);
			centerPt.y -= globals.yOffset;
			if ((startAngle < 180) && ((startAngle + arcAngle) > 180)) {
				arcAngle = arcAngle - (180 - startAngle);
				startAngle = 180;
			}
			pt[1] = getArcPoint(centerPt, startAngle);
			pt[0] = new Point(pt[1].x, pt[1].y + globals.yOffset);
			pt[2] = getArcPoint(centerPt, startAngle + arcAngle);
			pt[3] = new Point(pt[2].x, pt[2].y + globals.yOffset);

			saveColor = dataset.getDataElementAt(i).gc.fillColor;
			dataset.getDataElementAt(i).gc.fillColor = saveColor.darker();
			dataset.getDataElementAt(i).gc.drawPolygon(g, pt);
			dataset.getDataElementAt(i).gc.fillColor = saveColor;
			startAngle += arcAngle;
		}

		//do tops
		startAngle = startDegrees;
		for (i = 0; i < dataset.data.size(); i++) {
			if (i == dataset.data.size() - 1)
				arcAngle = 360 - startAngle + startDegrees;
			else
				arcAngle =
					(int) Math.round(
						360 * dataset.getDataElementAt(i).y / total);
			radians = (double) (arcAngle / 2 + startAngle) / 180.0 * Math.PI;
			/***********not exploding yet
						explodedX = xLoc + dataset.getDataElementAt(i).y2 * Math.cos(radians);
						explodedY = yLoc + dataset.getDataElementAt(i).y2 * Math.sin(radians);
			*******************/
			explodedX = xLoc;
			explodedY = yLoc;
			dataset.getDataElementAt(i).gc.fillArc(
				g,
				plotarea.transform.point(explodedX, explodedY),
				widthHeight,
				startAngle,
				arcAngle);
			if (useDisplayList && globals.useDisplayList) {
				globals.displayList.addArc(
					(Object) dataset.getDataElementAt(i),
					plotarea.transform.point(explodedX, explodedY),
					plotarea.transform.point(width, height),
					startAngle,
					arcAngle);
			}
			if (textLabelsOn || valueLabelsOn || percentLabelsOn)
				doPieLabel(
					g,
					explodedX,
					explodedY,
					radians,
					dataset.getDataElementAt(i));
			startAngle += arcAngle;
		}
		total = 0.0;
	}
	/**
	 *	Method for drawing overall Pie
	 * @param	g	Graphics to draw
	 * @param	xCenter	X location in percentage coordinates
	 * @param	yCenter	Y location in percentage coordinates
	 */
	//internal functions
	protected void doPie(Graphics g, double xCenter, double yCenter) {

		int i;
		int whichSlice;
		int startAngle = startDegrees;
		int arcAngle;
		double explodedX, explodedY;
		double radians;

		for (i = 0; i < dataset.data.size(); i++) {
			total += dataset.getDataElementAt(i).y;
		}

		for (i = 0; i < dataset.data.size(); i++) {
			if (i == dataset.data.size() - 1) {
				arcAngle = 360 - startAngle + startDegrees;
			} else
				arcAngle =
					(int) Math.round(
						360 * dataset.getDataElementAt(i).y / total);
			radians = (double) (arcAngle / 2 + startAngle) / 180.0 * Math.PI;
			if (dataset.getDataElementAt(i).y2 != Datum.DEFAULT) { //default
				explodedX =
					xLoc + dataset.getDataElementAt(i).y2 * Math.cos(radians);
				explodedY =
					yLoc + dataset.getDataElementAt(i).y2 * Math.sin(radians);
			} else {
				explodedX = xLoc;
				explodedY = yLoc;
			}
			dataset.getDataElementAt(i).gc.fillArc(
				g,
				plotarea.transform.point(explodedX, explodedY),
				plotarea.transform.point(width, height),
				startAngle,
				arcAngle);
			if (useDisplayList && globals.useDisplayList) {
				globals.displayList.addArc(
					(Object) dataset.getDataElementAt(i),
					plotarea.transform.point(explodedX, explodedY),
					plotarea.transform.point(width, height),
					startAngle,
					arcAngle);
			}
			if (textLabelsOn || valueLabelsOn || percentLabelsOn)
				doPieLabel(
					g,
					explodedX,
					explodedY,
					radians,
					dataset.getDataElementAt(i));
			startAngle += arcAngle;
		}
		total = 0.0;
	}
	/**
	 *	Draws a specified data point label.  Note: textual Pie labels behave
	 *  somewhat differently, compared to other chart types.  A "|" character
	 *  within a data label string is recognized as a line break.
	 */
	private void doPieLabel(
		Graphics g,
		double centerX,
		double centerY,
		double radians,
		Datum dataPoint) {

		double pieEdgeX;
		double pieEdgeY;
		double pointerEndX;
		double pointerEndY;
		Point pixelLoc;
		int x, y;
		String valueString = null;
		double twoPI = 2.0 * Math.PI;
		StringTokenizer st = null;
		int longestTextStringWidth = 0;

		//need to get radians within 2 PI
		while (radians > (twoPI))
			radians -= twoPI;
		//if(valueLabelsOn){
		valueString = formatLabel(dataPoint.y);
		//}

		g.setFont(labelFont);
		g.setColor(labelColor);
		FontMetrics fm = g.getFontMetrics();

		if (textLabelsOn && (dataPoint.label != null)) {
			st = new StringTokenizer(dataPoint.label, "|");
			while (st.hasMoreTokens()) {
				int length = fm.stringWidth(st.nextToken());
				if (length > longestTextStringWidth)
					longestTextStringWidth = length;
			}
			st = new StringTokenizer(dataPoint.label, "|");
		}

		if (labelPosition == 1) {
			//at edge of slice
			pieEdgeX = centerX + width / 2 * Math.cos(radians);
			pieEdgeY = centerY + height / 2 * Math.sin(radians);
			pixelLoc = plotarea.transform.point(pieEdgeX, pieEdgeY);
			x = pixelLoc.x;
			y = pixelLoc.y;
		} else if (labelPosition == 0) {
			//center of slice
			pieEdgeX = centerX + width / 4 * Math.cos(radians);
			pieEdgeY = centerY + height / 4 * Math.sin(radians);
			pixelLoc = plotarea.transform.point(pieEdgeX, pieEdgeY);
			x = pixelLoc.x;
			y = pixelLoc.y;
		} else if (labelPosition == 2) {
			//outside edge with pointer
			pieEdgeX = centerX + width / 2 * Math.cos(radians);
			pieEdgeY = centerY + height / 2 * Math.sin(radians);
			pointerEndX = centerX + width / 1.8 * Math.cos(radians);
			pointerEndY = centerY + height / 1.8 * Math.sin(radians);
			lineGc.drawLine(
				g,
				plotarea.transform.point(pieEdgeX, pieEdgeY),
				plotarea.transform.point(pointerEndX, pointerEndY));
			g.setColor(labelColor); //set color back to label colr
			// a little bit of room at the end of the line
			pointerEndX = centerX + width / 1.79 * Math.cos(radians);
			pointerEndY = centerY + height / 1.79 * Math.sin(radians);
			pixelLoc = plotarea.transform.point(pointerEndX, pointerEndY);
			x = pixelLoc.x;
			y = pixelLoc.y;
		} else
			return;

		//on the right bottom quarter
		if ((radians >= Math.PI * 1.5) && (radians <= twoPI)) {
			y -= fm.getAscent();
			if (textLabelsOn) {
				while (st.hasMoreTokens()) {
					lineGc.drawString(g, x, y, st.nextToken());
					y -= fm.getAscent();
				}
			}
			if (valueLabelsOn) {
				lineGc.drawString(g, x, y, valueString);
				y -= fm.getAscent();
			}
			if (percentLabelsOn) {
				//lineGc.drawString(g, x, y, pctStr(dataPoint.y/total));
				lineGc.drawString(g, x, y, valueString + "%");
			}
			return;
		}
		//right top quarter
		if ((radians >= 0.0) && (radians < Math.PI / 2)) {
			if (percentLabelsOn) {
				//lineGc.drawString(g, x, y, pctStr(dataPoint.y/total));
				lineGc.drawString(g, x, y, valueString + "%");
				y += fm.getAscent();
			}
			if (valueLabelsOn) {
				lineGc.drawString(g, x, y, valueString);
				y += fm.getAscent();
			}
			if (textLabelsOn) {
				Vector v = new Vector();
				while (st.hasMoreTokens())
					v.addElement(st.nextToken());
				for (int i = v.size(); i > 0; i--) {
					lineGc.drawString(g, x, y, ((String) v.elementAt(i - 1)));
					y += fm.getAscent();
				}
			}
			return;
		}
		//on the left top quarter
		if ((radians >= Math.PI / 2) && (radians < Math.PI)) {
			if ((textLabelsOn)
				&& (dataPoint.label != null))
				//assumes that the longest labels are text
				x -= longestTextStringWidth;
			else if (valueLabelsOn)
				x -= fm.stringWidth(valueString);
			else
				x -= fm.stringWidth(pctStr(dataPoint.y / total));
			if (percentLabelsOn) {
				//lineGc.drawString(g, x, y, pctStr(dataPoint.y/total));
				lineGc.drawString(g, x, y, valueString + "%");
				y += fm.getAscent();
			}
			if (valueLabelsOn) {
				lineGc.drawString(g, x, y, valueString);
				y += fm.getAscent();
			}
			if (textLabelsOn) {
				Vector v = new Vector();
				while (st.hasMoreTokens())
					v.addElement(st.nextToken());
				for (int i = v.size(); i > 0; i--) {
					lineGc.drawString(g, x, y, ((String) v.elementAt(i - 1)));
					y += fm.getAscent();
				}
			}
			return;
		}
		//on the left bottom quarter
		if ((radians >= Math.PI) && (radians < Math.PI * 1.5)) {
			if ((textLabelsOn)
				&& (dataPoint.label != null))
				//assumes that the longest labels are text
				x -= longestTextStringWidth;
			else if (valueLabelsOn)
				x -= fm.stringWidth(valueString);
			else
				x -= fm.stringWidth(pctStr(dataPoint.y / total));
			y -= fm.getAscent();
			if (textLabelsOn) {
				while (st.hasMoreTokens()) {
					lineGc.drawString(g, x, y, st.nextToken());
					y -= fm.getAscent();
				}
			}
			if (valueLabelsOn) {
				lineGc.drawString(g, x, y, valueString);
				y -= fm.getAscent();
			}
			if (percentLabelsOn) {
				//lineGc.drawString(g, x, y, pctStr(dataPoint.y/total));
				lineGc.drawString(g, x, y, valueString + "%");
				y -= fm.getAscent();
			}
		}
	}
	/**
	 *	Draw this Pie on the specified Graphics
	 */
	public synchronized void draw(Graphics g) {
		if (g == null)
			return;
		if (dataset == null)
			dataset = datasets[0];
		if (dataset == null)
			return;
		if (!globals.threeD)
			doPie(g, xLoc, yLoc);
		else
			doDPie(g, xLoc, yLoc);
	}
	//returns a point on the arc from a given angle
	private Point getArcPoint(Point center, int angle) {
		double radians;
		int x, y;
		radians = (double) (angle) / 180.0 * Math.PI;
		x = (int) ((widthHeight.x / 2) * Math.cos(radians)) + center.x;
		y = (int) ((widthHeight.y / 2) * Math.sin(radians)) + center.y;
		return new Point(x, y);
	}
	/**
	 *	Inquires the Pie's relative height.  Values range from 0 to 1.  Default
	 * 	is 0.6, or 60% of the entire chart area.
	 *
	 * @return	Pie height
	 */
	public double getHeight() {
		return height;
	}
	/**
	 *	Inquires the current color for Pie labels
	 * @return Current label color
	 */
	public Color getLabelColor() {
		return labelColor;
	}
	/**
	 *	Inquires which Font will be used for this Pie's labels
	 * @return Pie label font
	 */
	public Font getLabelFont() {
		return labelFont;
	}
	/**
	 *	Inquires where each slice's label should be drawn.
	 *
	 *	@return	Pie label position
	 */
	public int getLabelPosition() {
		return labelPosition;
	}
	/**
	 *	Inquires whether this Pie will display each data point's percentage
	 *	value.
	 * @return True if percentage labels will draw
	 */
	public boolean getPercentLabelsOn() {
		return percentLabelsOn;
	}
	/**
	 *	Inquires where the first Pie slice will begin.  Default value is 0, 
	 *	which is at 3 o' clock.
	 * @return	start degrees
	 */
	public int getStartDegrees() {
		return startDegrees;
	}
	//accessors
	/**
	 *	Inquires whether this Pie will draw each data point's Datum label
	 * @return True if text labels will draw
	 * @see javachart.chart.Datum
	 */
	public boolean getTextLabelsOn() {
		return textLabelsOn;
	}
	/**
	 *	Inquires whether this Pie will display each data point's numeric value
	 * @return True if value labels will draw
	 */
	public boolean getValueLabelsOn() {
		return valueLabelsOn;
	}
	/**
	 *	Inquires the Pie's relative width.  Values range from 0 to 1.  Default
	 * 	is 0.6, or 60% of the entire chart area.
	 *
	 * @return	Pie width
	 */
	public double getWidth() {
		return width;
	}
	/**
	 *	Inquires the X location of the center of this Pie.  Values returned
	 *	range from 0 to 1
	 * @return	X location
	 */
	public double getXLoc() {
		return xLoc;
	}
	/**
	 *	Inquires the Y location of the center of this Pie.  Values returned
	 *	range from 0 to 1
	 * @return	Y location
	 */
	public double getYLoc() {
		return yLoc;
	}
	/**
	 *	Turns a double, say .53, into a String like "53%"
	 * @param	d	input double
	 */
	protected String pctStr(double d) {
		if (percentFormat == null)
			percentFormat = NumberFormat.getPercentInstance();
		return percentFormat.format(d);
	}
	/**
	 *	Sets an explosion value for this Pie slice.  This value ranges from 0
	 *	to 1, and shifts a specified slice radially away from the Pie center 
	 *	by the specified amount.
	 *
	 * @param	whichSlice	Slice to explode
	 * @param	exp		How much to explode
	 */
	public synchronized void setExplosion(int whichSlice, double exp) {
		try {
			dataset.getDataElementAt(whichSlice).y2 = exp;
		} catch (NullPointerException e) {
			// do nothing
		} catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
	}
	/**
	 * Installs a user-defined Format for use in drawing Pie value labels.  This could be done
	 * to do things like specify a fixed Locale, or create additional formatting, such as
	 * prepending a "$" to each label.  Note: the percent label format will be derived from
	 * this format, so setting a Locale for formatting here will also affect percent label formatting.
	 * Set format to null to revert to default formatting.
	 * @param format java.text.Format
	 */
	public void setFormat(Format format) {
		super.setFormat(format);
		if (format == null) {
			numberFormat = NumberFormat.getInstance();
			percentFormat = null;
		} else {
			if (format instanceof NumberFormat)
				percentFormat = NumberFormat.getPercentInstance();
		}
	}
	/**
	 *	Determines the Pie's relative height. Values range from 0 to 1.  Default
	 *	is 0.6, or 60% of the entire chart area.
	 * @param	h	Pie height
	 */
	public void setHeight(double h) {
		if (h < 1.0)
			height = h;
	}
	/**
	 *	Sets a new color for Pie labels
	 * @param 	c	New label color
	 */
	public void setLabelColor(Color c) {
		labelColor = c;
	}
	/**
	 *	Sets a new Font for use in drawing Pie labels
	 * @param	f	Pie label font
	 */
	public void setLabelFont(Font f) {
		labelFont = f;
	}
	/**
	 *	Sets a position for Pie slice labels. 0 - in the center of each slice,
	 *	1 - at the edge of each slice, 2 - outside the slice with a pointer 
	 *	line.
	 *
	 * @param	pos	label position
	 */
	public void setLabelPosition(int pos) {
		if ((labelPosition < 0) || (labelPosition > 2))
			labelPosition = 1;
		else
			labelPosition = pos;
	}
	/**
	 *	Toggles percentage labels for this Pie, which are off by default
	 * @param	onOff	True for percentage labels
	 */
	public void setPercentLabelsOn(boolean onOff) {
		percentLabelsOn = onOff;
	}
	/**
	 *	Determines where the first Pie slice will begin.  All values are in
	 *	degrees, based on 0 at 3 o'clock
	 *
	 * @param	deg	degrees of rotation
	 */
	public void setStartDegrees(int deg) {
		if ((startDegrees >= 0) || (startDegrees <= 360))
			startDegrees = deg;
		while (startDegrees < 0)
			startDegrees += 360;
		while (startDegrees > 360)
			startDegrees -= 360;
	}
	/**
	 *	Toggles Datum labels for this Pie, which are off by default.
	 * @param	onOff	True for Datum labels
	 * @see javachart.chart.Datum
	 */
	public void setTextLabelsOn(boolean onOff) {
		textLabelsOn = onOff;
	}
	/**
	 *	Toggles numeric labels for this Pie, which are on by default.
	 * @param	onOff	True for value labels
	 */
	public void setValueLabelsOn(boolean onOff) {
		valueLabelsOn = onOff;
	}
	/**
	 *	Determines the Pie's relative width. Values range from 0 to 1.  Default
	 *	is 0.6, or 60% of the entire chart area.
	 * @param	w	Pie width
	 */
	public void setWidth(double w) {
		if (w < 1.0)
			width = w;
	}
	/**
	 *	Sets the X location of the center of this Pie.  Valid values range 
	 *	from 0 to 1.
	 * @param	loc	X location
	 */
	public void setXLoc(double loc) {
		if ((loc < 0.0) || (loc > 1.0))
			xLoc = 0.5;
		else
			xLoc = loc;
	}
	/**
	 *	Sets the Y location of the center of this Pie.  Valid values range 
	 *	from 0 to 1.
	 * @param	loc	Y location
	 */
	public void setYLoc(double loc) {
		if ((loc < 0.0) || (loc > 1.0))
			yLoc = 0.5;
		else
			yLoc = loc;
	}
}