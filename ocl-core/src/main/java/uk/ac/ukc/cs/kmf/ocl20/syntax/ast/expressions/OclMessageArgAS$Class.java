/**
 *
 *  Class OclMessageArgAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public class OclMessageArgAS$Class
implements
	OclMessageArgAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public OclMessageArgAS$Class() {
		//--- Set property 'expression' from 'OclMessageArgAS' ---
		this.expression = null;
		//--- Set property 'type' from 'OclMessageArgAS' ---
		this.type = null;
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

	/** Property 'type' from 'OclMessageArgAS' */
	protected uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS type;
	/** Get property 'type' from 'OclMessageArgAS' */
	public uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS getType() {
		return type;
	}
	/** Set property 'type' from 'OclMessageArgAS' */
	public void setType(uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS type) { 
		this.type = type;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.expressions.OclMessageArgAS";
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
		OclMessageArgAS$Class obj = new OclMessageArgAS$Class();
		obj.expression = expression==null ? null : this.expression;
		obj.type = type==null ? null : this.type;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.OclMessageArgASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}