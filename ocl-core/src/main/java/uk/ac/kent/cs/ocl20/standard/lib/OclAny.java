package uk.ac.kent.cs.ocl20.standard.lib;

import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;

public interface OclAny {
    public OclBoolean equalTo(OclAny object2);
    public OclBoolean notEqualTo(OclAny object2);
    public OclBoolean oclIsNew();
    public OclBoolean oclIsUndefined();
    public Object oclAsType(Classifier type);
    public OclBoolean oclIsTypeOf(Classifier type);
    public OclBoolean oclIsKindOf(Classifier type);
    public OclBoolean oclIsInState(Object type);
    public OclSet allInstances();
    
    public Object getImpl();
	public void setImpl(Object impl);
	
    public Object clone();
}
