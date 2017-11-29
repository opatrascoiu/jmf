/**
 * @author Octavian Patrascoiu
 *
 **/

package uk.ac.kent.cs.kmf.common;

import java.lang.reflect.Constructor;
import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;

import uk.ac.kent.cs.kmf.util.ILog;

public abstract class RepositoryImpl
	implements Repository 
{
	/** Build an element */
	public Object buildElement(String fullClassName) {
		Object instance = null;
		try {
			//--- Create an instance using reflection ---
			Class cls = Class.forName(fullClassName);
			Constructor constr = cls.getConstructor(new Class[]{});
			instance = constr.newInstance(new Object[]{});
			//--- Store the value ---
			List elements = (List)population.get(fullClassName);
			if (elements == null) elements = new Vector();
			elements.add(instance);
			population.put(fullClassName, elements);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	/** Add an element */
	public void addElement(String fullClassName, Object elem) {
		//--- Store the value ---
		List elements = (List)population.get(fullClassName);
		if (elements == null) elements = new Vector();
		elements.add(elem);
		population.put(fullClassName, elements);			
	}
	/** Remove an element */
	public void removeElement(String fullClassName, Object elem) {
		//--- Store the value ---
		List elements = (List)population.get(fullClassName);
		if (elements == null) elements = new Vector();
		elements.remove(elem);
		population.put(fullClassName, elements);			
	}
	/** Get all elements of a given type */
	public List getElements(String fullClassName) {
		return (List)population.get(fullClassName);
	}
	/** Remove all elements of a given type */
	public void removeElements(String fullClassName) {
		((List)population.get(fullClassName)).retainAll(new ArrayList());
	}
	/** Check if the first class name is an instance of the second class name */
	protected boolean isInstanceOf(String elemName, String fullClassName) {
		//--- Check if fullClassName is a superinterface of elemName ---
		boolean res = false;
		try {
			res = Class.forName(fullClassName).isAssignableFrom(Class.forName(elemName));
		} catch (Exception e) {
		}
		return res;
	}
	/** Get all instances of a type */
	public List getInstances(String fullClassName) {
		List objs = new Vector();
		Set elemNames = population.keySet();
		Iterator nameIt = elemNames.iterator();
		while (nameIt.hasNext()) {
			String elemName = (String)nameIt.next();
			if (isInstanceOf(elemName, fullClassName)) {
				List elements = (List)population.get(elemName);
				Iterator elemIt = elements.iterator();
				while (elemIt.hasNext()) {
					Object elem = elemIt.next();
					objs.add(elem);
				}
			}
		}
		return objs;
	}
	/** Remove all instances of a type */
	public void removeInstances(String fullClassName) {
		List objs = new Vector();
		Set elemNames = population.keySet();
		Iterator nameIt = elemNames.iterator();
		while (nameIt.hasNext()) {
			String elemName = (String)nameIt.next();
			if (isInstanceOf(elemName, fullClassName)) {
				((List)population.get(elemName)).retainAll(new ArrayList());
			}
		}
	}

	/** Get all the elements */
	public List getAllElements() {
		List res = new ArrayList();
		Iterator i = population.keySet().iterator();
		while (i.hasNext()) {
			String key = (String)i.next();
			res.addAll((Collection)population.get(key));
		}
		return res;
	}

	/** Remove all the elements */
	public void removeAllElements() {
		List res = new ArrayList();
		Iterator i = population.keySet().iterator();
		while (i.hasNext()) {
			String key = (String)i.next();
			((Collection)population.get(key)).retainAll(new ArrayList());
		}
	}
	
	public ILog getLog() {
		return log;
	}
	public void setLog(ILog log) {
		this.log = log;
	}

	public String getModelName() {
		return modelName;
	}
	
	public void saveXMI(String arg0) {
	}

	public Repository loadXMI(String arg0) {
		return null;
	}

	public DefaultMutableTreeNode toJTree() {
		return null;
	}

	/** Storage for factories */
	protected Map factories = new Hashtable();
	/** Storage for population*/
	protected Map population = new HashMap();
	/** Log file */
	protected ILog log;
	/** Model name */
	protected String modelName;
}
