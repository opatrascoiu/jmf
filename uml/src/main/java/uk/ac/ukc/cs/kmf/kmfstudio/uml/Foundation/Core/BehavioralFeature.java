/**
 *
 *  Class BehavioralFeature.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

public interface BehavioralFeature
extends
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlElement,
    Feature,
    ModelElement,
    Element
{

	/** Get the 'isQuery' from 'BehavioralFeature' */
	public Boolean getIsQuery();
	/** Set the 'isQuery' from 'BehavioralFeature' */
	public void setIsQuery(Boolean isQuery);

	/** Get the 'parameter' from 'BehavioralFeature' */
	public java.util.List getParameter();
	/** Set the 'parameter' from 'BehavioralFeature' */
	public void setParameter(java.util.List parameter);

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
