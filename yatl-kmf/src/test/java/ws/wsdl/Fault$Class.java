/**
 *
 *  Class Fault$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class Fault$Class
implements
	Fault,
    ws.WsVisitable
{
	/** Default constructor */
	public Fault$Class() {
		//--- Set property 'name' from 'Fault' ---
		this.name = null;
		//--- Set property 'message' from 'Fault' ---
		this.message = null;
		//--- Set property 'operation' from 'Fault' ---
		this.operation = null;
	}
	/** Specialized constructor */
	public Fault$Class(String name, Message message) {
		//--- Set property 'name' from 'Fault' ---
		this.name = name;
		//--- Set property 'message' from 'Fault' ---
		this.message = message;
		//--- Set property 'operation' from 'Fault' ---
		this.operation = null;
	}


	/** Property 'name' from 'Fault' */
	protected String name;
	/** Get property 'name' from 'Fault' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'Fault' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'message' from 'Fault' */
	protected Message message;
	/** Get property 'message' from 'Fault' */
		public Message getMessage() {
		return message;
	}
	/** Set property 'message' from 'Fault' */
		public void setMessage(Message message) {
		this.message = message;
	}

	/** Property 'operation' from 'Fault' */
	protected Operation operation;
	/** Get property 'operation' from 'Fault' */
	public Operation getOperation() {
		return operation;
	}
	/** Set property 'operation' from 'Fault' */
	public void setOperation(Operation operation) { 
		this.operation = operation;
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
		String strId = "ws.wsdl.Fault";
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
		if (operation != null)
			this.operation.getFaults().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		Fault$Class obj = new Fault$Class();
		obj.name = name==null ? null : this.name;
		obj.message = message==null ? null : (Message)this.message.clone();
		obj.operation = operation==null ? null : this.operation;
		return obj;
	}

	/** Accept 'ws.wsdl.FaultVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
