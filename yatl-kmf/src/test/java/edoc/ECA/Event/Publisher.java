/**
 *
 *  Class Publisher.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public interface Publisher
extends
    edoc.EdocElement
{

	/** Get the 'offers' from 'Publisher' */
	public java.util.Set getOffers();
	/** Set the 'offers' from 'Publisher' */
	public void setOffers(java.util.Set offers);

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