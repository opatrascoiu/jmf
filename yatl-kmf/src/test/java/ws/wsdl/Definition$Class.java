/**
 *
 *  Class Definition$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class Definition$Class
extends
	ExtensibleElement$Class
implements
	Definition,
    ws.WsVisitable
{
	/** Default constructor */
	public Definition$Class() {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'imports' from 'Definition' ---
		this.imports = new java.util.Vector();
		//--- Set property 'types' from 'Definition' ---
		this.types = new java.util.Vector();
		//--- Set property 'messages' from 'Definition' ---
		this.messages = new java.util.Vector();
		//--- Set property 'bindings' from 'Definition' ---
		this.bindings = new java.util.Vector();
		//--- Set property 'services' from 'Definition' ---
		this.services = new java.util.Vector();
		//--- Set property 'portTypes' from 'Definition' ---
		this.portTypes = new java.util.Vector();
	}


	/** Property 'imports' from 'Definition' */
	protected java.util.List imports;
	/** Get property 'imports' from 'Definition' */
	public java.util.List getImports() {
		return imports;
	}
	/** Set property 'imports' from 'Definition' */
	public void setImports(java.util.List imports) { 
		this.imports = imports;
	}

	/** Property 'types' from 'Definition' */
	protected java.util.List types;
	/** Get property 'types' from 'Definition' */
	public java.util.List getTypes() {
		return types;
	}
	/** Set property 'types' from 'Definition' */
	public void setTypes(java.util.List types) { 
		this.types = types;
	}

	/** Property 'messages' from 'Definition' */
	protected java.util.List messages;
	/** Get property 'messages' from 'Definition' */
	public java.util.List getMessages() {
		return messages;
	}
	/** Set property 'messages' from 'Definition' */
	public void setMessages(java.util.List messages) { 
		this.messages = messages;
	}

	/** Property 'bindings' from 'Definition' */
	protected java.util.List bindings;
	/** Get property 'bindings' from 'Definition' */
	public java.util.List getBindings() {
		return bindings;
	}
	/** Set property 'bindings' from 'Definition' */
	public void setBindings(java.util.List bindings) { 
		this.bindings = bindings;
	}

	/** Property 'services' from 'Definition' */
	protected java.util.List services;
	/** Get property 'services' from 'Definition' */
	public java.util.List getServices() {
		return services;
	}
	/** Set property 'services' from 'Definition' */
	public void setServices(java.util.List services) { 
		this.services = services;
	}

	/** Property 'portTypes' from 'Definition' */
	protected java.util.List portTypes;
	/** Get property 'portTypes' from 'Definition' */
	public java.util.List getPortTypes() {
		return portTypes;
	}
	/** Set property 'portTypes' from 'Definition' */
	public void setPortTypes(java.util.List portTypes) { 
		this.portTypes = portTypes;
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
		String strId = "ws.wsdl.Definition";
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
		java.util.Iterator importsIt = this.imports.iterator();
		while (importsIt.hasNext()) {
			ws.wsdl.Import importsObj = (ws.wsdl.Import)importsIt.next();
			if (importsObj != null)
				importsObj.setDefinition(null);
		}
		java.util.Iterator typesIt = this.types.iterator();
		while (typesIt.hasNext()) {
			ws.xsd.Type typesObj = (ws.xsd.Type)typesIt.next();
			if (typesObj != null)
				typesObj.setDefinition(null);
		}
		java.util.Iterator messagesIt = this.messages.iterator();
		while (messagesIt.hasNext()) {
			ws.wsdl.Message messagesObj = (ws.wsdl.Message)messagesIt.next();
			if (messagesObj != null)
				messagesObj.setDefinition(null);
		}
		java.util.Iterator bindingsIt = this.bindings.iterator();
		while (bindingsIt.hasNext()) {
			ws.wsdl.Binding bindingsObj = (ws.wsdl.Binding)bindingsIt.next();
			if (bindingsObj != null)
				bindingsObj.setDefinition(null);
		}
		java.util.Iterator servicesIt = this.services.iterator();
		while (servicesIt.hasNext()) {
			ws.wsdl.Service servicesObj = (ws.wsdl.Service)servicesIt.next();
			if (servicesObj != null)
				servicesObj.setDefinition(null);
		}
		java.util.Iterator portTypesIt = this.portTypes.iterator();
		while (portTypesIt.hasNext()) {
			ws.wsdl.PortType portTypesObj = (ws.wsdl.PortType)portTypesIt.next();
			if (portTypesObj != null)
				portTypesObj.setDefinition(null);
		}
	}

	/** Clone the object */
	public Object clone() {
		Definition$Class obj = new Definition$Class();
		obj.documentation = documentation==null ? null : this.documentation;
		obj.imports = imports==null ? null : (java.util.List)((java.util.Vector)this.imports).clone();
		obj.types = types==null ? null : (java.util.List)((java.util.Vector)this.types).clone();
		obj.messages = messages==null ? null : (java.util.List)((java.util.Vector)this.messages).clone();
		obj.bindings = bindings==null ? null : (java.util.List)((java.util.Vector)this.bindings).clone();
		obj.services = services==null ? null : (java.util.List)((java.util.Vector)this.services).clone();
		obj.portTypes = portTypes==null ? null : (java.util.List)((java.util.Vector)this.portTypes).clone();
		return obj;
	}

	/** Accept 'ws.wsdl.DefinitionVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
