/**
 *
 *  Class OclMessageArgAS$Class.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:50
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.expressions;

import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitable;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor;

public class OclMessageArgAS$Class
implements
	OclMessageArgAS,
    SyntaxVisitable
{
	/** Default constructor */
	public OclMessageArgAS$Class() {
		//--- Set property 'type' from 'OclMessageArgAS' ---
		this.type = null;
		//--- Set property 'expression' from 'OclMessageArgAS' ---
		this.expression = null;
	}


	/** Property 'type' from 'OclMessageArgAS' */
	protected uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS type;
	/** Get property 'type' from 'OclMessageArgAS' */
	public uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS getType() {
		return type;
	}
	/** Set property 'type' from 'OclMessageArgAS' */
	public void setType(uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS type) { 
		this.type = type;
	}

	/** Property 'expression' from 'OclMessageArgAS' */
	protected OclExpressionAS expression;
	/** Get property 'expression' from 'OclMessageArgAS' */
	public OclExpressionAS getExpression() {
		return expression;
	}
	/** Set property 'expression' from 'OclMessageArgAS' */
	public void setExpression(OclExpressionAS expression) { 
		this.expression = expression;
	}

	/** Override toString */
	public String toString() {
		String strId = "ast.expressions.OclMessageArgAS";
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

	/** Accept 'uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclMessageArgAS$Visitor' */
	public Object accept(SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		OclMessageArgAS$Class obj = new OclMessageArgAS$Class();
		obj.type = type==null ? null : this.type;
		obj.expression = expression==null ? null : this.expression;
		return obj;
	}
}
