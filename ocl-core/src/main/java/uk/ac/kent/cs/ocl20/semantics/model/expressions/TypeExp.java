/**
 *
 *  Class IntegerLiteralExp.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.model.expressions;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;

public interface TypeExp
extends
    SemanticsElement,
    LiteralExp,
    OclExpression
{
	/** Get the type */
	public Classifier getLiteralType();

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
