/**
 *
 *  Class EnumLiteral.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

public interface EnumLiteral
extends
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlElement,
    ModelElement,
    Element
{

	/** Get the 'enumeration' from 'EnumLiteral' */
	public Enumeration_ getEnumeration();
	/** Set the 'enumeration' from 'EnumLiteral' */
	public void setEnumeration(Enumeration_ enumeration);

	/** Get the id */
	public String getId();
	/** Set the id */
	public void setId(String id);

	/** Override the toString */
	public String toString();

	/** Delete the object */
	public void delete();

	/** Clone the object */
	public Object clone();
}
