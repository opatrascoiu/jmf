package uk.ac.kent.cs.kmf.kmfstudio;

import java.io.*;
import java.util.*;
import java.text.*;

import uk.ac.kent.cs.kmf.util.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;

/**
 * @author Octavian Patrascoiu
 *
 */
public class NamingImpl 
	implements Naming
{
	/** Constructor */
	public NamingImpl(Project project, ILog log) {
		// Compute local properties
		this.project = project;
		this.model = project.getModel(log);
		this.log = log;
		// Compute the offset
		rootOffset = project.getPackageOffset();
		if (rootOffset.length()!=0 && !rootOffset.endsWith(".")) rootOffset += ".";
		// Compute model name and model package
		if (project.getModelName().length() !=0 ) 
			model.getName().setBody_(project.getModelName()); 
		modelName = getPackageName((Package)model);
		modelPackage = rootOffset+modelName;
		// Suffixes and prefixes
		interfacePrefix = project.getInterfacePrefix();
		interfaceSuffix = project.getInterfaceSuffix();
		classPrefix = project.getClassPrefix();
		classSuffix = project.getClassSuffix();
		listInterface = project.getListInterface();
		listClass = project.getListClass();
		setInterface = project.getSetInterface();
		setClass = project.getSetClass();
	}
	
	/** Get the name of a ModelElement */
	public String getName(ModelElement element) {
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
	public String getModelName() {
		return modelName;
	}
	/** Get rootoffset */
	public String getRootOffset() {
		return rootOffset;
	}
	/** Set rootoffset */
	public void setRootOffset(String string) {
		rootOffset = string;
	}
	/** Get the model package */
	public String getModelPackage() {
		return modelPackage;
	}

	/** Get package name */
	public String getPackageName(Package pkg) {
		return getCleanName(pkg.getName().getBody_());
	}
	/** Get full package name */
	public String getFullPackageName(Package pkg) {
		if (pkg.getNamespace_() == null) return rootOffset+getPackageName(pkg);
		if (pkg.getNamespace_().getName().getBody_().equals("null")) return rootOffset+getPackageName(pkg);
		String pre = getFullPackageName((Package)pkg.getNamespace_());
		return pre+"."+getPackageName(pkg);
	}
    
	/** Get classifier name */
	public String getClassifierName(Classifier cls) {
		if (cls == null) {
			log.reportError("'null' classifier found");
			return "nullClassifier";
		}
		String name = cls.getName().getBody_();
		if (name.length() >= 1)
			name = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
		if (isStereotype(cls, "primitive")) return name;
		if (!name.endsWith("}")) name = getCleanName(name);
		return name;
	}
	/** Get classifier name */
	public String getClassifierName(Classifier cls, Classifier dst) {
		if (cls == null) {
			log.reportError("'null' classifier found");
			return "nullClassifier";
		}
		String name = cls.getName().getBody_();
		if (name.length() >= 1)
			name = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
		if (isStereotype(cls, "primitive")) return name;
		if (!name.endsWith("}")) name = getCleanName(name);
		if (!inSamePackage(cls, dst)) name = getFullPackageName(cls)+"."+name;
		return name;
	}
	/** Get full classifier name */
	public String getFullClassifierName(Classifier cls) {
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
	public String getFullPackageName(Classifier cls) {
		String name = getFullClassifierName(cls);
		int pos = name.lastIndexOf('.');
		if (pos != -1) name = name.substring(0, pos);
		return name;
	}
	/** Get interface name of a class */
	public String getInterfaceName(Classifier cls) {
		return getInterfaceName(getClassifierName(cls));
	}
	public String getInterfaceName(String modelName) {
		return interfacePrefix+getCleanName(modelName)+interfaceSuffix;
	}
	/** Get full interface name of a class */
	public String getFullInterfaceName(Classifier cls) {
		return getFullPackageName(cls)+"."+getInterfaceName(cls);
	}
	/** Get class name of a class */
	public String getClassName(Classifier cls) {
		return classPrefix+getClassifierName(cls)+classSuffix;
	}
	/** Get class name of a class */
	public String getClassName(Classifier cls, Classifier src) {
		return classPrefix+getClassifierName(cls, src)+classSuffix;
	}
	/** Get class name of a class */
	public String getClassName(String clsName) {
		return classPrefix+getCleanName(clsName)+classSuffix;
	}
	/** Get full class name of a class */
	public String getFullClassName(Classifier cls) {
		return getFullPackageName(cls)+"."+getClassName(cls);
	}
	/** Get factory interface name of a class */
	public String getFactoryInterfaceName(Classifier cls) {
		return interfacePrefix+getClassifierName(cls)+"Factory"+interfaceSuffix;
	}
	/** Get full factory interface name of a class */
	public String getFullFactoryInterfaceName(Classifier cls) {
		return getFullPackageName(cls)+"."+getFactoryInterfaceName(cls);
	}
	/** Get factory class name of a class */
	public String getFactoryClassName(Classifier cls) {
		return classPrefix+getClassifierName(cls)+"Factory"+classSuffix;
	}
	/** Get full factory class name of a class */
	public String getFullFactoryClassName(Classifier cls) {
		return getFullPackageName(cls)+"."+getFactoryClassName(cls);
	}
	/** Get visitor interface name of a class */
	public String getVisitorInterfaceName(Classifier cls) {
		return interfacePrefix+getClassifierName(cls)+"Visitor"+interfaceSuffix;
	}
	/** Get full visitor interface name of a class */
	public String getFullVisitorInterfaceName(Classifier cls) {
		return getFullPackageName(cls)+"."+getVisitorInterfaceName(cls);
	}
	/** Get visitor interface name of a factory */
	public String getFactoryVisitorInterfaceName(Classifier cls) {
		return interfacePrefix+getFactoryInterfaceName(cls)+"Visitor"+interfaceSuffix;
	}
	/** Get full visitor interface name of a factory */
	public String getFullFactoryVisitorInterfaceName(Classifier cls) {
		return getFullPackageName(cls)+"."+getFactoryVisitorInterfaceName(cls);
	}
    
	public String removeOffset(String packageOffset, String interfaceName) {
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
	public String getPropertyName(Attribute attrib) {
		String name = attrib.getName().getBody_();
		return getCleanName(name);
	}
	/** Get property name for an association end */
	public String getPropertyName(AssociationEnd aend) {
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
		return getCleanName(name);
	}
	/** Get property type for an attribute */
	public String getPropertyType(Attribute attrib, Classifier src, Classifier dst) {
		if (attrib.getType() != null) {
			// Get model type name and Java mapping
			String modelTypeName = attrib.getType().getName().getBody_();
			String javaTypeName = JavaType.getJavaType(modelTypeName);
			// Simple type
			if (!isCollection(attrib)) {
				if (javaTypeName != null) 
					return javaTypeName;
				else if (!inSamePackage(src, dst)) 
					return getFullPackageName(src)+"."+getInterfaceName(modelTypeName);
				else 
					return getInterfaceName(modelTypeName);
			// Collection - attrib has multiplicity
			} else if (attrib.getMultiplicity()!=null) {
				if (attrib.getOrdering() == OrderingKind$Class.ORDERED)
					return listInterface;
				else
					return setInterface;
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
	public String getPropertyFullType(Attribute attrib, Classifier src) {
		if (attrib.getType() != null) {
			// Get model type name and Java mapping
			String modelTypeName = attrib.getType().getName().getBody_();
			String javaTypeName = JavaType.getJavaType(modelTypeName);
			// Simple type
			if (!isCollection(attrib)) {
				if (javaTypeName != null) 
					return javaTypeName;
				else
					return getFullPackageName(attrib.getType())+"."+getInterfaceName(modelTypeName);
			// Collection - attrib has multiplicity
			} else if (attrib.getMultiplicity()!=null) {
				if (attrib.getOrdering() == OrderingKind$Class.ORDERED)
					return listInterface;
				else
					return setInterface;
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
	public String getPropertyType(AssociationEnd aend, Classifier src, Classifier dst) {
		if (aend != null) {
			if (!isCollection(aend)) {
				String typeName = getClassifierName(aend.getType());
				if (!inSamePackage(src, dst)) 
					return getFullPackageName(src)+"."+getInterfaceName(typeName);
				return 
					getInterfaceName(typeName);
			} else {
				if (aend.getOrdering() == OrderingKind$Class.ORDERED)
					return listInterface;
				else
					return setInterface;
			}
		} else {
			log.reportError("The property '"+getPropertyName(aend)+"' from '"+getClassifierName(dst)+"' should have a type");
			return "";
		}
	}
	/** Get full property type for an association end */
	public String getPropertyFullType(AssociationEnd aend, Classifier src) {
		if (aend != null) {
			if (!isCollection(aend)) {
				return getFullPackageName(src)+"."+getClassifierName(aend.getType());
			} else {
				if (aend.getOrdering() == OrderingKind$Class.ORDERED)
					return listInterface;
				else
					return setInterface;
			}
		} else {
			log.reportError("The property '"+getPropertyName(aend)+"' from '"+getClassifierName(src)+"' should have a type");
			return "";
		}
	}
	/** Get property default value for an attribute */
	public String getPropertyDefaultValue(Attribute attrib) {
		if (attrib.getType() == null) return "null";
		String modelTypeName = getClassifierName(attrib.getType());
		String javaDefault = JavaFactory.createString(modelTypeName);
		if (javaDefault == null) 
			return "null";
		else
			return javaDefault;
	}
	/** Get property default value for an association end */
	public String getPropertyDefaultValue(AssociationEnd aend) {
		if (aend == null) return "null";
		if (isCollection(aend))
			if (aend.getOrdering() == OrderingKind$Class.ORDERED) 
				return  "new "+listClass+"()";
			else 
				return "new "+setClass+"()";
		else
			return "null";
	}
	/** Get Java type name - used by operation */
	public String getTypeName(Classifier type, Classifier dst) {
		if (type != null) {
			String modelTypeName = type.getName().getBody_();
			String javaTypeName = JavaType.getJavaType(modelTypeName);
			if (javaTypeName != null) 
				return javaTypeName;
			else if (modelTypeName.endsWith("}")) 
				return getModelName()+"Enum";
			else if (!inSamePackage(type, dst)) 
				return getFullPackageName(type)+"."+getInterfaceName(modelTypeName);
			else
				return getInterfaceName(modelTypeName);
		} else {
			return "";
		}
	}    	
	/** Get full Java type name - used by operation */
	public String getFullTypeName(Classifier type) {
		if (type != null) {
			String modelTypeName = getClassifierName(type);
			String javaTypeName = JavaType.getJavaType(modelTypeName);
			if (javaTypeName != null) 
				return javaTypeName;
			else if (modelTypeName.endsWith("}")) 
				return getModelPackage()+"."+getModelName()+"Enum";
			else
				return getFullPackageName(type)+"."+getInterfaceName(modelTypeName);
		} else {
			return "";
		}
	}    	
	/** Get return type name for an operation */
	public String getReturnTypeName(Operation op, Classifier dst) {
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
	public String getOperationName(Operation op) {
		if (op == null) {
			log.reportError("'null' operation found");
			return "nullOperation";
		}
		String name = op.getName().getBody_();
		return getCleanName(name);
	}
	/** Get parameter name */
	public String getParameterName(Parameter par) {
		if (par == null) {
			log.reportError("'null' parameter found");
			return "nullOperation";
		}
		String name = par.getName().getBody_();
		return getCleanName(name);
	}
	/** Get names of parameters */
	public List getParamNames(Operation op) {
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
	public List getParamQuotedNames(Operation op) {
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
	public List getParamTypeNames(Operation op, Classifier dst) {
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
	public List getParamFullTypeNames(Operation op, Classifier dst) {
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
	public String getConstraintName(Constraint c) {
		String name = getCleanName(c.getName().getBody_());
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
	/* Compute all super classes of a class, including class */
	public List allSuperClasses(GeneralizableElement cls, boolean report) {
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
	/** Compute all subclasses of a class including class */
	public List allSubClasses(GeneralizableElement cls) {
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
				Iterator it = crtCls.getSpecialization().iterator();
				while (it.hasNext()) {
					Generalization gen = (Generalization)it.next();
					Object child = gen.getChild();
					if (child != null) {
						if (!classes.contains(child)) { 
							classes.add(child);
							last++;
						} else {
						}
					}
				}
			}
		}
		return classes;
	}
	/** Get attribute names */
	public Set getAttributeNames(List classes) {
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
	public Set getAssociationNames(List classes, Set associations) {
		Set names = new LinkedHashSet();
		Iterator itCls = classes.iterator();
		while (itCls.hasNext()) {
			Classifier cls = (Classifier)itCls.next();
			Iterator itAssoc = associations.iterator();
			while (itAssoc.hasNext()) {
				Association assoc = (Association)itAssoc.next();
				AssociationEnd aend1 = (AssociationEnd)assoc.getConnection().get(0);
				AssociationEnd aend2 = (AssociationEnd)assoc.getConnection().get(1);
				if (add(aend1, aend2, cls)) {
					String assocName = getPropertyName(aend2);
					names.add(assocName);
				}
				if (add(aend2, aend1, cls)) {
					String assocName = getPropertyName(aend1);
					names.add(assocName);
				}
			}
		}
		return names;
	}

	/** Check if an assocition end should be added or not */
	public boolean add(AssociationEnd aend1, AssociationEnd aend2, Classifier cls) {
		if (aend1.getType() == cls && (isNavigable(aend2) || project.isGenerateBidirectional()))
			return true;
		else
			return false;
	}

	/** Get the name of the root package */
	public String getRootName(String name) {
		int pos = name.indexOf('.');
		if (pos == -1) return name;
		else return name.substring(0, pos);
	}
	/** Check if two classifiers are in the same package */
	public boolean inSamePackage(Classifier src, Classifier dst) {
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
	public String toList(List vect, String separator) {
		String res = new String();
		for(int i=0; i<vect.size(); i++) {
			if (i!=0) res += separator;
			res += vect.get(i);
		}
		return res;
	}
	/** Convert a vector into a quoted list using a separator */
	public String toListQuote(List vect, String separator) {
		String res = "";
		for(int i=0; i<vect.size(); i++) {
			if (i!=0) res += separator;
			res += "\""+vect.get(i)+"\"";
		}
		return res;
	}
	/** Print time stamp on the generated files */
	public void putStamp(String clsName, PrintWriter out) {
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
	protected  String toUpperCaseFirst(String name) {
		if (name.length() >= 1)
			name = name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
		return name;
	}

	/** Top level visitor interface name */
	public String getVisitorInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"Visitor"+interfaceSuffix;
	}
	/** Top level visitor interface name */
	public String getFullVisitorInterface(String modelName) {
		return getModelPackage()+"."+getVisitorInterface(modelName);
	}
	/** Top level visitor class name */
	public String getVisitorClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"Visitor"+classSuffix;
	}
	/** Top level visitor class name */
	public String getFullVisitorClass(String modelName) {
		return getModelPackage()+"."+getVisitorClass(modelName);
	}
	/** Top level visitable interface name */
	public String getVisitableInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"Visitable"+interfaceSuffix;
	}
	/** Top level visitable interface name */
	public String getFullVisitableInterface(String modelName) {
		return getModelPackage()+"."+getVisitableInterface(modelName);
	}
	/** Top level parseAll visitor interface name */
	public String getParseAllVisitorInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"ParseAllVisitor"+interfaceSuffix;
	}
	/** Top level parseAll visitor interface class */
	public String getParseAllVisitorClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"ParseAllVisitor"+classSuffix;
	}
	/** Top level analyseAll visitor interface name */
	public String getAnalyseAllVisitorInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"AnalyseAllVisitor"+interfaceSuffix;
	}
	/** Top level analyseAll visitor interface class */
	public String getAnalyseAllVisitorClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"AnalyseAllVisitor"+classSuffix;
	}
	/** Top level evaluateAll visitor interface name */
	public String getEvaluateAllVisitorInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"EvaluateAllVisitor"+interfaceSuffix;
	}
	/** Top level analyseAll visitor interface class */
	public String getEvaluateAllVisitorClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"EvaluateAllVisitor"+classSuffix;
	}
	/** Get the name of the factory interface */
	public String getFactoryInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"Factory"+interfaceSuffix;
	}
	/** Get the name of the factory interface */
	public String getFullFactoryInterface(String modelName) {
		return getModelPackage()+"."+getFactoryInterface(modelName);
	}
	/** Get the name of the factory class */
	public String getFactoryClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"Factory"+classSuffix;
	}
	/** Get the name of the factory interface */
	public String getFullFactoryClass(String modelName) {
		return getModelPackage()+"."+getFactoryClass(modelName);
	}
	/** Get the name of the repository interface */
	public String getRepositoryInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"Repository"+interfaceSuffix;
	}
	/** Get the name of the repository interface */
	public String getFullRepositoryInterface(String modelName) {
		return getModelPackage()+".repository."+getRepositoryInterface(modelName);
	}
	/** Get the name of the repository class */
	public String getRepositoryClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"Repository"+classSuffix;
	}
	/** Get the name of the repository class */
	public String getFullRepositoryClass(String modelName) {
		return getModelPackage()+".repository."+getRepositoryClass(modelName);
	}
	/** Get the name of the top level element interface */
	public String getElementInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"Element"+interfaceSuffix;
	}
	/** Get the name of the top level element class */
	public String getElementClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"Element"+classSuffix;
	}
	/** Get the name of the top level element interface */
	public String getFullElementInterface(String modelName) {
		return getModelPackage()+"."+getElementInterface(modelName);
	}
	/** Get the name of the top level element class */
	public String getFullElementClass(String modelName) {
		return getModelPackage()+"."+getElementClass(modelName);
	}
	/** Get the name of the ocl evaluator class */
	public String getOCLClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"OCLEvaluator"+classSuffix;
	}
	/** Get the name of the JTree visitor interface */
	public String getJTreeVisitorInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"JTreeVisitor"+interfaceSuffix;
	}
	/** Get the name of the JTree visitor class */
	public String getJTreeVisitorClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"JTreeVisitor"+classSuffix;
	}
	/** Get the name of the HUTN visitor interface */
	public String getHUTNVisitorInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"HUTNVisitor"+interfaceSuffix;
	}
	/** Get the name of the HUTN visitor class */
	public String getHUTNVisitorClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"HUTNVisitor"+classSuffix;
	}
	/** Get the name of the ReaderAdapter class name */
	public String getReaderAdapterClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"ReaderAdapter"+classSuffix;
	}
	/** Get the name of the AdapterFactory class name */
	public String getAdapterFactoryClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"FactoryAdapter"+classSuffix;
	}
	/** Get the name of the XMI visitor interface */
	public String getXMIVisitorInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"XMIVisitor";
	}
	/** Get the name of the XNMI visitor class */
	public String getXMIVisitorClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"XMIVisitor"+classSuffix;
	}
	/** Get the name of the view/edit frame class */
	public String getViewEditFrameClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"ViewEditFrame"+classSuffix;
	}
	/** Get the name of the invoke class name */
	public String getInvokeMethodClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"InvokeMethod"+classSuffix;
	}
	/** Get the name of the View visitor interface */
	public String getViewVisitorInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"ViewVisitor"+interfaceSuffix;
	}
	/** Get the name of the View visitor class */
	public String getViewVisitorClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"ViewVisitor"+classSuffix;
	}
	/** Get the name of the Edit visitor interface */
	public String getEditVisitorInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"EditVisitor"+interfaceSuffix;
	}
	/** Get the name of the Edit visitor class */
	public String getEditVisitorClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"EditVisitor"+classSuffix;
	}
	/** Get the name of the Lifecycle interface */
	public String getLifecycleInterface(String modelName) {
		return toUpperCaseFirst(modelName)+"Lifecycle";
	}
	/** Get the name of the Lifecycle visitor class */
	public String getLifecycleClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"Lifecycle"+classSuffix;
	}
	/** Get the name of the Browser visitor interface */
	public String getBrowserInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"Browser"+interfaceSuffix;
	}
	/** Get the name of the Browser visitor interface */
	public String getFullBrowserInterface(String modelName) {
		return getModelPackage()+".repository."+getBrowserInterface(modelName);
	}
	/** Get the name of the Browser visitor class */
	public String getBrowserClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"Browser"+classSuffix;
	}
	/** Get the name of the Browser visitor class */
	public String getFullBrowserClass(String modelName) {
		return getModelPackage()+".repository."+getBrowserClass(modelName);
	}
	/** Get the name of the Startup interface */
	public String getStartupInterface(String modelName) {
		return interfacePrefix+toUpperCaseFirst(modelName)+"Startup"+interfaceSuffix;
	}
	/** Get the name of the Startup class */
	public String getStartupClass(String modelName) {
		return classPrefix+toUpperCaseFirst(modelName)+"Startup"+classSuffix;
	}

	/** Check if a stereotype is of a specific kind */
	public boolean isStereotype(ModelElement elem, String kind) {
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
	public boolean isCollection(AssociationEnd aend) {
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
	public boolean isCollection(Attribute attrib) {
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
		String colls = "Collection List Set java.util.Collection java.util.List java.util.Set";
		String typeName = attrib.getType().getName().getBody_();
		return (colls.indexOf(typeName) != -1); 
	}
	/** Check if an association end is an aggregation */
	public boolean isAggregate(AssociationEnd aend) {
		return aend.getAggregation() == AggregationKind$Class.AGGREGATE;
	}
	/** Check if an association end is a composition */
	public boolean isComposite(AssociationEnd aend) {
		return aend.getAggregation() == AggregationKind$Class.COMPOSITE;
	}
	/** Check if an association end is navigable */
	public boolean isNavigable(AssociationEnd aend) {
		return aend.getIsNavigable().booleanValue();
	}
	/** Check if a feature is public */
	public boolean isPublic(Feature feat) {
		return feat.getVisibility() == VisibilityKind$Class.PUBLIC;
	}
	/** Check if a feature is private */
	public boolean isPrivate(Feature feat) {
		return feat.getVisibility() == VisibilityKind$Class.PRIVATE;
	}
	/** Check if a feature is protected */
	public boolean isProtected(Feature feat) {
		return feat.getVisibility() == VisibilityKind$Class.PROTECTED;
	}
	/** Check if a feature is package visible */
	public boolean isPackage(Feature feat) {
		return feat.getVisibility() == VisibilityKind$Class.PACKAGE;
	}

	/** Clean the name */
	public String getCleanName(String name) {
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

	/** Check if a character is alphanumeric */  
	public boolean isAlpha(char ch) {
		if ('a' <= ch && ch <= 'z') return true;
		if ('A' <= ch && ch <= 'Z') return true;
		if ('0' <= ch && ch <= '9') return true;
		if (ch == '_') return true;
		return false;
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
	public boolean isKeyword(String name) {
		return keywords.contains(name);
	}

	/** Get the accesor name */
	public String getGetter(String name) {
		if (name.length() == 0) return "get_";
		return "get"+name.substring(0,1).toUpperCase()+name.substring(1, name.length());
	}
	/** Get the mutator name */
	public String getSetter(String name) {
		if (name.length() == 0) return "set_";
		return "set"+name.substring(0,1).toUpperCase()+name.substring(1, name.length());
	}

	/** Collection interface and class */
	public String getCollectionInterface() {
		return project.getCollectionInterface();
	} 
	public String getCollectionClass() {
		return project.getCollectionClass();
	} 
	
	/** List interface and class */
	public String getListInterface() {
		return project.getListInterface();
	} 
	public String getListClass() {
		return project.getListClass();
	} 
	
	/** List interface and class */
	public String getSetInterface() {
		return project.getSetInterface();
	} 
	public String getSetClass() {
		return project.getSetClass();
	} 

	//
	// Local properties
	//
	protected Project project;
	protected ILog log;
	protected Model model;
	protected String modelName;
	protected String modelPackage;
	protected String rootOffset;
	protected String interfacePrefix = "";
	protected String interfaceSuffix = "";
	protected String classPrefix = "";
	protected String classSuffix = "$Class";
	protected String listInterface = "java.util.List";
	protected String listClass = "java.util.Vector";
	protected String setInterface = "java.util.Set";
	protected String setClass = "java.util.LinkedHashSet";
}
