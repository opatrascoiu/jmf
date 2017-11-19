package uk.ac.kent.cs.ocl20.standard.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class OclSequenceImpl extends OclCollectionImpl implements OclSequence {
	private java.util.List _implementation;

	protected void setImplementation(java.util.List list) {
		_implementation = list;
	}
	protected java.util.Collection implementation() {
		return _implementation;
	}
	protected java.util.List list_impl() {
		return _implementation;
	}

	protected OclSequenceImpl(Object[] array, StdLibAdapter adapter) {
		super(adapter);
		setImplementation(new Vector(Arrays.asList(array)));
	}

	//--- OclSequence ---
	public OclInteger count(Object object) {
		return super.count(object);
	}

	public OclBoolean equalTo(OclSequence sequence2) {
		if (adapter.impl(this.size()) != adapter.impl(sequence2.size()))
			return adapter.Boolean(false);
		List l = list_impl();
		List l2 = ((OclSequenceImpl) sequence2).list_impl();
		return adapter.Boolean(l.equals(l2));
	}

	public OclBoolean notEqualTo(OclSequence sequence2) {
		return equalTo(sequence2).not();
	}

	public OclSequence union(OclSequence sequence2) {
		OclSequenceImpl s = (OclSequenceImpl) adapter.Sequence(list_impl());
		s.list_impl().addAll(((OclSequenceImpl) sequence2).list_impl());
		return s;
	}

	public OclSequence append(Object object) {
		OclSequenceImpl seq = (OclSequenceImpl) adapter.Sequence(list_impl());
		seq.implementation().add(object);
		return seq;
	}

	public OclSequence prepend(Object object) {
		OclSequenceImpl seq = (OclSequenceImpl) adapter.Sequence(list_impl());
		seq.list_impl().add(0, object);
		return seq;
	}

	public OclSequence subSequence(OclInteger lower, OclInteger upper) {
		if (adapter.impl(lower).intValue() < 1) return null;
		if (adapter.impl(upper).intValue() > adapter.impl(this.size()).intValue() )  return null;
		int l = ((OclIntegerImpl) lower).int_impl() - 1;
		int u = ((OclIntegerImpl) upper).int_impl() - 1 +1;
		return adapter.Sequence(list_impl().subList(l, u));
	}

	public Object at(OclInteger i) {
		return list_impl().get(((OclIntegerImpl) i).int_impl() - 1);
	}

	public OclInteger indexOf(Object o) {
		return adapter.Integer( list_impl().indexOf(o) + 1 );
	}

	public Object first() {
		if (list_impl().size() < 1) return adapter.Undefined();
		Object first = list_impl().get(0); 
		return first;
	}
	
	public OclSequence tail() {
		return adapter.Sequence( list_impl().subList(1,list_impl().size()) );
	}
	
	public OclSequence insertAt(OclInteger i, Object o) {
		OclSequenceImpl seq = (OclSequenceImpl)this.clone();
		seq.list_impl().add( adapter.impl(i).intValue(), o );
		return seq;
	}
	
	public Object last() {
		if (list_impl().size() < 1) return adapter.Undefined();
		return (Object) list_impl().get(list_impl().size() - 1);
	}

	public OclSequence including(Object object) {
		return this.append(object);
	}

	public OclSequence excluding(Object object) {
		OclSequenceImpl s = (OclSequenceImpl) adapter.Sequence(this.list_impl());
		Collection col = new ArrayList();
		col.add(object);
		s.list_impl().removeAll(col);
		return s;
	}

	public OclSequence flatten() {
		OclSequenceImpl flat = new OclSequenceImpl(new Object[0], adapter);
		java.util.Iterator i = _implementation.iterator();
		while (i.hasNext()) {
			Object elem = (Object) i.next();
			if (elem instanceof OclBag) {
				flat.implementation().addAll(adapter.impl(((OclBag) elem).flatten()));
			} else if (elem instanceof OclSet) {
				flat.implementation().addAll(adapter.impl(((OclSet) elem).flatten()));
			} else if (elem instanceof OclSequence) {
				flat.implementation().addAll(adapter.impl(((OclSequence) elem).flatten()));
			} else {
				flat.implementation().add(elem);
			}
		}
		return flat;
	}

	public OclBag asBag() {
		return adapter.Bag(list_impl());
	}

	public OclSet asSet() {
		return adapter.Set(list_impl());
	}

	public OclSequence asSequence() {
		return adapter.Sequence(list_impl());
	}

	public OclOrderedSet asOrderedSet() {
		return adapter.OrderedSet(list_impl());
	}

	//--- IOclVisitable ---
	public Object accept(OclVisitor v, Object obj) {
		return v.visit(this, obj);
	}

	//--- Object ---
	public String toString() {
		String str = "Sequence { ";
		Iterator i = _implementation.iterator();
		while (i.hasNext()) {
			str += i.next();
			if (i.hasNext())
				str += ", ";
		}
		return str + " }";
	}

	public Object clone() {
		return adapter.Sequence(list_impl());
	}

	public boolean equals(Object o) {
		return (o instanceof OclSequence) ? adapter.impl(equalTo((OclSequence)o)).booleanValue() : false;
	}

	public Object getImpl() {
		return _implementation;
	}
}
