/**
 *
 *  Class KeyAttribute$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public class KeyAttribute$Class
extends
	KeyElement$Class
implements
	KeyAttribute,
    edoc.EdocVisitable
{
	/** Default constructor */
	public KeyAttribute$Class() {
		//--- Set property 'containingKey' from 'KeyElement' ---
		this.containingKey = null;
		//--- Set property 'attributeName' from 'KeyAttribute' ---
		this.attributeName = null;
	}


	/** Property 'attributeName' from 'KeyAttribute' */
	protected edoc.ECA.DocumentModel.Attribute attributeName;
	/** Get property 'attributeName' from 'KeyAttribute' */
	public edoc.ECA.DocumentModel.Attribute getAttributeName() {
		return attributeName;
	}
	/** Set property 'attributeName' from 'KeyAttribute' */
	public void setAttributeName(edoc.ECA.DocumentModel.Attribute attributeName) { 
		this.attributeName = attributeName;
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
		String strId = "edoc.ECA.EntityPkg.KeyAttribute";
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
		if (containingKey != null)
			this.containingKey.getKeyElements().remove(this);
		if (attributeName != null)
			this.attributeName.getKeyAttribute().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		KeyAttribute$Class obj = new KeyAttribute$Class();
		obj.containingKey = containingKey==null ? null : this.containingKey;
		obj.attributeName = attributeName==null ? null : this.attributeName;
		return obj;
	}

	/** Accept 'edoc.ECA.EntityPkg.KeyAttributeVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}