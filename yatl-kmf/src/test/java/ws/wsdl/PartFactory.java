/**
 *
 *  Class PartFactory.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public interface PartFactory
extends
    ws.WsFactory
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(String name, String element, ws.xsd.Type type);
}
