/**
 *
 *  Class FaultFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class FaultFactory$Class
	extends ws.WsFactory$Class
	implements FaultFactory
{
	/** Default factory constructor */
	public FaultFactory$Class() {
	}
	public FaultFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Fault obj = new Fault$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Fault", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, ws.wsdl.Message message) {
		Fault obj = new Fault$Class(name, message);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Fault", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "FaultFactory";
	}

	/** Accept 'ws.wsdl.FaultVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
