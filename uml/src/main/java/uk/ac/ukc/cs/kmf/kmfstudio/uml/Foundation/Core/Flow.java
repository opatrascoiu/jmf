/**
 *
 *  Class Flow.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

public interface Flow
extends
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlElement,
    Relationship,
    ModelElement,
    Element
{

	/** Get the 'target' from 'Flow' */
	public java.util.Set getTarget();
	/** Set the 'target' from 'Flow' */
	public void setTarget(java.util.Set target);

	/** Get the 'source' from 'Flow' */
	public java.util.Set getSource();
	/** Set the 'source' from 'Flow' */
	public void setSource(java.util.Set source);

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
