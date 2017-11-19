/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.FontMetrics;

/**
 *      Creates a legend appropriate for various kinds of line charts.  
 *	This legend overrides the doVerticalIcons and doHorizontalIcons
 *	methods to draw lines and markers instead of rectangles.
 *@see  javachart.chart.LegendInterface
 *@see  javachart.chart.Legend
 *@see  javachart.chart.PieLegend
 */

public class LineLegend extends Legend implements LegendInterface {

	/**
	 * Creates a new LineLegend without assigning Globals and Datasets.
	 */
	public LineLegend() {
	}
	/**
	 *	Constructs a LineLegend for the specified array of Dataset classes
	 *@param       d       Array of Dataset classes to consider
	 *@param       g       This chart's Globals class
	 */
	public LineLegend(Dataset d[], Globals g) {
		super(d, g);
	}
	/**
	 *	Overrides Legend to draw line & marker icons rather than rectangles
	 * @see javachart.chart.Legend
	 */
	protected synchronized void doHorizontalIcons(Graphics g) {

		int numDatasets = datasetsInUse();
		g.setFont(labelFont);
		FontMetrics fm = g.getFontMetrics();
		int vertPad = fm.getMaxAscent() / 2;
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

			datasets[i].gc.drawLine(
				g,
				fromPoint.x,
				fromPoint.y + vertPad,
				fromPoint.x + pIconWidth,
				fromPoint.y + vertPad);
			datasets[i]
				.gc
				.drawImage(
					g,
					new Point(
						fromPoint.x + (pIconWidth / 2),
						fromPoint.y + /*(pIconHeight/2)*/
			+vertPad));

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
	 *	Overrides Legend to draw line & marker icons rather than rectangles
	 * @see javachart.chart.Legend
	 */
	protected synchronized void doVerticalIcons(Graphics g) {

		g.setFont(labelFont);
		FontMetrics fm = g.getFontMetrics();
		int vertPad = fm.getMaxAscent() / 2;
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
			datasets[i].gc.drawLine(
				g,
				fromPoint.x,
				fromPoint.y + vertPad,
				fromPoint.x + pIconWidth,
				fromPoint.y + vertPad);
			datasets[i].gc.drawImage(
				g,
				new Point(
					fromPoint.x + (pIconWidth / 2),
					fromPoint.y + (pIconHeight / 2)));
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
}