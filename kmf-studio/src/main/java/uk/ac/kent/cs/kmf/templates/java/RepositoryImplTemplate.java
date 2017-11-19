package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.Iterator;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class RepositoryImplTemplate {
	public RepositoryImplTemplate(Model model, Context context, PrintWriter out) {
		this.model = model;
		this.context = context;
		this.out = out;
		this.log = context.getLog();
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.log = context.getLog();
		this.modelName = context.getNaming().getModelName();
		this.modelPackage = context.getNaming().getModelPackage();
		this.repositoryInterface = context.getNaming().getRepositoryInterface(modelName);
		this.repositoryClass = context.getNaming().getRepositoryClass(modelName);
		this.elementInterface = context.getNaming().getElementInterface(modelName);
		this.factoryInterface = context.getNaming().getFactoryInterface(modelName);
	}

	/**
	  * Print Repository
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(repositoryClass, out);
		// Print header
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import java.io.*;");
		out.println("import java.util.*;");
		if (context.isGenerateBrowser()) {
			out.println("import javax.swing.tree.*;");
			out.println("import uk.ac.kent.cs.kmf.xmi.*;");
		}
		out.println();
		out.println("import "+modelPackage+".*;");
		Iterator pkgIt = context.getPackages().iterator();
		out.println();
		out.println("public class "+repositoryClass);
		out.println(indent+"extends uk.ac.kent.cs.kmf.common.RepositoryImpl");
		out.println(indent+"implements "+repositoryInterface);
		out.println("{");
		out.println(indent+"/** Default repository constructor */");
		out.println(indent+"public "+repositoryClass+"() {");
		out.println(indent+indent+"setLog(new uk.ac.kent.cs.kmf.util.FileLog(\"Repository.log\"));");
		out.println(indent+indent+"init();");
		out.println(indent+"}");
		out.println(indent+"/** Repository constructor */");
		out.println(indent+"public "+repositoryClass+"(uk.ac.kent.cs.kmf.util.ILog log) {");
		out.println(indent+indent+"setLog(log);");
		out.println(indent+indent+"init();");
		out.println(indent+"}");
		// Output the initialization
		out.println(indent+"/** Init the attributes */");
		out.println(indent+"protected void init() {");
		out.println(indent+indent+"log.reportMessage(\"Init "+modelName+" repository"+"\");");
		Iterator clsIt = context.getClasses().iterator();
		out.println(indent+indent+"// Add factories");
		while (clsIt.hasNext()) {
			Classifier cls = (Classifier)clsIt.next();
			// Primitive stereotypes
			if (context.getNaming().isStereotype(cls, "primitive")) {
			// External stereotypes
			} else if (context.getNaming().isStereotype(cls, "external")) {
			// Enumeration stereotypes
			} else if (context.getNaming().isStereotype(cls, "enumeration")) {
				String key = context.getNaming().getFullClassifierName(cls);
				key = context.getNaming().removeOffset(context.getNaming().getRootOffset(), key);
				String fullFactName = context.getNaming().getFullFactoryClassName(cls);
				out.println(indent+indent+"log.reportMessage(\"Adding factory and initializing population for '"+key+"'\");");
				out.println(indent+indent+"factories.put(\""+key+"\", new "+fullFactName+"(this));");
				out.println(indent+indent+"population.put(\""+key+"\", new Vector());");
			} else {   
				String key = context.getNaming().getFullClassifierName(cls);
				key = context.getNaming().removeOffset(context.getNaming().getRootOffset(), key);
				String fullFactName = context.getNaming().getFullFactoryClassName(cls);
				out.println(indent+indent+"log.reportMessage(\"Adding factory and initializing population for '"+key+"'\");");
				if (!cls.getIsAbstract().booleanValue())
					out.println(indent+indent+"factories.put(\""+key+"\", new "+fullFactName+"(this));");
				out.println(indent+indent+"population.put(\""+key+"\", new Vector());");
			}
		}
		out.println(indent+"}");
		// Output the function that get the name of the model
		out.println();
		out.println(indent+"//--- MODEL NAME ---");
		out.println(indent+"protected String modelName = \""+modelName+"\";");
		out.println(indent+"public String getModelName() {");
		out.println(indent+indent+"return modelName;");
		out.println(indent+"}");
		// Output the function that builds an element
		out.println();
		out.println(indent+"//--- POPULATION ---");
		out.println(indent+"/** Build an element */");
		out.println(indent+"public Object buildElement(String fullClassName) {");
		out.println(indent+indent+"Object elem = null;");
		out.println(indent+indent+"try {");
		out.println(indent+indent+indent+""+context.getNaming().getFactoryInterface(modelName)+" factory = ("+context.getNaming().getFactoryInterface(modelName)+")factories.get(fullClassName);");
		out.println(indent+indent+indent+"elem = factory.build();");
		out.println(indent+indent+"} catch (Exception e) {");
		out.println(indent+indent+indent+"log.reportMessage(\"Error: Missing factory for '\"+fullClassName+\"' class \");");
		out.println(indent+indent+"}");
		out.println(indent+indent+"return elem;");
		out.println(indent+"}");
		// Output the function that adds an element
		out.println(indent+"/** Add an element */");
		out.println(indent+"public void addElement(String fullClassName, Object elem) {");
		out.println(indent+indent+"try {");
		out.println(indent+indent+indent+"((List)population.get(fullClassName)).add(elem);");
		out.println(indent+indent+"} catch (Exception e) {;");
		out.println(indent+indent+indent+"log.reportMessage(\"Error: Missing population for '\"+fullClassName+\"' class\");");
		out.println(indent+indent+"}");
		out.println(indent+"}");
		// Output the function that removes an element
		out.println(indent+"/** Remove an element */");
		out.println(indent+"public void removeElement(String fullClassName, Object elem) {");
		out.println(indent+indent+"try {");
		out.println(indent+indent+indent+"((List)population.get(fullClassName)).remove(elem);");
		out.println(indent+indent+"} catch (Exception e) {;");
		out.println(indent+indent+indent+"log.reportMessage(\"Error: Missing population for '\"+fullClassName+\"' class\");");
		out.println(indent+indent+"}");
		out.println(indent+"}");
		// Output the function that gets all the elements of a given type
		out.println(indent+"/** Get all elements of a type */");
		out.println(indent+"public List getElements(String fullClassName) {");
		out.println(indent+indent+"List objs = null;");
		out.println(indent+indent+"try {");
		out.println(indent+indent+indent+"objs = (List)population.get(fullClassName);");
		out.println(indent+indent+"} catch (Exception e) {;");
		out.println(indent+indent+indent+"log.reportMessage(\"Error: Missing population for '\"+fullClassName+\"' class\");");
		out.println(indent+indent+"}");
		out.println(indent+indent+"return objs;");
		out.println(indent+"}");
		// Output the function that gets all the instances of a given type
		out.println(indent+"/** Check if the first class name is an instance of the second class name */");
		out.println(indent+"protected boolean isInstanceOf(String elemName, String fullClassName) {");
		out.println(indent+indent+"//--- Check if fullClassName is a superinterface of elemName ---");
		out.println(indent+indent+"boolean res = false;");
		out.println(indent+indent+"try {");
		String rootOffset = context.getPackageOffset();
		if (rootOffset != null && rootOffset.length() != 0) {
			out.println(indent+indent+indent+"fullClassName = \""+rootOffset+"\"+fullClassName;");
			out.println(indent+indent+indent+"elemName = \""+rootOffset+"\"+elemName;");
		}
		out.println(indent+indent+indent+"res = Class.forName(fullClassName).isAssignableFrom(Class.forName(elemName));");
		out.println(indent+indent+"} catch (Exception e) {");
		out.println(indent+indent+"}");
		out.println(indent+indent+"return res;");
		out.println(indent+"}");
		out.println(indent+"/** Get all instances of a type */");
		out.println(indent+"public List getInstances(String fullClassName) {");
		out.println(indent+indent+"List objs = new Vector();");
		out.println(indent+indent+"Set elemNames = population.keySet();");
		out.println(indent+indent+"Iterator nameIt = elemNames.iterator();");
		out.println(indent+indent+"while (nameIt.hasNext()) {");
		out.println(indent+indent+indent+"String elemName = (String)nameIt.next();");
		out.println(indent+indent+indent+"if (isInstanceOf(elemName, fullClassName)) {");
		out.println(indent+indent+indent+indent+"List elements = (List)population.get(elemName);");
		out.println(indent+indent+indent+indent+"Iterator elemIt = elements.iterator();");
		out.println(indent+indent+indent+indent+"while (elemIt.hasNext()) {");
		out.println(indent+indent+indent+indent+indent+"Object elem = elemIt.next();");
		out.println(indent+indent+indent+indent+indent+"objs.add(elem);");
		out.println(indent+indent+indent+indent+"}");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+"}");
		out.println(indent+indent+"return objs;");
		out.println(indent+"}");
		out.println(indent+"/** Get all instances from population */");
		out.println(indent+"public List getAllElements() {");
		out.println(indent+indent+"List res = new ArrayList();");
		out.println(indent+indent+"Iterator i = population.keySet().iterator();");
		out.println(indent+indent+"while (i.hasNext()) {");
		out.println(indent+indent+indent+"String key = (String)i.next();");
		out.println(indent+indent+indent+"res.addAll((Collection)population.get(key));");
		out.println(indent+indent+"}");
		out.println(indent+indent+"return res;");
		out.println(indent+"}");
		out.println(indent+"/** Get the entire population */");
		out.println(indent+"public Map getPopulation() {");
		out.println(indent+indent+"return population;");
		out.println(indent+"}");
		// Output the function that gets a factory for a given type
		out.println();
		out.println(indent+"//--- FACTORIES ---");
		out.println(indent+"/** Get a specific factory */");
		out.println(indent+"public "+context.getNaming().getFullFactoryInterface(modelName)+" getFactory(String fullClassName) {");
		out.println(indent+indent+context.getNaming().getFullFactoryInterface(modelName)+" factory = null;");
		out.println(indent+indent+"try {");
		out.println(indent+indent+indent+"factory = ("+context.getNaming().getFullFactoryInterface(modelName)+")factories.get(fullClassName);");
		out.println(indent+indent+"} catch (Exception e) {");
		out.println(indent+indent+indent+"log.reportMessage(\"Error: Missing factory for '\"+fullClassName+\"' class\");");
		out.println(indent+indent+"}");
		out.println(indent+indent+"return factory;");
		out.println(indent+"}");
		out.println(indent+"/** Get the all factories */");
		out.println(indent+"public Map getFactories() {");
		out.println(indent+indent+"return factories;");
		out.println(indent+"}");
		// Output the functions that deals with HUTN description
		if (context.isGenerateJTreeVisitor()) {
			out.println();       
			out.println(indent+"//--- BROWSER ---");
			// Output the function that gets the JTree of the repository
			out.println(indent+"/** Get a description using a JTree */");
			out.println(indent+"public DefaultMutableTreeNode toJTree() {");
			out.println(indent+indent+"//--- Creat the JTree visitor ---");
			out.println(indent+indent+""+context.getNaming().getJTreeVisitorInterface(modelName)+" jtreeVisitor = new "+context.getNaming().getJTreeVisitorClass(modelName)+"();");
			out.println(indent+indent+"//--- Init root ---");
			out.println(indent+indent+"DefaultMutableTreeNode root = new DefaultMutableTreeNode(\"Repository\", true);");
			out.println(indent+indent+"DefaultMutableTreeNode factoriesNode = new DefaultMutableTreeNode(\"Lifecycle\", true);");
			out.println(indent+indent+"DefaultMutableTreeNode elementsNode = new DefaultMutableTreeNode(\"Elements\", true);");
			out.println(indent+indent+"root.add(factoriesNode);");
			out.println(indent+indent+"root.add(elementsNode);");
			out.println(indent+indent+"//--- Add every factory ---");
			out.println(indent+indent+"Set factoryNames = factories.keySet();");
			out.println(indent+indent+"Iterator i = factoryNames.iterator();");
			out.println(indent+indent+"while (i.hasNext()) {");
			out.println(indent+indent+indent+"String factoryName = (String)i.next();");
			out.println(indent+indent+indent+""+factoryInterface+" factory = ("+factoryInterface+")factories.get(factoryName);");
			out.println(indent+indent+indent+"DefaultMutableTreeNode factoryNode = (DefaultMutableTreeNode)factory.accept(jtreeVisitor, new Object());");
			out.println(indent+indent+indent+"factoriesNode.add(factoryNode);");
			out.println(indent+indent+"}");
			out.println(indent+indent+"//--- Add every element ---");
			out.println(indent+indent+"Set visitedElements = new LinkedHashSet();");
			out.println(indent+indent+"Set elemNames = population.keySet();");
			out.println(indent+indent+"Iterator j = elemNames.iterator();");
			out.println(indent+indent+"while (j.hasNext()) {");
			out.println(indent+indent+indent+"String elemName = (String)j.next();");
			out.println(indent+indent+indent+"//--- Add a node for each class ---");
			out.println(indent+indent+indent+"List elements = (List)population.get(elemName);");
			out.println(indent+indent+indent+"if (elements.size() != 0) {");
			out.println(indent+indent+indent+indent+"DefaultMutableTreeNode elemNode = new DefaultMutableTreeNode(\"Instances of \"+elemName, true);");
			out.println(indent+indent+indent+indent+"Iterator elementIt = elements.iterator();");
			out.println(indent+indent+indent+indent+"while (elementIt.hasNext()) {");
			out.println(indent+indent+indent+indent+indent+elementInterface+" element = ("+elementInterface+")elementIt.next();");
			out.println(indent+indent+indent+indent+indent+"//--- Add a node for each instance ---");
			out.println(indent+indent+indent+indent+indent+"visitedElements.add(element);");
			out.println(indent+indent+indent+indent+indent+"DefaultMutableTreeNode instanceNode = (DefaultMutableTreeNode)element.accept(jtreeVisitor, visitedElements);");
			out.println(indent+indent+indent+indent+indent+"elemNode.add(instanceNode);");
			out.println(indent+indent+indent+indent+"}");
			out.println(indent+indent+indent+indent+"elementsNode.add(elemNode);");
			out.println(indent+indent+indent+"}");
			out.println(indent+indent+"}");
			out.println(indent+indent+"return root;");
			out.println(indent+"}");
		}
		if (context.isGenerateXMIVisitor()) {
			// Output the function that save/load the repository into/from an XMI file
			out.println();
			out.println(indent+"/** Save the content into an XMI file */");
			out.println(indent+"public void saveXMI(String fileName) {");
			out.println(indent+indent+"//--- Create an XMI file ---");
			out.println(indent+indent+"if (!fileName.toLowerCase().endsWith(\".xml\")) fileName += \".xml\";");
			out.println(indent+indent+"XMIFile xmiFile = new XMIFile(fileName);");
			out.println(indent+indent+"//--- Create an XMI vistor ---");
			out.println(indent+indent+""+context.getNaming().getXMIVisitorInterface(modelName)+" xmiVisitor = new "+context.getNaming().getXMIVisitorClass(modelName)+"();");
			out.println(indent+indent+"//--- Create an empty (reference, xmiObject) table  ---");
			out.println(indent+indent+"Map mapRefToXMI = new IdentityHashMap();");
			out.println(indent+indent+"//--- Create the top objects container ---");
			out.println(indent+indent+"Collection topObjs = xmiFile.getTopObjects();");
			out.println(indent+indent+"//--- For each factory ---");
			out.println(indent+indent+"Set factoryNames = factories.keySet();");
			out.println(indent+indent+"Iterator i = factoryNames.iterator();");
			out.println(indent+indent+"while (i.hasNext()) {");
			out.println(indent+indent+indent+"String elemName = (String)i.next();");
			out.println(indent+indent+indent+""+context.getNaming().getFactoryInterface(modelName)+" factory = ("+context.getNaming().getFactoryInterface(modelName)+")factories.get(elemName);");
			out.println(indent+indent+indent+"//--- Create the factory ---");
			out.println(indent+indent+indent+"XMIObject xmifactory = (XMIObject)factory.accept(xmiVisitor, mapRefToXMI);");
			out.println(indent+indent+indent+"//--- Add factory to top objects ---");
//		  out.println(indent+"  topObjs.add(xmifactory);");
			out.println(indent+indent+indent+"//--- Compute all the instances created by the factory ---");
			out.println(indent+indent+indent+"List elements = (List)population.get(elemName);");
			out.println(indent+indent+indent+"if (elements == null) continue;");
			out.println(indent+indent+indent+"Iterator j = elements.iterator();");
			out.println(indent+indent+indent+"while (j.hasNext()) {");
			out.println(indent+indent+indent+indent+""+elementInterface+" element = ("+elementInterface+")j.next();");
			out.println(indent+indent+indent+indent+"//--- Create the XMI element ---");
			out.println(indent+indent+indent+indent+"XMIObject xmiElement = (XMIObject)element.accept(xmiVisitor, mapRefToXMI);");
			out.println(indent+indent+indent+indent+"//--- Add the element to top objects ---");
			out.println(indent+indent+indent+indent+"topObjs.add(xmiElement);");
			out.println(indent+indent+indent+"}");
			out.println(indent+indent+"}");
			out.println(indent+indent+"try {");
			out.println(indent+indent+indent+"//--- Register the AdapterFactory into XMI framework ---");
			out.println(indent+indent+indent+"AdapterFactoryRegister.registerAdapterFactory(new "+context.getNaming().getAdapterFactoryClass(modelName)+"(this, log));");
			out.println(indent+indent+indent+"//--- Save the elements ---");
			out.println(indent+indent+indent+"IXMIWriter writer = new XMIWriter();");
			out.println(indent+indent+indent+"writer.write(xmiFile, fileName, log);");
			out.println(indent+indent+"} catch (Exception e) {");
			out.println(indent+indent+indent+"log.reportMessage(\"\"+e);");
			out.println(indent+indent+"}");
			out.println(indent+"}");
			out.println(indent+"/** Create a new repository from an XMI file */");
			out.println(indent+"public uk.ac.kent.cs.kmf.common.Repository loadXMI(String fileName) {");
			out.println(indent+indent+"try {");
			out.println(indent+indent+indent+"//--- Create a repository ---");
			out.println(indent+indent+indent+""+context.getNaming().getRepositoryInterface(modelName)+" rep = new "+context.getNaming().getRepositoryClass(modelName)+"();");
			out.println(indent+indent+indent+"rep.setLog(log);");
			out.println(indent+indent+indent+"//--- Register the AdapterFactory into XMI framework ---");
			out.println(indent+indent+indent+"AdapterFactoryRegister.registerAdapterFactory(new "+context.getNaming().getAdapterFactoryClass(modelName)+"(rep, log));");
			out.println(indent+indent+indent+"//--- Build the elements ---");
			out.println(indent+indent+indent+"XMIFile xmiFile = (new XMIReader()).read(fileName, log);");
			out.println(indent+indent+indent+"return rep;");
			out.println(indent+indent+"} catch (Exception e) {");
			out.println(indent+indent+indent+"log.reportMessage(\"\"+e);");
			out.println(indent+indent+indent+"return null;");
			out.println(indent+indent+"}");
			out.println(indent+"}");
			out.println();
		}
		if (context.isGenerateHUTNVisitor()) {
			out.println(indent+"/** Get a HUTN description of the repository */");
			out.println(indent+"public String toHUTN() {");
			String hutnVisitorInterface = context.getNaming().getHUTNVisitorInterface(modelName);
			String hutnVisitorClass = context.getNaming().getHUTNVisitorClass(modelName);
			out.println(indent+indent+"//--- Create the HUTN visitor ---");
			out.println(indent+indent+""+hutnVisitorInterface+" hutnVisitor = new "+hutnVisitorClass+"();");
			out.println(indent+indent+"//--- Add every factory ---");
			out.println(indent+indent+"String buffer = new String();");
			out.println(indent+indent+"buffer += \""+context.getNaming().getRepositoryInterface(modelName)+" {\\n\";");
			out.println(indent+indent+"buffer += \"factories\\n\";");
			out.println(indent+indent+"Set factoryNames = factories.keySet();");
			out.println(indent+indent+"Iterator factoryIt = factoryNames.iterator();");
			out.println(indent+indent+"while (factoryIt.hasNext()) {");
			out.println(indent+indent+indent+"String factoryName = (String)factoryIt.next();");
			out.println(indent+indent+indent+"buffer += \"  \"+factoryName+\"\\n\";");
			out.println(indent+indent+"}");
			out.println(indent+indent+"buffer += \"\\n\";");
			out.println(indent+indent+"//--- Add every element ---");
			out.println(indent+indent+"buffer += \"Elements\\n\";");
			out.println(indent+indent+"Set elemNames = population.keySet();");
			out.println(indent+indent+"Iterator elemIt = elemNames.iterator();");
			out.println(indent+indent+"while (elemIt.hasNext()) {");
			out.println(indent+indent+indent+"String elemName = (String)elemIt.next();");
			out.println(indent+indent+indent+"buffer += \"Instances of \"+elemName+\"\\n\";");
			out.println(indent+indent+indent+"List elements = (List)population.get(elemName);");
			out.println(indent+indent+indent+"Iterator elementIt = elements.iterator();");
			out.println(indent+indent+indent+"while (elementIt.hasNext()) {");
			out.println(indent+indent+indent+indent+""+elementInterface+" element = ("+elementInterface+")elementIt.next();");
			out.println(indent+indent+indent+indent+"buffer += element.accept(hutnVisitor, new Object());");
			out.println(indent+indent+indent+"}");
			out.println(indent+indent+"}");
			out.println(indent+indent+"buffer += \"}\\n\";");
			out.println(indent+indent+"log.reportMessage(buffer);");
			out.println(indent+indent+"return buffer;");
			out.println(indent+"}");
			out.println(indent+"/** Save the content into a HUTN file */");
			out.println(indent+"public void saveHUTN(String fileName) {");
			out.println(indent+indent+"//--- Open the HUTN file ---");
			out.println(indent+indent+"if (!fileName.toLowerCase().endsWith(\".hutn\")) fileName += \".hutn\";");
			out.println(indent+indent+"PrintWriter out;");
			out.println(indent+indent+"try {");
			out.println(indent+indent+indent+"out = new PrintWriter(new FileWriter(new File(fileName)), true);");
			out.println(indent+indent+"} catch (Exception e) {");
			out.println(indent+indent+indent+"log.reportMessage(\"\"+e);");
			out.println(indent+indent+indent+"return;");
			out.println(indent+indent+"}");
			out.println(indent+indent+"//--- Create the HUTN visitor ---");
			out.println(indent+indent+""+hutnVisitorInterface+" hutnVisitor = new "+hutnVisitorClass+"();");
			out.println(indent+indent+"//--- Add every factory ---");
			out.println(indent+indent+"out.println(\""+context.getNaming().getRepositoryInterface(modelName)+" {\");");
			out.println(indent+indent+"out.println(\"factories\");");
			out.println(indent+indent+"Set factoryNames = factories.keySet();");
			out.println(indent+indent+"Iterator factoryIt = factoryNames.iterator();");
			out.println(indent+indent+"while (factoryIt.hasNext()) {");
			out.println(indent+indent+indent+"String factoryName = (String)factoryIt.next();");
			out.println(indent+indent+indent+"out.println(\"  \"+factoryName);");
			out.println(indent+indent+"}");
			out.println(indent+indent+"out.println();");
			out.println(indent+indent+"//--- Add every element ---");
			out.println(indent+indent+"out.println(\"Elements\");");
			out.println(indent+indent+"Set elemNames = population.keySet();");
			out.println(indent+indent+"Iterator elemIt = elemNames.iterator();");
			out.println(indent+indent+"while (elemIt.hasNext()) {");
			out.println(indent+indent+indent+"String elemName = (String)elemIt.next();");
			out.println(indent+indent+indent+"out.println(\"Instances of \"+elemName);");
			out.println(indent+indent+indent+"List elements = (List)population.get(elemName);");
			out.println(indent+indent+indent+"Iterator elementIt = elements.iterator();");
			out.println(indent+indent+indent+"while (elementIt.hasNext()) {");
			out.println(indent+indent+indent+indent+""+elementInterface+" element = ("+elementInterface+")elementIt.next();");
			out.println(indent+indent+indent+indent+"out.println(element.accept(hutnVisitor, new Object()));");
			out.println(indent+indent+indent+"}");
			out.println(indent+indent+"}");
			out.println(indent+indent+"out.println(\"}\");");        
			out.println(indent+indent+"out.close();");        
			out.println(indent+"}");
			out.println(indent+"/** Create a new repository from an HUTN file */");
			out.println(indent+"public "+context.getNaming().getRepositoryInterface(modelName)+" loadHUTN(String fileName) {");
			out.println(indent+indent+"try {");
			out.println(indent+indent+indent+"//--- Create a repository ---");
			out.println(indent+indent+indent+""+context.getNaming().getRepositoryInterface(modelName)+" rep = new "+context.getNaming().getRepositoryClass(modelName)+"();");
			out.println(indent+indent+indent+"return rep;");
			out.println(indent+indent+"} catch (Exception e) {");
			out.println(indent+indent+indent+"log.reportMessage(\"\"+e);");
			out.println(indent+indent+indent+"return null;");
			out.println(indent+indent+"}");
			out.println(indent+"}");
		}
		// Output the function that registers a factory for a given type
		out.println(indent+"/** Add a factory */");
		out.println(indent+"public void registerFactory(String fullClassName, "+context.getNaming().getFactoryInterface(modelName)+" factory) {");
		out.println(indent+indent+"if (!factories.containsKey(fullClassName)) {");
		out.println(indent+indent+indent+"log.reportMessage(\"Factory for '\"+fullClassName+\"' was replaced\");");
		out.println(indent+indent+"}");
		out.println(indent+indent+"factories.put(fullClassName, factory);");
		out.println(indent+"}");
		// Output the functions that parse/evaluate all the invariants from the repository
		if (context.isGenerateInvariant()) {
			out.println();        
			out.println(indent+"/** Parse all invariants */");
			out.println(indent+context.getNaming().getParseAllVisitorInterface(modelName)+" parseAllVis = new "+context.getNaming().getParseAllVisitorClass(modelName)+"();");
			out.println(indent+"public void parseInvariants() {");
			out.println(indent+indent+"Map data = new HashMap();");
			out.println(indent+indent+"data.put(\"log\", "+context.getNaming().getGetter("log")+"());");
			out.println(indent+indent+"//--- Parse every invariant ---");
			out.println(indent+indent+"log.reportMessage(\"Parsing invariants from repository ...\");");
			out.println(indent+indent+"Set elemNames = population.keySet();");
			out.println(indent+indent+"Iterator elemIt = elemNames.iterator();");
			out.println(indent+indent+"while (elemIt.hasNext()) {");
			out.println(indent+indent+indent+"String elemName = (String)elemIt.next();");
			out.println(indent+indent+indent+"List elements = (List)population.get(elemName);");
			out.println(indent+indent+indent+"Iterator elementIt = elements.iterator();");
			out.println(indent+indent+indent+"while (elementIt.hasNext()) {");
			out.println(indent+indent+indent+indent+""+elementInterface+" element = ("+elementInterface+")elementIt.next();");
			out.println(indent+indent+indent+indent+"element.accept(parseAllVis, data);");
			out.println(indent+indent+indent+"}");
			out.println(indent+indent+"}");
			out.println(indent+"}");
			out.println(indent+"/** Evaluate all invariants */");
			out.println(indent+context.getNaming().getEvaluateAllVisitorInterface(modelName)+" evaluateAllVis = new "+context.getNaming().getEvaluateAllVisitorClass(modelName)+"();");
			out.println(indent+"public void evaluateInvariants() {");
			out.println(indent+indent+"Map data = new HashMap();");
			out.println(indent+indent+"data.put(\"log\", "+context.getNaming().getGetter("log")+"());");
			out.println(indent+indent+"//--- Evaluate every invariant ---");
			out.println(indent+indent+"log.reportMessage(\"Evaluating invariants from repository ...\");");
			out.println(indent+indent+"Set elemNames = population.keySet();");
			out.println(indent+indent+"Iterator elemIt = elemNames.iterator();");
			out.println(indent+indent+"while (elemIt.hasNext()) {");
			out.println(indent+indent+indent+"String elemName = (String)elemIt.next();");
			out.println(indent+indent+indent+"List elements = (List)population.get(elemName);");
			out.println(indent+indent+indent+"Iterator elementIt = elements.iterator();");
			out.println(indent+indent+indent+"while (elementIt.hasNext()) {");
			out.println(indent+indent+indent+indent+""+elementInterface+" element = ("+elementInterface+")elementIt.next();");
			out.println(indent+indent+indent+indent+"element.accept(evaluateAllVis, data);");
			out.println(indent+indent+indent+"}");
			out.println(indent+indent+"}");
			out.println(indent+"}");
		}
		out.println(indent+"/** The factories */");
		out.println(indent+"protected Hashtable factories = new Hashtable();");
		out.println(indent+"/** The package offset */");
		out.println(indent+"protected String rootOffset = \""+rootOffset+"\";");
		out.println();
		out.println("}");
	}

	//
	// Local properties
	//
	protected Model model;
	protected Context context;
	protected PrintWriter out;
	
	protected ILog log;
	protected String indent;
	protected String modelName;
	protected String modelPackage;
	protected String repositoryInterface;
	protected String repositoryClass;
	protected String elementInterface;
	protected String factoryInterface;
}
