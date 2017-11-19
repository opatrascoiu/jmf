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
public class HutnImplTemplate {
	public HutnImplTemplate(Model model, Context context, PrintWriter out) {
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
		this.interfaceName = context.getNaming().getHUTNVisitorInterface(modelName);
		className = context.getNaming().getHUTNVisitorClass(modelName);
	}

	/**
	  * Print HUTNVisitor implementation
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(className, out);
		// Print the code
		out.println("package "+modelPackage+".repository;");
		out.println();
		out.println("public class "+className);
		out.println(indent+"implements "+interfaceName);
		out.println("{");
		// Output visit method for default factory
		if (context.isGenerateFactory()) {
			out.println(indent+"/** Visit factory for '"+context.getNaming().getFactoryInterface(modelName)+"' */");
			out.println(indent+"public Object visit("+modelPackage+"."+context.getNaming().getFactoryInterface(modelName)+" host, Object data) {");
			out.println(indent+indent+"String str = \""+modelPackage+"."+context.getNaming().getFactoryInterface(modelName)+"\";");
			out.println(indent+indent+"return str;");
			out.println(indent+"}");
		}
		// Output visit methods for each normal class
		Iterator i = context.getClasses().iterator();
		while (i.hasNext()) {
			Classifier cls = (Classifier)i.next();
			// Primitive types
			if (context.getNaming().isStereotype(cls, "primitive")) {
				log.reportMessage("No HUTN visitor implementation for "+cls.getName().getBody_()+" it is <<\"primitive\">>");
			// External types
			} else if (context.getNaming().isStereotype(cls, "external")) {
				log.reportMessage("No HUTN visitor implementation for "+cls.getName().getBody_()+" it is <<\"external\">>");
			// Stereotype enumerators
			} else if (context.getNaming().isStereotype(cls, "enumeration")) {
				out.println(indent+"/** Visit factory for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullFactoryInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"String str = \""+context.getNaming().getFullFactoryInterfaceName(cls)+"\";");
				out.println(indent+indent+"return str;");
				out.println(indent+"}");
				out.println(indent+"/** Visit class for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"String str = \""+context.getNaming().getFullInterfaceName(cls)+"\";");
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
							out.println(indent+indent+"if (host == "+fullAttribName+") str = \""+fullAttribName+"\";");
						}
					}
				}
				out.println(indent+indent+"return str;");
				out.println(indent+"}");
			// Ordinary classes
			} else {
				// Factory
				// Abstract types
				if (cls.getIsAbstract().booleanValue() || !context.isGenerateFactory()) {
					log.reportMessage("No HUTN visitor for "+cls.getName().getBody_()+" it is \"abstract\".");
				} else {
					out.println(indent+"/** Visit factory for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
					out.println(indent+"public Object visit("+context.getNaming().getFullFactoryInterfaceName(cls)+" host, Object data) {");
					out.println(indent+indent+"String str = \""+context.getNaming().getFullFactoryInterfaceName(cls)+"\";");
					out.println(indent+indent+"return str;");
					out.println(indent+"}");
				}
				// Class
				out.println(indent+"/** Visit class for '"+context.getNaming().getFullInterfaceName(cls)+"' */");
				out.println(indent+"public Object visit("+context.getNaming().getFullInterfaceName(cls)+" host, Object data) {");
				out.println(indent+indent+"String str = \"  \"+host.toString()+\" {\\n\";");
				// Compute all ancestors and this
				List superClasses = context.getNaming().allSuperClasses(cls, false);
				// Add properties
				Iterator j = superClasses.iterator();
				Set addedAttrib = new LinkedHashSet();
				Set addedAssoc = new LinkedHashSet();
				while (j.hasNext()) {
					Classifier superClass = (Classifier)j.next();
					String superName = context.getNaming().getClassifierName(superClass);
					out.println(indent+indent+"//--- Properties for "+superName+" ---");
					Iterator k = superClass.getFeature().iterator();
					// Add atributes from super class
					while (k.hasNext()) {
						Feature f = (Feature)k.next();
						if (f instanceof Attribute) {
							// Create the property
							Attribute attrib = (Attribute)f;
							String attribName = context.getNaming().getPropertyName(attrib);
							if (!addedAttrib.contains(attribName)) {
								String getter = context.getNaming().getGetter(attribName);
								out.println(indent+indent+"//--- Property "+attribName+" ---");
								out.println(indent+indent+"str += \"    "+attribName+"\"+\": \";");
								out.println(indent+indent+"if(host."+getter+"() == null) "+ "str += \"null\"+\"\\n\";");
								out.println(indent+indent+"else str += host."+getter+"().toString()+\"\\n\";");
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
						if (context.getNaming().add(aend1, aend2, superClass)) {
							String assocName = context.getNaming().getPropertyName(aend2);
							if (!addedAssoc.contains(assocName)) {
								String getter = context.getNaming().getGetter(assocName);
								out.println(indent+indent+"//--- Association "+assocName+" ---");
								out.println(indent+indent+"str += \"    "+assocName+"\"+\": \";");
								out.println(indent+indent+"if(host."+getter+"() == null) "+ "str += \"null\"+\"\\n\";");
								out.println(indent+indent+"else str += host."+getter+"().toString()+\"\\n\";");
								// Add it
								addedAssoc.add(assocName);
							}
						}
						if (context.getNaming().add(aend2, aend1, superClass)) {
							String assocName = context.getNaming().getPropertyName(aend1);
							if (!addedAssoc.contains(assocName)) {
								String getter = context.getNaming().getGetter(assocName);
								out.println(indent+indent+"//--- Association "+assocName+" ---");
								out.println(indent+indent+"str += \"    "+assocName+"\"+\": \";");
								out.println(indent+indent+"if(host."+getter+"() == null) "+ "str += \"null\"+\"\\n\";");
								out.println(indent+indent+"else str += host."+getter+"().toString()+\"\\n\";");
								// Add it
								addedAssoc.add(assocName);
							}
						}
					}
				}
				out.println(indent+indent+"str += \"  }\\n\";");
				out.println(indent+indent+"return str;");
				out.println(indent+"}");
			}
		}
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
