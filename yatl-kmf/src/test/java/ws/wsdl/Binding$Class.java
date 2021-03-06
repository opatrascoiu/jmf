/**
 *
 *  Class Binding$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class Binding$Class
extends
	ExtensibleElement$Class
implements
	Binding,
    ws.WsVisitable
{
	/** Default constructor */
	public Binding$Class() {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'Binding' ---
		this.name = null;
		//--- Set property 'definition' from 'Binding' ---
		this.definition = null;
		//--- Set property 'operations' from 'Binding' ---
		this.operations = new java.util.Vector();
		//--- Set property 'port' from 'Binding' ---
		this.port = null;
	}
	/** Specialized constructor */
	public Binding$Class(String name) {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'Binding' ---
		this.name = name;
		//--- Set property 'definition' from 'Binding' ---
		this.definition = null;
		//--- Set property 'operations' from 'Binding' ---
		this.operations = new java.util.Vector();
		//--- Set property 'port' from 'Binding' ---
		this.port = null;
	}


	/** Property 'name' from 'Binding' */
	protected String name;
	/** Get property 'name' from 'Binding' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'Binding' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'definition' from 'Binding' */
	protected Definition definition;
	/** Get property 'definition' from 'Binding' */
	public Definition getDefinition() {
		return definition;
	}
	/** Set property 'definition' from 'Binding' */
	public void setDefinition(Definition definition) { 
		this.definition = definition;
	}

	/** Property 'operations' from 'Binding' */
	protected java.util.List operations;
	/** Get property 'operations' from 'Binding' */
	public java.util.List getOperations() {
		return operations;
	}
	/** Set property 'operations' from 'Binding' */
	public void setOperations(java.util.List operations) { 
		this.operations = operations;
	}

	/** Property 'port' from 'Binding' */
	protected Port port;
	/** Get property 'port' from 'Binding' */
	public Port getPort() {
		return port;
	}
	/** Set property 'port' from 'Binding' */
	public void setPort(Port port) { 
		this.port = port;
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
		String strId = "ws.wsdl.Binding";
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
		if (definition != null)
			this.definition.getBindings().remove(this);
		java.util.Iterator operationsIt = this.operations.iterator();
		while (operationsIt.hasNext()) {
			ws.wsdl.BindingOperation operationsObj = (ws.wsdl.BindingOperation)operationsIt.next();
			if (operationsObj != null)
				operationsObj.setBinding(null);
		}
		if (port != null)
			this.port.setBinding(null);
	}

	/** Clone the object */
	public Object clone() {
		Binding$Class obj = new Binding$Class();
		obj.documentation = documentation==null ? null : this.documentation;
		obj.name = name==null ? null : this.name;
		obj.definition = definition==null ? null : this.definition;
		obj.operations = operations==null ? null : (java.util.List)((java.util.Vector)this.operations).clone();
		obj.port = port==null ? null : this.port;
		return obj;
	}

	/** Accept 'ws.wsdl.BindingVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
