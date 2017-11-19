package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class ReaderAdapterImplTemplate {
	public ReaderAdapterImplTemplate(Model model, Context context, PrintWriter out) {
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
		this.adapterFactoryClass = context.getNaming().getAdapterFactoryClass(modelName);
		this.readerAdapterClass = context.getNaming().getReaderAdapterClass(modelName);
	}

	/**
	  * Print ReaderAdapter implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(readerAdapterClass, out);
		String repositoryInterface = context.getNaming().getFullRepositoryInterface(modelName);
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import java.util.*;");
		out.println("import java.lang.reflect.*;");
		out.println();
		out.println("import uk.ac.kent.cs.kmf.xmi.*;");
		out.println("import uk.ac.kent.cs.kmf.util.*;");
		out.println();
		out.println("/**");
		out.println("  * This class represents a reader modelImplAdapter that can be used to load XMI files into the repository");
		out.println("  */");
		out.println("public class "+readerAdapterClass);
		out.println(indent+"extends ReaderAdapter");
		out.println(indent+"implements IReaderAdapter");
		out.println("{");
		out.println(indent+"/** The repository */");
		out.println(indent+""+repositoryInterface+" rep;");
		out.println(indent+"/** The log */");
		out.println(indent+"ILog log;");
		out.println();
		out.println(indent+"/** Abstract factory */");
		out.println(indent+"public "+readerAdapterClass+"("+repositoryInterface+" rep, ILog log) {");
		out.println(indent+indent+"this.rep = rep;");
		out.println(indent+indent+"this.log = log;");
		out.println(indent+"}");
		out.println();
		out.println(indent+"/** ");
		out.println(indent+"  * This method is called everytime a CreateObject event appears during ");
		out.println(indent+"  * the XMI file parsing");
		out.println(indent+"  */");
		out.println(indent+"public java.lang.Object createObject(ObjectInfo info) {");
		out.println(indent+indent+"// References to other objects is the XMI framework responsability");
		out.println(indent+indent+"if (info.getIdref() != null) return null;");
		out.println(indent+indent+"// Use the repository to create object");
		out.println(indent+indent+"else {");
		out.println(indent+indent+indent+"// Get a clean xmiName");
		out.println(indent+indent+indent+"String objName = Naming.getCleanName(info.getXMIName());");
		out.println(indent+indent+indent+"if (objName.equals(\"Collection_\")) return new Vector();");
		out.println(indent+indent+indent+"if (objName.equals(\"Set_\")) return new HashSet();");
		out.println(indent+indent+indent+"if (objName.equals(\"List_\")) return new Vector();");
		out.println(indent+indent+indent+"if (objName.equals(\"Boolean_\")) return new Boolean(info.getLabel());");
		out.println(indent+indent+indent+"if (objName.equals(\"Integer_\")) return new Integer(info.getLabel());");
		out.println(indent+indent+indent+"if (objName.equals(\"Double_\")) return new Double(info.getLabel());");
		out.println(indent+indent+indent+"if (objName.equals(\"String_\")) return new String(info.getLabel());");
		out.println(indent+indent+indent+"Object obj = rep.buildElement(objName);");
		out.println(indent+indent+indent+"// Debug info");
		out.println(indent+indent+indent+"if (obj == null) {");
		out.println(indent+indent+indent+indent+"log.reportMessage(\"Cannot create an instance of '\"+objName+\"'\");");
		out.println(indent+indent+indent+"} else {");
		out.println("//              log.reportMessage(\"Create an instance of '\"+objName+\"'\");");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+indent+"return obj;");
		out.println(indent+indent+"}");
		out.println(indent+"}");
		out.println();
		out.println(indent+"/** ");
		out.println(indent+"  * This method is called everytime a CreateProperty event appears");
		out.println(indent+"  * during XMI parsing");
		out.println(indent+"  */");
		out.println(indent+"public java.lang.Object createProperty(FeatureInfo info) {");
		out.println(indent+indent+"// Get a clean xmiName");
		out.println(indent+indent+"String propertyName = Naming.getCleanName(info.getXMIName());");
		out.println(indent+indent+"if (propertyName.indexOf(\".\")!=-1)"); 
		out.println(indent+indent+"	propertyName = propertyName.substring(propertyName.lastIndexOf(\".\")+1, propertyName.length());");
		out.println(indent+indent+"// Set the property");
		out.println(indent+indent+"if (info.getValue() == null) return propertyName;");
		out.println(indent+indent+"String message = \"Create property '\"+propertyName+\"' into '\"+info.getObject()+\"' with value '\"+info.getValue()+\"'\";");
		out.println("//        log.reportMessage(message);");
		out.println(indent+indent+"setPropertyValue(info.getObject(), propertyName, info.getValue());");
		out.println(indent+indent+"return null;");
		out.println(indent+"}");
		out.println();
		out.println(indent+"/** ");
		out.println(indent+"  * This method is called everytime a ResolveValue event appears");
		out.println(indent+"  * during XMI parsing");
		out.println(indent+"  */");
		out.println(indent+"public void resolveValue(java.lang.Object object, java.lang.Object property, java.lang.Object value) {");
		out.println(indent+indent+"// Set property");
		out.println(indent+indent+"String message = \"Resolve property '\"+property+\"' from '\"+object+\"' to '\"+value+\"'\";");
		out.println("//        log.reportMessage(message);");
		out.println(indent+indent+"setPropertyValue(object, (String)property, value);");
		out.println(indent+"}");
		out.println();
		out.println(indent+"/** ");
		out.println(indent+"  * This method is called everytime a SetProperty event appears");
		out.println(indent+"  * during XMI parsing");
		out.println(indent+"  */");
		out.println(indent+"public void setPropertyValue(Object object, String name, Object value) {");
		// Comnpute the property's name
		out.println(indent+indent+"String propertyName = name;");
		out.println(indent+indent+"if (propertyName.indexOf(\".\")!=-1)"); 
		out.println(indent+indent+"	propertyName = propertyName.substring(propertyName.lastIndexOf(\".\")+1, propertyName.length());");
		out.println(indent+indent+"// Get the object's method corresponding to property");
		out.println(indent+indent+"// Get also return type");
		out.println(indent+indent+"String getterName = Naming.getGetter(propertyName);");
		out.println(indent+indent+"Class type = null;");
		out.println(indent+indent+"Method getterMethod = null;");
		out.println(indent+indent+"try {");
		out.println(indent+indent+indent+"getterMethod  = object.getClass().getMethod(getterName, new Class[] {});");
		out.println(indent+indent+indent+"type = getterMethod.getReturnType();");
		out.println(indent+indent+"} catch (Exception e) {");
		out.println(indent+indent+indent+"log.reportMessage(\"Cannot find property '\"+propertyName+\"'\");");
		out.println(indent+indent+indent+"log.reportMessage(e.toString());");
		out.println(indent+indent+"}");
		out.println(indent+indent+"// Set the property to the specific value");
		out.println(indent+indent+"String setterName = Naming.getSetter(propertyName);");
		out.println(indent+indent+"try {");
		out.println(indent+indent+indent+"// Get the set method");
		out.println(indent+indent+indent+"Method setterMethod = object.getClass().getMethod(setterName, new Class[] {type});");
		out.println(indent+indent+indent+"// Return type is Boolean");
		out.println(indent+indent+indent+"if (type == Boolean.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{Boolean.valueOf((String)value)});");
		out.println(indent+indent+indent+"// Return type is Byte");
		out.println(indent+indent+indent+"} else if (type == Byte.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{Byte.valueOf((String)value)});");
		out.println(indent+indent+indent+"// Return type is Character");
		out.println(indent+indent+indent+"} else if (type == Character.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{new Character(((String)value).charAt(0))});");
		out.println(indent+indent+indent+"// Return type is Double");
		out.println(indent+indent+indent+"} else if (type == Double.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{Double.valueOf((String)value)});");
		out.println(indent+indent+indent+"// Return type is Float");
		out.println(indent+indent+indent+"} else if (type == Float.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{Float.valueOf((String)value)});");
		out.println(indent+indent+indent+"// Return type is Integer");
		out.println(indent+indent+indent+"} else if (type == Integer.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{Integer.valueOf((String)value)});");
		out.println(indent+indent+indent+"// Return type is Long");
		out.println(indent+indent+indent+"} else if (type == Long.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{Long.valueOf((String)value)});");
		out.println(indent+indent+indent+"// Return type is Short");
		out.println(indent+indent+indent+"} else if (type == Short.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{Short.valueOf((String)value)});");
		out.println(indent+indent+indent+"// Return type is a String");
		out.println(indent+indent+indent+"} else if (type == String.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{new String((String)value)});");
		out.println(indent+indent+indent+"// Return type is a StringBuffer");
		out.println(indent+indent+indent+"} else if (type == StringBuffer.class) {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{new String((String)value)});");
		out.println(indent+indent+indent+"// Collection");
		out.println(indent+indent+indent+"} else if (Collection.class.isAssignableFrom(type)) {");
		out.println(indent+indent+indent+indent+"((Collection)getterMethod.invoke(object, new Object[]{})).add(value);");
		out.println(indent+indent+indent+"// Other return type");
		out.println(indent+indent+indent+"} else {");
		out.println(indent+indent+indent+indent+"setterMethod.invoke(object, new Object[]{value});");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+"} catch (Exception e) {");
		out.println(indent+indent+indent+"String message = \"Cannot invoke mutator '\"+getterName+\"(\"+type+\")'\\n\";");
		out.println(indent+indent+indent+"message += \"this = \"+object+\"\\n\";");
		out.println(indent+indent+indent+"message += \"argument = '\"+value.getClass()+\"(\"+value+\")\";");
		out.println(indent+indent+indent+"log.reportMessage(message);");
		out.println(indent+indent+indent+"log.reportMessage(e.toString());");
		out.println(indent+indent+"}");
		out.println(indent+"}");
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
	protected String adapterFactoryClass;
	protected String readerAdapterClass;
}
