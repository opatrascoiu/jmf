/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.FontMetrics;

/**
 *      Creates a legend appropriate for various kinds of Pie charts.
 *      Useful methods for subclassing include recalculateSize, which
 *      recomputes upper right coordinates based on the Legend's datasets
 *      and dataset names and various settings for iconWidth, etc.  By
 *      overriding the methods doVerticalIcons or doHorizontalIcons
 *      this legend draws icons for each Datum rather than each dataset.
 *@see  javachart.chart.LegendInterface
 *@see  javachart.chart.LineLegend
 *@see  javachart.chart.Legend
 */

public class PieLegend 
	extends Legend 
	implements LegendInterface 
{
	protected Dataset dataset;

	/**
	 * Creates a new PieLegend without assigning Datasets and Globals.
	 */
	public PieLegend() {
	}
	/**
	 *      Constructs a Legend using only the first element from the specified
	 *	array of Dataset classes
	 *
	 * @param       d       Array of Dataset classes to consider
	 * @param       g       This chart's Globals class
	 */
	public PieLegend(Dataset d[], Globals g) {
		super(d, g);
		datasets = d;
	}
	/**
	 * Overrides method in Legend to examine Datum classes rather than Datasets.
	 * @return int
	 * @param fm java.awt.FontMetrics
	 * @param numPoints int
	 */
	protected int cellWidth(int numPoints, FontMetrics fm) {
		int maxStringWidth = 0;
		for (int i = 0; i < numPoints; i++) {
			int wid =
				fm.stringWidth(datasets[0].getDataElementAt(i).getString());
			if (wid > maxStringWidth)
				maxStringWidth = wid;
		}
		return maxStringWidth
			+ (int) ((double) globals.maxX * (iconWidth + iconGap * 2));
	}
	/**
	 * This method was created in VisualAge.
	 * @return int
	 * @param fm java.awt.FontMetrics
	 * @param numPoints int
	 */
	protected int cellWidth(FontMetrics fm, int numPoints) {
		return 0;
	}
	/**
	 *	Overrides Legend to draw icons for each element in a single dataset
	 */
	protected synchronized void doHorizontalIcons(Graphics g) {
		int numDatasets = datasets[0].data.size();
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

			datasets[0].getDataElementAt(i).gc.fillRect(g, fromPoint, toPoint);
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
				datasets[0].getDataElementAt(i).getString().replace('|', ' '));
			fromPoint.translate(cellWidth, 0);
			toPoint.translate(cellWidth, 0);
		}
	}
	/**
	 *	Overrides Legend to draw icons for each element in a single dataset
	 */
	protected synchronized void doVerticalIcons(Graphics g) {
		g.setFont(labelFont);
		FontMetrics fm = g.getFontMetrics();
		int numDatasets = datasets[0].data.size();
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
			datasets[0].getDataElementAt(i).gc.fillRect(g, fromPoint, toPoint);
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
				datasets[0].getDataElementAt(i).getString().replace('|', ' '));
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
	 *		Overrides Legend to calculate size based on a single Dataset
	 *@param	g	Target Graphics class
	 */
	public synchronized void recalculateSize(Graphics g) {
		g.setFont(labelFont);
		FontMetrics fm = g.getFontMetrics();
		int numDatasets = datasets[0].data.size();
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
}