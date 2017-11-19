/**
 *
 *  Class ExceptionGroup$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public class ExceptionGroup$Class
extends
	OutputGroup$Class
implements
	ExceptionGroup,
    edoc.EdocVisitable
{
	/** Default constructor */
	public ExceptionGroup$Class() {
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
		//--- Set property 'ports' from 'PortOwner' ---
		this.ports = new java.util.LinkedHashSet();
	}
	/** Specialized constructor */
	public ExceptionGroup$Class(String name, Boolean isSynchronous, Boolean isTransactional, edoc.ECA.CCA.DirectionType direction, edoc.ECA.CCA.Status postCondition) {
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
		//--- Set property 'ports' from 'PortOwner' ---
		this.ports = new java.util.LinkedHashSet();
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
		String strId = "edoc.ECA.BusinessProcessPkg.ExceptionGroup";
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
		java.util.Iterator portsIt = this.ports.iterator();
		while (portsIt.hasNext()) {
			edoc.ECA.CCA.Port portsObj = (edoc.ECA.CCA.Port)portsIt.next();
			if (portsObj != null)
				portsObj.setOwner(null);
		}
	}

	/** Clone the object */
	public Object clone() {
		ExceptionGroup$Class obj = new ExceptionGroup$Class();
		obj.name = name==null ? null : this.name;
		obj.isSynchronous = isSynchronous==null ? null : this.isSynchronous;
		obj.isTransactional = isTransactional==null ? null : this.isTransactional;
		obj.direction = direction==null ? null : (edoc.ECA.CCA.DirectionType)this.direction.clone();
		obj.postCondition = postCondition==null ? null : (edoc.ECA.CCA.Status)this.postCondition.clone();
		obj.representedBy = representedBy==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.representedBy).clone();
		obj.owner = owner==null ? null : this.owner;
		obj.ports = ports==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.ports).clone();
		return obj;
	}

	/** Accept 'edoc.ECA.BusinessProcessPkg.ExceptionGroupVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
