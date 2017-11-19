/**
 *
 *  Class OnMessage.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface OnMessage
extends
    ws.WsElement,
    PartnerActivity,
    Activity
{

	/** Get the 'pick' from 'OnMessage' */
	public Pick getPick();
	/** Set the 'pick' from 'OnMessage' */
	public void setPick(Pick pick);

	/** Get the 'eventHandler' from 'OnMessage' */
	public EventHandler getEventHandler();
	/** Set the 'eventHandler' from 'OnMessage' */
	public void setEventHandler(EventHandler eventHandler);

	/** Get the 'variable' from 'OnMessage' */
	public Variable getVariable();
	/** Set the 'variable' from 'OnMessage' */
	public void setVariable(Variable variable);

	/** Get the 'activity' from 'OnMessage' */
	public Activity getActivity();
	/** Set the 'activity' from 'OnMessage' */
	public void setActivity(Activity activity);

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
