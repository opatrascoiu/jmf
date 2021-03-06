/**
 *
 *  Class PartFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class PartFactory$Class
	extends ws.WsFactory$Class
	implements PartFactory
{
	/** Default factory constructor */
	public PartFactory$Class() {
	}
	public PartFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Part obj = new Part$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Part", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, String element, ws.xsd.Type type) {
		Part obj = new Part$Class(name, element, type);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Part", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "PartFactory";
	}

	/** Accept 'ws.wsdl.PartVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
