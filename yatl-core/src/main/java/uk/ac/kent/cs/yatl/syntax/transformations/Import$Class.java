/**
 *
 *  Class Import$Class.java
 *
 *  Generated by KMFStudio at 22 July 2003 18:50:16
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.yatl.syntax.transformations;

import uk.ac.kent.cs.yatl.YatlVisitable;
import uk.ac.kent.cs.yatl.YatlVisitor;

public class Import$Class
implements
	Import,
    YatlVisitable
{
	/** Default constructor */
	public Import$Class() {
		//--- Set property 'name' from 'Import' ---
		this.name = null;
		//--- Set property 'unit' from 'Import' ---
		this.unit = null;
	}
	/** Specialized constructor */
	public Import$Class(String name) {
		//--- Set property 'name' from 'Import' ---
		this.name = name;
		//--- Set property 'unit' from 'Import' ---
		this.unit = null;
	}


	/** Property 'name' from 'Import' */
	protected String name;
	/** Get property 'name' from 'Import' */
	public String getName() {
		return name;
	}
	/** Set property 'name' from 'Import' */
	public void setName(String name) {
		this.name = name;
	}

	/** Property 'unit' from 'Import' */
	protected Unit unit;
	/** Get property 'unit' from 'Import' */
	public Unit getUnit() {
		return unit;
	}
	/** Set property 'unit' from 'Import' */
	public void setUnit(Unit unit) { 
		this.unit = unit;
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
		String strId = "syntax.transformations.Import";
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
		if (unit != null)
			this.unit.getImports().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		Import$Class obj = new Import$Class();
		obj.name = name==null ? null : this.name;
		obj.unit = unit==null ? null : this.unit;
		return obj;
	}

	/** Accept 'uk.ac.kent.cs.ktl.syntax.transformations.Import$Visitor' */
	public Object accept(YatlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
