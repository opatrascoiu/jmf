/**
 *
 *  Class KeyElement.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public interface KeyElement
extends
    edoc.EdocElement
{

	/** Get the 'containingKey' from 'KeyElement' */
	public Key getContainingKey();
	/** Set the 'containingKey' from 'KeyElement' */
	public void setContainingKey(Key containingKey);

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