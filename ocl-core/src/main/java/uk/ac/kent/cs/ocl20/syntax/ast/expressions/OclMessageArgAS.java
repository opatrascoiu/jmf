/**
 *
 *  Class OclMessageArgAS.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.expressions;

import uk.ac.kent.cs.ocl20.syntax.SyntaxElement;

public interface OclMessageArgAS
extends
    SyntaxElement
{

	/** Get the 'type' from 'OclMessageArgAS' */
	public uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS getType();
	/** Set the 'type' from 'OclMessageArgAS' */
	public void setType(uk.ac.kent.cs.ocl20.syntax.ast.types.TypeAS type);

	/** Get the 'expression' from 'OclMessageArgAS' */
	public OclExpressionAS getExpression();
	/** Set the 'expression' from 'OclMessageArgAS' */
	public void setExpression(OclExpressionAS expression);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}