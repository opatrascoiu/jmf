/**
 *
 *  Class BooleanExpression.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface BooleanExpression
extends
    ws.WsElement,
    Expression
{

	/** Get the 'case_' from 'BooleanExpression' */
	public Case getCase_();
	/** Set the 'case_' from 'BooleanExpression' */
	public void setCase_(Case case_);

	/** Get the 'onAlarm' from 'BooleanExpression' */
	public OnAlarm getOnAlarm();
	/** Set the 'onAlarm' from 'BooleanExpression' */
	public void setOnAlarm(OnAlarm onAlarm);

	/** Get the 'while_' from 'BooleanExpression' */
	public While getWhile_();
	/** Set the 'while_' from 'BooleanExpression' */
	public void setWhile_(While while_);

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
