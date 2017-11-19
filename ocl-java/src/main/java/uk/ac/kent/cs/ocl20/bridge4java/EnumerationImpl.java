package uk.ac.kent.cs.ocl20.bridge4java;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.bridge.BridgeFactory;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.bridge.DataTypeImpl;
import uk.ac.kent.cs.ocl20.semantics.bridge.EnumLiteral;
import uk.ac.kent.cs.ocl20.semantics.bridge.Enumeration_;
import uk.ac.kent.cs.ocl20.semantics.bridge.ModelElement;
import uk.ac.kent.cs.ocl20.semantics.bridge.Property;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.standard.types.OclAnyTypeImpl;

/**
 * @author dha
 *
 */
public class EnumerationImpl
	extends DataTypeImpl implements Enumeration_
{
	/** Construct an Enumeration */
	public EnumerationImpl(java.lang.Class eenum, OclProcessor proc) {
		super(proc);
		_enum = eenum;

		//--- Add operations for OCLANY_TYPE ---
		BridgeFactory bf = super.processor.getBridgeFactory();
		TypeFactory tf = super.processor.getTypeFactory();
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "=", new Classifier[] { this }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "<>", new Classifier[] { this }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "=", new Classifier[] { tf.buildOclAnyType() }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "<>", new Classifier[] { tf.buildOclAnyType() }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsNew", new Classifier[] {}));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsUndefined", new Classifier[] {}));
		// Signature of oclAsType is computed later
		// OCLANY_TYPE.getOperations().add(buildOperation(CLASSIFIER_TYPE, "oclAsType", new Classifier[] { CLASSIFIER_TYPE }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsTypeOf", new Classifier[] { tf.buildClassifier() }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsKindOf", new Classifier[] { tf.buildClassifier() }));
		// Signature of allInstances is computed later
		// OCLANY_TYPE.getOperations().add(buildOperation(buildSetType(CLASSIFIER_TYPE), "allInstances", NONE));
	}

	/** Get name */
	public String getName() {
		return _enum.getName();
	}

	/** Check if this (an Enumeration) conforms with t2 */
	public Boolean conformsTo(Classifier t2) {
		if (t2 instanceof Enumeration_) {
			return (getName().equals(t2.getName())) ? Boolean.TRUE : Boolean.FALSE;
		}
		if (t2.getClass() == OclAnyTypeImpl.class) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/** Search for an Enumeration Literal with a given name */
	Map _literals = new HashMap();
	public EnumLiteral lookupEnumLiteral(String name) {
		EnumLiteral enumLit = (EnumLiteral) _literals.get(name);
		if (enumLit == null) {
			java.lang.reflect.Field f = null;
			try {
				f = _enum.getField(name);
			} catch (Exception e) {
			}
			if (f!=null) {
				enumLit = processor.getBridgeFactory().buildEnumLiteral(f);
				enumLit.setEnumeration(this);
				_literals.put(name, enumLit);
			}
		}
		return enumLit; 
	}

	/** Search for an Enumeration Literal with a given name */
	public Property lookupProperty(String name) {
		return lookupEnumLiteral(name);
	}

	/** Search for an owned element */
	public ModelElement lookupOwnedElement(String name) {
		return lookupEnumLiteral(name);
	}
	
	/** Get Enumeration Literals */
	List literals = null;
	public List getLiteral() {
		if (_enum != null) {
			literals = new Vector();
			Field fields[] = _enum.getFields();
			for(int i=0; i<fields.length; i++) {
				EnumLiteral enumLit = processor.getBridgeFactory().buildEnumLiteral(fields[i]);
				enumLit.setEnumeration(this);
				literals.add(enumLit);
			}
		}
		return literals;
	}

	/** Set Enumeration Literals */
	public void setLiteral(List literals) {
		if (_enum == null) this.literals = literals;
	}

	/** Accept a Sematics Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}
	
	/** Equals */
	public boolean equals(Object o) {
		if (!(o instanceof Enumeration_))
			return false;
		return (this.toString().equals(o.toString()));
	}

	/** Wrapped Java Class */
	protected java.lang.Class _enum;
	public java.lang.Class getImpl() {
		return _enum;
	}
	public void setImpl(java.lang.Class eenum) {
		this._enum = eenum;
	}

	/** ToString */
	public String toString() {
		return "Enumeration("+getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new EnumerationImpl(_enum, processor);
	}
}
