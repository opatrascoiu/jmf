/**
 *
 *  Class PortTypeFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class PortTypeFactory$Class
	extends ws.WsFactory$Class
	implements PortTypeFactory
{
	/** Default factory constructor */
	public PortTypeFactory$Class() {
	}
	public PortTypeFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		PortType obj = new PortType$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.PortType", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		PortType obj = new PortType$Class(name);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.PortType", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "PortTypeFactory";
	}

	/** Accept 'ws.wsdl.PortTypeVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
