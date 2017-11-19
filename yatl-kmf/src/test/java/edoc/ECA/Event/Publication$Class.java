/**
 *
 *  Class Publication$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public class Publication$Class
extends
	edoc.ECA.CCA.FlowPort$Class
implements
	Publication,
    edoc.EdocVisitable
{
	/** Default constructor */
	public Publication$Class() {
		//--- Set property 'name' from 'Port' ---
		this.name = null;
		//--- Set property 'isSynchronous' from 'Port' ---
		this.isSynchronous = null;
		//--- Set property 'isTransactional' from 'Port' ---
		this.isTransactional = null;
		//--- Set property 'direction' from 'Port' ---
		this.direction = null;
		//--- Set property 'postCondition' from 'Port' ---
		this.postCondition = null;
		//--- Set property 'representedBy' from 'Port' ---
		this.representedBy = new java.util.LinkedHashSet();
		//--- Set property 'owner' from 'Port' ---
		this.owner = null;
		//--- Set property 'type' from 'FlowPort' ---
		this.type = null;
		//--- Set property 'typeProperty' from 'FlowPort' ---
		this.typeProperty = null;
		//--- Set property 'publicationClause' from 'Publication' ---
		this.publicationClause = null;
		//--- Set property 'domain' from 'Publication' ---
		this.domain = null;
		//--- Set property 'offeredBy' from 'Publication' ---
		this.offeredBy = null;
		//--- Set property 'announcedBy' from 'Publication' ---
		this.announcedBy = new java.util.LinkedHashSet();
	}
	/** Specialized constructor */
	public Publication$Class(String name, Boolean isSynchronous, Boolean isTransactional, edoc.ECA.CCA.DirectionType direction, edoc.ECA.CCA.Status postCondition, String publicationClause, String domain) {
		//--- Set property 'name' from 'Port' ---
		this.name = name;
		//--- Set property 'isSynchronous' from 'Port' ---
		this.isSynchronous = isSynchronous;
		//--- Set property 'isTransactional' from 'Port' ---
		this.isTransactional = isTransactional;
		//--- Set property 'direction' from 'Port' ---
		this.direction = direction;
		//--- Set property 'postCondition' from 'Port' ---
		this.postCondition = postCondition;
		//--- Set property 'representedBy' from 'Port' ---
		this.representedBy = new java.util.LinkedHashSet();
		//--- Set property 'owner' from 'Port' ---
		this.owner = null;
		//--- Set property 'type' from 'FlowPort' ---
		this.type = null;
		//--- Set property 'typeProperty' from 'FlowPort' ---
		this.typeProperty = null;
		//--- Set property 'publicationClause' from 'Publication' ---
		this.publicationClause = publicationClause;
		//--- Set property 'domain' from 'Publication' ---
		this.domain = domain;
		//--- Set property 'offeredBy' from 'Publication' ---
		this.offeredBy = null;
		//--- Set property 'announcedBy' from 'Publication' ---
		this.announcedBy = new java.util.LinkedHashSet();
	}


	/** Property 'publicationClause' from 'Publication' */
	protected String publicationClause;
	/** Get property 'publicationClause' from 'Publication' */
		public String getPublicationClause() {
		return publicationClause;
	}
	/** Set property 'publicationClause' from 'Publication' */
		public void setPublicationClause(String publicationClause) {
		this.publicationClause = publicationClause;
	}

	/** Property 'domain' from 'Publication' */
	protected String domain;
	/** Get property 'domain' from 'Publication' */
		public String getDomain() {
		return domain;
	}
	/** Set property 'domain' from 'Publication' */
		public void setDomain(String domain) {
		this.domain = domain;
	}

	/** Property 'offeredBy' from 'Publication' */
	protected Publisher offeredBy;
	/** Get property 'offeredBy' from 'Publication' */
	public Publisher getOfferedBy() {
		return offeredBy;
	}
	/** Set property 'offeredBy' from 'Publication' */
	public void setOfferedBy(Publisher offeredBy) { 
		this.offeredBy = offeredBy;
	}

	/** Property 'announcedBy' from 'Publication' */
	protected java.util.Set announcedBy;
	/** Get property 'announcedBy' from 'Publication' */
	public java.util.Set getAnnouncedBy() {
		return announcedBy;
	}
	/** Set property 'announcedBy' from 'Publication' */
	public void setAnnouncedBy(java.util.Set announcedBy) { 
		this.announcedBy = announcedBy;
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
		String strId = "edoc.ECA.Event.Publication";
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
		java.util.Iterator representedByIt = this.representedBy.iterator();
		while (representedByIt.hasNext()) {
			edoc.ECA.CCA.PortUseage representedByObj = (edoc.ECA.CCA.PortUseage)representedByIt.next();
			if (representedByObj != null)
				representedByObj.setRepresents(null);
		}
		if (owner != null)
			this.owner.getPorts().remove(this);
		if (type != null)
			this.type.getFlowTypeOf().remove(this);
		if (typeProperty != null)
			this.typeProperty.getConstrains().remove(this);
		if (offeredBy != null)
			this.offeredBy.getOffers().remove(this);
		java.util.Iterator announcedByIt = this.announcedBy.iterator();
		while (announcedByIt.hasNext()) {
			edoc.ECA.Event.PubSubNotice announcedByObj = (edoc.ECA.Event.PubSubNotice)announcedByIt.next();
			announcedByObj.getAnnounces().remove(this);
			announcedByObj.getAnnounces().remove(this);
		}
	}

	/** Clone the object */
	public Object clone() {
		Publication$Class obj = new Publication$Class();
		obj.name = name==null ? null : this.name;
		obj.isSynchronous = isSynchronous==null ? null : this.isSynchronous;
		obj.isTransactional = isTransactional==null ? null : this.isTransactional;
		obj.direction = direction==null ? null : (edoc.ECA.CCA.DirectionType)this.direction.clone();
		obj.postCondition = postCondition==null ? null : (edoc.ECA.CCA.Status)this.postCondition.clone();
		obj.representedBy = representedBy==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.representedBy).clone();
		obj.owner = owner==null ? null : this.owner;
		obj.type = type==null ? null : this.type;
		obj.typeProperty = typeProperty==null ? null : this.typeProperty;
		obj.publicationClause = publicationClause==null ? null : this.publicationClause;
		obj.domain = domain==null ? null : this.domain;
		obj.offeredBy = offeredBy==null ? null : this.offeredBy;
		obj.announcedBy = announcedBy==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.announcedBy).clone();
		return obj;
	}

	/** Accept 'edoc.ECA.Event.PublicationVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}