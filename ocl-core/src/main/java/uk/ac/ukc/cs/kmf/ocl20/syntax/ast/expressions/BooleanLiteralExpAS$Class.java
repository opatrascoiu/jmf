/**
 *
 *  Class BooleanLiteralExpAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public class BooleanLiteralExpAS$Class
implements
	BooleanLiteralExpAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public BooleanLiteralExpAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'value' from 'BooleanLiteralExpAS' ---
		this.value = new Boolean(false);
	}
	/** Specialized constructor */
	public BooleanLiteralExpAS$Class(Boolean isMarkedPre, Boolean value) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'value' from 'BooleanLiteralExpAS' ---
		this.value = value;
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

	/** Property 'value' from 'BooleanLiteralExpAS' */
	protected Boolean value;
	/** Get property 'value' from 'BooleanLiteralExpAS' */
		public Boolean getValue() {
		return value;
	}
	/** Set property 'value' from 'BooleanLiteralExpAS' */
		public void setValue(Boolean value) {
		this.value = value;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.expressions.BooleanLiteralExpAS";
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
		BooleanLiteralExpAS$Class obj = new BooleanLiteralExpAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.parent = parent==null ? null : this.parent;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		obj.value = value==null ? null : this.value;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.BooleanLiteralExpASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
