/**
 *
 *  Class LetExpAS$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class LetExpAS$Class
extends
	OclExpressionAS$Class
implements
	LetExpAS,
    ocl.syntax.SyntaxVisitable
{
	/** Default constructor */
	public LetExpAS$Class() {
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
		//--- Set property 'variables' from 'LetExpAS' ---
		this.variables = new java.util.Vector();
		//--- Set property 'inExp' from 'LetExpAS' ---
		this.inExp = null;
	}
	/** Specialized constructor */
	public LetExpAS$Class(Boolean isMarkedPre) {
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
		//--- Set property 'variables' from 'LetExpAS' ---
		this.variables = new java.util.Vector();
		//--- Set property 'inExp' from 'LetExpAS' ---
		this.inExp = null;
	}


	/** Property 'variables' from 'LetExpAS' */
	protected java.util.List variables;
	/** Get property 'variables' from 'LetExpAS' */
	public java.util.List getVariables() {
		return variables;
	}
	/** Set property 'variables' from 'LetExpAS' */
	public void setVariables(java.util.List variables) { 
		this.variables = variables;
	}

	/** Property 'inExp' from 'LetExpAS' */
	protected OclExpressionAS inExp;
	/** Get property 'inExp' from 'LetExpAS' */
	public OclExpressionAS getInExp() {
		return inExp;
	}
	/** Set property 'inExp' from 'LetExpAS' */
	public void setInExp(OclExpressionAS inExp) { 
		this.inExp = inExp;
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
		String strId = "syntax.ast.expressions.LetExpAS";
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
		java.util.Iterator variablesIt = this.variables.iterator();
		while (variablesIt.hasNext()) {
			ocl.syntax.ast.contexts.VariableDeclarationAS variablesObj = (ocl.syntax.ast.contexts.VariableDeclarationAS)variablesIt.next();
			if (variablesObj != null)
				variablesObj.setLetExpAS(null);
		}
		if (inExp != null)
			this.inExp.setLetExpAS(null);
	}

	/** Clone the object */
	public Object clone() {
		LetExpAS$Class obj = new LetExpAS$Class();
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
		obj.variables = variables==null ? null : (java.util.List)((java.util.Vector)this.variables).clone();
		obj.inExp = inExp==null ? null : this.inExp;
		return obj;
	}

	/** Accept 'ocl.syntax.ast.expressions.LetExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
