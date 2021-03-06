/**
 *
 *  Class Switch.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface Switch
extends
    ws.WsElement,
    StructuredActivity,
    Activity
{

	/** Get the 'otherwise' from 'Switch' */
	public Activity getOtherwise();
	/** Set the 'otherwise' from 'Switch' */
	public void setOtherwise(Activity otherwise);

	/** Get the 'cases' from 'Switch' */
	public java.util.Set getCases();
	/** Set the 'cases' from 'Switch' */
	public void setCases(java.util.Set cases);

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
