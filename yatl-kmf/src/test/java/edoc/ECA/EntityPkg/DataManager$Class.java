/**
 *
 *  Class DataManager$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public class DataManager$Class
extends
	edoc.ECA.CCA.ProcessComponent$Class
implements
	DataManager,
    edoc.EdocVisitable
{
	/** Default constructor */
	public DataManager$Class() {
		//--- Set property 'nodes' from 'Choreography' ---
		this.nodes = new java.util.LinkedHashSet();
		//--- Set property 'connections' from 'Choreography' ---
		this.connections = new java.util.LinkedHashSet();
		//--- Set property 'supertype' from 'Choreography' ---
		this.supertype = null;
		//--- Set property 'subtypes' from 'Choreography' ---
		this.subtypes = new java.util.LinkedHashSet();
		//--- Set property 'portsUsed' from 'UsageContext' ---
		this.portsUsed = new java.util.LinkedHashSet();
		//--- Set property 'bindings' from 'Composition' ---
		this.bindings = new java.util.LinkedHashSet();
		//--- Set property 'uses' from 'Composition' ---
		this.uses = new java.util.LinkedHashSet();
		//--- Set property 'name' from 'PortOwner' ---
		this.name = null;
		//--- Set property 'ports' from 'PortOwner' ---
		this.ports = new java.util.LinkedHashSet();
		//--- Set property 'granularity' from 'ProcessComponent' ---
		this.granularity = null;
		//--- Set property 'isPersistent' from 'ProcessComponent' ---
		this.isPersistent = null;
		//--- Set property 'primitiveKind' from 'ProcessComponent' ---
		this.primitiveKind = null;
		//--- Set property 'primitiveSpec' from 'ProcessComponent' ---
		this.primitiveSpec = null;
		//--- Set property 'contextualBindings' from 'ProcessComponent' ---
		this.contextualBindings = new java.util.LinkedHashSet();
		//--- Set property 'usedBy' from 'ProcessComponent' ---
		this.usedBy = new java.util.LinkedHashSet();
		//--- Set property 'properties' from 'ProcessComponent' ---
		this.properties = new java.util.LinkedHashSet();
		//--- Set property 'networkAccess' from 'DataManager' ---
		this.networkAccess = null;
		//--- Set property 'shareable' from 'DataManager' ---
		this.shareable = null;
		//--- Set property 'manages' from 'DataManager' ---
		this.manages = null;
	}
	/** Specialized constructor */
	public DataManager$Class(String name, edoc.ECA.CCA.GranularityKind granularity, Boolean isPersistent, String primitiveKind, String primitiveSpec, Boolean networkAccess, Boolean shareable) {
		//--- Set property 'nodes' from 'Choreography' ---
		this.nodes = new java.util.LinkedHashSet();
		//--- Set property 'connections' from 'Choreography' ---
		this.connections = new java.util.LinkedHashSet();
		//--- Set property 'supertype' from 'Choreography' ---
		this.supertype = null;
		//--- Set property 'subtypes' from 'Choreography' ---
		this.subtypes = new java.util.LinkedHashSet();
		//--- Set property 'portsUsed' from 'UsageContext' ---
		this.portsUsed = new java.util.LinkedHashSet();
		//--- Set property 'bindings' from 'Composition' ---
		this.bindings = new java.util.LinkedHashSet();
		//--- Set property 'uses' from 'Composition' ---
		this.uses = new java.util.LinkedHashSet();
		//--- Set property 'name' from 'PortOwner' ---
		this.name = name;
		//--- Set property 'ports' from 'PortOwner' ---
		this.ports = new java.util.LinkedHashSet();
		//--- Set property 'granularity' from 'ProcessComponent' ---
		this.granularity = granularity;
		//--- Set property 'isPersistent' from 'ProcessComponent' ---
		this.isPersistent = isPersistent;
		//--- Set property 'primitiveKind' from 'ProcessComponent' ---
		this.primitiveKind = primitiveKind;
		//--- Set property 'primitiveSpec' from 'ProcessComponent' ---
		this.primitiveSpec = primitiveSpec;
		//--- Set property 'contextualBindings' from 'ProcessComponent' ---
		this.contextualBindings = new java.util.LinkedHashSet();
		//--- Set property 'usedBy' from 'ProcessComponent' ---
		this.usedBy = new java.util.LinkedHashSet();
		//--- Set property 'properties' from 'ProcessComponent' ---
		this.properties = new java.util.LinkedHashSet();
		//--- Set property 'networkAccess' from 'DataManager' ---
		this.networkAccess = networkAccess;
		//--- Set property 'shareable' from 'DataManager' ---
		this.shareable = shareable;
		//--- Set property 'manages' from 'DataManager' ---
		this.manages = null;
	}


	/** Property 'networkAccess' from 'DataManager' */
	protected Boolean networkAccess;
	/** Get property 'networkAccess' from 'DataManager' */
		public Boolean getNetworkAccess() {
		return networkAccess;
	}
	/** Set property 'networkAccess' from 'DataManager' */
		public void setNetworkAccess(Boolean networkAccess) {
		this.networkAccess = networkAccess;
	}

	/** Property 'shareable' from 'DataManager' */
	protected Boolean shareable;
	/** Get property 'shareable' from 'DataManager' */
		public Boolean getShareable() {
		return shareable;
	}
	/** Set property 'shareable' from 'DataManager' */
		public void setShareable(Boolean shareable) {
		this.shareable = shareable;
	}

	/** Property 'manages' from 'DataManager' */
	protected edoc.ECA.DocumentModel.CompositeData manages;
	/** Get property 'manages' from 'DataManager' */
	public edoc.ECA.DocumentModel.CompositeData getManages() {
		return manages;
	}
	/** Set property 'manages' from 'DataManager' */
	public void setManages(edoc.ECA.DocumentModel.CompositeData manages) { 
		this.manages = manages;
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
		String strId = "edoc.ECA.EntityPkg.DataManager";
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
		java.util.Iterator nodesIt = this.nodes.iterator();
		while (nodesIt.hasNext()) {
			edoc.ECA.CCA.Node nodesObj = (edoc.ECA.CCA.Node)nodesIt.next();
			if (nodesObj != null)
				nodesObj.setOwner(null);
		}
		java.util.Iterator connectionsIt = this.connections.iterator();
		while (connectionsIt.hasNext()) {
			edoc.ECA.CCA.AbstractTransition connectionsObj = (edoc.ECA.CCA.AbstractTransition)connectionsIt.next();
			if (connectionsObj != null)
				connectionsObj.setOwner(null);
		}
		if (supertype != null)
			this.supertype.getSubtypes().remove(this);
		java.util.Iterator subtypesIt = this.subtypes.iterator();
		while (subtypesIt.hasNext()) {
			edoc.ECA.CCA.Choreography subtypesObj = (edoc.ECA.CCA.Choreography)subtypesIt.next();
			if (subtypesObj != null)
				subtypesObj.setSupertype(null);
		}
		java.util.Iterator portsUsedIt = this.portsUsed.iterator();
		while (portsUsedIt.hasNext()) {
			edoc.ECA.CCA.PortUseage portsUsedObj = (edoc.ECA.CCA.PortUseage)portsUsedIt.next();
			if (portsUsedObj != null)
				portsUsedObj.setExtent(null);
		}
		java.util.Iterator bindingsIt = this.bindings.iterator();
		while (bindingsIt.hasNext()) {
			edoc.ECA.CCA.ContextualBinding bindingsObj = (edoc.ECA.CCA.ContextualBinding)bindingsIt.next();
			if (bindingsObj != null)
				bindingsObj.setOwner(null);
		}
		java.util.Iterator usesIt = this.uses.iterator();
		while (usesIt.hasNext()) {
			edoc.ECA.CCA.ComponentUsage usesObj = (edoc.ECA.CCA.ComponentUsage)usesIt.next();
			if (usesObj != null)
				usesObj.setOwner(null);
		}
		java.util.Iterator portsIt = this.ports.iterator();
		while (portsIt.hasNext()) {
			edoc.ECA.CCA.Port portsObj = (edoc.ECA.CCA.Port)portsIt.next();
			if (portsObj != null)
				portsObj.setOwner(null);
		}
		java.util.Iterator contextualBindingsIt = this.contextualBindings.iterator();
		while (contextualBindingsIt.hasNext()) {
			edoc.ECA.CCA.ContextualBinding contextualBindingsObj = (edoc.ECA.CCA.ContextualBinding)contextualBindingsIt.next();
			if (contextualBindingsObj != null)
				contextualBindingsObj.setBindsTo(null);
		}
		java.util.Iterator usedByIt = this.usedBy.iterator();
		while (usedByIt.hasNext()) {
			edoc.ECA.CCA.ComponentUsage usedByObj = (edoc.ECA.CCA.ComponentUsage)usedByIt.next();
			if (usedByObj != null)
				usedByObj.setUses(null);
		}
		java.util.Iterator propertiesIt = this.properties.iterator();
		while (propertiesIt.hasNext()) {
			edoc.ECA.CCA.PropertyDefinition propertiesObj = (edoc.ECA.CCA.PropertyDefinition)propertiesIt.next();
			if (propertiesObj != null)
				propertiesObj.setComponent(null);
		}
		if (manages != null)
			this.manages.getManagedBy().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		DataManager$Class obj = new DataManager$Class();
		obj.nodes = nodes==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.nodes).clone();
		obj.connections = connections==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.connections).clone();
		obj.supertype = supertype==null ? null : this.supertype;
		obj.subtypes = subtypes==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.subtypes).clone();
		obj.portsUsed = portsUsed==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.portsUsed).clone();
		obj.bindings = bindings==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.bindings).clone();
		obj.uses = uses==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.uses).clone();
		obj.name = name==null ? null : this.name;
		obj.ports = ports==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.ports).clone();
		obj.granularity = granularity==null ? null : (edoc.ECA.CCA.GranularityKind)this.granularity.clone();
		obj.isPersistent = isPersistent==null ? null : this.isPersistent;
		obj.primitiveKind = primitiveKind==null ? null : this.primitiveKind;
		obj.primitiveSpec = primitiveSpec==null ? null : this.primitiveSpec;
		obj.contextualBindings = contextualBindings==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.contextualBindings).clone();
		obj.usedBy = usedBy==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.usedBy).clone();
		obj.properties = properties==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.properties).clone();
		obj.networkAccess = networkAccess==null ? null : this.networkAccess;
		obj.shareable = shareable==null ? null : this.shareable;
		obj.manages = manages==null ? null : this.manages;
		return obj;
	}

	/** Accept 'edoc.ECA.EntityPkg.DataManagerVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}