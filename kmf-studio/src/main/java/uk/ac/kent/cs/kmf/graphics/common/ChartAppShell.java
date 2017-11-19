/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.common;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.util.Vector;

import uk.ac.kent.cs.kmf.graphics.chart.ChartInterface;
import uk.ac.kent.cs.kmf.graphics.chart.Datum;
import uk.ac.kent.cs.kmf.graphics.chart.Gc;

public abstract class ChartAppShell
	extends Applet
	implements Runnable, GetParam 
{
	// set to true to avoid message and dot
	protected boolean home = true;

	public ChartInterface chart;
	protected ParameterParser parser;

	protected Thread getThread;
	protected int networkInterval = -1;
	protected MediaTracker imageTracker;
	protected boolean gotImages;

	//	for copyright notice
	static MessageFrame messageFrame;
	protected boolean useDwellLabel = true;
	protected NumberFormat dwellLabelFormat = null;
	//initialize after setting default Locale
	protected boolean dwellUseXValue = true;
	protected boolean dwellUseYValue = true;
	protected boolean dwellUseString = false;
	protected String dwellXString, dwellYString;

	//for timing the dwell labels
	private int secondsSoFar = 0;

	protected Vector displayList = new Vector();
	protected boolean showDataPopup = false;
	protected boolean dwellLabelVisible = false;
	protected int popupX = 0;
	protected int popupY = 0;
	protected String dwellLabelXString, dwellLabelYString, dwellLabelLabelString;
	public boolean closeURL(InputStream myInputStream) {
		try {
			myInputStream.close();
		} catch (java.io.IOException e) {
			System.out.println("can't close URL");
			return false;
		}
		return true;
	}
	
	/**
	 * cleans up applet resources.
	 */
	public void destroy() {
		stop();
		chart.setImage(null);
		chart = null;
		if (messageFrame != null)
			messageFrame.dispose();
	}
	protected void displayInfo(Vector list) {
		for (int j = 0; j < list.size(); j++) {
			Object myObj = list.elementAt(j);
			if (myObj instanceof Datum) {
				dwellLabelXString = getDwellLabelXString((Datum) myObj);
				dwellLabelYString = getDwellLabelYString((Datum) myObj);
				dwellLabelLabelString = getDwellLabelLabelString((Datum) myObj);
				showDataPopup = true;
				repaint();
				return;
			}
		}
	}
	protected void doDwellLabel() {
		Vector list;
		Point pickpt;

		if ((dwellLabelVisible) || (!useDwellLabel))
			return;
		pickpt = new Point(popupX, popupY);
		displayList.removeAllElements();
		if (chart.getDisplayList().contains(pickpt, displayList)) {
			displayInfo(displayList);
		}
	}
	void doMessage() {
		if (messageFrame == null) {
			messageFrame = new MessageFrame();
			messageFrame.setAppletContext(getAppletContext());
		}
		messageFrame.setVisible(true);
	}
	protected abstract void drawDataPopup(Graphics g);
	public void drawMyStuff(Graphics g) {
		/* draws dot & message... meant to be overloaded by 
		user function.  This method is called after drawing the chart
		*/
		if (home)
			return;
		g.setColor(Color.blue);
		g.fillRect(getSize().width - 20, getSize().height - 20, 5, 5);
	}
	protected String getDwellLabelLabelString(Datum d) {
		String s = d.getLabel();
		return s;
	}
	protected abstract String getDwellLabelXString(Datum d);	
	protected String getDwellLabelYString(Datum d) {
		String s;
		if (dwellLabelFormat instanceof NumberFormat) {
			s = ((NumberFormat) dwellLabelFormat).format(d.getY());
		} else {
			return dwellLabelFormat.format(new Double(d.getY()));
		}
		int where = dwellYString.indexOf("#");
		return dwellYString.substring(0, where)
			+ s
			+ dwellYString.substring(where + 1);
	}
	public void getMyDatasets(String s) {
		/* doesn't do anything here... meant to be overloaded by 
		user function.  This function must perform create an x, y, y2
		array, and then call getDatasetParameter(setnumber,x,y,y2,y3)
		*/
	}
	protected abstract void getOptions(); 
	public abstract void getMyOptions();
	/**
	 * Initializes the locale - must be set before creating a Chart to get correct default formatting
	 * on Axes, labels, etc.  Also initializes a defaultFont for all charts.
	 */
	protected void initLocale() {
		String s = getParameter("defaultFont");
		if (s != null)
			Gc.defaultFont = ParameterParser.getFont(s);
		ParameterParser.setLocale(getParameter("locale"));
	}
	/**
	 * Registers this applet to listen for mouse motion and click events.
	 */
	protected void installMouseAdapter() {
//		addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (!home)
//					doMessage();
//			}
//		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				if (showDataPopup == true) {
					if ((Math.abs(e.getX() - popupX) > 3)
						|| (Math.abs(e.getY() - popupY) > 3)) {
						showDataPopup = false;
						repaint();
					}
				}
				popupX = e.getX();
				popupY = e.getY();
			}
		});
	}
	/*
	protected String[] getLabels(String s) {
		String	labels[];
		int i, j;
		int start, end;
		 	
		i = 0;
		j = 0;
		while(i != -1) {
		 	i = s.indexOf(delimiter, i+1);
		 	j++;
		}
		labels = new String[j];
		
		start = 0;
		for(i=0;i<j-1;i++) {
		 	end = s.indexOf(delimiter, start);
		 	labels[i] = s.substring(start, end);
		 	start = end + 1;
		}
		labels[i] = s.substring(start);
		return labels;
	}
	*/

	public Image makeURLImage(String s) {
		Image img;

		img = getImage(getCodeBase(), s);
		if (!gotImages) {
			imageTracker = new MediaTracker(this);
			gotImages = true;
		}
		imageTracker.addImage(img, 0);
		return img;
	}
	//URL convenience methods
	public InputStream openURL(String s) {
		String s1, s2;
		URLConnection connection;
		InputStream myInputStream;
		URL myUrl;

		// Open a URL
		try {
			myUrl = new URL(s);
		} catch (java.net.MalformedURLException e) {
			//relative url?
			try {
				s1 = getDocumentBase().toExternalForm();
				s2 = s1.substring(0, s1.lastIndexOf("/") + 1);
				myUrl = new URL(s2 + s);
			} catch (java.net.MalformedURLException f) {
				System.out.println("couldn't open " + s);
				return null;
			}
		}

		// Try to get an input stream
		try {
			connection = myUrl.openConnection();
			connection.setUseCaches(false);
			myInputStream = connection.getInputStream();
		} catch (java.io.IOException e) {
			System.out.println("can't open stream " + s);
			return null;
		}
		return myInputStream;
	}
	public abstract void paint(Graphics g);
	/**
	 * overrides default print to attempt to work around some Netscape bugs.
	 * @param g java.awt.Graphics
	 */
	public void print(Graphics g) {
		String s = System.getSecurityManager().toString();
		if (s.indexOf("etscape") != -1) {
			System.out.println("correcting for Netscape sizing bug");
			double scaleFactor = 0.75;
			s = getParameter("netscapePrintScaleFactor");
			if (s != null) {
				scaleFactor = Double.valueOf(s).doubleValue();
			}
			Dimension d =
				new Dimension(
					(int) (getSize().width * scaleFactor),
					(int) (getSize().height * scaleFactor));
			chart.resize(d.width, d.height);
			if (getParameter("noRotations") != null) {
				chart.drawGraph(g);
			} else {
				Image tmpImage = createImage(d.width, d.height);
				Image saveImage = chart.getImage();
				chart.setImage(tmpImage);
				chart.drawGraph(tmpImage.getGraphics());
				g.drawImage(tmpImage, 0, 0, null);
				chart.setImage(saveImage);
			}
			chart.resize(getSize().width, getSize().height);
		} else
			//chart.drawGraph(g);
			g.drawImage(chart.getImage(), 0, 0, null);
	}
	protected void reReadURLDatasets() {
		parser.reReadURLDatasets();
	}
	public void run() {
		while (true) {
			//sleep first, because it draws on initial paint
			try {
				Thread.sleep(1000);
				if ((networkInterval != -1)
					&& (secondsSoFar > networkInterval)) {
					secondsSoFar = 0;
					reReadURLDatasets();
					showDataPopup = false;
					repaint();
				} else {
					doDwellLabel();
					secondsSoFar++;
				}
			} catch (InterruptedException e) {
			}
		}
	}
	public void start() {
		if (useDwellLabel)
			chart.setUseDisplayList(true);
		else
			chart.setUseDisplayList(false);
		if (getThread == null) {
			getThread = new Thread(this);
			getThread.start();
		}
	}
	public void stop() {
		if (getThread != null) {
			getThread.stop();
			getThread = null;
		}
	}
	public void update(Graphics g) {
		paint(g);
	}
}