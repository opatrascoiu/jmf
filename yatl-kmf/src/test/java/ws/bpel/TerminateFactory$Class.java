/**
 *
 *  Class TerminateFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class TerminateFactory$Class
	extends ws.WsFactory$Class
	implements TerminateFactory
{
	/** Default factory constructor */
	public TerminateFactory$Class() {
	}
	public TerminateFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Terminate obj = new Terminate$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Terminate", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, ws.bpel.BooleanExpression joinCondition, Boolean suppressJoinFailure) {
		Terminate obj = new Terminate$Class(name, joinCondition, suppressJoinFailure);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Terminate", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "TerminateFactory";
	}

	/** Accept 'ws.bpel.TerminateVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
