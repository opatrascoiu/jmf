/**
 *
 *  Class EventNotice.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public interface EventNotice
extends
    edoc.EdocElement,
    PubSubNotice,
    edoc.ECA.DocumentModel.CompositeData,
    edoc.ECA.DocumentModel.DataElement,
    edoc.ECA.ModelManagement.PackageContent
{

	/** Get the 'describes' from 'EventNotice' */
	public BusinessEvent getDescribes();
	/** Set the 'describes' from 'EventNotice' */
	public void setDescribes(BusinessEvent describes);

	/** Get the 'triggeredBy' from 'EventNotice' */
	public BusinessEvent getTriggeredBy();
	/** Set the 'triggeredBy' from 'EventNotice' */
	public void setTriggeredBy(BusinessEvent triggeredBy);

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
