/**
 *
 *  Class IfExpAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public interface IfExpAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement,
    OclExpressionAS
{

	/** Get the 'thenExpression' from 'IfExpAS' */
	public OclExpressionAS getThenExpression();
	/** Set the 'thenExpression' from 'IfExpAS' */
	public void setThenExpression(OclExpressionAS thenExpression);

	/** Get the 'elseExpression' from 'IfExpAS' */
	public OclExpressionAS getElseExpression();
	/** Set the 'elseExpression' from 'IfExpAS' */
	public void setElseExpression(OclExpressionAS elseExpression);

	/** Get the 'condition' from 'IfExpAS' */
	public OclExpressionAS getCondition();
	/** Set the 'condition' from 'IfExpAS' */
	public void setCondition(OclExpressionAS condition);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
