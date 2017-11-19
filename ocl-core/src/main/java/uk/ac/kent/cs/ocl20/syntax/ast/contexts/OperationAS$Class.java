/**
 *
 *  Class OperationAS$Class.java
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

public class OperationAS$Class
implements
	OperationAS,
    SyntaxVisitable
{
	/** Default constructor */
	public OperationAS$Class() {
		//--- Set property 'pathName' from 'OperationAS' ---
		this.pathName = new Vector();
		//--- Set property 'name' from 'OperationAS' ---
		this.name = new String();
		//--- Set property 'type' from 'OperationAS' ---
		this.type = null;
		//--- Set property 'parameters' from 'OperationAS' ---
		this.parameters = new Vector();
	}
	/** Specialized constructor */
	public OperationAS$Class(List pathName, String name) {
		//--- Set property 'pathName' from 'OperationAS' ---
		this.pathName = pathName;
		//--- Set property 'name' from 'OperationAS' ---
		this.name = name;
		//--- Set property 'type' from 'OperationAS' ---
		this.type = null;
		//--- Set property 'parameters' from 'OperationAS' ---
		this.parameters = new Vector();
	}


	/** Property 'pathName' from 'OperationAS' */
	protected List pathName;
	/** Get property 'pathName' from 'OperationAS' */
	public List getPathName() {
		return pathName;
	}
	/** Set property 'pathName' from 'OperationAS' */
	public void setPathName(List pathName) {
		this.pathName = pathName;
	}

	/** Property 'name' from 'OperationAS' */
	protected String name;
	/** Get property 'name' from 'OperationAS' */
	public String getName() {
		return name;
	}
	/** Set property 'name' from 'OperationAS' */
	public void setName(String name) {
		this.name = name;
	}

	/** Property 'type' from 'OperationAS' */
	protected uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS type;
	/** Get property 'type' from 'OperationAS' */
	public uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS getType() {
		return type;
	}
	/** Set property 'type' from 'OperationAS' */
	public void setType(uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS type) { 
		this.type = type;
	}

	/** Property 'parameters' from 'OperationAS' */
	protected List parameters;
	/** Get property 'parameters' from 'OperationAS' */
	public List getParameters() {
		return parameters;
	}
	/** Set property 'parameters' from 'OperationAS' */
	public void setParameters(List parameters) { 
		this.parameters = parameters;
	}

	/** Override toString */
	public String toString() {
		String strId = "ast.contexts.OperationAS";
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

	/** Accept 'uk.ac.kent.cs.ocl20.syntax.ast.contexts.OperationAS$Visitor' */
	public Object accept(SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		OperationAS$Class obj = new OperationAS$Class();
		obj.pathName = pathName==null ? null : (List)((Vector)this.pathName).clone();
		obj.name = name==null ? null : this.name;
		obj.type = type==null ? null : this.type;
		obj.parameters = parameters==null ? null : (List)((Vector)this.parameters).clone();
		return obj;
	}
}