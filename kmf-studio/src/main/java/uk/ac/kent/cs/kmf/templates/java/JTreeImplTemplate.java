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
public class JTreeImplTemplate {
	public JTreeImplTemplate(Model model, Context context, PrintWriter out) {
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
		this.jtreeInterface = context.getNaming().getJTreeVisitorInterface(modelName);
		this.jtreeClass = context.getNaming().getJTreeVisitorClass(modelName);
	}

	/**
	  * Print JTreeVisitor implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(jtreeClass, out);
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("import java.util.*;");
		out.println("import java.lang.reflect.*;");
		out.println("import javax.swing.tree.*;");
		out.println();
		out.println("import uk.ac.kent.cs.kmf.util.*;");
		out.println();
		out.println("public class "+jtreeClass);
		out.println(indent+"implements "+jtreeInterface);
		out.println("{");
		// Output visit method for default factory
		if (context.isGenerateFactory()) {
			out.println(indent+"/** Visit factory for '"+context.getNaming().getFactoryInterface(modelName)+"' */");
			out.println(indent+"public Object visit("+modelPackage+"."+context.getNaming().getFactoryInterface(modelName)+" host, Object data) {");
			out.println(indent+indent+"DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);");
			out.println(indent+indent+"return root;");
			out.println(indent+"}");
		}
		// Output visit methods for each class
		Iterator i = context.getClasses().iterator();
		while (i.hasNext()) {
			Classifier cls = (Classifier)i.next();
			// Primitive types
			if (context.getNaming().isStereotype(cls, "primitive")) {
			// External types
			} else if (context.getNaming().isStereotype(cls, "external")) {
			// Stereotype enumerators
			} else if (context.getNaming().isStereotype(cls, "enumeration")) {
				out.println(indent+"/** Visit factory for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullFactoryInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);");
				out.println(indent+indent+"//--- Add all methods ---");
				out.println(indent+indent+"Class factoryClass = host.getClass();");
				out.println(indent+indent+"Method methods[] = factoryClass.getMethods();");
				out.println(indent+indent+"for(int i=0; i<methods.length; i++)");
				out.println(indent+indent+indent+"if (methods[i].getName().indexOf(\"build\")==0)");
				out.println(indent+indent+indent+indent+"root.add(new DefaultMutableTreeNode(methods[i], false));");
				out.println(indent+indent+"return root;");
				out.println(indent+"}");
				out.println(indent+"/** Visit class for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullPackageName(cls)+"."+context.getNaming().getInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"DefaultMutableTreeNode root = new DefaultMutableTreeNode(\""+context.getNaming().getFullPackageName(cls)+"."+context.getNaming().getInterfaceName(cls)+"\", false);");
				// Compare current value to each enumerator
				List ancest = context.getNaming().allSuperClasses(cls, false);
				Iterator c = ancest.iterator();
				while (c.hasNext()) {
					Classifier a_cls = (Classifier)c.next();
					String a_clsName = context.getNaming().getClassifierName(a_cls);
					Iterator j = a_cls.getFeature().iterator();
					while (j.hasNext()) {
						Feature f = (Feature)j.next();
						if (f instanceof Attribute) {
							Attribute attrib = (Attribute)f;
							String attribName = context.getNaming().getPropertyName(attrib);
							String fullAttribName = context.getNaming().getFullClassName(cls)+"."+attribName.toUpperCase();
							out.println(indent+indent+"if (host == "+fullAttribName+") root = new DefaultMutableTreeNode(\""+fullAttribName+"\", false);");
						}
					}
				}
				out.println(indent+indent+"return root;");
				out.println(indent+"}");
			// Ordinary classes
			} else {
				// Factory
				// Abstract types
				if (cls.getIsAbstract().booleanValue() || !context.isGenerateFactory()) {
				// Non-Abstract types
				} else {
					out.println(indent+"/** Visit factory for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
					out.println(indent+"public Object visit("+context.getNaming().getFullFactoryInterfaceName(cls)+" host, Object data) {");
					out.println(indent+indent+"DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);");
					out.println(indent+indent+"//--- Add all methods ---");
					out.println(indent+indent+"Class factoryClass = host.getClass();");
					out.println(indent+indent+"Method methods[] = factoryClass.getMethods();");
					out.println(indent+indent+"for(int i=0; i<methods.length; i++)");
					out.println(indent+indent+indent+"if (methods[i].getName().indexOf(\"build\")==0)");
					out.println(indent+indent+indent+indent+"root.add(new DefaultMutableTreeNode(methods[i], false));");
					out.println(indent+indent+"return root;");
					out.println(indent+"}");
				}
				// Class
				out.println(indent+"/** Visit class for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"DefaultMutableTreeNode root = new DefaultMutableTreeNode(host, true);");
				// Compute all super classes and this
				List superClasses = context.getNaming().allSuperClasses(cls, false);
				// Add properties
				Iterator j = superClasses.iterator();
				Set addedAttrib = new LinkedHashSet();
				Set addedAssoc = new LinkedHashSet();
				while (j.hasNext()) {
					Classifier superCls = (Classifier)j.next();
					String superName = context.getNaming().getClassifierName(superCls);
					out.println(indent+indent+"//--- Properties for "+superName+" ---");
					Iterator k = superCls.getFeature().iterator();
					// Add atributes from a_cls
					while (k.hasNext()) {
						Feature f = (Feature)k.next();
						if (f instanceof Attribute) {
							// Create the property
							Attribute attrib = (Attribute)f;
							String attribName = context.getNaming().getPropertyName(attrib);
							if (!addedAttrib.contains(attribName)) {
								String getter = context.getNaming().getGetter(attribName);
								out.println(indent+indent+"//--- Property "+attribName+" ---");
								out.println(indent+indent+"DefaultMutableTreeNode "+attribName+"Node = getNode(this, \""+attribName+"\", host."+getter+"(), data);");
								out.println(indent+indent+"root.add("+attribName+"Node);");
								// Add it
								addedAttrib.add(attribName);
							}
						}
					}
					// Add asociations from a_cls
					Iterator assocIt = context.getAssociations().iterator();
					while (assocIt.hasNext()) {
						Association assoc = (Association)assocIt.next();
						AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
						AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
						if (context.getNaming().add(aend1, aend2, superCls)) {
							String assocName = context.getNaming().getPropertyName(aend2);
							if (!addedAssoc.contains(assocName)) {
								String getter = context.getNaming().getGetter(assocName);
								out.println(indent+indent+"//--- Association "+assocName+" ---");
								out.println(indent+indent+"DefaultMutableTreeNode "+assocName+"Node = getNode(this, \""+assocName+"\", host."+getter+"(), data);");
								out.println(indent+indent+"root.add("+assocName+"Node);");
								// Add it
								addedAssoc.add(assocName);
							}
						}
						if (context.getNaming().add(aend2, aend1, superCls)) {
							String assocName = context.getNaming().getPropertyName(aend1);
							if (!addedAssoc.contains(assocName)) {
								String getter = context.getNaming().getGetter(assocName);
								out.println(indent+indent+"//--- Association "+assocName+" ---");
								out.println(indent+indent+"DefaultMutableTreeNode "+assocName+"Node = getNode(this, \""+assocName+"\", host."+getter+"(), data);");
								out.println(indent+indent+"root.add("+assocName+"Node);");
								// Add it
								addedAssoc.add(assocName);
							}
						}
					}
				}
				out.println(indent+indent+"return root;");
				out.println(indent+"}");
			}
		}
		// Output the auxiliary getNode
		out.println(indent+"/** Auxiliary function used by the vistors */");
		out.println(indent+"DefaultMutableTreeNode getNode("+jtreeInterface+" visitor, String name, Object obj, Object data) {");
		out.println(indent+indent+"//--- Elements visited on this branch ---");
		out.println(indent+indent+"Set visitedElements = (Set)data;");
		out.println(indent+indent+"//--- Create node ---");
		out.println(indent+indent+"DefaultMutableTreeNode root = new DefaultMutableTreeNode(name);");
		out.println(indent+indent+"//--- Construct the value ---");
		out.println(indent+indent+"//--- Primitive types ---");
		out.println(indent+indent+"if (obj == null) {");
		out.println(indent+indent+indent+"DefaultMutableTreeNode objNode = new DefaultMutableTreeNode(\"null\");");
		out.println(indent+indent+indent+"root.add(objNode);");
		out.println(indent+indent+indent+"return root;");
		out.println(indent+indent+"}");
		out.println(indent+indent+"if (Type.isInstanceofPrimitiveType(obj)) {");
		out.println(indent+indent+indent+"DefaultMutableTreeNode objNode = new DefaultMutableTreeNode(obj.toString());");
		out.println(indent+indent+indent+"root.add(objNode);");
		out.println(indent+indent+indent+"return root;");            
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- Collection types ---");
		out.println(indent+indent+"if (Type.isInstanceofCollectionType(obj)) {");
		out.println(indent+indent+indent+"Collection col = (Collection)obj;");
		out.println(indent+indent+indent+"Iterator i = col.iterator();");
		out.println(indent+indent+indent+"while (i.hasNext()) {");
		out.println(indent+indent+indent+indent+"DefaultMutableTreeNode objNode = getNode(this, \"Element\", i.next(), data);");
		out.println(indent+indent+indent+indent+"root.add((DefaultMutableTreeNode)objNode.getChildAt(0));");
		out.println(indent+indent+indent+"}");
		out.println(indent+indent+indent+"return root;");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- Enumerations types ---");
		out.println(indent+indent+"String fullClassName = obj.getClass().getName();");
		out.println(indent+indent+"if (fullClassName.endsWith(\"Enum\") || fullClassName.endsWith(\"Kind\")) {");
		out.println(indent+indent+indent+"//--- Get class ---");
		out.println(indent+indent+indent+"DefaultMutableTreeNode objNode = new DefaultMutableTreeNode(\"Error\");");
		out.println(indent+indent+indent+"try {");
		out.println(indent+indent+indent+indent+"Class objClass = obj.getClass();");
		out.println(indent+indent+indent+indent+"Class visitorClass = Class.forName(\""+context.getNaming().getFullVisitorInterface(modelName)+"\");");
		out.println(indent+indent+indent+indent+"Class dataClass = data.getClass();");
		out.println(indent+indent+indent+indent+"Method accept = objClass.getMethod(\"accept\", new Class[] {visitorClass, dataClass});");
		out.println(indent+indent+indent+indent+"objNode = (DefaultMutableTreeNode)accept.invoke(obj, new Object[] {visitor, data});");
		out.println(indent+indent+indent+"} catch (Exception e) {");
		out.println(indent+indent+indent+"}");  
		out.println(indent+indent+indent+"root.add(objNode);");
		out.println(indent+indent+indent+"return root;");
		out.println(indent+indent+"}");
		out.println(indent+indent+"//--- User types ---");
		out.println(indent+indent+"//--- Already added on the current branch: make a toSring node ---");
		out.println(indent+indent+"if (visitedElements.contains(obj)) {");
		out.println(indent+indent+indent+"DefaultMutableTreeNode objNode = new DefaultMutableTreeNode(obj, false);");
		out.println(indent+indent+indent+"root.add(objNode);");
		out.println(indent+indent+"//--- Was not visited previously on the current branch ---");
		out.println(indent+indent+"} else {");
		out.println(indent+indent+indent+"// Add it");
		out.println(indent+indent+indent+"visitedElements.add(obj);");
		out.println(indent+indent+indent+"// Visit it");
		String elementName = context.getNaming().getFullElementInterface(modelName);
		out.println(indent+indent+indent+"DefaultMutableTreeNode objNode = (DefaultMutableTreeNode)(("+elementName+")obj).accept(visitor, visitedElements);");
		out.println(indent+indent+indent+"root.add(objNode);");
		out.println(indent+indent+"}");
		out.println(indent+indent+"return root;");
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
	protected String jtreeInterface;
	protected String jtreeClass;
}
