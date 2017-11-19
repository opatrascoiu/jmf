package uk.ac.kent.cs.ocl20.standard.lib;

public interface OclOrderedSet
    extends OclCollection
{
	public OclBoolean equalTo(OclOrderedSet set2);
	public OclBoolean notEqualTo(OclOrderedSet set2);
	
	public OclBag union(OclBag bag);
	public OclSet union(OclSet set2);
	public OclOrderedSet union(OclOrderedSet set2);
	public OclSet intersection(OclBag bag);
	public OclSet intersection(OclSet set2);
	public OclOrderedSet intersection(OclOrderedSet set2);
    
    public OclOrderedSet append(Object object);
    public OclOrderedSet prepend(Object object);
    public OclOrderedSet insertAt(OclInteger index, Object object);
    public OclOrderedSet subOrderedSet(OclInteger lower, OclInteger upper);
    public Object at(OclInteger index);
    public Object first();
    public Object last();
    public OclOrderedSet including(Object object);
    public OclOrderedSet excluding(Object object);
    
	public OclInteger count(Object object);
    public OclOrderedSet flatten();
    
	public OclBag asBag();
	public OclSet asSet();
	public OclSequence asSequence();
	public OclOrderedSet asOrderedSet();
}
