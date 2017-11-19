package uk.ac.kent.cs.kmf.kmfstudio.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uk.ac.kent.cs.kmf.common.History;
import uk.ac.kent.cs.kmf.kmfstudio.CodeGenerator;
import uk.ac.kent.cs.kmf.kmfstudio.CodeGeneratorImpl;
import uk.ac.kent.cs.kmf.kmfstudio.NamingImpl;
import uk.ac.kent.cs.kmf.kmfstudio.Project;
import uk.ac.kent.cs.kmf.kmfstudio.ProjectImpl;
import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.kent.cs.kmf.quality.metrics.MetricSet;
import uk.ac.kent.cs.kmf.quality.viewers.Viewer;
import uk.ac.kent.cs.kmf.util.ConsoleLog;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;
import uk.ac.kent.cs.kmf.util.Pair;
import uk.ac.kent.cs.kmf.util.XMIToUMLLoader;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.bridge4kmf.KmfOclProcessorImpl;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

public class StudioGUI extends JFrame {
    //--- Look&feel constants ---
    public static final Font font = new Font("dialog", Font.PLAIN, 12);
    public static final Dimension listSize = new Dimension(215, 6*font.getSize());
    public static final Dimension fieldSize = new Dimension(200, 3*font.getSize());
    public static final Dimension wndSize = new Dimension(600, 600);
    //--- Meta model and meta ocl processor ---
    public static ILog staticLog = new OutputStreamLog(System.out);
    public static Model metaModel = loadMetaModel();
    public static OclProcessor metaOclProcessor = new KmfOclProcessorImpl(metaModel, staticLog);
    public static MetricSet allMetricSet = getAllMetricSet();

    //--- Layout panels ---
    protected JSplitPane mainPanel;
    protected JSplitPane leftPanel;
    protected JSplitPane rightPanel;
    protected Dimension screenSize;
    protected int barHeight = 70;
    protected double mainDiv = 0.25;
    protected double rightDiv = 0.70;
    protected int dividerSize = 4;
    
    //--- Projects ---
    protected java.util.List projects;
    protected uk.ac.kent.cs.kmf.kmfstudio.Project crtProject;
    
    //--- Explorer tree ---
    protected DefaultTreeModel projectsModel;
    protected JTree projectsTree;

	//--- Desktop ---
	protected JTabbedPane desktopPane;
	
    //--- Console ---
    protected ConsoleLog console = new ConsoleLog();

	//--- Project choosers ---
	protected JFileChooser openChooser;
	protected JFileChooser saveChooser;
	
	//--- History ---
	protected History history = new History();
	
    //--- Construct the BROWSER ---
    public StudioGUI() {
        //--- Set title and size to full screen ---
        super("KMFStudio");
        screenSize = getToolkit().getScreenSize();
        screenSize.setSize(screenSize.width, screenSize.height);
        setSize(screenSize);
		
        getContentPane().setLayout(new BorderLayout());

		//--- Init metamodel and meta processsor ---

		//--- Load history ---
		history.load();
		
        //--- Create MENU ---
        JMenuBar menuBar = createMenuBar();
        getContentPane().add(menuBar, BorderLayout.NORTH);

        //--- Create EXPLORER ---
        JInternalFrame explorerFrame = createExplorer();

        //--- Create DESKTOP ---
        desktopPane = createDesktop();

        //--- Create OUTPUT ---
        JInternalFrame consoleFrame = createConsole();

        //--- Create SPLIT PANELS ---
        mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainPanel.setDividerSize(dividerSize);
        mainPanel.setDividerLocation((int)(screenSize.width*mainDiv));
        mainPanel.setContinuousLayout(true);
        leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        leftPanel.setDividerSize(dividerSize);
        leftPanel.setContinuousLayout(true);
        rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        rightPanel.setDividerSize(dividerSize);
        rightPanel.setDividerLocation((int)(screenSize.height*rightDiv));
        rightPanel.setContinuousLayout(true);

        //--- Add components to SPLIT PANELS ---
        leftPanel.setTopComponent(explorerFrame);
        rightPanel.setTopComponent(desktopPane);
        rightPanel.setBottomComponent(consoleFrame);
        mainPanel.setLeftComponent(leftPanel);
        mainPanel.setRightComponent(rightPanel);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

		//--- Initilize projects ---
		projects = new Vector();

		//--- Initialize choosers ---
    	openChooser = new JFileChooser();
        openChooser.setDialogTitle("Open Project");
        openChooser.setApproveButtonText("Open");
        openChooser.setApproveButtonToolTipText("Open existing project");
        openChooser.setFileFilter(new BasicFileFilter(new String[] {"ksp"}));
		try {
			openChooser.setCurrentDirectory(new File("C:/D/Work/Java Projects"));
		} catch (Exception e) {
		}
    	saveChooser = new JFileChooser();
        saveChooser.setDialogTitle("Save As");
        saveChooser.setApproveButtonText("Save");
        saveChooser.setApproveButtonToolTipText("Save current project");
        saveChooser.setFileFilter(new BasicFileFilter(new String[] {"ksp"}));
		try {
			saveChooser.setCurrentDirectory(new File("C:/D/Work/Java Projects"));
		} catch (Exception e) {
		}

        //--- Add listener for window closing event ---
        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	// Save history
            	history.save();
                System.exit(0);
            }
        };
        addWindowListener(wndCloser);
        
        show();        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
      *   Create layout - functions
      */
    //--- Create the MENU ---
    protected JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        //--- Add FILE items ---
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('f');
        //--- Add NEW ---
        JMenuItem item = new JMenuItem("New Project ...");
        item.setMnemonic('n');
        ActionListener lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newFileAction(e);
            }
        };
        item.addActionListener(lst);
        fileMenu.add(item);
        //--- Add a separator ---
        fileMenu.addSeparator();
        //--- Add OPEN item ---
        item = new JMenuItem("Open Project ...");
        item.setMnemonic('o');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFileAction(e);
            }
        };
        item.addActionListener(lst);
        fileMenu.add(item);
        //--- Add a separator ---
        fileMenu.addSeparator();
        //--- Add CLOSE item ---
        item = new JMenuItem("Close Project");
        item.setMnemonic('c');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeFileAction(e);
            }
        };
        item.addActionListener(lst);
        fileMenu.add(item);
        //--- Add CLOSE item ---
        item = new JMenuItem("Close All");
        item.setMnemonic('a');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeAllFileAction(e);
            }
        };
        item.addActionListener(lst);
        fileMenu.add(item);
        //--- Add a separator ---
        fileMenu.addSeparator();
        // Add SAVE item
        item = new JMenuItem("Save Project");
        item.setMnemonic('s');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFileAction(e);
            }
        };
        item.addActionListener(lst);
        fileMenu.add(item);
        // Add SAVE AS item
        item = new JMenuItem("Save Project As ...");
        item.setMnemonic('a');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAsFileAction(e);
            }
        };
        item.addActionListener(lst);
        fileMenu.add(item);
        //--- Add history
        if (!history.isEmpty()) {
        	//--- Add a separator ---
        	fileMenu.addSeparator();
        	//--- Add each file ---
        	Iterator i = history.iterator();
        	while (i.hasNext()) {
		        item = new JMenuItem((String)history.next());
		        lst = new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                openHistoryFileAction(e);
		            }
		        };
        		item.addActionListener(lst);
        		fileMenu.add(item);
        	}
        }
        //--- Add a separator ---
        fileMenu.addSeparator();
        //--- Create EXIT item ---
        item = new JMenuItem("Exit");
        item.setMnemonic('x');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitFileAction(e);
            }
        };
        item.addActionListener(lst);
        fileMenu.add(item);
        //--- Add FILE ---
        menuBar.add(fileMenu);

		//--- Add QUALITY ---
		JMenu qualityMenu = new JMenu("Quality");
		qualityMenu.setMnemonic('q');
		//--- Add COMPUTE QUALITY ---
		item = new JMenuItem("Compute Metrics");
		item.setMnemonic('m');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				computeMetricsAction(e, false);
			}
		};
		item.addActionListener(lst);
		qualityMenu.add(item);
		//--- Add DISPLAY VIOLATION ---
		item = new JMenuItem("Display Violations");
		item.setMnemonic('d');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				computeMetricsAction(e, true);
			}
		};
		item.addActionListener(lst);
		qualityMenu.add(item);
		//--- Add a separator ---
		qualityMenu.addSeparator();
		//--- Add QUALITY REPORT ---
		item = new JMenuItem("Quality Report");
		item.setMnemonic('q');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qualityReportAction(e, true);
			}
		};
		item.addActionListener(lst);
		qualityMenu.add(item);		
		//--- Add a separator ---
		qualityMenu.addSeparator();
		//--- Add OCL EVALUTATION ---
		item = new JMenuItem("Evaluate OCL");
		item.setMnemonic('e');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evaluateOCLAction(e);
			}
		};
		item.addActionListener(lst);
		qualityMenu.add(item);
		//--- Add QUALITY ---
		menuBar.add(qualityMenu);
		
        //--- Add TOOLS items ---
        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setMnemonic('t');
        //--- Add GENERATE ---
        item = new JMenuItem("Generate Code");
        item.setMnemonic('g');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateCodeAction(e);
            }
        };
        item.addActionListener(lst);
        toolsMenu.add(item);
        //--- Add a separator ---
        toolsMenu.addSeparator();
        //--- Add OPTIONS ---
        item = new JMenuItem("Options ...");
        item.setMnemonic('o');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setOptionsAction(e);
            }
        };
        item.addActionListener(lst);
        toolsMenu.add(item);
        //--- Add Tools ---
        menuBar.add(toolsMenu);

        //--- Add WINDOW items ---
        JMenu windowMenu = new JMenu("Window");
        windowMenu.setMnemonic('w');
        //--- Add Window ---
        menuBar.add(windowMenu);

        //--- Add HELP items ---
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('h');
        //--- Add README ---
        item = new JMenuItem("Readme");
        item.setMnemonic('r');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readmeHelpAction(e);
            }
        };
        item.addActionListener(lst);
        helpMenu.add(item);
        //--- Add a separator ---
        helpMenu.addSeparator();
        //--- Add ABOUT ---
        item = new JMenuItem("About");
        item.setMnemonic('a');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aboutHelpAction(e);
            }
        };
        item.addActionListener(lst);
        helpMenu.add(item);
        //--- Add Help ---
        menuBar.add(helpMenu);

        return menuBar;
    }

    //--- Create the EXPLORER ---
    protected JInternalFrame createExplorer() {
        //--- Create PROJECTS frames ---
        JInternalFrame projectsFrame = new JInternalFrame("Project Explorer", true);
        projectsFrame.setVisible(true);

        //--- Create PROJECTS model ---
        DefaultMutableTreeNode projectsRoot = new DefaultMutableTreeNode("Projects", true);
        projectsModel = new DefaultTreeModel(projectsRoot);

        //--- Create the PROJECTS JTrees ---
        projectsTree = new JTree(projectsModel);
        projectsTree.setShowsRootHandles(true);
        projectsTree.setEditable(false);
        projectsTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                selectProjectAction(e);
            }
        });

        //--- Add the PROJECTS JTree to frame ---
        JScrollPane projectsScroll = new JScrollPane();
        projectsScroll.getViewport().add(projectsTree);
        projectsFrame.getContentPane().add(projectsScroll);
        JScrollPane elementsScroll = new JScrollPane();

        return projectsFrame;
    }

    //--- Create the DESKTOP ---
    protected JTabbedPane createDesktop() {
        JTabbedPane desktopPane = new JTabbedPane();
        return desktopPane;
    }

    //--- Create the CONSOLE ---
    protected JInternalFrame createConsole() {
        JInternalFrame frame = new JInternalFrame("Console", true);
        //--- Add Console ---
        console.setEditable(false);
        JScrollPane scroller = new JScrollPane(console);
        frame.getContentPane().add(scroller);
        frame.setVisible(true);
        frame.pack();
        return frame;
    }

    /**
      *   Reset - functions
      */
    //--- Reset STUDIO ---
    protected void resetSTUDIO() {
        //--- Reset EXPLORER ---
        resetExplorer();
        //--- Reset DESKTOP ---
        resetDesktop();
        //--- Reset output area ---
        refreshConsole();
    }
    //--- Reset EXPLORER ---
    protected void resetExplorer() {
        //--- Reset projects ---
        projects = new Vector();
        crtProject = null;
        //--- Refresh EXPLORER ---
        refreshExplorer();
    }
    //--- Reset the DESKTOP ---
    protected void resetDesktop() {
    }

    /**
      *   Refresh - functions
      */
    //--- Refresh EXPLORER ---
    protected void refreshExplorer() {
        DefaultMutableTreeNode projectsRoot = new DefaultMutableTreeNode("Projects", true);
        projectsModel.setRoot(projectsRoot);
    	for(int i=0; i<projects.size(); i++) {
    		Project project = (Project)projects.get(i);
        	DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(project, true);
        	// Load model
			uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository rep = project.getRepository(console);
			if (rep != null) {
				rep.setLog(console); 
				DefaultMutableTreeNode modelNode = rep.toJTree();
				if (modelNode != null)
	      			projectNode.add(modelNode);
			}
        	projectsRoot.add(projectNode);
    	}
    }

    //--- Refresh DESKTOP ---
    protected void refreshViewDesktop() {
    }
    protected void refreshEditDesktop() {
    }
    protected void refreshEvalDesktop() {
    }
    protected void refreshBuildDesktop() {
    }
    protected void refreshDesktop() {
        //--- Refresh all desktops ---
        refreshViewDesktop();
        refreshEditDesktop();
        refreshEvalDesktop();
        refreshBuildDesktop();
    }

    //--- Refresh CONSOLE ---
    protected void refreshConsole() {
    }

    /**
      *  MENU actions
      */
    //--- FILE actions ---
    /** Create a new project */
    protected void newFileAction(ActionEvent e) {
    	//--- Create a new project ---
    	crtProject = new uk.ac.kent.cs.kmf.kmfstudio.ProjectImpl();
    	crtProject.setProjectName("Noname");
        //--- Add project ---
    	projects.add(crtProject);
		//--- Set options ---
		setOptionsAction(e);		
        //--- Add log message ---
        console.reportMessage("Create new project '"+crtProject.getProjectPath()+"/"+crtProject.getProjectName()+"'");
        //--- Refresh EXPLORER ---
        refreshExplorer();
    }
    /** Open an existing project */
    protected void openFileAction(ActionEvent e) {
    	//--- Create a new project ---
    	crtProject = new uk.ac.kent.cs.kmf.kmfstudio.ProjectImpl();
		crtProject.setProjectName("Noname");
        //--- Choose project location ---
        if (openChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
        //--- Set project path and name ---
        File file = openChooser.getSelectedFile();
        crtProject.setProjectPath(file.getParent());
        crtProject.setProjectName(file.getName());
        String projectFileName = crtProject.getProjectFileName();
        //--- Load the project ---
   	    console.reportMessage("Loading project '"+projectFileName+"' ...");
       	crtProject.loadSettings();
   		//--- Set once again path and name in case the projects was moved using cut/paste ---
   		if (!crtProject.getProjectFileName().equals(projectFileName)) {
   			//--- Change the name ---
        	crtProject.setProjectPath(file.getParent());
        	crtProject.setProjectName(file.getName());
        	//--- Save it ---
        	crtProject.saveSettings();
   		}
        //--- Add project ---
   		projects.add(crtProject);
       	//--- Add project to history---
		history.add(crtProject.getProjectFileName());
    	//--- Refresh explorer ---
    	refreshExplorer();
    }
    /** Close current project */
    protected void closeFileAction(ActionEvent e) {
        if (crtProject == null) return;
        //--- Add log message ---
	    console.reportMessage("Closing project '"+crtProject.getProjectFileName()+"' ...");
        //--- Ask user if he wants to save ---
        saveOnExitAction(e);
		//--- Remove current project ---
        projects.remove(crtProject);
		//--- Set new current project ---
        crtProject = null;
        if (projects.size() != 0) crtProject = (Project)projects.get(0);
		//--- Refresh explorer ---
        refreshExplorer();
    }
    /** Close all projects */
    protected void closeAllFileAction(ActionEvent e) {
        //--- Add log message ---
	    console.reportMessage("Closing all projects ...");
    	for(int i=0; i<projects.size(); i++) {
    		crtProject = (Project)projects.get(i);
        	//--- Ask user if he wants to save ---
        	saveOnExitAction(e);
    	}
		//--- Reset explorer ---
        resetExplorer();
    }
    /** Save current project if asked */
    protected void saveOnExitAction(ActionEvent e) {
    	if (crtProject == null) return;
		Object[] options = {"Yes", "No"};
		int n = JOptionPane.showOptionDialog(
			this, 
			"Do want to save project '"+crtProject.getProjectFileName()+"' ?",
			"Close Project",
   			JOptionPane.YES_NO_CANCEL_OPTION,
    		JOptionPane.QUESTION_MESSAGE,
    		null,
    		options,
    		options[0]
    	);
    	if (n == JOptionPane.YES_OPTION) {
    		saveFileAction(e);
    	}
    }
    /** Save current project */
    protected void saveFileAction(ActionEvent e) {
        if (crtProject == null) return;
        //--- Add log message ---
        console.reportMessage("Saving current project '"+crtProject.getProjectFileName()+"' ...");
		//--- Save settings ---
		if (crtProject.getProjectName().equals("Noname.ksp")) saveAsFileAction(e);
        else crtProject.saveSettings();
    }
    /** Save current project as a new project */
    protected void saveAsFileAction(ActionEvent e) {
        if (crtProject == null) return;
    	//--- Choose project location ---
        if (saveChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        //--- Set project path and name ---
        File file = saveChooser.getSelectedFile();
        crtProject.setProjectPath(file.getParent());
        crtProject.setProjectName(file.getName());
        //--- Add log message ---
        console.reportMessage("Saving current project as '"+crtProject.getProjectFileName()+"' ...");
        //--- Save the project ---
        crtProject.saveSettings();
        //--- Add project to history---
   		history.add(crtProject.getProjectFileName());
    }
    /** Open a history file */
    protected void openHistoryFileAction(ActionEvent e) {
    	//--- Create a new project ---
    	crtProject = new ProjectImpl();
		crtProject.setProjectName("Noname");
        //--- Load the project ---
        String projectFileName = e.getActionCommand();
        File file = new File(projectFileName);
        if (file.exists()) {
	        //--- Add log message ---
    	    console.reportMessage("Loading project '"+projectFileName+"' ...");
        	crtProject.loadSettings(projectFileName, console);
	   		//--- Set once again path and name in case the projects was moved using cut/paste ---
	   		if (!crtProject.getProjectFileName().equals(projectFileName)) {
	   			//--- Change the name ---
	        	crtProject.setProjectPath(file.getParent());
	        	crtProject.setProjectName(file.getName());
	        	//--- Save it ---
	        	crtProject.saveSettings();
	   		}
	        //--- Add project ---
    		projects.add(crtProject);
    		//--- Refresh explorer ---
    		refreshExplorer();
        } else {
    	    console.reportMessage("Missing file '"+projectFileName);
        }
    }
    /** Exit */
    protected void exitFileAction(ActionEvent e) {
        //--- Ask user if he wants to save ---
        saveOnExitAction(e);
        //--- Save history ---
        history.save();
        System.exit(0);
    }

	//--- QUALITY actions ---
	/** Evaluate metrics/display violations */
	protected void computeMetricsAction(ActionEvent e, boolean onlyViolation) {
		if (crtProject == null) return;
		try {
			// Get selected elements
			List selectedElements = getSelectedModelElements();
			if (selectedElements.size() == 0) return;
			QualityModel qModel = crtProject.getQualityModel((ILog)console);
			qModel.evaluate(selectedElements, crtProject.getMetrics());
			Viewer viewer = new Viewer(qModel, selectedElements, Viewer.KIVIAT);
			viewer.view(onlyViolation);
		} catch (Exception ex) {
			ex.printStackTrace();
			console.reportMessage(""+ex);
		}
	}
	protected void qualityReportAction(ActionEvent e, boolean onlyViolation) {
		if (crtProject == null) return;
		try {
			// Get selected elements
			List selectedElements = getSelectedModelElements();
			if (selectedElements.size() == 0) return;
			QualityModel qModel = crtProject.getQualityModel((ILog)console);
			qModel.evaluate(selectedElements, crtProject.getMetrics());
			Viewer viewer = new Viewer(qModel, selectedElements, Viewer.PIE);
			viewer.view(onlyViolation);
		} catch (Exception ex) {
			ex.printStackTrace();
			console.reportMessage(""+ex);
		}
	}
	protected List getSelectedModelElements() {
		List res = new Vector();
		TreePath paths[] = projectsTree.getSelectionPaths();
		if (paths == null) return res;
		for(int i=0; i<paths.length; i++) {
			TreePath path = paths[i];
			if (path != null) {
				Object node = path.getLastPathComponent();
				if (node instanceof DefaultMutableTreeNode) {
					Object obj = ((DefaultMutableTreeNode)node).getUserObject();
					if (obj instanceof ModelElement) {
						res.add(obj);
					}
				}
			}			
		}
		return res;
	}
	protected void evaluateOCLAction(ActionEvent e) {
		if (crtProject == null) return;
		try {
			// Get selected element
			Object selectedElement = getSelectedModelElement();
			if (selectedElement == null) return;
			// Create an OCL evaluator
			Map context = new HashMap();
			context.put("self", selectedElement);
			UmlOclEvaluator evalFrame = new UmlOclEvaluator(context, metaOclProcessor);
			// Add it to desktop
			desktopPane.addTab(selectedElement.toString(), evalFrame);
		} catch (Exception ex) {
			ex.printStackTrace();
			console.reportMessage(""+ex);
		}
	}
	protected Object getSelectedModelElement() {
		Object res = null;
		TreePath path = projectsTree.getSelectionPath();
		if (path != null) {
			Object node = path.getLastPathComponent();
			if (node instanceof DefaultMutableTreeNode) {
				Object obj = ((DefaultMutableTreeNode)node).getUserObject();
				if (obj instanceof ModelElement) {
					res = obj;
				}
			}
		}
		return res;
	}
	
	//--- TOOLS actions ---
    /** Generate code */
    protected void generateCodeAction(ActionEvent e) {
        if (crtProject == null) return;
        try {
        	//--- Reset the console ---
        	console.reset();
        	//--- Generate the code ---
        	CodeGenerator gen = new CodeGeneratorImpl();
			gen.generate(crtProject, new NamingImpl(crtProject, console), console);						
        } catch (Exception ex) {
        	console.reportMessage(""+ex);
        }
    }
    /** Set options for the current project */
    protected void setOptionsAction(ActionEvent e) {
        if (crtProject == null) return;
        new OptionsFrame(crtProject);
    }

    //--- WINDOW actions ---

    //--- HELP actions ---
    /** Show the readme file */
    protected void readmeHelpAction(ActionEvent e) {
    	// Create the frame and the tab panel
    	JFrame aboutFrame = new JFrame("Readme");
    	JTabbedPane aboutTab = new JTabbedPane();
    	// Add tabs to panel
        JTextArea textArea;
        int noLines = 40;
        int noColumns = 45;
        String text = new String();
        text += "               Kent Modelling Framework\n";
        text += "                  Installation Notes\n";
        text += "\n";
        text += "About KMF\n";
        text += "---------\n";
        text += "KMF is being developed to provide a set of tools to support model\n";
        text += "driven software development. At the core of KMF is KMF-Studio, a tool\n";
        text += "to generate modelling tools from the definition of modelling\n";
        text += "languages expressed as metamodels. KMF-Studio is supported by\n";
        text += "OCLCommon and OCL4KMF, two Java libraries which allow dynamic evaluation of OCL\n";
        text += "constraints. Tools generated using KM-Studio use OCLCommon and OCL4KMF to provide\n";
        text += "built in support for checking well-formedness of models, amongst\n";
        text += "other things. As KMF develops, we will be making available\n";
        text += "specific modelling tools built with the help of KMF-Studio.\n";
        text += "\n";
        text += "Platform\n";
        text += "--------\n";
        text += "KMF-Studio has been developed using Java and requires Java 1.4 or\n";
        text += "higher.\n";
        text += "\n";
        text += "Installation procedure\n";
        text += "----------------------\n";
        text += "The installation procedure assumes that you have downloaded the\n";
        text += "KMF-Studio package from http://www.cs.ukc.ac.uk/kmf. The package, a zip\n";
        text += "file, contains all the binary code, some examples, a user guide,\n";
        text += "and a .bat file (for Windows platforms). KMF-Studio was tested and\n";
        text += "developed on Windows XP using the Eclipse Framework from IBM.\n";
        text += "\n";
        text += "Unzip the package into the desired location <root>. After that you\n";
        text += "can start using KMF-Studio. This can be done on Windows system by\n";
        text += "launching \"startKMFStudio.bat\" from the \"bin\" directory. For other\n";
        text += "systems, invoke java as usual, making sure that the following\n";
        text += "libraries (from the lib directory) are included in the classpath:\n";
        text += "    xercesImpl.jar\n";
        text += "    xmlParserAPIs.jar\n";
        text += "    CUPRuntime.jar\n";
        text += "    UTIL-1.2.jar\n";
        text += "    XMI-1.2.jar\n";
        text += "    UMLModel-2.0.jar\n";
        text += "    OCLCommon.jar\n";
        text += "    OCL4KMF.jar\n";
        text += "    KMFStudio-2.0.jar\n";
        text += "The main class is:\n";
        text += "    uk.ac.ukc.cs.kmf.kmfstudio.KMFStudio\n";
        text += "For installations that have multiple Java runtime environments\n";
        text += "installed, ensure that the Java 1.4 runtime is used.\n";
        text += "\n";
        text += "Files\n";
        text += "-----\n";
        text += "<root>\n";
        text += "    \"KMF-StudioLicense.txt\"\n";
        text += "        contains the licence for the KMF-Studio\n";
        text += "    \"KMFLibraryLicense.txt\"\n";
        text += "        contains the licence for the libraries contained in the KMF distrubtion\n";
        text += "<root>/bin\n";
        text += "    startKMFStudio.bat\n";
        text += "        the bat file used to launch KMFStudio on Windows machines\n";
        text += "<root>/examples\n";
        text += "<root>/lib\n";
        text += "    \"xercesImpl.jar\"\n";
        text += "    \"xmlParsersAPIs.jar\"\n";
        text += "        an XML parser\n";
        text += "    \"CUPRuntime\"\n";
        text += "        a parser generator \n";
        text += "    \"XMI-1.2.jar\"\n";
        text += "        a framework to read/write XMI files\n";
        text += "    \"UTIL-1.2.jar\"\n";
        text += "        a set of utilities\n";
        text += "    \"UMLModel-2.0\"\n";
        text += "        a jar file that allows the internal representation of UML\n";
        text += "        notions\n";
        text += "    \"OCLCommon.jar\" and \"OCL4KMF.jar\"\n";
        text += "        used to build and evaluate OCL expressions\n";
        text += "    \"KMFStudio\"\n";
        text += "        the main package\n";
        text += "\n";
        text += "Uninstalling procedure\n";
        text += "----------------------\n";
        text += "Deleting the <root> directory and its content will uninstall KMFStudio.\n";
        text += "You don't have to reboot the system.\n";
        text += "\n";
        text += "Acknowledgements\n";
        text += "----------------\n";
        text += "KMF makes use of, and is distributed with, the following open\n";
        text += "source software:\n";
        text += "\n";
        text += "The Xerces library, available from http://xml.apache.org/\n";
        text += "\n";
        text += "The CUPRuntime library, available from http://www.cs.princeton.edu/~appel/modern/java/CUP/\n";
        text += "\n";
        text += "Octavian Patrascoiu, University of Kent\n";
        text += "17th April 2002\n";
        text += "\n";
        textArea = new JTextArea(text, noLines, noColumns);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
	    aboutTab.addTab("Readme", new JScrollPane(textArea));
    	// Add panel to frame
    	aboutFrame.getContentPane().add(aboutTab);
    	aboutFrame.pack();
        // Put frame in the middle of the scree
        Dimension screen = getToolkit().getScreenSize();
        int width  = aboutFrame.getContentPane().getSize().width;
        int height = aboutFrame.getContentPane().getSize().height;
        int left = (screen.width - width)/2;
        int top = (screen.height - height)/2;
        aboutFrame.setBounds(left, top, width, height);
	    aboutFrame.show();
    }
    /** Show the licenses */
    protected void aboutHelpAction(ActionEvent e) {
    	// Create the frame and the tab panel
    	JFrame aboutFrame = new JFrame("About KMFStudio");
    	JTabbedPane aboutTab = new JTabbedPane();
    	// Add tabs to panel
        JTextArea textArea;
        int noLines = 40;
        int noColumns = 45;
        String text;
    	// Add VERSION tab panel 
        text = new String();
		text += "Kent Modelling Framework\n";
		text += "\n";
		text += "KMF-Studio\n";
  		text += "Version: 3.0\n";
  		text += "\n";
        textArea = new JTextArea(text, noLines, noColumns);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
	    aboutTab.addTab("Version", new JScrollPane(textArea));
    	// Add CREDITS tab panel 
        text = new String();
		text += "Developing team\n";
		text += " Octavian Patrascoiu\n";
        textArea = new JTextArea(text, noLines, noColumns);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
	    aboutTab.addTab("Credits", new JScrollPane(textArea));
    	// Add panel to frame
    	aboutFrame.getContentPane().add(aboutTab);
    	aboutFrame.pack();
        // Put frame in the middle of the scree
        Dimension screen = getToolkit().getScreenSize();
        int width  = aboutFrame.getContentPane().getSize().width;
        int height = aboutFrame.getContentPane().getSize().height;
        int left = (screen.width - width)/2;
        int top = (screen.height - height)/2;
        aboutFrame.setBounds(left, top, width, height);
	    aboutFrame.show();
    }

    //--- SELECT action ---
    /** Select the current project */
    protected void selectProjectAction(TreeSelectionEvent e) {
        TreePath path = projectsTree.getSelectionPath();
        if (path == null) return;
        Object node = path.getLastPathComponent();
        if (node instanceof DefaultMutableTreeNode) {
	        Object obj = ((DefaultMutableTreeNode)node).getUserObject();
	        if (obj instanceof Project) {
	        	crtProject = (Project)obj;
	        }
        }
    }
    
    //--- Load all metrics --
	protected static MetricSet getAllMetricSet() {
		MetricSet metricSet = new MetricSet();
		DOMParser parser = new DOMParser();
		try {
            String uri = getResource("allMetrics.xml").toURI().toString();
			parser.parse(uri);
			Document doc = parser.getDocument();
			scan4Metrics(doc, metricSet); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		return metricSet;
	}
	protected static void scan4Metrics(Node node, MetricSet metricSet) {
		switch (node.getNodeType()) {
			case Node.DOCUMENT_NODE: 
			case Node.ELEMENT_NODE:
				String name = node.getNodeName();
				if (name.equals("metrics")) {
					NamedNodeMap attributes = node.getAttributes();
					NodeList children = node.getChildNodes();
					for(int i=0; i<children.getLength(); i++) {
						Node child = children.item(i);
						if (child.getNodeName().equals("metric")) {
							Metric m = getMetric(child);
							metricSet.addMetric(m);
						}						
					}
				} else {
					NodeList children = node.getChildNodes(); 
					for(int i=0; i<children.getLength(); i++)
						scan4Metrics(children.item(i), metricSet);
				}
		}
	}
	protected static Metric getMetric(Node node) {
		Metric m = new Metric();
		NamedNodeMap attributes = node.getAttributes();
		// Get key, name, type, min, max
		m.setNamespace(attributes.getNamedItem("namespace").getNodeValue());
		m.setKey(attributes.getNamedItem("key").getNodeValue());
		m.setName(attributes.getNamedItem("name").getNodeValue());
		m.setType(attributes.getNamedItem("type").getNodeValue());
		m.setMin(Metric.getDoubleValue(attributes.getNamedItem("min").getNodeValue()));
		m.setMax(Metric.getDoubleValue(attributes.getNamedItem("max").getNodeValue()));
		// Get body and diagnostic
		NodeList children = node.getChildNodes(); 
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeName().equals("body")) {
				Node firstChild = child.getFirstChild();
				if (firstChild != null)
					m.setBody(firstChild.getNodeValue());
			} else if (child.getNodeName().equals("diagnostic")) {
				Node firstChild = child.getFirstChild();
				if (firstChild != null)
					m.setDiagnostic(firstChild.getNodeValue());
			}
		}
		return m;
	}
	
	//--- Load metamodel ---
	protected static Model loadMetaModel() {
		// Load model
		try {			
			String fileName = "UMLModel.xmi";
            File file = new File(getResource(fileName).toURI().toURL().getFile());
            InputStream uri = new FileInputStream(file);
			Pair pair = ((new XMIToUMLLoader()).loadModel(uri, staticLog));
			return (Model)pair.getFirst();
		} catch (Exception e) {
			staticLog.reportError("Cannot load metamodel 'UMLModel.xmi'");
			return null;
		}
	}

    private static URL getResource(String resourceName) {
        return StudioGUI.class.getClassLoader().getResource(resourceName);
    }

    /**
      *   Main function
      */
    public static void main(String[] args) {
        try {
//            UIManager.setLookAndFeel(new com.incors.plaf.alloy.AlloyLookAndFeel());
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//            UIManager.setLookAndFeel(new com.incors.plaf.alloy.AlloyLookAndFeel());
        } catch (Exception e) {
        }
        (new StudioGUI()).setVisible(true);
    }
}
