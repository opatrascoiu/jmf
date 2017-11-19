/**
 *
 *  Class PortType$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.wsdl;

public class PortType$Class
extends
	ExtensibleElement$Class
implements
	PortType,
    ws.WsVisitable
{
	/** Default constructor */
	public PortType$Class() {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'PortType' ---
		this.name = null;
		//--- Set property 'partnerActivity' from 'PortType' ---
		this.partnerActivity = null;
		//--- Set property 'definition' from 'PortType' ---
		this.definition = null;
		//--- Set property 'operations' from 'PortType' ---
		this.operations = new java.util.Vector();
	}
	/** Specialized constructor */
	public PortType$Class(String name) {
		//--- Set property 'documentation' from 'WSDLElement' ---
		this.documentation = null;
		//--- Set property 'name' from 'PortType' ---
		this.name = name;
		//--- Set property 'partnerActivity' from 'PortType' ---
		this.partnerActivity = null;
		//--- Set property 'definition' from 'PortType' ---
		this.definition = null;
		//--- Set property 'operations' from 'PortType' ---
		this.operations = new java.util.Vector();
	}


	/** Property 'name' from 'PortType' */
	protected String name;
	/** Get property 'name' from 'PortType' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'PortType' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'partnerActivity' from 'PortType' */
	protected ws.bpel.PartnerActivity partnerActivity;
	/** Get property 'partnerActivity' from 'PortType' */
	public ws.bpel.PartnerActivity getPartnerActivity() {
		return partnerActivity;
	}
	/** Set property 'partnerActivity' from 'PortType' */
	public void setPartnerActivity(ws.bpel.PartnerActivity partnerActivity) { 
		this.partnerActivity = partnerActivity;
	}

	/** Property 'definition' from 'PortType' */
	protected Definition definition;
	/** Get property 'definition' from 'PortType' */
	public Definition getDefinition() {
		return definition;
	}
	/** Set property 'definition' from 'PortType' */
	public void setDefinition(Definition definition) { 
		this.definition = definition;
	}

	/** Property 'operations' from 'PortType' */
	protected java.util.List operations;
	/** Get property 'operations' from 'PortType' */
	public java.util.List getOperations() {
		return operations;
	}
	/** Set property 'operations' from 'PortType' */
	public void setOperations(java.util.List operations) { 
		this.operations = operations;
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
		String strId = "ws.wsdl.PortType";
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
		if (partnerActivity != null)
			this.partnerActivity.setPortType(null);
		if (definition != null)
			this.definition.getPortTypes().remove(this);
		java.util.Iterator operationsIt = this.operations.iterator();
		while (operationsIt.hasNext()) {
			ws.wsdl.PortTypeOperation operationsObj = (ws.wsdl.PortTypeOperation)operationsIt.next();
			if (operationsObj != null)
				operationsObj.setPortType(null);
		}
	}

	/** Clone the object */
	public Object clone() {
		PortType$Class obj = new PortType$Class();
		obj.documentation = documentation==null ? null : this.documentation;
		obj.name = name==null ? null : this.name;
		obj.partnerActivity = partnerActivity==null ? null : this.partnerActivity;
		obj.definition = definition==null ? null : this.definition;
		obj.operations = operations==null ? null : (java.util.List)((java.util.Vector)this.operations).clone();
		return obj;
	}

	/** Accept 'ws.wsdl.PortTypeVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
