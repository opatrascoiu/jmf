package uk.ac.kent.cs.ocl20.standard.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class OclOrderedSetImpl
	extends OclCollectionImpl
	implements OclOrderedSet
{
	protected OclOrderedSetImpl(Object[] array, StdLibAdapter adapter) {
		super(adapter);
		_implementation = new Vector();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				if (!adapter.impl(includes(array[i])).booleanValue()) 
					_implementation.add(array[i]);
			}
		}
	}

	protected java.util.List _implementation;
	protected java.util.Collection implementation() {
		return _implementation;
	}
	protected java.util.List orderedset_impl() {
		return _implementation;
	}

	public OclBoolean equalTo(OclOrderedSet set2) {
		if (adapter.impl(this.size()) != adapter.impl(set2.size()))
			return adapter.Boolean(false);
		Collection b = implementation();
		Iterator it = b.iterator();
		while (it.hasNext()) {
			Object ob = (Object) it.next();
			if (!adapter.impl(count(ob).equalTo(set2.count(ob))).booleanValue())
				return adapter.Boolean(false);
		}
		return adapter.Boolean(true);
	}
	
	public OclBoolean notEqualTo(OclOrderedSet set2) {
		return equalTo(set2).not();
	}

	//--- Set ---
	public OclOrderedSet union(OclOrderedSet set2) {
		OclOrderedSet s = this.union(set2.asBag()).asOrderedSet();
		return s;
	}

	public OclSet union(OclSet set2) {
		OclSet s = this.union(set2.asBag()).asSet();
		return s;
	}

	public OclBag union(OclBag bag) {
		OclBagImpl b = (OclBagImpl) adapter.Bag(orderedset_impl());
		b.implementation().addAll(((OclBagImpl) bag).implementation());
		return b;
	}

	public OclSet intersection(OclSet set2) {
		OclSetImpl s = (OclSetImpl) adapter.Set();
		List s1 = adapter.impl(this);
		Set s2 = adapter.impl(set2);
		Iterator i1 = s1.iterator();
		while (i1.hasNext()) {
			Object o = (Object) i1.next();
			if (adapter.impl(set2.includes(o)).booleanValue())
				adapter.impl(s).add(o);
		}
		Iterator i2 = s2.iterator();
		while (i2.hasNext()) {
			Object o = (Object) i2.next();
			if (adapter.impl(this.includes(o)).booleanValue())
				adapter.impl(s).add(o);
		}
		return s;
	}

	public OclSet intersection(OclBag bag) {
		return this.intersection(bag.asSet());
	}

	public OclOrderedSet intersection(OclOrderedSet set2) {
		return this.intersection(set2.asSet()).asOrderedSet();
	}

	public OclSet subtract(OclSet set2) {
		OclSetImpl s = (OclSetImpl) adapter.Set();
		List set = adapter.impl(this);
		Set s2 = adapter.impl(set2);
		Iterator i1 = set.iterator();
		while (i1.hasNext()) {
			Object o = (Object) i1.next();
			if (!s2.contains(o))
				adapter.impl(s).add(o);
		}
		return s;
	}

	public OclSet symmetricDifference(OclSet set2) {
		OclSetImpl s = (OclSetImpl) adapter.Set();
		List s1 = adapter.impl(this);
		Set s2 = adapter.impl(set2);
		Iterator i1 = s1.iterator();
		while (i1.hasNext()) {
			Object o = (Object) i1.next();
			if (!s2.contains(o))
				adapter.impl(s).add(o);
		}
		Iterator i2 = s2.iterator();
		while (i2.hasNext()) {
			Object o = (Object) i2.next();
			if (!s1.contains(o))
				adapter.impl(s).add(o);
		}
		return s;
	}

	//--- OrderedSet ---
	public OclOrderedSet append(Object object) {
		OclOrderedSet seq = (OclOrderedSet)this.clone();
		if (!adapter.impl(includes(object)).booleanValue()) 
			((java.util.Collection)seq.getImpl()).add(object);
		return seq;
	}

	public OclOrderedSet prepend(Object object) {
		OclOrderedSet seq = (OclOrderedSet)this.clone();
		if (!adapter.impl(includes(object)).booleanValue()) 
		((java.util.List)seq.getImpl()).add(0,object);
		return seq;
	}

	public OclOrderedSet including(Object object) {
		return this.append(object);
	}

	public OclOrderedSet excluding(Object object) {
		OclOrderedSet s = (OclOrderedSet)this.clone();
		Collection col = new ArrayList();
		col.add(object);
		((java.util.Collection)s.getImpl()).removeAll(col);
		return s;
	}

	public OclOrderedSet insertAt(OclInteger index, Object object) {
		int i = adapter.impl(index).intValue();
		OclOrderedSet seq = (OclOrderedSet)this.clone();
		((java.util.List)seq.getImpl()).add(i, object);
		return seq;
	}

	public OclOrderedSet subOrderedSet(OclInteger lower, OclInteger upper) {
		if (adapter.impl(lower).intValue() < 1) return null;
		if (adapter.impl(upper).intValue() > adapter.impl(this.size()).intValue() )  return null;
		int l = ((java.lang.Integer)lower.getImpl()).intValue() - 1;
		int u = ((java.lang.Integer)upper.getImpl()).intValue() - 1 +1;
		return adapter.OrderedSet(orderedset_impl().subList(l, u));
	}

	public Object at(OclInteger i) {
		return orderedset_impl().get(((java.lang.Integer)i.getImpl()).intValue()-1);
	}

	public OclInteger indexOf(Object object) {
		Iterator i = _implementation.iterator();
		int j = 1;
		while (i.hasNext()) {
			Object obj = i.next();
			if (obj.equals(object)) return adapter.Integer(j);
			j++;
		}
		return adapter.Integer(0);
	}

	public Object first() {
		if (orderedset_impl().size() < 1) return adapter.Undefined();
		return orderedset_impl().get(0);
	}
	
	public Object last() {
		if (orderedset_impl().size() < 1) return adapter.Undefined();
		return orderedset_impl().get(orderedset_impl().size() - 1);
	}

	public OclOrderedSet flatten() {
		OclOrderedSet flat = (OclOrderedSet)this.clone();
		((java.util.Collection)flat.getImpl()).clear();
		java.util.Iterator i = _implementation.iterator();
		while (i.hasNext()) {
			Object elem = i.next();
			if (elem instanceof OclCollection) {
				java.util.Iterator j = null;
				if (elem instanceof OclBag) {
					j = adapter.impl(((OclBag)elem).flatten()).iterator();
				} else if (elem instanceof OclSet) {
					j = adapter.impl(((OclSet)elem).flatten()).iterator();
				} else if (elem instanceof OclOrderedSet) {
					j = adapter.impl(((OclOrderedSet)elem).flatten()).iterator();
				} else if (elem instanceof OclSequence) {
					j = adapter.impl(((OclSequence)elem).flatten()).iterator();
				}
				while (j.hasNext()) {
					Object x = j.next();
					if (x!= null && !adapter.impl(flat).contains(x)) ((java.util.Collection)flat.getImpl()).add(x);
				}
			} else {
				((java.util.Collection)flat.getImpl()).add(elem);
			}
		}
		return flat;
	}


	public OclBag asBag() {
		return adapter.Bag(orderedset_impl());
	}

	public OclSet asSet() {
		return adapter.Set(orderedset_impl());
	}

	public OclOrderedSet asOrderedSet() {
		return this;
	}

	public OclSequence asSequence() {
		return adapter.Sequence(orderedset_impl());
	}

	//--- Object ---
	public String toString() {
		String str = "OrderedSet { ";
		java.util.Iterator i = _implementation.iterator();
		while (i.hasNext()) {
			str += i.next();
			if (i.hasNext())
				str += ", ";
		}
		return str + " }";
	}
	
	public boolean equals(Object o) {
		return (o instanceof OclSet) ? adapter.impl(equalTo((OclOrderedSet)o)).booleanValue() : false;
	}
	
	public Object clone() {
		return adapter.OrderedSet(_implementation);
	}
	
	public Object getImpl() {
		return _implementation;
	}
}
