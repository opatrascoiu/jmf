/**
 *
 *  Class Wait.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface Wait
extends
    ws.WsElement,
    BasicActivity,
    Activity
{

	/** Get the 'durationKind' from 'Wait' */
	public DurationKind getDurationKind();
	/** Set the 'durationKind' from 'Wait' */
	public void setDurationKind(DurationKind durationKind);

	/** Get the 'expression' from 'Wait' */
	public Expression getExpression();
	/** Set the 'expression' from 'Wait' */
	public void setExpression(Expression expression);

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
