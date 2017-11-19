/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.common;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.URL;

import uk.ac.kent.cs.kmf.graphics.chart.Transform;

public class MessageFrame extends Frame implements ActionListener {
	AppletContext appletContext;
	Button okBut, jumpBut;
	Panel buttonPanel;
	Transform logoTransform;

	/** Stuff to draw logo **/

	static double poly1X[] = { 0.12, 0.375, 0.325, 0.050 };

	static double poly1Y[] = { 0.925, 0.2, 0.035, 0.795 };

	static double poly2X[] = { 0.53, 0.66, 0.393, 0.335 };

	static double poly2Y[] = { 0.95, 0.97, 0.235, 0.4 };

	static double poly3X[] = { 0.445, 0.49, 0.95, 0.90 };

	static double poly3Y[] = { 0.285, 0.41, 0.41, 0.285 };

	static double poly4X[] = { 0.54, 0.585, 1.06, 1.01 };

	static double poly4Y[] = { 0.554, 0.681, 0.681, 0.554 };

	static double poly5X[] = { 0.635, 0.675, 1.16, 1.116 };

	static double poly5Y[] = { 0.822, 0.935, 0.935, 0.822 };

	public MessageFrame() {
		super();

		setLayout(new BorderLayout());
		buttonPanel = new Panel();
		add("South", buttonPanel);

		buttonPanel.setLayout(new FlowLayout());
		okBut = new Button("OK");
		buttonPanel.add(okBut);
		jumpBut = new Button("Tell me more...");
		buttonPanel.add(jumpBut);

		okBut.addActionListener(this);
		jumpBut.addActionListener(this);
		//addWindowListener(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		setTitle("Chart Information");
		setSize(450, 200);

		logoTransform = new Transform(0.0, 0.0, 1.2, 1.2, 390, 50, 440, 100);
		setResizable(false);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okBut)
			setVisible(false);
		if (e.getSource() == jumpBut) {
			if (appletContext != null)
				try {
					appletContext.showDocument(
						new URL("http://www.ve.com/kavachart/redirectIndex.html"),
						"_blank");
				} catch (Exception er) {
					System.out.println("Can't open www.ve.com");
				}
		}

	}
	static void drawLogo(
		Graphics g,
		Component onWhat,
		uk.ac.kent.cs.kmf.graphics.chart.Transform t) {
		g.setColor(Color.blue);
		fillPolygon(g, poly1X, poly1Y, onWhat, t);
		fillPolygon(g, poly2X, poly2Y, onWhat, t);
		fillPolygon(g, poly3X, poly3Y, onWhat, t);
		fillPolygon(g, poly4X, poly4Y, onWhat, t);
		fillPolygon(g, poly5X, poly5Y, onWhat, t);
	}
	static void fillPolygon(
		Graphics g,
		double[] xArray,
		double[] yArray,
		Component onWhat,
		Transform t) {
		int[] xArr = new int[xArray.length];
		int[] yArr = new int[yArray.length];
		Point[] pointlist = t.pointList(xArray, yArray);
		for (int i = 0; i < xArray.length; i++) {
			xArr[i] = pointlist[i].x;
			yArr[i] = onWhat.getSize().height - 20 - pointlist[i].y;
		}

		//		System.out.println("{");
		//		for(int i=0;i<xArray.length;i++)
		//			System.out.println(xArr[i] + ",");
		//		System.out.println("};");
		//		System.out.println("{");
		//		for(int i=0;i<xArray.length;i++)
		//			System.out.println(yArr[i] + ",");
		//		System.out.println("};");

		g.fillPolygon(xArr, yArr, xArr.length);
	}
	/**
	 * This method was created in VisualAge.
	 * @param args java.lang.String[]
	 */
	public static void main(String args[]) {
		MessageFrame mf = new MessageFrame();
		mf.setVisible(true);
	}
	public void paint(Graphics g) {
		FontMetrics fm;

		fm = g.getFontMetrics();
		g.setColor(Color.white);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setColor(Color.black);
		int yPos = 70;
		g.drawString(
			"Free JavaChart applets copyright (c) 1998 - 2000, Visual Engineering, Inc.",
			10,
			yPos);
		yPos += fm.getMaxAscent() + 5;
		g.drawString(
			"Commercial and educational versions also available at minimal cost",
			10,
			yPos);
		yPos += fm.getMaxAscent() + 5;
		g.drawString(
			"Contact us at (650) 856-5694, info@ve.com, www.ve.com",
			10,
			yPos);
		paintComponents(g);
		drawLogo(g, this, logoTransform);
	}
	public void setAppletContext(AppletContext a) {
		appletContext = a;
	}
}