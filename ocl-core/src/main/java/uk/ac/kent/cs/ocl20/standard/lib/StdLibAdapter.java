package uk.ac.kent.cs.ocl20.standard.lib;

/**
 * @author dha
 *
 */
public interface StdLibAdapter {
	public OclAny OclAny(java.lang.Object obj);
	public java.lang.Object impl(OclAny obj);
	
	public OclType Type(java.lang.Class cls);
	
	public OclUndefined Undefined();

	public OclBoolean Boolean(boolean b);
	public OclBoolean Boolean(java.lang.Boolean b);
	public OclBoolean Boolean(java.lang.String b);
	public java.lang.Boolean impl(OclBoolean b);

	public OclReal Real(double r);
	public OclReal Real(Double r);
	public OclReal Real(java.lang.String r);
	public OclReal Real(float r);
	public java.lang.Double impl(OclReal r);

	public OclInteger Integer(int i);
	public OclInteger Integer(Integer i);
	public OclInteger Integer(String i);
	public java.lang.Integer impl(OclInteger i);
	
	public OclString String(java.lang.String s);
	public java.lang.String impl(OclString s);
	
	public OclTuple Tuple(java.util.Map m);
	public OclTuple Tuple(OclString[] keys, OclAny[] arr);
	public java.util.Map impl(OclTuple t);
	
	public OclCollection OclCollection(java.util.Collection obj);
	public java.util.Collection impl(OclCollection b);
	
	public OclSet Set();
	public OclSet Set(java.util.Collection impl);
	public OclSet Set(Object[] array);
	public java.util.Set impl(OclSet s);
	
	public OclOrderedSet OrderedSet();
	public OclOrderedSet OrderedSet(java.util.Collection impl);
	public OclOrderedSet OrderedSet(Object[] array);
	public java.util.List impl(OclOrderedSet s);
	
	public OclSequence Sequence();
	public OclSequence Sequence(java.util.Collection impl);
	public OclSequence Sequence(Object[] array);
	public java.util.List impl(OclSequence s);
	
	public OclBag Bag();
	public OclBag Bag(java.util.Collection impl);
	public OclBag Bag(Object[] array);
	public java.util.Collection impl(OclBag b);
}
