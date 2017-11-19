/**
 *
 *  Class SwitchFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class SwitchFactory$Class
	extends ws.WsFactory$Class
	implements SwitchFactory
{
	/** Default factory constructor */
	public SwitchFactory$Class() {
	}
	public SwitchFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Switch obj = new Switch$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Switch", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, ws.bpel.BooleanExpression joinCondition, Boolean suppressJoinFailure) {
		Switch obj = new Switch$Class(name, joinCondition, suppressJoinFailure);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Switch", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "SwitchFactory";
	}

	/** Accept 'ws.bpel.SwitchVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
