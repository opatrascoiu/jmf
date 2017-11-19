package uk.ac.kent.cs.ocl20.standard.lib;

import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.model.types.BooleanType;
import uk.ac.kent.cs.ocl20.standard.types.OclAnyTypeImpl;

public class OclBooleanImpl 
	extends OclAnyImpl 
	implements OclBoolean 
{
	protected OclBooleanImpl(StdLibAdapter adapter) {
		super(adapter);
	}

	static OclBoolean TRUE ;
	static OclBoolean FALSE ;

	public Object getImpl() {
		if (this == TRUE)
			return java.lang.Boolean.TRUE ;
		else if (this == FALSE)
			return java.lang.Boolean.FALSE;
		else
			return null;
	}
	public void setImpl(Object impl) {
	}
	
	//--- OclBoolean ---
	public OclBoolean equalTo(OclBoolean b2) {
		//if ( this == UNDEFINED ) return UNDEIFNED;
		if (b2 instanceof OclUndefined) return b2;
		OclBoolean res = adapter.Boolean(this.equals(b2)); 
		return res;
	}

	public OclBoolean or(OclBoolean b2) {
		if (this == TRUE) return TRUE;
		if (b2 == TRUE) return TRUE;
		if (this == FALSE && b2 == FALSE) return FALSE;
		if (b2 instanceof OclUndefined) return b2;
		return adapter.Undefined();
	}

	public OclBoolean oclIsKindOf(Classifier type) {
		if (type instanceof BooleanType) return adapter.Boolean(true); 
		if (type.getClass() == OclAnyTypeImpl.class) return adapter.Boolean(true);;
		return adapter.Boolean(false);
	}

	public OclBoolean oclIsTypeOf(Classifier type) {
		if (type instanceof BooleanType) return adapter.Boolean(true); 
		return adapter.Boolean(false);
	}

	public Object oclAsType(Classifier type) {
		if (type instanceof BooleanType) return this; 
		if (type.getClass() == OclAnyTypeImpl.class) return this;
		return adapter.Undefined();
	}

	public OclBoolean xor(OclBoolean b2) {
		//if ( this == UNDEFINED ) return UNDEFINED;
		if (b2 instanceof OclUndefined) return b2;
		if (this == b2) return FALSE;
		return TRUE;
	}

	public OclBoolean and(OclBoolean b2) {
		if (this == FALSE)	return FALSE;
		if (b2 == FALSE) return FALSE;
		if (this == TRUE && b2 == TRUE) return TRUE;
		if (b2 instanceof OclUndefined) return b2;
		return adapter.Undefined();
	}

	public OclBoolean not() {
		if (this == TRUE)
			return FALSE;
		if (this == FALSE)
			return TRUE;
		return adapter.Undefined();
	}

	public OclBoolean implies(OclBoolean b2) {
		if (this == FALSE)
			return TRUE;
		if (b2 == TRUE)
			return TRUE;
		if (this == TRUE)
			return b2;
		//if (this==UNDEFINED) return UNDEFINED; unless b2 ==TRUE
		return adapter.Undefined();
	}

	//--- OclVisitable ---
	public Object accept(OclVisitor v, Object obj) {
		return v.visit(this, obj);
	}

	//--- Object ---
	public String toString() {
		if (this == TRUE)
			return "true";
		if (this == FALSE)
			return "false";
		return adapter.Undefined().toString();
	}

	public boolean equals(Object b) {
		// if this is undef..this equalTo in undef is called
		if (! (b instanceof OclBoolean) ) return false;
		boolean v1 = adapter.impl(this).booleanValue();
		boolean v2 = adapter.impl((OclBoolean)b).booleanValue(); 
		return  v1 == v2;
	}
	public int hashCode() {
		return this == FALSE ? 0 : 1;
	}
	public Object clone() {
		return this;
	}
}
