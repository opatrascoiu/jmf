/**
 *
 *  Class VariableExpAS$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class VariableExpAS$Class
extends
	OclExpressionAS$Class
implements
	VariableExpAS,
    ocl.syntax.SyntaxVisitable
{
	/** Default constructor */
	public VariableExpAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'constraintAS' from 'OclExpressionAS' ---
		this.constraintAS = null;
		//--- Set property 'variableDeclarationAS' from 'OclExpressionAS' ---
		this.variableDeclarationAS = null;
		//--- Set property 'firstSource' from 'OclExpressionAS' ---
		this.firstSource = null;
		//--- Set property 'lastSource' from 'OclExpressionAS' ---
		this.lastSource = null;
		//--- Set property 'collectionItemAS' from 'OclExpressionAS' ---
		this.collectionItemAS = null;
		//--- Set property 'leftOperandSource' from 'OclExpressionAS' ---
		this.leftOperandSource = null;
		//--- Set property 'thenSource' from 'OclExpressionAS' ---
		this.thenSource = null;
		//--- Set property 'oclMessageExpAS' from 'OclExpressionAS' ---
		this.oclMessageExpAS = null;
		//--- Set property 'oclMessageArgAS' from 'OclExpressionAS' ---
		this.oclMessageArgAS = null;
		//--- Set property 'selectionExpAS' from 'OclExpressionAS' ---
		this.selectionExpAS = null;
		//--- Set property 'callArguments' from 'OclExpressionAS' ---
		this.callArguments = null;
		//--- Set property 'bodyLoop' from 'OclExpressionAS' ---
		this.bodyLoop = null;
		//--- Set property 'sourceLoop' from 'OclExpressionAS' ---
		this.sourceLoop = null;
		//--- Set property 'callSource' from 'OclExpressionAS' ---
		this.callSource = null;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'child' from 'OclExpressionAS' ---
		this.child = null;
		//--- Set property 'rightOperandSource' from 'OclExpressionAS' ---
		this.rightOperandSource = null;
		//--- Set property 'elseSource' from 'OclExpressionAS' ---
		this.elseSource = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'letExpAS' from 'OclExpressionAS' ---
		this.letExpAS = null;
	}
	/** Specialized constructor */
	public VariableExpAS$Class(Boolean isMarkedPre) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'constraintAS' from 'OclExpressionAS' ---
		this.constraintAS = null;
		//--- Set property 'variableDeclarationAS' from 'OclExpressionAS' ---
		this.variableDeclarationAS = null;
		//--- Set property 'firstSource' from 'OclExpressionAS' ---
		this.firstSource = null;
		//--- Set property 'lastSource' from 'OclExpressionAS' ---
		this.lastSource = null;
		//--- Set property 'collectionItemAS' from 'OclExpressionAS' ---
		this.collectionItemAS = null;
		//--- Set property 'leftOperandSource' from 'OclExpressionAS' ---
		this.leftOperandSource = null;
		//--- Set property 'thenSource' from 'OclExpressionAS' ---
		this.thenSource = null;
		//--- Set property 'oclMessageExpAS' from 'OclExpressionAS' ---
		this.oclMessageExpAS = null;
		//--- Set property 'oclMessageArgAS' from 'OclExpressionAS' ---
		this.oclMessageArgAS = null;
		//--- Set property 'selectionExpAS' from 'OclExpressionAS' ---
		this.selectionExpAS = null;
		//--- Set property 'callArguments' from 'OclExpressionAS' ---
		this.callArguments = null;
		//--- Set property 'bodyLoop' from 'OclExpressionAS' ---
		this.bodyLoop = null;
		//--- Set property 'sourceLoop' from 'OclExpressionAS' ---
		this.sourceLoop = null;
		//--- Set property 'callSource' from 'OclExpressionAS' ---
		this.callSource = null;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'child' from 'OclExpressionAS' ---
		this.child = null;
		//--- Set property 'rightOperandSource' from 'OclExpressionAS' ---
		this.rightOperandSource = null;
		//--- Set property 'elseSource' from 'OclExpressionAS' ---
		this.elseSource = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'letExpAS' from 'OclExpressionAS' ---
		this.letExpAS = null;
	}


	/** Property 'variableDeclarationAS' from 'VariableExpAS' */
	protected ocl.syntax.ast.contexts.VariableDeclarationAS variableDeclarationAS;
	/** Get property 'variableDeclarationAS' from 'VariableExpAS' */
	public ocl.syntax.ast.contexts.VariableDeclarationAS getVariableDeclarationAS() {
		return variableDeclarationAS;
	}
	/** Set property 'variableDeclarationAS' from 'VariableExpAS' */
	public void setVariableDeclarationAS(ocl.syntax.ast.contexts.VariableDeclarationAS variableDeclarationAS) { 
		this.variableDeclarationAS = variableDeclarationAS;
	}

	/** The id */
	protected String id;
	/** Get the id */
	public String getId() {
		return id;
	}
	/** Set the id */
	public void setId(String id) {
		this.id = id;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.expressions.VariableExpAS";
		String name = null;
		try {
			java.lang.Class cls = this.getClass();
			java.lang.reflect.Method method = cls.getMethod("getName", new java.lang.Class[] {});
			name = (String) method.invoke(this, new Object[] {});
			if (name != null && name.length()==0) name = null;
		} catch (Exception e) {
		}
		if (name == null)
			return strId+" 'id-"+getId()+"'";
		else
			return strId+" '"+name+"-"+getId()+"'";
	}

	/** Delete the object */
	public void delete() {
		if (constraintAS != null)
			this.constraintAS.setBodyExpression(null);
		if (variableDeclarationAS != null)
			this.variableDeclarationAS.setInitExp(null);
		if (firstSource != null)
			this.firstSource.setFirst(null);
		if (lastSource != null)
			this.lastSource.setLast(null);
		if (collectionItemAS != null)
			this.collectionItemAS.setItem(null);
		if (leftOperandSource != null)
			this.leftOperandSource.setLeftOperand(null);
		if (thenSource != null)
			this.thenSource.setThenExpression(null);
		if (oclMessageExpAS != null)
			this.oclMessageExpAS.setTarget(null);
		if (oclMessageArgAS != null)
			this.oclMessageArgAS.setExpression(null);
		if (selectionExpAS != null)
			this.selectionExpAS.setSource(null);
		if (callArguments != null)
			this.callArguments.getArguments().remove(this);
		if (bodyLoop != null)
			this.bodyLoop.setLoopBody(null);
		if (sourceLoop != null)
			this.sourceLoop.setSource(null);
		if (callSource != null)
			this.callSource.setSource(null);
		if (parent != null)
			this.parent.setChild(null);
		if (child != null)
			this.child.setParent(null);
		if (rightOperandSource != null)
			this.rightOperandSource.setRightOperand(null);
		if (elseSource != null)
			this.elseSource.setElseExpression(null);
		if (ifExpAS != null)
			this.ifExpAS.setCondition(null);
		if (letExpAS != null)
			this.letExpAS.setInExp(null);
	}

	/** Clone the object */
	public Object clone() {
		VariableExpAS$Class obj = new VariableExpAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.constraintAS = constraintAS==null ? null : this.constraintAS;
		obj.variableDeclarationAS = variableDeclarationAS==null ? null : this.variableDeclarationAS;
		obj.firstSource = firstSource==null ? null : this.firstSource;
		obj.lastSource = lastSource==null ? null : this.lastSource;
		obj.collectionItemAS = collectionItemAS==null ? null : this.collectionItemAS;
		obj.leftOperandSource = leftOperandSource==null ? null : this.leftOperandSource;
		obj.thenSource = thenSource==null ? null : this.thenSource;
		obj.oclMessageExpAS = oclMessageExpAS==null ? null : this.oclMessageExpAS;
		obj.oclMessageArgAS = oclMessageArgAS==null ? null : this.oclMessageArgAS;
		obj.selectionExpAS = selectionExpAS==null ? null : this.selectionExpAS;
		obj.callArguments = callArguments==null ? null : this.callArguments;
		obj.bodyLoop = bodyLoop==null ? null : this.bodyLoop;
		obj.sourceLoop = sourceLoop==null ? null : this.sourceLoop;
		obj.callSource = callSource==null ? null : this.callSource;
		obj.parent = parent==null ? null : this.parent;
		obj.child = child==null ? null : this.child;
		obj.rightOperandSource = rightOperandSource==null ? null : this.rightOperandSource;
		obj.elseSource = elseSource==null ? null : this.elseSource;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		obj.letExpAS = letExpAS==null ? null : this.letExpAS;
		return obj;
	}

	/** Accept 'ocl.syntax.ast.expressions.VariableExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
