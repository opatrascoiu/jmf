/**
 *
 *  Class InitiatingRole.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public interface InitiatingRole
extends
    edoc.EdocElement
{

	/** Get the 'name' from 'InitiatingRole' */
	public String getName();
	/** Set the 'name' from 'InitiatingRole' */
	public void setName(String name);

	/** Get the 'protocol' from 'InitiatingRole' */
	public Protocol getProtocol();
	/** Set the 'protocol' from 'InitiatingRole' */
	public void setProtocol(Protocol protocol);

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