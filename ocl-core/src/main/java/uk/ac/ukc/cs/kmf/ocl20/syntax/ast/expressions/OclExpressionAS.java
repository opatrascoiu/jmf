/**
 *
 *  Class OclExpressionAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public interface OclExpressionAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement
{

	/** Get the 'isMarkedPre' from 'OclExpressionAS' */
	public Boolean getIsMarkedPre();
	/** Set the 'isMarkedPre' from 'OclExpressionAS' */
	public void setIsMarkedPre(Boolean isMarkedPre);

	/** Get the 'parent' from 'OclExpressionAS' */
	public OclExpressionAS getParent();
	/** Set the 'parent' from 'OclExpressionAS' */
	public void setParent(OclExpressionAS parent);

	/** Get the 'ifExpAS' from 'OclExpressionAS' */
	public IfExpAS getIfExpAS();
	/** Set the 'ifExpAS' from 'OclExpressionAS' */
	public void setIfExpAS(IfExpAS ifExpAS);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
