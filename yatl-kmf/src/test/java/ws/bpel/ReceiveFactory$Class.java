/**
 *
 *  Class ReceiveFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class ReceiveFactory$Class
	extends ws.WsFactory$Class
	implements ReceiveFactory
{
	/** Default factory constructor */
	public ReceiveFactory$Class() {
	}
	public ReceiveFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Receive obj = new Receive$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Receive", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, ws.bpel.BooleanExpression joinCondition, Boolean suppressJoinFailure, Boolean createInstance) {
		Receive obj = new Receive$Class(name, joinCondition, suppressJoinFailure, createInstance);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Receive", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ReceiveFactory";
	}

	/** Accept 'ws.bpel.ReceiveVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
