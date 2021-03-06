/**
 *
 *  Class PartnerFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:38
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class PartnerFactory$Class
	extends ws.WsFactory$Class
	implements PartnerFactory
{
	/** Default factory constructor */
	public PartnerFactory$Class() {
	}
	public PartnerFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Partner obj = new Partner$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Partner", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, String serviceLinkType, String myRole, String partnerRole) {
		Partner obj = new Partner$Class(name, serviceLinkType, myRole, partnerRole);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.Partner", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "PartnerFactory";
	}

	/** Accept 'ws.bpel.PartnerVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
