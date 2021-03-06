/**
 *
 *  Class OclMessageExpAS.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.expressions;

import java.util.List;

import uk.ac.kent.cs.ocl20.syntax.SyntaxElement;

public interface OclMessageExpAS
extends
    SyntaxElement,
    OclExpressionAS
{

	/** Get the 'name' from 'OclMessageExpAS' */
	public String getName();
	/** Set the 'name' from 'OclMessageExpAS' */
	public void setName(String name);

	/** Get the 'kind' from 'OclMessageExpAS' */
	public OclMessageKindAS getKind();
	/** Set the 'kind' from 'OclMessageExpAS' */
	public void setKind(OclMessageKindAS kind);

	/** Get the 'arguments' from 'OclMessageExpAS' */
	public List getArguments();
	/** Set the 'arguments' from 'OclMessageExpAS' */
	public void setArguments(List arguments);

	/** Get the 'target' from 'OclMessageExpAS' */
	public OclExpressionAS getTarget();
	/** Set the 'target' from 'OclMessageExpAS' */
	public void setTarget(OclExpressionAS target);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
