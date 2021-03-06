/**
 *
 *  Class CaseFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class CaseFactory$Class
	extends ws.WsFactory$Class
	implements CaseFactory
{
	/** Default factory constructor */
	public CaseFactory$Class() {
	}
	public CaseFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Case obj = new Case$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Case", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "CaseFactory";
	}

	/** Accept 'ws.bpel.CaseVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
