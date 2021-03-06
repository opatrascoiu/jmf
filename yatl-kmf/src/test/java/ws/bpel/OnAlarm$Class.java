/**
 *
 *  Class OnAlarm$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class OnAlarm$Class
implements
	OnAlarm,
    ws.WsVisitable
{
	/** Default constructor */
	public OnAlarm$Class() {
		//--- Set property 'durationKind' from 'OnAlarm' ---
		this.durationKind = null;
		//--- Set property 'pick' from 'OnAlarm' ---
		this.pick = null;
		//--- Set property 'booleanExpression' from 'OnAlarm' ---
		this.booleanExpression = null;
		//--- Set property 'eventHandler' from 'OnAlarm' ---
		this.eventHandler = null;
		//--- Set property 'activity' from 'OnAlarm' ---
		this.activity = null;
		//--- Set property 'son' from 'OnAlarm' ---
		this.son = null;
		//--- Set property 'parent' from 'OnAlarm' ---
		this.parent = null;
	}
	/** Specialized constructor */
	public OnAlarm$Class(DurationKind durationKind) {
		//--- Set property 'durationKind' from 'OnAlarm' ---
		this.durationKind = durationKind;
		//--- Set property 'pick' from 'OnAlarm' ---
		this.pick = null;
		//--- Set property 'booleanExpression' from 'OnAlarm' ---
		this.booleanExpression = null;
		//--- Set property 'eventHandler' from 'OnAlarm' ---
		this.eventHandler = null;
		//--- Set property 'activity' from 'OnAlarm' ---
		this.activity = null;
		//--- Set property 'son' from 'OnAlarm' ---
		this.son = null;
		//--- Set property 'parent' from 'OnAlarm' ---
		this.parent = null;
	}


	/** Property 'durationKind' from 'OnAlarm' */
	protected DurationKind durationKind;
	/** Get property 'durationKind' from 'OnAlarm' */
		public DurationKind getDurationKind() {
		return durationKind;
	}
	/** Set property 'durationKind' from 'OnAlarm' */
		public void setDurationKind(DurationKind durationKind) {
		this.durationKind = durationKind;
	}

	/** Property 'pick' from 'OnAlarm' */
	protected Pick pick;
	/** Get property 'pick' from 'OnAlarm' */
	public Pick getPick() {
		return pick;
	}
	/** Set property 'pick' from 'OnAlarm' */
	public void setPick(Pick pick) { 
		this.pick = pick;
	}

	/** Property 'booleanExpression' from 'OnAlarm' */
	protected BooleanExpression booleanExpression;
	/** Get property 'booleanExpression' from 'OnAlarm' */
	public BooleanExpression getBooleanExpression() {
		return booleanExpression;
	}
	/** Set property 'booleanExpression' from 'OnAlarm' */
	public void setBooleanExpression(BooleanExpression booleanExpression) { 
		this.booleanExpression = booleanExpression;
	}

	/** Property 'eventHandler' from 'OnAlarm' */
	protected EventHandler eventHandler;
	/** Get property 'eventHandler' from 'OnAlarm' */
	public EventHandler getEventHandler() {
		return eventHandler;
	}
	/** Set property 'eventHandler' from 'OnAlarm' */
	public void setEventHandler(EventHandler eventHandler) { 
		this.eventHandler = eventHandler;
	}

	/** Property 'activity' from 'OnAlarm' */
	protected Activity activity;
	/** Get property 'activity' from 'OnAlarm' */
	public Activity getActivity() {
		return activity;
	}
	/** Set property 'activity' from 'OnAlarm' */
	public void setActivity(Activity activity) { 
		this.activity = activity;
	}

	/** Property 'son' from 'OnAlarm' */
	protected OnAlarm son;
	/** Get property 'son' from 'OnAlarm' */
	public OnAlarm getSon() {
		return son;
	}
	/** Set property 'son' from 'OnAlarm' */
	public void setSon(OnAlarm son) { 
		this.son = son;
	}

	/** Property 'parent' from 'OnAlarm' */
	protected OnAlarm parent;
	/** Get property 'parent' from 'OnAlarm' */
	public OnAlarm getParent() {
		return parent;
	}
	/** Set property 'parent' from 'OnAlarm' */
	public void setParent(OnAlarm parent) { 
		this.parent = parent;
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
		String strId = "ws.bpel.OnAlarm";
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
		if (pick != null)
			this.pick.getOnAlarms().remove(this);
		if (booleanExpression != null)
			this.booleanExpression.setOnAlarm(null);
		if (eventHandler != null)
			this.eventHandler.getOnAlarm().remove(this);
		if (activity != null)
			this.activity.setActivityAlarm(null);
		if (son != null)
			this.son.setParent(null);
		if (parent != null)
			this.parent.setSon(null);
	}

	/** Clone the object */
	public Object clone() {
		OnAlarm$Class obj = new OnAlarm$Class();
		obj.durationKind = durationKind==null ? null : (DurationKind)this.durationKind.clone();
		obj.pick = pick==null ? null : this.pick;
		obj.booleanExpression = booleanExpression==null ? null : this.booleanExpression;
		obj.eventHandler = eventHandler==null ? null : this.eventHandler;
		obj.activity = activity==null ? null : this.activity;
		obj.son = son==null ? null : this.son;
		obj.parent = parent==null ? null : this.parent;
		return obj;
	}

	/** Accept 'ws.bpel.OnAlarmVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
