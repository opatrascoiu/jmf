package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;

/**
 * @author Octavian Patrascoiu
 *
 */
public class XmiImplTemplate {
	public XmiImplTemplate(Model model, Context context, PrintWriter out) {
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
		this.interfaceName = context.getNaming().getXMIVisitorInterface(modelName);
		this.className = context.getNaming().getXMIVisitorClass(modelName);
	}

	/**
	  * Print XMIVisitor implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(className, out);
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import java.util.*;");
		out.println();
		out.println("import uk.ac.kent.cs.kmf.xmi.*;");
		out.println("import uk.ac.kent.cs.kmf.util.*;");
		out.println();
		out.println("import "+modelPackage+".*;");
		out.println();
		out.println("public class "+className);
		out.println(indent+"implements "+interfaceName);
		out.println("{");
		// Output visit method for default factory
		if (context.isGenerateFactory()) {
			String fullFactName = modelPackage+"."+context.getNaming().getFactoryInterface(modelName); 
			String key = fullFactName;
			key = context.getNaming().removeOffset(context.getNaming().getRootOffset(), key);
			out.println(indent+"/** Visit factory for '"+key+"' */");
			out.println(indent+"public Object visit("+fullFactName+" host, Object data) {");
			out.println(indent+indent+"IXMIObject xmiObject = new XMIObject(\""+key+"\", host);");
			out.println(indent+indent+"return xmiObject;");
			out.println(indent+"}");
		}
		// Output visit methods for each class
		Iterator i = context.getClasses().iterator();
		while (i.hasNext()) {
			Classifier cls = (Classifier)i.next();
			String fullFactName = context.getNaming().getFullFactoryInterfaceName(cls); 
			String factKey = fullFactName;
			factKey = context.getNaming().removeOffset(context.getNaming().getRootOffset(), factKey);
			String fullClassName = context.getNaming().getFullInterfaceName(cls); 
			String classKey = fullClassName;
			classKey = context.getNaming().removeOffset(context.getNaming().getRootOffset(), classKey);
			// Primitive types
			if (context.getNaming().isStereotype(cls, "primitive")) {
				log.reportMessage("No XMI visitor implementation for "+cls.getName().getBody_()+" it is <<\"primitive\">>");
			// External types
			} else if (context.getNaming().isStereotype(cls, "external")) {
				log.reportMessage("No XMI visitor implementation for "+cls.getName().getBody_()+" it is <<\"external\">>");
			// Stereotype enumerators
			} else if (context.getNaming().isStereotype(cls, "enumeration")) {
				out.println(indent+"/** Visit factory for '"+factKey+"' */");
				out.println(indent+"public Object visit("+fullFactName+" host, Object data) {");
				out.println(indent+indent+"IXMIObject xmiObject = new XMIObject(\""+factKey+"\", host);");
				out.println(indent+indent+"return xmiObject;");
				out.println(indent+"}");
				// Class
				out.println(indent+"/** Visit class for '"+classKey+"' */");
				out.println(indent+"public Object visit("+fullClassName+" host, Object data) {");
				out.println(indent+indent+"if (((Map)data).containsKey(host)) return ((Map)data).get(host);");
				out.println(indent+indent+"IXMIObject xmiObject = new XMIObject(\""+classKey+"\", host);");
				// Compare current value to each enumerator
				List superClasses = context.getNaming().allSuperClasses(cls, false);
				Iterator j = superClasses.iterator();
				while (j.hasNext()) {
					Classifier superClass = (Classifier)j.next();
					String superName = context.getNaming().getClassifierName(superClass);
					Iterator k = superClass.getFeature().iterator();
					while (k.hasNext()) {
						Feature f = (Feature)k.next();
						if (f instanceof Attribute) {
							Attribute attrib = (Attribute)f;
							String attribName = context.getNaming().getPropertyName(attrib);
							String fullAttribName = context.getNaming().getFullClassName(cls)+"."+attribName.toUpperCase();
							out.println(indent+indent+"if (host == "+fullAttribName+") xmiObject = new XMIObject(\""+fullAttribName+"\", host);");
						}
					}
				}
				out.println(indent+indent+"//--- Add (host, xmiObject) to data ---");
				out.println(indent+indent+"((Map)data).put(host, xmiObject);");
				out.println(indent+indent+"return xmiObject;");
				out.println(indent+"}");
			// Ordinary classes
			} else {
				// Factory
				// Abstract types
				if (cls.getIsAbstract().booleanValue() || !context.isGenerateFactory()) {
					log.reportMessage("No XMI visitor for "+cls.getName().getBody_()+" it is \"abstract\".");
				// Non-Abstract types
				} else {
					out.println(indent+"/** Visit factory for '"+factKey+"' */");
					out.println(indent+"public Object visit("+fullFactName+" host, Object data) {");
					out.println(indent+indent+"IXMIObject xmiObject = new XMIObject(\""+factKey+"\", host);");
					out.println(indent+indent+"return xmiObject;");
					out.println(indent+"}");
				}
				// Class
				out.println(indent+"/** Visit class for '"+classKey+"' */");
				out.println(indent+"public Object visit("+fullClassName+" host, Object data) {");
				out.println(indent+indent+"if (((Map)data).containsKey(host)) return ((Map)data).get(host);");
				out.println(indent+indent+"IXMIObject xmiObject = new XMIObject(\""+classKey+"\", host);");
				out.println(indent+indent+"//--- Add (host, xmiObject) to data ---");
				out.println(indent+indent+"((Map)data).put(host, xmiObject);");
				// Compute all ancestors and this
				List ancest = context.getNaming().allSuperClasses(cls, false);
				// Add attributes
				Iterator c = ancest.iterator();
				Set addedAttrib = new LinkedHashSet();
				Set addedAssoc = new LinkedHashSet();
				while (c.hasNext()) {
					Classifier superCls = (Classifier)c.next();
					String superClsName = context.getNaming().getClassifierName(superCls);
					out.println(indent+indent+"//--- Properties for "+superClsName+" ---");
					Iterator j = superCls.getFeature().iterator();
					while (j.hasNext()) {
						Feature f = (Feature)j.next();
						if (f instanceof Attribute) {
							// Create the property
							Attribute attrib = (Attribute)f;
							String attribName = context.getNaming().getPropertyName(attrib);
							String fullPropTypeName = context.getNaming().getFullInterfaceName(superCls)+"."+attribName; 
							String propKey = fullPropTypeName;
							propKey = context.getNaming().removeOffset(context.getNaming().getRootOffset(), propKey);
							if (!addedAttrib.contains(attribName)) {
								String getter = context.getNaming().getGetter(attribName);
								out.println(indent+indent+"//--- Attribute "+propKey+" ---");
								out.println(indent+indent+"IXMIProperty "+attribName+"IXMIProperty = getProperty(\""+propKey+"\", host."+getter+"(), (Map)data);");
								out.println(indent+indent+"xmiObject.add("+attribName+"IXMIProperty);");
								addedAttrib.add(attribName);
							}
						}
					}
					Iterator assocIt = context.getAssociations().iterator();
					while (assocIt.hasNext()) {
						Association assoc = (Association)assocIt.next();
						AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
						AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
						if (context.getNaming().add(aend1, aend2, superCls)) {
							String assocName = context.getNaming().getPropertyName(aend2);
							String fullPropTypeName = context.getNaming().getFullInterfaceName(superCls)+"."+assocName; 
							String propKey = fullPropTypeName;
							propKey = context.getNaming().removeOffset(context.getNaming().getRootOffset(), propKey);
							if (!addedAssoc.contains(assocName)) {
								String getter = context.getNaming().getGetter(assocName);
								out.println(indent+indent+"//--- Association "+propKey+" ---");
								out.println(indent+indent+"IXMIProperty "+assocName+"IXMIProperty = getProperty(\""+propKey+"\", host."+getter+"(), (Map)data);");
								out.println(indent+indent+"xmiObject.add("+assocName+"IXMIProperty);");
								addedAssoc.add(assocName);
							}
						}
						if (context.getNaming().add(aend2, aend1, superCls)) {
							String assocName = context.getNaming().getPropertyName(aend1);
							String fullPropTypeName = context.getNaming().getFullInterfaceName(superCls)+"."+assocName; 
							String propKey = fullPropTypeName;
							propKey = context.getNaming().removeOffset(context.getNaming().getRootOffset(), propKey);
							if (!addedAssoc.contains(assocName)) {
								String getter = context.getNaming().getGetter(assocName);
								out.println(indent+indent+"//--- Association "+propKey+" ---");
								out.println(indent+indent+"IXMIProperty "+assocName+"IXMIProperty = getProperty(\""+propKey+"\", host."+getter+"(), (Map)data);");
								out.println(indent+indent+"xmiObject.add("+assocName+"IXMIProperty);");
								addedAssoc.add(assocName);
							}
						}
					}
				}
				out.println(indent+indent+"return xmiObject;");
				out.println(indent+"}");
			}
		}
		// Output the auxiliary getProperty
		out.println(indent+"/** Auxiliary function */");
		out.println(indent+"IXMIProperty getProperty(String name, Object obj, Map data) {");
		out.println(indent+indent+"//--- Create property ---");
		out.println(indent+indent+"IXMIProperty prop = new XMIProperty(name);");
		out.println(indent+indent+"//--- Compute property type ---");
		out.println(indent+indent+"prop.setXMIType(XMIProperty.OBJECT);");
		out.println(indent+indent+"if (Type.isInstanceofPrimitiveType(obj)) {");
		out.println(indent+indent+indent+"prop.setXMIType(XMIProperty.BASIC);");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- Collection types ---");
		out.println(indent+indent+"if (Type.isInstanceofCollectionType(obj)) {");
		out.println(indent+indent+indent+"prop.setXMIType(XMIProperty.COLLECTION);");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- Check if obj is null ---");
		out.println(indent+indent+"if (obj == null) {");
		out.println(indent+indent+indent+"prop.setXMIValue(null);");
		out.println(indent+indent+indent+"return prop;");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- Check if obj is already in data ---");
		out.println(indent+indent+"if (data.containsKey(obj)) {");
		out.println(indent+indent+indent+"prop.setXMIValue(data.get(obj));");
		out.println(indent+indent+indent+"return prop;");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- Construct the value: an XMI object ---");
		out.println(indent+indent+"//--- Basic types ---");
		out.println(indent+indent+"if (Type.isInstanceofPrimitiveType(obj)) {");
		out.println(indent+indent+indent+"prop.setXMIValue(obj.toString());");
		out.println(indent+indent+indent+"return prop;");            
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- Collection types ---");
		out.println(indent+indent+"if (Type.isInstanceofCollectionType(obj)) {");
		out.println(indent+indent+indent+"Collection col = (Collection)obj;");
		out.println(indent+indent+indent+"Iterator i = col.iterator();");
		out.println(indent+indent+indent+"while (i.hasNext()) {");
		out.println(indent+indent+indent+indent+"Object value = i.next();");
		out.println(indent+indent+indent+indent+"if (Type.isInstanceofPrimitiveType(value)) {");
		out.println(indent+indent+indent+indent+indent+"IXMIObject xmiElem = new XMIObject(\"BASIC\", value);");
		out.println(indent+indent+indent+indent+indent+"xmiElem.setLABEL(value.toString());");
		out.println(indent+indent+indent+indent+indent+"if (value instanceof java.lang.Boolean) xmiElem = new XMIObject(\"Boolean\", value);");
		out.println(indent+indent+indent+indent+indent+"else if (value instanceof java.lang.Integer) xmiElem = new XMIObject(\"Integer\", value);");
		out.println(indent+indent+indent+indent+indent+"else if (value instanceof java.lang.Double) xmiElem = new XMIObject(\"Double\", value);");
		out.println(indent+indent+indent+indent+indent+"else if (value instanceof java.lang.String) xmiElem = new XMIObject(\"String\", value);");
		out.println(indent+indent+indent+indent+indent+"//--- Link collection to elem ---");
		out.println(indent+indent+indent+indent+indent+"prop.addXMIValue(xmiElem);");
		String elementInterface = context.getNaming().getElementInterface(modelName);
		out.println(indent+indent+indent+indent+"} else if (value instanceof "+elementInterface+") {");
		out.println(indent+indent+indent+indent+indent+"IXMIObject xmiElem = (IXMIObject)(("+elementInterface+")value).accept(this, data);");
		out.println(indent+indent+indent+indent+indent+"//--- Link collection to elem ---");
		out.println(indent+indent+indent+indent+indent+"prop.addXMIValue(xmiElem);");
		out.println(indent+indent+indent+indent+"}");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+indent+"return prop;");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- User types ---");
		out.println(indent+indent+"prop.setXMIValue((("+context.getNaming().getElementInterface(modelName)+")obj).accept(this, data));");
		out.println(indent+indent+"return prop;");
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
	protected String interfaceName;
	protected String className;
}
