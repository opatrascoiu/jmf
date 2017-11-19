/**
 *
 *  Class IfExpAS$Class.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.expressions;

import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitable;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor;

public class IfExpAS$Class
extends
	OclExpressionAS$Class
implements
	IfExpAS,
    SyntaxVisitable
{
	/** Default constructor */
	public IfExpAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'thenExpression' from 'IfExpAS' ---
		this.thenExpression = null;
		//--- Set property 'elseExpression' from 'IfExpAS' ---
		this.elseExpression = null;
		//--- Set property 'condition' from 'IfExpAS' ---
		this.condition = null;
	}
	/** Specialized constructor */
	public IfExpAS$Class(Boolean isMarkedPre) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'thenExpression' from 'IfExpAS' ---
		this.thenExpression = null;
		//--- Set property 'elseExpression' from 'IfExpAS' ---
		this.elseExpression = null;
		//--- Set property 'condition' from 'IfExpAS' ---
		this.condition = null;
	}


	/** Property 'thenExpression' from 'IfExpAS' */
	protected OclExpressionAS thenExpression;
	/** Get property 'thenExpression' from 'IfExpAS' */
	public OclExpressionAS getThenExpression() {
		return thenExpression;
	}
	/** Set property 'thenExpression' from 'IfExpAS' */
	public void setThenExpression(OclExpressionAS thenExpression) { 
		this.thenExpression = thenExpression;
	}

	/** Property 'elseExpression' from 'IfExpAS' */
	protected OclExpressionAS elseExpression;
	/** Get property 'elseExpression' from 'IfExpAS' */
	public OclExpressionAS getElseExpression() {
		return elseExpression;
	}
	/** Set property 'elseExpression' from 'IfExpAS' */
	public void setElseExpression(OclExpressionAS elseExpression) { 
		this.elseExpression = elseExpression;
	}

	/** Property 'condition' from 'IfExpAS' */
	protected OclExpressionAS condition;
	/** Get property 'condition' from 'IfExpAS' */
	public OclExpressionAS getCondition() {
		return condition;
	}
	/** Set property 'condition' from 'IfExpAS' */
	public void setCondition(OclExpressionAS condition) { 
		this.condition = condition;
	}

	/** Override toString */
	public String toString() {
		return "if "+getCondition()+" then "+getThenExpression()+" else "+getElseExpression()+" endif";
	}

	/** Accept 'uk.ac.kent.cs.ocl20.syntax.ast.expressions.IfExpAS$Visitor' */
	public Object accept(SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		IfExpAS$Class obj = new IfExpAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.parent = parent==null ? null : this.parent;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		obj.thenExpression = thenExpression==null ? null : this.thenExpression;
		obj.elseExpression = elseExpression==null ? null : this.elseExpression;
		obj.condition = condition==null ? null : this.condition;
		return obj;
	}
}
