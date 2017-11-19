/**
 *
 *  Class Activity$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public class Activity$Class
extends
	edoc.ECA.CCA.ComponentUsage$Class
implements
	Activity,
    edoc.EdocVisitable
{
	/** Default constructor */
	public Activity$Class() {
		//--- Set property 'portsUsed' from 'UsageContext' ---
		this.portsUsed = new java.util.LinkedHashSet();
		//--- Set property 'name' from 'ComponentUsage' ---
		this.name = null;
		//--- Set property 'owner' from 'ComponentUsage' ---
		this.owner = null;
		//--- Set property 'filledBy' from 'ComponentUsage' ---
		this.filledBy = new java.util.LinkedHashSet();
		//--- Set property 'uses' from 'ComponentUsage' ---
		this.uses = null;
		//--- Set property 'configuration' from 'ComponentUsage' ---
		this.configuration = new java.util.LinkedHashSet();
		//--- Set property 'usesArtifact' from 'Activity' ---
		this.usesArtifact = new java.util.LinkedHashSet();
		//--- Set property 'performedBy' from 'Activity' ---
		this.performedBy = new java.util.LinkedHashSet();
	}
	/** Specialized constructor */
	public Activity$Class(String name) {
		//--- Set property 'portsUsed' from 'UsageContext' ---
		this.portsUsed = new java.util.LinkedHashSet();
		//--- Set property 'name' from 'ComponentUsage' ---
		this.name = name;
		//--- Set property 'owner' from 'ComponentUsage' ---
		this.owner = null;
		//--- Set property 'filledBy' from 'ComponentUsage' ---
		this.filledBy = new java.util.LinkedHashSet();
		//--- Set property 'uses' from 'ComponentUsage' ---
		this.uses = null;
		//--- Set property 'configuration' from 'ComponentUsage' ---
		this.configuration = new java.util.LinkedHashSet();
		//--- Set property 'usesArtifact' from 'Activity' ---
		this.usesArtifact = new java.util.LinkedHashSet();
		//--- Set property 'performedBy' from 'Activity' ---
		this.performedBy = new java.util.LinkedHashSet();
	}


	/** Property 'usesArtifact' from 'Activity' */
	protected java.util.Set usesArtifact;
	/** Get property 'usesArtifact' from 'Activity' */
	public java.util.Set getUsesArtifact() {
		return usesArtifact;
	}
	/** Set property 'usesArtifact' from 'Activity' */
	public void setUsesArtifact(java.util.Set usesArtifact) { 
		this.usesArtifact = usesArtifact;
	}

	/** Property 'performedBy' from 'Activity' */
	protected java.util.Set performedBy;
	/** Get property 'performedBy' from 'Activity' */
	public java.util.Set getPerformedBy() {
		return performedBy;
	}
	/** Set property 'performedBy' from 'Activity' */
	public void setPerformedBy(java.util.Set performedBy) { 
		this.performedBy = performedBy;
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
		String strId = "edoc.ECA.BusinessProcessPkg.Activity";
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
		java.util.Iterator portsUsedIt = this.portsUsed.iterator();
		while (portsUsedIt.hasNext()) {
			edoc.ECA.CCA.PortUseage portsUsedObj = (edoc.ECA.CCA.PortUseage)portsUsedIt.next();
			if (portsUsedObj != null)
				portsUsedObj.setExtent(null);
		}
		if (owner != null)
			this.owner.getUses().remove(this);
		java.util.Iterator filledByIt = this.filledBy.iterator();
		while (filledByIt.hasNext()) {
			edoc.ECA.CCA.ContextualBinding filledByObj = (edoc.ECA.CCA.ContextualBinding)filledByIt.next();
			if (filledByObj != null)
				filledByObj.setFills(null);
		}
		if (uses != null)
			this.uses.getUsedBy().remove(this);
		java.util.Iterator configurationIt = this.configuration.iterator();
		while (configurationIt.hasNext()) {
			edoc.ECA.CCA.PropertyValue configurationObj = (edoc.ECA.CCA.PropertyValue)configurationIt.next();
			if (configurationObj != null)
				configurationObj.setOwner(null);
		}
		java.util.Iterator usesArtifactIt = this.usesArtifact.iterator();
		while (usesArtifactIt.hasNext()) {
			edoc.ECA.BusinessProcessPkg.ProcessRole usesArtifactObj = (edoc.ECA.BusinessProcessPkg.ProcessRole)usesArtifactIt.next();
			usesArtifactObj.getArtifactFor().remove(this);
			usesArtifactObj.getArtifactFor().remove(this);
		}
		java.util.Iterator performedByIt = this.performedBy.iterator();
		while (performedByIt.hasNext()) {
			edoc.ECA.BusinessProcessPkg.ProcessRole performedByObj = (edoc.ECA.BusinessProcessPkg.ProcessRole)performedByIt.next();
			performedByObj.getPerforms().remove(this);
			performedByObj.getPerforms().remove(this);
		}
	}

	/** Clone the object */
	public Object clone() {
		Activity$Class obj = new Activity$Class();
		obj.portsUsed = portsUsed==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.portsUsed).clone();
		obj.name = name==null ? null : this.name;
		obj.owner = owner==null ? null : this.owner;
		obj.filledBy = filledBy==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.filledBy).clone();
		obj.uses = uses==null ? null : this.uses;
		obj.configuration = configuration==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.configuration).clone();
		obj.usesArtifact = usesArtifact==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.usesArtifact).clone();
		obj.performedBy = performedBy==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.performedBy).clone();
		return obj;
	}

	/** Accept 'edoc.ECA.BusinessProcessPkg.ActivityVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
