/**
 *
 *  Class ComplexType.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.xsd;

public interface ComplexType
extends
    ws.WsElement,
    Type,
    Element
{

	/** Get the 'sequence' from 'ComplexType' */
	public java.util.List getSequence();
	/** Set the 'sequence' from 'ComplexType' */
	public void setSequence(java.util.List sequence);

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
