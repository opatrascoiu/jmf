/**
 *
 *  Class FromVariableFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class FromVariableFactory$Class
	extends ws.WsFactory$Class
	implements FromVariableFactory
{
	/** Default factory constructor */
	public FromVariableFactory$Class() {
	}
	public FromVariableFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		FromVariable obj = new FromVariable$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.FromVariable", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "FromVariableFactory";
	}

	/** Accept 'ws.bpel.FromVariableVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
