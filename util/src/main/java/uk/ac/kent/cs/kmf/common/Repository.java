/**
 * @author Octavian Patrascoiu
 *
 **/

package uk.ac.kent.cs.kmf.common;

import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;

import uk.ac.kent.cs.kmf.util.ILog;

public interface Repository {
	/** Get model name */
	public String getModelName();
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
	
	/** Save content as XMI */
	public void saveXMI(String fileName);
	/** Create a new repository from an XMI file */
	public Repository loadXMI(String fileName);
	
	/** Log file */
	public ILog getLog();
	public void setLog(ILog log);
	
	/** Create the JTree */
	public DefaultMutableTreeNode toJTree();
}
