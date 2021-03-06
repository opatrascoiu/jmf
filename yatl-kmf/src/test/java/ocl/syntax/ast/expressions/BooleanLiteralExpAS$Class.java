/**
 *
 *  Class BooleanLiteralExpAS$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class BooleanLiteralExpAS$Class
implements
	BooleanLiteralExpAS,
    ocl.syntax.SyntaxVisitable
{
	/** Default constructor */
	public BooleanLiteralExpAS$Class() {
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
		//--- Set property 'value' from 'BooleanLiteralExpAS' ---
		this.value = new Boolean(false);
	}
	/** Specialized constructor */
	public BooleanLiteralExpAS$Class(Boolean isMarkedPre, Boolean value) {
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

	/** Property 'constraintAS' from 'OclExpressionAS' */
	protected ocl.syntax.ast.contexts.ConstraintAS constraintAS;
	/** Get property 'constraintAS' from 'OclExpressionAS' */
	public ocl.syntax.ast.contexts.ConstraintAS getConstraintAS() {
		return constraintAS;
	}
	/** Set property 'constraintAS' from 'OclExpressionAS' */
	public void setConstraintAS(ocl.syntax.ast.contexts.ConstraintAS constraintAS) { 
		this.constraintAS = constraintAS;
	}

	/** Property 'variableDeclarationAS' from 'OclExpressionAS' */
	protected ocl.syntax.ast.contexts.VariableDeclarationAS variableDeclarationAS;
	/** Get property 'variableDeclarationAS' from 'OclExpressionAS' */
	public ocl.syntax.ast.contexts.VariableDeclarationAS getVariableDeclarationAS() {
		return variableDeclarationAS;
	}
	/** Set property 'variableDeclarationAS' from 'OclExpressionAS' */
	public void setVariableDeclarationAS(ocl.syntax.ast.contexts.VariableDeclarationAS variableDeclarationAS) { 
		this.variableDeclarationAS = variableDeclarationAS;
	}

	/** Property 'firstSource' from 'OclExpressionAS' */
	protected CollectionRangeAS firstSource;
	/** Get property 'firstSource' from 'OclExpressionAS' */
	public CollectionRangeAS getFirstSource() {
		return firstSource;
	}
	/** Set property 'firstSource' from 'OclExpressionAS' */
	public void setFirstSource(CollectionRangeAS firstSource) { 
		this.firstSource = firstSource;
	}

	/** Property 'lastSource' from 'OclExpressionAS' */
	protected CollectionRangeAS lastSource;
	/** Get property 'lastSource' from 'OclExpressionAS' */
	public CollectionRangeAS getLastSource() {
		return lastSource;
	}
	/** Set property 'lastSource' from 'OclExpressionAS' */
	public void setLastSource(CollectionRangeAS lastSource) { 
		this.lastSource = lastSource;
	}

	/** Property 'collectionItemAS' from 'OclExpressionAS' */
	protected CollectionItemAS collectionItemAS;
	/** Get property 'collectionItemAS' from 'OclExpressionAS' */
	public CollectionItemAS getCollectionItemAS() {
		return collectionItemAS;
	}
	/** Set property 'collectionItemAS' from 'OclExpressionAS' */
	public void setCollectionItemAS(CollectionItemAS collectionItemAS) { 
		this.collectionItemAS = collectionItemAS;
	}

	/** Property 'leftOperandSource' from 'OclExpressionAS' */
	protected LogicalExpAS leftOperandSource;
	/** Get property 'leftOperandSource' from 'OclExpressionAS' */
	public LogicalExpAS getLeftOperandSource() {
		return leftOperandSource;
	}
	/** Set property 'leftOperandSource' from 'OclExpressionAS' */
	public void setLeftOperandSource(LogicalExpAS leftOperandSource) { 
		this.leftOperandSource = leftOperandSource;
	}

	/** Property 'thenSource' from 'OclExpressionAS' */
	protected IfExpAS thenSource;
	/** Get property 'thenSource' from 'OclExpressionAS' */
	public IfExpAS getThenSource() {
		return thenSource;
	}
	/** Set property 'thenSource' from 'OclExpressionAS' */
	public void setThenSource(IfExpAS thenSource) { 
		this.thenSource = thenSource;
	}

	/** Property 'oclMessageExpAS' from 'OclExpressionAS' */
	protected OclMessageExpAS oclMessageExpAS;
	/** Get property 'oclMessageExpAS' from 'OclExpressionAS' */
	public OclMessageExpAS getOclMessageExpAS() {
		return oclMessageExpAS;
	}
	/** Set property 'oclMessageExpAS' from 'OclExpressionAS' */
	public void setOclMessageExpAS(OclMessageExpAS oclMessageExpAS) { 
		this.oclMessageExpAS = oclMessageExpAS;
	}

	/** Property 'oclMessageArgAS' from 'OclExpressionAS' */
	protected OclMessageArgAS oclMessageArgAS;
	/** Get property 'oclMessageArgAS' from 'OclExpressionAS' */
	public OclMessageArgAS getOclMessageArgAS() {
		return oclMessageArgAS;
	}
	/** Set property 'oclMessageArgAS' from 'OclExpressionAS' */
	public void setOclMessageArgAS(OclMessageArgAS oclMessageArgAS) { 
		this.oclMessageArgAS = oclMessageArgAS;
	}

	/** Property 'selectionExpAS' from 'OclExpressionAS' */
	protected SelectionExpAS selectionExpAS;
	/** Get property 'selectionExpAS' from 'OclExpressionAS' */
	public SelectionExpAS getSelectionExpAS() {
		return selectionExpAS;
	}
	/** Set property 'selectionExpAS' from 'OclExpressionAS' */
	public void setSelectionExpAS(SelectionExpAS selectionExpAS) { 
		this.selectionExpAS = selectionExpAS;
	}

	/** Property 'callArguments' from 'OclExpressionAS' */
	protected CallExpAS callArguments;
	/** Get property 'callArguments' from 'OclExpressionAS' */
	public CallExpAS getCallArguments() {
		return callArguments;
	}
	/** Set property 'callArguments' from 'OclExpressionAS' */
	public void setCallArguments(CallExpAS callArguments) { 
		this.callArguments = callArguments;
	}

	/** Property 'bodyLoop' from 'OclExpressionAS' */
	protected LoopExpAS bodyLoop;
	/** Get property 'bodyLoop' from 'OclExpressionAS' */
	public LoopExpAS getBodyLoop() {
		return bodyLoop;
	}
	/** Set property 'bodyLoop' from 'OclExpressionAS' */
	public void setBodyLoop(LoopExpAS bodyLoop) { 
		this.bodyLoop = bodyLoop;
	}

	/** Property 'sourceLoop' from 'OclExpressionAS' */
	protected LoopExpAS sourceLoop;
	/** Get property 'sourceLoop' from 'OclExpressionAS' */
	public LoopExpAS getSourceLoop() {
		return sourceLoop;
	}
	/** Set property 'sourceLoop' from 'OclExpressionAS' */
	public void setSourceLoop(LoopExpAS sourceLoop) { 
		this.sourceLoop = sourceLoop;
	}

	/** Property 'callSource' from 'OclExpressionAS' */
	protected CallExpAS callSource;
	/** Get property 'callSource' from 'OclExpressionAS' */
	public CallExpAS getCallSource() {
		return callSource;
	}
	/** Set property 'callSource' from 'OclExpressionAS' */
	public void setCallSource(CallExpAS callSource) { 
		this.callSource = callSource;
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

	/** Property 'child' from 'OclExpressionAS' */
	protected OclExpressionAS child;
	/** Get property 'child' from 'OclExpressionAS' */
	public OclExpressionAS getChild() {
		return child;
	}
	/** Set property 'child' from 'OclExpressionAS' */
	public void setChild(OclExpressionAS child) { 
		this.child = child;
	}

	/** Property 'rightOperandSource' from 'OclExpressionAS' */
	protected LogicalExpAS rightOperandSource;
	/** Get property 'rightOperandSource' from 'OclExpressionAS' */
	public LogicalExpAS getRightOperandSource() {
		return rightOperandSource;
	}
	/** Set property 'rightOperandSource' from 'OclExpressionAS' */
	public void setRightOperandSource(LogicalExpAS rightOperandSource) { 
		this.rightOperandSource = rightOperandSource;
	}

	/** Property 'elseSource' from 'OclExpressionAS' */
	protected IfExpAS elseSource;
	/** Get property 'elseSource' from 'OclExpressionAS' */
	public IfExpAS getElseSource() {
		return elseSource;
	}
	/** Set property 'elseSource' from 'OclExpressionAS' */
	public void setElseSource(IfExpAS elseSource) { 
		this.elseSource = elseSource;
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

	/** Property 'letExpAS' from 'OclExpressionAS' */
	protected LetExpAS letExpAS;
	/** Get property 'letExpAS' from 'OclExpressionAS' */
	public LetExpAS getLetExpAS() {
		return letExpAS;
	}
	/** Set property 'letExpAS' from 'OclExpressionAS' */
	public void setLetExpAS(LetExpAS letExpAS) { 
		this.letExpAS = letExpAS;
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
		BooleanLiteralExpAS$Class obj = new BooleanLiteralExpAS$Class();
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
		obj.value = value==null ? null : this.value;
		return obj;
	}

	/** Accept 'ocl.syntax.ast.expressions.BooleanLiteralExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
