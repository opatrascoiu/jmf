/**
 *
 *  Class Element.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.xsd;

public interface Element
extends
    ws.WsElement
{

	/** Get the 'name' from 'Element' */
	public String getName();
	/** Set the 'name' from 'Element' */
	public void setName(String name);

	/** Get the 'variable' from 'Element' */
	public ws.bpel.Variable getVariable();
	/** Set the 'variable' from 'Element' */
	public void setVariable(ws.bpel.Variable variable);

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
