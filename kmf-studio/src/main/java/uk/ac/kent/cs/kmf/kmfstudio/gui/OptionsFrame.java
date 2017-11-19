package uk.ac.kent.cs.kmf.kmfstudio.gui;

import java.io.*;
import java.util.Vector;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import uk.ac.kent.cs.kmf.common.*;
import uk.ac.kent.cs.kmf.kmfstudio.*;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;

public class OptionsFrame extends JFrame implements ItemListener {
	public OptionsFrame(Project project) {
		//--- Set project ---
		this.project = project;
		
		//--- Set the title ---
		setTitle("Options ["+project.getProjectFileName()+"]");
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

		//--- XMI ---
		projectPanel.addLabel("Input XMI file", line, column);
	    inputField = projectPanel.addTextField(project.getXmiFileName(), line, column+1);
		button = projectPanel.addButton("Browse", line, column+2);
	    button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    button.setVerticalAlignment(javax.swing.SwingConstants.TOP);
	    button.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			    chooseInputAction(evt);
			}
		});
	    line++;

		//--- LICENSE ---
		projectPanel.addLabel("License file", line, column);
	    licenseField = projectPanel.addTextField(project.getLicenseFileName(), line, column+1);
	    button = projectPanel.addButton("Browse", line, column+2);
	    button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    button.setVerticalAlignment(javax.swing.SwingConstants.TOP);
	    button.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			    chooseLicenseAction(evt);
			}
		});
	    line++;
		
		//--- PACKAGE OFFSET ---
		projectPanel.addLabel("Package offset", line, column);
	    packageOffsetField = projectPanel.addTextField(project.getPackageOffset(), line, column+1);
		line++;
		
		//--- MODEL NAME ---
		projectPanel.addLabel("Model name", line, column);
		modelNameField = projectPanel.addTextField(project.getModelName(), line, column+1);
		line++;
		
		//
		//--- Create and fill CODE tab panel ---
		//
		line = 0;
		codePanel = new MatrixPanel();

		//--- STARTUP code generation ---
	    checkStartup = new JCheckBox();
	    checkStartup.setSelected(project.isGenerateStartup());
	    codePanel.add(checkStartup, line, column);
	    codePanel.addLabel("Generate code for startup", line, column+1);
	    line++;

		//--- BROWSER code generation ---
	    checkBrowser = new JCheckBox();
	    checkBrowser.setSelected(project.isGenerateBrowser());
	    codePanel.add(checkBrowser, line, column);
	    codePanel.addLabel("Generate code for browser", line, column+1);
		line++;
		
		//--- REPOSITORY code generation ---
	    checkRepository = new JCheckBox();
	    checkRepository.setSelected(project.isGenerateRepository());
	    codePanel.add(checkRepository, line, column);
	    codePanel.addLabel("Generate code for repository", line, column+1);
		line++;
		
		//--- FACTORY code generation ---
	    checkFactory = new JCheckBox();
	    checkFactory.setSelected(project.isGenerateFactory());
	    codePanel.add(checkFactory, line, column);
	    codePanel.addLabel("Generate code for factory", line, column+1);
		line++;
		
		//--- Change line and column
	    line = 0;
	    column = column + 2;

		//--- VISITOR code generation ---
		checkVisitor = new JCheckBox();
		checkVisitor.setSelected(project.isGenerateVisitor());
		codePanel.add(checkVisitor, line, column);
		codePanel.addLabel("Generate code for visitors", line, column+1);
		line++;

		//--- HUTN visitor code generation ---
	    checkHUTNVisitor = new JCheckBox();
	    checkHUTNVisitor.setSelected(project.isGenerateHUTNVisitor());
	    codePanel.add(checkHUTNVisitor, line, column);
	    codePanel.addLabel("Generate code for HUTN visitors", line, column+1);
		line++;
		
		//--- JTree visitor code generation ---
	    checkJTreeVisitor = new JCheckBox();
	    checkJTreeVisitor.setSelected(project.isGenerateJTreeVisitor());
	    codePanel.add(checkJTreeVisitor, line, column);
	    codePanel.addLabel("Generate code for JTree visitors", line, column+1);
		line++;
		
		//--- ViewEdit visitor code generation ---
	    checkViewEditVisitor = new JCheckBox();
	    checkViewEditVisitor.setSelected(project.isGenerateViewEditVisitor());
	    codePanel.add(checkViewEditVisitor, line, column);
	    codePanel.addLabel("Generate code ViewEdit for visitors", line, column+1);
		line++;
		
		//--- XMI visitor code generation ---
	    checkXMIVisitor = new JCheckBox();
	    checkXMIVisitor.setSelected(project.isGenerateXMIVisitor());
	    codePanel.add(checkXMIVisitor, line, column);
		codePanel.addLabel("Generate code for XMI visitors", line, column+1);
		line++;
				
		//--- Change line and column
	    line = 0;
	    column = column + 2;

		//--- Bidirectional links ---
		checkBidir = new JCheckBox();
		checkBidir.setSelected(project.isGenerateBidirectional());
		codePanel.add(checkBidir, line, column);
		codePanel.addLabel("Generate code for bidirectional links", line, column+1);
		line++;

		//--- INVARIANTS code generation ---
	    checkInvariant = new JCheckBox();
	    checkInvariant.setSelected(project.isGenerateInvariant());
	    codePanel.add(checkInvariant, line, column);
	    codePanel.addLabel("Generate code for invariants", line, column+1);
		line++;
		
		//--- ID code generation ---
	    checkID = new JCheckBox();
	    checkID.setSelected(project.isGenerateID());
	    codePanel.add(checkID, line, column);
	    codePanel.addLabel("Generate ID string for objects", line, column+1);
		line++;
		
		//
		//--- Create and fill NAMING panel
		//
		namingPanel = new MatrixPanel();

		//--- Interface Prefix  ---
		line = 0;
		namingPanel.addLabel("Prefix for interfaces' name", line, column);
		interfacePrefixField = namingPanel.addTextField(project.getInterfacePrefix(), line, column+1);
		line++;

		//--- Interface Suffix  ---
		namingPanel.addLabel("Suffix for interfaces' name", line, column);
		interfaceSuffixField = namingPanel.addTextField(project.getInterfaceSuffix(), line, column+1);
		line++;

		//--- Class Prefix  ---
		namingPanel.addLabel("Prefix for classes' name", line, column);
		classPrefixField = namingPanel.addTextField(project.getClassPrefix(), line, column+1);
		line++;

		//--- Class Suffix  ---
		namingPanel.addLabel("Suffix for classes' name", line, column);
		classSuffixField = namingPanel.addTextField(project.getClassSuffix(), line, column+1);
		line++;

		//--- Change line and column
		line = 0;
		column = column + 2;

		//--- Collection interface  ---
		namingPanel.addLabel("Collection interface", line, column);
		collectionInterfaceField = namingPanel.addTextField(project.getCollectionInterface(), line, column+1);
		line++;

		//--- Collection class  ---
		namingPanel.addLabel("Collection class", line, column);
		collectionClassField = namingPanel.addTextField(project.getCollectionClass(), line, column+1);
		line++;

		//--- List interface  ---
		namingPanel.addLabel("List interface", line, column);
		listInterfaceField = namingPanel.addTextField(project.getListInterface(), line, column+1);
		line++;

		//--- List class  ---
		namingPanel.addLabel("List class", line, column);
		listClassField = namingPanel.addTextField(project.getListClass(), line, column+1);
		line++;

		//--- Set interface  ---
		namingPanel.addLabel("Set interface", line, column);
		setInterfaceField = namingPanel.addTextField(project.getSetInterface(), line, column+1);
		line++;

		//--- Set class  ---
		namingPanel.addLabel("Set class", line, column);
		setClassField = namingPanel.addTextField(project.getSetClass(), line, column+1);
		line++;

		//
		//--- Create and fill METRICS panel
		//
		metricsPanel = new MatrixPanel();
		line = 0;
		column = 0;

		//--- All Metrics  ---
		column = 0;
		metricsPanel.addLabel("All metrics", line, column);
		allMetrics = StudioGUI.allMetricSet.getMetrics();
		if (allMetrics == null) allMetrics = new Vector();
		allMetricsList = metricsPanel.addList((Vector)allMetrics, new Dimension(350, 200), line+1, column);
		JButton addButton = metricsPanel.addButton("Add", line+2, column);
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addMetricsAction(evt);
			}
		});

		
		//-- Metrics ---
		column = 0;
		metricsPanel.addLabel("Metrics", line, column+1);
		metrics = project.getMetrics();
		if (metrics == null) metrics = new Vector();
		metricsList = metricsPanel.addList((Vector)metrics, new Dimension(350, 200), line+1, column+1);
		JButton removeButton = metricsPanel.addButton("Remove", line+2, column+1);
		removeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeMetricsAction(evt);
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
		
		//--- Add CODE panel to MAIN tab
	    tabPanel.addTab("Code", new JScrollPane(codePanel));

		//--- Add NAMINGG panel to MAIN tab
		tabPanel.addTab("Naming", new JScrollPane(namingPanel));

		//--- Add METRICS panel to MAIN tab
		tabPanel.addTab("Metrics", new JScrollPane(metricsPanel));

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

	//--- Choose the input file ---
    protected void chooseInputAction(java.awt.event.ActionEvent evt) {
		//--- Create INPUT file chooser ---
	    inputChooser.setFileFilter(new BasicFileFilter(new String[] {"xml", "xmi"}));
		// Add your handling code here:
	    int returnVal = inputChooser.showOpenDialog(this);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File inputFile = inputChooser.getSelectedFile();
		    inputField.setText(inputFile.getAbsolutePath());			
		}		  
	}

	//--- Choose the licence file ---
    protected void chooseLicenseAction(java.awt.event.ActionEvent evt) {
		//--- Create INPUT file chooser ---
	    inputChooser.setFileFilter(new BasicFileFilter(new String[] {"xml"}));
		// Add your handling code here:
	    int returnVal = inputChooser.showOpenDialog(this);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File inputFile = inputChooser.getSelectedFile();
		    licenseField.setText(inputFile.getName());			
		}		  
	}
	
	//--- Add selected metrics ---
	protected void addMetricsAction(java.awt.event.ActionEvent evt) {
		//--- Get selected metrics ---
		Object selMetrics[] = allMetricsList.getSelectedValues();
		if (selMetrics != null) {
			for(int i=0; i<selMetrics.length; i++) {
				Metric m = (Metric)selMetrics[i];
				if (!metrics.contains(m)) {
					metrics.add(m);
					project.addMetric(m);
				} 
			}
			metricsList.setListData((Vector)metrics);
		}
		metricsList.clearSelection();
	}
	
	//--- Remove selected metrics ---
	protected void removeMetricsAction(java.awt.event.ActionEvent evt) {
		//--- Get selected metrics ---
		Object selMetrics[] = metricsList.getSelectedValues();
		if (selMetrics != null) {
			for(int i=0; i<selMetrics.length; i++) {
				Metric m = (Metric)selMetrics[i];
				metrics.remove(m);
				project.removeMetric(m);
			}
		}
		metricsList.setListData((Vector)metrics);
		metricsList.clearSelection();
	}

	//--- Consistency ---
	protected void consistent() {
		if (project == null) return;
		if (checkStartup.isSelected()) {
			checkBrowser.setSelected(true);				
		}
		if (checkBrowser.isSelected()) {
			checkRepository.setSelected(true);				
			checkFactory.setSelected(true);			
			checkVisitor.setSelected(true);			
			checkHUTNVisitor.setSelected(true);			
			checkJTreeVisitor.setSelected(true);			
			checkViewEditVisitor.setSelected(true);			
			checkXMIVisitor.setSelected(true);
			checkBidir.setSelected(true);
		}
		if (checkRepository.isSelected()) {
			checkFactory.setSelected(true);			
		}
		// Set options to keep consistency
		if (checkHUTNVisitor.isSelected()) {
			checkVisitor.setSelected(true);			
		}
		if (checkJTreeVisitor.isSelected()) {
			checkVisitor.setSelected(true);			
		}
		if (checkViewEditVisitor.isSelected()) {
			checkVisitor.setSelected(true);			
		}
		if (checkXMIVisitor.isSelected()) {
			checkVisitor.setSelected(true);			
		}
		if (checkInvariant.isSelected()) {
			checkVisitor.setSelected(true);			
		}
	}

	//--- Disable CODE panel ---
	protected void disableCodePanel() {
		checkStartup.setEnabled(false);
		checkBrowser.setEnabled(false);
		checkRepository.setEnabled(false);
		checkFactory.setEnabled(false);
		checkVisitor.setEnabled(false);
		checkHUTNVisitor.setEnabled(false);
		checkJTreeVisitor.setEnabled(false);
		checkViewEditVisitor.setEnabled(false);
		checkXMIVisitor.setEnabled(false);
		checkObserver.setEnabled(false);				
		checkBidir.setEnabled(false);				
		checkInvariant.setEnabled(false);				
		checkID.setEnabled(false);				
	}

	//--- Enable CODE panel ---
	protected void enableCodePanel() {
		checkStartup.setEnabled(true);
		checkBrowser.setEnabled(true);
		checkRepository.setEnabled(true);
		checkFactory.setEnabled(true);
		checkVisitor.setEnabled(true);
		checkHUTNVisitor.setEnabled(true);
		checkJTreeVisitor.setEnabled(true);
		checkViewEditVisitor.setEnabled(true);
		checkXMIVisitor.setEnabled(true);
		checkObserver.setEnabled(true);				
		checkBidir.setEnabled(true);				
		checkInvariant.setEnabled(true);				
		checkID.setEnabled(true);				
	}
	
	//--- OK action ---
    protected void okAction(java.awt.event.ActionEvent evt) {
		// Set flags in order to be consistent
		if (project == null) return;
		consistent();

		// Set options
		setOptions();
				
		// Save the options inside the file
		project.saveSettings();
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
		// Set flags to be consistent
		consistent();
		// Set options
		setOptions();
	}

	protected void setOptions() {
		// Set options inside the project
		project.setXmiFileName(inputField.getText());
		project.setLicenseFileName(licenseField.getText());
		project.setPackageOffset(packageOffsetField.getText());
		project.setModelName(modelNameField.getText());

		project.setGenerateStartup(checkStartup.isSelected());
		project.setGenerateBrowser(checkBrowser.isSelected());
		project.setGenerateRepository(checkRepository.isSelected());
		project.setGenerateFactory(checkFactory.isSelected());
		project.setGenerateVisitor(checkVisitor.isSelected());
		project.setGenerateHUTNVisitor(checkHUTNVisitor.isSelected());
		project.setGenerateJTreeVisitor(checkJTreeVisitor.isSelected());
		project.setGenerateViewEditVisitor(checkViewEditVisitor.isSelected());
		project.setGenerateXMIVisitor(checkXMIVisitor.isSelected());
		project.setGenerateBidirectional(checkBidir.isSelected());
		project.setGenerateInvariant(checkInvariant.isSelected());
		project.setGenerateID(checkID.isSelected());
		project.setGenerateObserver(checkObserver.isSelected());
		
		project.setInterfacePrefix(interfacePrefixField.getText());
		project.setInterfaceSuffix(interfaceSuffixField.getText());
		project.setClassPrefix(classPrefixField.getText());
		project.setClassSuffix(classSuffixField.getText());
		project.setCollectionInterface(collectionInterfaceField.getText());
		project.setCollectionClass(collectionClassField.getText());
		project.setListInterface(listInterfaceField.getText());
		project.setListClass(listClassField.getText());
		project.setSetInterface(setInterfaceField.getText());
		project.setSetClass(setClassField.getText());
	}
	
	/** Listens to the check boxes. */
    public void itemStateChanged(ItemEvent e) {
		//--- Get the chamged item ---
	    Object source = e.getItemSelectable();
		//--- source == checkToolGen ---
	    if (source == checkToolGen) {
			System.out.println("Change");
			if (checkToolGen.isSelected()) {
				disableCodePanel();
			} else {
				enableCodePanel();
			}
		}
	}

	protected Project project;
    protected JFileChooser inputChooser;

	protected MatrixPanel projectPanel;
	protected JTextField inputField;
	protected JTextField licenseField;	
	protected JTextField packageOffsetField;	
	protected JTextField modelNameField;	
    protected JCheckBox checkToolGen;

	protected MatrixPanel codePanel;
    protected JCheckBox checkStartup;
    protected JCheckBox checkRepository;
    protected JCheckBox checkBrowser;
    protected JCheckBox checkFactory;
    protected JCheckBox checkVisitor;
    protected JCheckBox checkHUTNVisitor;
    protected JCheckBox checkJTreeVisitor;
    protected JCheckBox checkViewEditVisitor;
    protected JCheckBox checkXMIVisitor;
    protected JCheckBox checkObserver = new JCheckBox();
	protected JCheckBox checkBidir;
    protected JCheckBox checkInvariant;
    protected JCheckBox checkID;

	protected MatrixPanel namingPanel;
	protected JTextField interfacePrefixField;
	protected JTextField interfaceSuffixField;
	protected JTextField classPrefixField;
	protected JTextField classSuffixField;
	protected JTextField collectionInterfaceField;	
	protected JTextField collectionClassField;	
	protected JTextField listInterfaceField;	
	protected JTextField listClassField;	
	protected JTextField setInterfaceField;	
	protected JTextField setClassField;	

	protected MatrixPanel metricsPanel;
	protected JList allMetricsList;
	protected java.util.List allMetrics;
	protected JList metricsList;
	protected java.util.List metrics;
}
