/**
 *
 *  Class EnumLiteralExpAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public interface EnumLiteralExpAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement,
    LiteralExpAS,
    PrimaryExpAS,
    OclExpressionAS
{

	/** Get the 'pathName' from 'EnumLiteralExpAS' */
	public java.util.List getPathName();
	/** Set the 'pathName' from 'EnumLiteralExpAS' */
	public void setPathName(java.util.List pathName);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}