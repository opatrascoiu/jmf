/**
 * @author Octavian Patrascoiu
 *
 **/

package uk.ac.kent.cs.kmf.common;

import java.util.List;

import uk.ac.kent.cs.kmf.util.ILog;

public interface Warehouse {
	/** Build an element */
	public Object buildElement(String fullClassName);
	/** Add an element */
	public void addElement(String fullClassName, Object elem);
	/** Remove an element */
	public void removeElement(String fullClassName, Object elem);
	/** Get all elements of a given type */
	public List getElements(String fullClassName);
	/** Remove all elements of a given type */
	public void removeElements(String fullClassName);
	/** Get all instances of a given type */
	public List getInstances(String fullClassName);
	/** Remove all instances of a given type */
	public void removeInstances(String fullClassName);
	/** Get all the elements */
	public List getAllElements();
	/** Get all the elements */
	public void removeAllElements();
	
	/** Get log */
	public ILog getLog();
	/** Set log */
	public void setLog(ILog log);
	
	/** Add a repository for a given model */
	public void registerRepository(String modelName, Repository rep);

	/** Get the repository of a given model */
	public Repository getRepository(String modelName);

	/** Get all the elements stored into a given repository */
	public List getAll(String modelName);	
}
