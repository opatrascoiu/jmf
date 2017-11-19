package uk.ac.kent.cs.ocl20.standard.lib;

public interface OclSet
    extends OclCollection
{
	public OclBoolean equalTo(OclSet set2);
	public OclBoolean notEqualTo(OclSet set2);

    public OclSet union(OclSet set2);
	public OclBag union(OclBag bag);
	public OclSet union(OclOrderedSet set2);
	public OclSet intersection(OclSet set2);
	public OclSet intersection(OclBag bag);
	public OclSet intersection(OclOrderedSet set2);
	public OclSet subtract(OclSet set2);
	public OclSet including(Object object);
	public OclSet excluding(Object object);
	public OclSet symmetricDifference(OclSet set2);

	public OclInteger count(Object object);
	public OclSet flatten();

	public OclBag asBag();
	public OclSet asSet();
	public OclSequence asSequence();
	public OclOrderedSet asOrderedSet();
}
