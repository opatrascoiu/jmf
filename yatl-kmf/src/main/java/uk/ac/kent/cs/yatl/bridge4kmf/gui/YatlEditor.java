package uk.ac.kent.cs.yatl.bridge4kmf.gui;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import uk.ac.kent.cs.kmf.util.ConsoleLog;
import uk.ac.kent.cs.kmf.util.ILog;

/**
 * @author Octavian Patrascoiu
 *
 */
public class YatlEditor
	extends JInternalFrame
{
	//
	// Constructors
	//
	public YatlEditor(File file, ILog log) {
		this.file = file;
		this.log = log;
		this.setSize(new Dimension(750, 700));
		this.setClosable(true);
		this.setIconifiable(true);
		this.setMaximizable(true);
		this.setResizable(true);
		this.
		initComponents();
		setVisible(true);
	}

	protected void initComponents() {
		// Load file
		String text = "";
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while ((line = buffer.readLine()) != null) {
				text += line+"\n";
			}
		} catch (Exception e) {
		}
		JScrollPane scroll = new JScrollPane();
		textArea = new JTextArea(text);
		scroll.setViewportView(textArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		// Add console
		getContentPane().add(scroll);
	}
	
	public String getAbsolutePath() {
		return file.getAbsolutePath();
	}
	
	public String getFileName() {
		return file.getName();
	}
	
	public String getText() {
		return textArea.getText();
	}
	
	public void save(String fileName) {
		try {
			file = new File(fileName);
		} catch (Exception e) {
			log.reportMessage("Cannot save file '"+fileName+"'");
		}
	}
	
	//
	// Local properties
	//
	protected File file;
	protected ILog log = new ConsoleLog();
	protected JTextArea textArea;
}
