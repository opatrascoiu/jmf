/**
 *
 *  Class EventBasedProcess$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public class EventBasedProcess$Class
implements
	EventBasedProcess,
    edoc.EdocVisitable
{
	/** Default constructor */
	public EventBasedProcess$Class() {
		//--- Set property 'offers' from 'Publisher' ---
		this.offers = new java.util.LinkedHashSet();
		//--- Set property 'subscriptions' from 'Subscriber' ---
		this.subscriptions = new java.util.LinkedHashSet();
		//--- Set property 'nodes' from 'Choreography' ---
		this.nodes = new java.util.LinkedHashSet();
		//--- Set property 'connections' from 'Choreography' ---
		this.connections = new java.util.LinkedHashSet();
		//--- Set property 'supertype' from 'Choreography' ---
		this.supertype = null;
		//--- Set property 'subtypes' from 'Choreography' ---
		this.subtypes = new java.util.LinkedHashSet();
		//--- Set property 'notificationRules' from 'EventBasedProcess' ---
		this.notificationRules = new java.util.LinkedHashSet();
		//--- Set property 'processEvents' from 'EventBasedProcess' ---
		this.processEvents = new java.util.LinkedHashSet();
	}


	/** Property 'offers' from 'Publisher' */
	protected java.util.Set offers;
	/** Get property 'offers' from 'Publisher' */
	public java.util.Set getOffers() {
		return offers;
	}
	/** Set property 'offers' from 'Publisher' */
	public void setOffers(java.util.Set offers) { 
		this.offers = offers;
	}

	/** Property 'subscriptions' from 'Subscriber' */
	protected java.util.Set subscriptions;
	/** Get property 'subscriptions' from 'Subscriber' */
	public java.util.Set getSubscriptions() {
		return subscriptions;
	}
	/** Set property 'subscriptions' from 'Subscriber' */
	public void setSubscriptions(java.util.Set subscriptions) { 
		this.subscriptions = subscriptions;
	}

	/** Property 'nodes' from 'Choreography' */
	protected java.util.Set nodes;
	/** Get property 'nodes' from 'Choreography' */
	public java.util.Set getNodes() {
		return nodes;
	}
	/** Set property 'nodes' from 'Choreography' */
	public void setNodes(java.util.Set nodes) { 
		this.nodes = nodes;
	}

	/** Property 'connections' from 'Choreography' */
	protected java.util.Set connections;
	/** Get property 'connections' from 'Choreography' */
	public java.util.Set getConnections() {
		return connections;
	}
	/** Set property 'connections' from 'Choreography' */
	public void setConnections(java.util.Set connections) { 
		this.connections = connections;
	}

	/** Property 'supertype' from 'Choreography' */
	protected edoc.ECA.CCA.Choreography supertype;
	/** Get property 'supertype' from 'Choreography' */
	public edoc.ECA.CCA.Choreography getSupertype() {
		return supertype;
	}
	/** Set property 'supertype' from 'Choreography' */
	public void setSupertype(edoc.ECA.CCA.Choreography supertype) { 
		this.supertype = supertype;
	}

	/** Property 'subtypes' from 'Choreography' */
	protected java.util.Set subtypes;
	/** Get property 'subtypes' from 'Choreography' */
	public java.util.Set getSubtypes() {
		return subtypes;
	}
	/** Set property 'subtypes' from 'Choreography' */
	public void setSubtypes(java.util.Set subtypes) { 
		this.subtypes = subtypes;
	}

	/** Property 'notificationRules' from 'EventBasedProcess' */
	protected java.util.Set notificationRules;
	/** Get property 'notificationRules' from 'EventBasedProcess' */
	public java.util.Set getNotificationRules() {
		return notificationRules;
	}
	/** Set property 'notificationRules' from 'EventBasedProcess' */
	public void setNotificationRules(java.util.Set notificationRules) { 
		this.notificationRules = notificationRules;
	}

	/** Property 'processEvents' from 'EventBasedProcess' */
	protected java.util.Set processEvents;
	/** Get property 'processEvents' from 'EventBasedProcess' */
	public java.util.Set getProcessEvents() {
		return processEvents;
	}
	/** Set property 'processEvents' from 'EventBasedProcess' */
	public void setProcessEvents(java.util.Set processEvents) { 
		this.processEvents = processEvents;
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
		String strId = "edoc.ECA.Event.EventBasedProcess";
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
		java.util.Iterator offersIt = this.offers.iterator();
		while (offersIt.hasNext()) {
			edoc.ECA.Event.Publication offersObj = (edoc.ECA.Event.Publication)offersIt.next();
			if (offersObj != null)
				offersObj.setOfferedBy(null);
		}
		java.util.Iterator subscriptionsIt = this.subscriptions.iterator();
		while (subscriptionsIt.hasNext()) {
			edoc.ECA.Event.Subscription subscriptionsObj = (edoc.ECA.Event.Subscription)subscriptionsIt.next();
			if (subscriptionsObj != null)
				subscriptionsObj.setHeldBy(null);
		}
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
		java.util.Iterator notificationRulesIt = this.notificationRules.iterator();
		while (notificationRulesIt.hasNext()) {
			edoc.ECA.Event.NotificationRule notificationRulesObj = (edoc.ECA.Event.NotificationRule)notificationRulesIt.next();
			if (notificationRulesObj != null)
				notificationRulesObj.setRuleContext(null);
		}
		java.util.Iterator processEventsIt = this.processEvents.iterator();
		while (processEventsIt.hasNext()) {
			edoc.ECA.Event.ProcessEvent processEventsObj = (edoc.ECA.Event.ProcessEvent)processEventsIt.next();
			if (processEventsObj != null)
				processEventsObj.setEventContext(null);
		}
	}

	/** Clone the object */
	public Object clone() {
		EventBasedProcess$Class obj = new EventBasedProcess$Class();
		obj.offers = offers==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.offers).clone();
		obj.subscriptions = subscriptions==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.subscriptions).clone();
		obj.nodes = nodes==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.nodes).clone();
		obj.connections = connections==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.connections).clone();
		obj.supertype = supertype==null ? null : this.supertype;
		obj.subtypes = subtypes==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.subtypes).clone();
		obj.notificationRules = notificationRules==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.notificationRules).clone();
		obj.processEvents = processEvents==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.processEvents).clone();
		return obj;
	}

	/** Accept 'edoc.ECA.Event.EventBasedProcessVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}