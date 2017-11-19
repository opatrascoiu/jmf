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
import uk.ac.kent.cs.kmf.util.Type;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Feature;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Generalization;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation;

/**
 * @author Octavian Patrascoiu
 *
 */
public class ClassImplTemplate {
	public ClassImplTemplate(Classifier cls, Context context, PrintWriter out) {
		this.cls = cls;
		this.context = context;
		this.out = out;
		this.log = context.getLog();
		initLocalProperties();
	}
	
	protected void initLocalProperties() {
		this.indent = context.getIndent();
		this.log = context.getLog();
		this.modelName = context.getNaming().getModelName();
		this.fullPkgName = context.getNaming().getFullPackageName(cls);
		this.interfaceName = context.getNaming().getInterfaceName(cls);
		this.className = context.getNaming().getClassName(cls);
	}

	/** 
	  * Print Implemention 
	  */
	public void generate() {
		// Print generation stamp
		context.getNaming().putStamp(className, out);
		// Enumeration
		if (cls instanceof Enumeration_ || context.getNaming().isStereotype(cls, "enumeration")) {
			outputEnumerationImplementation(out);
		// Normal type
		} else {
			// Add package/import
			out.println("package "+fullPkgName+";");
			out.println();
			// Compute all superclasess and this
			List superClasses = context.getNaming().allSuperClasses(cls, false);
			// Compute interfaces
			List interfaces = new Vector();
			interfaces.add(interfaceName);
			if (context.isGenerateVisitor()) {
				interfaces.add(context.getNaming().getFullVisitableInterface(modelName));
			}
			// Compute header
			String header = "public class "+className+"\n";
			// Simple inheritance
			if (cls.getGeneralization().size()==1) {
				Generalization gen = (Generalization)cls.getGeneralization().iterator().next();
				Classifier superCls = (Classifier)gen.getParent();
				if (!superCls.getIsAbstract().booleanValue()) {
					String parentName = context.getNaming().inSamePackage(superCls, cls) ? context.getNaming().getClassName(superCls) : context.getNaming().getFullClassName(superCls);
					header += "extends\n"+indent+parentName+"\n";
				}
			}	
			if (interfaces.size() != 0) {
				Iterator j = interfaces.iterator();
				header += "implements\n"+indent+j.next();
				while (j.hasNext()) header += ",\n    "+j.next();
			}
			header += "\n{";
			// Add header
			out.println(header);
			// Add constructors
			outputConstructorsToImplementation(superClasses, out);
			// Add properties
			out.println();
			outputPropertiesToImplementation(superClasses, out);
			// Add identifier
			if (context.isGenerateID()) {
				out.println();
				out.println(indent+"/** The id */");
				out.println(indent+"protected String id;");
				out.println(indent+"/** Get the id */");
				out.println(indent+"public String getId() {");
				out.println(indent+indent+"return id;");
				out.println(indent+"}");
				out.println(indent+"/** Set the id */");
				out.println(indent+"public void setId(String id) {");
				out.println(indent+indent+"this.id = id;");
				out.println(indent+"}");
			}
//			// Add invariants
//			if (context.isGenerateInvariant()) {
//				out.println();
//				out.println(indent+"/** The invariants */");
//				out.println(indent+"protected List invariants;");
//				out.println(indent+"/** Get the invariants */");
//				out.println(indent+"public List getInvariants() {");
//				out.println(indent+indent+"return invariants;");
//				out.println(indent+"}");
//				out.println(indent+"/** Set the invariants */");
//				out.println(indent+"public void setInvariants(List invariants) {");
//				out.println(indent+indent+"this.invariants = invariants;");
//				out.println(indent+"}");
//				outputInvariantsToImplementation(cls, out);        
//			}
			// Add toString
			out.println();
			out.println(indent+"/** Override toString */");
			out.println(indent+"public String toString() {");
			String cleanName = context.getNaming().removeOffset(context.getNaming().getRootOffset(), context.getNaming().getFullInterfaceName(cls));
			out.println(indent+indent+"String strId = \""+cleanName+"\";");
			out.println(indent+indent+"String name = null;");
			out.println(indent+indent+"try {");
			out.println(indent+indent+indent+"java.lang.Class cls = this.getClass();");
			out.println(indent+indent+indent+"java.lang.reflect.Method method = cls.getMethod(\""+context.getNaming().getGetter("name")+"\", new java.lang.Class[] {});");
			out.println(indent+indent+indent+"name = (String) method.invoke(this, new Object[] {});");
			out.println(indent+indent+indent+"if (name != null && name.length()==0) name = null;");
			out.println(indent+indent+"} catch (Exception e) {");
			out.println(indent+indent+"}");
			if (context.isGenerateID()) {
				out.println(indent+indent+"if (name == null)");
				out.println(indent+indent+indent+"return strId+\" 'id-\"+getId()+\"'\";");
				out.println(indent+indent+"else");
	        	
				out.println(indent+indent+indent+"return strId+\" '\"+name+\"-\"+getId()+\"'\";");
			} else {
				out.println(indent+indent+"if (name == null)");
				out.println(indent+indent+indent+"return strId;");
				out.println(indent+indent+"else");
				out.println(indent+indent+indent+"return strId+\" '\"+name+\"'\";");
			}
			out.println(indent+"}");
			// Output delete
			out.println();
			out.println(indent+"/** Delete the object */");
			outputDeleteToImplementation(cls, superClasses, out);
			// Output clone
			out.println();
			out.println(indent+"/** Clone the object */");
			outputCloneToImplementation(cls, superClasses, out);
			// Output accept
			if (context.isGenerateVisitor()) {
				out.println();
				out.println(indent+"/** Accept '"+context.getNaming().getFullVisitorInterfaceName(cls)+"' */");
				out.println(indent+"public Object accept("+context.getNaming().getFullVisitorInterface(modelName)+" v, Object data) {");
				out.println(indent+indent+"return v.visit(this, data);");
				out.println(indent+"}");
			}
			out.println("}");
		}
	}
    
	/** 
	  * Print the implementation of an enumeration
	  */
	protected void outputEnumerationImplementation(PrintWriter out) {
		out.println("package "+fullPkgName+";");
		out.println();
//		  out.println("import java.io.*;");
//		out.println("import java.util.*;");
//		  out.println();
//		out.println("import uk.ac.kent.cs.kmf.util.*;");
//		  out.println();
//		  out.println("import "+modelPackage+".*;");
//		  out.println();
//		  if (context.isGenerateVisitor()) {
//			out.println("import "+modelPackage+"."+CodeGenerator.getVisitableInterface(modelName)+";");
//			out.println("import "+modelPackage+"."+CodeGenerator.getVisitorInterface(modelName)+";");
//		  }
//		  if (context.isGenerateFactory()) {
//			out.println("import "+modelPackage+"."+CodeGenerator.getFactoryInterface(modelName)+";");
//		  }
		// Compute interfaces
		List interfaces = new Vector();
		interfaces.add(interfaceName);
		if (context.isGenerateVisitor()) {
			interfaces.add(context.getNaming().getFullVisitableInterface(modelName));
		}
		// Compute header
		String header = "public class "+className+"\n";
		if (interfaces.size() != 0) {
			Iterator j = interfaces.iterator();
			header += "implements\n    "+j.next();
			while (j.hasNext()) header += ",\n    "+j.next();
		}
		header += "\n{";
		// Add header
		out.println(header);
		// Add enumerators
		if (cls instanceof Class_) {
			Iterator j = cls.getFeature().iterator();
			while (j.hasNext()) {
				Feature f = (Feature)j.next();
				if (f instanceof Attribute) {
					Attribute attrib = (Attribute)f;
					String name = context.getNaming().getPropertyName(attrib);
					out.println(indent+"/** The '" + name + "' enumerator */");
					out.println(indent+"public static final "+interfaceName+" "+name+" = new "+className+"();");
				}
			}
		} else {
			Iterator j = ((Enumeration_)cls).getLiterals().iterator();
			while (j.hasNext()) {
				EnumLiteral lit = (EnumLiteral)j.next();
				String name = lit.getName().getBody_();
				out.println(indent+"/** The '" + name + "' enumerator */");
				out.println(indent+"public static final "+interfaceName+" "+name+" = new "+className+"();");
			}
		}
		// Add constructors
		out.println();
		out.println(indent+"/** Default constructors */");
		out.println(indent+"public "+className+"() {");
		out.println(indent+"}");
		// Add identifier
		if (context.isGenerateID()) {
			out.println();
			out.println(indent+"/** The id */");
			out.println(indent+"protected String id;");
			out.println(indent+"/** Get the id */");
			out.println(indent+"public String getId() {");
			out.println(indent+indent+"return id;");
			out.println(indent+"}");
			out.println(indent+"/** Set the id */");
			out.println(indent+"public void setId(String id) {");
			out.println(indent+indent+"this.id = \"1\";");
			out.println(indent+"}");
		}
		// Add toString
		out.println();
		out.println(indent+"/** Overrride toString */");
		out.println(indent+"public String toString() {");
		out.println(indent+indent+"String res = \""+interfaceName+"\";");
		// Add enumerators
		if (cls instanceof Class_) {
			Iterator j = cls.getFeature().iterator();
			while (j.hasNext()) {
				Feature f = (Feature)j.next();
				if (f instanceof Attribute) {
					Attribute attrib = (Attribute)f;
					String name = context.getNaming().getPropertyName(attrib);
					out.println(indent+indent+"if (this == "+name+") res += \"::"+name+"\";");
				}
			}
		} else {
			Iterator j = ((Enumeration_)cls).getLiterals().iterator();
			while (j.hasNext()) {
				EnumLiteral lit = (EnumLiteral)j.next();
				String name = lit.getName().getBody_();
				out.println(indent+indent+"if (this == "+name+") res += \"::"+name+"\";");
			}
		}
		out.println(indent+indent+"return res;");
		out.println(indent+"}");
		// Add clone
		out.println(indent+"/** Clone the object */");
		out.println(indent+"public Object clone() {");
		out.println(indent+indent+""+className+" obj = new "+className+"();");
		out.println(indent+indent+"return obj;");
		out.println(indent+"}");
		// Add delete
		out.println(indent+"/** Delete the object */");
		out.println(indent+"public void delete() {");
		out.println(indent+"}");
		// Add visitable
		if (context.isGenerateVisitor() && !cls.getIsAbstract().booleanValue()) {
			out.println();
			out.println(indent+"/** Accept the visitor */");
			out.println(indent+"public Object accept("+context.getNaming().getFullVisitorInterface(modelName)+" v, Object obj) {");
			out.println(indent+indent+"return v.visit(this, obj);");
			out.println(indent+"}");
		}
		out.println("}");
	}

	/** 
	  * Print constructors to Class
	  */
	protected void outputConstructorsToImplementation(List superClasses, PrintWriter out) {
		// Compute signature and body constructors
		String defaultSignature = indent+"public "+className+"(";
		String signature = indent+"public "+className+"(";
		String defaultBody = new String(" {\n");
		String body = new String(" {\n");
		Set addedAttrib = new LinkedHashSet();
		Set addedAssoc = new LinkedHashSet();
		Set addedInvariant = new LinkedHashSet();
		int argNo = 1;
		for(int i=superClasses.size()-1; i>=0; i--) {
			Classifier superCls = (Classifier)superClasses.get(i);
			String superClsName = context.getNaming().getClassifierName(superCls);
			// Add attributes
			Iterator j = superCls.getFeature().iterator();
			while (j.hasNext()) {
				Feature f = (Feature)j.next();
				if (f instanceof Attribute) {
					// Compute name, type, and default value
					Attribute attrib = (Attribute)f;
					String attribName = context.getNaming().getPropertyName(attrib);
					if (!addedAttrib.contains(attribName)) {
						String propName = context.getNaming().getPropertyName(attrib);
						String typeName = context.getNaming().getPropertyType(attrib, attrib.getType(), cls);
						String defaultValue = context.getNaming().getPropertyDefaultValue(attrib);
						// Add them to signature and bodies
						if (argNo++ != 1) signature += ", ";
						signature += typeName+" "+propName;
						defaultBody += indent+indent+"//--- Set property '"+ propName + "' from '"+superClsName+"' ---\n";
						body += indent+indent+"//--- Set property '"+propName+"' from '"+superClsName+"' ---\n";
						defaultBody += indent+indent+"this."+propName+" = "+defaultValue+";\n";
						body += indent+indent+"this."+propName+" = "+propName+";\n";
						// Add it
						addedAttrib.add(attribName);
					}
				}
			}
            
			// Add associations
			j = context.getAssociations().iterator();
			while (j.hasNext()) {
				Association assoc = (Association)j.next();
				AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
				AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
				if (context.getNaming().add(aend1, aend2, superCls)) {
					String assocName = context.getNaming().getPropertyName(aend2);
					if (!addedAssoc.contains(assocName)) {
						// Compute name, type, and default value
						String propName = context.getNaming().getPropertyName(aend2);
						String typeName = context.getNaming().getPropertyType(aend2, aend2.getType(), cls);
						String defaultValue = context.getNaming().getPropertyDefaultValue(aend2);
						// Add them to bodies
						defaultBody += indent+indent+"//--- Set property '"+ propName + "' from '"+superClsName+"' ---\n";
						body += indent+indent+"//--- Set property '"+propName+"' from '"+superClsName+"' ---\n";
						defaultBody += indent+indent+"this."+propName+" = "+defaultValue+";\n";
						body += indent+indent+"this."+propName+" = "+defaultValue+";\n";
						// Add it
						addedAssoc.add(assocName);                            
					}
				}
				if (context.getNaming().add(aend2, aend1, superCls)) {
					String assocName = context.getNaming().getPropertyName(aend1);
					if (!addedAssoc.contains(assocName)) {
						// Compute name, type, and default value
						String propName = context.getNaming().getPropertyName(aend1);
						String typeName = context.getNaming().getPropertyType(aend1, aend1.getType(), cls);
						String defaultValue = context.getNaming().getPropertyDefaultValue(aend1);
						// Add them to signature and bodies
						defaultBody += indent+indent+"//--- Set property '"+ propName + "' from '"+superClsName+"' ---\n";
						body += indent+indent+"//--- Set property '"+propName+"' from '"+superClsName+"' ---\n";
						defaultBody += indent+indent+"this."+propName+" = "+defaultValue+";\n";
						body += indent+indent+"this."+propName+" = "+defaultValue+";\n";
						// Add it
						addedAssoc.add(assocName);                            
					}
				}
			}
		}
		// Close signature and body
		defaultSignature += ")";
		defaultBody += indent+"}";
		signature += ")";
		body += indent+"}";
		// Print Default constructor
		out.println(indent+"/** Default constructor */");
		out.println(defaultSignature + defaultBody);
		if (!signature.equals(defaultSignature)) {
			out.println(indent+"/** Specialized constructor */");
			out.println(signature + body);
		}
	}   

	/** 
	 *  Print Attributes, Associations, and Operations to implementation
	 */
	protected void outputPropertiesToImplementation(List classes, PrintWriter out) {
		// Simple inheritance
		if (cls.getGeneralization().size()==1) {
			Generalization gen = (Generalization)cls.getGeneralization().iterator().next();
			Classifier superCls = (Classifier)gen.getParent(); 
			if (!superCls.getIsAbstract().booleanValue()) {
				classes = new Vector();
				classes.add(cls);
			}
		}
		// Add Properties        
		Set addedProp = new LinkedHashSet();
		for(int i = classes.size()-1; i>=0; i--) {
			Classifier a_cls = (Classifier)classes.get(i);
			String a_clsName = context.getNaming().getClassifierName(a_cls);
			Iterator j = a_cls.getFeature().iterator();
			while (j.hasNext()) {
				Feature f = (Feature)j.next();
				if (f instanceof Attribute) {
					Attribute attrib = (Attribute)f;
					String attribName = context.getNaming().getPropertyName(attrib);
					if (!addedProp.contains(attribName)) {
						// Print the attribute
						out.println();
						out.println(indent+"/** Property '"+attribName+"' from '"+a_clsName+"' */");
						out.println(ClassGenerator.getPropertyDecl(context, attrib, (Classifier)attrib.getType(), cls));
						out.println(indent+"/** Get property '"+attribName+"' from '"+a_clsName+"' */");
						out.println(ClassGenerator.getPropertyAccessor(context, attrib, (Classifier)attrib.getType(), cls));
						out.println(indent+"/** Set property '"+attribName+"' from '"+a_clsName+"' */");
						out.println(ClassGenerator.getPropertyMutator(context, attrib, (Classifier)attrib.getType(), cls));
						// Add it
						addedProp.add(attribName);
					} else {
						log.reportWarning("Property '"+attribName+"' is overriden in class '"+context.getNaming().getFullClassifierName(cls)+"'");
					}
				}
			}
			Iterator k = context.getAssociations().iterator();
			while (k.hasNext()) {
				Association assoc = (Association)k.next();
				AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
				AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
				if (context.getNaming().add(aend1, aend2, a_cls)) {
					String assocName = context.getNaming().getPropertyName(aend2);
					if (!addedProp.contains(assocName)) {
						// Print the association
						out.println();
						out.println(indent+"/** Property '"+assocName+"' from '"+a_clsName+"' */");
						out.println(ClassGenerator.getPropertyDecl(context, aend2, (Classifier)aend2.getType(), cls ));
						out.println(indent+"/** Get property '"+assocName+"' from '"+a_clsName+"' */");
						out.println(ClassGenerator.getPropertyAccessor(context, aend2, (Classifier)aend2.getType(), cls));
//						if (!isCollection(aend2)  && !isComposite(aend2)) {
							out.println(indent+"/** Set property '"+assocName+"' from '"+a_clsName+"' */");
							out.println(ClassGenerator.getPropertyMutator(context, aend2, (Classifier)aend2.getType(), cls));
//						}
						// Add it
						addedProp.add(assocName);                            
					} else {
						log.reportWarning("Association '"+assocName+"' is overriden in class '"+context.getNaming().getFullClassifierName(cls)+"'");
					}
				}
				if (context.getNaming().add(aend2, aend1, a_cls)) {
					String assocName = context.getNaming().getPropertyName(aend1);
					if (!addedProp.contains(assocName)) {
						out.println();
						out.println(indent+"/** Property '"+assocName+"' from '"+a_clsName+"' */");
						out.println(ClassGenerator.getPropertyDecl(context, aend1, (Classifier)aend1.getType(), cls));
						out.println(indent+"/** Get property '"+assocName+"' from '"+a_clsName+"' */");
						out.println(ClassGenerator.getPropertyAccessor(context, aend1, (Classifier)aend1.getType(), cls));
//						if (!isCollection(aend1)  && !isComposite(aend1)) {
							out.println(indent+"/** Set property '"+assocName+"' from '"+a_clsName+"' */");
							out.println(ClassGenerator.getPropertyMutator(context, aend1, (Classifier)aend1.getType(), cls));
//						}
						// Add it
						addedProp.add(assocName);                            
					} else {
						log.reportWarning("Association '"+assocName+"' is overriden in class '"+context.getNaming().getFullClassifierName(cls)+"'");
					}
				}
			}
		}
		Iterator c = classes.iterator();
		Set addedOper = new LinkedHashSet();
		while (c.hasNext()) {
			Classifier a_cls = (Classifier)c.next();
			String a_clsName = context.getNaming().getClassifierName(a_cls);
			// Add operations
			Iterator k = a_cls.getFeature().iterator();
			while (k.hasNext()) {
				Feature f = (Feature)k.next();
				if (f instanceof Operation) {
					Operation op = (Operation)f;
					String opName = context.getNaming().getOperationName(op);
					String opKey = ClassGenerator.getOperationSignature(context, op, (Classifier)op.getOwner(), cls, true);
					if (!addedOper.contains(opKey)) {
						// Print it
						out.println();
						out.println(indent+"/** Operation '"+opName+"' from '"+a_clsName+"' */");
						out.println(ClassGenerator.getOperationDefinition(context, op, (Classifier)op.getOwner(), cls));
						// Add it
						addedOper.add(opKey);
					} else {
						log.reportWarning("Operation '"+opName+"' is overriden in class '"+context.getNaming().getFullClassifierName(cls)+"'");
					}
				}
			}
		}        
	}

	/**
	  * Print the constraints to class
	  */
	protected void outputInvariantsToImplementation(Classifier cls, PrintWriter out) {
		out.println(indent+"/** Parse all invariants */");
		out.println(indent+"public Boolean parseInvariants(ILog log) {");
		out.println(indent+indent+"java.util.Iterator i = invariants.iterator();");
		out.println(indent+indent+"boolean res = true;");
		out.println(indent+indent+"log.reportMessage(\"Parsing invariants from \"+this.toString());");
		out.println(indent+indent+"while(i.hasNext()) {");
		out.println(indent+indent+indent+"String nameValue[] = (String[])i.next();");
		out.println(indent+indent+indent+"String name = nameValue[0];");
		out.println(indent+indent+indent+"String value = nameValue[1];");
		out.println(indent+indent+indent+"log.reportMessage(name+\":\"+value);");
		out.println(indent+indent+"}");
		out.println(indent+indent+"return new Boolean(res);");
		out.println(indent+"}");
		out.println(indent+"/** Build suntax trees and evaluate all invariants */");
		out.println(indent+"public Boolean evaluateInvariants(ILog log) {");
		out.println(indent+indent+"java.util.Iterator i = invariants.iterator();");
		out.println(indent+indent+"boolean res = true;");
		out.println(indent+indent+"log.reportMessage(\"Parsing invariants from \"+this.toString());");
		out.println(indent+indent+"while(i.hasNext()) {");
		out.println(indent+indent+indent+"String nameValue[] = (String[])i.next();");
		out.println(indent+indent+indent+"String name = nameValue[0];");
		out.println(indent+indent+indent+"String value = nameValue[1];");
		out.println(indent+indent+indent+"log.reportMessage(name+\":\"+value);");
		out.println(indent+indent+"}");
		out.println(indent+indent+"return new Boolean(res);");
		out.println(indent+"}");
	}
	
	/**
	  * Print Delete to implementation
	  */
	protected void outputDeleteToImplementation(Classifier cls, List superClasses, PrintWriter out) {
		// Output delete for bidirectional links (used in browser)
		if (context.isGenerateBidirectional()) {
			out.println(indent+"public void delete() {");
			Set addedAttrib = new LinkedHashSet();
			Set addedAssoc = new LinkedHashSet();
			for(int i=superClasses.size()-1; i>=0; i--) {
				Classifier superCls = (Classifier)superClasses.get(i);
				Iterator n = context.getAssociations().iterator();
				while (n.hasNext()) {
					Association assoc = (Association)n.next();
					AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
					AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
					if (context.getNaming().add(aend1, aend2, superCls)) {
						String assocName = context.getNaming().getPropertyName(aend2);
						if (!addedAssoc.contains(assocName)) {
							outputDeleteToImplementation(aend1, (Classifier)aend1.getType(), aend2, (Classifier)aend2.getType(), cls, out);
							addedAssoc.add(assocName);
						}
					}
					if (context.getNaming().add(aend2, aend1, superCls)) {
						String assocName = context.getNaming().getPropertyName(aend1);
						if (!addedAssoc.contains(assocName)) {
							outputDeleteToImplementation(aend2, (Classifier)aend2.getType(), aend1, (Classifier)aend1.getType(), cls, out);
							addedAssoc.add(assocName);
						}
					}
				}
			}
			out.println(indent+"}");
		}
	}

	/** 
	  * Print AssociationEnd's deletion
	  */
	protected void outputDeleteToImplementation(AssociationEnd aend1, Classifier type1, AssociationEnd aend2, Classifier type2, Classifier cls, PrintWriter out) {
		uk.ac.kent.cs.kmf.kmfstudio.Naming naming = context.getNaming();
		String assocName1 = naming.getPropertyName(aend1);
		String typeName1 = naming.getFullInterfaceName(type1);
		String assocName2 = naming.getPropertyName(aend2);
		String typeName2 = naming.getFullInterfaceName(type2);
		// Basic type
		if (Type.isPrimitiveType(typeName2)) {
		// Collection type
		} if (context.getNaming().isCollection(aend2)) {
			out.println(indent+indent+"java.util.Iterator "+assocName2+"It = this."+assocName2+".iterator();");
			out.println(indent+indent+"while ("+assocName2+"It.hasNext()) {");
			out.println(indent+indent+indent+typeName2+" "+assocName2+"Obj = ("+typeName2+")"+assocName2+"It.next();");
			if (context.getNaming().isCollection(aend1)) {
				out.println(indent+indent+indent+assocName2+"Obj."+context.getNaming().getGetter(assocName1)+"().remove(this);");
				out.println(indent+indent+indent+assocName2+"Obj."+context.getNaming().getGetter(assocName1)+"().remove(this);");
			} else {
				out.println(indent+indent+indent+"if ("+assocName2+"Obj != null)");
				out.println(indent+indent+indent+indent+assocName2+"Obj."+context.getNaming().getSetter(assocName1)+"(null);");
			}
			out.println(indent+indent+"}");
		} else {
			if (context.getNaming().isCollection(aend1)) {
				out.println(indent+indent+"if ("+assocName2+" != null)");
				out.println(indent+indent+indent+"this."+assocName2+"."+context.getNaming().getGetter(assocName1)+"().remove(this);");
			} else {
				out.println(indent+indent+"if ("+assocName2+" != null)");
				out.println(indent+indent+indent+"this."+assocName2+"."+context.getNaming().getSetter(assocName1)+"(null);");
			}
		}
	}

	/**
	  * Print Clone to implementation
	  */
	protected void outputCloneToImplementation(Classifier cls, List superClasses, PrintWriter out) {
		// Output clone
		out.println(indent+"public Object clone() {");
		out.println(indent+indent+""+className+" obj = new "+className+"();");
		Set addedAttrib = new LinkedHashSet();
		Set addedAssoc = new LinkedHashSet();
		for(int i=superClasses.size()-1; i>=0; i--) {
			Classifier superCls = (Classifier)superClasses.get(i);
			// clone for attributes assumes all attributes are 'by_value'
			Iterator m = superCls.getFeature().iterator();
			while (m.hasNext()) {
				Feature f = (Feature)m.next();
				if (f instanceof Attribute) {
					Attribute attrib = (Attribute)f;
					String attribName = context.getNaming().getPropertyName(attrib);
					if (!addedAttrib.contains(attribName)) {
						outputCloneToImplementation(attrib, (Classifier)attrib.getType(), cls, out);
						// Add it
						addedAttrib.add(attribName);
					}
				}
			}
			// clone for assoc ends clones 'by_value' for 'COMPOSITE' otherwise clones 'by_ref'
			Iterator n = context.getAssociations().iterator();
			while (n.hasNext()) {
				Association assoc = (Association)n.next();
				AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
				AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
				if (context.getNaming().add(aend1, aend2, superCls)) {
					String assocName = context.getNaming().getPropertyName(aend2);
					if (!addedAssoc.contains(assocName)) {
						outputCloneToImplementation(aend2, (Classifier)aend2.getType(), cls, out);
						addedAssoc.add(assocName);
					}
				}
				if (context.getNaming().add(aend2, aend1, superCls)) {
					String assocName = context.getNaming().getPropertyName(aend1);
					if (!addedAssoc.contains(assocName)) {
						outputCloneToImplementation(aend1, (Classifier)aend1.getType(), cls, out);
						addedAssoc.add(assocName);
					}
				}
			}
		}
		out.println(indent+indent+"return obj;");
		out.println(indent+"}");
	}
	
	/** 
	  * Print Attribute's clone
	  */
	protected String collectionType(String typeName) {
		if (typeName.equals(context.getNaming().getCollectionInterface())) return context.getNaming().getCollectionClass();
		if (typeName.equals(context.getNaming().getListInterface())) return context.getNaming().getListClass();
		if (typeName.equals(context.getNaming().getSetInterface())) return context.getNaming().getSetClass();
		return typeName;
	}
	protected void outputCloneToImplementation(Attribute attrib, Classifier type, Classifier cls, PrintWriter out) {
		String attribName = context.getNaming().getPropertyName(attrib);
		String typeName = context.getNaming().getPropertyType(attrib, type, cls);
		String castType = collectionType(typeName);
		// Basic type
		if (Type.isPrimitiveType(typeName)) {
			out.println(indent+indent+"obj."+attribName+" = "+attribName+"==null ? null : this."+attribName+";");
		// Collection type
		} else if (context.getNaming().isCollection(attrib)) {
			out.println(indent+indent+"obj."+attribName+" = "+attribName+"==null ? null : ("+typeName+")(("+castType+")this."+attribName+").clone();");
		// User type
		} else {
			out.println(indent+indent+"obj."+attribName+" = "+attribName+"==null ? null : ("+typeName+")this."+attribName+".clone();");
		}
	}

	/** 
	  * Print AssociationEnd's clone
	  */
	protected void outputCloneToImplementation(AssociationEnd aend, Classifier type, Classifier cls, PrintWriter out) {
		String assocName = context.getNaming().getPropertyName(aend);
		String typeName = context.getNaming().getPropertyType(aend, type, cls);
		String castType = collectionType(typeName);
		// Basic type
		if (Type.isPrimitiveType(typeName)) {
			out.println(indent+indent+"obj."+assocName+" = "+assocName+"==null ? null : this."+assocName+";");
		// Collection type
		} else if (context.getNaming().isComposite(aend)) {
			if (context.getNaming().isCollection(aend)) {
				out.println(indent+indent+"obj."+assocName+" = "+assocName+"==null ? null : ("+typeName+")(("+castType+")this."+assocName+").clone();");
			} else {
				out.println(indent+indent+"obj."+assocName+" = "+assocName+"==null ? null : this."+assocName+";");
			}
		} else {
			if (context.getNaming().isCollection(aend))
				out.println(indent+indent+"obj."+assocName+" = "+assocName+"==null ? null : ("+typeName+")(("+castType+")this."+assocName+").clone();");
			else
				out.println(indent+indent+"obj."+assocName+" = "+assocName+"==null ? null : this."+assocName+";");
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
	protected String modelName;
	protected String fullPkgName;
	protected String interfaceName;
	protected String className;
}
