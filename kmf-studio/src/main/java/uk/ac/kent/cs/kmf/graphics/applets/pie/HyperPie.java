package uk.ac.kent.cs.kmf.graphics.applets.pie;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Vector;

import uk.ac.kent.cs.kmf.graphics.chart.Datum;
import uk.ac.kent.cs.kmf.graphics.chart.PieChart;

public class HyperPie 
	extends PieApp 
{
	private int getDatumIndex(Datum d, int setNumber) {
		Vector dataVector;
		int i;

		if (setNumber == -1)
			return -1;

		dataVector = chart.getDatasets()[setNumber].getData();
		for (i = 0; i < dataVector.size(); i++) {
			if (dataVector.elementAt(i) == d)
				return i;
		}
		return -1;
	}
	/**
	* Adds hyperlinks to the parameters parsed.
	*/
	public void getMyOptions() {
		super.getMyOptions();
		String str = getParameter("dataset0Links");
		if (str != null) {
			String[] linkStrings = parser.getLabels(str);
			links.addElement((Object) linkStrings);
		} else {
			String[] linkStrings = new String[] {
				"Quality.htm#Excellent",
				"Quality.htm#Good"
			};
			links.addElement((Object) linkStrings);
		}
		str = getParameter("target");
		if (str != null) {
			target = str;
		}
	}
	public void handlePick(Point e) {
		int i;
		Vector list;
		Point pickpt;
		Object myObj;
		Datum myDat;
		int whichDatum = -1;
		
		if (links == null)
			return;

		PieChart b = (PieChart) chart;
		pickpt = new Point(e.x, e.y);
		list = new Vector();
		if (b.getDisplayList().contains(pickpt, list)) {
			for (int j = 0; j < list.size(); j++) {
				myObj = list.elementAt(j);
				//last item in list, manipulate as needed
				if (myObj instanceof Datum)
					whichDatum = getDatumIndex((Datum) myObj, 0);
			}
			if (whichDatum == -1)
				return;
			openLink(0, whichDatum);
		}
	}
	public void init() {
		super.init();
		chart.setUseDisplayList(true);
	}
	/**
	* Adds hyperlink handling to default mouseDown behavior.
	*/
	protected void installMouseAdapter() {
		super.installMouseAdapter();
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				handlePick(new Point(e.getX(), e.getY()));
			}
		});
	}
	private void openLink(int setNumber, int elementNumber) {
		String[] linkList;
		String s, s1, s2;

		try {
			linkList = (String[]) links.elementAt(setNumber);
			s = linkList[elementNumber];
			System.out.println("opening " + s);
			try {
				getAppletContext().showDocument(new URL(s), target);
			} catch (java.net.MalformedURLException e) {
				//relative url?
				try {
					s1 = getDocumentBase().toExternalForm();
					s2 = s1.substring(0, s1.lastIndexOf("/") + 1);
					URL myUrl = new URL(s2 + s);
					getAppletContext().showDocument(myUrl, target);
				} catch (java.net.MalformedURLException f) {
					System.out.println("couldn't open " + s);
					return;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return;
		}
	}

	//
	// Local attributes
	//
	protected Vector links = new Vector();
	protected String str;
	protected String target = "_blank";
}