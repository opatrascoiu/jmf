/**
 *
 *  Class Tie.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

public interface Tie
extends
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlElement,
    ModelElement,
    Element
{

	/** Get the 'tiedElement' from 'Tie' */
	public java.util.Set getTiedElement();
	/** Set the 'tiedElement' from 'Tie' */
	public void setTiedElement(java.util.Set tiedElement);

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
