/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

/**
 *	Provides a standard set of methods for dealing with chart Legends in 
 *	a uniform way.  Using a LegendInterface rather than a specific 
 *	Legend instance provides more flexibility in creating and managing 
 *	subclasses and user interfaces.
 *@see	javachart.chart.Legend
 *@see	javachart.chart.LineLegend
 *@see	javachart.chart.PieLegend
 */

public interface LegendInterface {

	public void draw(Graphics g);
	//Accessors
	public Gc getBackgroundGc();
	public boolean getBackgroundVisible();
	public double getIconGap();
	public double getIconHeight();
	public double getIconWidth();
	public Color getLabelColor();
	public Font getLabelFont();
	public double getLlX();
	public double getLlY();
	public double getUrX();
	public double getUrY();
	public boolean getUseDisplayList();
	public boolean getVerticalLayout();
	public void resize(int w, int h);
	public void setBackgroundGC(Gc g);
	public void setBackgroundVisible(boolean vis);
	public void setIconGap(double d);
	public void setIconHeight(double d);
	public void setIconWidth(double d);
	public void setLabelColor(Color c);
	public void setLabelFont(Font f);
	public void setLlX(double d);
	public void setLlY(double d);
	public void setUseDisplayList(boolean onOff);
	public void setVerticalLayout(boolean trueFalse);
	public String toString();
}