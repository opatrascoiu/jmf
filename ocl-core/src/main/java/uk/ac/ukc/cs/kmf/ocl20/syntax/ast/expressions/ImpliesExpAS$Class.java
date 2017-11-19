/**
 *
 *  Class ImpliesExpAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public class ImpliesExpAS$Class
implements
	ImpliesExpAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public ImpliesExpAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'leftOperand' from 'LogicalExpAS' ---
		this.leftOperand = null;
		//--- Set property 'rightOperand' from 'LogicalExpAS' ---
		this.rightOperand = null;
	}
	/** Specialized constructor */
	public ImpliesExpAS$Class(Boolean isMarkedPre) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'leftOperand' from 'LogicalExpAS' ---
		this.leftOperand = null;
		//--- Set property 'rightOperand' from 'LogicalExpAS' ---
		this.rightOperand = null;
	}


	/** Property 'isMarkedPre' from 'OclExpressionAS' */
	protected Boolean isMarkedPre;
	/** Get property 'isMarkedPre' from 'OclExpressionAS' */
		public Boolean getIsMarkedPre() {
		return isMarkedPre;
	}
	/** Set property 'isMarkedPre' from 'OclExpressionAS' */
		public void setIsMarkedPre(Boolean isMarkedPre) {
		this.isMarkedPre = isMarkedPre;
	}

	/** Property 'parent' from 'OclExpressionAS' */
	protected OclExpressionAS parent;
	/** Get property 'parent' from 'OclExpressionAS' */
	public OclExpressionAS getParent() {
		return parent;
	}
	/** Set property 'parent' from 'OclExpressionAS' */
	public void setParent(OclExpressionAS parent) { 
		this.parent = parent;
	}

	/** Property 'ifExpAS' from 'OclExpressionAS' */
	protected IfExpAS ifExpAS;
	/** Get property 'ifExpAS' from 'OclExpressionAS' */
	public IfExpAS getIfExpAS() {
		return ifExpAS;
	}
	/** Set property 'ifExpAS' from 'OclExpressionAS' */
	public void setIfExpAS(IfExpAS ifExpAS) { 
		this.ifExpAS = ifExpAS;
	}

	/** Property 'leftOperand' from 'LogicalExpAS' */
	protected OclExpressionAS leftOperand;
	/** Get property 'leftOperand' from 'LogicalExpAS' */
	public OclExpressionAS getLeftOperand() {
		return leftOperand;
	}
	/** Set property 'leftOperand' from 'LogicalExpAS' */
	public void setLeftOperand(OclExpressionAS leftOperand) { 
		this.leftOperand = leftOperand;
	}

	/** Property 'rightOperand' from 'LogicalExpAS' */
	protected OclExpressionAS rightOperand;
	/** Get property 'rightOperand' from 'LogicalExpAS' */
	public OclExpressionAS getRightOperand() {
		return rightOperand;
	}
	/** Set property 'rightOperand' from 'LogicalExpAS' */
	public void setRightOperand(OclExpressionAS rightOperand) { 
		this.rightOperand = rightOperand;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.expressions.ImpliesExpAS";
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
		ImpliesExpAS$Class obj = new ImpliesExpAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.parent = parent==null ? null : this.parent;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		obj.leftOperand = leftOperand==null ? null : this.leftOperand;
		obj.rightOperand = rightOperand==null ? null : this.rightOperand;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.ImpliesExpASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}