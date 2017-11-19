package uk.ac.kent.cs.ocl20.bridge4java;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author dha
 *
 */
public class JavaBridgeFactory extends BridgeFactoryImpl {
	/** Constructor */
	public JavaBridgeFactory(OclProcessor proc) {
		super(proc);
	}

	/** Environment */
	public Environment buildEnvironment(java.lang.Package pkg) {
		Namespace ns = new NamespaceImpl(pkg, processor);
		Environment env = super.buildEnvironment();
		return env.addNamespace(ns);
	}

	/** Namespace */
	public Namespace buildNamespace(Object o) {
		if (o instanceof java.lang.Package)
			return buildNamespace((java.lang.Package)o);
		else
			return null;
	}
	public Namespace buildNamespace(java.lang.Package ns) {
		return new NamespaceImpl(ns, processor);
	}

	/** Operation */
	public Operation buildOperation(Classifier ret, String op_name, Classifier params[]) {
		Operation oper = new OperationImpl(null, processor);
		oper.setName(op_name);
		oper.setReturnType(ret);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Classifier paramType = (Classifier) params[i];
				//oper.getParameterNames().add(paramName);
				oper.getParameterTypes().add(paramType);
			}
		}
		return oper;
	}
	public Operation buildOperation(java.lang.reflect.Method op) {
		return new OperationImpl(op, processor);
	}

	/** Property */
	public Property buildProperty(java.lang.Object o) {
		if (o instanceof java.lang.reflect.Method)
			return new PropertyImpl((java.lang.reflect.Method)o, processor);
		else
			return null;
	}
	public Property buildProperty(Classifier ret, String name) {
		Property prop = new PropertyImpl(null, processor);
		prop.setName(name);
		prop.setType(ret);
		return prop;
	}

	/** Classifier */
	public Classifier buildClassifier(Object o) {
		if (o instanceof java.lang.Class)
			return buildClassifier((java.lang.Class)o);
		else
			return null;
	}
	public Classifier buildClassifier(java.lang.Class cls) {
		if (cls == Set.class) return processor.getTypeFactory().buildSetType( buildClassifier(java.lang.Object.class) );
		if (cls == List.class) return processor.getTypeFactory().buildSequenceType( buildClassifier(java.lang.Object.class) );
		if (cls == Collection.class) return processor.getTypeFactory().buildBagType( buildClassifier(java.lang.Object.class) );
		return (Classifier) buildModelElement(cls);
	}

	/** Model Element */
	public ModelElement buildModelElement(Object o) {
		java.lang.Class me = (java.lang.Class) o;
		if (java.util.Enumeration.class.isAssignableFrom(me))
			return buildEnumeration(me);
		else if (me.getName().equals("java.lang.String"))
			return processor.getTypeFactory().buildStringType();
		else if (me.getName().equals("java.lang.Integer"))
			return processor.getTypeFactory().buildIntegerType();
		else if (me.getName().equals("java.lang.Boolean"))
			return processor.getTypeFactory().buildBooleanType();
		else
			return processor.getBridgeFactory().buildOclModelElementType(me);
	}
	public OclModelElementType buildOclModelElementType(Object o)  {
		if (o instanceof java.lang.Class) {
			if (java.util.Enumeration.class.isAssignableFrom((java.lang.Class)o)) {
				super.processor.getLog().reportError("Creating OclModelElementType from Enumeration !!!");
			}
			return buildOclModelElementType((java.lang.Class)o);
		}
		else
			return null;
	}
	public OclModelElementType buildOclModelElementType(java.lang.Class cls) {
		return new OclModelElementTypeImpl(cls, processor);
	}
	
	/** Enumeration */
	public Enumeration_ buildEnumeration(Object o) {
		if (o instanceof java.lang.Class)
			return buildEnumeration((java.lang.Class)o);
		else
			return null;
	}
	public Enumeration_ buildEnumeration(java.lang.Class enum_) {
		return new EnumerationImpl(enum_, processor);
	}
	
	/** Enumeration Literal */
	public EnumLiteral buildEnumLiteral(Object o) {
		if (o instanceof java.lang.reflect.Field)
			return buildEnumLiteral((java.lang.reflect.Field)o, null);
		else
			return null;
	}
	public EnumLiteral buildEnumLiteral(java.lang.reflect.Field enumLit, Enumeration_ enum_) {
		return new EnumLiteralImpl(enumLit, enum_);
	}
}
