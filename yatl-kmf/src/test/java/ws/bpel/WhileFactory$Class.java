/**
 *
 *  Class WhileFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class WhileFactory$Class
	extends ws.WsFactory$Class
	implements WhileFactory
{
	/** Default factory constructor */
	public WhileFactory$Class() {
	}
	public WhileFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		While obj = new While$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.While", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, ws.bpel.BooleanExpression joinCondition, Boolean suppressJoinFailure) {
		While obj = new While$Class(name, joinCondition, suppressJoinFailure);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.While", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "WhileFactory";
	}

	/** Accept 'ws.bpel.WhileVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
