/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.text.*;
import java.util.*;

/**
 * A convenience superclass for various DataRepresentations.
 * Bar, Line, Area, and other visual data representations share common features that
 * benefit from this level of abstraction.  User classes that implement DataRepresentation
 * can be used with existing KavaChart charts more easily.
 */
public abstract class DataRepresentation 
	implements java.io.Serializable 
{
	/**
	 * DataRepresentation constructor comment.
	 */
	public DataRepresentation() {
		super();
	}
	/**
	 * Painting method.
	 * @param g Graphics
	 */
	public abstract void draw(java.awt.Graphics g);
	protected String formatLabel(double inVal) {
		if (userFormat != null) {
			return userFormat.format(new Double(inVal));
		}
		return numberFormat.format(inVal);
	}
	/**
	 * Returns the array of Dataset classes represented.
	 * @return javachart.chart.Dataset[]
	 */
	public Dataset[] getDatasets() {
		return datasets;
	}
	/**
	 * Returns the user-defined Format (NumberFormat, MessageFormat, etc.).
	 * @return java.text.Format
	 */
	public Format getFormat() {
		return userFormat;
	}
	/**
	 *	Inquires what angle data point labels will draw at.
	 */
	public int getLabelAngle() {
		return labelAngle;
	}
	/**
	 *  Returns this Axis' label format value.
	 *  @return label format
	 *  @deprecated	as of release 3.0, superceded by the much more flexible get/setFormat
	 */
	public int getLabelFormat() {
		return labelFormat;
	}
	/**
	 *  Returns the labels' floating point display precision.  A precision of 2 yields
	 *	labels like "1.11".  A precision of 4 makes labels like "1.1111"
	 *@return       label precision
	 */
	public int getLabelPrecision() {
		return labelPrecision;
	}
	/**
	 *	Inquires whether this DataRepresentation will draw labels at each data point
	 *
	 * @return	True if labels are active
	 */
	public boolean getLabelsOn() {
		return labelsOn;
	}
	/**
	 *      Return the current display list setting.
	 * @return       Current status of display list usage
	 * @see          javachart.chart.DisplayList
	 */
	public boolean getUseDisplayList() {
		return useDisplayList;
	}
	/**
	 *	Inquires this component's X axis
	 * @return	Current X Axis
	 */
	public AxisInterface getXAxis() {
		return xAxis;
	}
	/**
	 *	Inquires this component's Y axis
	 * @return	Current Y Axis
	 */
	public AxisInterface getYAxis() {
		return yAxis;
	}
	/**
	 * Installs an array of Dataset classes to be represented.
	 * @param d javachart.chart.Dataset[]
	 */
	public void setDatasets(Dataset[] d) {
		datasets = d;
		if (globals == null)
			return;
		for (int i = 0; i < d.length; i++) {
			if (d[i] != null) {
				if (d[i].globals != globals) {
					d[i].setGlobals(globals);
				}
			}
		}
	}
	/**
	 * Installs a user-defined Format for use in drawing Axis labels.  This could be done
	 * to do things like specify a fixed Locale, or create additional formatting, such as
	 * prepending a "$" to each label.  Set format to null to revert to default formatting.
	 * @param format java.text.Format
	 */
	public void setFormat(Format format) {
		userFormat = format;
	}
	/**
	 * Installs a Globals class into this DataRepresentation and its instance
	 * variables (xAxis, yAxis, etc.).  Globals objects are
	 * used to provide consistent sizing, DisplayList management, etc. for all
	 * chart objects.
	 * @param g javachart.chart.Globals
	 */
	public void setGlobals(Globals g) {
		if (g == null)
			return;
		globals = g;
		xAxis.setGlobals(g);
		yAxis.setGlobals(g);
		plotarea.globals = g;
		if (datasets != null) {
			//try to avoid installing Globals in each Datum
			for (int i = 0; i < datasets.length; i++) {
				if (datasets[i] != null) {
					if (datasets[i].globals != g) {
						datasets[i].setGlobals(g);
					}
				}
			}
		}
	}
	/**
	 *	Sets the angle at which data point labels should draw.  The default 
	 *	angle is 0.
	 * @param	num	degrees of angle
	 */
	public void setLabelAngle(int num) {
		labelAngle = num;
	}
	/**
	 *      Determines what format to use for this item's labels.  The default
	 *	is Gc.DEFAULT_FORMAT, which is a machine-dependent representation
	 *	for Java 1.0x, and may include Scientific notation.
	 *
	 *	Other possibilities include Gc.COMMA_FORMAT, which formats numbers as
	 *	1,000.00 and Gc.EURO_FORMAT, which formats numbers like 1.000,00.
	 *
	 *	As of release 3.0, this method simply uses Locale.FRANCE with NumberFormat
	 *  for settings of Gc.EURO_FORMAT, and Locale.US for Gc.COMMA_FORMAT,
	 *  and the default Locale for Gc.DEFAULT_FORMAT
	 *  @param        i   	label format
	 *  @deprecated Replaced by the much more flexible and accurate setFormat
	 */
	public void setLabelFormat(int i) {
		//Note: we're assuming that Locale.FRANCE will always be available...
		if (i == Gc.EURO_FORMAT)
			numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);
		if (i == Gc.COMMA_FORMAT)
			numberFormat = NumberFormat.getNumberInstance(Locale.US);
		if (i == Gc.DEFAULT_FORMAT)
			numberFormat = NumberFormat.getNumberInstance();
	}
	/**
	 *      Set the labels' floating point display precision.
	 *@param        num       A new label precision
	 */
	public void setLabelPrecision(int num) {
		labelPrecision = num;
		if (userFormat != null) {
			if (userFormat instanceof NumberFormat) {
				((NumberFormat) userFormat).setMinimumFractionDigits(num);
				((NumberFormat) userFormat).setMaximumFractionDigits(num);
			}
		}
		if (num != -1) {
			numberFormat.setMinimumFractionDigits(num);
			numberFormat.setMaximumFractionDigits(num);
		} else {
			//	clear old settings
			numberFormat = NumberFormat.getInstance(); 
		}
	}
	/**
	 *	Turns on Datum labels at each data point.  Labels are off by default.
	 *  @param	onOff	True to enable data point labelling.
	 *  @see	javachart.chart.Datum
	 */
	public void setLabelsOn(boolean onOff) {
		labelsOn = onOff;
	}
	/**
	 *  Turn display lists on or off for this chart component.
	 *  @param        onOff   New display list usage
	 *  @see          javachart.chart.DisplayList
	 */
	public void setUseDisplayList(boolean on) {
		useDisplayList = on;
	}
	/**
	 * Installs the AxisInterface class used to scale this DataRepresentation's X Values.
	 * @param ax javachart.chart.AxisInterface
	 */
	public void setXAxis(AxisInterface ax) {
		ax.setGlobals(globals);
		ax.setPlotarea(plotarea);
		ax.setDatasets(datasets);
		ax.setIsXAxis(true);

		xAxis = ax;
	}
	/**
	 * Installs the AxisInterface class used to scale this DataRepresentation's Y Values.
	 * @param ax javachart.chart.AxisInterface
	 */
	public void setYAxis(AxisInterface ax) {
		ax.setGlobals(globals);
		ax.setPlotarea(plotarea);
		ax.setDatasets(datasets);
		ax.setIsXAxis(false);

		yAxis = ax;
	}
	
	//
	// Local properties
	//
	protected Plotarea plotarea;
	protected Globals globals;
	protected AxisInterface xAxis;
	protected AxisInterface yAxis;
	protected Dataset[] datasets;
	protected boolean useDisplayList = true;
	protected int labelAngle = 0;
	protected int labelFormat = Gc.COMMA_FORMAT;
	protected boolean labelsOn = false;
	protected int labelPrecision = 2;
	protected Format userFormat = null;
	protected NumberFormat numberFormat = NumberFormat.getInstance();
}