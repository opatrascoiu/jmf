package uk.ac.kent.cs.yatl.bridge4kmf.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uk.ac.kent.cs.yatl.bridge4kmf.gui.YatlStudio;

/**
 * @author Octavian Patrascoiu
 *
 */
public class FileSaveAsListener implements ActionListener {
	public FileSaveAsListener(YatlStudio studio) {
		this.studio = studio;
	}

	public void actionPerformed(ActionEvent e) {
		action();
	}
	
	public void action() {
		studio.saveProjectAs();
	}

	//
	// Local properties
	//
	protected YatlStudio studio;
}
