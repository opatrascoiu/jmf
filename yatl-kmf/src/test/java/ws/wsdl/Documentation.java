/**
 *
 *  Class Documentation.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public interface Documentation
extends
    ws.WsElement
{

	/** Get the 'text' from 'Documentation' */
	public String getText();
	/** Set the 'text' from 'Documentation' */
	public void setText(String text);

	/** Get the 'wSDLElement' from 'Documentation' */
	public WSDLElement getWSDLElement();
	/** Set the 'wSDLElement' from 'Documentation' */
	public void setWSDLElement(WSDLElement wSDLElement);

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
