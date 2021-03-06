/**
 *
 *  Class Model$Class.java
 *
 *  Generated by KMFStudio at 22 July 2003 18:50:16
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.yatl.syntax.transformations;

import uk.ac.kent.cs.yatl.YatlVisitable;
import uk.ac.kent.cs.yatl.YatlVisitor;

public class Model$Class
implements
	Model,
    YatlVisitable
{
	/** Default constructor */
	public Model$Class() {
		//--- Set property 'name' from 'NamedElement' ---
		this.name = null;
		//--- Set property 'namespace_' from 'Model' ---
		this.namespace_ = null;
	}
	/** Specialized constructor */
	public Model$Class(String name) {
		//--- Set property 'name' from 'NamedElement' ---
		this.name = name;
		//--- Set property 'namespace_' from 'Model' ---
		this.namespace_ = null;
	}


	/** Property 'name' from 'NamedElement' */
	protected String name;
	/** Get property 'name' from 'NamedElement' */
	public String getName() {
		return name;
	}
	/** Set property 'name' from 'NamedElement' */
	public void setName(String name) {
		this.name = name;
	}

	/** Property 'namespace_' from 'Model' */
	protected Namespace namespace_;
	/** Get property 'namespace_' from 'Model' */
	public Namespace getNamespace_() {
		return namespace_;
	}
	/** Set property 'namespace_' from 'Model' */
	public void setNamespace_(Namespace namespace_) { 
		this.namespace_ = namespace_;
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
		String strId = "syntax.transformations.Model";
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
			if (namespace_ != null)
			this.namespace_.setSourceModel(null);
	}

	/** Clone the object */
	public Object clone() {
		Model$Class obj = new Model$Class();
		obj.name = name==null ? null : this.name;
		obj.namespace_ = namespace_==null ? null : this.namespace_;
		return obj;
	}

	/** Accept 'uk.ac.kent.cs.ktl.syntax.transformations.Model$Visitor' */
	public Object accept(YatlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
