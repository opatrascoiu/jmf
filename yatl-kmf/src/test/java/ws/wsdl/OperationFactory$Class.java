/**
 *
 *  Class OperationFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class OperationFactory$Class
	extends ws.WsFactory$Class
	implements OperationFactory
{
	/** Default factory constructor */
	public OperationFactory$Class() {
	}
	public OperationFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Operation obj = new Operation$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Operation", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		Operation obj = new Operation$Class(name);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.wsdl.Operation", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OperationFactory";
	}

	/** Accept 'ws.wsdl.OperationVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}