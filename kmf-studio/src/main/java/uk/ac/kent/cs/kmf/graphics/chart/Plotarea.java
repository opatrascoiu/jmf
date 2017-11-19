/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

/**
 *	This class typically draws a rectangle over an area bounded by 
 *	2 axes.  The Axis class requires a Plotarea object to draw in 
 *	the correct location.  By subclassing Plotarea, you can create 
 *	transparent plotareas, texture-filled plotareas, gradient fills 
 *	or other special effects.  Plotarea also contains a draw3d 
 *	method for creating a dimensional effect.  Subclasses that wish to 
 *	override drawing functionality should be aware that Plotarea's draw 
 *	method will call draw3d when the Chart's global 3-D settings are true.
 *@see	javachart.chart.Chart
 *@see	javachart.chart.Background
 */

public class Plotarea implements Serializable {

	Gc gc = new Gc(Color.white, null);
	double urX = .8;
	double urY = .8;
	double llX = .2;
	double llY = .2;
	boolean useDisplayList = true;
	Globals globals;

	//utilities
	private int gWidth = 640;
	private int gHeight = 480;
	Transform transform =
		new Transform(
			0.0,
			0.0,
			1.0,
			1.0,
			0,
			0,
			(int) (gWidth),
			(int) (gHeight));
	;

	public Plotarea() {
	}
	/**
	 * 	Constructs a new Plotarea
	 * @param	g	This chart's Globals class
	 */
	public Plotarea(Globals w) {
		globals = w;
		gc.globals = globals;
	}
	/**
	 *	Draws a plotting region to the specified Graphics class
	 * @param	g	Graphics class to draw
	 */
	public synchronized void draw(Graphics g) {
		if (globals.threeD)
			draw3d(g);
		else
			gc.fillRect(
				g,
				transform.point(llX, llY),
				transform.point(urX, urY));
		if (useDisplayList && globals.useDisplayList)
			globals.displayList.addRectangle(
				(Object) this,
				transform.point(llX, llY),
				transform.point(urX, urY));
	}
	/**
	 *	Draws a plotting region with 3-D visual effects using the specified
	 *	Graphics class.
	 * @param	g	Graphics class to draw
	 */
	public synchronized void draw3d(Graphics g) {
		Point pts[];
		int saveURx, saveURy;
		Color saveColor;

		pts = new Point[4];
		if (g == null)
			return;
		pts[0] = transform.point(llX, llY);
		pts[1] =
			new Point(pts[0].x + globals.xOffset, pts[0].y + globals.yOffset);
		pts[2] = transform.point(urX, urY);
		pts[3] =
			new Point(pts[2].x + globals.xOffset, pts[2].y + globals.yOffset);
		gc.fillRect(g, pts[1], pts[3]);
		if (useDisplayList && globals.useDisplayList)
			globals.displayList.addRectangle((Object) this, pts[1], pts[3]);

		//side face
		saveColor = gc.fillColor;
		gc.fillColor = saveColor.darker();
		saveURx = pts[2].x;
		saveURy = pts[2].y;
		pts[2].x = pts[1].x;
		pts[2].y = pts[3].y;
		pts[3].x = pts[0].x;
		pts[3].y = saveURy;
		gc.drawPolygon(g, pts);
		pts[2].x = saveURx + globals.xOffset;
		pts[2].y = pts[1].y;
		pts[3].x = saveURx;
		pts[3].y = pts[0].y;
		gc.drawPolygon(g, pts);
		gc.fillColor = saveColor;
	}
	/**
	 *	Inquires the Gc used for this Plotarea's graphical attributes
	 * @return	The Plotarea Gc
	 */
	public Gc getGc() {
		return gc;
	}
	/**
	 *	Inquires the lower left X location of this Plotarea, which is a 
	 *	double ranging from 0 to 1.
	 *
	 * @return	lower left X location.
	 */
	public double getLlX() {
		return llX;
	}
	/**
	 *	Inquires the lower left Y location of this Plotarea, which is a 
	 *	double ranging from 0 to 1.
	 *
	 * @return	lower left Y location.
	 */
	public double getLlY() {
		return llY;
	}
	/**
	 *	Inquires the upper right X location of this Plotarea, which is a 
	 *	double ranging from 0 to 1.
	 *
	 * @return	Upper right X location.
	 */
	public double getUrX() {
		return urX;
	}
	/**
	 *	Inquires the upper right Y location of this Plotarea, which is a 
	 *	double ranging from 0 to 1.
	 *
	 * @return	Upper right Y location.
	 */
	public double getUrY() {
		return urY;
	}
	/**
	 *	Inquires whether this Plotarea should add objects to the chart's global
	 *	DisplayList.  Objects are added by default when the DisplayList is 
	 *	active.
	 *
	 * @return	True if Plotarea objects go into DisplayList
	 * @see		javachart.chart.Globals
	 */
	public boolean getUseDisplayList() {
		return useDisplayList;
	}
	/**
	 *	Resizes the ploting area based on changes in the overall chart size.
	 *	Note: this does not specify a Plotarea relative size and location, 
	 *	which is specified with llX, llY, urX, and urY methods.
	 *
	 * @param	w	new chart width
	 * @param	h	new chart height
	 */
	public synchronized void resize(int w, int h) {
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
	 *	Sets a new Gc for this Plotarea
	 * @param	g	new Gc class
	 */
	public void setGc(Gc g) {
		gc = g;
	}
	/**
	 *	Sets the lower left X location of this Plotarea, which is a double
	 *	ranging from 0 to 1.
	 *
	 * @param	d	lower left X location
	 */
	public void setLlX(double d) {
		llX = d;
	}
	/**
	 *	Sets the lower left Y location of this Plotarea, which is a double
	 *	ranging from 0 to 1.
	 *
	 * @param	d	lower left Y location
	 */
	public void setLlY(double d) {
		llY = d;
	}
	/**
	 *	Sets the upper right X location of this Plotarea, which is a double
	 *	ranging from 0 to 1.
	 *
	 * @param	d	Upper right X location
	 */
	public void setUrX(double d) {
		urX = d;
	}
	/**
	 *	Sets the upper right Y location of this Plotarea, which is a double
	 *	ranging from 0 to 1.
	 *
	 * @param	d	Upper right Y location
	 */
	public void setUrY(double d) {
		urY = d;
	}
	/**
	 *	Determines whether Plotarea objects go into the chart's global 
	 *	DisplayList.  By setting onOff to false, you can eliminate Plotarea
	 *	objects from the DisplayList
	 *
	 * @param	onOff	True if Plotareas should go into DisplayList
	 */
	public void setUseDisplayList(boolean onOff) {
		useDisplayList = onOff;
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