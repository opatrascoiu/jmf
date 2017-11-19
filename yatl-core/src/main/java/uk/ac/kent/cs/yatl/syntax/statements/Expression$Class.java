/**
 *
 *  Class Expression$Class.java
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

public class Expression$Class
implements
	Expression,
    YatlVisitable
{
	/** Default constructor */
	public Expression$Class() {
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
	}
	/** Specialized constructor */
	public Expression$Class(List operands, Object type) {
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
	}


	/** Property 'operands' from 'Expression' */
	protected List operands;
	/** Get property 'operands' from 'Expression' */
	public List getOperands() {
		return operands;
	}
	/** Set property 'operands' from 'Expression' */
	public void setOperands(List operands) {
		this.operands = operands;
	}

	/** Property 'type' from 'Expression' */
	protected Object type;
	/** Get property 'type' from 'Expression' */
	public Object getType() {
		return type;
	}
	/** Set property 'type' from 'Expression' */
	public void setType(Object type) {
		this.type = type;
	}

	/** Property 'loopStm' from 'Expression' */
	protected LoopStm loopStm;
	/** Get property 'loopStm' from 'Expression' */
	public LoopStm getLoopStm() {
		return loopStm;
	}
	/** Set property 'loopStm' from 'Expression' */
	public void setLoopStm(LoopStm loopStm) { 
		this.loopStm = loopStm;
	}

	/** Property 'ifStm' from 'Expression' */
	protected IfStm ifStm;
	/** Get property 'ifStm' from 'Expression' */
	public IfStm getIfStm() {
		return ifStm;
	}
	/** Set property 'ifStm' from 'Expression' */
	public void setIfStm(IfStm ifStm) { 
		this.ifStm = ifStm;
	}

	/** Property 'expressionStm' from 'Expression' */
	protected ExpressionStm expressionStm;
	/** Get property 'expressionStm' from 'Expression' */
	public ExpressionStm getExpressionStm() {
		return expressionStm;
	}
	/** Set property 'expressionStm' from 'Expression' */
	public void setExpressionStm(ExpressionStm expressionStm) { 
		this.expressionStm = expressionStm;
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
		String strId = "syntax.statements.Expression";
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
		Expression$Class obj = new Expression$Class();
		obj.operands = operands==null ? null : (List)((Vector)this.operands).clone();
		obj.type = type==null ? null : this.type;
		obj.loopStm = loopStm==null ? null : this.loopStm;
		obj.ifStm = ifStm==null ? null : this.ifStm;
		obj.expressionStm = expressionStm==null ? null : this.expressionStm;
		return obj;
	}

	/** Accept 'uk.ac.kent.cs.ktl.syntax.statements.Expression$Visitor' */
	public Object accept(YatlVisitor v, Object data) {
		return v.visit(this, data);
	}
}