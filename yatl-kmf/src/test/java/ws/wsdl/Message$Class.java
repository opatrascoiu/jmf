/**
 *
 *  Class Message$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class Message$Class
extends
	ExtensibleElement$Class
implements
	Message,
    ws.WsVisitable
{
	/** Default constructor */
	public Message$Class() {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'Message' ---
		this.name = null;
		//--- Set property 'variable' from 'Message' ---
		this.variable = null;
		//--- Set property 'definition' from 'Message' ---
		this.definition = null;
		//--- Set property 'parts' from 'Message' ---
		this.parts = new java.util.Vector();
	}
	/** Specialized constructor */
	public Message$Class(String name) {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'Message' ---
		this.name = name;
		//--- Set property 'variable' from 'Message' ---
		this.variable = null;
		//--- Set property 'definition' from 'Message' ---
		this.definition = null;
		//--- Set property 'parts' from 'Message' ---
		this.parts = new java.util.Vector();
	}


	/** Property 'name' from 'Message' */
	protected String name;
	/** Get property 'name' from 'Message' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'Message' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'variable' from 'Message' */
	protected ws.bpel.Variable variable;
	/** Get property 'variable' from 'Message' */
	public ws.bpel.Variable getVariable() {
		return variable;
	}
	/** Set property 'variable' from 'Message' */
	public void setVariable(ws.bpel.Variable variable) { 
		this.variable = variable;
	}

	/** Property 'definition' from 'Message' */
	protected Definition definition;
	/** Get property 'definition' from 'Message' */
	public Definition getDefinition() {
		return definition;
	}
	/** Set property 'definition' from 'Message' */
	public void setDefinition(Definition definition) { 
		this.definition = definition;
	}

	/** Property 'parts' from 'Message' */
	protected java.util.List parts;
	/** Get property 'parts' from 'Message' */
	public java.util.List getParts() {
		return parts;
	}
	/** Set property 'parts' from 'Message' */
	public void setParts(java.util.List parts) { 
		this.parts = parts;
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
		String strId = "ws.wsdl.Message";
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
		if (documentation != null)
			this.documentation.setWSDLElement(null);
		if (variable != null)
			this.variable.setMessageType(null);
		if (definition != null)
			this.definition.getMessages().remove(this);
		java.util.Iterator partsIt = this.parts.iterator();
		while (partsIt.hasNext()) {
			ws.wsdl.Part partsObj = (ws.wsdl.Part)partsIt.next();
			if (partsObj != null)
				partsObj.setMessage(null);
		}
	}

	/** Clone the object */
	public Object clone() {
		Message$Class obj = new Message$Class();
		obj.documentation = documentation==null ? null : this.documentation;
		obj.name = name==null ? null : this.name;
		obj.variable = variable==null ? null : this.variable;
		obj.definition = definition==null ? null : this.definition;
		obj.parts = parts==null ? null : (java.util.List)((java.util.Vector)this.parts).clone();
		return obj;
	}

	/** Accept 'ws.wsdl.MessageVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
