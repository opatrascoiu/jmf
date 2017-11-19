package uk.ac.kent.cs.ocl20.bridge4java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.lang.reflect.Method;
import java.util.Arrays;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.semantics.model.types.*;
import uk.ac.kent.cs.ocl20.standard.types.*;

/**
 * @author dha
 *
 */
public class OclModelElementTypeImpl
	extends OclAnyTypeImpl 
	implements OclModelElementType 
{
	/** Construct a Model Element Type */
	public OclModelElementTypeImpl(java.lang.Class impl, OclProcessor proc) {
		super(proc);
		processor = proc;
		class_ = impl;
		List oper = super.getOperations();
		BridgeFactory bf = proc.getBridgeFactory();
		TypeFactory tf = proc.getTypeFactory();
		oper.add(bf.buildOperation(tf.buildBooleanType(), "=", new Classifier[] { this }));
		oper.add(bf.buildOperation(tf.buildBooleanType(), "<>", new Classifier[] { this }));
		List oclAnyOper = ((TypeFactoryImpl)tf).OCLANY_TYPE.getOperations();
		oper.addAll(oclAnyOper);
	}

	/** Wrapped Java Class */
	java.lang.Class class_;
	public java.lang.Class getImpl() {
		return class_;
	}
	public void setImpl(java.lang.Class class_) {
		this.class_ = class_;
	}

	/** Lookup a property */
	private Map _properties = new HashMap();
	public Property lookupProperty(String name) {
		String impl_name = processor.getModelImplAdapter().getGetterName(name);
		Property prop = (Property) _properties.get(name);
		java.lang.reflect.Method f = null;
		Method[] methods  = class_.getMethods();
		for(int i=0; i<methods.length; i++) {
			java.lang.reflect.Method method = (java.lang.reflect.Method) methods[i];
			String methodName = method.getName();
			if (methodName.equals(impl_name)) {
				f = method;
				break;
			}
		}
		if (f != null) {
			return ((JavaBridgeFactory)processor.getBridgeFactory()).buildProperty(f);
		}
		return null;
	}

	/** Lookup an operation */
	public Operation lookupOperation(String name, List types) {
		Operation o = super.lookupOperation(name, types);
		if (o == null) {
			java.lang.reflect.Method f = null;
			Iterator i = Arrays.asList(class_.getMethods()).iterator();
			while (i.hasNext()) {
				java.lang.reflect.Method f2 = (java.lang.reflect.Method) i.next();
				if (f2.getName().equals(name)) {
					f = f2;
					break;
				}
			}
			if (f != null) {
				return ((JavaBridgeFactory)processor.getBridgeFactory()).buildOperation(f);
			}
			return null;
		}
		return o;
	}

	/** Name */
	public String getName() {
		String[] clsName = class_.getName().split("[.]");
		return clsName[clsName.length-1];
	}

	/** Namespace */
	public Namespace getNamespace() {
		Namespace ns = super.getNamespace();
		if (ns == null) {
			ns = processor.getBridgeFactory().buildNamespace( class_.getPackage() );
			super.setNamespace(ns);
		}
		return ns;
	}

	/** Checks if this conforms to c */
	public Boolean conformsTo(Classifier c) {
		if (c instanceof OclAnyType)
			return Boolean.TRUE;
		if (c.getClass() == OclModelElementTypeImpl.class) {
			java.lang.Class cc = ((OclModelElementTypeImpl) c).class_;
			return new Boolean(class_.isAssignableFrom(cc));
		}
		return null;
	}

	/** Get the delegate Class */
	public Object getDelegate() {
		return this;
	}

	/** Get the implementation Class */
	public Class getImplClass() {
		return class_.getClass();
	}

	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object data) {
		return  v.visit(this, data);
	}

	/** ToString */
	public String toString() {
		return getName();
	}

	/** Clone */
	public Object clone() {
		return new OclModelElementTypeImpl(class_, processor);
	}	
}
