/**
 *
 *  Class PropertyAlias.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface PropertyAlias
extends
    ws.WsElement,
    ws.wsdl.ExtensibleElement,
    ws.wsdl.WSDLElement
{

	/** Get the 'query' from 'PropertyAlias' */
	public String getQuery();
	/** Set the 'query' from 'PropertyAlias' */
	public void setQuery(String query);

	/** Get the 'property' from 'PropertyAlias' */
	public Property getProperty();
	/** Set the 'property' from 'PropertyAlias' */
	public void setProperty(Property property);

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
