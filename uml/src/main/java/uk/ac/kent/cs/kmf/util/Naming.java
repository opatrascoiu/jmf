package uk.ac.kent.cs.kmf.util;

import java.util.*;

import java.io.*;
import java.text.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.*;

public class Naming 
{
	/** 
	  * Create packages in the out directory
	  */
	protected static Model model;
	protected static String modelName;
	protected static String modelPackage;
	protected static String outDirName = new String();
	protected static String rootOffset = new String();
	protected static String licenceFileName; 
	protected static ILog log;
	
	// Settings
	public static String interfacePrefix = "";
	public static String interfaceSuffix = "";
	public static String classPrefix = "";
	public static String classSuffix = "$Class";
	public static String collectionInterface = "java.util.Collection";
	public static String listInterface = "java.util.List";
	public static String setInterface = "java.util.Set";
	public static String collectionClass = "java.util.Vector";
	public static String listClass = "java.util.Vector";
	public static String setClass = "java.util.LinkedHashSet";
        
	/** Construct the code generator */
	public Naming(Model model, String rootOffset, ILog log) {
		//--- Set the model ---
		Naming.model = model;
		//--- Compute the offset
		Naming.rootOffset = rootOffset;
		if (rootOffset.length()!=0 && !rootOffset.endsWith(".")) rootOffset += ".";
		//--- Compute model name and model package
		modelName = getPackageName((Package)model);
		modelPackage = rootOffset+modelName;
	}
    
	/** Get the model from an XMI file */
	public static Model getModel() {
		return model;
	}
	
	/** Get the name of a ModelElement */
	public static String getName(ModelElement element) {
		Name name = element.getName();
		if (name != null) return name.getBody_();
		if (element instanceof AssociationEnd) {
			StringBuffer nameB = new StringBuffer(getClassifierName(((AssociationEnd)element).getType()));
			nameB.setCharAt(0, Character.toLowerCase(nameB.charAt(0)));
			String str = new String(nameB);
			return str;
		}
		return "";
	}
	/** Get the model name */
	public static String getModelName() {
		return modelName;
	}
	/** Get rootoffset */
	public static String getRootOffset() {
		return rootOffset;
	}
	/** Set rootoffset */
	public static void setRootOffset(String string) {
		rootOffset = string;
	}
	/** Get the model package */
	public static String getModelPackage() {
		return modelPackage;
	}

	/** Get package name */
	public static String getPackageName(Package pkg) {
		return uk.ac.kent.cs.kmf.util.Naming.getCleanName(pkg.getName().getBody_());
	}
	/** Get full package name */
	public static String getFullPackageName(Package pkg) {
		if (pkg.getNamespace_() == null) return rootOffset+getPackageName(pkg);
		if (pkg.getNamespace_().getName().getBody_().equals("null")) return rootOffset+getPackageName(pkg);
		String pre = getFullPackageName((Package)pkg.getNamespace_());
		return pre+"."+getPackageName(pkg);
	}
    
	/** Get classifier name */
	public static String getClassifierName(Classifier cls) {
		if (cls == null) {
			log.reportError("'null' classifier found");
			return "nullClassifier";
		}
		String name = cls.getName().getBody_();
		if (name.length() >= 1)
			name = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
		if (isStereotype(cls, "primitive")) return name;
		if (!name.endsWith("}")) name = uk.ac.kent.cs.kmf.util.Naming.getCleanName(name);
		return name;
	}
	/** Get classifier name */
	public static String getClassifierName(Classifier cls, Classifier dst) {
		if (cls == null) {
			log.reportError("'null' classifier found");
			return "nullClassifier";
		}
		String name = cls.getName().getBody_();
		if (name.length() >= 1)
			name = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
		if (isStereotype(cls, "primitive")) return name;
		if (!name.endsWith("}")) name = uk.ac.kent.cs.kmf.util.Naming.getCleanName(name);
		if (!inSamePackage(cls, dst)) name = getFullPackageName(cls)+"."+name;
		return name;
	}
	/** Get full classifier name */
	public static String getFullClassifierName(Classifier cls) {
		if (cls == null) {
			log.reportError("'null' classifier found");
			return rootOffset+"";
		}
		String name = getClassifierName(cls);
		if (isStereotype(cls, "primitive")) return name;
		if (cls.getNamespace_() == null) return rootOffset+getClassifierName(cls);
		if (cls.getNamespace_().getName().getBody_().equals("null")) return rootOffset+getClassifierName(cls);
		String pre = (getFullPackageName((Package)cls.getNamespace_()));
		return pre+"."+getClassifierName(cls);
	}
	/** Get full package name of a classifier */
	public static String getFullPackageName(Classifier cls) {
		String name = getFullClassifierName(cls);
		int pos = name.lastIndexOf('.');
		if (pos != -1) name = name.substring(0, pos);
		return name;
	}
	/** Get interface name of a class */
	public static  String getInterfaceName(Classifier cls) {
		return interfacePrefix+getClassifierName(cls)+interfaceSuffix;
	}
	/** Get full interface name of a class */
	public static String getFullInterfaceName(Classifier cls) {
		return getFullPackageName(cls)+"."+getInterfaceName(cls);
	}
	/** Get class name of a class */
	public static String getClassName(Classifier cls) {
		return getClassifierName(cls)+classSuffix;
	}
	/** Get class name of a class */
	public static String getClassName(Classifier cls, Classifier src) {
		return getClassifierName(cls, src)+classSuffix;
	}
	/** Get class name of a class */
	public static String getClassName(String clsName) {
		return uk.ac.kent.cs.kmf.util.Naming.getCleanName(clsName)+classSuffix;
	}
	/** Get full class name of a class */
	public static String getFullClassName(Classifier cls) {
		return getFullPackageName(cls)+"."+getClassName(cls);
	}
	/** Get factory interface name of a class */
	public static String getFactoryInterfaceName(Classifier cls) {
		return getClassifierName(cls)+"$Factory";
	}
	/** Get full factory interface name of a class */
	public static String getFullFactoryInterfaceName(Classifier cls) {
		return getFullPackageName(cls)+"."+getFactoryInterfaceName(cls);
	}
	/** Get factory class name of a class */
	public static String getFactoryClassName(Classifier cls) {
		return getClassifierName(cls)+"$Factory"+classSuffix;
	}
	/** Get full factory class name of a class */
	public static String getFullFactoryClassName(Classifier cls) {
		return getFullPackageName(cls)+"."+getFactoryClassName(cls);
	}
	/** Get visitor interface name of a class */
	public static  String getVisitorInterfaceName(Classifier cls) {
		return getClassifierName(cls)+"$Visitor";
	}
	/** Get full visitor interface name of a class */
	public static String getFullVisitorInterfaceName(Classifier cls) {
		return getFullPackageName(cls)+"."+getVisitorInterfaceName(cls);
	}
	/** Get visitor interface name of a factory */
	public static String getFactoryVisitorInterfaceName(Classifier cls) {
		return getFactoryInterfaceName(cls)+"$Visitor";
	}
	/** Get full visitor interface name of a factory */
	public static String getFullFactoryVisitorInterfaceName(Classifier cls) {
		return getFullPackageName(cls)+"."+getFactoryVisitorInterfaceName(cls);
	}
    
	public static String removeOffset(String packageOffset, String interfaceName) {
		String header = null;
		if (packageOffset != null && packageOffset.length() != 0) header = packageOffset;
		if (!packageOffset.endsWith(".")) header += ".";
//		String modelName = getModelName();
//		if (modelName == null || modelName.length() == 0) modelName = CodeGenerator.getModelName();
//		if (modelName != null && modelName.length() != 0) {
//			if (header != null) 
//				if (header.endsWith(".")) header += modelName;
//				else header += "."+modelName;
//			else header = modelName;
//		}
//		if (header != null && header.length() != 0) {
//			if (interfaceName.startsWith(header)) {
//				interfaceName = interfaceName.substring(header.length()+1, interfaceName.length());
//			}
//		}
		if (header != null && header.length() != 0) {
			if (interfaceName.startsWith(header)) {
				interfaceName = interfaceName.substring(header.length(), interfaceName.length());
			}
		}
		return interfaceName;
	}
    
	/** Get property name for an attribute */
	public static String getPropertyName(Attribute attrib) {
		String name = attrib.getName().getBody_();
		return uk.ac.kent.cs.kmf.util.Naming.getCleanName(name);
	}
	/** Get property name for an association end */
	public static String getPropertyName(AssociationEnd aend) {
		String name = null;
		if (aend.getName() == null) {
			StringBuffer nameB = new StringBuffer(getClassifierName(aend.getType()));
			nameB.setCharAt(0, Character.toLowerCase(nameB.charAt(0)));
			name = new String(nameB);
		} else {
			name = aend.getName().getBody_();
			if (name.equals("")) {
				StringBuffer nameB = new StringBuffer(getClassifierName(aend.getType()));
				nameB.setCharAt(0, Character.toLowerCase(nameB.charAt(0)));
				name = new String(nameB);
			}
		}
		return uk.ac.kent.cs.kmf.util.Naming.getCleanName(name);
	}
	/** Get property type for an attribute */
	public static String getPropertyType(Attribute attrib, Classifier src, Classifier dst) {
		if (attrib.getType() != null) {
			// Get model type name and Java mapping
			String modelTypeName = attrib.getType().getName().getBody_();
			String javaTypeName = Type.getJavaType(modelTypeName);
			// Simple type
			if (!isCollection(attrib)) {
				if (javaTypeName != null) return javaTypeName;
				if (!inSamePackage(src, dst)) modelTypeName = getFullPackageName(src)+"."+modelTypeName;
				return modelTypeName;
			// Collection - attrib has multiplicity
			} else if (attrib.getMultiplicity()!=null) {
				if (attrib.getOrdering() == OrderingKind$Class.ORDERED) {
					return Naming.listInterface;
//					  return "IMutableSequence";
				} else {
					return Naming.setInterface;
//					  return "IMutableSet";
				}
			// Collection - javaTypeName is a Collection
			} else {
				return javaTypeName;
			}
		} else {
			log.reportError("The property '"+getPropertyName(attrib)+"' from '"+getClassifierName(dst)+"' should have a type");
			return "";
		}
	}
	/** Get full property type for an attribute */
	public static  String getPropertyFullType(Attribute attrib, Classifier src) {
		if (attrib.getType() != null) {
			// Get model type name and Java mapping
			String modelTypeName = attrib.getType().getName().getBody_();
			String javaTypeName = Type.getJavaType(modelTypeName);
			// Simple type
			if (!isCollection(attrib)) {
				if (javaTypeName != null) return javaTypeName;
				modelTypeName = getFullPackageName(attrib.getType())+"."+modelTypeName;
				return modelTypeName;
			// Collection - attrib has multiplicity
			} else if (attrib.getMultiplicity()!=null) {
				if (attrib.getOrdering() == OrderingKind$Class.ORDERED) {
					return Naming.listInterface;
//					  return "IMutableSequence";
				} else {
					return Naming.setInterface;
//					  return "IMutableSet";
				}
			// Collection - javaTypeName is a Collection
			} else {
				return javaTypeName;
			}
		} else {
			log.reportError("The property '"+getPropertyName(attrib)+"' from '"+getClassifierName(src)+"' should have a type");
			return "";
		}
	}
	/** Get property type for an association end */
	public static  String getPropertyType(AssociationEnd aend, Classifier src, Classifier dst) {
		if (aend != null) {
			if (!isCollection(aend)) {
				String typeName = getClassifierName(aend.getType());
				if (!inSamePackage(src, dst)) typeName = getFullPackageName(src)+"."+typeName;
				return typeName;
			} else {
				if (aend.getOrdering() == OrderingKind$Class.ORDERED) {
					return Naming.listInterface;
				} else {
					return Naming.setInterface;
				}
			}
		} else {
			log.reportError("The property '"+getPropertyName(aend)+"' from '"+getClassifierName(dst)+"' should have a type");
			return "";
		}
	}
	/** Get full property type for an association end */
	public static  String getPropertyFullType(AssociationEnd aend, Classifier src) {
		if (aend != null) {
			if (!isCollection(aend)) {
				String typeName = getFullPackageName(src)+"."+getClassifierName(aend.getType());
				return typeName;
			} else {
				if (aend.getOrdering() == OrderingKind$Class.ORDERED) {
					return Naming.listInterface;
				} else {
					return Naming.setInterface;
				}
			}
		} else {
			log.reportError("The property '"+getPropertyName(aend)+"' from '"+getClassifierName(src)+"' should have a type");
			return "";
		}
	}
	/** Get property default value for an attribute */
	public static  String getPropertyDefaultValue(Attribute attrib) {
		if (attrib.getType() == null) return "null";
		String modelTypeName = getClassifierName(attrib.getType());
		String javaDefault = JavaFactory.createString(modelTypeName);
		if (javaDefault == null) return "null";
		return javaDefault;
	}
	/** Get property default value for an association end */
	public static  String getPropertyDefaultValue(AssociationEnd aend) {
		if (aend == null) return "null";
		if (isCollection(aend)) 
			if (aend.getOrdering() == OrderingKind$Class.ORDERED)
				return "new "+Naming.listClass+"()";
			else
				return "new "+Naming.setClass+"()";
//		  if (isCollection(aend)) return aend.ordering() == OrderingKind.ORDERED ? "OCL.MutableSequence()" : "OCL.MutableSet()";
		return "null";
	}
	/** Get Java type name - used by operation */
	public static  String getTypeName(Classifier type, Classifier dst) {
		if (type != null) {
			String modelTypeName = type.getName().getBody_();
			String javaTypeName = Type.getJavaType(modelTypeName);
			if (javaTypeName != null) return javaTypeName;
			if (modelTypeName.endsWith("}")) return getModelName()+"Enum";
			if (!inSamePackage(type, dst)) modelTypeName = getFullPackageName(type)+"."+modelTypeName;
			return modelTypeName;
		} else {
			return "";
		}
	}    	
	/** Get full Java type name - used by operation */
	public static  String getFullTypeName(Classifier type) {
		if (type != null) {
			String modelTypeName = getClassifierName(type);
			String javaTypeName = Type.getJavaType(modelTypeName);
			if (javaTypeName != null) return javaTypeName;
			if (modelTypeName.endsWith("}")) return getModelPackage()+"."+getModelName()+"Enum";
			modelTypeName = getFullPackageName(type)+"."+modelTypeName;
			return modelTypeName;
		} else {
			return "";
		}
	}    	
	/** Get return type name for an operation */
	public static  String getReturnTypeName(Operation op, Classifier dst) {
		Iterator i = op.getParameter().iterator();
		while(i.hasNext()) {
			Parameter p = (Parameter)i.next();
			if (p.getKind() == ParameterDirectionKind$Class.RETURN) {
				return getTypeName(p.getType(), dst);
			}
		}
		return "void";
	}
	/** Get operation name */
	public static  String getOperationName(Operation op) {
		if (op == null) {
			log.reportError("'null' operation found");
			return "nullOperation";
		}
		String name = op.getName().getBody_();
		return uk.ac.kent.cs.kmf.util.Naming.getCleanName(name);
	}
	/** Get parameter name */
	public static  String getParameterName(Parameter par) {
		if (par == null) {
			log.reportError("'null' parameter found");
			return "nullOperation";
		}
		String name = par.getName().getBody_();
		return uk.ac.kent.cs.kmf.util.Naming.getCleanName(name);
	}
	/** Get names of parameters */
	public static  List getParamNames(Operation op) {
		Vector names = new Vector();
		Iterator i = op.getParameter().iterator();
		while(i.hasNext()) {
			Parameter p = (Parameter)i.next();
			if (p.getKind() == ParameterDirectionKind$Class.OUT || p.getKind() == ParameterDirectionKind$Class.INOUT) {
				names.add(getParameterName(p));
			}
		}
		return names;
	}
	/** Get quoted names of parameters */
	public static  List getParamQuotedNames(Operation op) {
		Vector names = new Vector();
		Iterator i = op.getParameter().iterator();
		while(i.hasNext()) {
			Parameter p = (Parameter)i.next();
			if (p.getKind() == ParameterDirectionKind$Class.OUT || p.getKind() == ParameterDirectionKind$Class.INOUT) {
				names.add("\""+getParameterName(p)+"\"");
			}
		}
		return names;
	}
	/** Get types of parameters */
	public static  List getParamTypeNames(Operation op, Classifier dst) {
		Vector types = new Vector();
		Iterator i = op.getParameter().iterator();
		while(i.hasNext()) {
			Parameter p = (Parameter)i.next();
			if (p.getKind() == ParameterDirectionKind$Class.OUT || p.getKind() == ParameterDirectionKind$Class.INOUT) {
				types.add(getTypeName(p.getType(), dst));
			}
		}
		return types;
	}
	/** Get full types of parameters */
	public static  List getParamFullTypeNames(Operation op, Classifier dst) {
		Vector types = new Vector();
		Iterator i = op.getParameter().iterator();
		while(i.hasNext()) {
			Parameter p = (Parameter)i.next();
			if (p.getKind() == ParameterDirectionKind$Class.OUT || p.getKind() == ParameterDirectionKind$Class.INOUT) {
				types.add(getFullTypeName(p.getType()));
			}
		}
		return types;
	}
	/** Get constraint name */
	public static  String getConstraintName(Constraint c) {
		String name = uk.ac.kent.cs.kmf.util.Naming.getCleanName(c.getName().getBody_());
		return "inv_"+name;
	}

	/**
	  * Auxiliary functions
	  */
/*    Set allSuperClasses(GeneralizableElement cls) {
		Set classes = new LinkedHashSet();
		if (cls == null) return classes;
		Iterator g = cls.generalization().iterator();
		while (g.hasNext()) {
			Generalization gen = (Generalization)g.next();
			Object parent = gen.parent();
			if (parent != null) {
				classes.add(parent);
				classes.addAll(allSuperClasses(gen.parent()));
			}
		}
		return classes;
	}
*/
	/* Compute all super classes of a class */
	public static  List allSuperClasses(GeneralizableElement cls, boolean report) {
		// Initialize the list
		List classes = new Vector();
		if (cls == null) return classes;
		classes.add(cls);
		int first = 0;
		int last = 1;
		// While there are unvisited nodes
		while (first < last) {
			// Get current class
			GeneralizableElement crtCls = (GeneralizableElement)classes.get(first++);
			// Scan all direct superclasses   	
			if (crtCls != null) {
				Iterator it = crtCls.getGeneralization().iterator();
				while (it.hasNext()) {
					Generalization gen = (Generalization)it.next();
					Object parent = gen.getParent();
					if (parent != null) {
						if (!classes.contains(parent)) { 
							classes.add(parent);
							last++;
						} else {
							if (report) 
								log.reportWarning("Multiple inheritance of class '"+getFullClassifierName((Classifier)parent)+"' in class '"+getFullClassifierName((Classifier)cls)+"'");
						}
					}
				}
			}
		}
		return classes;
	}
	/** Get attribute names */
	public static  Set getAttributeNames(List classes) {
		Set names = new LinkedHashSet();
		Iterator it = classes.iterator();
		while (it.hasNext()) {
			Classifier cls = (Classifier)it.next();
			Iterator m = cls.getFeature().iterator();
			while (m.hasNext()) {
				Feature f = (Feature)m.next();
				if (f instanceof Attribute) {
					Attribute attrib = (Attribute)f;
					String attribName = getPropertyName(attrib);
					names.add(attribName);
				}
			}

		}
		return names;
	}
	/** Get association end names */
	public static  Set getAssociationNames(List classes, Set associations) {
		Set names = new LinkedHashSet();
		Iterator itCls = classes.iterator();
		while (itCls.hasNext()) {
			Classifier cls = (Classifier)itCls.next();
			Iterator itAssoc = associations.iterator();
			while (itAssoc.hasNext()) {
				Association assoc = (Association)itAssoc.next();
				AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
				AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
				if (aend1.getType() == cls && isNavigable(aend2)) {
					String assocName = getPropertyName(aend2);
					names.add(assocName);
				}
				if (aend2.getType() == cls && isNavigable(aend1)) {
					String assocName = getPropertyName(aend1);
					names.add(assocName);
				}
			}
		}
		return names;
	}
	/** Get the name of the root package */
	public static  String getRootName(String name) {
		int pos = name.indexOf('.');
		if (pos == -1) return name;
		else return name.substring(0, pos);
	}
	/** Check if two classifiers are in the same package */
	public static  boolean inSamePackage(Classifier src, Classifier dst) {
		if (src == null) return false;
		if (dst == null) return false;
		String srcName = getFullClassifierName(src);
		String dstName = getFullClassifierName(dst);
		int srcPos = srcName.lastIndexOf('.');
		int dstPos = dstName.lastIndexOf('.');
		String srcPack = "";
		String dstPack = "";
		if (srcPos != -1) srcPack = srcName.substring(0, srcPos);
		if (dstPos != -1) dstPack = dstName.substring(0, dstPos);
		return srcPack.equals(dstPack);
	}
	/** Convert a vector into a list using a separator */
	public static  String toList(List vect, String separator) {
		String res = new String();
		for(int i=0; i<vect.size(); i++) {
			if (i!=0) res += separator;
			res += vect.get(i);
		}
		return res;
	}
	/** Convert a vector into a quoted list using a separator */
	public static  String toListQuote(List vect, String separator) {
		String res = "";
		for(int i=0; i<vect.size(); i++) {
			if (i!=0) res += separator;
			res += "\""+vect.get(i)+"\"";
		}
		return res;
	}
	/** Print time stamp on the generated files */
	public static  void putStamp(String clsName, PrintWriter out) {
		out.println("/**");
		out.println(" *");
		out.println(" *  Class "+clsName+".java");
		Calendar cal = Calendar.getInstance();
		String DATE_FORMAT = "dd MMMMM yyyy HH:mm:ss";
		SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(DATE_FORMAT);
		Date date = cal.getTime();
		out.println(" *");
		out.println(" *  Generated by KMFStudio at "+dateFormatter.format(date));	
		out.println(" *  Visit http://www.cs.ukc.ac.uk/kmf");
		out.println(" *");
		out.println(" */");
		out.println();
	}

	/** Change first letter from modelName to upper case */
	protected  static String toUpperCaseFirst(String name) {
		if (name.length() >= 1)
			name = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
		return name;
	}

	/** Top level visitor interface name */
	public static  String getVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"Visitor";
	}
	/** Top level visitor interface class */
	public static  String getVisitorClass(String modelName) {
		return toUpperCaseFirst(modelName)+"Visitor"+classSuffix;
	}
	/** Top level visitor name */
	public static  String getVisitableInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"Visitable";
	}
	/** Top level parseAll visitor interface name */
	public static  String getParseAllVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"ParseAllVisitor";
	}
	/** Top level parseAll visitor interface class */
	public static  String getParseAllVisitorClass(String modelName) {
		return toUpperCaseFirst(modelName)+"ParseAllVisitor"+classSuffix;
	}
	/** Top level analyseAll visitor interface name */
	public static  String getAnalyseAllVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"AnalyseAllVisitor";
	}
	/** Top level analyseAll visitor interface class */
	public static  String getAnalyseAllVisitorClass(String modelName) {
		return toUpperCaseFirst(modelName)+"AnalyseAllVisitor"+classSuffix;
	}
	/** Top level evaluateAll visitor interface name */
	public static  String getEvaluateAllVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"EvaluateAllVisitor";
	}
	/** Top level analyseAll visitor interface class */
	public static  String getEvaluateAllVisitorClass(String modelName) {
		return toUpperCaseFirst(modelName)+"EvaluateAllVisitor"+classSuffix;
	}
	/** Get the name of the factory interface */
	public static  String getFactoryInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"Factory";
	}
	/** Get the name of the factory class */
	public static  String getFactoryClass(String modelName) {
		return toUpperCaseFirst(modelName)+"Factory"+classSuffix;
	}
	/** Get the name of the repository interface */
	public static  String getRepositoryInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"Repository";
	}
	/** Get the name of the repository class */
	public static  String getRepositoryClass(String modelName) {
		return toUpperCaseFirst(modelName)+"Repository"+classSuffix;
	}
	/** Get the name of the top level element interface */
	public static  String getElementInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"Element";
	}
	/** Get the name of the top level element class */
	public static  String getElementClass(String modelName) {
		return toUpperCaseFirst(modelName)+"Element"+classSuffix;
	}
	/** Get the name of the ocl evaluator class */
	public static  String getOCLClass(String modelName) {
		return toUpperCaseFirst(modelName)+"OCLEvaluator"+classSuffix;
	}
	/** Get the name of the JTree visitor interface */
	public static  String getJTreeVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"JTreeVisitor";
	}
	/** Get the name of the JTree visitor class */
	public static  String getJTreeVisitorClass(String modelName) {
		return toUpperCaseFirst(modelName)+"JTreeVisitor"+classSuffix;
	}
	/** Get the name of the HUTN visitor interface */
	public static  String getHUTNVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"HUTNVisitor";
	}
	/** Get the name of the HUTN visitor class */
	public static  String getHUTNVisitorClass(String modelName) {
		return toUpperCaseFirst(modelName)+"HUTNVisitor"+classSuffix;
	}
	/** Get the name of the ReaderAdapter class name */
	public static  String getReaderAdapterClass(String modelName) {
		return toUpperCaseFirst(modelName)+"ReaderAdapter"+classSuffix;
	}
	/** Get the name of the AdapterFactory class name */
	public static  String getAdapterFactoryClass(String modelName) {
		return toUpperCaseFirst(modelName)+"FactoryAdapter"+classSuffix;
	}
	/** Get the name of the XMI visitor interface */
	public static  String getXMIVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"XMIVisitor";
	}
	/** Get the name of the XNMI visitor class */
	public static  String getXMIVisitorClass(String modelName) {
		return toUpperCaseFirst(modelName)+"XMIVisitor"+classSuffix;
	}
	/** Get the name of the view/edit frame class */
	public static  String getViewEditFrameClass(String modelName) {
		return toUpperCaseFirst(modelName)+"ViewEditFrame"+classSuffix;
	}
	/** Get the name of the invoke class name */
	public static  String getInvokeMethodClass(String modelName) {
		return toUpperCaseFirst(modelName)+"InvokeMethod"+classSuffix;
	}
	/** Get the name of the View visitor interface */
	public static  String getViewVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"ViewVisitor";
	}
	/** Get the name of the View visitor class */
	public static  String getViewVisitorClass(String modelName) {
		return toUpperCaseFirst(modelName)+"ViewVisitor"+classSuffix;
	}
	/** Get the name of the Edit visitor interface */
	public static  String getEditVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"EditVisitor";
	}
	/** Get the name of the Edit visitor class */
	public static  String getEditVisitorClass(String modelName) {
		return toUpperCaseFirst(modelName)+"EditVisitor"+classSuffix;
	}
	/** Get the name of the Lifecycle interface */
	public static  String getLifecycleInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"Lifecycle";
	}
	/** Get the name of the Lifecycle visitor class */
	public static  String getLifecycleClass(String modelName) {
		return toUpperCaseFirst(modelName)+"Lifecycle"+classSuffix;
	}
	/** Get the name of the Browser visitor interface */
	public static  String getBrowserInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"Browser";
	}
	/** Get the name of the Browser visitor class */
	public static  String getBrowserClass(String modelName) {
		return toUpperCaseFirst(modelName)+"Browser"+classSuffix;
	}
	/** Get the name of the Startup interface */
	public static  String getStartupInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"Startup";
	}
	/** Get the name of the Startup class */
	public static  String getStartupClass(String modelName) {
		return toUpperCaseFirst(modelName)+"Startup"+classSuffix;
	}

	/** Check if a stereotype is of a specific kind */
	public static  boolean isStereotype(ModelElement elem, String kind) {
		if (elem == null) return false;
		Set stereoSet = elem.getStereotype();
		if (stereoSet == null) return false;
		Iterator it = stereoSet.iterator();
		while (it.hasNext()) {
			Stereotype stereo = (Stereotype)it.next();
			Name name = stereo.getName();
			if (name == null) return false;
			if (name.getBody_().equals(kind)) return true;
		}
		return false;
	}
	/** Check if an association end is collection */
	public static  boolean isCollection(AssociationEnd aend) {
		if (aend.getMultiplicity()!=null) {
			MultiplicityRange range = (MultiplicityRange)aend.getMultiplicity().getRange().iterator().next();
			int lower = range.getLower().intValue();
			int upper = range.getUpper().intValue();
			// Upper = *
			if (upper == -1) return true;
			// Optional element
			if (lower == 0 && upper == 1) return false;
			if (lower < upper) return true;
			if (lower == upper && lower > 1) return true;
		}
		return false; 
	}
	/** Check if an attribute is a collection */
	public static  boolean isCollection(Attribute attrib) {
		// Check mutiplicity
		if (attrib.getMultiplicity()!=null) {
			Iterator i = attrib.getMultiplicity().getRange().iterator();
			if (i.hasNext()) {
				MultiplicityRange range = (MultiplicityRange)i.next();
				int lower = range.getLower().intValue();
				int upper = range.getUpper().intValue();
				// Upper = *
				if (upper == -1) return true;
				// Optional element
				if (lower == 0 && upper == 1) ;
				else if (lower < upper) return true;
				else if (lower == upper && lower > 1) return true;
			}
		}
		if (attrib.getType() == null) return false;
		String colls = "Set Sequence Bag MutableSet MutableSequence MutableBag Collection List Set";
		String typeName = attrib.getType().getName().getBody_();
		return (colls.indexOf(typeName) != -1); 
	}
	/** Check if an association end is an aggregation */
	public static  boolean isAggregate(AssociationEnd aend) {
		return aend.getAggregation() == AggregationKind$Class.AGGREGATE;
	}
	/** Check if an association end is a composition */
	public static  boolean isComposite(AssociationEnd aend) {
		return aend.getAggregation() == AggregationKind$Class.COMPOSITE;
	}
	/** Check if an association end is navigable */
	public static  boolean isNavigable(AssociationEnd aend) {
		return aend.getIsNavigable().booleanValue();
	}
	/** Check if a feature is public static */
	public static  boolean isPublic(Feature feat) {
		return feat.getVisibility() == VisibilityKind$Class.PUBLIC;
	}
	/** Check if a feature is private */
	public static  boolean isPrivate(Feature feat) {
		return feat.getVisibility() == VisibilityKind$Class.PRIVATE;
	}
	/** Check if a feature is protected */
	public static  boolean isProtected(Feature feat) {
		return feat.getVisibility() == VisibilityKind$Class.PROTECTED;
	}
	/** Check if a feature is package visible */
	public static  boolean isPackage(Feature feat) {
		return feat.getVisibility() == VisibilityKind$Class.PACKAGE;
	}

	/** Java keywords */
    protected static Set keywords = new HashSet();
    static {
    	// Java keywords
    	keywords.add("abstract"); keywords.add("boolean"); keywords.add("break"); keywords.add("byte");
    	keywords.add("case"); keywords.add("catch"); keywords.add("char"); keywords.add("class");
    	keywords.add("const"); keywords.add("continue"); keywords.add("default"); keywords.add("do");
    	keywords.add("double"); keywords.add("else"); keywords.add("extends"); keywords.add("final");
    	keywords.add("finally"); keywords.add("float");	keywords.add("for"); keywords.add("goto");
    	keywords.add("if");	keywords.add("implements"); keywords.add("import"); keywords.add("instanceof");
    	keywords.add("int"); keywords.add("interface"); keywords.add("long"); keywords.add("native");
    	keywords.add("new"); keywords.add("package"); keywords.add("private"); keywords.add("protected");
    	keywords.add("public"); keywords.add("return"); keywords.add("short"); keywords.add("public"); keywords.add("static");
    	keywords.add("strictfp"); keywords.add("super"); keywords.add("switch"); keywords.add("synchronized");
    	keywords.add("this"); keywords.add("throw"); keywords.add("throws"); keywords.add("transient");
    	keywords.add("try"); keywords.add("void"); keywords.add("volatile"); keywords.add("while");
    	// Java types
    	keywords.add("Object");
    	keywords.add("Enumeration");
    	keywords.add("Integer");
    	keywords.add("Boolean");
    	keywords.add("Double");
    	keywords.add("String");
    	keywords.add("Collection");
    	keywords.add("Set");
    	keywords.add("Class");
		// CS keywords
		keywords.add("namespace");
		keywords.add("internal");
    }
    public static boolean isKeyword(String name) {
    	return keywords.contains(name);
    }
    
	/** Clean the name */
    public static boolean isAlpha(char ch) {
    	if ('a' <= ch && ch <= 'z') return true;
    	if ('A' <= ch && ch <= 'Z') return true;
    	if ('0' <= ch && ch <= '9') return true;
    	if (ch == '_') return true;
    	return false;
    }
    public static String getCleanName(String name) {
    	String res = new String();
    	name = name.trim();
    	for(int i=0; i<name.length(); i++) {
    		char ch = name.charAt(i);
    		if (ch == '\t' || ch == '\n' || ch == '\r' || ch == '/') ;
    		else if (isAlpha(ch) || ch == '.') res += ch;
    		else res += '_';
    	}
    	// Get last name
    	int pos = res.lastIndexOf(".");
    	String lastName = res.substring(pos+1, res.length());
    	if (isKeyword(lastName)) lastName = lastName+"_";
    	return res.substring(0, pos+1)+lastName; 
    }
        
    /** Get the accesor name */
    public static String getGetter(String name) {
    	if (name.length() == 0) return "get_";
    	return "get"+name.substring(0,1).toUpperCase()+name.substring(1, name.length());
    }
    /** Get the mutator name */
    public static String getSetter(String name) {
    	if (name.length() == 0) return "set_";
    	return "set"+name.substring(0,1).toUpperCase()+name.substring(1, name.length());
    }
 
	/** UML structure */
    protected static Map UMLPath = new HashMap();
    static {
    	// Behavioral elements
    	// State Machines
    	UMLPath.put("Action", "Behavioral Elements.State Machines");
    	UMLPath.put("Guard", "Behavioral Elements.State Machines");
    	UMLPath.put("Pseudostate", "Behavioral Elements.State Machines");
    	UMLPath.put("State", "Behavioral Elements.State Machines");
    	UMLPath.put("StateMachine", "Behavioral Elements.State Machines");
    	UMLPath.put("StateVertex", "Behavioral Elements.State Machines");
    	UMLPath.put("StubState", "Behavioral Elements.State Machines");
    	UMLPath.put("SynchState", "Behavioral Elements.State Machines");
    	UMLPath.put("Transition", "Behavioral Elements.State Machines");
		UMLPath.put("CallEvent", "Behavioral Elements.State Machines");
		UMLPath.put("CallEvent", "Behavioral Elements.State Machines");
		UMLPath.put("CompositeState", "Behavioral Elements.State Machines");
    	// Foundation
    	// FoundationCore
    	UMLPath.put("Abstraction", "Foundation.Core");
    	UMLPath.put("Association", "Foundation.Core");
    	UMLPath.put("AssociationClass", "Foundation.Core");
    	UMLPath.put("AssociationEnd", "Foundation.Core");
    	UMLPath.put("Attribute", "Foundation.Core");
    	UMLPath.put("BehavioralFeature", "Foundation.Core");
    	UMLPath.put("Bindind", "Foundation.Core");
    	UMLPath.put("CallAction", "Foundation.Core");
    	UMLPath.put("Class", "Foundation.Core");
    	UMLPath.put("Classifier", "Foundation.Core");
    	UMLPath.put("Comment", "Foundation.Core");
    	UMLPath.put("Component", "Foundation.Core");
    	UMLPath.put("Constraint", "Foundation.Core");
    	UMLPath.put("DataType", "Foundation.Core");
    	UMLPath.put("Dependency", "Foundation.Core");
    	UMLPath.put("Element", "Foundation.Core");
    	UMLPath.put("EnumLiteral", "Foundation.Core");
    	UMLPath.put("Enumeration", "Foundation.Core");
    	UMLPath.put("Feature", "Foundation.Core");
    	UMLPath.put("Flow", "Foundation.Core");
    	UMLPath.put("GeneralizableElement", "Foundation.Core");
    	UMLPath.put("Generalization", "Foundation.Core");
    	UMLPath.put("Interface", "Foundation.Core");
    	UMLPath.put("Method", "Foundation.Core");
    	UMLPath.put("ModelElement", "Foundation.Core");
    	UMLPath.put("Namespace", "Foundation.Core");
    	UMLPath.put("Node", "Foundation.Core");
    	UMLPath.put("Operation", "Foundation.Core");
    	UMLPath.put("Parameter", "Foundation.Core");
    	UMLPath.put("Permission", "Foundation.Core");
    	UMLPath.put("PresentationElement", "Foundation.Core");
    	UMLPath.put("Primitive", "Foundation.Core");
    	UMLPath.put("Relationship", "Foundation.Core");
    	UMLPath.put("SendAction", "Foundation.Core");
    	UMLPath.put("Signal", "Foundation.Core");
    	UMLPath.put("StructuralFeature", "Foundation.Core");
    	UMLPath.put("TemplateParameter", "Foundation.Core");
    	UMLPath.put("Tie", "Foundation.Core");
    	UMLPath.put("Usage", "Foundation.Core");
    	// Data types
    	UMLPath.put("ActionExpression", "Foundation.Data Types");
    	UMLPath.put("AggregationKind", "Foundation.Data Types");
    	UMLPath.put("ArgListsExpression", "Foundation.Data Types");
    	UMLPath.put("Boolean", "Foundation.Data Types");
    	UMLPath.put("BooleanExpression", "Foundation.Data Types");
    	UMLPath.put("CallConcurrencyKind", "Foundation.Data Types");
    	UMLPath.put("ChangebilityKind", "Foundation.Data Types");
    	UMLPath.put("Expression", "Foundation.Data Types");
    	UMLPath.put("Geometry", "Foundation.Data Types");
    	UMLPath.put("Integer", "Foundation.Data Types");
    	UMLPath.put("IterationExpression", "Foundation.Data Types");
    	UMLPath.put("LocationReference", "Foundation.Data Types");
    	UMLPath.put("MappingExpression", "Foundation.Data Types");
    	UMLPath.put("MessageDirectionKind", "Foundation.Data Types");
    	UMLPath.put("Multiplicity", "Foundation.Data Types");
    	UMLPath.put("MultiplicityRange", "Foundation.Data Types");
    	UMLPath.put("Name", "Foundation.Data Types");
    	UMLPath.put("ObjectSetExpression", "Foundation.Data Types");
    	UMLPath.put("OrderingKind", "Foundation.Data Types");
    	UMLPath.put("ParameterDirectionKind", "Foundation.Data Types");
    	UMLPath.put("ProcedureExpression", "Foundation.Data Types");
    	UMLPath.put("PseudostateKind", "Foundation.Data Types");
    	UMLPath.put("ScopeKind", "Foundation.Data Types");
    	UMLPath.put("String", "Foundation.Data Types");
    	UMLPath.put("Time", "Foundation.Data Types");
    	UMLPath.put("TimeExpression", "Foundation.Data Types");
    	UMLPath.put("TypeExpression", "Foundation.Data Types");
    	UMLPath.put("UnlimitedInteger", "Foundation.Data Types");
    	UMLPath.put("VisibilityKind", "Foundation.Data Types");   	
    	// Extension Mechanisms
    	UMLPath.put("Stereotype", "Foundation.Extension Mechanisms");   	
    	UMLPath.put("StringArray", "Foundation.Extension Mechanisms");   	
    	UMLPath.put("TagDefinition", "Foundation.Extension Mechanisms");   	
    	UMLPath.put("TaggedValue", "Foundation.Extension Mechanisms");
    	// Model Management
    	UMLPath.put("Model", "Model Management");
    	UMLPath.put("Package", "Model Management");
    	UMLPath.put("Subsystem", "Model Management");    	
    }
    public static String getUMLPathName(String name) {
    	String pathName = (String)UMLPath.get(name);
    	return pathName == null ? name : pathName+"."+name;
    }
}
