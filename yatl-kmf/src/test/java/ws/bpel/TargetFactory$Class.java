/**
 *
 *  Class TargetFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class TargetFactory$Class
	extends ws.WsFactory$Class
	implements TargetFactory
{
	/** Default factory constructor */
	public TargetFactory$Class() {
	}
	public TargetFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Target obj = new Target$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Target", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "TargetFactory";
	}

	/** Accept 'ws.bpel.TargetVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
