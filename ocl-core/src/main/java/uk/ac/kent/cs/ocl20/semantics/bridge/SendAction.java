/**
 *
 *  Class SendAction.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:49:04
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.bridge;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface SendAction
extends
    SemanticsElement,
    ModelElement
{
	/** Get the 'signal' from 'SendAction' */
	public Signal getSignal();
	/** Set the 'signal' from 'SendAction' */
	public void setSignal(Signal signal);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
