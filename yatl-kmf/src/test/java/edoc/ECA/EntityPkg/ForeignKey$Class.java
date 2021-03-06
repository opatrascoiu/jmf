/**
 *
 *  Class ForeignKey$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public class ForeignKey$Class
extends
	KeyElement$Class
implements
	ForeignKey,
    edoc.EdocVisitable
{
	/** Default constructor */
	public ForeignKey$Class() {
		//--- Set property 'containingKey' from 'KeyElement' ---
		this.containingKey = null;
		//--- Set property 'owner' from 'ForeignKey' ---
		this.owner = null;
	}


	/** Property 'owner' from 'ForeignKey' */
	protected EntityData owner;
	/** Get property 'owner' from 'ForeignKey' */
	public EntityData getOwner() {
		return owner;
	}
	/** Set property 'owner' from 'ForeignKey' */
	public void setOwner(EntityData owner) { 
		this.owner = owner;
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
		String strId = "edoc.ECA.EntityPkg.ForeignKey";
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
		if (owner != null)
			this.owner.getForeignKeys().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		ForeignKey$Class obj = new ForeignKey$Class();
		obj.containingKey = containingKey==null ? null : this.containingKey;
		obj.owner = owner==null ? null : this.owner;
		return obj;
	}

	/** Accept 'edoc.ECA.EntityPkg.ForeignKeyVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
