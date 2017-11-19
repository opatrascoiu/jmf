package uk.ac.kent.cs.ocl20.standard.lib;

public interface OclSequence
    extends OclCollection
{
	public OclInteger count(Object object);
	public OclBoolean equalTo(OclSequence seq2);
	public OclBoolean notEqualTo(OclSequence seq2);
	public OclSequence union(OclSequence sequence2);
	public OclSequence flatten();
	public OclSequence append(Object object);
	public OclSequence prepend(Object object);
	public OclSequence insertAt(OclInteger index, Object object);
	public OclSequence subSequence(OclInteger lower, OclInteger upper);
	public Object at(OclInteger i);
	public OclInteger indexOf(Object object);
	public Object first();
	public Object last();
	public OclSequence including(Object object);
	public OclSequence excluding(Object object);

	public OclBag asBag();
	public OclSet asSet();
	public OclOrderedSet asOrderedSet();
	public OclSequence asSequence();
}
