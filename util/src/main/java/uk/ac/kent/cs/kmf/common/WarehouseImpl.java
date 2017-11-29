/**
 * @author Octavian Patrascoiu
 *
 **/

package uk.ac.kent.cs.kmf.common;

import java.util.*;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;

public class WarehouseImpl
	implements Warehouse 
{
	/** Constructor */
	public WarehouseImpl() {
		this.log = new OutputStreamLog(System.out); 
	}
	public WarehouseImpl(ILog log) {
		this.log = log; 
	}
	
	/** Get log */
	protected ILog log;
	public ILog getLog() {
		return log;
	}
	/** Set log */
	public void setLog(ILog log) {
		this.log = log;
	}

	/** Register a repository for a model */
	public void registerRepository(String modelName, Repository rep) {
		if (repositories.get(modelName) != null) {
			log.reportMessage("Repository for model '"+modelName+"' was replaced");
		}
		repositories.put(modelName, rep);
	}

	/** Get the respository associated to a model */
	public Repository getRepository(String modelName) {
		Repository rep = (Repository)repositories.get(modelName); 
		if (rep == null) {
			log.reportMessage("Repository for model '"+modelName+"' is not registered.");
		}
		return rep;
	}
	
	/** Build an element */
	protected String getModelName(String fullClassName) {
		String result = "";
		int i = fullClassName.indexOf('.');
		if (i != -1) result = fullClassName.substring(0, i);
		return result;
	}
	public Object buildElement(String fullClassName) {
		//--- Get model name ---
		String modelName = getModelName(fullClassName);
		//--- Get repository ---
		Repository rep = (Repository)repositories.get(modelName);
		//--- build an element ---
		if (rep != null) return rep.buildElement(fullClassName);
		else {
			log.reportError("Missing repository for model '"+modelName+"'");
			return null;
		} 
	}
	/** Add an element */
	public void addElement(String fullClassName, Object elem) {
		//--- Get model name ---
		String modelName = getModelName(fullClassName);
		//--- Get repository ---
		Repository rep = (Repository)repositories.get(modelName);
		//--- build an element ---
		if (rep != null) rep.addElement(fullClassName, elem);
		else log.reportError("Missing repository for model '"+modelName+"'");

	}
	/** Remove an element */
	public void removeElement(String fullClassName, Object elem){
		//--- Get model name ---
		String modelName = getModelName(fullClassName);
		//--- Get repository ---
		Repository rep = (Repository)repositories.get(modelName);
		//--- build an element ---
		if (rep != null) rep.removeElement(fullClassName, elem);
		else log.reportError("Missing repository for model '"+modelName+"'");
	}
	/** Get all elements of a given type */
	public List getElements(String fullClassName) {
		//--- Get model name ---
		String modelName = getModelName(fullClassName);
		//--- Get repository ---
		Repository rep = (Repository)repositories.get(modelName);
		//--- build an element ---
		if (rep != null) return rep.getElements(fullClassName);
		else {
			log.reportError("Missing repository for model '"+modelName+"'");
			return null;
		} 
	}
	/** Get all elements of a given type */
	public void removeElements(String fullClassName) {
		//--- Get model name ---
		String modelName = getModelName(fullClassName);
		//--- Get repository ---
		Repository rep = (Repository)repositories.get(modelName);
		//--- build an element ---
		if (rep != null) rep.removeElements(fullClassName);
		else {
			log.reportError("Missing repository for model '"+modelName+"'");
		} 
	}
	/** Get all instances of a given type */
	public List getInstances(String fullClassName) {
		//--- Get model name ---
		String modelName = getModelName(fullClassName);
		//--- Get repository ---
		Repository rep = (Repository)repositories.get(modelName);
		//--- build an element ---
		if (rep != null) return rep.getInstances(fullClassName);
		else {
			log.reportError("Missing repository for model '"+modelName+"'");
			return null;
		} 
	}
	/** Remove all instances of a given type */
	public void removeInstances(String fullClassName) {
		//--- Get model name ---
		String modelName = getModelName(fullClassName);
		//--- Get repository ---
		Repository rep = (Repository)repositories.get(modelName);
		//--- build an element ---
		if (rep != null) rep.removeInstances(fullClassName);
		else {
			log.reportError("Missing repository for model '"+modelName+"'");
		} 
	}

	/** Get all the elements */
	public List getAllElements() {
		List res = new ArrayList();
		Set keys = repositories.keySet();
		Iterator i = keys.iterator();
		while (i.hasNext()) {
			String key = (String)i.next();
			Repository rep = (Repository)repositories.get(key);
			Collection pop = rep.getAllElements(); 
			res.addAll(pop);	
		}
		return res;
	}

	/** Remove all the elements */
	public void removeAllElements() {
		Set keys = repositories.keySet();
		Iterator i = keys.iterator();
		while (i.hasNext()) {
			String key = (String)i.next();
			Repository rep = (Repository)repositories.get(key);
			rep.removeAllElements(); 
		}
	}
	
	/** Get all the elements stored into a repository */
	public List getAll(String modelName) {
		Repository rep = (Repository)repositories.get(modelName); 
		return rep.getAllElements();
	}

	/** toString */
	public String toString() {
		String result = "";
		Set keys = repositories.keySet();
		Iterator i = keys.iterator();
		while (i.hasNext()) {
			String key = (String)i.next();
			result += "Population for key '"+key+"'\n";
		}
		return result;
	}
	/** Map of repositories */
	Map repositories = new LinkedHashMap();
}
