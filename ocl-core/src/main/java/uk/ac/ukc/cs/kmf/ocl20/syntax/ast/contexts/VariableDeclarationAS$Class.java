/**
 *
 *  Class VariableDeclarationAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts;

public class VariableDeclarationAS$Class
implements
	VariableDeclarationAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public VariableDeclarationAS$Class() {
		//--- Set property 'name' from 'VariableDeclarationAS' ---
		this.name = new String();
		//--- Set property 'initExp' from 'VariableDeclarationAS' ---
		this.initExp = null;
		//--- Set property 'type' from 'VariableDeclarationAS' ---
		this.type = null;
	}
	/** Specialized constructor */
	public VariableDeclarationAS$Class(String name) {
		//--- Set property 'name' from 'VariableDeclarationAS' ---
		this.name = name;
		//--- Set property 'initExp' from 'VariableDeclarationAS' ---
		this.initExp = null;
		//--- Set property 'type' from 'VariableDeclarationAS' ---
		this.type = null;
	}


	/** Property 'name' from 'VariableDeclarationAS' */
	protected String name;
	/** Get property 'name' from 'VariableDeclarationAS' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'VariableDeclarationAS' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'initExp' from 'VariableDeclarationAS' */
	protected uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.OclExpressionAS initExp;
	/** Get property 'initExp' from 'VariableDeclarationAS' */
	public uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.OclExpressionAS getInitExp() {
		return initExp;
	}
	/** Set property 'initExp' from 'VariableDeclarationAS' */
	public void setInitExp(uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.OclExpressionAS initExp) { 
		this.initExp = initExp;
	}

	/** Property 'type' from 'VariableDeclarationAS' */
	protected uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS type;
	/** Get property 'type' from 'VariableDeclarationAS' */
	public uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS getType() {
		return type;
	}
	/** Set property 'type' from 'VariableDeclarationAS' */
	public void setType(uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS type) { 
		this.type = type;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.contexts.VariableDeclarationAS";
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
		VariableDeclarationAS$Class obj = new VariableDeclarationAS$Class();
		obj.name = name==null ? null : this.name;
		obj.initExp = initExp==null ? null : this.initExp;
		obj.type = type==null ? null : this.type;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts.VariableDeclarationASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
