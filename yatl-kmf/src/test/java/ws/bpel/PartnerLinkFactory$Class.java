/**
 *
 *  Class PartnerLinkFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class PartnerLinkFactory$Class
	extends ws.WsFactory$Class
	implements PartnerLinkFactory
{
	/** Default factory constructor */
	public PartnerLinkFactory$Class() {
	}
	public PartnerLinkFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		PartnerLink obj = new PartnerLink$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.PartnerLink", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		PartnerLink obj = new PartnerLink$Class(name);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.PartnerLink", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "PartnerLinkFactory";
	}

	/** Accept 'ws.bpel.PartnerLinkVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
