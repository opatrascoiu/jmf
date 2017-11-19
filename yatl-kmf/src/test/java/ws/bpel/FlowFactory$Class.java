/**
 *
 *  Class FlowFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class FlowFactory$Class
	extends ws.WsFactory$Class
	implements FlowFactory
{
	/** Default factory constructor */
	public FlowFactory$Class() {
	}
	public FlowFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Flow obj = new Flow$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Flow", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, ws.bpel.BooleanExpression joinCondition, Boolean suppressJoinFailure) {
		Flow obj = new Flow$Class(name, joinCondition, suppressJoinFailure);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Flow", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "FlowFactory";
	}

	/** Accept 'ws.bpel.FlowVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
