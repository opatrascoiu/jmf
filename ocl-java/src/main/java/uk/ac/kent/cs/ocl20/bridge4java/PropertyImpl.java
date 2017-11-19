package uk.ac.kent.cs.ocl20.bridge4java;

import java.util.*;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author dha
 *
 */
public class PropertyImpl implements Property {
	public static Map types = new HashMap();
	public static Map enumerations = new HashMap();
	
	private OclProcessor _processor;
	/** Construct a Property */
	public PropertyImpl(java.lang.reflect.Method  method, OclProcessor proc) {
		_processor = proc;
		this.method = method;
		this.name = method.getName();
	}

	/** Wrapped Java Method */
	protected java.lang.reflect.Method method;
	public java.lang.reflect.Method getImpl() {
		return method;
	}
	public void setImpl(java.lang.reflect.Method method) {
		this.method = method;
	}

	/** Get Property type */
	Classifier type = null;
	public Classifier getType() {
		if (method != null) {
			Class returnType = method.getReturnType();
			if (returnType == Boolean.class) {
				type = _processor.getTypeFactory().buildBooleanType();
			} else if (Integer.class.isAssignableFrom(returnType)) {
				type = _processor.getTypeFactory().buildIntegerType();
			} else if (Float.class.isAssignableFrom(returnType)) {
				type = _processor.getTypeFactory().buildRealType();
			} else if (Double.class.isAssignableFrom(returnType)) {
				type = _processor.getTypeFactory().buildRealType();
			} else if (String.class.isAssignableFrom(returnType)) {
				type = _processor.getTypeFactory().buildStringType();
			} else if (Collection.class.isAssignableFrom(returnType)) {
				String key = method.getDeclaringClass()+"-"+method.getName();
				Classifier elementType = _processor.getTypeFactory().buildClassifier();
				Class linkType=null;
				if (linkType==null) linkType = getTypeFromMemberVariable();
				if (linkType==null) linkType = getTypeFromMap();
				if (linkType != null) {
					elementType = _processor.getBridgeFactory().buildOclModelElementType(linkType);
				}
				if (Set.class.isAssignableFrom(returnType)) {
					type = _processor.getTypeFactory().buildSetType(elementType);
				} else if (List.class.isAssignableFrom(returnType)) {
					type = _processor.getTypeFactory().buildSequenceType(elementType);
				} else {
					type = _processor.getTypeFactory().buildBagType(elementType);
				}
			} else {
				String key = method.getDeclaringClass()+"-"+method.getName();
				if (enumerations.get(key) != null)
					type = _processor.getBridgeFactory().buildEnumeration(returnType);
				else
					type = _processor.getBridgeFactory().buildClassifier(returnType);
			}
		} 
		return type; 
	}
	Class getTypeFromMap() {
		String key = method.getDeclaringClass()+"-"+method.getName();
		return (Class)types.get(key);
	}
	Class getTypeFromMemberVariable() {
		try {
			return this.method.getDeclaringClass().getField(this.getName()+"_elementType").getType();
		} catch (SecurityException e) {
			//e.printStackTrace();
		} catch (NoSuchFieldException e) {
			//e.printStackTrace();
		}
		return null;
	}

	/** Set type */
	public void setType(Classifier type) {
		if (method == null ) this.type = type;
	}

	/** Name */
	protected String name = null;
	public String getName() {
		if (method != null) name = _processor.getModelImplAdapter().getModelPropertyName(method.getName());
		return name;
	}
	public void setName(String name) {
		if (method == null) this.name = name;
	}

	/** Get delegate */
	public Object getDelegate() {
		return this.getClass();
	}
	
	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	/** ToString */
	public String toString() {
		return "Property("+getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new PropertyImpl(method, _processor);
	}
}
