/**
 *
 *  Class LetExpAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public class LetExpAS$Class
extends
	OclExpressionAS$Class
implements
	LetExpAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public LetExpAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'variables' from 'LetExpAS' ---
		this.variables = new java.util.Vector();
		//--- Set property 'in' from 'LetExpAS' ---
		this.in = null;
	}
	/** Specialized constructor */
	public LetExpAS$Class(Boolean isMarkedPre) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'variables' from 'LetExpAS' ---
		this.variables = new java.util.Vector();
		//--- Set property 'in' from 'LetExpAS' ---
		this.in = null;
	}


	/** Property 'variables' from 'LetExpAS' */
	protected java.util.List variables;
	/** Get property 'variables' from 'LetExpAS' */
	public java.util.List getVariables() {
		return variables;
	}
	/** Set property 'variables' from 'LetExpAS' */
	public void setVariables(java.util.List variables) { 
		this.variables = variables;
	}

	/** Property 'in' from 'LetExpAS' */
	protected OclExpressionAS in;
	/** Get property 'in' from 'LetExpAS' */
	public OclExpressionAS getIn() {
		return in;
	}
	/** Set property 'in' from 'LetExpAS' */
	public void setIn(OclExpressionAS in) { 
		this.in = in;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.expressions.LetExpAS";
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

	/** Delete the object */

	/** Clone the object */
	public Object clone() {
		LetExpAS$Class obj = new LetExpAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.parent = parent==null ? null : this.parent;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		obj.variables = variables==null ? null : (java.util.List)((java.util.Vector)this.variables).clone();
		obj.in = in==null ? null : this.in;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.LetExpASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
