/**
 *
 *  Class PubSubNotice.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public interface PubSubNotice
extends
    edoc.EdocElement,
    edoc.ECA.DocumentModel.CompositeData,
    edoc.ECA.DocumentModel.DataElement,
    edoc.ECA.ModelManagement.PackageContent
{

	/** Get the 'subscribesTo' from 'PubSubNotice' */
	public java.util.Set getSubscribesTo();
	/** Set the 'subscribesTo' from 'PubSubNotice' */
	public void setSubscribesTo(java.util.Set subscribesTo);

	/** Get the 'announces' from 'PubSubNotice' */
	public java.util.Set getAnnounces();
	/** Set the 'announces' from 'PubSubNotice' */
	public void setAnnounces(java.util.Set announces);

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