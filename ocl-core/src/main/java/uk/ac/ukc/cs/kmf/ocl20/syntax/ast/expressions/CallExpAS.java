/**
 *
 *  Class CallExpAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public interface CallExpAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement,
    OclExpressionAS
{

	/** Get the 'arguments' from 'CallExpAS' */
	public java.util.List getArguments();
	/** Set the 'arguments' from 'CallExpAS' */
	public void setArguments(java.util.List arguments);

	/** Get the 'source' from 'CallExpAS' */
	public OclExpressionAS getSource();
	/** Set the 'source' from 'CallExpAS' */
	public void setSource(OclExpressionAS source);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}