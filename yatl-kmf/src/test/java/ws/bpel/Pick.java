/**
 *
 *  Class Pick.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface Pick
extends
    ws.WsElement,
    StructuredActivity,
    Activity
{

	/** Get the 'createInstance' from 'Pick' */
	public Boolean getCreateInstance();
	/** Set the 'createInstance' from 'Pick' */
	public void setCreateInstance(Boolean createInstance);

	/** Get the 'onMessages' from 'Pick' */
	public java.util.Set getOnMessages();
	/** Set the 'onMessages' from 'Pick' */
	public void setOnMessages(java.util.Set onMessages);

	/** Get the 'onAlarms' from 'Pick' */
	public java.util.Set getOnAlarms();
	/** Set the 'onAlarms' from 'Pick' */
	public void setOnAlarms(java.util.Set onAlarms);

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
