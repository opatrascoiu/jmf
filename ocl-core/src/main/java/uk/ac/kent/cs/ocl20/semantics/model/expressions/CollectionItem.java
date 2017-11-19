/**
 *
 *  Class CollectionItem.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.model.expressions;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface CollectionItem
extends
    SemanticsElement,
    CollectionLiteralPart
{

	/** Get the 'item' from 'CollectionItem' */
	public OclExpression getItem();
	/** Set the 'item' from 'CollectionItem' */
	public void setItem(OclExpression item);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
