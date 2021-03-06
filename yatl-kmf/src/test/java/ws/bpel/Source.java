/**
 *
 *  Class Source.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface Source
extends
    ws.WsElement
{

	/** Get the 'transitionCondition' from 'Source' */
	public BooleanExpression getTransitionCondition();
	/** Set the 'transitionCondition' from 'Source' */
	public void setTransitionCondition(BooleanExpression transitionCondition);

	/** Get the 'activity' from 'Source' */
	public Activity getActivity();
	/** Set the 'activity' from 'Source' */
	public void setActivity(Activity activity);

	/** Get the 'link' from 'Source' */
	public Link getLink();
	/** Set the 'link' from 'Source' */
	public void setLink(Link link);

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
