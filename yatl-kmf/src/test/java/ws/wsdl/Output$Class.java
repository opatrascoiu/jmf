/**
 *
 *  Class Output$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class Output$Class
implements
	Output,
    ws.WsVisitable
{
	/** Default constructor */
	public Output$Class() {
		//--- Set property 'name' from 'Output' ---
		this.name = null;
		//--- Set property 'message' from 'Output' ---
		this.message = null;
		//--- Set property 'operation' from 'Output' ---
		this.operation = null;
	}
	/** Specialized constructor */
	public Output$Class(String name, Message message) {
		//--- Set property 'name' from 'Output' ---
		this.name = name;
		//--- Set property 'message' from 'Output' ---
		this.message = message;
		//--- Set property 'operation' from 'Output' ---
		this.operation = null;
	}


	/** Property 'name' from 'Output' */
	protected String name;
	/** Get property 'name' from 'Output' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'Output' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'message' from 'Output' */
	protected Message message;
	/** Get property 'message' from 'Output' */
		public Message getMessage() {
		return message;
	}
	/** Set property 'message' from 'Output' */
		public void setMessage(Message message) {
		this.message = message;
	}

	/** Property 'operation' from 'Output' */
	protected Operation operation;
	/** Get property 'operation' from 'Output' */
	public Operation getOperation() {
		return operation;
	}
	/** Set property 'operation' from 'Output' */
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
		String strId = "ws.wsdl.Output";
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
			this.operation.setOutput(null);
	}

	/** Clone the object */
	public Object clone() {
		Output$Class obj = new Output$Class();
		obj.name = name==null ? null : this.name;
		obj.message = message==null ? null : (Message)this.message.clone();
		obj.operation = operation==null ? null : this.operation;
		return obj;
	}

	/** Accept 'ws.wsdl.OutputVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
