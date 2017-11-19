package uk.ac.kent.cs.ocl20.standard.lib;

public interface OclBag 
	extends OclCollection 
{
	public OclBoolean equalTo(OclBag bag2);
	public OclBoolean notEqualTo(OclBag bag2);
	
    public OclBag union(OclBag bag2);
    public OclBag union(OclSet set);
	public OclBag union(OclOrderedSet set);
    public OclBag intersection(OclBag bag2);
    public OclSet intersection(OclSet set);
	public OclOrderedSet intersection(OclOrderedSet set);
    public OclBag including(Object object);
    public OclBag excluding(Object object);

    public OclInteger count(Object object);
    public OclBag flatten();

    public OclBag asBag();
    public OclSet asSet();
    public OclSequence asSequence();
    public OclOrderedSet asOrderedSet();
}
