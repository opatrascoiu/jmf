/**
 *
 *  Class PortTypeOperation.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public interface PortTypeOperation
extends
    ws.WsElement,
    Operation,
    WSDLElement
{

	/** Get the 'portType' from 'PortTypeOperation' */
	public PortType getPortType();
	/** Set the 'portType' from 'PortTypeOperation' */
	public void setPortType(PortType portType);

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
