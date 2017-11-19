package uk.ac.kent.cs.kmf.kmfstudio;

import java.io.*;
import java.util.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package;

/**
 * @author Octavian Patrascoiu
 *
 */
public interface Naming {
	/** Get the name of a ModelElement */
	public String getName(ModelElement element);

	/** Get the model name */
	public String getModelName();

	/** Get rootoffset */
	public String getRootOffset();

	/** Set rootoffset */
	public void setRootOffset(String string);

	/** Get the model package */
	public String getModelPackage();

	/** Get package name */
	public String getPackageName(Package pkg);

	/** Get full package name */
	public String getFullPackageName(Package pkg);
    
	/** Get classifier name */
	public String getClassifierName(Classifier cls);

	/** Get classifier name */
	public String getClassifierName(Classifier cls, Classifier dst);

	/** Get full classifier name */
	public String getFullClassifierName(Classifier cls);

	/** Get full package name of a classifier */
	public String getFullPackageName(Classifier cls);

	/** Get interface name of a class */
	public String getInterfaceName(Classifier cls);

	/** Get full interface name of a class */
	public String getFullInterfaceName(Classifier cls);

	/** Get class name of a class */
	public String getClassName(Classifier cls);

	/** Get class name of a class */
	public String getClassName(Classifier cls, Classifier src);

	/** Get class name of a class */
	public String getClassName(String clsName);

	/** Get full class name of a class */
	public String getFullClassName(Classifier cls);

	/** Get factory interface name of a class */
	public String getFactoryInterfaceName(Classifier cls);

	/** Get full factory interface name of a class */
	public String getFullFactoryInterfaceName(Classifier cls);

	/** Get factory class name of a class */
	public String getFactoryClassName(Classifier cls);

	/** Get full factory class name of a class */
	public String getFullFactoryClassName(Classifier cls);

	/** Get visitor interface name of a class */
	public String getVisitorInterfaceName(Classifier cls);

	/** Get full visitor interface name of a class */
	public String getFullVisitorInterfaceName(Classifier cls);

	/** Get visitor interface name of a factory */
	public String getFactoryVisitorInterfaceName(Classifier cls);

	/** Get full visitor interface name of a factory */
	public String getFullFactoryVisitorInterfaceName(Classifier cls);
    
	public String removeOffset(String packageOffset, String interfaceName);
    
	/** Get property name for an attribute */
	public String getPropertyName(Attribute attrib);

	/** Get property name for an association end */
	public String getPropertyName(AssociationEnd aend);

	/** Get property type for an attribute */
	public String getPropertyType(Attribute attrib, Classifier src, Classifier dst);

	/** Get full property type for an attribute */
	public String getPropertyFullType(Attribute attrib, Classifier src);

	/** Get property type for an association end */
	public String getPropertyType(AssociationEnd aend, Classifier src, Classifier dst);

	/** Get full property type for an association end */
	public String getPropertyFullType(AssociationEnd aend, Classifier src);

	/** Get property default value for an attribute */
	public String getPropertyDefaultValue(Attribute attrib);

	/** Get property default value for an association end */
	public String getPropertyDefaultValue(AssociationEnd aend);

	/** Get Java type name - used by operation */
	public String getTypeName(Classifier type, Classifier dst);

	/** Get full Java type name - used by operation */
	public String getFullTypeName(Classifier type);

	/** Get return type name for an operation */
	public String getReturnTypeName(Operation op, Classifier dst);

	/** Get operation name */
	public String getOperationName(Operation op);

	/** Get parameter name */
	public String getParameterName(Parameter par);

	/** Get names of parameters */
	public List getParamNames(Operation op);

	/** Get quoted names of parameters */
	public List getParamQuotedNames(Operation op);

	/** Get types of parameters */
	public List getParamTypeNames(Operation op, Classifier dst);

	/** Get full types of parameters */
	public List getParamFullTypeNames(Operation op, Classifier dst);

	/** Get constraint name */
	public String getConstraintName(Constraint c);

	/**
	  * Auxiliary functions
	  */
	/* Compute all super classes of a class */
	public List allSuperClasses(GeneralizableElement cls, boolean report);
	/** Compute all subclasses of a class including class */
	public List allSubClasses(GeneralizableElement cls);

	/** Get attribute names */
	public Set getAttributeNames(List classes);

	/** Get association end names */
	public Set getAssociationNames(List classes, Set associations);

	/** Get the name of the root package */
	public String getRootName(String name);

	/** Check if two classifiers are in the same package */
	public boolean inSamePackage(Classifier src, Classifier dst);

	/** Convert a vector into a list using a separator */
	public String toList(List vect, String separator);

	/** Convert a vector into a quoted list using a separator */
	public String toListQuote(List vect, String separator);

	/** Print time stamp on the generated files */
	public void putStamp(String clsName, PrintWriter out);

	/** Top level visitor interface name */
	public String getVisitorInterface(String modelName);

	/** Top level visitor interface name */
	public String getFullVisitorInterface(String modelName);

	/** Top level visitor class name */
	public String getVisitorClass(String modelName);

	/** Top level visitor class name */
	public String getFullVisitorClass(String modelName);

	/** Top level visitable interface name */
	public String getVisitableInterface(String modelName);

	/** Top level visitable interface name */
	public String getFullVisitableInterface(String modelName);

	/** Top level parseAll visitor interface name */
	public String getParseAllVisitorInterface(String modelName);

	/** Top level parseAll visitor interface class */
	public String getParseAllVisitorClass(String modelName);

	/** Top level analyseAll visitor interface name */
	public String getAnalyseAllVisitorInterface(String modelName);

	/** Top level analyseAll visitor interface class */
	public String getAnalyseAllVisitorClass(String modelName);

	/** Top level evaluateAll visitor interface name */
	public String getEvaluateAllVisitorInterface(String modelName);

	/** Top level analyseAll visitor interface class */
	public String getEvaluateAllVisitorClass(String modelName);

	/** Get the name of the factory interface */
	public String getFactoryInterface(String modelName);

	/** Get the name of the factory interface */
	public String getFullFactoryInterface(String modelName);

	/** Get the name of the factory class */
	public String getFactoryClass(String modelName);

	/** Get the name of the factory interface */
	public String getFullFactoryClass(String modelName);

	/** Get the name of the repository interface */
	public String getRepositoryInterface(String modelName);

	/** Get the name of the repository interface */
	public String getFullRepositoryInterface(String modelName);

	/** Get the name of the repository class */
	public String getRepositoryClass(String modelName);

	/** Get the name of the repository class */
	public String getFullRepositoryClass(String modelName);

	/** Get the name of the top level element interface */
	public String getElementInterface(String modelName);

	/** Get the name of the top level element class */
	public String getElementClass(String modelName);

	/** Get the name of the top level element interface */
	public String getFullElementInterface(String modelName);

	/** Get the name of the top level element class */
	public String getFullElementClass(String modelName);

	/** Get the name of the ocl evaluator class */
	public String getOCLClass(String modelName);

	/** Get the name of the JTree visitor interface */
	public String getJTreeVisitorInterface(String modelName);

	/** Get the name of the JTree visitor class */
	public String getJTreeVisitorClass(String modelName);

	/** Get the name of the HUTN visitor interface */
	public String getHUTNVisitorInterface(String modelName);

	/** Get the name of the HUTN visitor class */
	public String getHUTNVisitorClass(String modelName);

	/** Get the name of the ReaderAdapter class name */
	public String getReaderAdapterClass(String modelName);

	/** Get the name of the AdapterFactory class name */
	public String getAdapterFactoryClass(String modelName);

	/** Get the name of the XMI visitor interface */
	public String getXMIVisitorInterface(String modelName);

	/** Get the name of the XNMI visitor class */
	public String getXMIVisitorClass(String modelName);

	/** Get the name of the view/edit frame class */
	public String getViewEditFrameClass(String modelName);

	/** Get the name of the invoke class name */
	public String getInvokeMethodClass(String modelName);

	/** Get the name of the View visitor interface */
	public String getViewVisitorInterface(String modelName);

	/** Get the name of the View visitor class */
	public String getViewVisitorClass(String modelName);

	/** Get the name of the Edit visitor interface */
	public String getEditVisitorInterface(String modelName);

	/** Get the name of the Edit visitor class */
	public String getEditVisitorClass(String modelName);

	/** Get the name of the Lifecycle interface */
	public String getLifecycleInterface(String modelName);

	/** Get the name of the Lifecycle visitor class */
	public String getLifecycleClass(String modelName);

	/** Get the name of the Browser visitor interface */
	public String getBrowserInterface(String modelName);

	/** Get the name of the Browser visitor interface */
	public String getFullBrowserInterface(String modelName);

	/** Get the name of the Browser visitor class */
	public String getBrowserClass(String modelName);

	/** Get the name of the Browser visitor class */
	public String getFullBrowserClass(String modelName);

	/** Get the name of the Startup interface */
	public String getStartupInterface(String modelName);

	/** Get the name of the Startup class */
	public String getStartupClass(String modelName);

	/** Check if a stereotype is of a specific kind */
	public boolean isStereotype(ModelElement elem, String kind);

	/** Check if an association end is collection */
	public boolean isCollection(AssociationEnd aend);

	/** Check if an attribute is a collection */
	public boolean isCollection(Attribute attrib);

	/** Check if an association end is an aggregation */
	public boolean isAggregate(AssociationEnd aend);

	/** Check if an association end is a composition */
	public boolean isComposite(AssociationEnd aend);

	/** Check if an association end is navigable */
	public boolean isNavigable(AssociationEnd aend);

	/** Check if a feature is public */
	public boolean isPublic(Feature feat);

	/** Check if a feature is private */
	public boolean isPrivate(Feature feat);

	/** Check if a feature is protected */
	public boolean isProtected(Feature feat);

	/** Check if a feature is package visible */
	public boolean isPackage(Feature feat);

	/** Check if a character is alphanumeric */  
	public boolean isAlpha(char ch);
	
	/** Java keywords */
	public boolean isKeyword(String name);

	/** Check if an assocition end should be added or not */
	public boolean add(AssociationEnd aend1, AssociationEnd aend2, Classifier cls);

	/** Clean the name */
	public String getCleanName(String name);

	/** Collection interface and class */
	public String getCollectionInterface(); 
	public String getCollectionClass(); 

	/** List interface and class */
	public String getListInterface(); 
	public String getListClass(); 

	/** List interface and class */
	public String getSetInterface(); 
	public String getSetClass();

	/** Get getter name */
	public String getGetter(String string); 

	/** Get setter name */
	public String getSetter(String string);
}
