/**
 *
 *  Class EventBasedProcess.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public interface EventBasedProcess
extends
    edoc.EdocElement,
    edoc.ECA.CCA.Choreography,
    Subscriber,
    Publisher
{

	/** Get the 'notificationRules' from 'EventBasedProcess' */
	public java.util.Set getNotificationRules();
	/** Set the 'notificationRules' from 'EventBasedProcess' */
	public void setNotificationRules(java.util.Set notificationRules);

	/** Get the 'processEvents' from 'EventBasedProcess' */
	public java.util.Set getProcessEvents();
	/** Set the 'processEvents' from 'EventBasedProcess' */
	public void setProcessEvents(java.util.Set processEvents);

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