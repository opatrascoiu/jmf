/**
 *
 *  Class OclExpressionAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public class OclExpressionAS$Class
implements
	OclExpressionAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public OclExpressionAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
	}
	/** Specialized constructor */
	public OclExpressionAS$Class(Boolean isMarkedPre) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
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

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.expressions.OclExpressionAS";
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
		OclExpressionAS$Class obj = new OclExpressionAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.parent = parent==null ? null : this.parent;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.OclExpressionASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}