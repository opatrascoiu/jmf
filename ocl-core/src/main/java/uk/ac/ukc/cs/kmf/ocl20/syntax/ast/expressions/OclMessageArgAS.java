/**
 *
 *  Class OclMessageArgAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public interface OclMessageArgAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement
{

	/** Get the 'expression' from 'OclMessageArgAS' */
	public OclExpressionAS getExpression();
	/** Set the 'expression' from 'OclMessageArgAS' */
	public void setExpression(OclExpressionAS expression);

	/** Get the 'type' from 'OclMessageArgAS' */
	public uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS getType();
	/** Set the 'type' from 'OclMessageArgAS' */
	public void setType(uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS type);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
