/**
 *
 *  Class OperationContextDecl.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:48:56
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.model.contexts;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface OperationContextDecl
extends
    SemanticsElement,
    ContextDeclaration
{

	/** Get the 'referredOperation' from 'OperationContextDecl' */
	public uk.ac.kent.cs.ocl20.semantics.bridge.Operation getReferredOperation();
	/** Set the 'referredOperation' from 'OperationContextDecl' */
	public void setReferredOperation(uk.ac.kent.cs.ocl20.semantics.bridge.Operation referredOperation);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
