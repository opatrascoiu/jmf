/**
 *
 *  Class UmlElement.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:47
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml;

public interface UmlElement
	extends UmlVisitable
{
	/** Get the id */
	public String getId();
	/** Set the id */
	public void setId(String id);

	/** Delete */
	public void delete();
	/** Override toString */
	public String toString();
}