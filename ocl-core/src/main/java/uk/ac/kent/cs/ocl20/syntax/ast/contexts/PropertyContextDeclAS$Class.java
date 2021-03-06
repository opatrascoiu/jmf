/**
 *
 *  Class PropertyContextDeclAS$Class.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.contexts;

import java.util.List;
import java.util.Vector;

import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitable;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor;

public class PropertyContextDeclAS$Class
extends
	ContextDeclarationAS$Class
implements
	PropertyContextDeclAS,
    SyntaxVisitable
{
	/** Default constructor */
	public PropertyContextDeclAS$Class() {
		//--- Set property 'constraints' from 'ContextDeclarationAS' ---
		this.constraints = new Vector();
		//--- Set property 'pathName' from 'PropertyContextDeclAS' ---
		this.pathName = new Vector();
		//--- Set property 'name' from 'PropertyContextDeclAS' ---
		this.name = new String();
		//--- Set property 'type' from 'PropertyContextDeclAS' ---
		this.type = null;
	}
	/** Specialized constructor */
	public PropertyContextDeclAS$Class(List pathName, String name) {
		//--- Set property 'constraints' from 'ContextDeclarationAS' ---
		this.constraints = new Vector();
		//--- Set property 'pathName' from 'PropertyContextDeclAS' ---
		this.pathName = pathName;
		//--- Set property 'name' from 'PropertyContextDeclAS' ---
		this.name = name;
		//--- Set property 'type' from 'PropertyContextDeclAS' ---
		this.type = null;
	}


	/** Property 'pathName' from 'PropertyContextDeclAS' */
	protected List pathName;
	/** Get property 'pathName' from 'PropertyContextDeclAS' */
	public List getPathName() {
		return pathName;
	}
	/** Set property 'pathName' from 'PropertyContextDeclAS' */
	public void setPathName(List pathName) {
		this.pathName = pathName;
	}

	/** Property 'name' from 'PropertyContextDeclAS' */
	protected String name;
	/** Get property 'name' from 'PropertyContextDeclAS' */
	public String getName() {
		return name;
	}
	/** Set property 'name' from 'PropertyContextDeclAS' */
	public void setName(String name) {
		this.name = name;
	}

	/** Property 'type' from 'PropertyContextDeclAS' */
	protected uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS type;
	/** Get property 'type' from 'PropertyContextDeclAS' */
	public uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS getType() {
		return type;
	}
	/** Set property 'type' from 'PropertyContextDeclAS' */
	public void setType(uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS type) { 
		this.type = type;
	}

	/** Get the location */
	protected Object location;
	public Object getLocation() {
		return location;
	}
	/** Set the location */
	public void setLocation(Object location) {
		this.location = location;
	}
	
	/** Override toString */
	public String toString() {
		String strId = "ast.contexts.PropertyContextDeclAS";
		String name = null;
		try {
			java.lang.Class cls = this.getClass();
			java.lang.reflect.Method method = cls.getMethod("getName", new java.lang.Class[] {});
			name = (String) method.invoke(this, new Object[] {});
			if (name != null && name.length()==0) name = null;
		} catch (Exception e) {
		}
		if (name == null)
			return strId;
		else
			return strId+" '"+name+"'";
	}

	/** Accept 'uk.ac.kent.cs.ocl20.syntax.ast.contexts.PropertyContextDeclAS$Visitor' */
	public Object accept(SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		PropertyContextDeclAS$Class obj = new PropertyContextDeclAS$Class();
		obj.constraints = constraints==null ? null : (List)((Vector)this.constraints).clone();
		obj.pathName = pathName==null ? null : (List)((Vector)this.pathName).clone();
		obj.name = name==null ? null : this.name;
		obj.type = type==null ? null : this.type;
		return obj;
	}
}
