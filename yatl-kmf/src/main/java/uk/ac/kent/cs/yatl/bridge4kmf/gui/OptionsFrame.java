package uk.ac.kent.cs.yatl.bridge4kmf.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import uk.ac.kent.cs.kmf.common.MatrixPanel;

public class OptionsFrame extends JFrame {
	public OptionsFrame(Project project) {
		//--- Set project ---
		this.project = project;
		
		//--- Set the title ---
		setTitle("Options ["+project.getProjectPathName()+"]");
		setContentPane(new MatrixPanel());
		
		//--- Create and fill TAB panel --
		JTabbedPane tabPanel = new JTabbedPane();

		//
		//--- Create and fill PROJECT panel
		//
		projectPanel = new MatrixPanel();
		//--- Initialize common variables ---
		int line = 0;
		int column = 0;
		JLabel label;
		JButton button;
	    inputChooser = new JFileChooser();
		try {
			inputChooser.setCurrentDirectory(new File("C:/D/Work/Java Projects/YATL4KMF/src/test/scripts"));
		} catch (Exception e) {
		}

		//--- PROJECT ---
		projectPanel.addLabel("Project file", line, column);
		projectNameField = projectPanel.addTextField(project.getProjectPathName(), line, column+1);
		line++;

		//--- INPUT ---
		projectPanel.addLabel("Source model file", line, column);
		sourceModelField = projectPanel.addTextField(project.getSourceModelFileName(), line, column+1);
		button = projectPanel.addButton("Browse", line, column+2);
		button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		button.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chooseSourceModelAction(evt);
			}
		});
		line++;
		projectPanel.addLabel("Source repository class name", line, column);
		sourceRepositoryClassField = projectPanel.addTextField(project.getSourceRepClassName(), line, column+1);
		line++;
		projectPanel.addLabel("Source repository file", line, column);
	    sourceRepositoryContentField = projectPanel.addTextField(project.getSourceRepFileName(), line, column+1);
		button = projectPanel.addButton("Browse", line, column+2);
	    button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    button.setVerticalAlignment(javax.swing.SwingConstants.TOP);
	    button.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			    chooseSourceRepositoryAction(evt);
			}
		});
	    line++;

		//--- Transformation ---
		projectPanel.addLabel("Transformation file", line, column);
		transfFileField = projectPanel.addTextField(project.getTransfFileName(), line, column+1);
		button = projectPanel.addButton("Browse", line, column+2);
		button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		button.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chooseTransformationAction(evt);
			}
		});
		line++;

		//--- OUTPUT ---
		projectPanel.addLabel("Target model file", line, column);
		targetModelField = projectPanel.addTextField(project.getTargetModelFileName(), line, column+1);
		button = projectPanel.addButton("Browse", line, column+2);
		button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		button.setVerticalAlignment(javax.swing.SwingConstants.TOP);
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chooseTargetModelAction(evt);
			}
		});
		line++;
		projectPanel.addLabel("Target repository class name", line, column);
		targetRepositoryClassField = projectPanel.addTextField(project.getTargetRepClassName(), line, column+1);
		line++;
		projectPanel.addLabel("Target repository file", line, column);
	    targetRepositoryContentField = projectPanel.addTextField(project.getTargetRepFileName(), line, column+1);
	    button = projectPanel.addButton("Browse", line, column+2);
	    button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    button.setVerticalAlignment(javax.swing.SwingConstants.TOP);
	    button.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			    chooseTargetRepositoryAction(evt);
			}
		});
	    line++;
				
		//
		//--- Create and fill CONFIRM panel ---
		//
	    MatrixPanel confirmPanel = new MatrixPanel();
		//--- OK button ---
		JButton okButton = confirmPanel.addButton("OK", 0, 0);
	    okButton.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			    okAction(evt);
			}
		});

		//--- Cancel button ---
		JButton cancelButton = confirmPanel.addButton("Cancel", 0, 1);
	    cancelButton.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			    cancelAction(evt);
			}
		});

		//--- Apply button ---
		JButton applyButton = confirmPanel.addButton("Apply", 0, 2);
	    applyButton.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			    applyAction(evt);
			}
		});

		//--- Add PROJECT panel to TAB panel ---
	    tabPanel.addTab("General", projectPanel);
		
		//--- Add TAB panel to MAIN panel ---
		((MatrixPanel)getContentPane()).add(tabPanel, 0, 0);

		//--- Add CONFIRM to MAIN panel ---		
		((MatrixPanel)getContentPane()).add(confirmPanel, 1, 0, GridBagConstraints.CENTER);
		
		// Add MAIN panel to frame
		((MatrixPanel)getContentPane()).add(tabPanel, 0, 0);

		// Put frame in the middle of the screen
	    pack();
	    Dimension screen = getToolkit().getScreenSize();
	    int width  = this.getSize().width+20;
	    int height = this.getSize().height+20;
	    int left = (screen.width - width)/2;
	    int top = (screen.height - height)/2;
	    setBounds(left, top, width, height);
	    show();
	}

	//--- Choose the source file ---
	protected void chooseSourceModelAction(java.awt.event.ActionEvent evt) {
		//--- Create INPUT file chooser ---
		inputChooser.setFileFilter(new BasicFileFilter(new String[] {"xml", "xmi"}));
		// Add your handling code here:
		int returnVal = inputChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File inputFile = inputChooser.getSelectedFile();
			sourceModelField.setText(inputFile.getAbsolutePath());			
			inputChooser.setCurrentDirectory(inputFile.getParentFile());
		}		  
	}
    protected void chooseSourceRepositoryAction(java.awt.event.ActionEvent evt) {
		//--- Create INPUT file chooser ---
	    inputChooser.setFileFilter(new BasicFileFilter(new String[] {"xml", "xmi"}));
		// Add your handling code here:
	    int returnVal = inputChooser.showOpenDialog(this);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File inputFile = inputChooser.getSelectedFile();
		    sourceRepositoryContentField.setText(inputFile.getAbsolutePath());			
			inputChooser.setCurrentDirectory(inputFile.getParentFile());
		}		  
	}

	//--- Choose the transformation file ---
	protected void chooseTransformationAction(java.awt.event.ActionEvent evt) {
		//--- Create INPUT file chooser ---
		inputChooser.setFileFilter(new BasicFileFilter(new String[] {"tl"}));
		// Add your handling code here:
		int returnVal = inputChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File inputFile = inputChooser.getSelectedFile();
			transfFileField.setText(inputFile.getAbsolutePath());			
			inputChooser.setCurrentDirectory(inputFile.getParentFile());
		}		  
	}

	//--- Choose the target file ---
	protected void chooseTargetModelAction(java.awt.event.ActionEvent evt) {
		//--- Create INPUT file chooser ---
		inputChooser.setFileFilter(new BasicFileFilter(new String[] {"xml", "xmi"}));
		// Add your handling code here:
		int returnVal = inputChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File inputFile = inputChooser.getSelectedFile();
			targetModelField.setText(inputFile.getAbsolutePath());			
			inputChooser.setCurrentDirectory(inputFile.getParentFile());
		}		  
	}
	protected void chooseTargetRepositoryAction(java.awt.event.ActionEvent evt) {
		//--- Create INPUT file chooser ---
		inputChooser.setFileFilter(new BasicFileFilter(new String[] {"xml", "xmi"}));
		// Add your handling code here:
		int returnVal = inputChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File inputFile = inputChooser.getSelectedFile();
			targetRepositoryContentField.setText(inputFile.getAbsolutePath());			
			inputChooser.setCurrentDirectory(inputFile.getParentFile());
		}		  
	}

	//--- OK action ---
    protected void okAction(java.awt.event.ActionEvent evt) {
		// Set flags in order to be consistent
		if (project == null) return;
		// Set options
		setOptions();
		// Save the options inside the file
		project.saveProject();
		// Close window
		dispose();
	}

	//--- Cancel action ---
    protected void cancelAction(java.awt.event.ActionEvent evt) {
		dispose();
	}

	//--- Apply action ---
    protected void applyAction(java.awt.event.ActionEvent evt) {
		// Set flags in order to be consistent
		if (project == null) return;
		// Set options
		setOptions();
	}

	protected void setOptions() {
		// Set options inside the project
		project.setProjectPathName(projectNameField.getText());
		project.setSourceModelFileName(sourceModelField.getText());
		project.setSourceRepClassName(sourceRepositoryClassField.getText());
		project.setSourceRepFileName(sourceRepositoryContentField.getText());
		project.setTransfFileName(transfFileField.getText());
		project.setTargetModelFileName(targetModelField.getText());
		project.setTargetRepClassName(targetRepositoryClassField.getText());
		project.setTargetRepFileName(targetRepositoryContentField.getText());
	}
	
	protected Project project;
    protected JFileChooser inputChooser;

	protected MatrixPanel projectPanel;
	protected JTextField projectNameField;
	protected JTextField sourceModelField;
	protected JTextField sourceRepositoryClassField;
	protected JTextField sourceRepositoryContentField;
	protected JTextField transfFileField;
	protected JTextField targetModelField;
	protected JTextField targetRepositoryClassField;
	protected JTextField targetRepositoryContentField;
}
