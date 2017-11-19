package uk.ac.kent.cs.yatl.bridge4kmf.gui;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import uk.ac.kent.cs.kmf.common.Repository;
import uk.ac.kent.cs.kmf.common.Warehouse;
import uk.ac.kent.cs.kmf.common.WarehouseImpl;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.Pair;
import uk.ac.kent.cs.kmf.util.XMIToUMLLoader;
import uk.ac.kent.cs.ocl20.bridge4kmf.KmfBridgeFactoryImpl;
import uk.ac.kent.cs.ocl20.bridge4kmf.KmfImplementationAdapterImpl;
import uk.ac.kent.cs.ocl20.bridge4kmf.KmfOclProcessorImpl;
import uk.ac.kent.cs.yatl.YatlProcessor;
import uk.ac.kent.cs.yatl.bridge4kmf.Yatl4KmfProcessorImpl;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class Project {
	public Project(ILog log) {
		this("noname.ysp", new WarehouseImpl(), log);
	}
	public Project(Warehouse warehouse, ILog log) {
		this("noname.ysp", warehouse, log);
	}
	public Project(String projectFileName, Warehouse warehouse, ILog log) {
		this.warehouse = warehouse;
		this.projectPathname = projectFileName;
		this.log = log;
	}
	public Project(File xmlFile, ILog log) {
		this(xmlFile.getAbsolutePath(), new WarehouseImpl(log), log);
	}
	
	// Load project
	public void loadProject() {
		try {
			// Create the XMLReader
			String parserClassName = "org.apache.xerces.parsers.SAXParser";
			XMLReader reader = XMLReaderFactory.createXMLReader(parserClassName);
			// Create and register the ContentHandler
			ContentHandler projectHandler = new ProjectHandler(this);
			reader.setContentHandler(projectHandler);
			// Parse
			InputSource inputSource = new InputSource("file:///"+projectPathname);
			reader.parse(inputSource);
		} catch (Exception e) {
		}
		try {
			// Load models, repositories, and register them
			if (sourceModelFileName != null && sourceModelFileName.length() != 0) {
				// Load model and set model name
				Pair pair = loadModel(sourceModelFileName, log);
				sourceModel = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model)pair.getFirst();
				sourceModelName = sourceModel.getName().getBody_();
				// Load repository and register it
				if (this.sourceRepClassName == null || this.sourceRepClassName.length() == 0) {
					sourceRepository = warehouse.getRepository(sourceModelName);
				} else {
					sourceRepository = this.getRepository(sourceRepClassName);
				}
				if (sourceRepository != null) {
					warehouse.registerRepository(sourceModelName, sourceRepository);
					if (sourceRepFileName != null && sourceRepFileName.length() != 0) {
						sourceRepository.setLog(log);
						// Check if repository file exists
						try {
							File file = new File(sourceRepFileName);
							if (file.exists()) {
								sourceRepository = sourceRepository.loadXMI(sourceRepFileName); 
								warehouse.registerRepository(sourceModelName, sourceRepository);
							} else {
								log.reportMessage("Source repository is empty.");
							}
						} catch (Exception e) {
							log.reportMessage("Source repository is empty.");
						}
					}
				}
			}
			if (targetModelFileName != null && targetModelFileName.length() != 0) {
				// Load model and set model name
				Pair pair = loadModel(targetModelFileName, log);
				targetModel = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model)pair.getFirst();
				targetModelName = targetModel.getName().getBody_();
				// Load repository and register it
				if (this.targetRepClassName == null || this.targetRepClassName.length() == 0) {
					targetRepository = warehouse.getRepository(targetModelName);
				} else {
					targetRepository = this.getRepository(targetRepClassName);
				}
				if (targetRepository != null) {
					warehouse.registerRepository(targetModelName, targetRepository);
					if (targetRepFileName != null && targetRepFileName.length() != 0) {
						targetRepository.setLog(log);
						// Check if repository file exists
						try {
							File file = new File(targetRepFileName);
							if (file.exists()) {
								targetRepository = targetRepository.loadXMI(targetRepFileName); 
								warehouse.registerRepository(targetModelName, targetRepository);
							} else {
								log.reportMessage("Target repository is empty.");
							}
						} catch (Exception e) {
							log.reportMessage("Target repository is empty.");
						}
					}
				}
			}
			// Create the yatl processor
			yatlProcessor = makeProcessor();
		} catch (Exception e) {
			if (log != null)
				log.reportError(""+e);
			else
				e.printStackTrace();
		}
	}
	protected YatlProcessor makeProcessor() {
		// Create an OclProcessor, add models, and set storage area in adapter
		KmfOclProcessorImpl oclProcessor = new KmfOclProcessorImpl(log);
		oclProcessor.setBridgeFactory(new KmfBridgeFactoryImpl(oclProcessor));		
		oclProcessor.addModel(sourceModel);
		oclProcessor.addModel(targetModel);
		((KmfImplementationAdapterImpl)oclProcessor.getModelImplAdapter()).setWarehouse(warehouse);

		// Create a KtlProcessor
		YatlProcessor yatlProcessor = new Yatl4KmfProcessorImpl(oclProcessor, log);
		return yatlProcessor;
	}
	public Pair loadModel(String xmiFileName, ILog log) {
		Pair pair = null;
		try {
			//--- Load the model ---
			pair = (new XMIToUMLLoader()).loadModel(xmiFileName, log); 
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model model = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model)pair.getFirst();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pair;
	}

	// Save project
	/** 
	 * Save a project into a file 
	 */
	public void saveProject() {
		try {
			File xmlFile = new File(projectPathname);		
			PrintWriter out = new PrintWriter(new FileWriter(xmlFile));
			out.println("<?xml version=\"1.0\"?>");
			// Project
			out.println("<"+ProjectHandler.projectTag+">");
			// Pathname
			out.println(tab+"<"+ProjectHandler.pathnameTag+">"+xmlFile.getAbsolutePath()+"</"+ProjectHandler.pathnameTag+">");
			// Transformation
			out.println(tab+"<"+ProjectHandler.transformationTag+">"+transfFileName+"</"+ProjectHandler.transformationTag+">");
			// Source
			out.println(tab+"<"+ProjectHandler.sourceTag+">");
			out.println(tab+tab+"<"+ProjectHandler.modelTag+">"+sourceModelFileName+"</"+ProjectHandler.modelTag+">");
			out.println(tab+tab+"<"+ProjectHandler.repositoryClassTag+">"+sourceRepClassName+"</"+ProjectHandler.repositoryClassTag+">");
			out.println(tab+tab+"<"+ProjectHandler.repositoryContentTag+">"+sourceRepFileName+"</"+ProjectHandler.repositoryContentTag+">");
			out.println(tab+"</"+ProjectHandler.sourceTag+">");
			// Target
			out.println(tab+"<"+ProjectHandler.targetTag+">");
			out.println(tab+tab+"<"+ProjectHandler.modelTag+">"+targetModelFileName+"</"+ProjectHandler.modelTag+">");
			out.println(tab+tab+"<"+ProjectHandler.repositoryClassTag+">"+targetRepClassName+"</"+ProjectHandler.repositoryClassTag+">");
			out.println(tab+tab+"<"+ProjectHandler.repositoryContentTag+">"+targetRepFileName+"</"+ProjectHandler.repositoryContentTag+">");
			out.println(tab+"</"+ProjectHandler.targetTag+">");
			// Close project
			out.println("</"+ProjectHandler.projectTag+">");
			out.close();
		} catch (Exception e) {
			if (log != null)
				log.reportError(""+e);
			else
				e.printStackTrace();
		}
	}

	//
	// Getters and setters
	//
	public YatlProcessor getYatlProcessor() { return yatlProcessor; }
	public void setYatlProcessor(YatlProcessor processor) { yatlProcessor = processor;	}

	public String getProjectName() {
		// Remove extension if it's ysp
		String temp[] = projectPathname.replace('.', '/').replace('\\', '/').split("/");
		int n = temp.length; 
		if (n == 0 || n == 1)
			return projectPathname;
		else if (temp[n-1].equals("ysp")) 
			return temp[n-2];
		else
			return temp[n-1];
	}
	public String getProjectPathName() {
		return projectPathname;
	}
	public void setProjectPathName(String string) {
		projectPathname = string;
	}

	public ILog getLog() {
		return log;
	}
	public void setLog(ILog log) {
		this.log = log;
		yatlProcessor.setLog(log);
	}

	public String getSourceModelFileName() {
		return sourceModelFileName;
	}
	public void setSourceModelFileName(String string) {
		sourceModelFileName = string;
	}

	public String getSourceRepFileName() {
		return sourceRepFileName;
	}
	public void setSourceRepFileName(String string) {
		sourceRepFileName = string;
	}

	public String getTargetModelFileName() {
		return targetModelFileName;
	}
	public void setTargetModelFileName(String string) {
		targetModelFileName = string;
	}

	public String getTargetRepFileName() {
		return targetRepFileName;
	}
	public void setTargetRepFileName(String string) {
		targetRepFileName = string;
	}

	public String getTransfFileName() {
		return transfFileName;
	}
	public void setTransfFileName(String string) {
		transfFileName = string;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getSourceModelName() {
		return sourceModelName;
	}

	public Model getSourceModel() {
		return sourceModel;
	}
	public void setSourceModel(Model model) {
		sourceModel = model;
		sourceModelName = model.getName().getBody_();
	}

	public String getSourceRepClassName() {
		return this.sourceRepClassName;
	}
	public void setSourceRepClassName(String className) {
		this.sourceRepClassName = className;
		Repository rep = getRepository(className);
		if (rep != null)
			warehouse.registerRepository(sourceModelName, rep);
	}
	protected Repository getRepository(String className) {
		try {
			Class cls = Class.forName(className);
			if (cls != null) {
				Constructor cons = cls.getConstructor(new Class[] {}); 
				Repository repository =  (Repository)cons.newInstance(new Object[] {});
				return repository;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Repository getSourceRepository() {
		return sourceRepository;
	}
	public void setSourceRepository(Repository repository) {
		sourceRepository = repository;
		warehouse.registerRepository(sourceModelName, repository);
	}

	public String getTargetModelName() {
		return targetModelName;
	}

	public Model getTargetModel() {
		return targetModel;
	}
	public void setTargetModel(Model model) {
		targetModel = model;
		targetModelName = model.getName().getBody_();
	}

	public String getTargetRepClassName() {
		return targetRepClassName;
	}
	public void setTargetRepClassName(String string) {
		targetRepClassName = string;
	}

	public Repository getTargetRepository() {
		return targetRepository;
	}
	public void setTargetRepository(Repository repository) {
		targetRepository = repository;
		warehouse.registerRepository(targetModelName, repository);
	}

	//
	// Local Properties
	//
	protected String projectPathname;
	protected ILog log;
	protected String sourceRepClassName;
	protected String sourceRepFileName;
	protected String transfFileName;
	protected String targetRepClassName;
	protected String targetRepFileName;
	protected String sourceModelFileName;
	protected String targetModelFileName;
	protected String tab = "\t";
	
	protected Warehouse warehouse = new WarehouseImpl();
	protected String sourceModelName;
	protected Model sourceModel;
	protected String targetModelName;
	protected Model targetModel;
	protected Repository sourceRepository;
	protected Repository targetRepository;
	protected YatlProcessor yatlProcessor;
}

class ProjectHandler implements ContentHandler {
	protected Project project; 
	protected String text;
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
	}
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (localName.equals(projectTag)) {
		} else if (localName.equals(pathnameTag)) {
			project.setProjectPathName(text);
		} else if (localName.equals(transformationTag)) {
			project.setTransfFileName(text);
		} else if (localName.equals(sourceTag)) {
			project.setSourceModelFileName(model);
			project.setSourceRepClassName(repositoryClass);
			project.setSourceRepFileName(repositoryContent);
		} else if (localName.equals(targetTag)) {
			project.setTargetModelFileName(model);
			project.setTargetRepClassName(repositoryClass);
			project.setTargetRepFileName(repositoryContent);
		} else if (localName.equals(modelTag)) {
			model = text;
		} else if (localName.equals(repositoryContentTag)) {
			repositoryContent = text;
		} else if (localName.equals(repositoryClassTag)) {
			repositoryClass = text;
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

	// XML tags	
	public static final String projectTag = "project";
	public static final String pathnameTag = "pathname";
	public static final String sourceTag = "source";
	public static final String targetTag = "target";
	public static final String transformationTag = "transformation";
	public static final String modelTag = "model";
	public static final String repositoryClassTag = "repositoryClass";
	public static final String repositoryContentTag = "repositoryContext";
	protected String model;
	protected String repositoryContent;
	protected String repositoryClass;
}

