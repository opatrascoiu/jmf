/**
 *
 *  Class Attribute$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.xsd;

public class Attribute$Class
extends
	Element$Class
implements
	Attribute,
    ws.WsVisitable
{
	/** Default constructor */
	public Attribute$Class() {
		//--- Set property 'name' from 'Element' ---
		this.name = null;
		//--- Set property 'variable' from 'Element' ---
		this.variable = null;
		//--- Set property 'complexType' from 'Attribute' ---
		this.complexType = null;
		//--- Set property 'type' from 'Attribute' ---
		this.type = null;
	}
	/** Specialized constructor */
	public Attribute$Class(String name) {
		//--- Set property 'name' from 'Element' ---
		this.name = name;
		//--- Set property 'variable' from 'Element' ---
		this.variable = null;
		//--- Set property 'complexType' from 'Attribute' ---
		this.complexType = null;
		//--- Set property 'type' from 'Attribute' ---
		this.type = null;
	}


	/** Property 'complexType' from 'Attribute' */
	protected ComplexType complexType;
	/** Get property 'complexType' from 'Attribute' */
	public ComplexType getComplexType() {
		return complexType;
	}
	/** Set property 'complexType' from 'Attribute' */
	public void setComplexType(ComplexType complexType) { 
		this.complexType = complexType;
	}

	/** Property 'type' from 'Attribute' */
	protected Type type;
	/** Get property 'type' from 'Attribute' */
	public Type getType() {
		return type;
	}
	/** Set property 'type' from 'Attribute' */
	public void setType(Type type) { 
		this.type = type;
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
		String strId = "ws.xsd.Attribute";
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
		if (variable != null)
			this.variable.setElement(null);
		if (complexType != null)
			this.complexType.getSequence().remove(this);
		if (type != null)
			this.type.setAttribute(null);
	}

	/** Clone the object */
	public Object clone() {
		Attribute$Class obj = new Attribute$Class();
		obj.name = name==null ? null : this.name;
		obj.variable = variable==null ? null : this.variable;
		obj.complexType = complexType==null ? null : this.complexType;
		obj.type = type==null ? null : this.type;
		return obj;
	}

	/** Accept 'ws.xsd.AttributeVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}
