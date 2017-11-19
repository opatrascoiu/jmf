/**
 *
 *  Class Service.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public interface Service
extends
    ws.WsElement,
    ExtensibleElement,
    WSDLElement
{

	/** Get the 'name' from 'Service' */
	public String getName();
	/** Set the 'name' from 'Service' */
	public void setName(String name);

	/** Get the 'definition' from 'Service' */
	public Definition getDefinition();
	/** Set the 'definition' from 'Service' */
	public void setDefinition(Definition definition);

	/** Get the 'ports' from 'Service' */
	public java.util.List getPorts();
	/** Set the 'ports' from 'Service' */
	public void setPorts(java.util.List ports);

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