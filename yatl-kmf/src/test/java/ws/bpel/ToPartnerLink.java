/**
 *
 *  Class ToPartnerLink.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface ToPartnerLink
extends
    ws.WsElement,
    ToSpec
{

	/** Get the 'partnerLink' from 'ToPartnerLink' */
	public PartnerLink getPartnerLink();
	/** Set the 'partnerLink' from 'ToPartnerLink' */
	public void setPartnerLink(PartnerLink partnerLink);

	/** Get the id */
	public String getId();
	/** Set the id */
	public void setId(String id);

	/** Override the toString */
	public String toString();

	/** Delete the object */
	public void delete();

	/** Clone the object */
	public Object clone();
}
