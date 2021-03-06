/**
 *
 *  Class IntegerLiteralExp.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.model.expressions;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface IntegerLiteralExp
extends
    SemanticsElement,
    NumericalLiteralExp,
    LiteralExp,
    OclExpression
{

	/** Get the 'integerSymbol' from 'IntegerLiteralExp' */
	public Integer getIntegerSymbol();
	/** Set the 'integerSymbol' from 'IntegerLiteralExp' */
	public void setIntegerSymbol(Integer integerSymbol);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
