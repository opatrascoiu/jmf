/**
 *
 *  Class PartnerLinkType$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class PartnerLinkType$Class
extends
	ws.wsdl.ExtensibleElement$Class
implements
	PartnerLinkType,
    ws.WsVisitable
{
	/** Default constructor */
	public PartnerLinkType$Class() {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'PartnerLinkType' ---
		this.name = null;
		//--- Set property 'role' from 'PartnerLinkType' ---
		this.role = new java.util.LinkedHashSet();
		//--- Set property 'partnerLink' from 'PartnerLinkType' ---
		this.partnerLink = null;
	}
	/** Specialized constructor */
	public PartnerLinkType$Class(String name) {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'PartnerLinkType' ---
		this.name = name;
		//--- Set property 'role' from 'PartnerLinkType' ---
		this.role = new java.util.LinkedHashSet();
		//--- Set property 'partnerLink' from 'PartnerLinkType' ---
		this.partnerLink = null;
	}


	/** Property 'name' from 'PartnerLinkType' */
	protected String name;
	/** Get property 'name' from 'PartnerLinkType' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'PartnerLinkType' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'role' from 'PartnerLinkType' */
	protected java.util.Set role;
	/** Get property 'role' from 'PartnerLinkType' */
	public java.util.Set getRole() {
		return role;
	}
	/** Set property 'role' from 'PartnerLinkType' */
	public void setRole(java.util.Set role) { 
		this.role = role;
	}

	/** Property 'partnerLink' from 'PartnerLinkType' */
	protected PartnerLink partnerLink;
	/** Get property 'partnerLink' from 'PartnerLinkType' */
	public PartnerLink getPartnerLink() {
		return partnerLink;
	}
	/** Set property 'partnerLink' from 'PartnerLinkType' */
	public void setPartnerLink(PartnerLink partnerLink) { 
		this.partnerLink = partnerLink;
	}

	/** The id */
	protected String id;
	/** Get the id */
	public String getId() {
		return id;
	}
	/** Set the id */
	public void setId(String id) {
		this.id = id;
	}

	/** Override toString */
	public String toString() {
		String strId = "ws.bpel.PartnerLinkType";
		String name = null;
		try {
			java.lang.Class cls = this.getClass();
			java.lang.reflect.Method method = cls.getMethod("getName", new java.lang.Class[] {});
			name = (String) method.invoke(this, new Object[] {});
			if (name != null && name.length()==0) name = null;
		} catch (Exception e) {
		}
		if (name == null)
			return strId+" 'id-"+getId()+"'";
		else
			return strId+" '"+name+"-"+getId()+"'";
	}

	/** Delete the object */
	public void delete() {
		if (documentation != null)
			this.documentation.setWSDLElement(null);
		java.util.Iterator roleIt = this.role.iterator();
		while (roleIt.hasNext()) {
			ws.bpel.Role roleObj = (ws.bpel.Role)roleIt.next();
			if (roleObj != null)
				roleObj.setPartnerLinkType(null);
		}
		if (partnerLink != null)
			this.partnerLink.setPartnerLinkType(null);
	}

	/** Clone the object */
	public Object clone() {
		PartnerLinkType$Class obj = new PartnerLinkType$Class();
		obj.documentation = documentation==null ? null : this.documentation;
		obj.name = name==null ? null : this.name;
		obj.role = role==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.role).clone();
		obj.partnerLink = partnerLink==null ? null : this.partnerLink;
		return obj;
	}

	/** Accept 'ws.bpel.PartnerLinkTypeVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}