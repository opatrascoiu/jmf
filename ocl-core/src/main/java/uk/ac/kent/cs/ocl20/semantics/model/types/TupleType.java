/**
 *
 *  Class TupleType.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:47:13
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.model.types;

import java.util.List;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface TupleType
extends
    SemanticsElement,
    uk.ac.kent.cs.ocl20.semantics.bridge.DataType,
    uk.ac.kent.cs.ocl20.semantics.bridge.Classifier,
    uk.ac.kent.cs.ocl20.semantics.bridge.Namespace,
    uk.ac.kent.cs.ocl20.semantics.bridge.ModelElement
{

	/** Get the 'partType' from 'TupleType' */
	public List getPartType();
	/** Set the 'partType' from 'TupleType' */
	public void setPartType(List partType);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
