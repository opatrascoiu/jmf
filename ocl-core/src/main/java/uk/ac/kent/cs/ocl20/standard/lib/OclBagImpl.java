package uk.ac.kent.cs.ocl20.standard.lib;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class OclBagImpl 
	extends OclCollectionImpl 
	implements OclBag 
{
	protected OclBagImpl(Object[] array, StdLibAdapter adapter) {
		super(adapter);
		_implementation = new Vector(Arrays.asList(array));
	}

	protected java.util.Collection _implementation;
	protected java.util.Collection implementation() {
		return _implementation;
	}
	protected java.util.Collection bag_impl() {
		return _implementation;
	}

	public OclBoolean equalTo(OclBag bag2) {
		if (adapter.impl(this.size()) != adapter.impl(bag2.size()))
			return adapter.Boolean(false);
		Collection b = implementation();
		Iterator it = b.iterator();
		while (it.hasNext()) {
			Object ob = (Object) it.next();
			if (!adapter.impl(count(ob).equalTo(bag2.count(ob))).booleanValue())
				return adapter.Boolean(false);
		}
		return adapter.Boolean(true);
	}

	public OclBoolean notEqualTo(OclBag bag2) {
		return equalTo(bag2).not();
	}

	public OclBag union(OclBag bag2) {
		OclBagImpl b = (OclBagImpl) adapter.Bag(implementation());
		b.implementation().addAll(((OclBagImpl) bag2).implementation());
		return b;
	}

	public OclBag union(OclSet set) {
		OclBagImpl b = (OclBagImpl) adapter.Bag(implementation());
		b.implementation().addAll(((OclSetImpl) set).set_impl());
		return b;
	}

	public OclBag union(OclOrderedSet set) {
		OclBagImpl b = (OclBagImpl) adapter.Bag(implementation());
		b.implementation().addAll(((OclSetImpl) set).set_impl());
		return b;
	}

	public OclBag intersection(OclBag bag2) {
		OclBagImpl b = (OclBagImpl) adapter.Bag();
		Collection b1 = adapter.impl((OclBag) ((OclBagImpl) this).clone());
		Collection b2 = adapter.impl((OclBag) ((OclBagImpl) bag2).clone());
		Iterator i1 = b1.iterator();
		while (i1.hasNext()) {
			Object o = (Object) i1.next();
			if (adapter.impl(bag2.includes(o)).booleanValue()) {
				adapter.impl(b).add(o);
				i1.remove();
				b2.remove(o);
			}
		}
		Iterator i2 = b2.iterator();
		while (i2.hasNext()) {
			Object o = (Object) i2.next();
			if (adapter.impl(this.includes(o)).booleanValue())
				adapter.impl(b).add(o);
		}
		return b;
	}

	public OclSet intersection(OclSet set) {
		return this.intersection(set.asBag()).asSet();
	}

	public OclOrderedSet intersection(OclOrderedSet set) {
		return this.intersection(set.asOrderedSet());
	}

	public OclBag including(Object object) {
		OclBagImpl b = (OclBagImpl) adapter.Bag(implementation());
		b.implementation().add(object);
		return b;
	}

	public OclBag excluding(Object object) {
		OclBagImpl b = (OclBagImpl) adapter.Bag(implementation());
		Vector v = new Vector();
		v.add(object);
		b.implementation().removeAll(v);
		return b;
	}

	public OclInteger count(Object object) {
		return super.count(object);
	}

	public OclBag flatten() {
		OclBag flat = adapter.Bag(new Object[0]);
		java.util.Iterator i = _implementation.iterator();
		while (i.hasNext()) {
			Object elem = (Object) i.next();
			if (elem instanceof OclBag) {
				((Collection)flat.getImpl()).addAll(adapter.impl(((OclBag) elem).flatten()));
			} else if (elem instanceof OclSet) {
				((Collection)flat.getImpl()).addAll(adapter.impl(((OclSet) elem).flatten()));
			} else if (elem instanceof OclSequence) {
				((Collection)flat.getImpl()).addAll(adapter.impl(((OclSequence) elem).flatten()));
			} else {
				((Collection)flat.getImpl()).add(elem);
			}
		}
		return flat;
	}

	public OclSequence asSequence() {
		return adapter.Sequence(implementation());
	}

	public OclSet asSet() {
		return adapter.Set(implementation());
	}
	
	public OclBag asBag() {
		return adapter.Bag(implementation());
	}
	
	public OclOrderedSet asOrderedSet() {
		return adapter.OrderedSet(implementation());
	}

	//--- IOclVisitable ---
	public Object accept(OclVisitor v, Object obj) {
		return v.visit(this, obj);
	}

	//--- Object ---
	public String toString() {
		String str = "Bag { ";
		java.util.Iterator i = _implementation.iterator();
		while (i.hasNext()) {
			str += i.next();
			if (i.hasNext())
				str += ", ";
		}
		return str + " }";
	}

	public Object clone() {
		return adapter.Bag(implementation());
	}

	public boolean equals(Object o) {
		if (o instanceof OclBag) {
			OclBoolean b = equalTo((OclBag)o);
			return adapter.impl(b).booleanValue();
		}
		return false;
	}

	public Object getImpl() {
		return _implementation;
	}

}
