/**
 *
 *  Class OclExpressionAS.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.expressions;

import uk.ac.kent.cs.ocl20.syntax.SyntaxElement;

public interface OclExpressionAS
extends
    SyntaxElement
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
