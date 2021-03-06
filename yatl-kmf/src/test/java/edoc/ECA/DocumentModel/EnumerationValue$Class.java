/**
 *
 *  Class EnumerationValue$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:35
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.DocumentModel;

public class EnumerationValue$Class
implements
	EnumerationValue,
    edoc.EdocVisitable
{
	/** Default constructor */
	public EnumerationValue$Class() {
		//--- Set property 'name' from 'EnumerationValue' ---
		this.name = null;
		//--- Set property 'enumeration' from 'EnumerationValue' ---
		this.enumeration = null;
		//--- Set property 'initialFor' from 'EnumerationValue' ---
		this.initialFor = null;
	}
	/** Specialized constructor */
	public EnumerationValue$Class(String name) {
		//--- Set property 'name' from 'EnumerationValue' ---
		this.name = name;
		//--- Set property 'enumeration' from 'EnumerationValue' ---
		this.enumeration = null;
		//--- Set property 'initialFor' from 'EnumerationValue' ---
		this.initialFor = null;
	}


	/** Property 'name' from 'EnumerationValue' */
	protected String name;
	/** Get property 'name' from 'EnumerationValue' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'EnumerationValue' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'enumeration' from 'EnumerationValue' */
	protected Enumeration_ enumeration;
	/** Get property 'enumeration' from 'EnumerationValue' */
	public Enumeration_ getEnumeration() {
		return enumeration;
	}
	/** Set property 'enumeration' from 'EnumerationValue' */
	public void setEnumeration(Enumeration_ enumeration) { 
		this.enumeration = enumeration;
	}

	/** Property 'initialFor' from 'EnumerationValue' */
	protected Enumeration_ initialFor;
	/** Get property 'initialFor' from 'EnumerationValue' */
	public Enumeration_ getInitialFor() {
		return initialFor;
	}
	/** Set property 'initialFor' from 'EnumerationValue' */
	public void setInitialFor(Enumeration_ initialFor) { 
		this.initialFor = initialFor;
	}

	/** The id */
	protected String id;
	/** Get the id */
	public String getId() {
		return id;
	}
	/** Set the id */
	public void setId(String id) {
		this.id = id;
	}

	/** Override toString */
	public String toString() {
		String strId = "edoc.ECA.DocumentModel.EnumerationValue";
		String name = null;
		try {
			java.lang.Class cls = this.getClass();
			java.lang.reflect.Method method = cls.getMethod("getName", new java.lang.Class[] {});
			name = (String) method.invoke(this, new Object[] {});
			if (name != null && name.length()==0) name = null;
		} catch (Exception e) {
		}
		if (name == null)
			return strId+" 'id-"+getId()+"'";
		else
			return strId+" '"+name+"-"+getId()+"'";
	}

	/** Delete the object */
	public void delete() {
		if (enumeration != null)
			this.enumeration.getValues().remove(this);
		if (initialFor != null)
			this.initialFor.setInitial(null);
	}

	/** Clone the object */
	public Object clone() {
		EnumerationValue$Class obj = new EnumerationValue$Class();
		obj.name = name==null ? null : this.name;
		obj.enumeration = enumeration==null ? null : this.enumeration;
		obj.initialFor = initialFor==null ? null : this.initialFor;
		return obj;
	}

	/** Accept 'edoc.ECA.DocumentModel.EnumerationValueVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
