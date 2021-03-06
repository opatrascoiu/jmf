/**
 *
 *  Class EnumLiteral.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:49:04
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.semantics.bridge;

import uk.ac.kent.cs.ocl20.semantics.SemanticsElement;

public interface EnumLiteral
extends
    SemanticsElement,
    ModelElement,
    Property
{
	/** Get the 'enumeration' from 'EnumLiteral' */
	public Enumeration_ getEnumeration();
	/** Set the 'enumeration' from 'EnumLiteral' */
	public void setEnumeration(Enumeration_ enumeration);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
