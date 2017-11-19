/*
 * Created on Feb 5, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package uk.ac.kent.cs.kmf.templates.java;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import uk.ac.kent.cs.kmf.kmfstudio.Context;
import uk.ac.kent.cs.kmf.kmfstudio.generators.ClassGenerator;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;

/**
 * @author Octavian Patrascoiu
 *
 */
public class ClassInterfTemplate {
	public ClassInterfTemplate(Classifier cls, Context context, PrintWriter out) {
		this.cls = cls;
		this.context = context;
		this.out = out;
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.log = context.getLog();
		this.indent = context.getIndent();
		this.modelName = context.getNaming().getModelName();
		this.fullPkgName = context.getNaming().getFullPackageName(cls);
		this.interfaceName = context.getNaming().getInterfaceName(cls);
	}

	/**
	  * Print Interface
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(interfaceName, out);
		// Print the Java code
		// Enumeration
		if (cls instanceof Enumeration_ || context.getNaming().isStereotype(cls, "enumeration")) {
			// Add package/import
			out.println("package "+fullPkgName+";");
			out.println();
			// Compute extended interfaces
			List interfaces = new Vector();
			interfaces.add(context.getNaming().getFullElementInterface(modelName));
			// Compute interface header
			String header = "public interface "+interfaceName+"\n";
			if (interfaces.size() != 0) {
				Iterator j = interfaces.iterator();
				header += "extends\n"+indent+j.next();
				while (j.hasNext()) header += ",\n"+indent+j.next();
			}
			header += "\n{";
			// Add header
			out.println(header);        	
			// Add identifier
			if (context.isGenerateID()) {
				out.println();
				out.println(indent+"/** Get the id */");
				out.println(indent+"public String getId();");
				out.println(indent+"/** Set the id */");
				out.println(indent+"public void setId(String id);");
			}
			// Add toString
			out.println();
			out.println(indent+"/** Override the toString */");
			out.println(indent+"public String toString();");
			// Add delete
			if (context.isGenerateBidirectional()) {
				out.println();
				out.println(indent+"/** Delete the object */");
				out.println(indent+"public void delete();");
			}
			// Add clone	
			out.println();
			out.println(indent+"/** Clone the object */");
			out.println(indent+"public Object clone();");
			out.println("}");
		// Normal class
		} else {
			// Add package/import
			out.println("package "+fullPkgName+";");
			out.println();
			// Compute extended interfaces
			List interfaces = new Vector();
			interfaces.add(context.getNaming().getFullElementInterface(modelName));
			List superClasses = context.getNaming().allSuperClasses(cls, true);
			superClasses.remove(cls);
			Iterator i = superClasses.iterator();
			while (i.hasNext()) {
				Classifier superCls = (Classifier)i.next();
				String name = context.getNaming().inSamePackage(superCls, cls) ? context.getNaming().getInterfaceName(superCls) : context.getNaming().getFullInterfaceName(superCls);
				if (!interfaces.contains(name)) interfaces.add(name);
				else log.reportWarning("Multiple inheritance of '"+context.getNaming().getInterfaceName(superCls)+"' in '"+interfaceName+"'");
			}
			// Compute interface header
			String header = "public interface "+interfaceName+"\n";
			if (interfaces.size() != 0) {
				Iterator j = interfaces.iterator();
				header += "extends\n    "+j.next();
				while (j.hasNext()) header += ",\n    "+j.next();
			}
			header += "\n{";
			// Add header
			out.println(header);        	
			// Add properties
			outputPropertiesToInterface(superClasses, out);
			// Add identifier
			if (context.isGenerateID()) {
				out.println();
				out.println(indent+"/** Get the id */");
				out.println(indent+"public String getId();");
				out.println(indent+"/** Set the id */");
				out.println(indent+"public void setId(String id);");
			}
			// Add toString
			out.println();
			out.println(indent+"/** Override the toString */");
			out.println(indent+"public String toString();");
			// Add delete
			if (context.isGenerateBidirectional()) {
				out.println();
				out.println(indent+"/** Delete the object */");
				out.println(indent+"public void delete();");
			}
			// Add clone
			out.println();
			out.println(indent+"/** Clone the object */");
			out.println(indent+"public Object clone();");
			out.println("}");
		}
	}

	/** Print the Attributes, Associations, and Operations to interface */
	protected void outputPropertiesToInterface(List ancest, PrintWriter out) {
		Set addedProperties = context.getNaming().getAttributeNames(ancest);
		addedProperties.addAll(context.getNaming().getAssociationNames(ancest, context.getAssociations()));
		Iterator i = cls.getFeature().iterator();
		while (i.hasNext()) {
			Feature f = (Feature)i.next();
			if (f instanceof Attribute) {
				Attribute attrib = (Attribute)f;
				String attribName = context.getNaming().getPropertyName(attrib);
				if (!addedProperties.contains(attribName)) {
					out.println();
					out.println(indent+"/** Get the '"+attribName+"' from '"+interfaceName+"' */");
					out.println(ClassGenerator.getPropertyAccessorSignature(context, attrib, (Classifier)attrib.getType(), cls)+";");
					out.println(indent+"/** Set the '"+attribName+"' from '"+interfaceName+"' */");
					out.println(ClassGenerator.getPropertyMutatorSignature(context, attrib, (Classifier)attrib.getType(), cls)+";");
					addedProperties.add(attribName);
				} else {
					log.reportWarning("Property '"+attribName+"' is already defined in interface '"+context.getNaming().getClassifierName(cls)+"'");
				}
			}
		}
		Iterator j = context.getAssociations().iterator();
		while (j.hasNext()) {
			Association assoc = (Association)j.next();
			AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
			AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
			if (context.getNaming().add(aend1, aend2, cls)) {
				String assocName = context.getNaming().getPropertyName(aend2);
				if (!addedProperties.contains(assocName)) {
					out.println();
					out.println(indent+"/** Get the '"+assocName+"' from '"+interfaceName+"' */");
					out.println(ClassGenerator.getPropertyAccessorSignature(context, aend2, (Classifier)aend2.getType(), (Classifier)cls)+";");
//					if (!isCollection(aend2) && !isComposite(aend2)) {
						out.println(indent+"/** Set the '"+assocName+"' from '"+interfaceName+"' */");
						out.println(ClassGenerator.getPropertyMutatorSignature(context, aend2, (Classifier)aend2.getType(), (Classifier)cls)+";");
//					}
					addedProperties.add(assocName);
				} else {
					log.reportWarning("Property '"+assocName+"' is already defined in interface '"+context.getNaming().getClassifierName(cls)+"'");
				}
			}
			if (context.getNaming().add(aend2, aend1, cls)) {
				String assocName = context.getNaming().getPropertyName(aend1);
				if (!addedProperties.contains(assocName)) {
					out.println();
					out.println(indent+"/** Get the '"+assocName+"' from '"+interfaceName+"' */");
					out.println(ClassGenerator.getPropertyAccessorSignature(context, aend1, (Classifier)aend1.getType(), (Classifier)cls)+";");
//					if (!isCollection(aend1)  && !isComposite(aend1)) {
						out.println(indent+"/** Set the '"+assocName+"' from '"+interfaceName+"' */");
						out.println(ClassGenerator.getPropertyMutatorSignature(context, aend1, (Classifier)aend1.getType(), (Classifier)cls)+";");
//					}
					addedProperties.add(assocName);
				} else {
					log.reportWarning("Property '"+assocName+"' is already defined in the interface of '"+context.getNaming().getClassifierName(cls)+"'");
				}
			}
		}
		Iterator k = cls.getFeature().iterator();
		Set addedOperations = new LinkedHashSet();
		while (k.hasNext()) {
			Feature f = (Feature)k.next();
			if (f instanceof Operation) {
				Operation op = (Operation)f;
				String opName = context.getNaming().getOperationName(op);
				String opKey = ClassGenerator.getOperationSignature(context, op, (Classifier)op.getOwner(), cls, true);
				if (!addedOperations.contains(opKey)) {
					out.println();
					out.println(indent+"/** Operation  '"+opName+"' from '"+interfaceName+"' */");
					out.println(ClassGenerator.getOperationDeclaration(context, op, cls, cls));
					addedOperations.add(opKey);
				} else {
					log.reportWarning("Operation '"+opName+"' is already defined in interface '"+context.getNaming().getClassifierName(cls)+"'");
				}
			}
		}
	}
	
	//
	// Local properties
	//
	protected Classifier cls;
	protected Context context;
	protected PrintWriter out;
	
	protected ILog log;
	protected String indent;
	protected String interfaceName;
	protected String modelName;
	protected String fullPkgName;
}
