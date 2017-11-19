/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Image;
import java.io.Serializable;

/**
 *	Certain information needs to be available to almost all KavaChart 
 *	classes.  This includes things like whether a chart should have 3-D 
 *	effects, how large is the drawing surface, etc.  The Globals class 
 *	is used to store all this information.  For standard KavaChart 
 *	graphs, this class is instantiated once, and then references are 
 *	propagated to the classes that require them.  Since Java passes 
 *	this information by reference, a global change requires only a 
 *	change to the Globals class stored in the top-level Chart class.
 *	
 *	A couple of fairly obscure, but necessary components in this class 
 *	include stringRotator and image.  Since graphics strings are rotated 
 *	with raster operations, and because these operations require access to 
 *	the drawing canvas used by all the other drawing classes, this 
 *	information must be available globally.  
 *@see	javachart.chart.Gc
 *@see	javachart.chart.RotateString
 *@see	javachart.chart.DisplayList
 */

public class Globals implements Serializable {
	int xOffset = 15;
	int yOffset = 15;
	boolean threeD = false;
	int maxY;
	int maxX;
	RotateString stringRotator;
	transient Image image;
	boolean useDisplayList = false;
	DisplayList displayList;

	/**
	 *	Does nothing.
	 */
	public Globals() {
	}
	/**
	 *	Initialize several internal variables with the given arguments.
	 *@param	topY		Height of the chart
	 *@param	xDepth		X offset for doing 3-D chart
	 *@param	yDepth		Y offset for doing 3-D chart
	 *@param	showDepth	To show, or not to show 3-D chart
	 *@param	r		Internal variable used by RotateString class
	 *@param	i		Internal image used by RotateString class
	 */
	public Globals(
		int topY,
		int xDepth,
		int yDepth,
		boolean showDepth,
		RotateString r,
		Image i) {
		maxY = topY;
		xOffset = xDepth;
		yOffset = yDepth;
		threeD = showDepth;
		stringRotator = r;
		image = i;
	}
	/**
	 *	Returns the current DisplayList
	 *@return	Current display list
	 */
	public DisplayList getDisplayList() {
		return displayList;
	}
	/**
	 *	Returns the internal image used by RotateString class.  This will return null unless an Image class
	 *  has been installed with setImage.
	 *@return	Chart image used by RotateString class
	 *@see		javachart.chart.RotateString
	 */
	public Image getImage() {
		return image;
	}
	/**
	 *	Returns the maximum X value (in pixels) for this chart
	 *@return	Maximum X value
	 */
	public int getMaxX() {
		return maxX;
	}
	/**
	 *	Returns the maximum Y value (in pixels) for this chart
	 *@return	Maximum Y value
	 */
	public int getMaxY() {
		return maxY;
	}
	/**
	 *	Return this chart's RotateString class
	 *@return	A RotateString class used by other chart classes
	 */
	public RotateString getStringRotator() {
		return stringRotator;
	}
	/**
	 *	Activates a global DisplayList for this entire chart
	 *@return	We are, or are not using display list
	 *@see		javachart.chart.Chart
	 *@see		javachart.chart.DisplayList
	 */
	public boolean getUseDisplayList() {
		return useDisplayList;
	}
	/**
	 *	Returns the X offset used by 3-D charts.
	 *@return	X offset
	 */
	public int getXOffset() {
		return xOffset;
	}
	/**
	 *	Returns the Y offset used by 3-D charts.
	 *@return	Y offset
	 */
	public int getYOffset() {
		return yOffset;
	}
	/**
	 *	Inquires whether this chart should use 3-D effects
	 *@return	True if charts will be drawn with 3-D effect
	 */
	public boolean isThreeD() {
		return threeD;
	}
	/**
	 *	Installs a new display list.
	 *@param	d	A new display list
	 */
	public void setDisplayList(DisplayList d) {
		displayList = d;
		d.globals = this;
	}
	/**
	 *	Set the internal image used by RotateString class.
	 *@param	i	New image used by RotateString class
	 *@see		javachart.chart.RotateString
	 */
	public void setImage(Image i) {
		image = i;
	}
	/**
	 *	Set the maximum Y value (in pixels).  Usually this is set 
	 *	automatically by calls to resize methods.
	 *@param	i	New maximum Y value
	 */
	public void setMaxY(int i) {
		maxY = i;
	}
	/**
	 *	Installs a new RotateString class for use by the entire chart
	 *@param	r	New RotateString class
	 */
	public void setStringRotator(RotateString r) {
		stringRotator = r;
	}
	/**
	 *	Sets the 3-D effects value for this chart
	 *@param	depth	True if chart should show a 3-D effect
	 */
	public void setThreeD(boolean depth) {
		threeD = depth;
	}
	/**
	 *	Enable or disable this chart's DisplayList
	 *@param	onOff	True to enable DisplayList
	 */
	public void setUseDisplayList(boolean onOff) {
		if (onOff && (displayList == null))
			displayList = new DisplayList(this);
		useDisplayList = onOff;
	}
	/**
	 *	Set the X offset used by 3-D charts.
	 *@param	x	New X offset
	 */
	public void setXOffset(int x) {
		xOffset = x;
	}
	/**
	 *	Set the Y offset used by 3-D charts.
	 *@param	y	New Y offset
	 */
	public void setYOffset(int y) {
		yOffset = y;
	}
}