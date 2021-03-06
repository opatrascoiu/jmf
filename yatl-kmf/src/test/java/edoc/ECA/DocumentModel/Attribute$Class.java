/**
 *
 *  Class Attribute$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:35
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.DocumentModel;

public class Attribute$Class
implements
	Attribute,
    edoc.EdocVisitable
{
	/** Default constructor */
	public Attribute$Class() {
		//--- Set property 'byValue' from 'Attribute' ---
		this.byValue = null;
		//--- Set property 'required' from 'Attribute' ---
		this.required = null;
		//--- Set property 'many' from 'Attribute' ---
		this.many = null;
		//--- Set property 'initialValue' from 'Attribute' ---
		this.initialValue = null;
		//--- Set property 'name' from 'Attribute' ---
		this.name = null;
		//--- Set property 'owner' from 'Attribute' ---
		this.owner = null;
		//--- Set property 'type' from 'Attribute' ---
		this.type = null;
		//--- Set property 'keyAttribute' from 'Attribute' ---
		this.keyAttribute = new java.util.LinkedHashSet();
	}
	/** Specialized constructor */
	public Attribute$Class(Boolean byValue, Boolean required, Boolean many, String initialValue, String name) {
		//--- Set property 'byValue' from 'Attribute' ---
		this.byValue = byValue;
		//--- Set property 'required' from 'Attribute' ---
		this.required = required;
		//--- Set property 'many' from 'Attribute' ---
		this.many = many;
		//--- Set property 'initialValue' from 'Attribute' ---
		this.initialValue = initialValue;
		//--- Set property 'name' from 'Attribute' ---
		this.name = name;
		//--- Set property 'owner' from 'Attribute' ---
		this.owner = null;
		//--- Set property 'type' from 'Attribute' ---
		this.type = null;
		//--- Set property 'keyAttribute' from 'Attribute' ---
		this.keyAttribute = new java.util.LinkedHashSet();
	}


	/** Property 'byValue' from 'Attribute' */
	protected Boolean byValue;
	/** Get property 'byValue' from 'Attribute' */
		public Boolean getByValue() {
		return byValue;
	}
	/** Set property 'byValue' from 'Attribute' */
		public void setByValue(Boolean byValue) {
		this.byValue = byValue;
	}

	/** Property 'required' from 'Attribute' */
	protected Boolean required;
	/** Get property 'required' from 'Attribute' */
		public Boolean getRequired() {
		return required;
	}
	/** Set property 'required' from 'Attribute' */
		public void setRequired(Boolean required) {
		this.required = required;
	}

	/** Property 'many' from 'Attribute' */
	protected Boolean many;
	/** Get property 'many' from 'Attribute' */
		public Boolean getMany() {
		return many;
	}
	/** Set property 'many' from 'Attribute' */
		public void setMany(Boolean many) {
		this.many = many;
	}

	/** Property 'initialValue' from 'Attribute' */
	protected String initialValue;
	/** Get property 'initialValue' from 'Attribute' */
		public String getInitialValue() {
		return initialValue;
	}
	/** Set property 'initialValue' from 'Attribute' */
		public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}

	/** Property 'name' from 'Attribute' */
	protected String name;
	/** Get property 'name' from 'Attribute' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'Attribute' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'owner' from 'Attribute' */
	protected CompositeData owner;
	/** Get property 'owner' from 'Attribute' */
	public CompositeData getOwner() {
		return owner;
	}
	/** Set property 'owner' from 'Attribute' */
	public void setOwner(CompositeData owner) { 
		this.owner = owner;
	}

	/** Property 'type' from 'Attribute' */
	protected DataElement type;
	/** Get property 'type' from 'Attribute' */
	public DataElement getType() {
		return type;
	}
	/** Set property 'type' from 'Attribute' */
	public void setType(DataElement type) { 
		this.type = type;
	}

	/** Property 'keyAttribute' from 'Attribute' */
	protected java.util.Set keyAttribute;
	/** Get property 'keyAttribute' from 'Attribute' */
	public java.util.Set getKeyAttribute() {
		return keyAttribute;
	}
	/** Set property 'keyAttribute' from 'Attribute' */
	public void setKeyAttribute(java.util.Set keyAttribute) { 
		this.keyAttribute = keyAttribute;
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
		String strId = "edoc.ECA.DocumentModel.Attribute";
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
		if (owner != null)
			this.owner.getFeatures().remove(this);
		if (type != null)
			this.type.getAttrs().remove(this);
		java.util.Iterator keyAttributeIt = this.keyAttribute.iterator();
		while (keyAttributeIt.hasNext()) {
			edoc.ECA.EntityPkg.KeyAttribute keyAttributeObj = (edoc.ECA.EntityPkg.KeyAttribute)keyAttributeIt.next();
			if (keyAttributeObj != null)
				keyAttributeObj.setAttributeName(null);
		}
	}

	/** Clone the object */
	public Object clone() {
		Attribute$Class obj = new Attribute$Class();
		obj.byValue = byValue==null ? null : this.byValue;
		obj.required = required==null ? null : this.required;
		obj.many = many==null ? null : this.many;
		obj.initialValue = initialValue==null ? null : this.initialValue;
		obj.name = name==null ? null : this.name;
		obj.owner = owner==null ? null : this.owner;
		obj.type = type==null ? null : this.type;
		obj.keyAttribute = keyAttribute==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.keyAttribute).clone();
		return obj;
	}

	/** Accept 'edoc.ECA.DocumentModel.AttributeVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
