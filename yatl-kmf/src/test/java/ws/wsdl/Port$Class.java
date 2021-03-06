/**
 *
 *  Class Port$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class Port$Class
extends
	WSDLElement$Class
implements
	Port,
    ws.WsVisitable
{
	/** Default constructor */
	public Port$Class() {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'Port' ---
		this.name = null;
		//--- Set property 'service' from 'Port' ---
		this.service = null;
		//--- Set property 'binding' from 'Port' ---
		this.binding = null;
	}
	/** Specialized constructor */
	public Port$Class(String name) {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'Port' ---
		this.name = name;
		//--- Set property 'service' from 'Port' ---
		this.service = null;
		//--- Set property 'binding' from 'Port' ---
		this.binding = null;
	}


	/** Property 'name' from 'Port' */
	protected String name;
	/** Get property 'name' from 'Port' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'Port' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'service' from 'Port' */
	protected Service service;
	/** Get property 'service' from 'Port' */
	public Service getService() {
		return service;
	}
	/** Set property 'service' from 'Port' */
	public void setService(Service service) { 
		this.service = service;
	}

	/** Property 'binding' from 'Port' */
	protected Binding binding;
	/** Get property 'binding' from 'Port' */
	public Binding getBinding() {
		return binding;
	}
	/** Set property 'binding' from 'Port' */
	public void setBinding(Binding binding) { 
		this.binding = binding;
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
		String strId = "ws.wsdl.Port";
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
		if (service != null)
			this.service.getPorts().remove(this);
		if (binding != null)
			this.binding.setPort(null);
	}

	/** Clone the object */
	public Object clone() {
		Port$Class obj = new Port$Class();
		obj.documentation = documentation==null ? null : this.documentation;
		obj.name = name==null ? null : this.name;
		obj.service = service==null ? null : this.service;
		obj.binding = binding==null ? null : this.binding;
		return obj;
	}

	/** Accept 'ws.wsdl.PortVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
