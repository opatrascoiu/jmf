package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.*;

/**
 *	A conventional Pie chart containing a single Pie with one slice per 
 *	data point, a PieLegend, a Plotarea, and a Background
 */

public class PieChart 
	extends Chart 
{
	/**
	 *	constructs a default chart 
	 */
	public PieChart() {
		super();
	}
	
	/**
	 *	constructs a default chart 
	 *	with the specified name
	 * @param	s	chart name
	 */
	public PieChart(String s) {
		super(s);
	}
	
	/**
	 *	Creates a new Dataset and Legend based on this data array
	 * @param	s	Dataset name
	 * @param	y	Array of pie slice values
	 */
	public void addDataset(String s, double y[]) {
		PieLegend pl;

		pie.dataset = new Dataset(s, y, true, globals);
		datasets[0] = pie.dataset;
		pl = (PieLegend) legend;
		pl.dataset = pie.dataset;
		numberOfDatasets = 1;
	}
	
	/**
	 *	Creates a Dataset and PieLegend based on this value and label array
	 * @param	s		Dataset name
	 * @param	y		Array of pie values
	 * @param	dataLabels	Pie slice labels
	 */
	public void addDataset(String s, double y[], String dataLabels[]) {
		PieLegend pl;

		pie.dataset = new Dataset(s, y, dataLabels, true, globals);
		datasets[0] = pie.dataset;
		pl = (PieLegend) legend;
		pl.dataset = pie.dataset;
		numberOfDatasets = 1;
	}
	
	/**
	 *	Draws the entire chart to the predefined Graphics class
	 */
	public void drawGraph() {
		if (canvas == null)
			return;
		drawGraph(canvas);
	}
	
	/**
	 *	Draws the entire chart on the specified Graphics class
	 * @param	g	Graphics class to draw
	 */
	public void drawGraph(Graphics g) {
		if (g == null)
			return;
		super.drawGraph();
		background.draw(g);
		pie.draw(g);
		if (legendVisible)
			legend.draw(g);
	}
	
	/**
	 *	This chart's Pie component
	 */
	public Pie getPie() {
		return pie;
	}
	protected void initChart() {
		initGlobals();
		setBackground(new Background());
		setPlotarea(new Plotarea());
		initDatasets();
		pie = new Pie();
		setDataRepresentation(pie);
		setLegend(new PieLegend());
		legend.setLlX(0.0);
		legend.setLlY(0.0);
		legend.setVerticalLayout(false);
		resize(640, 480);
	}
	
	/**
	 * Overrides Chart to assign the Pie's lineGc Globals class.
	 * @param dr javachart.chart.DataRepresentation
	 */
	public void setDataRepresentation(DataRepresentation dr) {
		pie.lineGc.globals = globals;
		super.setDataRepresentation(dr);
	}
	
	/**
	 *	Install a new Pie class for this chart
	 */
	public void setPie(Pie p) {
		pie = p;
		setDataRepresentation(p);
	}
	
	/**
	 * A utility method for setting slice colors.  This is the equivalent of
	 * getDatasets()[0].getDataElementAt(slice).getGc().setFillColor(color);
	 * @param slice int
	 * @param color Color
	 */
	public void setSliceColor(int slice, Color color) {
		try {
			datasets[0].getDataElementAt(slice).getGc().setFillColor(color);
		} catch (Exception e) {
			//nothing - probably no slice
		}
	}

	//
	// Local attributes
	//
	protected Pie pie;
}