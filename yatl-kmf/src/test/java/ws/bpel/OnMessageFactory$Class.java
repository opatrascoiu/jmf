/**
 *
 *  Class OnMessageFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class OnMessageFactory$Class
	extends ws.WsFactory$Class
	implements OnMessageFactory
{
	/** Default factory constructor */
	public OnMessageFactory$Class() {
	}
	public OnMessageFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		OnMessage obj = new OnMessage$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.OnMessage", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, ws.bpel.BooleanExpression joinCondition, Boolean suppressJoinFailure) {
		OnMessage obj = new OnMessage$Class(name, joinCondition, suppressJoinFailure);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.OnMessage", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OnMessageFactory";
	}

	/** Accept 'ws.bpel.OnMessageVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
