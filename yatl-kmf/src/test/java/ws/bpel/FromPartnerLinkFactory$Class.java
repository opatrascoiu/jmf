/**
 *
 *  Class FromPartnerLinkFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class FromPartnerLinkFactory$Class
	extends ws.WsFactory$Class
	implements FromPartnerLinkFactory
{
	/** Default factory constructor */
	public FromPartnerLinkFactory$Class() {
	}
	public FromPartnerLinkFactory$Class(ws.repository.WsRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		FromPartnerLink obj = new FromPartnerLink$Class();
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.FromPartnerLink", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(ws.bpel.EndPointReference endPointReference) {
		FromPartnerLink obj = new FromPartnerLink$Class(endPointReference);
		obj.setId(ws.WsFactory$Class.newId());
		repository.addElement("ws.bpel.FromPartnerLink", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "FromPartnerLinkFactory";
	}

	/** Accept 'ws.bpel.FromPartnerLinkVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
