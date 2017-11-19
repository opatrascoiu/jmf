/**
 *
 *  Class RealLiteralExpAS$Class.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.expressions;

import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitable;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor;

public class RealLiteralExpAS$Class
implements
	RealLiteralExpAS,
    SyntaxVisitable
{
	/** Default constructor */
	public RealLiteralExpAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'value' from 'RealLiteralExpAS' ---
		this.value = new Double(0);
	}
	/** Specialized constructor */
	public RealLiteralExpAS$Class(Boolean isMarkedPre, Double value) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'value' from 'RealLiteralExpAS' ---
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

	/** Property 'value' from 'RealLiteralExpAS' */
	protected Double value;
	/** Get property 'value' from 'RealLiteralExpAS' */
	public Double getValue() {
		return value;
	}
	/** Set property 'value' from 'RealLiteralExpAS' */
	public void setValue(Double value) {
		this.value = value;
	}

	/** Override toString */
	public String toString() {
		String strId = "ast.expressions.RealLiteralExpAS";
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

	/** Accept 'uk.ac.kent.cs.ocl20.syntax.ast.expressions.RealLiteralExpAS$Visitor' */
	public Object accept(SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		RealLiteralExpAS$Class obj = new RealLiteralExpAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.parent = parent==null ? null : this.parent;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		obj.value = value==null ? null : this.value;
		return obj;
	}
}