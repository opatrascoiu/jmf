/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.io.Serializable;

/**
 *	Creates a legend appropriate for various kinds of bar charts.  
 *	Useful methods for subclassing include recalculateSize, which 
 *	recomputes upper right coordinates based on the Legend's datasets 
 *	and dataset names and various settings for iconWidth, etc.  Legend 
 *	subclasses generally override the methods doVerticalIcons or 
 *	doHorizontalIcons to change the Legend's behavior.
 *@see	javachart.chart.LegendInterface
 *@see	javachart.chart.LineLegend
 *@see	javachart.chart.PieLegend
 */

public class Legend implements LegendInterface, Serializable {
	//package vars
	Globals globals;
	protected Gc backgroundGc = new Gc(Color.white, null);
	protected Font labelFont = Gc.defaultFont;
	protected Color labelColor = Color.black;
	protected boolean verticalLayout = false;
	protected boolean backgroundVisible = true;
	protected double llX = 0.0, llY = 0.0;
	protected double urX, urY;

	protected double iconHeight = 0.05;
	protected double iconWidth = 0.07;
	protected double iconGap = 0.02;
	protected boolean useDisplayList = true;

	//utility vars
	protected Dataset datasets[];
	protected Transform transform;
	int gWidth = 640;
	int gHeight = 480;
	//static final double backgroundFudge = 0.02;

	/**
	 * Creates a Legend with unassigned Datasets and Globals.
	 */
	public Legend() {
	}
	/**
	 *	Constructs a Legend for the specified array of Dataset classes
	 *
	 * @param	d	Array of Dataset classes to consider
	 * @param	g	This chart's Globals class
	 */
	public Legend(Dataset d[], Globals g) {
		backgroundGc.globals = g;
		globals = g;
		datasets = d;
	}
	/**
	 * Internal method to calcualte one dataset cell's height.
	 * @return int
	 */
	protected int cellHeight() {
		return (int) ((double) globals.maxY * (iconHeight + iconGap + iconGap));
	}
	/**
	 * internal method to calculate one dataset entry size.
	 * @return int
	 */
	protected int cellWidth(int numDataSets, FontMetrics fm) {
		int maxStringWidth = 0;
		for (int i = 0; i < numDataSets; i++) {
			int wid = fm.stringWidth(datasets[i].setName);
			if (wid > maxStringWidth)
				maxStringWidth = wid;
		}
		return maxStringWidth
			+ (int) ((double) globals.maxX * (iconWidth + iconGap * 2));
	}
	//utility routines
	synchronized int datasetsInUse() {
		int i;
		for (i = 0; i < datasets.length; i++)
			if (datasets[i] == null)
				return i;
		return i;
	}
	/**
	 *	Draws Legend icons and labels horizontally on the specified Graphics
	 * @param	g	Graphics class to draw
	 */
	protected synchronized void doHorizontalIcons(Graphics g) {
		int numDatasets = datasetsInUse();
		g.setFont(labelFont);
		FontMetrics fm = g.getFontMetrics();
		int cellWidth = cellWidth(numDatasets, fm);

		int pIconWidth = (int) (iconWidth * (double) gWidth);
		int pIconHeight = (int) (iconHeight * (double) gHeight);
		int pFudge = (int) (iconGap * (double) gWidth);

		Point fromPoint = transform.point(llX + iconGap, llY + iconGap);
		Point toPoint =
			new Point(fromPoint.x + pIconWidth, fromPoint.y + pIconHeight);
		int startX = fromPoint.x;
		int numColumns = (globals.maxX - fromPoint.x + pFudge) / cellWidth;
		if (numColumns < 1)
			numColumns = 1;
		int numLines = numDatasets / numColumns;
		if (numDatasets % numColumns == 0)
			numLines--;
		int shiftUp = (numLines) * cellHeight();
		fromPoint.translate(0, shiftUp);
		toPoint.translate(0, shiftUp);

		int rowNum = 0;
		for (int i = 0; i < numDatasets; i++) {

			if (fromPoint.x - pFudge + cellWidth > globals.maxX) {
				//do another row
				fromPoint.x = startX;
				toPoint.x = startX + pIconWidth;
				fromPoint.translate(0, - (cellHeight()));
				toPoint.translate(0, - (cellHeight()));
			}

			datasets[i].gc.fillRect(g, fromPoint, toPoint);
			if (useDisplayList && globals.useDisplayList)
				globals.displayList.addRectangle(
					datasets[i],
					fromPoint,
					toPoint);
			g.setColor(labelColor);
			backgroundGc.drawString(
				g,
				toPoint.x + pFudge,
				fromPoint.y,
				datasets[i].setName);
			fromPoint.translate(cellWidth, 0);
			toPoint.translate(cellWidth, 0);
		}
	}
	/**
	 *	Draws Legend icons and labels vertically on the specified Graphics
	 * @param	g	Graphics to draw
	 */
	protected synchronized void doVerticalIcons(Graphics g) {

		g.setFont(labelFont);
		FontMetrics fm = g.getFontMetrics();
		int numDatasets = datasetsInUse();
		int cellWidth = cellWidth(numDatasets, fm);
		int cellHeight = cellHeight();

		int pIconWidth = (int) (iconWidth * (double) gWidth);
		int pIconHeight = (int) (iconHeight * (double) gHeight);
		int pFudge = (int) (iconGap * (double) gWidth);

		Point fromPoint = transform.point(llX + iconGap, llY + iconGap);
		Point toPoint =
			new Point(fromPoint.x + pIconWidth, fromPoint.y + pIconHeight);
		int startY = fromPoint.y;

		Point pixSize =
			new Point(toPoint.x - fromPoint.x, toPoint.y - fromPoint.y);
		for (int i = 0; i < numDatasets; i++) {
			datasets[i].gc.fillRect(g, fromPoint, toPoint);
			if (useDisplayList && globals.useDisplayList)
				globals.displayList.addRectangle(
					(Object) datasets[i],
					fromPoint,
					toPoint);
			g.setColor(labelColor);
			backgroundGc.drawString(
				g,
				toPoint.x + 2,
				fromPoint.y,
				datasets[i].setName);
			fromPoint.translate(0, cellHeight);
			toPoint.translate(0, cellHeight);
			//create another column
			if (toPoint.y > globals.maxY) {
				fromPoint.y = startY;
				toPoint.y = fromPoint.y + pIconHeight;
				fromPoint.translate(cellWidth, 0);
				toPoint.translate(cellWidth, 0);
			}
		}
	}
	/**
	 *	Draws a Legend on the specified Graphics class
	 *
	 * @param	g	Graphics class to draw on
	 */
	public void draw(Graphics g) {
		if (g == null)
			return;
		recalculateSize(g);
		if (backgroundVisible) {
			backgroundGc.fillRect(
				g,
				transform.point(llX, llY),
				transform.point(urX, urY));
			if (useDisplayList && globals.useDisplayList) {
				globals.displayList.addRectangle(
					(Object) this,
					transform.point(llX, llY),
					transform.point(urX, urY));
			}
		}
		if (verticalLayout)
			doVerticalIcons(g);
		else
			doHorizontalIcons(g);
	}
	/**
	 *      Returns the Gc used by the Legend background
	 *@return       Current background Gc
	 */
	public Gc getBackgroundGc() {
		return backgroundGc;
	}
	/**
	 *      Returns the Legend's background visibility status.
	 *@return       Background visibility status
	 */
	public boolean getBackgroundVisible() {
		return backgroundVisible;
	}
	/**
	 *      Returns the current gap between two icons, or between an icon and the
	 *	subsequent text string (default 0.01).
	 *@return       Current gap between icons
	 */
	public double getIconGap() {
		return iconGap;
	}
	/**
	 *      Returns the current icon height (default 0.05).
	 *@return       Current icon height
	 */
	public double getIconHeight() {
		return iconHeight;
	}
	/**
	 *      Returns the current icon width (default 0.07)
	 *@return       Current icon width
	 */
	public double getIconWidth() {
		return iconWidth;
	}
	/**
	 *      Returns the current label color.
	 *@return       Current label color
	 */
	public Color getLabelColor() {
		return labelColor;
	}
	/**
	 *      Returns the current label font.
	 *@return       Current label font
	 */
	public Font getLabelFont() {
		return labelFont;
	}
	/**
	 *      Returns the current lower-left X coordinate.
	 *@return       Lower-left X coordinate
	 */
	public double getLlX() {
		return llX;
	}
	/**
	 *      Returns the current lower-left Y coordinate.
	 *@return       Current lower-left Y coordinate
	 */
	public double getLlY() {
		return llY;
	}
	/**
	 *      Returns the current upper-right X coordinate.
	 *@return       Current upper-right X coordinate
	 */
	public double getUrX() {
		return urX;
	}
	/**
	 *      Returns the current upper-right Y coordinate.
	 *@return       Current upper-right Y coordinate
	 */
	public double getUrY() {
		return urY;
	}
	//Accessors
	/**
	 *      Returns true if the Legend will add objects to this chart's DisplayList
	 *@return       Display list status
	 */
	public boolean getUseDisplayList() {
		return useDisplayList;
	}
	/**
	 *      Returns the legend layout.  True for vertical, and false for
	 *      horizontal.
	 *return        If the legend is vertical
	 */
	public boolean getVerticalLayout() {
		return verticalLayout;
	}
	/**
	 *	Recalculates the legend size (upper right corner) based on the current
	 *	Dataset information and canvas size
	 *
	 * @param	g	Target Graphics class
	 */
	public synchronized void recalculateSize(Graphics g) {

		g.setFont(labelFont);
		FontMetrics fm = g.getFontMetrics();
		int numDatasets = datasetsInUse();
		int cellWidth = cellWidth(numDatasets, fm);
		int cellHeight = cellHeight();
		int nRows = 0, nColumns = 0;

		if (verticalLayout) {
			int vPixAvail = (int) ((double) globals.maxY * (1.0 - llY));
			nRows = vPixAvail / cellHeight;
			if (nRows > numDatasets)
				nRows = numDatasets;
			if (nRows == 0)
				nRows = 1;
			nColumns = numDatasets / nRows;
			if (numDatasets % nRows != 0)
				nColumns++;
		} else { //still need to fix horizontalLayout
			int hPixAvail = (int) ((double) globals.maxX * (1.0 - llX));
			nColumns = hPixAvail / cellWidth;
			if (nColumns > numDatasets)
				nColumns = numDatasets;
			if (nColumns == 0)
				nColumns = 1;
			nRows = numDatasets / nColumns;
			if (numDatasets % nColumns != 0)
				nRows++;
		}
		if (nRows == 0)
			nRows = 1;
		if (nColumns == 0)
			nColumns = 1;
		urX =
			llX
				+ iconGap
				+ ((double) nColumns
					* (double) cellWidth
					/ (double) globals.maxX);
		urY =
			llY
				+ iconGap
				+ ((double) nRows * (double) cellHeight / (double) globals.maxY);
	}
	/**
	 *	Resizes the Legend's transform to account for changes in the overall
	 *	drawing canvas.  Note: this does not directly change the size of the
	 *	Legend, which is sized with the methods llX, llY, urX and urY.
	 *
	 * @param	w	Width of drawing canvas
	 * @param	h	Height of drawing canvas
	 */
	public void resize(int w, int h) {
		gWidth = w;
		gHeight = h;

		transform =
			new Transform(
				0.0,
				0.0,
				1.0,
				1.0,
				0,
				0,
				(int) (gWidth),
				(int) (gHeight));
	}
	/**
	 *      Installs a new Gc for use in drawing Legend backgrounds
	 *@param        g       A new background Gc
	 */
	public void setBackgroundGC(Gc g) {
		backgroundGc = g;
		g.globals = globals;
	}
	/**
	 *      Change the background visibility status.
	 *@param        vis     New visibility status
	 */
	public void setBackgroundVisible(boolean vis) {
		backgroundVisible = vis;
	}
	/**
	 *      Sets gap between icons to a new size.
	 *@param        d       New gap between icons
	 */
	public void setIconGap(double d) {
		iconGap = d;
	}
	/**
	 *      Sets icon height to a new size.
	 *@param        d       New icon height
	 */
	public void setIconHeight(double d) {
		iconHeight = d;
	}
	/**
	 *      Sets icon width to a new size.
	 *@param        d       New icon width
	 */
	public void setIconWidth(double d) {
		iconWidth = d;
	}
	/**
	 *      Use a new label color.
	 *@param        c       A new label color
	 */
	public void setLabelColor(Color c) {
		labelColor = c;
	}
	/**
	 *      Use a new label font.
	 *@param        f       A new label font
	 */
	public void setLabelFont(Font f) {
		labelFont = f;
	}
	/**
	 *      Use a new lower-left X coordinate.
	 *@param        d       A new lower-left X coordinate
	 */
	public void setLlX(double d) {
		llX = d;
	}
	/**
	 *      Use a new lower-left Y coordinate.
	 *@param        d       A new lower-left Y coordinate
	 */
	public void setLlY(double d) {
		llY = d;
	}
	/**
	 *      By default, the chart's DisplayList will accumulate Legend objects
	 *	if the DisplayList is active.  Use this method to prevent Legend
	 *	objects from accumulating into the DisplayList.
	 *
	 *@param        onOff   False if you wish to eliminate Legend information
	 *@see		javachart.chart.DisplayList
	 */
	public void setUseDisplayList(boolean onOff) {
		useDisplayList = onOff;
	}
	/**
	 *      Sets the legend layout.  True for vertical, and false for
	 *      horizontal.
	 *@param        trueFalse       New legend layout
	 */
	public void setVerticalLayout(boolean trueFalse) {
		verticalLayout = trueFalse;
	}
	public String toString() {
		return getClass().getName()
			+ "["
			+ "urX "
			+ urX
			+ "urY "
			+ urY
			+ "llX "
			+ llX
			+ "llY "
			+ llY
			+ "]";
	}
}