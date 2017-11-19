/**
 *
 *  Class EdocBrowser$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.repository;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import uk.ac.kent.cs.kmf.util.*;

import uk.ac.kent.cs.ocl20.*;
import uk.ac.kent.cs.ocl20.bridge4kmf.*;

import edoc.*;

public class EdocBrowser$Class extends JFrame {
	/** Construct the BROWSER */
	public EdocBrowser$Class(String xmiFileName) {
		//--- Set title and size to full screen ---
		super("KMF [edoc]");
		screenSize = getToolkit().getScreenSize();
		screenSize.setSize(screenSize.width, screenSize.height);
		setSize(screenSize);
		getContentPane().setLayout(new BorderLayout());
		//--- Create MENU ---
		JMenuBar menuBar = createMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		//--- Create EXPLORER ---
		JTabbedPane explorerPane = createExplorer();
		//--- Create DESKTOP ---
		JTabbedPane desktopPane = createDesktop();
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
		leftPanel.setTopComponent(explorerPane);
		rightPanel.setTopComponent(desktopPane);
		rightPanel.setBottomComponent(consoleFrame);
		mainPanel.setLeftComponent(leftPanel);
		mainPanel.setRightComponent(rightPanel);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		//--- Initialize FILE CHOOSER ---
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new BasicFileFilter(new String[] {"xmi", "hutn"}));
		//--- Set the xmiFile ---
		this.xmiFileName = xmiFileName;
		this.rootOffset = "";
		oclProcessor = new KmfOclProcessorImpl(xmiFileName, console);
		//--- Set the repository ---
		setRep(new edoc.repository.EdocRepository$Class());
		//--- Add listener for window closing event ---
		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(wndCloser);
		show();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	// Static constants ---
	public static final Font font = new Font("dialog", Font.PLAIN, 12);
	public static final Dimension listSize = new Dimension(215, 6*font.getSize());
	public static final Dimension fieldSize = new Dimension(200, 3*font.getSize());
	public static final Dimension wndSize = new Dimension(600, 600);

	/** XMI file name which contains the model */
	protected String xmiFileName;
	public String getXmiFileName() { return xmiFileName;}
	public void setXmiFileName(String string) {	xmiFileName = string; }

	/** OclProcessor */
	protected OclProcessor oclProcessor;
	public OclProcessor getOclProcessor() { return oclProcessor; }
	public void setOclProcessor(OclProcessor processor) { oclProcessor = processor;	}

	/** Root offset */
	protected String rootOffset;
	public String getRootOffset() { return rootOffset; }
	public void setRootOffset(String string) { rootOffset = string; }

	/** Repository */
	protected edoc.repository.EdocRepository crtRep;
	public void setRep(edoc.repository.EdocRepository rep) {
		//--- Reset console ---
		resetConsole();
		//--- Set current repository ---
		this.crtRep = rep;
		//--- Set current repository's log ---
		crtRep.setLog(console);
		//--- Update explorer area ---
		refreshExplorer();
	}

	// Panels
	protected JSplitPane mainPanel;
	protected JSplitPane leftPanel;
	protected JSplitPane rightPanel;
	protected Dimension screenSize;
	protected int barHeight = 33;
	protected double mainDiv = 0.22;
	protected double rightDiv = 0.70;
	protected int dividerSize = 4;
	//--- Explorer trees ---
	protected DefaultTreeModel factoriesModel;
	protected JTree factoriesTree;
	protected Vector selectedLifecycleNodes = new Vector();
	protected DefaultTreeModel elementsModel;
	protected JTree elementsTree;
	protected Vector selectedElementNodes = new Vector();
	//--- View, Edit and OCL desktops ---
	protected JDesktopPane viewDesktop;
	protected int viewCount;
	protected JDesktopPane editDesktop;
	protected int editCount;
	protected JDesktopPane evalDesktop;
	protected int evalCount;
	protected JDesktopPane buildDesktop;
	protected int buildCount;
	//--- Console ---
	protected ILog console = new ConsoleLog();
	public ILog getLog() {
		return console;
	}
	//--- File chooser ---
	protected JFileChooser fileChooser;
	protected class BasicFileFilter extends javax.swing.filechooser.FileFilter {
		String ext[];
		public BasicFileFilter() {
			this.ext = new String[] {""};
		}
		public BasicFileFilter(String ext[]) {
			this.ext = new String[ext.length];
			for(int i=0; i<ext.length; i++) this.ext[i] = ext[i];
		}
		public boolean accept(File f) {
			if (f.isDirectory()) return true;
			String path = f.getPath();
			int pos = path.lastIndexOf(".");
			if (pos == -1) return true;
			if (ext.length == 0) return true;
			String ext1 = path.substring(pos+1);
			for(int i=0; i<ext.length; i++) {
				if (ext[i].equals("")) return true;
				if (ext1.equalsIgnoreCase(ext[i])) return true;
			}
			return false;
		}
		public String getDescription() {
			if (ext.length == 0) return "*.*";
			String str = new String();
			str += "*."+ext[0];
			for(int i=1; i<ext.length; i++) {
				str += ", *."+ext[i];
			}
			return str;
		}
	};
	//--- File name ---
	protected String crtFileName;


	/** Create the MENU */
	protected JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();

		//--- Add FILE items ---
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('f');
		//--- Add NEW ---
		JMenuItem item = new JMenuItem("New");
		item.setMnemonic('n');
		ActionListener lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFileAction(e);
			}
		};
		item.addActionListener(lst);
		fileMenu.add(item);
		//--- Add OPEN item ---
		item = new JMenuItem("Open ...");
		item.setMnemonic('o');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFileAction(e);
			}
		};
		item.addActionListener(lst);
		fileMenu.add(item);
		//--- Add CLOSE item ---
		item = new JMenuItem("Close");
		item.setMnemonic('c');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFileAction(e);
			}
		};
		item.addActionListener(lst);
		fileMenu.add(item);
		//--- Add a separator ---
		fileMenu.addSeparator();
		// Add SAVE item
		item = new JMenuItem("Save");
		item.setMnemonic('s');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileAction(e);
			}
		};
		item.addActionListener(lst);
		fileMenu.add(item);
		// Add SAVE AS item
		item = new JMenuItem("Save As ...");
		item.setMnemonic('a');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAsFileAction(e);
			}
		};
		item.addActionListener(lst);
		fileMenu.add(item);
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

		//--- Add VIEW items ---
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic('v');
		//--- Add REFRESH EXPLORER ---
		item = new JMenuItem("Refresh Explorer");
		item.setMnemonic('x');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshExplorer();
			}
		};
		item.addActionListener(lst);
		viewMenu.add(item);
		//--- Add REFRESH VIEW ---
		item = new JMenuItem("Refresh View");
		item.setMnemonic('v');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshViewDesktop();
			}
		};
		item.addActionListener(lst);
		viewMenu.add(item);
		//--- Add REFRESH EDIT ---
		item = new JMenuItem("Refresh Edit");
		item.setMnemonic('d');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshEditDesktop();
			}
		};
		item.addActionListener(lst);
		viewMenu.add(item);
		//--- Add REFRESH BUILD ---
		item = new JMenuItem("Refresh Lifecycle");
		item.setMnemonic('l');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshBuildDesktop();
			}
		};
		item.addActionListener(lst);
		viewMenu.add(item);
		//--- Add a separator ---
		viewMenu.addSeparator();
		//--- Add REFRESH ---
		item = new JMenuItem("Refresh All");
		item.setMnemonic('r');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshExplorer();
				refreshDesktop();
			}
		};
		item.addActionListener(lst);
		viewMenu.add(item);
		//--- Add View ---
		menuBar.add(viewMenu);

		//--- Add EDIT items ---
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('e');
		//--- Add BUILD ELEMENT ---
		item = new JMenuItem("Build Element");
		item.setMnemonic('b');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildEditAction(e);
			}
		};
		item.addActionListener(lst);
		editMenu.add(item);
		//--- Add DELETE ELEMENT ---
		item = new JMenuItem("Delete Element");
		item.setMnemonic('d');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteEditAction(e);
			}
		};
		item.addActionListener(lst);
		editMenu.add(item);
		//--- Add a separator ---
		editMenu.addSeparator();
		//--- Add VIEW PROPERTIES---
		item = new JMenuItem("View Properties");
		item.setMnemonic('v');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classViewAction(e);
			}
		};
		item.addActionListener(lst);
		editMenu.add(item);
		//--- Add EDIT PROPERTIES ---
		item = new JMenuItem("Edit Properties");
		item.setMnemonic('e');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classEditAction(e);
			}
		};
		item.addActionListener(lst);
		editMenu.add(item);
		//--- Add Edit ---
		menuBar.add(editMenu);

		//--- Add TOOLS items ---
		JMenu toolsMenu = new JMenu("Tools");
		toolsMenu.setMnemonic('t');
		//--- Add PARSE ---
		item = new JMenuItem("Parse");
		item.setMnemonic('p');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parseToolsAction(e);
			}
		};
		item.addActionListener(lst);
		toolsMenu.add(item);
		//--- Add EVALUATE ---
		item = new JMenuItem("Evaluate");
		item.setMnemonic('e');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evaluateToolsAction(e);
			}
		};
		item.addActionListener(lst);
		toolsMenu.add(item);
		//--- Add a separator ---
		toolsMenu.addSeparator();
		//--- Add PARSE ALL ---
		item = new JMenuItem("Parse All");
		item.setMnemonic('r');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parseAllToolsAction(e);
			}
		};
		item.addActionListener(lst);
		toolsMenu.add(item);
		//--- Add EVALUATE ALL ---
		item = new JMenuItem("Evaluate All");
		item.setMnemonic('v');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evaluateAllToolsAction(e);
			}
		};
		item.addActionListener(lst);
		toolsMenu.add(item);
		//--- Add a separator ---
		toolsMenu.addSeparator();
		//--- Add OCL EVALUATION item ---
		item = new JMenuItem("OCL Evaluation");
		item.setMnemonic('o');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oclEvaluationToolsAction(e);
			}
		};
		item.addActionListener(lst);
		toolsMenu.add(item);
		//--- Add Tools ---
		menuBar.add(toolsMenu);

		//--- Add WINDOW items ---
		JMenu windowMenu = new JMenu("Window");
		windowMenu.setMnemonic('w');
		//--- Add CLOSE ALL VIEWS ---
		item = new JMenuItem("Close All Views");
		item.setMnemonic('v');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeAllViewWindowsAction(e);
			}
		};
		item.addActionListener(lst);
		windowMenu.add(item);
		//--- Add CLOSE ALL EDIT ---
		item = new JMenuItem("Close All Editors");
		item.setMnemonic('e');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeAllEditWindowsAction(e);
			}
		};
		item.addActionListener(lst);
		windowMenu.add(item);
		//--- Add CLOSE ALL OCL ---
		item = new JMenuItem("Close All Evaluators");
		item.setMnemonic('a');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeAllEvalWindowsAction(e);
			}
		};
		item.addActionListener(lst);
		windowMenu.add(item);
		//--- Add CLOSE ALL BUILD ---
		item = new JMenuItem("Close All Lifecycles");
		item.setMnemonic('b');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeAllBuildWindowsAction(e);
			}
		};
		item.addActionListener(lst);
		windowMenu.add(item);
		//--- Add a separator ---
		windowMenu.addSeparator();
		//--- Add CLOSE ALL ---
		item = new JMenuItem("Close All");
		item.setMnemonic('l');
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeAllWindowsAction(e);
			}
		};
		item.addActionListener(lst);
		windowMenu.add(item);
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
		item = new JMenuItem("About edoc");
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

	/** Create the EXPLORER */
	protected JTabbedPane createExplorer() {
		//--- Create LifecycleS and ELEMENTS frames ---
		JInternalFrame factoriesFrame = new JInternalFrame("Lifecycle Explorer", true);
		JInternalFrame elementsFrame = new JInternalFrame("Elements Explorer", true);
		//--- Create the LifecycleS and ELEMENTS models ---
		DefaultMutableTreeNode factoriesRoot = new DefaultMutableTreeNode("Lifecycle", true);
		factoriesModel = new DefaultTreeModel(factoriesRoot);
		DefaultMutableTreeNode elementsRoot = new DefaultMutableTreeNode("Elements", true);
		elementsModel = new DefaultTreeModel(elementsRoot);
		//--- Create the LifecycleS and ELEMENTS JTrees ---
		factoriesTree = new JTree(factoriesModel);
		factoriesTree.setShowsRootHandles(true);
		factoriesTree.setEditable(false);
		factoriesTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				selectLifecyclesAction(e);
			}
		});
		elementsTree = new JTree(elementsModel);
		elementsTree.setShowsRootHandles(true);
		elementsTree.setEditable(false);
		elementsTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				selectElementsAction(e);
			}
		});
		//--- Add the LIFECYCLE and ELEMENTS JTrees to frames ---
		JScrollPane factoriesScroll = new JScrollPane();
		factoriesScroll.getViewport().add(factoriesTree);
		factoriesFrame.getContentPane().add(factoriesScroll);
		factoriesFrame.setVisible(true);
		JScrollPane elementsScroll = new JScrollPane();
		elementsScroll.getViewport().add(elementsTree);
		elementsFrame.getContentPane().add(elementsScroll);
		elementsFrame.setVisible(true);
		//--- Create the TABBEDPANE ---
		JTabbedPane explorerPane = new JTabbedPane();
		explorerPane.addTab("Elements", elementsFrame);
		explorerPane.addTab("Lifecycle", factoriesFrame);
		return explorerPane;
	}

	/** Create the DESKTOP */
	protected JTabbedPane createDesktop() {
		//--- Create the VIEW, EDIT and OCL desktops ---
		viewDesktop = new JDesktopPane();
		editDesktop = new JDesktopPane();
		evalDesktop = new JDesktopPane();
		buildDesktop = new JDesktopPane();
		//--- Create the TABBEDPANE ---
		JTabbedPane desktopPane = new JTabbedPane();
		desktopPane.addTab("View", viewDesktop);
		desktopPane.addTab("Edit", editDesktop);
		desktopPane.addTab("Evaluate", evalDesktop);
		desktopPane.addTab("Lifecycle", buildDesktop);
		return desktopPane;
	}

	/** Create the CONSOLE */
	protected JInternalFrame createConsole() {
		JInternalFrame frame = new JInternalFrame("Console", true);
		//--- Create console panel ---
		console = new ConsoleLog();
		((ConsoleLog)console).setEditable(false);
		JScrollPane scroller = new JScrollPane((ConsoleLog)console);
		frame.getContentPane().add(scroller);
		frame.setVisible(true);
		return frame;
	}

	/** Reset the BROWSER */
	protected void resetBrowser() {
		/** Reset file name */
		crtFileName = null;
		/** Reset repository */
		crtRep = null;
		/** Reset EXPLORER */
		refreshExplorer();
		/** Reset DESKTOP */
		resetDesktop();
		/** Reset output area */
		resetConsole();
	}

	/** Reset the DESKTOP */
	protected void resetDesktop() {
		//--- Reset all desktops ---
		resetViewDesktop();
		resetEditDesktop();
		resetEvalDesktop();
		resetBuildDesktop();
	}
	/** Reset the VIEW DESKTOP */
	protected void resetViewDesktop() {
		//--- Close all VIEW frames ---
		JInternalFrame frames[] = viewDesktop.getAllFrames();
		for(int i=0; i<frames.length; i++) frames[i].setVisible(false);
		//--- Reset counter ---
		viewCount = 0;
	}
	/** Reset the EDIT DESKTOP */
	protected void resetEditDesktop() {
		//--- Close all EDIT frames ---
		JInternalFrame frames[] = editDesktop.getAllFrames();
		for(int i=0; i<frames.length; i++) frames[i].setVisible(false);
		//--- Reset counter ---
		editCount = 0;
	}
	/** Reset the EVAL DESKTOP */
	protected void resetEvalDesktop() {
		//--- Close all EVAL frames ---
		JInternalFrame frames[] = evalDesktop.getAllFrames();
		for(int i=0; i<frames.length; i++) frames[i].setVisible(false);
		//--- Reset counter ---
		evalCount = 0;
	}
	/** Reset the BUILD DESKTOP */
	protected void resetBuildDesktop() {
		//--- Close all BUILD frames ---
		JInternalFrame frames[] = buildDesktop.getAllFrames();
		for(int i=0; i<frames.length; i++) frames[i].setVisible(false);
		//--- Reset counter ---
		buildCount = 0;
	}
	/** Reset the CONSOLE */
	protected void resetConsole() {
	  console.reset();
	}

	/** Refresh EXPLORER */
	protected void refreshExplorer() {
		if (crtRep == null) return;
		DefaultMutableTreeNode root = crtRep.toJTree();
		factoriesModel.setRoot(root.getChildAt(0));
		elementsModel.setRoot(root.getChildAt(1));
	}

	/** Refresh DESKTOP */
	protected void refreshDesktop() {
		//--- Refresh all desktops ---
		refreshViewDesktop();
		refreshEditDesktop();
		refreshEvalDesktop();
		refreshBuildDesktop();
	}
	/** Refresh VIEW DESKTOP */
	protected void refreshViewDesktop() {
		//--- Refresh all VIEW frames ---
		JInternalFrame frames[] = viewDesktop.getAllFrames();
		for(int i=0; i<frames.length; i++) ((EdocViewEditFrame$Class)frames[i]).refreshAction();
	}
	/** Refresh EDIT DESKTOP */
	protected void refreshEditDesktop() {
		//--- Refresh all EDIT frames ---
		JInternalFrame frames[] = editDesktop.getAllFrames();
		for(int i=0; i<frames.length; i++) ((EdocViewEditFrame$Class)frames[i]).refreshAction();
	}
	/** Refresh EVAL DESKTOP */
	protected void refreshEvalDesktop() {
	}
	/** Refresh BUILD DESKTOP */
	protected void refreshBuildDesktop() {
		//--- Refresh all BUILD frames ---
		JInternalFrame frames[] = buildDesktop.getAllFrames();
		for(int i=0; i<frames.length; i++) ((EdocInvokeMethod$Class)frames[i]).refreshAction();
	}

	/** Refresh CONSOLE */
	protected void refreshConsole() {
		if (crtRep == null) return;
	}

	/**
	  *  MENU actions
	  */
	/** Create a new file */
	protected void newFileAction(ActionEvent e) {
		//--- Set current fileName to null ---
		crtFileName = null;
		//--- Create a new repository ---
		crtRep = new edoc.repository.EdocRepository$Class();
		//--- Refresh explorer area ---
		refreshExplorer();
		//--- Reset output area ---
		refreshConsole();
	}
	/** Open a file */
	protected void openFileAction(ActionEvent e) {
		//--- Open choose file dialog ---
		fileChooser.setDialogTitle("Open");
		fileChooser.setApproveButtonText("Open");
		fileChooser.setApproveButtonToolTipText("Open repository");
		if (fileChooser.showOpenDialog(EdocBrowser$Class.this) != JFileChooser.APPROVE_OPTION) return;
		//--- Get file name ---
		File file = fileChooser.getSelectedFile();
		crtFileName = file.getAbsolutePath();
		//--- Load the repository ---
		edoc.repository.EdocRepository$Class rep1 = null;
		if (crtFileName.endsWith("xmi")) {
			if (crtRep == null) crtRep = new edoc.repository.EdocRepository$Class(console);
			EdocFactory$Class.resetId();
			rep1 = (edoc.repository.EdocRepository$Class)crtRep.loadXMI(crtFileName);
		} else if (crtFileName.endsWith("hutn")) {
			console.reportMessage("HUTN loader is not implemented");
		}
		//--- Reset output area ---
		if (rep1 != null) {
			crtRep = rep1;
			//--- Refresh explorer area ---
			refreshExplorer();
			//--- Add message to output ---
			console.reportMessage("Load repository from '"+crtFileName+"'");
		} else {
			console.reportMessage("Error while reading input file '"+crtFileName+"'");
		}
	}
	/** Close current file */
	protected void closeFileAction(ActionEvent e) {
		saveFileAction(e);
	}
	/** Save current file */
	protected void saveFileAction(ActionEvent e) {
		if (crtRep == null) return;
		if (crtFileName != null) {;
			//--- Save the repository ---
			if (crtFileName.endsWith("xmi")) {
				crtRep.saveXMI(crtFileName);
			} else if (crtFileName.endsWith("hutn")) {
				crtRep.saveHUTN(crtFileName);
			}
			//--- Add message to output ---
			console.reportMessage("Save repository to '"+crtFileName+"'");
		} else {
			//--- Choose file and save ---
			saveAsFileAction(e);
		}
	}
	/** Save current file as a new file */
	protected void saveAsFileAction(ActionEvent e) {
		if (crtRep == null) return;
		fileChooser.setDialogTitle("Save");
		fileChooser.setApproveButtonText("Save");
		fileChooser.setApproveButtonToolTipText("Save repository");
		if (fileChooser.showOpenDialog(EdocBrowser$Class.this) != JFileChooser.APPROVE_OPTION) return;
		//--- Get file name ---
		File file = fileChooser.getSelectedFile();
		crtFileName = file.getAbsolutePath();
		//--- Save the repository ---
		if (crtFileName.endsWith("xmi")) {
			crtRep.saveXMI(crtFileName);
		} else if (crtFileName.endsWith("hutn")) {
			crtRep.saveHUTN(crtFileName);
		}
		//--- Add message to output ---
		console.reportMessage("Save repository to '"+crtFileName+"'");
	}
	/** Exit */
	protected void exitFileAction(ActionEvent e) {
		saveAsFileAction(e);
		if (crtFileName != null) {
			File file = new File("Repository.log");
			file.delete();
		}
		System.exit(0);
	}

	/** Refresh view */
	protected void refreshViewAction(ActionEvent e) {
		if (crtRep == null) return;
		//--- Refresh EXPLORER ---
		refreshExplorer();
		//--- Refresh DESKTOP ---
		refreshDesktop();
	}

	/** Build an object */
	protected void buildEditAction(ActionEvent e) {
		if (crtRep == null) return;
		for(int i=0; i<selectedLifecycleNodes.size(); i++) {
			//--- Get the Lifecycle ---
			DefaultMutableTreeNode methodNode = (DefaultMutableTreeNode)selectedLifecycleNodes.elementAt(i);
			Object methodObject = methodNode.getUserObject();
			if (!(methodObject instanceof Method)) continue;
			Method method = (Method)methodObject;
			DefaultMutableTreeNode factoryNode = (DefaultMutableTreeNode)methodNode.getParent();
			Object factoryObject = factoryNode.getUserObject();
			if (!(factoryObject instanceof edoc.EdocFactory$Class)) continue;
			edoc.EdocFactory factory = (edoc.EdocFactory$Class)factoryObject;
			//--- Make Lifecycle frame ---
			EdocInvokeMethod$Class buildFrame = new EdocInvokeMethod$Class(crtRep, this, factory, method);
			//--- Set Lifecycle's location ---
			if (buildDesktop.getAllFrames().length == 0) buildCount = 0;
			int x1 = buildCount/20;
			if (x1 == 3) x1 = 0;
			int x = buildCount%20+10*x1;
			int y = buildCount%20;
			buildFrame.setLocation(25*x, 25*y);
			//--- Add Lifecycle ---
			buildDesktop.add(buildFrame);
			buildFrame.setVisible(true);
			buildCount++;
		}
	}
	/** Delete an object */
	protected void deleteEditAction(ActionEvent e) {
		if (crtRep == null) return;
		for(int i=0; i<selectedElementNodes.size(); i++) {
			//--- Get the ELEMENT ---
			Object userObject = ((DefaultMutableTreeNode)selectedElementNodes.elementAt(i)).getUserObject();
			if (!(userObject instanceof EdocElement)) continue;
			EdocElement element = (EdocElement)userObject;
			//--- Remove from repository ---
			String classPathName = element.toString();
			int pos = classPathName.indexOf("'");
			if (pos != -1) classPathName = classPathName.substring(0, pos-1);
			crtRep.getElements(classPathName).remove(element);
			//--- Delete links ---
			element.delete();
		}
	}
	/** View an object */
	protected void classViewAction(ActionEvent e) {
		if (crtRep == null) return;
		for(int i=0; i<selectedElementNodes.size(); i++) {
			//--- Get the ELEMENT ---
			Object userObject = ((DefaultMutableTreeNode)selectedElementNodes.elementAt(i)).getUserObject();
			if (!(userObject instanceof EdocElement)) continue;
			EdocElement element = (EdocElement)userObject;
			//--- Make the VIEW frame ---
			EdocViewVisitor view = new EdocViewVisitor$Class(oclProcessor);
			JInternalFrame viewFrame = (JInternalFrame)element.accept(view, null);
			//--- Set the VIEW's location ---
			if (viewDesktop.getAllFrames().length == 0) viewCount = 0;
			int x1 = viewCount/20;
			if (x1 == 3) x1 = 0;
			int x = viewCount%20+10*x1;
			int y = viewCount%20;
			viewFrame.setLocation(25*x, 25*y);
			//--- Add VIEW ---
			viewDesktop.add(viewFrame);
			viewFrame.setVisible(true);
			viewCount++;
		}
	}
	/** Edit an object */
	protected void classEditAction(ActionEvent e) {
		if (crtRep == null) return;
		for(int i=0; i<selectedElementNodes.size(); i++) {
			//--- Get the ELEMENT ---
			Object userObject = ((DefaultMutableTreeNode)selectedElementNodes.elementAt(i)).getUserObject();
			if (!(userObject instanceof EdocElement)) continue;
			EdocElement element = (EdocElement)userObject;
			//--- Make the EDIT frame ---
			EdocEditVisitor edit = new EdocEditVisitor$Class(oclProcessor);
			JInternalFrame editFrame = (JInternalFrame)element.accept(edit, crtRep);
			//--- Set the EDIT's location ---
			if (editDesktop.getAllFrames().length == 0) editCount = 0;
			int x1 = editCount/20;
			if (x1 == 3) x1 = 0;
			int x = editCount%20+10*x1;
			int y = editCount%20;
			editFrame.setLocation(25*x, 25*y);
			//--- Add EDIT ---
			editDesktop.add(editFrame);
			editFrame.setVisible(true);
			editCount++;
		}
	}

	/** Parse invariants for current object */
	protected void parseToolsAction(ActionEvent e) {
		if (crtRep == null) return;
	}
	/** Evaluate invariants for current object */
	protected void evaluateToolsAction(ActionEvent e) {
		if (crtRep == null) return;
	}
	/** Parse all invariants */
	protected void parseAllToolsAction(ActionEvent e) {
		if (crtRep == null) return;
	}
	/** Evaluate all invariants */
	protected void evaluateAllToolsAction(ActionEvent e) {
		if (crtRep == null) return;
	}
	/** Run-time invariant evaluation */
	protected void oclEvaluationToolsAction(ActionEvent e) {
		if (crtRep == null) return;
	}

	/** Close all view windows */
	protected void closeAllViewWindowsAction(ActionEvent e) {
		//--- Reset desktop ---
		resetViewDesktop();
	}
	/** Close all edit windows */
	protected void closeAllEditWindowsAction(ActionEvent e) {
		//--- Reset desktop ---
		resetEditDesktop();
	}
	/** Close all evaluate windows */
	protected void closeAllEvalWindowsAction(ActionEvent e) {
		//--- Reset desktop ---
		resetEvalDesktop();
	}
	/** Close all build windows */
	protected void closeAllBuildWindowsAction(ActionEvent e) {
		//--- Reset desktop ---
		resetBuildDesktop();
	}
	/** Close all windows */
	protected void closeAllWindowsAction(ActionEvent e) {
		//--- Reset desktop ---
		resetDesktop();
	}

	/** Readme */
	protected void readmeHelpAction(ActionEvent e) {
	}
	/** About */
	protected void aboutHelpAction(ActionEvent e) {
	}

	/** Select lifecycle */
	protected void selectLifecyclesAction(TreeSelectionEvent e) {
		TreePath paths[] = factoriesTree.getSelectionPaths();
		selectedLifecycleNodes.clear();
		if (paths == null) return;        
		for(int i=0; i<paths.length; i++) {
			selectedLifecycleNodes.add(paths[i].getLastPathComponent());
		}
	}
	/** Select element */
	protected void selectElementsAction(TreeSelectionEvent e) {
		TreePath paths[] = elementsTree.getSelectionPaths();
		selectedElementNodes.clear();
		if (paths == null) return;        
		for(int i=0; i<paths.length; i++) {
			selectedElementNodes.add(paths[i].getLastPathComponent());
		}
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
		new EdocBrowser$Class("C:/D/Work/Java Projects/YATL4KMF/src/test/scripts/EDOC.xmi");
	}
}