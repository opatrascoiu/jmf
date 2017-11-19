/**
 *
 *  Class Primitive.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:49:06
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.bridge;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface Primitive
extends
    SemanticsElement,
    uk.ac.kent.cs.ocl20.semantics.model.types.OclAnyType,
    DataType,
    Classifier,
    Namespace,
    ModelElement
{
	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
