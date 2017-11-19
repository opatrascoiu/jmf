/**
 *
 *  Class NewExp$Class.java
 *
 *  Generated by KMFStudio at 22 July 2003 18:50:16
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.yatl.syntax.statements;

import java.util.List;
import java.util.Vector;

import uk.ac.kent.cs.yatl.YatlVisitable;
import uk.ac.kent.cs.yatl.YatlVisitor;

public class NewExp$Class
extends
	Expression$Class
implements
	NewExp,
    YatlVisitable
{
	/** Default constructor */
	public NewExp$Class() {
		//--- Set property 'operands' from 'Expression' ---
		this.operands = new Vector();
		//--- Set property 'type' from 'Expression' ---
		this.type = null;
		//--- Set property 'loopStm' from 'Expression' ---
		this.loopStm = null;
		//--- Set property 'ifStm' from 'Expression' ---
		this.ifStm = null;
		//--- Set property 'expressionStm' from 'Expression' ---
		this.expressionStm = null;
		//--- Set property 'pathName' from 'NewExp' ---
		this.pathName = new Vector();
	}
	/** Specialized constructor */
	public NewExp$Class(List operands, Object type, List pathName) {
		//--- Set property 'operands' from 'Expression' ---
		this.operands = operands;
		//--- Set property 'type' from 'Expression' ---
		this.type = type;
		//--- Set property 'loopStm' from 'Expression' ---
		this.loopStm = null;
		//--- Set property 'ifStm' from 'Expression' ---
		this.ifStm = null;
		//--- Set property 'expressionStm' from 'Expression' ---
		this.expressionStm = null;
		//--- Set property 'pathName' from 'NewExp' ---
		this.pathName = pathName;
	}


	/** Property 'pathName' from 'NewExp' */
	protected List pathName;
	/** Get property 'pathName' from 'NewExp' */
	public List getPathName() {
		return pathName;
	}
	/** Set property 'pathName' from 'NewExp' */
	public void setPathName(List pathName) {
		this.pathName = pathName;
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
		String strId = "syntax.statements.NewExp";
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
			if (loopStm != null)
			this.loopStm.setExpression(null);
			if (ifStm != null)
			this.ifStm.setExpression(null);
			if (expressionStm != null)
			this.expressionStm.setExpression(null);
	}

	/** Clone the object */
	public Object clone() {
		NewExp$Class obj = new NewExp$Class();
		obj.operands = operands==null ? null : (List)((Vector)this.operands).clone();
		obj.type = type==null ? null : this.type;
		obj.loopStm = loopStm==null ? null : this.loopStm;
		obj.ifStm = ifStm==null ? null : this.ifStm;
		obj.expressionStm = expressionStm==null ? null : this.expressionStm;
		obj.pathName = pathName==null ? null : (List)((Vector)this.pathName).clone();
		return obj;
	}

	/** Accept 'uk.ac.kent.cs.ktl.syntax.statements.NewExp$Visitor' */
	public Object accept(YatlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
