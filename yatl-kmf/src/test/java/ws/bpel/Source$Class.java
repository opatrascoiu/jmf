/**
 *
 *  Class Source$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class Source$Class
implements
	Source,
    ws.WsVisitable
{
	/** Default constructor */
	public Source$Class() {
		//--- Set property 'transitionCondition' from 'Source' ---
		this.transitionCondition = null;
		//--- Set property 'activity' from 'Source' ---
		this.activity = null;
		//--- Set property 'link' from 'Source' ---
		this.link = null;
	}
	/** Specialized constructor */
	public Source$Class(BooleanExpression transitionCondition) {
		//--- Set property 'transitionCondition' from 'Source' ---
		this.transitionCondition = transitionCondition;
		//--- Set property 'activity' from 'Source' ---
		this.activity = null;
		//--- Set property 'link' from 'Source' ---
		this.link = null;
	}


	/** Property 'transitionCondition' from 'Source' */
	protected BooleanExpression transitionCondition;
	/** Get property 'transitionCondition' from 'Source' */
		public BooleanExpression getTransitionCondition() {
		return transitionCondition;
	}
	/** Set property 'transitionCondition' from 'Source' */
		public void setTransitionCondition(BooleanExpression transitionCondition) {
		this.transitionCondition = transitionCondition;
	}

	/** Property 'activity' from 'Source' */
	protected Activity activity;
	/** Get property 'activity' from 'Source' */
	public Activity getActivity() {
		return activity;
	}
	/** Set property 'activity' from 'Source' */
	public void setActivity(Activity activity) { 
		this.activity = activity;
	}

	/** Property 'link' from 'Source' */
	protected Link link;
	/** Get property 'link' from 'Source' */
	public Link getLink() {
		return link;
	}
	/** Set property 'link' from 'Source' */
	public void setLink(Link link) { 
		this.link = link;
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
		String strId = "ws.bpel.Source";
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
		if (activity != null)
			this.activity.getSourceOf().remove(this);
		if (link != null)
			this.link.setSource(null);
	}

	/** Clone the object */
	public Object clone() {
		Source$Class obj = new Source$Class();
		obj.transitionCondition = transitionCondition==null ? null : (BooleanExpression)this.transitionCondition.clone();
		obj.activity = activity==null ? null : this.activity;
		obj.link = link==null ? null : this.link;
		return obj;
	}

	/** Accept 'ws.bpel.SourceVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
