package uk.ac.kent.cs.ocl20.standard.lib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OclTupleImpl 
	extends OclAnyImpl 
	implements OclTuple 
{
	private java.util.Map _implementation;
	public java.util.Map implementation() {
		return _implementation;
	}

	protected OclTupleImpl(Map m, StdLibAdapter adapter) {
		super(adapter);
		_implementation = m;
	}

	protected OclTupleImpl(OclString[] keys, OclAny[] arr, StdLibAdapter adapter) {
		super(adapter);
		_implementation = new HashMap();
		for (int i = 0; i < keys.length; i++) {
			_implementation.put(keys[i], arr[i]);
		}
	}

	public OclBoolean equalTo(OclTuple tuple2) {
		Set keys1 = _implementation.keySet();
		Set keys2 = ((Map)tuple2.getImpl()).keySet();
		OclBoolean bool = adapter.Set(keys1).equalTo(adapter.Set(keys2)); 
		if (!adapter.impl(bool).booleanValue()) 
			return adapter.Boolean(false);
		Iterator i = keys1.iterator();
		while (i.hasNext()) {
			String key = (String)i.next();
			Object obj1 = _implementation.get(key);
			Object obj2 = ((Map)tuple2.getImpl()).get(key);
			if (!obj1.equals(obj2))
				return adapter.Boolean(false);
		}
		return adapter.Boolean(true);
	}

	public OclBoolean notEqualTo(OclTuple tuple2) {
		return equalTo(tuple2).not();
	}

	public Object getImpl() {
		return implementation();
	}

	public void setImpl(Object impl) {
		_implementation = (Map)impl;
	}

	//--- OclTuple ---
	public OclAny property(OclString s) {
		OclString key = s;
		OclAny o = (OclAny)_implementation.get(key);
		if (o == null)
			//return adapter.Undefined("Tuple doesn't contain element - "+key,null);
			return adapter.Undefined();
		return o;
	}

	public OclAny property(OclString s, Object[] pobjs) {
		return adapter.Undefined(); //property(s);
	}

	public void setProperty(OclString name, Object value) {
		_implementation.put(name, value);
	}

	//--- Object ---
	public String toString() {
		String str = "Tuple { ";
		Iterator i = _implementation.keySet().iterator();
		while (i.hasNext()) {
			OclString s = (OclString) i.next();
			str += adapter.impl(s) + " = " + _implementation.get(s);
			if (i.hasNext())
				str += ", ";
		}
		return str + " }";
	}

	public boolean equals(Object o) {
		return (o instanceof OclTuple) ? adapter.impl(equalTo((OclTuple) o)).booleanValue() : false;
	}

	public Object clone() {
		return new OclTupleImpl(
			(OclString[])_implementation.keySet().toArray(new OclString[0]),
			(OclAny[])_implementation.values().toArray(new OclAny[0]),
			super.adapter
			);
	}

	//--- IOclVisitable ---
	public Object accept(OclVisitor v, Object obj) {
		return v.visit(this, obj);
	}
}
