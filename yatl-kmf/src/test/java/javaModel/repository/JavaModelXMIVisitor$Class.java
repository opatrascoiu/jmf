/**
 *
 *  Class JavaModelXMIVisitor$Class.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:04
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel.repository;

import java.util.*;

import uk.ac.kent.cs.kmf.xmi.*;
import uk.ac.kent.cs.kmf.util.*;

import javaModel.*;

public class JavaModelXMIVisitor$Class
	implements JavaModelXMIVisitor
{
	/** Visit factory for 'javaModel.JavaModelFactory' */
	public Object visit(javaModel.JavaModelFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.JavaModelFactory", host);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.JavaClassFactory' */
	public Object visit(javaModel.JavaClassFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.JavaClassFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.JavaClass' */
	public Object visit(javaModel.JavaClass host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.JavaClass", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for JavaClass ---
		//--- Association javaModel.JavaClass.fields ---
		IXMIProperty fieldsIXMIProperty = getProperty("javaModel.JavaClass.fields", host.getFields(), (Map)data);
		xmiObject.add(fieldsIXMIProperty);
		//--- Association javaModel.JavaClass.methods ---
		IXMIProperty methodsIXMIProperty = getProperty("javaModel.JavaClass.methods", host.getMethods(), (Map)data);
		xmiObject.add(methodsIXMIProperty);
		//--- Association javaModel.JavaClass.sources ---
		IXMIProperty sourcesIXMIProperty = getProperty("javaModel.JavaClass.sources", host.getSources(), (Map)data);
		xmiObject.add(sourcesIXMIProperty);
		//--- Association javaModel.JavaClass.implements_ ---
		IXMIProperty implements_IXMIProperty = getProperty("javaModel.JavaClass.implements_", host.getImplements_(), (Map)data);
		xmiObject.add(implements_IXMIProperty);
		//--- Properties for JavaClassifier ---
		//--- Association javaModel.JavaClassifier.sub ---
		IXMIProperty subIXMIProperty = getProperty("javaModel.JavaClassifier.sub", host.getSub(), (Map)data);
		xmiObject.add(subIXMIProperty);
		//--- Association javaModel.JavaClassifier.super_ ---
		IXMIProperty super_IXMIProperty = getProperty("javaModel.JavaClassifier.super_", host.getSuper_(), (Map)data);
		xmiObject.add(super_IXMIProperty);
		//--- Properties for JavaPackageElement ---
		//--- Association javaModel.JavaPackageElement.javaPackage ---
		IXMIProperty javaPackageIXMIProperty = getProperty("javaModel.JavaPackageElement.javaPackage", host.getJavaPackage(), (Map)data);
		xmiObject.add(javaPackageIXMIProperty);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.JavaFieldFactory' */
	public Object visit(javaModel.JavaFieldFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.JavaFieldFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.JavaField' */
	public Object visit(javaModel.JavaField host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.JavaField", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for JavaField ---
		//--- Attribute javaModel.JavaField.isFinal ---
		IXMIProperty isFinalIXMIProperty = getProperty("javaModel.JavaField.isFinal", host.getIsFinal(), (Map)data);
		xmiObject.add(isFinalIXMIProperty);
		//--- Attribute javaModel.JavaField.isStatic ---
		IXMIProperty isStaticIXMIProperty = getProperty("javaModel.JavaField.isStatic", host.getIsStatic(), (Map)data);
		xmiObject.add(isStaticIXMIProperty);
		//--- Attribute javaModel.JavaField.isVolatile ---
		IXMIProperty isVolatileIXMIProperty = getProperty("javaModel.JavaField.isVolatile", host.getIsVolatile(), (Map)data);
		xmiObject.add(isVolatileIXMIProperty);
		//--- Attribute javaModel.JavaField.isTransient ---
		IXMIProperty isTransientIXMIProperty = getProperty("javaModel.JavaField.isTransient", host.getIsTransient(), (Map)data);
		xmiObject.add(isTransientIXMIProperty);
		//--- Association javaModel.JavaField.javaClass ---
		IXMIProperty javaClassIXMIProperty = getProperty("javaModel.JavaField.javaClass", host.getJavaClass(), (Map)data);
		xmiObject.add(javaClassIXMIProperty);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.JavaMethodFactory' */
	public Object visit(javaModel.JavaMethodFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.JavaMethodFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.JavaMethod' */
	public Object visit(javaModel.JavaMethod host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.JavaMethod", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for JavaMethod ---
		//--- Attribute javaModel.JavaMethod.isAbstract ---
		IXMIProperty isAbstractIXMIProperty = getProperty("javaModel.JavaMethod.isAbstract", host.getIsAbstract(), (Map)data);
		xmiObject.add(isAbstractIXMIProperty);
		//--- Attribute javaModel.JavaMethod.isNative ---
		IXMIProperty isNativeIXMIProperty = getProperty("javaModel.JavaMethod.isNative", host.getIsNative(), (Map)data);
		xmiObject.add(isNativeIXMIProperty);
		//--- Attribute javaModel.JavaMethod.isSynchronized ---
		IXMIProperty isSynchronizedIXMIProperty = getProperty("javaModel.JavaMethod.isSynchronized", host.getIsSynchronized(), (Map)data);
		xmiObject.add(isSynchronizedIXMIProperty);
		//--- Attribute javaModel.JavaMethod.isFinal ---
		IXMIProperty isFinalIXMIProperty = getProperty("javaModel.JavaMethod.isFinal", host.getIsFinal(), (Map)data);
		xmiObject.add(isFinalIXMIProperty);
		//--- Attribute javaModel.JavaMethod.isConstructor ---
		IXMIProperty isConstructorIXMIProperty = getProperty("javaModel.JavaMethod.isConstructor", host.getIsConstructor(), (Map)data);
		xmiObject.add(isConstructorIXMIProperty);
		//--- Attribute javaModel.JavaMethod.isStatic ---
		IXMIProperty isStaticIXMIProperty = getProperty("javaModel.JavaMethod.isStatic", host.getIsStatic(), (Map)data);
		xmiObject.add(isStaticIXMIProperty);
		//--- Attribute javaModel.JavaMethod.body ---
		IXMIProperty bodyIXMIProperty = getProperty("javaModel.JavaMethod.body", host.getBody(), (Map)data);
		xmiObject.add(bodyIXMIProperty);
		//--- Association javaModel.JavaMethod.javaClasses ---
		IXMIProperty javaClassesIXMIProperty = getProperty("javaModel.JavaMethod.javaClasses", host.getJavaClasses(), (Map)data);
		xmiObject.add(javaClassesIXMIProperty);
		//--- Association javaModel.JavaMethod.javaException ---
		IXMIProperty javaExceptionIXMIProperty = getProperty("javaModel.JavaMethod.javaException", host.getJavaException(), (Map)data);
		xmiObject.add(javaExceptionIXMIProperty);
		//--- Association javaModel.JavaMethod.parameters ---
		IXMIProperty parametersIXMIProperty = getProperty("javaModel.JavaMethod.parameters", host.getParameters(), (Map)data);
		xmiObject.add(parametersIXMIProperty);
		//--- Association javaModel.JavaMethod.result ---
		IXMIProperty resultIXMIProperty = getProperty("javaModel.JavaMethod.result", host.getResult(), (Map)data);
		xmiObject.add(resultIXMIProperty);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit class for 'javaModel.JavaElement' */
	public Object visit(javaModel.JavaElement host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.JavaElement", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.JavaPackageFactory' */
	public Object visit(javaModel.JavaPackageFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.JavaPackageFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.JavaPackage' */
	public Object visit(javaModel.JavaPackage host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.JavaPackage", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for JavaPackage ---
		//--- Association javaModel.JavaPackage.elements ---
		IXMIProperty elementsIXMIProperty = getProperty("javaModel.JavaPackage.elements", host.getElements(), (Map)data);
		xmiObject.add(elementsIXMIProperty);
		//--- Properties for JavaPackageElement ---
		//--- Association javaModel.JavaPackageElement.javaPackage ---
		IXMIProperty javaPackageIXMIProperty = getProperty("javaModel.JavaPackageElement.javaPackage", host.getJavaPackage(), (Map)data);
		xmiObject.add(javaPackageIXMIProperty);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.JavaParameterFactory' */
	public Object visit(javaModel.JavaParameterFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.JavaParameterFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.JavaParameter' */
	public Object visit(javaModel.JavaParameter host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.JavaParameter", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for JavaParameter ---
		//--- Association javaModel.JavaParameter.owner ---
		IXMIProperty ownerIXMIProperty = getProperty("javaModel.JavaParameter.owner", host.getOwner(), (Map)data);
		xmiObject.add(ownerIXMIProperty);
		//--- Association javaModel.JavaParameter.function ---
		IXMIProperty functionIXMIProperty = getProperty("javaModel.JavaParameter.function", host.getFunction(), (Map)data);
		xmiObject.add(functionIXMIProperty);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.JavaPackageElementFactory' */
	public Object visit(javaModel.JavaPackageElementFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.JavaPackageElementFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.JavaPackageElement' */
	public Object visit(javaModel.JavaPackageElement host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.JavaPackageElement", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for JavaPackageElement ---
		//--- Association javaModel.JavaPackageElement.javaPackage ---
		IXMIProperty javaPackageIXMIProperty = getProperty("javaModel.JavaPackageElement.javaPackage", host.getJavaPackage(), (Map)data);
		xmiObject.add(javaPackageIXMIProperty);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.JavaInterfaceFactory' */
	public Object visit(javaModel.JavaInterfaceFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.JavaInterfaceFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.JavaInterface' */
	public Object visit(javaModel.JavaInterface host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.JavaInterface", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for JavaInterface ---
		//--- Association javaModel.JavaInterface.implementedBy ---
		IXMIProperty implementedByIXMIProperty = getProperty("javaModel.JavaInterface.implementedBy", host.getImplementedBy(), (Map)data);
		xmiObject.add(implementedByIXMIProperty);
		//--- Properties for JavaClassifier ---
		//--- Association javaModel.JavaClassifier.sub ---
		IXMIProperty subIXMIProperty = getProperty("javaModel.JavaClassifier.sub", host.getSub(), (Map)data);
		xmiObject.add(subIXMIProperty);
		//--- Association javaModel.JavaClassifier.super_ ---
		IXMIProperty super_IXMIProperty = getProperty("javaModel.JavaClassifier.super_", host.getSuper_(), (Map)data);
		xmiObject.add(super_IXMIProperty);
		//--- Properties for JavaPackageElement ---
		//--- Association javaModel.JavaPackageElement.javaPackage ---
		IXMIProperty javaPackageIXMIProperty = getProperty("javaModel.JavaPackageElement.javaPackage", host.getJavaPackage(), (Map)data);
		xmiObject.add(javaPackageIXMIProperty);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.JavaClassifierFactory' */
	public Object visit(javaModel.JavaClassifierFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.JavaClassifierFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.JavaClassifier' */
	public Object visit(javaModel.JavaClassifier host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.JavaClassifier", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for JavaClassifier ---
		//--- Association javaModel.JavaClassifier.sub ---
		IXMIProperty subIXMIProperty = getProperty("javaModel.JavaClassifier.sub", host.getSub(), (Map)data);
		xmiObject.add(subIXMIProperty);
		//--- Association javaModel.JavaClassifier.super_ ---
		IXMIProperty super_IXMIProperty = getProperty("javaModel.JavaClassifier.super_", host.getSuper_(), (Map)data);
		xmiObject.add(super_IXMIProperty);
		//--- Properties for JavaPackageElement ---
		//--- Association javaModel.JavaPackageElement.javaPackage ---
		IXMIProperty javaPackageIXMIProperty = getProperty("javaModel.JavaPackageElement.javaPackage", host.getJavaPackage(), (Map)data);
		xmiObject.add(javaPackageIXMIProperty);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.DataTypeFactory' */
	public Object visit(javaModel.DataTypeFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.DataTypeFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.DataType' */
	public Object visit(javaModel.DataType host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.DataType", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for DataType ---
		//--- Attribute javaModel.DataType.kind ---
		IXMIProperty kindIXMIProperty = getProperty("javaModel.DataType.kind", host.getKind(), (Map)data);
		xmiObject.add(kindIXMIProperty);
		//--- Properties for JavaClassifier ---
		//--- Association javaModel.JavaClassifier.sub ---
		IXMIProperty subIXMIProperty = getProperty("javaModel.JavaClassifier.sub", host.getSub(), (Map)data);
		xmiObject.add(subIXMIProperty);
		//--- Association javaModel.JavaClassifier.super_ ---
		IXMIProperty super_IXMIProperty = getProperty("javaModel.JavaClassifier.super_", host.getSuper_(), (Map)data);
		xmiObject.add(super_IXMIProperty);
		//--- Properties for JavaPackageElement ---
		//--- Association javaModel.JavaPackageElement.javaPackage ---
		IXMIProperty javaPackageIXMIProperty = getProperty("javaModel.JavaPackageElement.javaPackage", host.getJavaPackage(), (Map)data);
		xmiObject.add(javaPackageIXMIProperty);
		//--- Properties for JavaElement ---
		//--- Attribute javaModel.JavaElement.name ---
		IXMIProperty nameIXMIProperty = getProperty("javaModel.JavaElement.name", host.getName(), (Map)data);
		xmiObject.add(nameIXMIProperty);
		return xmiObject;
	}
	/** Visit factory for 'javaModel.DataKindFactory' */
	public Object visit(javaModel.DataKindFactory host, Object data) {
		IXMIObject xmiObject = new XMIObject("javaModel.DataKindFactory", host);
		return xmiObject;
	}
	/** Visit class for 'javaModel.DataKind' */
	public Object visit(javaModel.DataKind host, Object data) {
		if (((Map)data).containsKey(host)) return ((Map)data).get(host);
		IXMIObject xmiObject = new XMIObject("javaModel.DataKind", host);
		//--- Add (host, xmiObject) to data ---
		((Map)data).put(host, xmiObject);
		//--- Properties for DataKind ---
		return xmiObject;
	}
	/** Auxiliary function */
	IXMIProperty getProperty(String name, Object obj, Map data) {
		//--- Create property ---
		IXMIProperty prop = new XMIProperty(name);
		//--- Compute property type ---
		prop.setXMIType(XMIProperty.OBJECT);
		if (Type.isInstanceofPrimitiveType(obj)) {
			prop.setXMIType(XMIProperty.BASIC);
		}
		//--- Collection types ---
		if (Type.isInstanceofCollectionType(obj)) {
			prop.setXMIType(XMIProperty.COLLECTION);
		}
		//--- Check if obj is null ---
		if (obj == null) {
			prop.setXMIValue(null);
			return prop;
		}
		//--- Check if obj is already in data ---
		if (data.containsKey(obj)) {
			prop.setXMIValue(data.get(obj));
			return prop;
		}
		//--- Construct the value: an XMI object ---
		//--- Basic types ---
		if (Type.isInstanceofPrimitiveType(obj)) {
			prop.setXMIValue(obj.toString());
			return prop;
		}
		//--- Collection types ---
		if (Type.isInstanceofCollectionType(obj)) {
			Collection col = (Collection)obj;
			Iterator i = col.iterator();
			while (i.hasNext()) {
				Object value = i.next();
				if (Type.isInstanceofPrimitiveType(value)) {
					IXMIObject xmiElem = new XMIObject("BASIC", value);
					xmiElem.setLABEL(value.toString());
					if (value instanceof java.lang.Boolean) xmiElem = new XMIObject("Boolean", value);
					else if (value instanceof java.lang.Integer) xmiElem = new XMIObject("Integer", value);
					else if (value instanceof java.lang.Double) xmiElem = new XMIObject("Double", value);
					else if (value instanceof java.lang.String) xmiElem = new XMIObject("String", value);
					//--- Link collection to elem ---
					prop.addXMIValue(xmiElem);
				} else if (value instanceof JavaModelElement) {
					IXMIObject xmiElem = (IXMIObject)((JavaModelElement)value).accept(this, data);
					//--- Link collection to elem ---
					prop.addXMIValue(xmiElem);
				}
			}
			return prop;
		}
		//--- User types ---
		prop.setXMIValue(((JavaModelElement)obj).accept(this, data));
		return prop;
	}
}
