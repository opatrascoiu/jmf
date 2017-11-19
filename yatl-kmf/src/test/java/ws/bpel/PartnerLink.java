/**
 *
 *  Class PartnerLink.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public interface PartnerLink
extends
    ws.WsElement
{

	/** Get the 'name' from 'PartnerLink' */
	public String getName();
	/** Set the 'name' from 'PartnerLink' */
	public void setName(String name);

	/** Get the 'businessProcess' from 'PartnerLink' */
	public BusinessProcess getBusinessProcess();
	/** Set the 'businessProcess' from 'PartnerLink' */
	public void setBusinessProcess(BusinessProcess businessProcess);

	/** Get the 'partnerRole' from 'PartnerLink' */
	public Role getPartnerRole();
	/** Set the 'partnerRole' from 'PartnerLink' */
	public void setPartnerRole(Role partnerRole);

	/** Get the 'partner' from 'PartnerLink' */
	public Partner getPartner();
	/** Set the 'partner' from 'PartnerLink' */
	public void setPartner(Partner partner);

	/** Get the 'fromPartnerLink' from 'PartnerLink' */
	public FromPartnerLink getFromPartnerLink();
	/** Set the 'fromPartnerLink' from 'PartnerLink' */
	public void setFromPartnerLink(FromPartnerLink fromPartnerLink);

	/** Get the 'toPartnerLink' from 'PartnerLink' */
	public ToPartnerLink getToPartnerLink();
	/** Set the 'toPartnerLink' from 'PartnerLink' */
	public void setToPartnerLink(ToPartnerLink toPartnerLink);

	/** Get the 'partnerActivity' from 'PartnerLink' */
	public PartnerActivity getPartnerActivity();
	/** Set the 'partnerActivity' from 'PartnerLink' */
	public void setPartnerActivity(PartnerActivity partnerActivity);

	/** Get the 'partnerLinkType' from 'PartnerLink' */
	public PartnerLinkType getPartnerLinkType();
	/** Set the 'partnerLinkType' from 'PartnerLink' */
	public void setPartnerLinkType(PartnerLinkType partnerLinkType);

	/** Get the 'linkRole' from 'PartnerLink' */
	public Role getLinkRole();
	/** Set the 'linkRole' from 'PartnerLink' */
	public void setLinkRole(Role linkRole);

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
