/**
 *
 *  Class ConstraintAS$Class.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.contexts;

import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitable;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor;

public class ConstraintAS$Class
implements
	ConstraintAS,
    SyntaxVisitable
{
	/** Default constructor */
	public ConstraintAS$Class() {
		//--- Set property 'name' from 'ConstraintAS' ---
		this.name = new String();
		//--- Set property 'kind' from 'ConstraintAS' ---
		this.kind = null;
		//--- Set property 'bodyExpression' from 'ConstraintAS' ---
		this.bodyExpression = null;
		//--- Set property 'defVariable' from 'ConstraintAS' ---
		this.defVariable = null;
		//--- Set property 'defOperation' from 'ConstraintAS' ---
		this.defOperation = null;
	}
	/** Specialized constructor */
	public ConstraintAS$Class(String name, ConstraintKindAS kind) {
		//--- Set property 'name' from 'ConstraintAS' ---
		this.name = name;
		//--- Set property 'kind' from 'ConstraintAS' ---
		this.kind = kind;
		//--- Set property 'bodyExpression' from 'ConstraintAS' ---
		this.bodyExpression = null;
		//--- Set property 'defVariable' from 'ConstraintAS' ---
		this.defVariable = null;
		//--- Set property 'defOperation' from 'ConstraintAS' ---
		this.defOperation = null;
	}


	/** Property 'name' from 'ConstraintAS' */
	protected String name;
	/** Get property 'name' from 'ConstraintAS' */
	public String getName() {
		return name;
	}
	/** Set property 'name' from 'ConstraintAS' */
	public void setName(String name) {
		this.name = name;
	}

	/** Property 'kind' from 'ConstraintAS' */
	protected ConstraintKindAS kind;
	/** Get property 'kind' from 'ConstraintAS' */
	public ConstraintKindAS getKind() {
		return kind;
	}
	/** Set property 'kind' from 'ConstraintAS' */
	public void setKind(ConstraintKindAS kind) {
		this.kind = kind;
	}

	/** Property 'bodyExpression' from 'ConstraintAS' */
	protected uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclExpressionAS bodyExpression;
	/** Get property 'bodyExpression' from 'ConstraintAS' */
	public uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclExpressionAS getBodyExpression() {
		return bodyExpression;
	}
	/** Set property 'bodyExpression' from 'ConstraintAS' */
	public void setBodyExpression(uk.ac.kent.cs.ocl20.syntax.ast.expressions.OclExpressionAS bodyExpression) { 
		this.bodyExpression = bodyExpression;
	}

	/** Property 'defVariable' from 'ConstraintAS' */
	protected VariableDeclarationAS defVariable;
	/** Get property 'defVariable' from 'ConstraintAS' */
	public VariableDeclarationAS getDefVariable() {
		return defVariable;
	}
	/** Set property 'defVariable' from 'ConstraintAS' */
	public void setDefVariable(VariableDeclarationAS defVariable) { 
		this.defVariable = defVariable;
	}

	/** Property 'defOperation' from 'ConstraintAS' */
	protected OperationAS defOperation;
	/** Get property 'defOperation' from 'ConstraintAS' */
	public OperationAS getDefOperation() {
		return defOperation;
	}
	/** Set property 'defOperation' from 'ConstraintAS' */
	public void setDefOperation(OperationAS defOperation) { 
		this.defOperation = defOperation;
	}

	/** Override toString */
	public String toString() {
		String strId = "ast.contexts.ConstraintAS";
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

	/** Accept 'uk.ac.kent.cs.ocl20.syntax.ast.contexts.ConstraintAS$Visitor' */
	public Object accept(SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		ConstraintAS$Class obj = new ConstraintAS$Class();
		obj.name = name==null ? null : this.name;
		obj.kind = kind==null ? null : (ConstraintKindAS)this.kind.clone();
		obj.bodyExpression = bodyExpression==null ? null : this.bodyExpression;
		obj.defVariable = defVariable==null ? null : this.defVariable;
		obj.defOperation = defOperation==null ? null : this.defOperation;
		return obj;
	}
}
