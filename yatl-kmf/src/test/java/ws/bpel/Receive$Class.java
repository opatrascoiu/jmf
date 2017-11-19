/**
 *
 *  Class Receive$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class Receive$Class
implements
	Receive,
    ws.WsVisitable
{
	/** Default constructor */
	public Receive$Class() {
		//--- Set property 'name' from 'Activity' ---
		this.name = null;
		//--- Set property 'joinCondition' from 'Activity' ---
		this.joinCondition = null;
		//--- Set property 'suppressJoinFailure' from 'Activity' ---
		this.suppressJoinFailure = null;
		//--- Set property 'flow' from 'Activity' ---
		this.flow = null;
		//--- Set property 'parent' from 'Activity' ---
		this.parent = null;
		//--- Set property 'activitySequence' from 'Activity' ---
		this.activitySequence = null;
		//--- Set property 'switch_' from 'Activity' ---
		this.switch_ = null;
		//--- Set property 'case_' from 'Activity' ---
		this.case_ = null;
		//--- Set property 'while_' from 'Activity' ---
		this.while_ = null;
		//--- Set property 'scope' from 'Activity' ---
		this.scope = null;
		//--- Set property 'compensationScope' from 'Activity' ---
		this.compensationScope = null;
		//--- Set property 'activityFaultHandler' from 'Activity' ---
		this.activityFaultHandler = null;
		//--- Set property 'targetOf' from 'Activity' ---
		this.targetOf = new java.util.LinkedHashSet();
		//--- Set property 'sourceOf' from 'Activity' ---
		this.sourceOf = new java.util.LinkedHashSet();
		//--- Set property 'activityEventHandler' from 'Activity' ---
		this.activityEventHandler = null;
		//--- Set property 'activityAlarm' from 'Activity' ---
		this.activityAlarm = null;
		//--- Set property 'activityOnMessage' from 'Activity' ---
		this.activityOnMessage = null;
		//--- Set property 'invoke' from 'Activity' ---
		this.invoke = null;
		//--- Set property 'catch_' from 'Activity' ---
		this.catch_ = null;
		//--- Set property 'correlations' from 'PartnerActivity' ---
		this.correlations = new java.util.LinkedHashSet();
		//--- Set property 'partnerLink' from 'PartnerActivity' ---
		this.partnerLink = null;
		//--- Set property 'portType' from 'PartnerActivity' ---
		this.portType = null;
		//--- Set property 'operation' from 'PartnerActivity' ---
		this.operation = null;
		//--- Set property 'createInstance' from 'Receive' ---
		this.createInstance = null;
		//--- Set property 'variable' from 'Receive' ---
		this.variable = null;
	}
	/** Specialized constructor */
	public Receive$Class(String name, BooleanExpression joinCondition, Boolean suppressJoinFailure, Boolean createInstance) {
		//--- Set property 'name' from 'Activity' ---
		this.name = name;
		//--- Set property 'joinCondition' from 'Activity' ---
		this.joinCondition = joinCondition;
		//--- Set property 'suppressJoinFailure' from 'Activity' ---
		this.suppressJoinFailure = suppressJoinFailure;
		//--- Set property 'flow' from 'Activity' ---
		this.flow = null;
		//--- Set property 'parent' from 'Activity' ---
		this.parent = null;
		//--- Set property 'activitySequence' from 'Activity' ---
		this.activitySequence = null;
		//--- Set property 'switch_' from 'Activity' ---
		this.switch_ = null;
		//--- Set property 'case_' from 'Activity' ---
		this.case_ = null;
		//--- Set property 'while_' from 'Activity' ---
		this.while_ = null;
		//--- Set property 'scope' from 'Activity' ---
		this.scope = null;
		//--- Set property 'compensationScope' from 'Activity' ---
		this.compensationScope = null;
		//--- Set property 'activityFaultHandler' from 'Activity' ---
		this.activityFaultHandler = null;
		//--- Set property 'targetOf' from 'Activity' ---
		this.targetOf = new java.util.LinkedHashSet();
		//--- Set property 'sourceOf' from 'Activity' ---
		this.sourceOf = new java.util.LinkedHashSet();
		//--- Set property 'activityEventHandler' from 'Activity' ---
		this.activityEventHandler = null;
		//--- Set property 'activityAlarm' from 'Activity' ---
		this.activityAlarm = null;
		//--- Set property 'activityOnMessage' from 'Activity' ---
		this.activityOnMessage = null;
		//--- Set property 'invoke' from 'Activity' ---
		this.invoke = null;
		//--- Set property 'catch_' from 'Activity' ---
		this.catch_ = null;
		//--- Set property 'correlations' from 'PartnerActivity' ---
		this.correlations = new java.util.LinkedHashSet();
		//--- Set property 'partnerLink' from 'PartnerActivity' ---
		this.partnerLink = null;
		//--- Set property 'portType' from 'PartnerActivity' ---
		this.portType = null;
		//--- Set property 'operation' from 'PartnerActivity' ---
		this.operation = null;
		//--- Set property 'createInstance' from 'Receive' ---
		this.createInstance = createInstance;
		//--- Set property 'variable' from 'Receive' ---
		this.variable = null;
	}


	/** Property 'name' from 'Activity' */
	protected String name;
	/** Get property 'name' from 'Activity' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'Activity' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'joinCondition' from 'Activity' */
	protected BooleanExpression joinCondition;
	/** Get property 'joinCondition' from 'Activity' */
		public BooleanExpression getJoinCondition() {
		return joinCondition;
	}
	/** Set property 'joinCondition' from 'Activity' */
		public void setJoinCondition(BooleanExpression joinCondition) {
		this.joinCondition = joinCondition;
	}

	/** Property 'suppressJoinFailure' from 'Activity' */
	protected Boolean suppressJoinFailure;
	/** Get property 'suppressJoinFailure' from 'Activity' */
		public Boolean getSuppressJoinFailure() {
		return suppressJoinFailure;
	}
	/** Set property 'suppressJoinFailure' from 'Activity' */
		public void setSuppressJoinFailure(Boolean suppressJoinFailure) {
		this.suppressJoinFailure = suppressJoinFailure;
	}

	/** Property 'flow' from 'Activity' */
	protected Flow flow;
	/** Get property 'flow' from 'Activity' */
	public Flow getFlow() {
		return flow;
	}
	/** Set property 'flow' from 'Activity' */
	public void setFlow(Flow flow) { 
		this.flow = flow;
	}

	/** Property 'parent' from 'Activity' */
	protected StructuredActivity parent;
	/** Get property 'parent' from 'Activity' */
	public StructuredActivity getParent() {
		return parent;
	}
	/** Set property 'parent' from 'Activity' */
	public void setParent(StructuredActivity parent) { 
		this.parent = parent;
	}

	/** Property 'activitySequence' from 'Activity' */
	protected ActivitySequence activitySequence;
	/** Get property 'activitySequence' from 'Activity' */
	public ActivitySequence getActivitySequence() {
		return activitySequence;
	}
	/** Set property 'activitySequence' from 'Activity' */
	public void setActivitySequence(ActivitySequence activitySequence) { 
		this.activitySequence = activitySequence;
	}

	/** Property 'switch_' from 'Activity' */
	protected Switch switch_;
	/** Get property 'switch_' from 'Activity' */
	public Switch getSwitch_() {
		return switch_;
	}
	/** Set property 'switch_' from 'Activity' */
	public void setSwitch_(Switch switch_) { 
		this.switch_ = switch_;
	}

	/** Property 'case_' from 'Activity' */
	protected Case case_;
	/** Get property 'case_' from 'Activity' */
	public Case getCase_() {
		return case_;
	}
	/** Set property 'case_' from 'Activity' */
	public void setCase_(Case case_) { 
		this.case_ = case_;
	}

	/** Property 'while_' from 'Activity' */
	protected While while_;
	/** Get property 'while_' from 'Activity' */
	public While getWhile_() {
		return while_;
	}
	/** Set property 'while_' from 'Activity' */
	public void setWhile_(While while_) { 
		this.while_ = while_;
	}

	/** Property 'scope' from 'Activity' */
	protected Scope scope;
	/** Get property 'scope' from 'Activity' */
	public Scope getScope() {
		return scope;
	}
	/** Set property 'scope' from 'Activity' */
	public void setScope(Scope scope) { 
		this.scope = scope;
	}

	/** Property 'compensationScope' from 'Activity' */
	protected Scope compensationScope;
	/** Get property 'compensationScope' from 'Activity' */
	public Scope getCompensationScope() {
		return compensationScope;
	}
	/** Set property 'compensationScope' from 'Activity' */
	public void setCompensationScope(Scope compensationScope) { 
		this.compensationScope = compensationScope;
	}

	/** Property 'activityFaultHandler' from 'Activity' */
	protected FaultHandler activityFaultHandler;
	/** Get property 'activityFaultHandler' from 'Activity' */
	public FaultHandler getActivityFaultHandler() {
		return activityFaultHandler;
	}
	/** Set property 'activityFaultHandler' from 'Activity' */
	public void setActivityFaultHandler(FaultHandler activityFaultHandler) { 
		this.activityFaultHandler = activityFaultHandler;
	}

	/** Property 'targetOf' from 'Activity' */
	protected java.util.Set targetOf;
	/** Get property 'targetOf' from 'Activity' */
	public java.util.Set getTargetOf() {
		return targetOf;
	}
	/** Set property 'targetOf' from 'Activity' */
	public void setTargetOf(java.util.Set targetOf) { 
		this.targetOf = targetOf;
	}

	/** Property 'sourceOf' from 'Activity' */
	protected java.util.Set sourceOf;
	/** Get property 'sourceOf' from 'Activity' */
	public java.util.Set getSourceOf() {
		return sourceOf;
	}
	/** Set property 'sourceOf' from 'Activity' */
	public void setSourceOf(java.util.Set sourceOf) { 
		this.sourceOf = sourceOf;
	}

	/** Property 'activityEventHandler' from 'Activity' */
	protected EventHandler activityEventHandler;
	/** Get property 'activityEventHandler' from 'Activity' */
	public EventHandler getActivityEventHandler() {
		return activityEventHandler;
	}
	/** Set property 'activityEventHandler' from 'Activity' */
	public void setActivityEventHandler(EventHandler activityEventHandler) { 
		this.activityEventHandler = activityEventHandler;
	}

	/** Property 'activityAlarm' from 'Activity' */
	protected OnAlarm activityAlarm;
	/** Get property 'activityAlarm' from 'Activity' */
	public OnAlarm getActivityAlarm() {
		return activityAlarm;
	}
	/** Set property 'activityAlarm' from 'Activity' */
	public void setActivityAlarm(OnAlarm activityAlarm) { 
		this.activityAlarm = activityAlarm;
	}

	/** Property 'activityOnMessage' from 'Activity' */
	protected OnMessage activityOnMessage;
	/** Get property 'activityOnMessage' from 'Activity' */
	public OnMessage getActivityOnMessage() {
		return activityOnMessage;
	}
	/** Set property 'activityOnMessage' from 'Activity' */
	public void setActivityOnMessage(OnMessage activityOnMessage) { 
		this.activityOnMessage = activityOnMessage;
	}

	/** Property 'invoke' from 'Activity' */
	protected Invoke invoke;
	/** Get property 'invoke' from 'Activity' */
	public Invoke getInvoke() {
		return invoke;
	}
	/** Set property 'invoke' from 'Activity' */
	public void setInvoke(Invoke invoke) { 
		this.invoke = invoke;
	}

	/** Property 'catch_' from 'Activity' */
	protected Catch catch_;
	/** Get property 'catch_' from 'Activity' */
	public Catch getCatch_() {
		return catch_;
	}
	/** Set property 'catch_' from 'Activity' */
	public void setCatch_(Catch catch_) { 
		this.catch_ = catch_;
	}

	/** Property 'correlations' from 'PartnerActivity' */
	protected java.util.Set correlations;
	/** Get property 'correlations' from 'PartnerActivity' */
	public java.util.Set getCorrelations() {
		return correlations;
	}
	/** Set property 'correlations' from 'PartnerActivity' */
	public void setCorrelations(java.util.Set correlations) { 
		this.correlations = correlations;
	}

	/** Property 'partnerLink' from 'PartnerActivity' */
	protected PartnerLink partnerLink;
	/** Get property 'partnerLink' from 'PartnerActivity' */
	public PartnerLink getPartnerLink() {
		return partnerLink;
	}
	/** Set property 'partnerLink' from 'PartnerActivity' */
	public void setPartnerLink(PartnerLink partnerLink) { 
		this.partnerLink = partnerLink;
	}

	/** Property 'portType' from 'PartnerActivity' */
	protected ws.wsdl.PortType portType;
	/** Get property 'portType' from 'PartnerActivity' */
	public ws.wsdl.PortType getPortType() {
		return portType;
	}
	/** Set property 'portType' from 'PartnerActivity' */
	public void setPortType(ws.wsdl.PortType portType) { 
		this.portType = portType;
	}

	/** Property 'operation' from 'PartnerActivity' */
	protected ws.wsdl.Operation operation;
	/** Get property 'operation' from 'PartnerActivity' */
	public ws.wsdl.Operation getOperation() {
		return operation;
	}
	/** Set property 'operation' from 'PartnerActivity' */
	public void setOperation(ws.wsdl.Operation operation) { 
		this.operation = operation;
	}

	/** Property 'createInstance' from 'Receive' */
	protected Boolean createInstance;
	/** Get property 'createInstance' from 'Receive' */
		public Boolean getCreateInstance() {
		return createInstance;
	}
	/** Set property 'createInstance' from 'Receive' */
		public void setCreateInstance(Boolean createInstance) {
		this.createInstance = createInstance;
	}

	/** Property 'variable' from 'Receive' */
	protected Variable variable;
	/** Get property 'variable' from 'Receive' */
	public Variable getVariable() {
		return variable;
	}
	/** Set property 'variable' from 'Receive' */
	public void setVariable(Variable variable) { 
		this.variable = variable;
	}

	/** Operation 'subActivities' from 'Activity' */
	public java.util.Set subActivities()	{
		return null;
	}

	/** Operation 'allSubActivities' from 'Activity' */
	public java.util.Set allSubActivities()	{
		return null;
	}

	/** Operation 'allParents' from 'Activity' */
	public java.util.Set allParents()	{
		return null;
	}

	/** Operation 'initialActivities' from 'Activity' */
	public java.util.Set initialActivities()	{
		return null;
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
		String strId = "ws.bpel.Receive";
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
		if (flow != null)
			this.flow.getActivities().remove(this);
		if (parent != null)
			this.parent.setActivity(null);
		if (activitySequence != null)
			this.activitySequence.getBody().remove(this);
		if (switch_ != null)
			this.switch_.setOtherwise(null);
		if (case_ != null)
			this.case_.setBody(null);
		if (while_ != null)
			this.while_.setBody(null);
		if (scope != null)
			this.scope.setScopeActivity(null);
		if (compensationScope != null)
			this.compensationScope.setCompensationActivity(null);
		if (activityFaultHandler != null)
			this.activityFaultHandler.setCatchAll(null);
		java.util.Iterator targetOfIt = this.targetOf.iterator();
		while (targetOfIt.hasNext()) {
			ws.bpel.Target targetOfObj = (ws.bpel.Target)targetOfIt.next();
			if (targetOfObj != null)
				targetOfObj.setActivity(null);
		}
		java.util.Iterator sourceOfIt = this.sourceOf.iterator();
		while (sourceOfIt.hasNext()) {
			ws.bpel.Source sourceOfObj = (ws.bpel.Source)sourceOfIt.next();
			if (sourceOfObj != null)
				sourceOfObj.setActivity(null);
		}
		if (activityEventHandler != null)
			this.activityEventHandler.setActivity(null);
		if (activityAlarm != null)
			this.activityAlarm.setActivity(null);
		if (activityOnMessage != null)
			this.activityOnMessage.setActivity(null);
		if (invoke != null)
			this.invoke.setCompensationHandler(null);
		if (catch_ != null)
			this.catch_.setActivity(null);
		java.util.Iterator correlationsIt = this.correlations.iterator();
		while (correlationsIt.hasNext()) {
			ws.bpel.Correlation correlationsObj = (ws.bpel.Correlation)correlationsIt.next();
			if (correlationsObj != null)
				correlationsObj.setPartnerActivity(null);
		}
		if (partnerLink != null)
			this.partnerLink.setPartnerActivity(null);
		if (portType != null)
			this.portType.setPartnerActivity(null);
		if (operation != null)
			this.operation.setPartnerActivity(null);
		if (variable != null)
			this.variable.setReceive(null);
	}

	/** Clone the object */
	public Object clone() {
		Receive$Class obj = new Receive$Class();
		obj.name = name==null ? null : this.name;
		obj.joinCondition = joinCondition==null ? null : (BooleanExpression)this.joinCondition.clone();
		obj.suppressJoinFailure = suppressJoinFailure==null ? null : this.suppressJoinFailure;
		obj.flow = flow==null ? null : this.flow;
		obj.parent = parent==null ? null : this.parent;
		obj.activitySequence = activitySequence==null ? null : this.activitySequence;
		obj.switch_ = switch_==null ? null : this.switch_;
		obj.case_ = case_==null ? null : this.case_;
		obj.while_ = while_==null ? null : this.while_;
		obj.scope = scope==null ? null : this.scope;
		obj.compensationScope = compensationScope==null ? null : this.compensationScope;
		obj.activityFaultHandler = activityFaultHandler==null ? null : this.activityFaultHandler;
		obj.targetOf = targetOf==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.targetOf).clone();
		obj.sourceOf = sourceOf==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.sourceOf).clone();
		obj.activityEventHandler = activityEventHandler==null ? null : this.activityEventHandler;
		obj.activityAlarm = activityAlarm==null ? null : this.activityAlarm;
		obj.activityOnMessage = activityOnMessage==null ? null : this.activityOnMessage;
		obj.invoke = invoke==null ? null : this.invoke;
		obj.catch_ = catch_==null ? null : this.catch_;
		obj.correlations = correlations==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.correlations).clone();
		obj.partnerLink = partnerLink==null ? null : this.partnerLink;
		obj.portType = portType==null ? null : this.portType;
		obj.operation = operation==null ? null : this.operation;
		obj.createInstance = createInstance==null ? null : this.createInstance;
		obj.variable = variable==null ? null : this.variable;
		return obj;
	}

	/** Accept 'ws.bpel.ReceiveVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
