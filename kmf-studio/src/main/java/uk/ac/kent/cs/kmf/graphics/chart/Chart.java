/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

/**
 *	An abstract class that implements several methods common to all 
 *	charts.  Subclasses of Chart inherit a variety of addDataset methods 
 *	and implementations of all the standard access methods defined in 
 *	ChartInterface.  Most final classes based on the Chart class are 
 *	fairly simple, requiring initialization of Axes and chart components.
 */

public abstract class Chart 
	implements ChartInterface, Serializable 
{
	/**
	 *	constructs a default chart 
	 */
	public Chart() {
		initChart();
	}
	
	/**
	 *	constructs a default chart 
	 *	with the specified name
	 *  @param s chart name
	 */
	public Chart(String s) {
		name = s;
		initChart();
	}
	
	/**
	 *	add a dataset to this chart
	 *  @param s name of dataset
	 *  @param x array of x values
	 */
	public void addDataset(String s, double x[]) {
		if (numberOfDatasets < MAX_DATASETS) {
			datasets[numberOfDatasets] =
				new Dataset(s, x, numberOfDatasets, globals);
			numberOfDatasets++;
		} else
			System.out.println("max datasets is " + MAX_DATASETS);
	}

	/**
	 *	add a dataset to this chart
	 *  @param s name of dataset
	 *  @param x array of x values
	 *  @param y array of y values
	 */
	public void addDataset(String s, double x[], double y[]) {
		if (numberOfDatasets < MAX_DATASETS) {
			datasets[numberOfDatasets] =
				new Dataset(s, x, y, numberOfDatasets, globals);
			numberOfDatasets++;
		} else
			System.out.println("max datasets is " + MAX_DATASETS);
	}
	/**
	 *	add a dataset to this chart
	 *  @param	x	name of x values (usually dates)
	 *  @param	hi	array of high values
	 *  @param	lo	array of low values
	 *  @param	close	array of close values
	 */
	public void addDataset(
		String s,
		double x[],
		double hi[],
		double lo[],
		double close[]) {

		if (numberOfDatasets < MAX_DATASETS) {
			datasets[numberOfDatasets] =
				new Dataset(s, x, hi, lo, close, numberOfDatasets, globals);
			numberOfDatasets++;
		} else {
			System.out.println("maximum number of datasets is " + MAX_DATASETS);
		}

	}

	/**
	 *	add a dataset to this chart
	 *  @param	s	name of dataset
	 *  @param	x	array of x values
	 *  @param	y	array of y values
	 *  @param	dataLabels	labels for each data point
	 */
	public void addDataset(String s, double x[], double y[], String labels[]) {
		if (numberOfDatasets < MAX_DATASETS) {
			datasets[numberOfDatasets] =
				new Dataset(s, x, y, labels, numberOfDatasets, globals);
			numberOfDatasets++;
		} else
			System.out.println("max datasets is " + MAX_DATASETS);
	}
	/**
	 *	add a dataset to this chart
	 *  @param	s	name of dataset
	 *  @param	x	array of x values
	 *  @param	dataLabels	labels for each data point
	 */
	public void addDataset(String s, double x[], String dataLabels[]) {
		if (numberOfDatasets < MAX_DATASETS) {
			datasets[numberOfDatasets] =
				new Dataset(s, x, dataLabels, numberOfDatasets, globals);
			numberOfDatasets++;
		} else {
			System.out.println("maximum number of datasets is " + MAX_DATASETS);
		}
	}
	/**
	 *	add a dataset to this chart
	 *
	 *@param	d	Existing Dataset class
	 */
	public void addDataset(Dataset d) {
		if (numberOfDatasets < MAX_DATASETS) {
			datasets[numberOfDatasets] = d;
			d.setGlobals(globals);
			numberOfDatasets++;
		} else
			System.out.println("max datasets is " + MAX_DATASETS);
	}
	/**
	 *	deletes the named dataset and rearranges this chart's dataset array
	 *@param	s	name of dataset to delete
	 */
	public void deleteDataset(String s) {
		for (int i = 0; i < numberOfDatasets; i++) {
			if (datasets[i].setName.equals(s)) {
				deleteDataset(datasets[i]);
				return;
			}
		}
	}
	/**
	 *	deletes the specified dataset and rearranges this chart's dataset array
	 *@param	d	Dataset to delete
	 */
	public void deleteDataset(Dataset d) {
		int i, j;

		for (i = 0; i < numberOfDatasets; i++)
			if (datasets[i] == d) {
				datasets[i] = null;
				for (j = i + 1; j < datasets.length; j++)
					datasets[j - 1] = datasets[j];
				continue;
			}
		numberOfDatasets--;
	}
	/**
	 *	draws an entire chart to this chart's Graphic class
	 */
	public void drawGraph() {
		if (globals.useDisplayList)
			globals.displayList.clear();
	}
	/**
	 *	draws an entire chart to the specified Graphics class
	 *@param	g	Graphics class to use
	 */
	public void drawGraph(Graphics g) {
		if (globals.useDisplayList)
			globals.displayList.clear();
	}
	/**
	 *	returns this chart's Background class
	 */
	public Background getBackground() {
		return background;
	}
	/**
	 *	returns this chart's default Graphics class
	 */
	public Graphics getCanvas() {
		return canvas;
	}
	/**
	 * Returns this chart's DataRepresentation.
	 * Most charts use a DataRepresentation subclass, such as Bar, Line, or Area to visually
	 * describe data.  This method returns that class.  Typically, Chart subclasses, such
	 * as BarChart will also include a method like getBar() to return the appropriate subclass.
	 * Note that some charts, such as BarLineChart contain several DataRepresentations, and this
	 * method will return only one of them.
	 * @return javachart.chart.DataRepresentation
	 */
	public DataRepresentation getDataRepresentation() {
		return dataRepresentation;
	}
	/**
	 *	returns the Dataset class with the specified name, or null if nonexistent
	 *@param	name	Dataset name
	 */
	public Dataset getDataset(String name) {
		for (int i = 0; i < datasets.length; i++) {
			if (datasets[i] != null)
				if (datasets[i].setName.equals(name))
					return datasets[i];
		}
		return null;
	}
	/**
	 *	returns this chart's array of Dataset classes
	 */
	public Dataset[] getDatasets() {
		return datasets;
	}
	/**
	 *	returns KavaChart's central DisplayList.
	 *@see javachart.chart.DisplayList
	 *@see javachart.chart.Globals
	 */
	public DisplayList getDisplayList() {
		return globals.getDisplayList();
	}
	/**
	 *	returns this chart's Globals class
	 */
	public Globals getGlobals() {
		return globals;
	}
	/**
	 *	returns the current height
	 */
	public int getHeight() {
		return globals.maxY;
	}
	/**
	 *	Returns this chart's Image, if it has been installed and produced.
	 *	This image is used internally by KavaChart's RotateString class to
	 *	rotate text strings with raster operations.  This must be a valid
	 *	chart Image to rotate strings properly.
	 *
	 *	This image may also be used after drawing for other operations, such
	 *	as exporting image files or for clipboard operations.
	 */
	public Image getImage() {
		return globals.getImage();
	}
	/**
	 *	returns this chart's Legend as a Legend Interface
	 */
	public LegendInterface getLegend() {
		return legend;
	}
	//accessors
	/**
	 *	returns this chart's name
	 */
	public String getName() {
		return name;
	}
	/**
	 *	returns the number of datasets used by this chart
	 */
	public int getNumDatasets() {
		return numberOfDatasets;
	}
	/**
	 *	returns this chart's Plotarea class
	 */
	public Plotarea getPlotarea() {
		return plotarea;
	}
	/**
	 *	returns this chart's RotateString class
	 */
	public RotateString getStringRotator() {
		return globals.stringRotator;
	}
	/**
	 *	inquires whether this chart accumulates drawing information into a 
	 *	central DisplayList.
	 */
	public boolean getUseDisplayList() {
		return globals.getUseDisplayList();
	}
	/**
	 *	returns the width (in pixels) of this chart
	 */
	public int getWidth() {
		return globals.maxX;
	}
	/**
	 *	returns this chart's X axis
	 */
	public AxisInterface getXAxis() {
		return xAxis;
	}
	/**
	 *	3-D effects are added by drawing effect panels offset in X and Y 
	 *	directions.  This method returns the current X offset value in pixels
	 */
	public int getXOffset() {
		return globals.xOffset;
	}
	/**
	 *	returns this chart's Y axis
	 */
	public AxisInterface getYAxis() {
		return yAxis;
	}
	/**
	 *	returns the current Y offset value in pixels
	 */
	public int getYOffset() {
		return globals.yOffset;
	}
	/**
	 *	returns true if the specified Dataset class is used in this chart
	 */
	public boolean includesDataset(Dataset d) {
		for (int i = 0; i < datasets.length; i++)
			if (datasets[i] == d)
				return true;
		return false;
	}
	protected void initChart() {
	}
	/**
	 *	Initializes an array to contain Dataset classes
	 */
	protected void initDatasets() {
		datasets = new Dataset[MAX_DATASETS];
	}
	/**
	 *	Installs a new Globals class for this chart
	 *@see	javachart.chart.Globals
	 */
	protected void initGlobals() {
		globals = new Globals();
	}
	/**
	 *	inquires whether this chart's Legend should be drawn with the rest of
	 *	the chart
	 */
	public boolean isLegendVisible() {
		return legendVisible;
	}
	/**
	 *	returns true if this chart will be drawn with 3-D effects
	 */
	public boolean isThreeD() {
		return globals.threeD;
	}
	/**
	 *	returns true if the X axis is drawn with the rest of the chart
	 */
	public boolean isXAxisVisible() {
		return xAxisVisible;
	}
	/**
	 *	returns true if the Y axis is drawn with the rest of the chart
	 */
	public boolean isYAxisVisible() {
		return yAxisVisible;
	}
	/**
	 *	draws an entire chart to this chart's Graphic class
	 */
	public void paint() {
		drawGraph();
	}
	/**
	 * A convenience method that automates double buffered painting.  This method automatically creates
	 * and installs an Image buffer class and a RotateString class the first time it's called.
	 * Subsequent calls will resize the chart and image buffer to the size of the input Component, draw
	 * the chart to the image buffer, and then draw that Image upon the Component.
	 * @param c java.awt.Component
	 * @param g java.awt.Graphics
	 */
	public void paint(java.awt.Component c, Graphics g) {
		java.awt.Dimension d = c.getSize();
		if ((globals.image == null)
			|| (d.width != globals.image.getWidth(null))
			|| (d.height != globals.image.getHeight(null))) {
			//set up image buffer
			globals.image = c.createImage(d.width, d.height);
			setStringRotator(new RotateString(c));
			resize(d.width, d.height);
		}
		drawGraph(globals.image.getGraphics());
		g.drawImage(globals.image, 0, 0, null);
	}
	/**
	 *	draws an entire chart to the specified Graphics class
	 *@param	g	Graphics class to use
	 */
	public void paint(Graphics g) {
		drawGraph(g);
	}
	/**
	 *	resizes all this chart's graphical components
	 */
	public void resize(int newWidth, int newHeight) {
		globals.maxY = newHeight;
		if (plotarea != null)
			plotarea.resize(newWidth, newHeight);
		if (background != null)
			background.resize(newWidth, newHeight);
		if (legend != null)
			legend.resize(newWidth, newHeight);
		globals.maxX = newWidth;
		globals.maxY = newHeight;
		width = newWidth;
		height = newHeight;
	}
	/**
	 *	installs a new Background class for this chart
	 *@param	b	the new Background
	 */
	public void setBackground(Background b) {
		background = b;
		background.globals = globals;
	}
	/**
	 *	sets a new Graphics class for default use
	 *@param	g	new Graphics class
	 *@deprecated
	 */
	public void setCanvas(Graphics g) {
		canvas = g;
	}
	/**
	 * Sets this chart's DataRepresentation.
	 * Most charts have a DataRepresentation subclass, such as Bar, Line, or Area that
	 * must be "installed" into a chart by assigning Axes, Plotarea, Dataset, and Globals classes.
	 * This utility method does these assignments.
	 * @param rep javachart.chart.DataRepresentation
	 */
	public void setDataRepresentation(DataRepresentation rep) {
		rep.plotarea = plotarea;
		rep.xAxis = xAxis;
		rep.yAxis = yAxis;
		rep.datasets = datasets;
		rep.globals = globals;
		dataRepresentation = rep;
	}
	/**
	 *	installs a new DisplayList for use in retrieving a list of chart 
	 *	objects at a specified location.
	 */
	public void setDisplayList(DisplayList d) {
		globals.setDisplayList(d);
	}
	/**
	 *	installs a new Globals class for this chart.
	 */
	/* need to check to make sure this works... */
	public void setGlobals(Globals g) {
		globals = g;
		try {
			dataRepresentation.globals = g;
			plotarea.globals = g;
			plotarea.gc.globals = g;
			background.globals = g;
			background.gc.globals = g;
			if (legend != null)
				if (legend instanceof Legend) {
					((Legend) legend).globals = g;
					((Legend) legend).backgroundGc.globals = g;
				}
			for (int i = 0; i < datasets.length; i++)
				if (datasets[i] != null)
					datasets[i].setGlobals(g);
		} catch (Exception e) {
			//System.out.println("failed to install Globals in chart");
		}
	}
	/**
	 *	sets a new height for this chart
	 *@param	h	the new height
	 */
	public void setHeight(int h) {
		height = h;
		resize(width, height);
	}
	/**
	 *	installs an Image for use in KavaChart's RotateString class.  
	 *	Normally, this is the Image associated with the Graphics class used
	 *	for drawing charts.
	 *
	 *@param	i	an Image class used for drawing charts;
	 */
	public void setImage(Image i) {
		globals.setImage(i);
	}
	/**
	 *	installs a new Legend for this chart
	 *@param	l	the new Legend
	 */
	public void setLegend(LegendInterface l) {
		legend = l;
		if (legend instanceof Legend) {
			((Legend) legend).datasets = datasets;
			((Legend) legend).globals = globals;
			((Legend) legend).backgroundGc.globals = globals;
		}
	}
	/**
	 *	sets this chart's legend visibility characteristic
	 *@param v	if true, this chart's Legend is drawn with the chart
	 */
	public void setLegendVisible(boolean v) {
		legendVisible = v;
	}
	/**
	 *	sets a new name for this chart
	 *@param	s	chart name
	 */
	public void setName(String s) {
		name = s;
	}
	/**
	 *	installs a new Plotarea for this chart
	 *@param	p	the new Plotarea
	 */
	public void setPlotarea(Plotarea p) {
		plotarea = p;
		plotarea.globals = globals;
		plotarea.gc.globals = globals;
	}
	/**
	 *	installs a new RotateString class for this chart
	 *	param	rotator	new RotateString class
	 */
	public void setStringRotator(RotateString rotator) {
		globals.stringRotator = rotator;
	}
	/**
	 *	sets this chart's 3-D effects attribute
	 *@param	b	true if 3-D effects are desired
	 */
	public void setThreeD(boolean b) {
		globals.threeD = b;
	}
	/**
	 *	Determines whether this chart will use a DisplayList to acquire 
	 *	geometric information about chart objects or not.  By default, 
	 *	KavaChart's DisplayList is not active.
	 *@param	yesNo	true if you wish to use a DisplayList
	 *@see	javachart.chart.DisplayList
	 *@see	javachart.chart.Globals
	 */
	public void setUseDisplayList(boolean yesNo) {
		globals.setUseDisplayList(yesNo);
	}
	/**
	 *	sets a new width for this chart
	 *@param	w	the new width
	 */
	public void setWidth(int w) {
		width = w;
		resize(width, height);
	}
	/**
	 *	Installs a new X axis for use by this chart.
	 *  Also assigns this chart's Globals, Plotarea, and Dataset array classes to this axis.
	 */
	public void setXAxis(AxisInterface a) {
		xAxis = a;
		try {
			Axis ax = (Axis) a;
			ax.globals = globals;
			ax.lineGc.globals = globals;
			ax.tickGc.globals = globals;
			ax.gridGc.globals = globals;
			ax.plotarea = plotarea;
			ax.datasets = datasets;
			ax.isXAxis = true;
			if (dataRepresentation != null)
				dataRepresentation.xAxis = a;
		} catch (Exception e) {
			//probably not an Axis subclass - just fail
		}
	}
	/**
	 *	toggles the visibility of this chart's X axis
	 *@param	vis	true if you want this chart's X axis drawn
	 */
	public void setXAxisVisible(boolean vis) {
		xAxisVisible = vis;
	}
	/**
	 *	sets the X offset for 3-D effects in pixels
	 *@param	i	new offset value
	 */
	public void setXOffset(int i) {
		globals.xOffset = i;
	}
	/**
	 *	installs a new Y axis for use by this chart
	 *  Also assigns this chart's Globals, Plotarea, and Dataset array classes to this axis.
	 */
	public void setYAxis(AxisInterface a) {
		yAxis = a;
		try {
			Axis ax = (Axis) a;
			ax.globals = globals;
			ax.lineGc.globals = globals;
			ax.tickGc.globals = globals;
			ax.gridGc.globals = globals;
			ax.plotarea = plotarea;
			ax.datasets = datasets;
			ax.isXAxis = false;
			if (dataRepresentation != null)
				dataRepresentation.yAxis = a;
		} catch (Exception e) {
			//probably not an Axis subclass - just fail
		}
	}
	/**
	 *	toggles the visibility of this chart's Y axis
	 *@param	vis	true if you want this chart's Y axis drawn
	 */
	public void setYAxisVisible(boolean vis) {
		yAxisVisible = vis;
	}
	/*
	 *	sets the Y offset for 3-D effects in pixels
	 *	param	i	new offset value
	 */
	public void setYOffset(int i) {
		globals.yOffset = i;
	}
	
	//
	// Local properties
	//
	protected String name = "New Chart";
	protected Graphics canvas;
	protected boolean legendVisible = false;
	protected int width = 640;
	protected int height = 480;
	protected boolean xAxisVisible = true;
	protected boolean yAxisVisible = true;

	protected Globals globals;
	protected Dataset datasets[];
	protected Plotarea plotarea;
	protected Background background;
	protected LegendInterface legend;
	protected AxisInterface xAxis, yAxis;
	protected DataRepresentation dataRepresentation;

	protected int numberOfDatasets = 0;
	public final static int MAX_DATASETS = 40;
}