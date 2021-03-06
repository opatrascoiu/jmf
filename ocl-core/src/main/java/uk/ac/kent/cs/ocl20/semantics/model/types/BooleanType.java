/**
 *
 *  Class BooleanType.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:13
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.model.types;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface BooleanType
extends
    SemanticsElement,
    uk.ac.kent.cs.ocl20.semantics.bridge.Primitive,
    OclAnyType,
    uk.ac.kent.cs.ocl20.semantics.bridge.DataType,
    uk.ac.kent.cs.ocl20.semantics.bridge.Classifier,
    uk.ac.kent.cs.ocl20.semantics.bridge.Namespace,
    uk.ac.kent.cs.ocl20.semantics.bridge.ModelElement
{

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
