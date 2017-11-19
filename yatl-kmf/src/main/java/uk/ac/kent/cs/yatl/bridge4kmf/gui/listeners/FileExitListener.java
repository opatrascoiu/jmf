package uk.ac.kent.cs.yatl.bridge4kmf.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uk.ac.kent.cs.yatl.bridge4kmf.gui.YatlStudio;

/**
 * @author Octavian Patrascoiu
 *
 */
public class FileExitListener implements ActionListener {
	public FileExitListener(YatlStudio studio) {
		this.studio = studio;
	}

	public void actionPerformed(ActionEvent e) {
		action();
	}
	
	public void action() {
	}

	/** Exit */
	protected void exitFileAction(ActionEvent e) {
		studio.exit();
	}
	
	//
	// Local properties
	//
	protected YatlStudio studio;
}
