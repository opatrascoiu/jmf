/**
 *
 *  Class EnumLiteralExpAS.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:50
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.expressions;

import java.util.List;

import uk.ac.kent.cs.ocl20.syntax.SyntaxElement;

public interface EnumLiteralExpAS
extends
    SyntaxElement,
    LiteralExpAS,
    PrimaryExpAS,
    OclExpressionAS
{

	/** Get the 'pathName' from 'EnumLiteralExpAS' */
	public List getPathName();
	/** Set the 'pathName' from 'EnumLiteralExpAS' */
	public void setPathName(List pathName);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
