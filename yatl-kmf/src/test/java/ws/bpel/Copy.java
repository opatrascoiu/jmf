/**
 *
 *  Class Copy.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface Copy
extends
    ws.WsElement
{

	/** Get the 'assign' from 'Copy' */
	public Assign getAssign();
	/** Set the 'assign' from 'Copy' */
	public void setAssign(Assign assign);

	/** Get the 'fromSpec' from 'Copy' */
	public FromSpec getFromSpec();
	/** Set the 'fromSpec' from 'Copy' */
	public void setFromSpec(FromSpec fromSpec);

	/** Get the 'toSpec' from 'Copy' */
	public ToSpec getToSpec();
	/** Set the 'toSpec' from 'Copy' */
	public void setToSpec(ToSpec toSpec);

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