/**
 *
 *  Class CollectionRangeAS$Class.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:50
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.expressions;

import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitable;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor;

public class CollectionRangeAS$Class
implements
	CollectionRangeAS,
    SyntaxVisitable
{
	/** Default constructor */
	public CollectionRangeAS$Class() {
		//--- Set property 'last' from 'CollectionRangeAS' ---
		this.last = null;
		//--- Set property 'first' from 'CollectionRangeAS' ---
		this.first = null;
	}


	/** Property 'last' from 'CollectionRangeAS' */
	protected OclExpressionAS last;
	/** Get property 'last' from 'CollectionRangeAS' */
	public OclExpressionAS getLast() {
		return last;
	}
	/** Set property 'last' from 'CollectionRangeAS' */
	public void setLast(OclExpressionAS last) { 
		this.last = last;
	}

	/** Property 'first' from 'CollectionRangeAS' */
	protected OclExpressionAS first;
	/** Get property 'first' from 'CollectionRangeAS' */
	public OclExpressionAS getFirst() {
		return first;
	}
	/** Set property 'first' from 'CollectionRangeAS' */
	public void setFirst(OclExpressionAS first) { 
		this.first = first;
	}

	/** Override toString */
	public String toString() {
		String strId = "ast.expressions.CollectionRangeAS";
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

	/** Accept 'uk.ac.kent.cs.ocl20.syntax.ast.expressions.CollectionRangeAS$Visitor' */
	public Object accept(SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		CollectionRangeAS$Class obj = new CollectionRangeAS$Class();
		obj.last = last==null ? null : this.last;
		obj.first = first==null ? null : this.first;
		return obj;
	}
}
