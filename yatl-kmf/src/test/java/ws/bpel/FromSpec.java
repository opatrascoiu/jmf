/**
 *
 *  Class FromSpec.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface FromSpec
extends
    ws.WsElement
{

	/** Get the 'copy' from 'FromSpec' */
	public Copy getCopy();
	/** Set the 'copy' from 'FromSpec' */
	public void setCopy(Copy copy);

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
