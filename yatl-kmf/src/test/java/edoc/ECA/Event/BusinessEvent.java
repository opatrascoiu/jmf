/**
 *
 *  Class BusinessEvent.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public interface BusinessEvent
extends
    edoc.EdocElement
{

	/** Get the 'describedBy' from 'BusinessEvent' */
	public java.util.Set getDescribedBy();
	/** Set the 'describedBy' from 'BusinessEvent' */
	public void setDescribedBy(java.util.Set describedBy);

	/** Get the 'triggers' from 'BusinessEvent' */
	public java.util.Set getTriggers();
	/** Set the 'triggers' from 'BusinessEvent' */
	public void setTriggers(java.util.Set triggers);

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
