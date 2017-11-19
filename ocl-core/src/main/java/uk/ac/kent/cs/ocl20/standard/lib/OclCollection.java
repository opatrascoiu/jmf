package uk.ac.kent.cs.ocl20.standard.lib;

public interface OclCollection {
    public OclInteger size();
    public OclBoolean includes(Object object);
    public OclBoolean excludes(Object object);
    public OclInteger count(Object object);
    public OclBoolean includesAll(OclCollection col);
    public OclBoolean excludesAll(OclCollection col);
    public OclBoolean isEmpty();
    public OclBoolean notEmpty();
    public Object sum();
    public OclSet product(OclCollection c2);
    
	public Object getImpl();
}
