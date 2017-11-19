package uk.ac.kent.cs.ocl20.standard.lib;

import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;

public abstract class OclAnyImpl 
	implements OclAny 
{
	protected StdLibAdapter adapter;
	protected OclAnyImpl(StdLibAdapter adapter) {
		this.adapter = adapter;
	}

	//--- OclAny ---
	public OclBoolean equalTo(OclAny object2) {
		return adapter.Boolean(this.equals(object2));
	}

	public OclBoolean notEqualTo(OclAny object2) {
		return this.equalTo(object2).not();
	}
	
	public OclBoolean oclIsUndefined() {
		return this.adapter.Boolean(false);
	}

	public OclBoolean oclIsNew() {
		return this.adapter.Boolean(false);
	}

	public OclBoolean oclIsKindOf(Classifier type) {
		boolean b = ((Class)type.getImplClass()).isAssignableFrom( this.getImpl().getClass() );
		return adapter.Boolean(b);
	}

	public OclBoolean oclIsTypeOf(Classifier type) {
		boolean b1 = ((Class)type.getImplClass()).isAssignableFrom( this.getImpl().getClass() );
		boolean b2 = (this.getImpl().getClass()).isAssignableFrom( (Class)type.getImplClass() );
		return adapter.Boolean(b1 && b2);
	}

	public Object oclAsType(Classifier type) {
		return this;
	}

	public OclBoolean oclIsInState(Object state) {
		return this.adapter.Boolean(false);
	}
	
	public OclSet allInstances() {
		return null;
	}

	public String toString(Class[] x) {
		String str = "(";
		for(int i=0; i<x.length; i++) {
			str+=x[i].getName();
			if (i+1<x.length) str+="' ";
		}
		str+=")";
		return str;
	}

	//--- Object ---
	public abstract Object clone();
	public abstract boolean equals(Object o);
	public int hashCode() {
		return 0;
	}
}
