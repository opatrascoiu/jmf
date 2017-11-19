/**
 *
 *  Class KeyElement$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public class KeyElement$Class
implements
	KeyElement,
    edoc.EdocVisitable
{
	/** Default constructor */
	public KeyElement$Class() {
		//--- Set property 'containingKey' from 'KeyElement' ---
		this.containingKey = null;
	}


	/** Property 'containingKey' from 'KeyElement' */
	protected Key containingKey;
	/** Get property 'containingKey' from 'KeyElement' */
	public Key getContainingKey() {
		return containingKey;
	}
	/** Set property 'containingKey' from 'KeyElement' */
	public void setContainingKey(Key containingKey) { 
		this.containingKey = containingKey;
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
		String strId = "edoc.ECA.EntityPkg.KeyElement";
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
	}

	/** Clone the object */
	public Object clone() {
		KeyElement$Class obj = new KeyElement$Class();
		obj.containingKey = containingKey==null ? null : this.containingKey;
		return obj;
	}

	/** Accept 'edoc.ECA.EntityPkg.KeyElementVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}