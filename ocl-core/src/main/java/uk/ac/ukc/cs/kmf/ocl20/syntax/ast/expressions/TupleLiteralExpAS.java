/**
 *
 *  Class TupleLiteralExpAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public interface TupleLiteralExpAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement,
    LiteralExpAS,
    PrimaryExpAS,
    OclExpressionAS
{

	/** Get the 'tupleParts' from 'TupleLiteralExpAS' */
	public java.util.List getTupleParts();
	/** Set the 'tupleParts' from 'TupleLiteralExpAS' */
	public void setTupleParts(java.util.List tupleParts);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
