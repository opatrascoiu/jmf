/*
 * Created on Feb 14, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package uk.ac.kent.cs.yatl.bridge4kmf.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uk.ac.kent.cs.yatl.bridge4kmf.gui.YatlStudio;

/**
 * @author Octavian Patrascoiu
 *
 */
public class FileHistoryListener implements ActionListener {
	public FileHistoryListener(YatlStudio studio) {
		this.studio = studio;
	}
	
	public void actionPerformed(ActionEvent e) {
		action(e);
	}
		
	public void action(ActionEvent e) {
		openHistoryProject(e);
	}
	
	/** Readme */
	protected void openHistoryProject(ActionEvent e) {
		studio.openHistoryProject(e);
	}
		
	//
	// Local properties
	//
	protected YatlStudio studio;
}
