/**
 *
 *  Class CallExpAS.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:50
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.expressions;

import java.util.List;

import uk.ac.kent.cs.ocl20.syntax.SyntaxElement;

public interface CallExpAS
extends
    SyntaxElement,
    OclExpressionAS
{

	/** Get the 'source' from 'CallExpAS' */
	public OclExpressionAS getSource();
	/** Set the 'source' from 'CallExpAS' */
	public void setSource(OclExpressionAS source);

	/** Get the 'arguments' from 'CallExpAS' */
	public List getArguments();
	/** Set the 'arguments' from 'CallExpAS' */
	public void setArguments(List arguments);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
