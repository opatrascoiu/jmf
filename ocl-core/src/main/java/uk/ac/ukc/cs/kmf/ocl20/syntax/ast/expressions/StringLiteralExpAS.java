/**
 *
 *  Class StringLiteralExpAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public interface StringLiteralExpAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement,
    PrimitiveLiteralExpAS,
    LiteralExpAS,
    PrimaryExpAS,
    OclExpressionAS
{

	/** Get the 'value' from 'StringLiteralExpAS' */
	public String getValue();
	/** Set the 'value' from 'StringLiteralExpAS' */
	public void setValue(String value);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
