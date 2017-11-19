/**
 *
 *  Class Namespace.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:49:05
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.bridge;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface Namespace
extends
    SemanticsElement,
    ModelElement
{
	/** Search for an owned element */
	public ModelElement lookupOwnedElement(String name);

	/** Get the 'namespace' from 'Namespace' */
	public Namespace getNamespace();
	/** Set the 'namespace' from 'Namespace' */
	public void setNamespace(Namespace namespace);

	/** Operation  'getEnvironmentWithoutParents' from 'Namespace' */
	public Environment getEnvironmentWithoutParents();

	/** Operation  'getEnvironmentWithParents' from 'Namespace' */
	public Environment getEnvironmentWithParents();

	/** Clone the object */
	public String getFullName(String sep);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
