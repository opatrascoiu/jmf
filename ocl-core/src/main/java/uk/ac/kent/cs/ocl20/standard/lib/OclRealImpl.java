package uk.ac.kent.cs.ocl20.standard.lib;

import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.model.types.RealType;
import uk.ac.kent.cs.ocl20.standard.types.OclAnyTypeImpl;

public class OclRealImpl 
	extends OclAnyImpl 
	implements OclReal 
{
	protected OclRealImpl(double r, StdLibAdapter adapter) {
		super(adapter);
		_implementation = r;
	}
	
	protected double _implementation;
	protected double implementation() {
		return _implementation;
	}

	public OclBoolean equalTo(OclReal r2) {
		return adapter.Boolean(this.implementation() == ((OclRealImpl) r2).implementation());
	}

	public OclBoolean notEqualTo(OclReal r2) {
		return adapter.Boolean(this.implementation() != ((OclRealImpl) r2).implementation());
	}

	public OclBoolean oclIsKindOf(Classifier type) {
		if (type instanceof RealType) return adapter.Boolean(true); 
		if (type.getClass() == OclAnyTypeImpl.class) return adapter.Boolean(true);;
		return adapter.Boolean(false);
	}

	public OclBoolean oclIsTypeOf(Classifier type) {
		if (type instanceof RealType) return adapter.Boolean(true); 
		return adapter.Boolean(false);
	}

	public Object oclAsType(Classifier type) {
		if (type instanceof RealType) return this; 
		if (type.getClass() == OclAnyTypeImpl.class) return this;
		return adapter.Undefined();
	}
	
	public Object getImpl() {
		return new Double(implementation());
	}

	public void setImpl(Object impl) {
		this._implementation = ((Double)impl).doubleValue();
	}

	//--- OclReal ---
	public OclReal add(OclReal r2) {
		return adapter.Real(this.implementation() + ((OclRealImpl) r2).implementation());
	}

	public OclReal subtract(OclReal r2) {
		return adapter.Real(this.implementation() - ((OclRealImpl) r2).implementation());
	}

	public OclReal multiply(OclReal r2) {
		return adapter.Real(this.implementation() * ((OclRealImpl) r2).implementation());
	}

	public OclReal negate() {
		return adapter.Real(this.implementation() * -1);
	}

	public OclReal divide(OclReal r2) {
		if (adapter.impl(r2).doubleValue() == 0) return adapter.Undefined();
		return adapter.Real(this.implementation() / ((OclRealImpl) r2).implementation());
	}

	public OclReal abs() {
		return adapter.Real(Math.abs(this.implementation()));
	}

	public OclInteger floor() {
		return adapter.Integer((int) Math.floor(this.implementation()));
	}

	public OclInteger round() {
		return adapter.Integer((int) Math.round(this.implementation()));
	}

	public OclReal max(OclReal r2) {
		return adapter.Real(Math.max(this.implementation(), ((OclRealImpl) r2).implementation()));
	}

	public OclReal min(OclReal r2) {
		return adapter.Real(Math.min(this.implementation(), ((OclRealImpl) r2).implementation()));
	}

	public OclBoolean lessThan(OclReal r2) {
		return adapter.Boolean(this.implementation() < ((OclRealImpl) r2).implementation());
	}

	public OclBoolean greaterThan(OclReal r2) {
		return adapter.Boolean(this.implementation() > ((OclRealImpl) r2).implementation());
	}

	public OclBoolean lessThanOrEqualTo(OclReal r2) {
		return adapter.Boolean(this.implementation() <= ((OclRealImpl) r2).implementation());
	}

	public OclBoolean greaterThanOrEqualTo(OclReal r2) {
		return adapter.Boolean(this.implementation() >= ((OclRealImpl) r2).implementation());
	}

	//--- IOclVisitable ---
	public Object accept(OclVisitor v, Object obj) {
		return v.visit(this, obj);
	}

	//---Object---
	public String toString() {
		return String.valueOf(implementation());
	}
	public boolean equals(Object r) {
		if (r instanceof OclRealImpl)
			return adapter.impl(this.equalTo((OclRealImpl) r)).booleanValue();
		return false;
	}

	public int hashCode() {
		return (int) implementation();
	}

	public Object clone() {
		return new OclRealImpl(implementation(), super.adapter);
	}
}
