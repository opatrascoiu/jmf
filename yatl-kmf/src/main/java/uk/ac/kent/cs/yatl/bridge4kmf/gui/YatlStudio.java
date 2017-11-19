package uk.ac.kent.cs.yatl.bridge4kmf.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JDesktopPane;
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
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import uk.ac.kent.cs.kmf.common.Repository;
import uk.ac.kent.cs.kmf.common.History;
import uk.ac.kent.cs.kmf.util.ConsoleLog;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.yatl.bridge4kmf.gui.listeners.*;
import uk.ac.kent.cs.yatl.syntax.transformations.Unit;

/**
 * @author Octavian Patrascoiu
 *
 */
public class YatlStudio extends JFrame {
	public YatlStudio() {
		this(null);
	}
	public YatlStudio(Project project) {
		// Set title and size to full screen
		super("YATL Studio");
		screenSize = getToolkit().getScreenSize();
		screenSize.setSize(screenSize.width, screenSize.height);
		setSize(screenSize);
		getContentPane().setLayout(new BorderLayout());
		// Load history
		history.load();
		// Create OUTPUT
		JInternalFrame consoleFrame = createConsole();
		// Set properties
		this.crtProject = project;
		if (crtProject != null) {
			crtProject.setLog(log);
			crtProject.loadProject();
			this.sourceModelName = crtProject.getSourceModelName();
			this.targetModelName = crtProject.getTargetModelName();
		}
		// Create MENU, SOURCE, TARGET, and DESKTOP
		JMenuBar menuBar = createMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		createExplorers();
		createDesktop();
		
		// Create SPLIT PANELS
		if (true) {
			// Create SPLIT PANELS
			mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			mainPanel.setDividerSize(dividerSize);
			mainPanel.setDividerLocation((int)(screenSize.width*mainDiv));
			mainPanel.setContinuousLayout(true);
		
			subPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			subPanel.setDividerSize(dividerSize);
			subPanel.setDividerLocation((int)(screenSize.width*subDiv));
			subPanel.setContinuousLayout(true);
		
			leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			leftPanel.setDividerSize(dividerSize);
			leftPanel.setContinuousLayout(true);

			middlePanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			middlePanel.setDividerSize(dividerSize);
			middlePanel.setDividerLocation((int)(screenSize.height*middleDiv));
			middlePanel.setContinuousLayout(true);

			rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			rightPanel.setDividerSize(dividerSize);
			rightPanel.setDividerLocation((int)(screenSize.height*middleDiv));
			rightPanel.setContinuousLayout(true);
		
			// Add components to SPLIT PANELS
			leftPanel.setTopComponent(sourcePane);
			rightPanel.setTopComponent(targetPane);
			middlePanel.setTopComponent(editPane);
			middlePanel.setBottomComponent(consoleFrame);
			subPanel.setLeftComponent(middlePanel);
			subPanel.setRightComponent(rightPanel);
			mainPanel.setLeftComponent(leftPanel);
			mainPanel.setRightComponent(subPanel);

			getContentPane().add(mainPanel, BorderLayout.CENTER);
		} else {
			mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			mainPanel.setDividerSize(dividerSize);
			mainPanel.setDividerLocation((int)(screenSize.width*mainDiv));
			mainPanel.setContinuousLayout(true);
			leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			leftPanel.setDividerSize(dividerSize);
			leftPanel.setContinuousLayout(true);
			middlePanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			middlePanel.setDividerSize(dividerSize);
			middlePanel.setDividerLocation((int)(screenSize.height*middleDiv));
			middlePanel.setContinuousLayout(true);
		
			// Add components to SPLIT PANELS
			leftPanel.setTopComponent(sourcePane);
			rightPanel.setTopComponent(targetPane);
			middlePanel.setTopComponent(editPane);
			middlePanel.setBottomComponent(consoleFrame);
			mainPanel.setLeftComponent(leftPanel);
			mainPanel.setRightComponent(middlePanel);
			getContentPane().add(mainPanel, BorderLayout.CENTER);
		}
		// Initialize FILE CHOOSER
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new BasicFileFilter(new String[] {"ysp"}));
		try {
			fileChooser.setCurrentDirectory(new File("C:/D/Work/Java Projects/YATL4KMF/src/test/scripts"));
		} catch (Exception e) {
		}
		
		// Add listener for window closing event
		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(wndCloser);
		show();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/** Create the MENU */
	protected JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();

		//--- Add FILE items ---
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('f');
		//--- Add NEW ---
		JMenuItem item = makeItem("New", 'n', new FileNewListener(this));
		fileMenu.add(item);
		//--- Add OPEN item ---
		item = makeItem("Open", 'o', new FileOpenListener(this));
		fileMenu.add(item);
		//--- Add CLOSE item ---
		item = makeItem("Close", 'c', new FileCloseListener(this));
		fileMenu.add(item);
		//--- Add CLOSE item ---
		item = makeItem("Close all", 'c', new FileCloseAllListener(this));
		fileMenu.add(item);
		//--- Add a separator ---
		fileMenu.addSeparator();
		// Add SAVE item
		item = makeItem("Save", 's', new FileSaveListener(this));
		fileMenu.add(item);
		// Add SAVE AS item
		item = makeItem("Save As ...", 'a', new FileSaveAsListener(this));
		fileMenu.add(item);
		//--- Add history
		if (!history.isEmpty()) {
			//--- Add a separator ---
			fileMenu.addSeparator();
			//--- Add each file ---
			Iterator i = history.iterator();
			while (i.hasNext()) {
				item = new JMenuItem((String)history.next());
				item.addActionListener(new FileHistoryListener(this));
				fileMenu.add(item);
			}
		}
		//--- Add a separator 
		fileMenu.addSeparator();
		// Create EXIT item 
		item = makeItem("Exit", 'x', new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		fileMenu.add(item);
		// Add FILE 
		menuBar.add(fileMenu);

		// Add VIEW items 
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic('v');
		// Add REFRESH EXPLORER 
		item = makeItem("Refresh", 'x', new ViewRefreshListener(this));
		viewMenu.add(item);
		// Add View 
		menuBar.add(viewMenu);

		// Add TOOLS items 
		JMenu toolsMenu = new JMenu("Tools");
		// Add PARSE 
		item = makeItem("Parse", 'p', new ToolsParseListener(this));
		toolsMenu.add(item);
		// Add ANALYSE 
		item = makeItem("Analyse", 'a', new ToolsAnalyseListener(this));
		toolsMenu.add(item);
		// Add COMPILE 
		item = makeItem("Compile", 'c', new ToolsCompileListener(this));
		toolsMenu.add(item);
		// Add a separator 
		toolsMenu.addSeparator();
		// Add TRANSFORM 
		item = makeItem("Transform", 't', new ToolsTransformListener(this));
		toolsMenu.add(item);
		// Add a separator 
		toolsMenu.addSeparator();
		item = makeItem("Options", 'o', new ToolsOptionsListener(this));
		toolsMenu.add(item);
		// Add Tools 
		menuBar.add(toolsMenu);

		// Add WINDOW items 
		JMenu windowMenu = new JMenu("Window");
		windowMenu.setMnemonic('w');
		// Add CLOSE ALL 
		item = makeItem("Close All", 'l', new WindowCloseAllListener(this));
		windowMenu.add(item);
		// Add Window 
		menuBar.add(windowMenu);

		// Add HELP items 
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('h');
		// Add README 
		item = makeItem("Readme", 'r', new HelpReadmeListener(this));
		helpMenu.add(item);
		// Add a separator 
		helpMenu.addSeparator();
		// Add ABOUT 
		item = makeItem("About sd", 'a', new HelpAboutListener(this));
		helpMenu.add(item);
		// Add Help 
		menuBar.add(helpMenu);

		return menuBar;
	}
	
	/** Create a menu item */
	protected JMenuItem makeItem(String name, char mnemo, ActionListener action) {
		JMenuItem item = new JMenuItem(name);
		item.setMnemonic(mnemo);
		item.addActionListener(action);
		return item; 
	}
	
	/** Create EXPLORERS */
	protected void createExplorers() {
		Object sourceExplorerPane = null;
		// Create SOURCE frame 
		JInternalFrame sourceFrame = new JInternalFrame("Source Explorer", true);
		// Create the SOURCE model 
		sourceRoot = crtProject == null ? null : new DefaultMutableTreeNode(crtProject.getSourceModelName(), true);
		sourceModel = new DefaultTreeModel(sourceRoot);
		// Create the SOURCE JTree 
		sourceTree = new JTree(sourceModel);
		sourceTree.setShowsRootHandles(true);
		sourceTree.setEditable(false);
		sourceTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				selectElementsAction(e);
			}
		});
		// Add population
		addSourcePopulation();
		// Add the SOURCE JTree to frame 
		JScrollPane sourceScroll = new JScrollPane();
		sourceScroll.getViewport().add(sourceTree);
		sourceFrame.getContentPane().add(sourceScroll);
		sourceFrame.setVisible(true);

		// Create TARGET frames 
		JInternalFrame targetFrame = new JInternalFrame("Target Explorer", true);
		// Create the TARGET model 
		targetRoot = crtProject == null ? null : new DefaultMutableTreeNode(crtProject.getTargetModelName(), true);
		targetModel = new DefaultTreeModel(targetRoot);
		// Create the TARGET JTree 
		targetTree = new JTree(targetModel);
		targetTree.setShowsRootHandles(true);
		targetTree.setEditable(false);
		targetTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				selectElementsAction(e);
			}
		});
		// Add population
		addTargetPopulation();
		// Add the TARGET JTree to frame 
		JScrollPane targetScroll = new JScrollPane();
		targetScroll.getViewport().add(targetTree);
		targetFrame.getContentPane().add(targetScroll);
		targetFrame.setVisible(true);		

		// Create the TABBED PANE 
		sourcePane = new JTabbedPane();
		sourcePane.addTab("Source", sourceFrame);
		targetPane = new JTabbedPane();
		targetPane.addTab("Target", targetFrame);
		
		// Expand trees
		expandTree(sourceTree);
		expandTree(targetTree);
	}
	protected void addSourcePopulation() {
		if (crtProject == null) return;
		Repository sourceRep = crtProject.getSourceRepository();
		if (sourceRep == null) return;
		DefaultMutableTreeNode temp = sourceRep.toJTree();
		temp.setUserObject(crtProject.getSourceModelName());
		sourceModel.setRoot(temp);
	}
	protected void addTargetPopulation() {
		if (crtProject == null) return;
		Repository targetRep = crtProject.getTargetRepository();
		if (targetRep == null) return;
		DefaultMutableTreeNode temp = targetRep.toJTree();
		temp.setUserObject(crtProject.getTargetModelName());
		targetModel.setRoot(temp);
	}
	
	/** Create the DESKTOP */
	protected void createDesktop() {
		// Create TABBEDPANE 
		editPane = new JTabbedPane();
		if (isTabPane) {
		} else {
			// Create EDIT desktop 
			editDesktop = new JDesktopPane();
			editPane.addTab("Edit", editDesktop);
		}
		if (crtProject == null) return;
		addEditor(new YatlEditor(new File(crtProject.getTransfFileName()), log));
	}

	/** Create the CONSOLE */
	protected JInternalFrame createConsole() {
		JInternalFrame frame = new JInternalFrame("Console", true);
		// Create console panel 
		log = new ConsoleLog();
		((ConsoleLog)log).setEditable(false);
		JScrollPane scroller = new JScrollPane((ConsoleLog)log);
		frame.getContentPane().add(scroller);
		frame.setVisible(true);
		return frame;
	}

	//
	// Reset methods
	//
	/** Reset system */
	public void reset() {
		crtProject = null;
		resetExplorers();
		resetEditDesktop();
		resetConsole();
	}
	/** Reset TREES */
	public void resetExplorers() {
//		sourceRoot.removeAllChildren();
//		targetRoot.removeAllChildren();
		sourceModel.setRoot(null);
		targetModel.setRoot(null);
	}
	/** Reset the EDIT DESKTOP */
	protected void resetEditDesktop() {
		// Close all EDIT frames 
		if (isTabPane) {
			editPane.removeAll();
		} else {
			JInternalFrame frames[] = editDesktop.getAllFrames();
			for(int i=0; i<frames.length; i++) frames[i].setVisible(false);
			// Reset counter 
			editCount = 0;
		}
	}
	/** Reset the CONSOLE */
	protected void resetConsole() {
		log.reset();
	}

	//
	// Refresh methods 
	//
	/** Refresh explorers and edit desktop */
	public void refresh() {
		refreshExplorers();
		refreshEditDesktop();
		refreshConsole();
	}
	/** Refresh EXPLORERS */
	public void refreshExplorers() {
		resetExplorers();
		// Add population
		addSourcePopulation();
		addTargetPopulation();
		// Expand trees
		expandTree(sourceTree);
		expandTree(targetTree);
	}
	protected void expandTree(JTree tree) {
		tree.expandRow(0);
		tree.expandRow(2);
	}
	/** Refresh EDIT DESKTOP */
	protected void refreshEditDesktop() {
		resetEditDesktop();
		// Add editor
		if (crtProject == null) return;
		addEditor(new YatlEditor(new File(crtProject.getTransfFileName()), log));
	}
	/** Refresh CONSOLE */
	protected void refreshConsole() {
	}

	/** Select element */
	protected void selectElementsAction(TreeSelectionEvent e) {
		TreePath paths[] = sourceTree.getSelectionPaths();
		selectedElementNodes.clear();
		if (paths == null) return;        
		for(int i=0; i<paths.length; i++) {
			selectedElementNodes.add(paths[i].getLastPathComponent());
		}
	}

	//
	// Operations on projects
	//
	/** Create a new project */
	public void newProject() {
		// Create a new project
		Project newProject = new Project(log);
		// Add project
//		projects.add(crtProject);
		// Set options
		setOptions(newProject);
		// Add log message
		log.reportMessage("Create new project '"+crtProject.getProjectPathName()+"'");
		// Refresh
		refresh();
	}
	/** Open an existing project */
	public void openProject() {
		// Choose project location
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
		File xmlFile = fileChooser.getSelectedFile();
		// Add log message
		log.reportMessage("Loading project '"+xmlFile.getAbsolutePath()+"' ...");
		// Create a new project
		Project tempProject = new Project(xmlFile, log);
		// Load the project
		tempProject.loadProject();
		// Add project
		crtProject = tempProject;
//		projects.add(crtProject);
		// Add project to history
		history.add(crtProject.getProjectPathName());
		// Refresh
		refresh();
	}
	/** Close current project */
	public void closeProject() {
		if (crtProject == null) return;
		// Add log message
		log.reportMessage("Closing project '"+crtProject.getProjectPathName()+"' ...");
		// Ask user if he wants to save
		saveOnExit();
		// Remove current project
//		projects.remove(crtProject);
		// Set new current project
		crtProject = null;
//		if (projects.size() != 0) crtProject = (Project)projects.get(0);
		// Reset system
		reset();
	}
	/** Close all projects */
	public void closeAllProjects() {
		// Add log message
		log.reportMessage("Closing all projects ...");
//		for(int i=0; i<projects.size(); i++) {
//			crtProject = (Project)projects.get(i);
			// Ask user if he wants to save
			saveOnExit();
//		}
		// Reset
		reset();
	}
	/** Save current project */
	public void saveProject() {
		if (crtProject == null) return;
		// Add log message
		log.reportMessage("Saving current project as '"+crtProject.getProjectPathName()+"' ...");
		// Save the project
		crtProject.saveProject();
		saveRepository(crtProject.getSourceRepository(), crtProject.getSourceRepFileName());
		saveRepository(crtProject.getTargetRepository(), crtProject.getTargetRepFileName());
		saveTransformation(getCrtEditor(), crtProject.getTransfFileName());
		// Add project to history
		history.add(crtProject.getProjectPathName());
	}
	/** Save current project if asked */
	protected void saveOnExit() {
		if (crtProject == null) return;
		Object[] options = {"Yes", "No"};
		int n = JOptionPane.showOptionDialog(
			this, 
			"Do want to save project '"+crtProject.getProjectPathName()+"' ?",
			"Close Project",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]
		);
		if (n == JOptionPane.YES_OPTION) {
			saveProject();
			exit();
		}
	}
	/** Save current project as a new project */
	public void saveProjectAs() {
		if (crtProject == null) return;
		// Choose project location
		if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
		// Set project path and name
		File file = fileChooser.getSelectedFile();
		crtProject.setProjectPathName(file.getAbsolutePath());
		// Add log message
		log.reportMessage("Saving current project as '"+crtProject.getProjectPathName()+"' ...");
		// Save the project
		crtProject.saveProject();
		saveRepository(crtProject.getSourceRepository(), crtProject.getSourceRepFileName());
		saveRepository(crtProject.getTargetRepository(), crtProject.getTargetRepFileName());
		saveTransformation(getCrtEditor(), crtProject.getTransfFileName());
		// Add project to history
		history.add(crtProject.getProjectPathName());
	}
	/** Open a history file */
	public void openHistoryProject(ActionEvent e) {
		// Create a new project
		crtProject = new Project(log);
		// Load the project
		String projectPathName = e.getActionCommand();
		File file = new File(projectPathName);
		if (file.exists()) {
			// Add log message
			log.reportMessage("Loading project '"+projectPathName+"' ...");
			crtProject.setProjectPathName(file.getAbsolutePath());
			crtProject.loadProject();
			// Add project
//			projects.add(crtProject);
			// Refresh explorer
			refresh();
		} else {
			log.reportMessage("Missing file '"+projectPathName);
		}
	}
	/** Exit */
	public void exit() {
		// Ask user if he wants to save
		saveOnExit();
		// Save history
		history.save();
		System.exit(0);
	}

	//
	// Operations on repositories
	//
	protected void saveRepository(Repository rep, String fileName) {
		if (fileName == null || fileName.length() == 0) return;
		// Add message to output
		log.reportMessage("Save repository to '"+fileName+"'");
		// Save the repository 
		rep.saveXMI(fileName);
	}
		
	//
	// Operations on transformations
	//
	/** Save current transformation */	
	protected void saveTransformation(YatlEditor editor, String fileName) {
		// Save the transformation 
		if (fileName.endsWith("tl")) {
			// Add message to output 
			log.reportMessage("Save transformation to '"+fileName+"'");
			File file = new File(fileName);
			YatlEditor crtEditor = this.getCrtEditor();
			if (file.exists() && crtEditor != null) {
				try {
					PrintWriter out = new PrintWriter(new FileOutputStream(file));
					out.print(editor.getText());
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//
	// Operations on editors
	//
	/** Add a transformation editor */
	protected void addEditor(YatlEditor editor) {
		if (isTabPane) {
			editPane.addTab(editor.getFileName(), editor);
		} else {
			editor.setTitle(editor.getFileName());
			editDesktop.add(editor);
			editor.moveToFront();
			try {
				editor.setSelected(true);
			} catch (Exception e) {
			}
			editDesktop.setSelectedFrame(editor);
		}
	}
	/** Get current editor */
	protected YatlEditor getCrtEditor() {
		if (isTabPane) {
			if (editPane == null) return null;
			Component frame = editPane.getSelectedComponent(); 
			if (frame instanceof YatlEditor) 
				return (YatlEditor)frame;
			return null;
		} else {
			if (editDesktop == null) return null;
			JInternalFrame frame = editDesktop.getSelectedFrame(); 
			if (frame instanceof YatlEditor) 
				return (YatlEditor)frame;
			return null;
		}
	}
	
	//
	// Options
	//
	/** Set options for the current project */
	public void setOptions() {
		setOptions(crtProject);
	}
	public void setOptions(Project project) {
		if (project == null) return;
		new OptionsFrame(project);
		crtProject = project;
	}

	/** Close all view windows */
	protected void closeAllViewWindowsAction() {
		// Reset desktop 
		resetEditDesktop();
	}
	
	//
	// YATL operations
	//
	public void parse() {
		if (crtProject == null) return;
		try {
			// Get input
			String fileName = getCrtTransformation();
			if (fileName == null) return;
			Reader input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			// Parse input
			log.reset();
			Unit unit = crtProject.getYatlProcessor().parse(input, log);
			// Display result
			String unitStr = "null";
			if (unit != null) {
				unitStr = unit.toString();
			}
			// Report message
			log.reportMessage("YATL Model:");
			log.reportMessage(unitStr);
			log.finalReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void analyse() {
		if (crtProject == null) return;
		try {
			// Get input
			String fileName = getCrtTransformation();
			if (fileName == null) return;
			Reader input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			// Analyse input
			log.reset();
			Unit res = crtProject.getYatlProcessor().analyse(input, log, true);
			// Report message
			log.finalReport();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void compile() {
		if (crtProject == null) return;
	}
	public void transform() {
		if (crtProject == null) return;
		try {
			// Get input
			String fileName = getCrtTransformation();
			if (fileName == null) return;
			Reader input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			// Interpret the transformation
			log.reset();
			// Reset target repository
			if (crtProject.getTargetRepository() != null)
				crtProject.getTargetRepository().removeAllElements();
			crtProject.getYatlProcessor().interpret(input, log);
			// Report message
			log.finalReport();
			// View result
			refreshExplorers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected String getCrtTransformation() {
		YatlEditor editor = getCrtEditor();
		if (editor == null) return null;
		return editor.getAbsolutePath();
	}

	//
	// Static constants
	//
	public static final Font font = new Font("dialog", Font.PLAIN, 12);
	public static final Dimension listSize = new Dimension(215, 6*font.getSize());
	public static final Dimension fieldSize = new Dimension(200, 3*font.getSize());
	public static final Dimension wndSize = new Dimension(600, 600);

	//
	// Getters and setters
	//
	public ILog getLog() { return log; }
	public void setLog(ILog log) { this.log = log; }

	public JFileChooser getFileChooser() { return fileChooser; }

	//
	// Local properties
	//
	protected Project crtProject;

	// Panels
	protected JTabbedPane sourcePane;
	protected JTabbedPane targetPane;
	protected JSplitPane mainPanel;
	protected JSplitPane subPanel;
	protected JSplitPane leftPanel;
	protected JSplitPane middlePanel;
	protected JSplitPane rightPanel;
	protected Dimension screenSize;
	protected int barHeight = 33;
	protected double mainDiv = 0.22;
	protected double subDiv = 0.55;
	protected double middleDiv = 0.70;
	protected int dividerSize = 4;

	// Explorer trees
	protected String sourceModelName;
	protected JTree sourceTree;
	protected DefaultTreeModel sourceModel;
	protected DefaultMutableTreeNode sourceRoot;
	protected String targetModelName;
	protected JTree targetTree;
	protected DefaultTreeModel targetModel;
	protected DefaultMutableTreeNode targetRoot;
	protected Vector selectedElementNodes = new Vector();
	
	// Edit panel
	protected JTabbedPane editPane;
	protected JDesktopPane editDesktop;
	protected boolean isTabPane = true;
	protected int editCount;

	// Console
	protected ILog log = new ConsoleLog();

	// File chooser
	protected JFileChooser fileChooser;

	// History
	protected History history = new History();
	
	// Test
	public static void main(String args[]) {
		(new YatlStudio()).setVisible(true);
	}
}