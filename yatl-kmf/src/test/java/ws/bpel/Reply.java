/**
 *
 *  Class Reply.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface Reply
extends
    ws.WsElement,
    PartnerActivity,
    Activity
{

	/** Get the 'faultName' from 'Reply' */
	public String getFaultName();
	/** Set the 'faultName' from 'Reply' */
	public void setFaultName(String faultName);

	/** Get the 'variable' from 'Reply' */
	public Variable getVariable();
	/** Set the 'variable' from 'Reply' */
	public void setVariable(Variable variable);

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
