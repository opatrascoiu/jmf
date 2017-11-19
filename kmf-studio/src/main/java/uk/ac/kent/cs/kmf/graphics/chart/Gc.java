/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.*;
import java.io.Serializable;

/**
 *	This is a general purpose graphics class for drawing functionality 
 *	and for storing graphical attributes.  Most other KavaChart classes 
 *	contain at least one Gc instance, which is used for storing things 
 *	like line color and fill color, as well as drawing methods.  
 *	Internally, KavaChart uses a "lower left" origin, which is more 
 *	natural to most chart styles than Java's "top left" origin.  The 
 *	Gc class converts all incoming coordinates into KavaChart's "lower 
 *	left" origin.
 *  @see	javachart.chart.RotateString
 *  @see	javachart.chart.Globals
 */

public class Gc implements Serializable {
	protected Color markerColor;
	protected int markerStyle = 1;
	protected int markerSize = 1;

	protected Color fillColor;
	protected transient Image image;

	protected Globals globals;
	protected Color lineColor;
	protected int lineStyle = 1;
	protected int lineWidth = 1;

	/**
	 *	A utility color - Gc.TRANSPARENT items will not be drawn
	 */
	public static Color TRANSPARENT = new Color(1, 1, 1);

	//labelling formats
	/**
	 *	default number format
	 */
	public final static int DEFAULT_FORMAT = 0;
	/**
	 *	American style numbers with commas and a decimal period (e.g. 1,000.00)
	 */
	public final static int COMMA_FORMAT = 1;
	/**
	 *	European style numbers with periods and a comma at the decimal point
	 *	(e.g. 1.000,00)
	 */
	public final static int EURO_FORMAT = 2;

	//used for unique bar/pie colors
	static int individualCount;

	//corresponds with axis side for convenience
	/**
	 *	string below given point
	 */
	public static final int keepBELOW = 0;
	/**
	 *	string to the left of given point
	 */
	public static final int keepLEFT = 1;
	/**
	 *	string above given point
	 */
	public static final int keepABOVE = 2;
	/**
	 *	string to the right of given point
	 */
	public static final int keepRIGHT = 3;

	public static Font defaultFont = new Font("TimesRoman", Font.PLAIN, 12);
	/**
	 *      Sets the fill, marker, and line color based on the dataset
	 *	index number.
	 *   @param        dataSetNumber   Dataset index number
	 *   @param        g   		This chart's Globals class
	 */
	public Gc(int dataSetNumber, Globals g) {
		globals = g;
		switch (dataSetNumber) {
			case 0 :
				assignColors(Color.blue);
				break;
			case 1 :
				assignColors(Color.red);
				break;
			case 2 :
				assignColors(Color.green);
				break;
			case 3 :
				assignColors(Color.cyan);
				break;
			case 4 :
				assignColors(Color.orange);
				break;
			case 5 :
				assignColors(Color.pink);
				break;
			case 6 :
				assignColors(Color.yellow);
				break;
			case 7 :
				assignColors(Color.magenta);
				break;
			case 8 :
				assignColors(Color.lightGray);
				break;
			case 9 :
				assignColors(Color.darkGray);
				break;
			case 10 :
				assignColors(Color.blue.darker());
				break;
			case 11 :
				assignColors(Color.red.darker());
				break;
			case 12 :
				assignColors(Color.green.darker());
				break;
			case 13 :
				assignColors(Color.cyan.darker());
				break;
			case 14 :
				assignColors(Color.orange.darker());
				break;
			case 15 :
				assignColors(Color.pink.darker());
				break;
			case 16 :
				assignColors(Color.yellow.darker());
				break;
			case 17 :
				assignColors(Color.magenta.darker());
				break;
			case 18 :
				assignColors(Color.lightGray.darker());
				break;
			case 19 :
				assignColors(Color.darkGray.darker());
				break;
			default :
				assignColors(randomColor());
				break;
		}
	}
	/**
	 *      Sets the fill, marker, and line color to a user specified color.
	 *      @param        clr     User's color
	 *      @param	g	This chart's Globals class
	 */
	public Gc(Color clr, Globals g) {
		globals = g;
		assignColors(clr);
	}
	/**
	 *      Sets the fill, marker, and line color to black.
	 *      @param        g	This chart's Globals class
	*/
	public Gc(Globals g) {
		globals = g;
		fillColor = Color.black;
		markerColor = Color.black;
		lineColor = Color.black;
	}
	/**
	 *      Sets the fill, marker, and line to a unique color.
	 *      @param        unique  True if color should be unique
	 *      @param        g  	This chart's Globals class
	 */
	public Gc(boolean unique, Globals g) {
		globals = g;
		if (!unique)
			return;
		individualCount++;
		switch (individualCount) {
			case 0 :
				assignColors(Color.blue);
				break;
			case 1 :
				assignColors(Color.red);
				break;
			case 2 :
				assignColors(Color.green);
				break;
			case 3 :
				assignColors(Color.cyan);
				break;
			case 4 :
				assignColors(Color.orange);
				break;
			case 5 :
				assignColors(Color.pink);
				break;
			case 6 :
				assignColors(Color.yellow);
				break;
			case 7 :
				assignColors(Color.magenta);
				break;
			case 8 :
				assignColors(Color.lightGray);
				break;
			case 9 :
				assignColors(Color.darkGray);
				break;
			case 10 :
				assignColors(Color.blue.darker());
				break;
			case 11 :
				assignColors(Color.red.darker());
				break;
			case 12 :
				assignColors(Color.green.darker());
				break;
			case 13 :
				assignColors(Color.cyan.darker());
				break;
			case 14 :
				assignColors(Color.orange.darker());
				break;
			case 15 :
				assignColors(Color.pink.darker());
				break;
			case 16 :
				assignColors(Color.yellow.darker());
				break;
			case 17 :
				assignColors(Color.magenta.darker());
				break;
			case 18 :
				assignColors(Color.lightGray.darker());
				break;
			case 19 :
				assignColors(Color.darkGray.darker());
				individualCount = 0;
				break;
			default :
				assignColors(randomColor());
		}
	}
	//utility functions
	private void assignColors(Color clr) {
		markerColor = clr;
		fillColor = clr;
		lineColor = clr;
	}
	/**
	 *      Draws an arc using the current fill color and KavaChart's coordinates.
	 *      @param        g       	The affected Graphics class
	 *      @param        center  	Center point
	 *      @param        heightWidth     Size of arc
	 *      @param        startAngle      Starting angle
	 *      @param        endAngle        Ending angle
	 */
	public void drawArc(
		Graphics g,
		Point center,
		Point heightWidth,
		int startAngle,
		int endAngle) {

		int urx, ury;

		if (fillColor == TRANSPARENT)
			return;
		urx = center.x - (heightWidth.x / 2);
		ury = globals.maxY - center.y - (heightWidth.y / 2);
		g.setColor(fillColor);
		g.drawArc(urx, ury, heightWidth.x, heightWidth.y, startAngle, endAngle);
	}

	public void drawDashedLine(
		Graphics g,
		int x1,
		int y1,
		int x2,
		int y2,
		double dashlength,
		double spacelength) {

		double linelength =
			Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		double yincrement =
			(y2 - y1) / (linelength / (dashlength + spacelength));
		double xincdashspace =
			(x2 - x1) / (linelength / (dashlength + spacelength));
		double yincdashspace =
			(y2 - y1) / (linelength / (dashlength + spacelength));
		double xincdash = (x2 - x1) / (linelength / (dashlength));
		double yincdash = (y2 - y1) / (linelength / (dashlength));

		int counter = 0;

		for (double i = 0;
			i < linelength - dashlength;
			i += dashlength + spacelength) {
			g.drawLine(
				(int) (x1 + xincdashspace * counter),
				(int) (y1 + yincdashspace * counter),
				(int) (x1 + xincdashspace * counter + xincdash),
				(int) (y1 + yincdashspace * counter + yincdash));
			counter++;
		}
		if ((dashlength + spacelength) * counter <= linelength)
			g.drawLine(
				(int) (x1 + xincdashspace * counter),
				(int) (y1 + yincdashspace * counter),
				x2,
				y2);

	}

	/**
	 *      Draw an image centered at a given point using Javachart's coordinate
	 *      system.
	 *      @param        g       The affected Graphics
	 *      @param        pt      Center of image
	 */
	public void drawImage(Graphics g, Point pt) {
		if (image != null) {
			g.drawImage(
				image,
				(pt.x - image.getWidth(null) / 2),
				globals.maxY - pt.y - image.getHeight(null) / 2,
				null);
		}
	}
	/**
	 *      A drawLine that uses KavaChart's coordinate system.
	 *      Uses the current lineColor.
	 *      @param        g       The affected Graphics class
	 *      @param        startx  X coordinate of starting point
	 *      @param        starty  Y coordinate of starting point
	 *      @param        endx    X coordinate of end point
	 *      @param        endy    Y coordinate of end point
	 */
	public void drawLine(
		Graphics g,
		int startx,
		int starty,
		int endx,
		int endy) {
		if (lineColor == TRANSPARENT)
			return;
		g.setColor(lineColor);
		if (lineWidth > 1) {
			Point[] pts = new Point[2];
			pts[0] = new Point(startx, starty);
			pts[1] = new Point(endx, endy);
			drawThickLine(g, pts, lineWidth, globals);
			return;
		}
		g.drawLine(startx, globals.maxY - starty, endx, globals.maxY - endy);
	}
	/**
	 *	Draws a line in the current line color using KavaChart's coordinates
	 *   @param	g	Graphics class to draw
	 *   @param	start	Start point
	 *   @param	end	End point
	 */
	public void drawLine(Graphics g, Point start, Point end) {
		if (lineColor == TRANSPARENT)
			return;
		g.setColor(lineColor);
		if (lineWidth < 2)
			g.drawLine(
				start.x,
				globals.maxY - start.y,
				end.x,
				globals.maxY - end.y);
		else {
			Point[] p = new Point[2];
			p[0] = start;
			p[1] = end;
			drawThickLine(g, p, lineWidth, globals);
		}
	}

	/**
	 *      Methods added to suit kiviat representation needs
	 *      Draw the kiviat circle  
	 */
	public void drawKiviatCircle(
		Graphics g,
		int xcenter,
		int ycenter,
		int circonference,
		Color user_color) {
		g.setColor(user_color);
		g.fillOval(
			xcenter - circonference,
			ycenter - circonference,
			circonference * 2,
			circonference * 2);
		g.setColor(Color.black);
		g.drawOval(
			xcenter - circonference,
			ycenter - circonference,
			circonference * 2,
			circonference * 2);

	}

	public void drawPolygon(Graphics g, Point pts[]) {
		int i;
		int xarr[];
		int yarr[];

		if (fillColor == TRANSPARENT)
			return;
		g.setColor(fillColor);
		xarr = new int[pts.length];
		yarr = new int[pts.length];
		for (i = 0; i < pts.length; i++) {
			xarr[i] = pts[i].x;
			yarr[i] = globals.maxY - pts[i].y;
		}
		g.fillPolygon(xarr, yarr, pts.length);
	}
	/**
	 *  Draws a polyline using the current lineColor.
	 *  @param        g       The affected graphics
	 *  @param        pts     Array of polyline's points
	 */
	public void drawPolyline(Graphics g, Point pts[]) {
		int i;

		if (lineColor == TRANSPARENT)
			return;
		g.setColor(lineColor);
		if (pts.length == 1) {
			return;
		}

		if (lineWidth > 1) {
			drawThickLine(g, pts, lineWidth, globals);
			return;
		}

		for (i = 1; i < pts.length; i++) {
			g.drawLine(
				pts[i - 1].x,
				globals.maxY - pts[i - 1].y,
				pts[i].x,
				globals.maxY - pts[i].y);
		}
	}
	/**
	 *	Draws a string in the current color and Font at the specified location,
	 *	position, and angle.  This method attempts to handle strings in a 
	 *	"smart" fashion, adjusting alignment points based on whether the 
	 *	string should remain above, below, to the left, or to the right of 
	 *	the specified location.  Strings with a rotation angle are aligned
	 *	with one of the corners of the string's extent box.  Strings with
	 *	an angle of 0 are centered at the location.
	 *
	 *  @param	g		Graphics affected
	 *  @param	startx		X position of string
	 *  @param	starty		Y position of string
	 *  @param	alignment	keepABOVE, keepBELOW, etc.
	 *  @param	angle		Rotation angle in degrees
	 *  @param	fm		A FontMetrics class for the unrotated string
	 *  @param	str		The String to draw
	*/
	public void drawSmartString(
		Graphics g,
		int startx,
		int starty,
		int alignment,
		int angle,
		FontMetrics fm,
		String str) {
		Rectangle extent;
		int adjX = 0, adjY = 0, adjAngle = 0;

		if (g.getColor() == TRANSPARENT)
			return;
		if ((str == null) || (str.length() == 0))
			return;
		//should work w/o rotation if stringRotator isn't initialized
		if (globals.stringRotator != null) {
			globals.stringRotator.setFont(g.getFont());
			globals.stringRotator.setColor(g.getColor());
		} else
			angle = 0;
		switch (alignment) {
			case keepBELOW :
				{
					if (angle == 0) {
						drawString(
							g,
							startx - (int) (fm.stringWidth(str) / 2),
							starty - fm.getMaxAscent(),
							str);
						return;
					}
					if ((angle < -90) || (angle > 90))
						adjAngle = 90;
					else
						adjAngle = angle;
					extent =
						globals.stringRotator.getExtent(
							str,
							startx,
							globals.maxY - starty,
							adjAngle,
							fm);
					if (adjAngle == 90) {
						adjX = startx + fm.getMaxAscent() / 2;
						adjY = starty - extent.height;
					} else if (adjAngle > 0) {
						adjX = startx - extent.width + fm.getMaxAscent();
						adjY = starty - extent.height;
					} else {
						adjX = startx - fm.getMaxAscent() / 2;
						adjY = starty - fm.getMaxAscent() / 2;
					}
					break;
				}
			case keepABOVE :
				{
					if (angle == 0) {
						drawString(
							g,
							startx - (int) (fm.stringWidth(str) / 2),
							starty,
							str);
						return;
					}
					if ((angle < -90) || (angle > 90))
						adjAngle = 90;
					else
						adjAngle = angle;
					extent =
						globals.stringRotator.getExtent(
							str,
							startx,
							globals.maxY - starty,
							adjAngle,
							fm);
					if (adjAngle == 90) {
						adjX = startx + fm.getMaxAscent() / 2;
						adjY = starty;
					}
					if (adjAngle == -90) {
						adjX = startx - fm.getMaxAscent() / 2;
						adjY = starty + extent.height;
					} else if (angle > 0) {
						adjX = startx;
						adjY = starty;
					} else {
						adjX = startx - extent.width + fm.getMaxAscent() / 2;
						adjY = starty + extent.height - fm.getMaxAscent() / 2;
					}
					break;
				}
			case keepRIGHT :
				{
					if (angle == 0) {
						drawString(g, startx, starty - fm.getHeight() / 2, str);
						return;
					}
					if ((angle < -90) || (angle > 90))
						adjAngle = 90;
					else
						adjAngle = angle;
					extent =
						globals.stringRotator.getExtent(
							str,
							startx,
							globals.maxY - starty,
							adjAngle,
							fm);
					if (angle > 0)
						adjX = startx + fm.getMaxAscent();
					else
						adjX = startx;
					if (angle == 90)
						adjY = starty - fm.stringWidth(str) / 2;
					else if (angle == -90)
						adjY = starty + fm.stringWidth(str) / 2;
					else
						adjY = starty;
					break;
				}
			case keepLEFT :
				{
					if (angle == 0) {
						drawString(
							g,
							startx - fm.stringWidth(str),
							starty - fm.getHeight() / 2,
							str);
						return;
					}
					if ((angle < -90) || (angle > 90))
						adjAngle = 90;
					else
						adjAngle = angle;
					extent =
						globals.stringRotator.getExtent(
							str,
							startx,
							globals.maxY - starty,
							adjAngle,
							fm);
					adjX = startx - extent.width;
					if (angle == 90)
						adjY = starty - fm.stringWidth(str) / 2;
					else if (angle == -90)
						adjY = starty + fm.stringWidth(str) / 2;
					else if (angle > 0)
						adjY = starty - extent.height + fm.getMaxAscent() / 2;
					else
						adjY = starty + extent.height - fm.getMaxAscent() / 2;
					break;
				}
			default :
				return;
		}

		globals.stringRotator.drawString(
			str,
			adjX,
			globals.maxY - adjY,
			adjAngle,
			globals.image);
	}
	/**
	 *  A drawString that uses KavaChart's coordinate system.
	 *  Also handles null-pointer exceptions.
	 *  @param        g       The affected Graphics
	 *  @param        startx  X coordinate of starting point
	 *  @param        starty  Y coordinate of starting point
	 *  @param        str     The string to be drawn
	 */
	public void drawString(Graphics g, int startx, int starty, String str) {
		if (g.getColor() == TRANSPARENT)
			return;
		try {
			g.drawString(str, startx, globals.maxY - starty);
		} catch (NullPointerException e) {
			//nothing
		}
	}
	private static void drawThickLine(
		Graphics g,
		Point[] pts,
		int lw,
		Globals gl) {

		if (pts.length == 0)
			return;
		double[] xVals = new double[pts.length];
		double[] yVals = new double[pts.length];
		double width = (double) lw;
		for (int i = 0; i < pts.length; i++) {
			xVals[i] = (double) pts[i].x;
			yVals[i] = (double) (gl.maxY - pts[i].y);
		}
		double[] xFactor = new double[xVals.length - 1];
		double[] yFactor = new double[xVals.length - 1];
		for (int i = 1; i < xVals.length; i++) {
			double atan =
				Math.atan2(xVals[i] - xVals[i - 1], yVals[i] - yVals[i - 1]);
			xFactor[i - 1] = Math.cos(atan);
			yFactor[i - 1] = Math.sin(atan);
		}

		int[] xRect = new int[4];
		int[] yRect = new int[4];
		int endTopX, endTopY, endBottomX, endBottomY;

		for (int i = 1; i < xVals.length; i++) {
			xRect[0] = (int) (xVals[i - 1] + width * xFactor[i - 1]);
			yRect[0] = (int) (yVals[i - 1] - width * yFactor[i - 1]);
			xRect[1] = (int) (xVals[i] + width * xFactor[i - 1]);
			yRect[1] = (int) (yVals[i] - width * yFactor[i - 1]);
			xRect[2] = (int) (xVals[i] - width * xFactor[i - 1]);
			yRect[2] = (int) (yVals[i] + width * yFactor[i - 1]);
			xRect[3] = (int) (xVals[i - 1] - width * xFactor[i - 1]);
			yRect[3] = (int) (yVals[i - 1] + width * yFactor[i - 1]);
			g.fillPolygon(xRect, yRect, 4);
		}

		for (int i = 1; i < xVals.length - 1; i++)
			g.fillOval(
				(int) (xVals[i] - width),
				(int) (yVals[i] - width),
				(int) width * 2,
				(int) width * 2);
		//bug in oval routine???
		//control line
		/**
		g.setColor(Color.red);
		for(int i=1;i<xVals.length;i++){
			g.drawLine((int)xVals[i-1], (int)yVals[i-1], (int)xVals[i], (int)yVals[i]);
		}
		*/

	}
	/**
	 *  Fills an Arc in the current fill color from a center point
	 *  @param        g       	The affected Graphics
	 *  @param        center  	Center point
	 *  @param        heightWidth     Size of arc
	 *  @param        startAngle      Starting angle
	 *  @param        endAngle        Ending angle
	 */
	public void fillArc(
		Graphics g,
		Point center,
		Point heightWidth,
		int startAngle,
		int endAngle) {

		int urx, ury;

		if (fillColor == TRANSPARENT)
			return;
		urx = center.x - (heightWidth.x / 2);
		ury = globals.maxY - center.y - (heightWidth.y / 2);
		g.setColor(fillColor);
		g.fillArc(urx, ury, heightWidth.x, heightWidth.y, startAngle, endAngle);
	}
	/**
   	 *	Fills a rectangle in the current fill color
	 *  @param	g	Graphics class to draw
	 *  @param	ll	lower left corner
	 *  @param	width	rectangle width
	 *  @param	height	rectangle height
	 */
	public void fillRect(Graphics g, Point ll, int width, int height) {
		if (fillColor == TRANSPARENT)
			return;
		g.setColor(fillColor);
		g.fillRect(ll.x, ll.y, width, height);
	}
	//graphics methods
	/**
	 *	Fills a rectangle with the current fill color
	 *  @param	g	Graphics class to draw
	 *  @param	ll	lower left corner of rectangle
	 *  @param	ur	upper right corner of rectangle
	*/
	public void fillRect(Graphics g, Point ll, Point ur) {
		if (fillColor == TRANSPARENT)
			return;
		int llx, urx;
		if (ll.x > ur.x) {
			llx = ur.x;
			urx = ll.x;
		} else {
			llx = ll.x;
			urx = ur.x;
		}
		g.setColor(fillColor);
		if (ur.y > ll.y)
			g.fillRect(llx, globals.maxY - ur.y, urx - llx, ur.y - ll.y);
		else
			g.fillRect(llx, globals.maxY - ll.y, urx - llx, ll.y - ur.y);
	}
	/**
	 *	Formats a numeric string into one with commas and periods, according
	 *	to the specified format
	 *  @param	s	Starting string
	 *  @param	format	DEFAULT_FORMAT, COMMA_FORMAT, or EURO_FORMAT
	 *  @return	a formatted string
	 *  @deprecated replaced by much more effective Format classes
	*/
	public static String formattedLabel(
		String s,
		int format,
		int labelPrecision) {
		char comma, dot, newChars[], oldChars[];
		int decimalLocation, firstChar, i, j;
		//truncate to label precision
		i = s.lastIndexOf(".");
		if (i != -1) {
			int length = s.length();
			//strip off trailing ".0" in java 1.1
			if ((labelPrecision == 0)
				|| ((i == length - 2) && (s.charAt(length - 1) == '0')))
				s = s.substring(0, i);
			else if (length > 1 + i + labelPrecision)
				s = s.substring(0, i + 1 + labelPrecision);
		}

		if (format == DEFAULT_FORMAT)
			return s;

		if (format == COMMA_FORMAT) {
			comma = ',';
			dot = '.';
		} else {
			comma = '.';
			dot = ',';
		}

		decimalLocation = s.indexOf('.');
		if (decimalLocation == -1)
			decimalLocation = s.length();
		else if (format == EURO_FORMAT)
			s = s.replace('.', ',');

		if ((decimalLocation % 3) != 0)
			newChars = new char[s.length() + (decimalLocation / 3)];
		else
			newChars = new char[s.length() + (decimalLocation / 3) - 1];
		oldChars = s.toCharArray();

		for (i = oldChars.length - 1, j = newChars.length - 1;
			i >= decimalLocation;
			i--) {
			newChars[j] = oldChars[i];
			j--;
		}

		if (oldChars[0] == '-') {
			firstChar = 1;
		} else
			firstChar = 0;

		for (j = 0, i = 0; i < decimalLocation; i++, j++) {
			if ((decimalLocation - i) % 3 == 0) {
				if ((j > firstChar) && (i < decimalLocation)) {
					newChars[j] = comma;
					j++;
				}
			}
			newChars[j] = oldChars[i];
		}
		return new String(newChars);
	}
	/**
	 * Returns the current fillColor.
	 * @return       Fill color
	 */
	public Color getFillColor() {
		return fillColor;
	}
	/**
	 *  Returns the Image used with this Gc.  For Line and Regress charts
	 *  this Image is used for data markers.  For Bar and Column charts, this
	 *  Image is the tile for the data bars.
	 *	Currently not implemented for Bar and Colum charts.
	 *  @return       Marker image
	 */
	public Image getImage() {
		return image;
	}
	/**
	 *  Returns the current lineColor.
	 *  @return       Line color
	 */
	public Color getLineColor() {
		return lineColor;
	}
	/**
	 *  Returns the current lineStyle.
	 *	Currently not implemented
	 *  @return       Line style
	 */
	public int getLineStyle() {
		return lineStyle;
	}
	/**
	 *  Returns the current lineWidth
	 *	This integer value is the number of pixels wide this Gc will draw lines.
	 *  @return       Line width
	*/
	public int getLineWidth() {
		return lineWidth;
	}
	/**
	 *  Returns the marker color.
	 *	Currently not implemented
	 *  @return       Marker color
	*/
	public Color getMarkerColor() {
		return markerColor;
	}
	/**
	 *  Returns the marker size.
	 *	Currently not implemented
	 *  @return       Marker size
	 */
	public int getMarkerSize() {
		return markerSize;
	}
	/**
	 *  Returns the marker style.
	 *	Currently not implemented
	 *  @return       Marker style
	 */
	public int getMarkerStyle() {
		return markerStyle;
	}
	/**
	 *	Converts a numeric String that contains scientific notation into
	 *	a String without scientific notation
	 *  @param	s	String to convert
	 *  @param	eIndex	location of the "e" in this string
	 *  @return	expanded number
	 *  @deprecated replaced by much more effective Format classes
	 */
	public static String nonSciNumberStr(String s, int eIndex) {
		int i, mantissa, decimalLocation;
		String trimmedString;
		String zeroes = "";

		i = s.indexOf('+');
		if (i == -1)
			mantissa = Integer.parseInt(s.substring(eIndex + 1));
		else
			mantissa = Integer.parseInt(s.substring(i + 1));
		trimmedString = (s.substring(0, eIndex)).trim();
		decimalLocation = trimmedString.indexOf('.');
		if (decimalLocation == -1) {
			if (mantissa > 0)
				i = mantissa;
			else
				i = -mantissa;
		} else {
			if (mantissa > 0)
				i = mantissa - (trimmedString.length() - decimalLocation) + 1;
			else
				i = -mantissa - decimalLocation;
		}
		if (i > 0) {
			for (int j=0; j<i; j++) zeroes += "0";			
		}

		if (decimalLocation == -1)
			if (mantissa < 0)
				return ("0." + zeroes.toString() + trimmedString);
			else
				return (trimmedString + zeroes);
		else //take out decimal point
			if (mantissa < 0)
				return (
					"0."
						+ zeroes
						+ trimmedString.substring(0, decimalLocation)
						+ trimmedString.substring(decimalLocation + 1));
			else if (zeroes != null)
				return (
					trimmedString.substring(0, decimalLocation)
						+ trimmedString.substring(decimalLocation + 1)
						+ zeroes);
			else {
				//i < 0, zeroes is null, number looks like this 2.345E2, which needs to become 234.5
				int newDecimalLoc = trimmedString.length() + i;
				return (
					trimmedString.substring(0, decimalLocation)
						+ trimmedString.substring(
							decimalLocation + 1,
							newDecimalLoc)
						+ '.'
						+ trimmedString.substring(newDecimalLoc));
			}
	}
	private Color randomColor() {
		float r, g, b;
		r = (float) Math.random();
		g = (float) Math.random();
		b = (float) Math.random();
		return new Color(r, g, b);
	}
	/**
	 *  Sets the current fillColor.
	 *  @param        c       Fill color
	 */
	public void setFillColor(Color c) {
		fillColor = c;
	}
	/**
	 *  Assigns an Image for use with this Gc.  For Line and Regress charts this
	 *  Image is used to create data markers.  For Bar and Column charts, this
	 *  Image is used as a tile for the data bars.
	 *	Currently not implemented for Bar and Colum charts.
	 *  @param        i       Marker image
	 */
	public void setImage(Image i) {
		image = i;
	}
	/**
	 *  Sets the current lineColor.
	 *  @param        c       Line color
	 */
	public void setLineColor(Color c) {
		lineColor = c;
	}
	/**
	 *  Sets the current lineStyle.
	 *	Currently not implemented
	 *  @param        i       Line style
	 */
	public void setLineStyle(int i) {
		lineStyle = i;
	}
	/**
	 *  Sets the current lineWidth
	 *	Sets the number of pixels wide for this Gc's lines and polylines.
	 *  @param        i       Line width
	*/
	public void setLineWidth(int i) {
		lineWidth = i;
	}
	/**
	 *  Sets the marker color.
	 *	Currently not implemented
	 *  @param        c       Marker color
	*/
	public void setMarkerColor(Color c) {
		markerColor = c;
	}
	/**
	 *  Sets the marker size
	 *	Currently not implemented
	 *  @param        i       Marker size
	*/
	public void setMarkerSize(int i) {
		markerSize = i;
	}
	/**
	 *  Sets the marker style
	 *	Currently not implemented
	 *  @param        i       Marker style
	*/
	public void setMarkerStyle(int i) {
		markerStyle = i;
	}
}