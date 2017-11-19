/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Point;
import java.io.Serializable;

/**
 *	Provides a simple 2-D transformation.  Default KavaChart graphs use 
 *	at least 2 Transforms: 1 that transforms data into the plotting 
 *	area, and a second that transforms the relative coordinates of 
 *	legends, axis locations, etc. (which range from 0 to 1.0) into the 
 *	real pixel coordinates of the screen.  While Transforms are fairly 
 *	simple in this context, note that all data representations (bars, 
 *	lines, etc.) are drawn through a data Transform.  By creating a 
 *	subclass of Transform, you can easily build data representations 
 *	that draw in non-linear spaces, such as logarithms, natural logs, 
 *	even discontinuous spaces.  For a complete representation, of 
 *	course, you would also need to create a descriptive axis system, 
 *	probably a subclass of Axis.
 *@see	javachart.chart.Plotarea
 *@see	javachart.chart.Area
 *@see	javachart.chart.Bar
 *@see	javachart.chart.Line
 */

public class Transform implements Serializable {

	//used for transforming graphics going into the plotting area

	//internals
	private double scaleX;
	private double scaleY;
	private double shiftX;
	private double shiftY;

	protected boolean logXScaling = false;
	protected boolean logYScaling = false;

	final static double LOG_10_E = 0.43429420;

	/**
	 *	Creates a transform that will scale numbers from a double precision
	 *	arena to an integer space.
	 *
	 * @param	dllX	lower left X
	 * @param	dllY	lower left Y
	 * @param	durX	upper right X
	 * @param	durY	upper right Y
	 * @param	illX	lower left X
	 * @param	illY	lower left Y
	 * @param	iurX	upper right X
	 * @param	iurY	upper right Y
	 */
	public Transform(double dllX, //double precision window
	double dllY, double durX, double durY, int illX, //integer precision window
	int illY, int iurX, int iurY) {

		scaleX = (double) (iurX - illX) / (durX - dllX);
		scaleY = (double) (iurY - illY) / (durY - dllY);

		shiftX = (double) illX - (scaleX * dllX);
		shiftY = (double) illY - (scaleY * dllY);
	}
	/**
	 *	Creates a transform that will scale numbers from a double precision
	 *	arena to an integer space.
	 *
	 * @param	dllX	lower left X
	 * @param	dllY	lower left Y
	 * @param	durX	upper right X
	 * @param	durY	upper right Y
	 * @param	ll	integer lower left
	 * @param	ur	integer upper right
	 */
	public Transform(double dllX, //double precision window
	double dllY, double durX, double durY, Point ll, //integer precision window
	Point ur) {

		scaleX = (double) (ur.x - ll.x) / (durX - dllX);
		scaleY = (double) (ur.y - ll.y) / (durY - dllY);

		shiftX = (double) ll.x - (scaleX * dllX);
		shiftY = (double) ll.y - (scaleY * dllY);
	}
	static double log10(double inVal) {
		return (Math.log(inVal) * LOG_10_E);
	}
	/**
	 *	Returns a transformed integerized Point from double precision X and Y
	 *	coordinates
	 */
	public Point point(double x, double y) {
		int iX, iY;

		if (!logXScaling)
			iX = (int) ((x * scaleX) + shiftX);
		else
			iX = (int) ((log10(x) * scaleX) + shiftX);

		if (!logYScaling)
			iY = (int) ((y * scaleY) + shiftY);
		else
			iY = (int) ((log10(y) * scaleY) + shiftY);

		return new Point(iX, iY);
	}
	/**
	 *	Transforms an array of double precision X and Y values into an array
	 *	of integerized Point classes
	 */
	public Point[] pointList(double x[], double y[]) {
		int i;
		Point pts[];

		pts = new Point[x.length];
		for (i = 0; i < x.length; i++) {
			pts[i] = point(x[i], y[i]);
		}
		return pts;
	}
	public String toString() {
		return getClass().getName()
			+ "["
			+ " shiftX "
			+ shiftX
			+ " shiftY "
			+ shiftY
			+ " scaleX "
			+ scaleX
			+ " scaleY "
			+ scaleY
			+ " ]";
	}
	/**
	 *	Transforms an integer X value into a double precision X value.  This
	 *	method is used to translate the X component of a pixel coordinate into
	 *	data or percentage space
	 */
	public double xValue(int ix) {
		double x;

		x = (double) ix;
		x = (x - shiftX) / scaleX;
		if (!logXScaling)
			return x;
		else
			return log10(x);
	}
	/**
	 *	Transforms an integer Y value into a double precision Y value.  This
	 *	method is used to translate the Y component of a pixel coordinate into
	 *	data or percentage space
	 */
	public double yValue(int iy) {
		double y;

		y = (double) iy;
		y = (y - shiftY) / scaleY;
		if (!logYScaling)
			return y;
		else
			return log10(y);
	}
}