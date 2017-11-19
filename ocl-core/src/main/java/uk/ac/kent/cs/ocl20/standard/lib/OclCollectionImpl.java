package uk.ac.kent.cs.ocl20.standard.lib;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

abstract public class OclCollectionImpl
	implements OclCollection 
{
	protected StdLibAdapter adapter;
	protected OclCollectionImpl(StdLibAdapter adapter) {
		this.adapter = adapter;
	}

	abstract java.util.Collection implementation();

	//--- Collection ---
	public OclInteger size() {
		int size = implementation().size();
		return adapter.Integer(size);
	}
	public OclBoolean includes(Object object) {
		Iterator i = implementation().iterator();
		boolean found = false;
		while (i.hasNext())
			if (object.equals(i.next())) {
				found = true;
				break;
			}
		return adapter.Boolean(found);
	}
	public OclBoolean excludes(Object object) {
		boolean found = adapter.impl(includes(object)).booleanValue();
		return adapter.Boolean(!found);
	}

	/* post: result = collection->iterate( elem; acc : Integer = 0 |
	                                        if elem = object then acc + 1 else acc endif )
	*/
	public OclInteger count(Object object) {
		int count = 0;
		Iterator i = implementation().iterator();
		while (i.hasNext())
			if (object.equals(i.next()))
				count++;
		return adapter.Integer(count);
	}

	public OclBoolean includesAll(OclCollection col) {
		return adapter.Boolean(implementation().containsAll((java.util.Collection) col.getImpl()));
	}

	public OclBoolean excludesAll(OclCollection col) {
		Iterator i = ((java.util.Collection)col.getImpl()).iterator();
		while (i.hasNext()) {
			if (this.implementation().contains(i.next())) {
				return adapter.Boolean(false);
			}
		}
		return adapter.Boolean(true);
	}

	public OclBoolean isEmpty() {
		return adapter.Boolean(implementation().isEmpty());
	}

	public OclBoolean notEmpty() {
		return isEmpty().not();
	}

	/* post: result = collection->iterate( elem; acc : T = 0 | acc + elem ) */
	/* elem must have an operation 'add(T)' defined. */
	//T sum();
	public Object sum() {
		if (this.implementation().isEmpty())
			return adapter.Integer(0);

		Iterator i = implementation().iterator();
		Object first = i.next();

		Method addMethod = getAddMethod(first);
		if (addMethod != null) {
			Object acc = null;
			try {
				Method m = first.getClass().getMethod("clone", new Class[] {});
				acc = m.invoke(first, new Object[] {});
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (acc != null) {
				try {
				while (i.hasNext()) {
					OclAny next = (OclAny) i.next();
					acc = addMethod.invoke(acc, new Object[] {next});
				}
				return acc;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return adapter.Undefined();
	}

	private Method getAddMethod(Object o) {
		Method[] meths = o.getClass().getMethods();
		for (int i = 0; i < meths.length; i++) {
			if (meths[i].getName().equals("add")) {
				if (meths[i].getParameterTypes().length == 1) {
					Class x = meths[i].getParameterTypes()[0];
					if (x.isAssignableFrom(o.getClass())) {
						return meths[i];
					}
				}
			}
		}
		return null;
	}

	public OclSet product(OclCollection col2) {
		Iterator i1 = this.implementation().iterator();
		Collection c2 = (java.util.Collection) col2.getImpl();
		Set result = new LinkedHashSet();
		while (i1.hasNext()) {
			OclAny o1 = (OclAny) i1.next();
			Iterator i2 = c2.iterator();
			while (i2.hasNext()) {
				OclAny o2 = (OclAny) i2.next();
				result.add(adapter.Tuple(new OclString[] { adapter.String("first"), adapter.String("second")}, new OclAny[] { o1, o2 }));
			}
		}
		return adapter.Set(result);
	}

	//--- Object ---
	abstract public Object clone();

}
