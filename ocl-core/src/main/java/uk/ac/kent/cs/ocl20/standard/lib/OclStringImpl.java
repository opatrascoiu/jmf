package uk.ac.kent.cs.ocl20.standard.lib;

import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.model.types.StringType;
import uk.ac.kent.cs.ocl20.standard.types.OclAnyTypeImpl;

public class OclStringImpl 
	extends OclAnyImpl 
	implements OclString 
{
	public OclStringImpl(java.lang.String s, StdLibAdapter adapter) {
		super(adapter);
		_implementation = s;
	}

	protected String _implementation;
	public String implementation() {
		return _implementation;
	}

	public Object getImpl() {
		return implementation();
	}
	
	public void setImpl(Object impl) {
		_implementation = (String)impl;
	}

	//--- OclString ---
	public OclBoolean equalTo(OclString string2) {
		return adapter.Boolean(implementation().equals(((OclStringImpl) string2).implementation()));
	}

	public OclBoolean notEqualTo(OclString string2) {
		return adapter.Boolean(!implementation().equals(((OclStringImpl) string2).implementation()));
	}

	public OclBoolean oclIsKindOf(Classifier type) {
		if (type instanceof StringType) return adapter.Boolean(true); 
		if (type.getClass() == OclAnyTypeImpl.class) return adapter.Boolean(true);;
		return adapter.Boolean(false);
	}

	public OclBoolean oclIsTypeOf(Classifier type) {
		if (type instanceof StringType) return adapter.Boolean(true); 
		return adapter.Boolean(false);
	}

	public Object oclAsType(Classifier type) {
		if (type instanceof StringType) return this; 
		if (type.getClass() == OclAnyTypeImpl.class) return this;
		return adapter.Undefined();
	}

	public OclInteger size() {
		return adapter.Integer(implementation().length());
	}

	public OclString concat(OclString string2) {
		return adapter.String(implementation() + ((OclStringImpl) string2).implementation());
	}

	public OclString substring(OclInteger lower, OclInteger upper) {
		return adapter.String(implementation().substring(adapter.impl(lower).intValue()-1, adapter.impl(upper).intValue()-1 + 1));
	}
	
	public OclInteger toInteger() {
		return adapter.Integer( Integer.parseInt(implementation()) );
	}

	public OclReal toReal() {
		return adapter.Real( Double.parseDouble(implementation()) );
	}

	//--- IOclVisitable ---
	public Object accept(OclVisitor v, Object obj) {
		return v.visit(this, obj);
	}
	//--- Object ---
	public String toString() {
		return "'" + _implementation + "'";
	}

	public boolean equals(Object o) {
		return (o instanceof OclString) ? adapter.impl(equalTo((OclString)o)).booleanValue() : false;
	}

	public int hashCode() {
		return implementation().hashCode();
	}

	public Object clone() {
		return adapter.String(_implementation);
	}
}
