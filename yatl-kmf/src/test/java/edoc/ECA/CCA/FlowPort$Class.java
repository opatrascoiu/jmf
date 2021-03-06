/**
 *
 *  Class FlowPort$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public class FlowPort$Class
implements
	FlowPort,
    edoc.EdocVisitable
{
	/** Default constructor */
	public FlowPort$Class() {
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
	}
	/** Specialized constructor */
	public FlowPort$Class(String name, Boolean isSynchronous, Boolean isTransactional, DirectionType direction, Status postCondition) {
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
	}


	/** Property 'name' from 'Port' */
	protected String name;
	/** Get property 'name' from 'Port' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'Port' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'isSynchronous' from 'Port' */
	protected Boolean isSynchronous;
	/** Get property 'isSynchronous' from 'Port' */
		public Boolean getIsSynchronous() {
		return isSynchronous;
	}
	/** Set property 'isSynchronous' from 'Port' */
		public void setIsSynchronous(Boolean isSynchronous) {
		this.isSynchronous = isSynchronous;
	}

	/** Property 'isTransactional' from 'Port' */
	protected Boolean isTransactional;
	/** Get property 'isTransactional' from 'Port' */
		public Boolean getIsTransactional() {
		return isTransactional;
	}
	/** Set property 'isTransactional' from 'Port' */
		public void setIsTransactional(Boolean isTransactional) {
		this.isTransactional = isTransactional;
	}

	/** Property 'direction' from 'Port' */
	protected DirectionType direction;
	/** Get property 'direction' from 'Port' */
		public DirectionType getDirection() {
		return direction;
	}
	/** Set property 'direction' from 'Port' */
		public void setDirection(DirectionType direction) {
		this.direction = direction;
	}

	/** Property 'postCondition' from 'Port' */
	protected Status postCondition;
	/** Get property 'postCondition' from 'Port' */
		public Status getPostCondition() {
		return postCondition;
	}
	/** Set property 'postCondition' from 'Port' */
		public void setPostCondition(Status postCondition) {
		this.postCondition = postCondition;
	}

	/** Property 'representedBy' from 'Port' */
	protected java.util.Set representedBy;
	/** Get property 'representedBy' from 'Port' */
	public java.util.Set getRepresentedBy() {
		return representedBy;
	}
	/** Set property 'representedBy' from 'Port' */
	public void setRepresentedBy(java.util.Set representedBy) { 
		this.representedBy = representedBy;
	}

	/** Property 'owner' from 'Port' */
	protected PortOwner owner;
	/** Get property 'owner' from 'Port' */
	public PortOwner getOwner() {
		return owner;
	}
	/** Set property 'owner' from 'Port' */
	public void setOwner(PortOwner owner) { 
		this.owner = owner;
	}

	/** Property 'type' from 'FlowPort' */
	protected edoc.ECA.DocumentModel.DataElement type;
	/** Get property 'type' from 'FlowPort' */
	public edoc.ECA.DocumentModel.DataElement getType() {
		return type;
	}
	/** Set property 'type' from 'FlowPort' */
	public void setType(edoc.ECA.DocumentModel.DataElement type) { 
		this.type = type;
	}

	/** Property 'typeProperty' from 'FlowPort' */
	protected PropertyDefinition typeProperty;
	/** Get property 'typeProperty' from 'FlowPort' */
	public PropertyDefinition getTypeProperty() {
		return typeProperty;
	}
	/** Set property 'typeProperty' from 'FlowPort' */
	public void setTypeProperty(PropertyDefinition typeProperty) { 
		this.typeProperty = typeProperty;
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
		String strId = "edoc.ECA.CCA.FlowPort";
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
	}

	/** Clone the object */
	public Object clone() {
		FlowPort$Class obj = new FlowPort$Class();
		obj.name = name==null ? null : this.name;
		obj.isSynchronous = isSynchronous==null ? null : this.isSynchronous;
		obj.isTransactional = isTransactional==null ? null : this.isTransactional;
		obj.direction = direction==null ? null : (DirectionType)this.direction.clone();
		obj.postCondition = postCondition==null ? null : (Status)this.postCondition.clone();
		obj.representedBy = representedBy==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.representedBy).clone();
		obj.owner = owner==null ? null : this.owner;
		obj.type = type==null ? null : this.type;
		obj.typeProperty = typeProperty==null ? null : this.typeProperty;
		return obj;
	}

	/** Accept 'edoc.ECA.CCA.FlowPortVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
