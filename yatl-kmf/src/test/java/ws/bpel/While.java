/**
 *
 *  Class While.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface While
extends
    ws.WsElement,
    StructuredActivity,
    Activity
{

	/** Get the 'body' from 'While' */
	public Activity getBody();
	/** Set the 'body' from 'While' */
	public void setBody(Activity body);

	/** Get the 'condition' from 'While' */
	public BooleanExpression getCondition();
	/** Set the 'condition' from 'While' */
	public void setCondition(BooleanExpression condition);

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
