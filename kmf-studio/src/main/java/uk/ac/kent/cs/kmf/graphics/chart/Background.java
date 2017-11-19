/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.io.Serializable;

/**
 *	This simple class draws a rectangle over the background of the 
 *	drawing surface.  By subclassing Background, you can create 
 *	transparent backgrounds, bevelled edges or other special effects.
 *
 *	Background also draws a chart Title, centered at the top of the 
 *	Bacground rectangle.
 *@see	javachart.chart.Chart
 *@see	javachart.chart.Plotarea
 */

public class Background implements Serializable {

	//package vars
	protected Gc gc = new Gc(Color.white, null);
	protected Color titleColor = Color.black;
	protected Font titleFont = Gc.defaultFont;
	protected String titleString = null;
	protected Color subTitleColor = Color.black;
	protected Font subTitleFont = Gc.defaultFont;
	protected String subTitleString = null;

	protected boolean useDisplayList = true;
	protected Globals globals;

	//utility vars
	Point startPoint = new Point(0, 0);

	/**
	 * Constructs a new Background without assigning Globals.
	 */
	public Background() {
	}
	/**
	 *      Constructs a new Background with a default white fill color.
	 *	The initial title font is TimesRoman, point size 12, color black.
	 *@param        g	This chart's Global class
	 */
	public Background(Globals g) {
		globals = g;
		gc.globals = g;
	}
	/**
	 *      Draw the chart background.  Also draws
	 *      the title and subtitle if set.
	 *@param        g       Graphic class to draw on
	 */
	public synchronized void draw(Graphics g) {
		FontMetrics fm;
		int startX, startY = 7;

		if (g == null) {
			System.out.println("null graphics in background");
			return;
		}
		gc.fillRect(g, startPoint, globals.maxX, globals.maxY);
		if (useDisplayList && globals.useDisplayList)
			globals.displayList.addRectangle(
				(Object) this,
				startPoint,
				new Point(globals.maxX, globals.maxY));

		if (gc.image != null) {
			g.drawImage(gc.image, 0, 0, null);
		}

		if (titleString != null) {
			g.setFont(titleFont);
			g.setColor(titleColor);
			fm = g.getFontMetrics();

			startX =
				((globals.maxX - startPoint.x) / 2)
					- (fm.stringWidth(titleString) / 2);
			startY = fm.getMaxAscent() + 10; /* add some fudge */
			g.drawString(titleString, startX, startY);
			if (useDisplayList && globals.useDisplayList)
				globals.displayList.addTextString(
					(Object) this,
					startX,
					startY,
					titleString,
					fm);
		}

		if (subTitleString != null) {
			g.setFont(subTitleFont);
			g.setColor(subTitleColor);
			fm = g.getFontMetrics();

			startX =
				((globals.maxX - startPoint.x) / 2)
					- (fm.stringWidth(subTitleString) / 2);
			startY = startY + fm.getMaxAscent() + 3; /* add some fudge */
			g.drawString(subTitleString, startX, startY);
			if (useDisplayList && globals.useDisplayList)
				globals.displayList.addTextString(
					(Object) this,
					startX,
					startY,
					subTitleString,
					fm);
		}
	}
	//Accessor methods
	/**
	 *      Returns the Background's Gc class
	 *@return       Current Gc class
	 */
	public Gc getGc() {
		return gc;
	}
	/**
	 *      Returns the current subtitle color.
	 *@return       Current subtitle color
	 */
	public Color getSubTitleColor() {
		return subTitleColor;
	}
	/**
	 *      Returns the current subtitle font.
	 *@return       Current subtitle font
	 */
	public Font getSubTitleFont() {
		return subTitleFont;
	}
	/**
	 *      Returns the current subtitle string.
	 *@return       Current subTitle string
	 */
	public String getSubTitleString() {
		return subTitleString;
	}
	/**
	 *      Returns the current title color.
	 *@return       Current title color
	 */
	public Color getTitleColor() {
		return titleColor;
	}
	/**
	 *      Returns the current title font.
	 *@return       Current title font
	 */
	public Font getTitleFont() {
		return titleFont;
	}
	/**
	 *      Returns the current title string.
	 *@return       Current title string
	 */
	public String getTitleString() {
		return titleString;
	}
	/**
	 *      Returns true if Background objects will appear in global DisplayList
	 *@return       current status
	 */
	public boolean getUseDisplayList() {
		return useDisplayList;
	}
	/**
	 *      Resizes the background.  Usually called automatically by the Chart
	 *	class containing this Background.
	 *@param        w       New width
	 *@param        h       New height
	 *@see       	javachart.chart.Chart
	 */
	protected synchronized void resize(int w, int h) {
		//vestigal
	}
	/**
	 *      Use a new Gc for this Background
	 *@param        g       New Gc
	 */
	public void setGc(Gc g) {
		gc = g;
		gc.globals = globals;
	}
	/**
	 *      Set the subtitle color.
	 *@param        c       A new subtitle color
	 */
	public void setSubTitleColor(Color c) {
		subTitleColor = c;
	}
	/**
	 *      Use a new subtitle font.
	 *@param        f       A new subtitle font
	 */
	public void setSubTitleFont(Font f) {
		subTitleFont = f;
	}
	/**
	 *      Use a new subtitle string.
	 *@param        s       A new subtitle string
	 */
	public void setSubTitleString(String s) {
		subTitleString = s;
	}
	/**
	 *      Set the title color.
	 *@param        c       A new title color
	 */
	public void setTitleColor(Color c) {
		titleColor = c;
	}
	/**
	 *      Use a new title font.
	 *@param        f       A new title font
	 */
	public void setTitleFont(Font f) {
		titleFont = f;
	}
	/**
	 *      Use a new title string.
	 *@param        s       A new title string
	 */
	public void setTitleString(String s) {
		titleString = s;
	}
	/**
	 *      Determines whether Background objects should be added to global 
	 *	DisplayList.  Default is true.
	 *@param        New option of display list usage
	 *@see		javachart.chart.DisplayList
	 *@see		javachart.chart.Globals
	 *@see		javachart.chart.Chart
	 */
	public void setUseDisplayList(boolean onOff) {
		useDisplayList = onOff;
	}
}