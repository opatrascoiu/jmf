/**
 *
 *  Class LetExpAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public interface LetExpAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement,
    OclExpressionAS
{

	/** Get the 'variables' from 'LetExpAS' */
	public java.util.List getVariables();
	/** Set the 'variables' from 'LetExpAS' */
	public void setVariables(java.util.List variables);

	/** Get the 'in' from 'LetExpAS' */
	public OclExpressionAS getIn();
	/** Set the 'in' from 'LetExpAS' */
	public void setIn(OclExpressionAS in);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
