package uk.ac.kent.cs.ocl20.standard.lib;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class OclSetImpl 
	extends OclCollectionImpl 
	implements OclSet 
{
	protected OclSetImpl(Object[] array, StdLibAdapter adapter) {
		super(adapter);
		_implementation = new LinkedHashSet();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				if (!adapter.impl(includes(array[i])).booleanValue()) 
					_implementation.add(array[i]);
			}
		}
	}

	protected java.util.Set _implementation;
	protected java.util.Collection implementation() {
		return _implementation;
	}
	protected java.util.Set set_impl() {
		return _implementation;
	}

	public OclBoolean equalTo(OclSet set2) {
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
	
	public OclBoolean notEqualTo(OclSet set2) {
		return equalTo(set2).not();
	}

	//--- OclSet ---
	public OclSet union(OclSet set2) {
		OclSet s = this.union(set2.asBag()).asSet();
		return s;
	}

	public OclBag union(OclBag bag) {
		OclBagImpl b = (OclBagImpl) adapter.Bag(set_impl());
		b.implementation().addAll(((OclBagImpl) bag).implementation());
		return b;
	}

	public OclSet union(OclOrderedSet set2) {
		OclSet s = this.union(set2.asBag()).asSet();
		return s;
	}

	public OclSet intersection(OclSet set2) {
		OclSetImpl s = (OclSetImpl) adapter.Set();
		Set s1 = adapter.impl(this);
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

	public OclSet intersection(OclOrderedSet set2) {
		return this.intersection(set2.asSet());
	}

	public OclSet subtract(OclSet set2) {
		OclSetImpl s = (OclSetImpl) adapter.Set();
		Set set = adapter.impl(this);
		Set s2 = adapter.impl(set2);
		Iterator i1 = set.iterator();
		while (i1.hasNext()) {
			Object o = (Object) i1.next();
			if (!s2.contains(o))
				adapter.impl(s).add(o);
		}
		return s;
	}

	public OclSet including(Object object) {
		OclSetImpl s = (OclSetImpl) adapter.Set(set_impl());
		if (!adapter.impl(includes(object)).booleanValue()) 
			s.set_impl().add(object);
		return s;
	}

	public OclSet excluding(Object object) {
		OclSetImpl s = (OclSetImpl) adapter.Set(set_impl());
		s.set_impl().remove(object);
		return s;
	}

	public OclSet symmetricDifference(OclSet set2) {
		OclSetImpl s = (OclSetImpl) adapter.Set();
		Set s1 = adapter.impl(this);
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

	public OclInteger count(Object object) {
		return super.count(object);
	}

	public OclSet flatten() {
		OclSetImpl flat = new OclSetImpl(new Object[0], adapter);
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

	public OclSequence asSequence() {
		return adapter.Sequence(set_impl());
	}

	public OclBag asBag() {
		return adapter.Bag(set_impl());
	}

	public OclSet asSet() {
		return adapter.Set(set_impl());
	}

	public OclOrderedSet asOrderedSet() {
		return adapter.OrderedSet(set_impl());
	}

	//--- Object ---
	public String toString() {
		String str = "Set { ";
		java.util.Iterator i = _implementation.iterator();
		while (i.hasNext()) {
			str += i.next();
			if (i.hasNext())
				str += ", ";
		}
		return str + " }";
	}

	public Object clone() {
		return adapter.Set(set_impl());
	}

	public boolean equals(Object o) {
		return (o instanceof OclSet) ? adapter.impl(equalTo((OclSet)o)).booleanValue() : false;
	}

	//--- IOclVisitable ---
	public Object accept(OclVisitor v, Object obj) {
		return v.visit(this, obj);
	}
	public Object getImpl() {
		return _implementation;
	}
}
