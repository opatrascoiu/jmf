/**
 *
 *  Class ProcessRole$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public class ProcessRole$Class
extends
	edoc.ECA.CCA.ComponentUsage$Class
implements
	ProcessRole,
    edoc.EdocVisitable
{
	/** Default constructor */
	public ProcessRole$Class() {
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
		//--- Set property 'selectionRule' from 'ProcessRole' ---
		this.selectionRule = null;
		//--- Set property 'creationRule' from 'ProcessRole' ---
		this.creationRule = null;
		//--- Set property 'artifactFor' from 'ProcessRole' ---
		this.artifactFor = new java.util.LinkedHashSet();
		//--- Set property 'performs' from 'ProcessRole' ---
		this.performs = new java.util.LinkedHashSet();
	}
	/** Specialized constructor */
	public ProcessRole$Class(String name, String selectionRule, String creationRule) {
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
		//--- Set property 'selectionRule' from 'ProcessRole' ---
		this.selectionRule = selectionRule;
		//--- Set property 'creationRule' from 'ProcessRole' ---
		this.creationRule = creationRule;
		//--- Set property 'artifactFor' from 'ProcessRole' ---
		this.artifactFor = new java.util.LinkedHashSet();
		//--- Set property 'performs' from 'ProcessRole' ---
		this.performs = new java.util.LinkedHashSet();
	}


	/** Property 'selectionRule' from 'ProcessRole' */
	protected String selectionRule;
	/** Get property 'selectionRule' from 'ProcessRole' */
		public String getSelectionRule() {
		return selectionRule;
	}
	/** Set property 'selectionRule' from 'ProcessRole' */
		public void setSelectionRule(String selectionRule) {
		this.selectionRule = selectionRule;
	}

	/** Property 'creationRule' from 'ProcessRole' */
	protected String creationRule;
	/** Get property 'creationRule' from 'ProcessRole' */
		public String getCreationRule() {
		return creationRule;
	}
	/** Set property 'creationRule' from 'ProcessRole' */
		public void setCreationRule(String creationRule) {
		this.creationRule = creationRule;
	}

	/** Property 'artifactFor' from 'ProcessRole' */
	protected java.util.Set artifactFor;
	/** Get property 'artifactFor' from 'ProcessRole' */
	public java.util.Set getArtifactFor() {
		return artifactFor;
	}
	/** Set property 'artifactFor' from 'ProcessRole' */
	public void setArtifactFor(java.util.Set artifactFor) { 
		this.artifactFor = artifactFor;
	}

	/** Property 'performs' from 'ProcessRole' */
	protected java.util.Set performs;
	/** Get property 'performs' from 'ProcessRole' */
	public java.util.Set getPerforms() {
		return performs;
	}
	/** Set property 'performs' from 'ProcessRole' */
	public void setPerforms(java.util.Set performs) { 
		this.performs = performs;
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
		String strId = "edoc.ECA.BusinessProcessPkg.ProcessRole";
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
		java.util.Iterator artifactForIt = this.artifactFor.iterator();
		while (artifactForIt.hasNext()) {
			edoc.ECA.BusinessProcessPkg.Activity artifactForObj = (edoc.ECA.BusinessProcessPkg.Activity)artifactForIt.next();
			artifactForObj.getUsesArtifact().remove(this);
			artifactForObj.getUsesArtifact().remove(this);
		}
		java.util.Iterator performsIt = this.performs.iterator();
		while (performsIt.hasNext()) {
			edoc.ECA.BusinessProcessPkg.Activity performsObj = (edoc.ECA.BusinessProcessPkg.Activity)performsIt.next();
			performsObj.getPerformedBy().remove(this);
			performsObj.getPerformedBy().remove(this);
		}
	}

	/** Clone the object */
	public Object clone() {
		ProcessRole$Class obj = new ProcessRole$Class();
		obj.portsUsed = portsUsed==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.portsUsed).clone();
		obj.name = name==null ? null : this.name;
		obj.owner = owner==null ? null : this.owner;
		obj.filledBy = filledBy==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.filledBy).clone();
		obj.uses = uses==null ? null : this.uses;
		obj.configuration = configuration==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.configuration).clone();
		obj.selectionRule = selectionRule==null ? null : this.selectionRule;
		obj.creationRule = creationRule==null ? null : this.creationRule;
		obj.artifactFor = artifactFor==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.artifactFor).clone();
		obj.performs = performs==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.performs).clone();
		return obj;
	}

	/** Accept 'edoc.ECA.BusinessProcessPkg.ProcessRoleVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
