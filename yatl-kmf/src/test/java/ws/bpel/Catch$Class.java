/**
 *
 *  Class Catch$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class Catch$Class
implements
	Catch,
    ws.WsVisitable
{
	/** Default constructor */
	public Catch$Class() {
		//--- Set property 'faultName' from 'Catch' ---
		this.faultName = null;
		//--- Set property 'faultVariable' from 'Catch' ---
		this.faultVariable = null;
		//--- Set property 'faultHandler' from 'Catch' ---
		this.faultHandler = null;
		//--- Set property 'activity' from 'Catch' ---
		this.activity = null;
	}
	/** Specialized constructor */
	public Catch$Class(String faultName) {
		//--- Set property 'faultName' from 'Catch' ---
		this.faultName = faultName;
		//--- Set property 'faultVariable' from 'Catch' ---
		this.faultVariable = null;
		//--- Set property 'faultHandler' from 'Catch' ---
		this.faultHandler = null;
		//--- Set property 'activity' from 'Catch' ---
		this.activity = null;
	}


	/** Property 'faultName' from 'Catch' */
	protected String faultName;
	/** Get property 'faultName' from 'Catch' */
		public String getFaultName() {
		return faultName;
	}
	/** Set property 'faultName' from 'Catch' */
		public void setFaultName(String faultName) {
		this.faultName = faultName;
	}

	/** Property 'faultVariable' from 'Catch' */
	protected Variable faultVariable;
	/** Get property 'faultVariable' from 'Catch' */
	public Variable getFaultVariable() {
		return faultVariable;
	}
	/** Set property 'faultVariable' from 'Catch' */
	public void setFaultVariable(Variable faultVariable) { 
		this.faultVariable = faultVariable;
	}

	/** Property 'faultHandler' from 'Catch' */
	protected FaultHandler faultHandler;
	/** Get property 'faultHandler' from 'Catch' */
	public FaultHandler getFaultHandler() {
		return faultHandler;
	}
	/** Set property 'faultHandler' from 'Catch' */
	public void setFaultHandler(FaultHandler faultHandler) { 
		this.faultHandler = faultHandler;
	}

	/** Property 'activity' from 'Catch' */
	protected Activity activity;
	/** Get property 'activity' from 'Catch' */
	public Activity getActivity() {
		return activity;
	}
	/** Set property 'activity' from 'Catch' */
	public void setActivity(Activity activity) { 
		this.activity = activity;
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
		String strId = "ws.bpel.Catch";
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
		if (faultVariable != null)
			this.faultVariable.setCatch_(null);
		if (faultHandler != null)
			this.faultHandler.getCatch_().remove(this);
		if (activity != null)
			this.activity.setCatch_(null);
	}

	/** Clone the object */
	public Object clone() {
		Catch$Class obj = new Catch$Class();
		obj.faultName = faultName==null ? null : this.faultName;
		obj.faultVariable = faultVariable==null ? null : this.faultVariable;
		obj.faultHandler = faultHandler==null ? null : this.faultHandler;
		obj.activity = activity==null ? null : this.activity;
		return obj;
	}

	/** Accept 'ws.bpel.CatchVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}