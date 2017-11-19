package uk.ac.kent.cs.kmf.kmfstudio;

import java.io.*;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import uk.ac.kent.cs.kmf.kmfstudio.gui.StudioGUI;
import uk.ac.kent.cs.kmf.quality.QualityModel;
import uk.ac.kent.cs.kmf.quality.metrics.Metric;
import uk.ac.kent.cs.kmf.quality.metrics.MetricFactory;
import uk.ac.kent.cs.kmf.quality.metrics.MetricSet;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;
import uk.ac.kent.cs.kmf.util.Pair;
import uk.ac.kent.cs.kmf.util.XMIToUMLLoader;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class ProjectImpl
	implements Project 
{
	/** Constructor */
	public ProjectImpl() {
	}

	/** Project path */
	protected String projectPath = new String();
	public String getProjectPath() { return projectPath; }
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath.replace('\\', '/');
	}

	/** Project name */
	protected String projectName = new String();
	public String getProjectName() { return projectName; }
	public void setProjectName(String projectName) {
		if (!projectName.endsWith(".ksp")) projectName += ".ksp"; 
		this.projectName = projectName;
	}

	/** Project type */
	protected String projectType = new String();
	public String getProjectType() { return projectType; }
	public void setProjectType(String projectType) {
		this.projectType = projectType.toLowerCase();
	}

	/**
	 * Returns the projectFileName.
	 * @return String
	 */
	public String getProjectFileName() {
		String path = getProjectPath();
		if (!path.endsWith("/")) path += "/";
		String name = getProjectName(); 
		return path+name;
	}

	/** Input file name */
	protected String xmiFileName = new String();
	public String getXmiFileName() { return xmiFileName; }
	public void setXmiFileName(String xmiFileName) { this.xmiFileName = xmiFileName;}

	/** Metamodel file name */
	protected String metaXmiFileName = new String();
	public String getMetaXmiFileName() { return metaXmiFileName; }
	public void setMetaXmiFileName(String metaXmiFileName) { this.metaXmiFileName = metaXmiFileName;}

	/** Lincense file name */
	protected String licenseFileName = new String();
	public String getLicenseFileName() { return licenseFileName; }
	public void setLicenseFileName(String licenseFileName) { this.licenseFileName = licenseFileName; }

	/** Package offset */
	protected String packageOffset = new String();
	public String getPackageOffset() { return packageOffset; }
	public void setPackageOffset(String packageOffset) { this.packageOffset = packageOffset; }

	/** Model name */
	protected String modelName = new String();
	public String getModelName() { return modelName; }
	public void setModelName(String modelName) { this.modelName = modelName; }

	/** Generate visitor */
	protected boolean generateVisitor;
	public boolean isGenerateVisitor() { return generateVisitor; }
	public void setGenerateVisitor(boolean generateVisitor) { this.generateVisitor = generateVisitor; }

	/** Generate HUTN visitor */
	protected boolean generateHUTNVisitor;
	public boolean isGenerateHUTNVisitor() { return generateHUTNVisitor; }
	public void setGenerateHUTNVisitor(boolean generateVisitor) { this.generateHUTNVisitor = generateVisitor; }

	/** Generate JTree visitor */
	protected boolean generateJTreeVisitor;
	public boolean isGenerateJTreeVisitor() { return generateJTreeVisitor; }
	public void setGenerateJTreeVisitor(boolean generateVisitor) {	this.generateJTreeVisitor = generateVisitor; }

	/** Generate ViewEdit visitor */
	protected boolean generateViewEditVisitor;
	public boolean isGenerateViewEditVisitor() { return generateViewEditVisitor; }
	public void setGenerateViewEditVisitor(boolean generateVisitor) { this.generateViewEditVisitor = generateVisitor; }

	/** Generate XMI visitor */
	protected boolean generateXMIVisitor;
	public boolean isGenerateXMIVisitor() { return generateXMIVisitor; }
	public void setGenerateXMIVisitor(boolean generateVisitor) { this.generateXMIVisitor = generateVisitor;	}

	/** Generate observer */
	protected boolean generateObserver;
	public boolean isGenerateObserver() { return generateObserver;	}
	public void setGenerateObserver(boolean generateObserver) {	this.generateObserver = generateObserver; }

	/** Generate visitor */
	protected boolean generateInvariant;
	public boolean isGenerateInvariant() { return generateInvariant; }
	public void setGenerateInvariant(boolean generateInvariant) { this.generateInvariant = generateInvariant; }

	/** Generate ID */
	protected boolean generateID;
	public boolean isGenerateID() {	return generateID; }
	public void setGenerateID(boolean ID) {	this.generateID = ID; }

	/** Generate factory */
	protected boolean generateFactory;
	public boolean isGenerateFactory() { return generateFactory; }
	public void setGenerateFactory(boolean generateFactory) { this.generateFactory = generateFactory; }

	/** Generate repository */
	protected boolean generateRepository;
	public boolean isGenerateRepository() {	return generateRepository; }
	public void setGenerateRepository(boolean generateRepository) {	this.generateRepository = generateRepository; }

	/** Generate browser */
	protected boolean generateBrowser;
	public boolean isGenerateBrowser() { return generateBrowser; }
	public void setGenerateBrowser(boolean generateBrowser) { this.generateBrowser = generateBrowser; }
	
	/** Generate startup */
	protected boolean generateStartup;
	public boolean isGenerateStartup() { return generateStartup; }
	public void setGenerateStartup(boolean generateStartup) { this.generateStartup = generateStartup; }

	/** Generate bidirectinal links */
	protected boolean generateBidirectional;
	public boolean isGenerateBidirectional() { return generateBidirectional;}
	public void setGenerateBidirectional(boolean b) { this.generateBidirectional = b;}
	
	/** Interface prefix */
	protected String interfacePrefix = "";
	public String getInterfacePrefix() { return interfacePrefix; }
	public void setInterfacePrefix(String s) { this.interfacePrefix = s; }

	/** Interface suffix */
	protected String interfaceSuffix = "";
	public String getInterfaceSuffix() { return interfaceSuffix; }
	public void setInterfaceSuffix(String s) { this.interfaceSuffix = s; }

	/** Class prefix */
	protected String classPrefix = "";
	public String getClassPrefix() { return classPrefix; }
	public void setClassPrefix(String s) { this.classPrefix = s; }
	
	/** Class suffix */
	protected String classSuffix = "$Class";
	public String getClassSuffix() { return classSuffix; }
	public void setClassSuffix(String s) { this.classSuffix = s; }

	/** Collection interface */
	protected String collectionInterface = "java.util.List";
	public String getCollectionInterface() { return collectionInterface; }
	public void setCollectionInterface(String s) { this.collectionInterface = s; }
	
	/** Collection class */
	protected String collectionClass = "java.util.Vector";
	public String getCollectionClass() { return collectionClass; }
	public void setCollectionClass(String s) { this.collectionClass = s; }
	
	/** List interface */
	protected String listInterface = "java.util.List";
	public String getListInterface() { return listInterface; }
	public void setListInterface(String s) { this.listInterface = s; }

	/** List class */
	protected String listClass = "java.util.Vector";
	public String getListClass() { return listClass; }
	public void setListClass(String s) { this.listClass = s; }

	/** Set interface */
	protected String setInterface = "java.util.Set";
	public String getSetInterface() { return setInterface; }
	public void setSetInterface(String s) { this.setInterface = s; }

	/** Set class */
	protected String setClass = "java.util.LinkedHashSet";
	public String getSetClass() { return setClass; }
	public void setSetClass(String s) { this.setClass = s; }
	
	/** Metrics all metrics*/
	protected MetricSet metricSet = new MetricSet();
	public MetricSet getMetricSet() { return metricSet; }
	public void setMetricSet(MetricSet set) { metricSet = set; }
	public List getMetrics() { return metricSet.getMetrics(); }
	public Metric getMetric(String key) { return metricSet.getMetric(key); }
	public void addMetric(Metric m) { metricSet.addMetric(m); }
	public void removeMetric(Metric m) { metricSet.removeMetric(m); }

	/** Load and get model */
	protected Model model;
	protected UmlRepository rep;
	protected DefaultMutableTreeNode root;
	protected Context context;
	protected QualityModel qualityModel;
	public Model getModel(ILog log) {
		if (model != null)
			return model;
		// Load model
		try {
			Pair pair = ((new XMIToUMLLoader()).loadModel(getXmiFileName(), log));
			model = (Model)pair.getFirst();
			context = new Context(this, new NamingImpl(this, log), log);
			qualityModel = new QualityModel(context, log);
			rep = (UmlRepository)pair.getSecond();
//			root = rep.toJTree();
		} catch (Exception e) {
			log.reportError("Cannot load model '"+getXmiFileName()+"'");
		}
		return model;
	}
	public Model getMetaModel(ILog log) {
		Model metaModel = StudioGUI.metaModel;
		if (metaModel != null)
			return metaModel;
		// Load model
		try {
			String xmiFileName = getXmiFileName(); 
			if (xmiFileName != null && xmiFileName.length() != 0) {
				Pair pair = ((new XMIToUMLLoader()).loadModel(getMetaXmiFileName(), log));
				StudioGUI.metaModel = (Model)pair.getFirst();
			}
		} catch (Exception e) {
			log.reportError("Cannot load model '"+getMetaXmiFileName()+"'");
		}
		return metaModel;
	}
	public UmlRepository getRepository(ILog log) {
		if (rep != null)
			return rep;
		// Load model
		try {
			String xmiFileName = getXmiFileName(); 
			if (xmiFileName != null && xmiFileName.length() != 0) {
				Pair pair = ((new XMIToUMLLoader()).loadModel(getXmiFileName(), log));
				model = (Model)pair.getFirst();
				context = new Context(this, new NamingImpl(this, log), log);
				qualityModel = new QualityModel(context, log);
				rep = (UmlRepository)pair.getSecond();
			}
//			root = rep.toJTree();
		} catch (Exception e) {
			log.reportError("Cannot load model '"+getXmiFileName()+"'");
		}
		return rep;
	}
	public Context getContext(ILog log) {
		if (model == null) getModel(log);
		return context;
	}
	public QualityModel getQualityModel(ILog log) {
		if (model == null) getModel(log);
		return qualityModel;
	}
	public DefaultMutableTreeNode toJTree(ILog log) {
		if (root != null)
			return root;
		// Load model
		try {
			Pair pair = ((new XMIToUMLLoader()).loadModel(getXmiFileName(), log));
			model = (Model)pair.getFirst();
			rep = (UmlRepository)pair.getSecond();
			root = rep.toJTree();
		} catch (Exception e) {
			log.reportError("Cannot load model '"+getXmiFileName()+"'");
		}
		return root;
	}
	
 	/** 
	 * Save a project into a file 
	 */
	public void saveSettings() {
		String projectFileName = getProjectFileName();
		String indent = "\t";
		try {
			PrintWriter out = new PrintWriter(new FileWriter(new File(projectFileName)));
	        out.println("<?xml version=\"1.0\"?>");
	        out.println("<project type =\""+getProjectType()+"\">");
	        out.println(indent+"<path>"+getProjectPath()+"</path>");
	        out.println(indent+"<name>"+getProjectName()+"</name>");
			out.println(indent+"<meta>"+getXmiFileName()+"</meta>");
	        out.println(indent+"<xmi>"+getXmiFileName()+"</xmi>");
	        out.println(indent+"<license>"+getLicenseFileName()+"</license>");
	        out.println(indent+"<offset>"+getPackageOffset()+"</offset>");
	        out.println(indent+"<modelName>"+getModelName()+"</modelName>");
	        out.println(indent+"<visitor>"+isGenerateVisitor()+"</visitor>");
	        out.println(indent+"<HUTNvisitor>"+isGenerateHUTNVisitor()+"</HUTNvisitor>");
	        out.println(indent+"<JTreevisitor>"+isGenerateJTreeVisitor()+"</JTreevisitor>");
	        out.println(indent+"<ViewEditvisitor>"+isGenerateViewEditVisitor()+"</ViewEditvisitor>");
	        out.println(indent+"<XMIvisitor>"+isGenerateXMIVisitor()+"</XMIvisitor>");
	        out.println(indent+"<observer>"+isGenerateObserver()+"</observer>");
	        out.println(indent+"<invariant>"+isGenerateInvariant()+"</invariant>");
	        out.println(indent+"<id>"+isGenerateID()+"</id>");
	        out.println(indent+"<factory>"+isGenerateFactory()+"</factory>");
	        out.println(indent+"<repository>"+isGenerateRepository()+"</repository>");
	        out.println(indent+"<browser>"+isGenerateBrowser()+"</browser>");
	        out.println(indent+"<startup>"+isGenerateStartup()+"</startup>");
			out.println(indent+"<bidirectional>"+isGenerateBidirectional()+"</bidirectional>");
			out.println(indent+"<interfacePrefix>"+getInterfacePrefix()+"</interfacePrefix>");
			out.println(indent+"<interfaceSuffix>"+getInterfaceSuffix()+"</interfaceSuffix>");
			out.println(indent+"<classPrefix>"+getClassPrefix()+"</classPrefix>");
			out.println(indent+"<classSuffix>"+getClassSuffix()+"</classSuffix>");
			out.println(indent+"<collectionInterface>"+getCollectionInterface()+"</collectionInterface>");
			out.println(indent+"<collectionClass>"+getCollectionClass()+"</collectionClass>");
			out.println(indent+"<listInterface>"+getListInterface()+"</listInterface>");
			out.println(indent+"<listClass>"+getListClass()+"</listClass>");
			out.println(indent+"<setInterface>"+getSetInterface()+"</setInterface>");
			out.println(indent+"<setClass>"+getSetClass()+"</setClass>");
			out.println(indent+"<setClass>"+getSetClass()+"</setClass>");			
			out.println(indent+"<metrics>");
			List metrics = metricSet.getMetrics();
			for(int i=0; i<metrics.size(); i++) {
				Metric m = (Metric)metrics.get(i);
				String header = indent+indent+"<metric ";
				header += "namespace='"+m.getNamespace()+"' ";
				header += "key='"+m.getKey()+"' ";
				header += "name='"+m.getName()+"' ";
				header += "type='"+m.getType()+"' ";
				header += "min='"+m.getMin()+"' ";
				header += "max='"+m.getMax()+"' ";
				header += ">";
				out.println(header);
				out.println(indent+indent+indent+"<body>"+m.getBody()+"</body>");
				out.println(indent+indent+indent+"<diagnostic>"+m.getDiagnostic()+"</diagnostic>");
				out.println(indent+indent+"</metric>");
			}
			out.println(indent+"</metrics>");			
	        out.println("</project>");
	        out.close();
			//--- End header
		} catch (Exception e) {
		}
	}
	/** 
	 * Load the project setting
	 */
	public void loadSettings(String projectFileName, ILog log) {
		//--- Get file name, and path name ---
		String temp = projectFileName.replace('\\', '/');
		int pos = temp.lastIndexOf("/");
		if (pos != -1) {
			projectName = temp.substring(pos+1, temp.length());
			projectPath = temp.substring(0, pos);
		} else {
			projectName = temp;
		}
		loadSettings(log);
	}
	public void loadSettings(String projectFileName) {
		//--- Get file name, and path name ---
		String temp = projectFileName.replace('\\', '/');
		int pos = temp.lastIndexOf("/");
		if (pos != -1) {
			projectName = temp.substring(pos+1, temp.length());
			projectPath = temp.substring(0, pos);
		} else {
			projectName = temp;
		}
		loadSettings(new OutputStreamLog(System.out));
    }
	public void loadSettings(ILog log) {
		String projectFileName = getProjectFileName();
		try {
		    //--- Create the XMLReader ---
		    String parserClassName = "org.apache.xerces.parsers.SAXParser";
		    XMLReader reader = XMLReaderFactory.createXMLReader(parserClassName);
		    //--- Create and register the ContentHandler ---
		    ContentHandler projectHandler = new ProjectHandler(this);
		    reader.setContentHandler(projectHandler);
	    	//--- Parse ---
	    	InputSource inputSource = new InputSource("file:///"+projectFileName);
	    	reader.parse(inputSource);
	    	// Load model
	    	getModel(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadSettings() {
		loadSettings(new OutputStreamLog(System.out));		
	}

	/**
	 * Override default toString.
	 */
	public String toString() {
		return projectName;
	}
}

class ProjectHandler implements ContentHandler {
	protected Project project; 
	protected String text;
	protected MetricFactory factory = new MetricFactory();
	protected MetricSet metricSet;
	protected Metric metric;
	protected Locator locator;
	public ProjectHandler(Project project) {
		this.project = project;
	}
	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
	}
	public void startDocument() throws SAXException {
	}
	public void endDocument() throws SAXException {
	}
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
	}
	public void endPrefixMapping(String prefix) throws SAXException {
	}
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
	    text = new String();
	    if (localName.equals("project")) {
	    	String projectType = atts.getValue("type");
	    	if (projectType == null) projectType = new String();
	    	project.setProjectType(projectType);
	    } else if (localName.equals("metrics")) {
			metricSet = factory.buildMetricSet();
		} else if (localName.equals("metric")) {
			metric = factory.buildMetric();
			metric.setNamespace(atts.getValue("namespace"));
			metric.setKey(atts.getValue("key"));
			metric.setName(atts.getValue("name"));
			metric.setType(atts.getValue("type"));
			metric.setMin(getDouble(atts.getValue("min")));
			metric.setMax(getDouble(atts.getValue("max")));
		} else if (localName.equals("body")) {
			text = "";
		} else if (localName.equals("message")) {
			text = "";
	    }
	}
	protected double getDouble(String string) {
		if (string.equals("POSITIVE_INFINITY")) return Double.POSITIVE_INFINITY;
		if (string.equals("NEGATIVE_INFINITY")) return Double.NEGATIVE_INFINITY;
		if (string.equals("MAX_VALUE")) return Double.MAX_VALUE;
		if (string.equals("MIN_VALUE")) return Double.MIN_VALUE;
		return Double.parseDouble(string);
	}
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
	    if (localName.equals("project")) {
	    } else if (localName.equals("name")) {
	    	project.setProjectName(text);
	    } else if (localName.equals("path")) {
	    	project.setProjectPath(text);
	    } else if (localName.equals("xmi")) {
	    	project.setXmiFileName(text);
	    } else if (localName.equals("license")) {
	    	project.setLicenseFileName(text);
	    } else if (localName.equals("offset")) {
	    	project.setPackageOffset(text);
	    } else if (localName.equals("modelName")) {
	    	project.setModelName(text);
	    } else if (localName.equals("visitor")) {
	    	project.setGenerateVisitor((new Boolean(text)).booleanValue());
	    } else if (localName.equals("HUTNvisitor")) {
	    	project.setGenerateHUTNVisitor((new Boolean(text)).booleanValue());
	    } else if (localName.equals("JTreevisitor")) {
	    	project.setGenerateJTreeVisitor((new Boolean(text)).booleanValue());
	    } else if (localName.equals("ViewEditvisitor")) {
	    	project.setGenerateViewEditVisitor((new Boolean(text)).booleanValue());
	    } else if (localName.equals("XMIvisitor")) {
	    	project.setGenerateXMIVisitor((new Boolean(text)).booleanValue());
	    } else if (localName.equals("observer")) {
	    	project.setGenerateObserver((new Boolean(text)).booleanValue());
	    } else if (localName.equals("invariant")) {
	    	project.setGenerateInvariant((new Boolean(text)).booleanValue());
	    } else if (localName.equals("id")) {
	    	project.setGenerateID((new Boolean(text)).booleanValue());
	    } else if (localName.equals("factory")) {
	    	project.setGenerateFactory((new Boolean(text)).booleanValue());
	    } else if (localName.equals("repository")) {
	    	project.setGenerateRepository((new Boolean(text)).booleanValue());
	    } else if (localName.equals("browser")) {
	    	project.setGenerateBrowser((new Boolean(text)).booleanValue());
	    } else if (localName.equals("startup")) {
	    	project.setGenerateStartup((new Boolean(text)).booleanValue());
		} else if (localName.equals("bidirectional")) {
			project.setGenerateBidirectional((new Boolean(text)).booleanValue());
		} else if (localName.equals("interfacePrefix")) {
			project.setInterfacePrefix(text);
		} else if (localName.equals("interfaceSuffix")) {
			project.setInterfaceSuffix(text);
		} else if (localName.equals("classPrefix")) {
			project.setClassPrefix(text);
		} else if (localName.equals("classSuffix")) {
			project.setClassSuffix(text);
		} else if (localName.equals("collectionInterface")) {
			project.setCollectionInterface(text);
		} else if (localName.equals("collectionClass")) {
			project.setCollectionClass(text);
		} else if (localName.equals("listInterface")) {
			project.setListInterface(text);
		} else if (localName.equals("listClass")) {
			project.setListClass(text);
		} else if (localName.equals("setInterface")) {
			project.setSetInterface(text);
		} else if (localName.equals("setClass")) {
			project.setSetClass(text);
		} else if (localName.equals("setClass")) {
			project.setSetClass(text);
		} else if (localName.equals("metrics")) {
			project.setMetricSet(metricSet);
		} else if (localName.equals("metric")) {
			metricSet.addMetric(metric);
		} else if (localName.equals("body")) {
			metric.setBody(text);
		} else if (localName.equals("message")) {
			metric.setDiagnostic(text);
	    } else {
	    }
	}
	public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
	    text += str;
	}
	public void ignorableWhitespace(char ch[], int start, int length) throws SAXException {
	}
	public void processingInstruction(String target, String data) throws SAXException {
	}
	public void skippedEntity(String name) throws SAXException {
	}
}
