package uk.ac.kent.cs.ocl20.standard.lib;


/**
 * @author dha
 *
 */
public class StdLibAdapterImpl
	implements StdLibAdapter
{
	public static StdLibAdapterImpl INSTANCE = new StdLibAdapterImpl();
	 
	public StdLibAdapterImpl() {
		OclUndefinedImpl.UNDEFINED = new OclUndefinedImpl("Default", null, this);
		OclBooleanImpl.FALSE = new OclBooleanImpl(this);
		OclBooleanImpl.TRUE = new OclBooleanImpl(this);
	}

	public OclAny OclAny(java.lang.Object obj) {
		if (obj == null) return this.Undefined();
		if (obj instanceof OclAny) return (OclAny)obj;
		if (obj instanceof java.lang.Boolean) return this.Boolean((java.lang.Boolean)obj);
		if (obj instanceof java.lang.String) return this.String((java.lang.String)obj);
		if (obj instanceof java.lang.Double) return this.Real((java.lang.Double)obj);
		if (obj instanceof java.lang.Float) return this.Real(new Double(((java.lang.Float)obj).doubleValue()));
		if (obj instanceof java.lang.Integer) return this.Integer((java.lang.Integer)obj);
		return this.Undefined();
	}

	public Object impl(OclAny obj) {
		if (obj instanceof OclUndefined) return this.impl((OclUndefined)obj);
		if (obj instanceof OclString) return this.impl((OclString)obj);
		if (obj instanceof OclInteger) return this.impl((OclInteger)obj);
		if (obj instanceof OclReal) return this.impl((OclReal)obj);
		if (obj instanceof OclBoolean) return this.impl((OclBoolean)obj);
		if (obj instanceof OclTuple) return this.impl((OclTuple)obj);
		return null;
	}

	public OclType Type(java.lang.Class cls) {
		return new OclTypeImpl(cls, this);
	}

	//--- Undefined ---
	public Object impl(OclUndefined obj) {
		return null;
	}
	
	public OclUndefined Undefined() {
		return OclUndefinedImpl.UNDEFINED;
	}

	//--- Boolean ---
	public OclBoolean Boolean(boolean b) {
		if (b)
			return OclBooleanImpl.TRUE;
		return OclBooleanImpl.FALSE;
	}

	public OclBoolean Boolean(java.lang.Boolean b) {
		if (b != null)
			return Boolean(b.booleanValue());
		else
			return Undefined();
	}

	public OclBoolean Boolean(java.lang.String b) {
		return Boolean(Boolean.valueOf(b).booleanValue());
	}

	public java.lang.Boolean impl(OclBoolean b) {
		if (b instanceof OclBooleanImpl)
			return new Boolean((OclBooleanImpl) b == OclBooleanImpl.TRUE);
		else
			return Boolean.FALSE;
	}

	//--- Real ---
	public OclReal Real(double r) {
		return new OclRealImpl(r, this);
	}

	public OclReal Real(Double r) {
		return new OclRealImpl(r.doubleValue(), this);
	}

	public OclReal Real(java.lang.String r) {
		return new OclRealImpl(Double.valueOf(r).doubleValue(), this);
	}

	public OclReal Real(float r) {
		return new OclRealImpl((double) r, this);
	}

	public java.lang.Double impl(OclReal r) {
		if (r instanceof OclInteger) return new Double( ((java.lang.Integer)r.getImpl()).doubleValue() );
		if (r instanceof OclReal) return (java.lang.Double)r.getImpl();
		return null;
	}

	//--- Integer ---
	public OclInteger Integer(int i) {
		return new OclIntegerImpl(i, this);
	}

	public OclInteger Integer(Integer i) {
		return new OclIntegerImpl(i.intValue(), this);
	}

	public OclInteger Integer(String i) {
		return new OclIntegerImpl(Integer.valueOf(i).intValue(), this);
	}

	public java.lang.Integer impl(OclInteger i) {
		return (java.lang.Integer)i.getImpl();
	}

	//--- String ---
	public OclString String(java.lang.String s) {
		return new OclStringImpl(s, this);
	}

	public java.lang.String impl(OclString s) {
		return (java.lang.String)s.getImpl();
	}

	//--- Tuple ---
	public OclTuple Tuple(java.util.Map m) {
		return new OclTupleImpl(m, this);
	}

	public OclTuple Tuple(OclString[] keys, OclAny[] arr) {
		return new OclTupleImpl(keys, arr, this);
	}

	public java.util.Map impl(OclTuple t) {
		return (java.util.Map)t.getImpl();
	}

	//--- Collection ---
	public OclCollection OclCollection(java.util.Collection obj) {
		if (obj == null) return this.Undefined();
		if (obj instanceof java.util.List) return this.Sequence((java.util.List)obj);
		if (obj instanceof java.util.Set) return this.Set((java.util.Set)obj);
		if (obj instanceof java.util.Collection) return this.Bag((java.util.Collection)obj);
		return this.Undefined();
	}

	public java.util.Collection impl(OclCollection col) {
		if (col instanceof OclOrderedSet) return this.impl((OclOrderedSet)col);
		if (col instanceof OclSet) return this.impl((OclSet)col);
		if (col instanceof OclSequence) return this.impl((OclSequence)col);
		if (col instanceof OclBag) return this.impl((OclBag)col);
		return ((OclCollectionImpl)col).implementation();
	}
   
	//--- Set ---
	public OclSet Set() {
		return Set(new Object[0]);
	}

	public OclSet Set(java.util.Collection impl) {
		if (impl != null) {
			return Set((Object[]) impl.toArray(new Object[0]));
		} else {
			return Set(); 
		}
			
	}

	public OclSet Set(Object[] array) {
		return new OclSetImpl(array, this);
	}

	public java.util.Set impl(OclSet s) {
		return (java.util.Set)s.getImpl();
	}

	//--- OrderedSet ---
	public OclOrderedSet OrderedSet() {
		return OrderedSet(new Object[0]);
	}

	public OclOrderedSet OrderedSet(java.util.Collection impl) {
		return OrderedSet((Object[]) impl.toArray(new Object[0]));
	}

	public OclOrderedSet OrderedSet(Object[] array) {
		return new OclOrderedSetImpl(array, this);
	}

	public java.util.List impl(OclOrderedSet s) {
		return (java.util.List)s.getImpl();
	}

	//--- Sequence ---
	public OclSequence Sequence() {
		return Sequence(new Object[0]);
	}

	public OclSequence Sequence(java.util.Collection impl) {
		return Sequence((Object[]) impl.toArray(new Object[0]));
	}

	public OclSequence Sequence(Object[] array) {
		return new OclSequenceImpl(array, this);
	}

	public java.util.List impl(OclSequence s) {
		return (java.util.List)s.getImpl();
	}

	//--- Bag ---
	public OclBag Bag() {
		return Bag(new Object[0]);
	}

	public OclBag Bag(java.util.Collection impl) {
		return Bag((Object[]) impl.toArray(new Object[0]));
	}

	public OclBag Bag(Object[] array) {
		return new OclBagImpl(array, this);
	}

	public java.util.Collection impl(OclBag b) {
		return (java.util.Collection)b.getImpl();
	}
}
