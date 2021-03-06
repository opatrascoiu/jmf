/**
 *
 *  Class PropertyValue$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public class PropertyValue$Class
implements
	PropertyValue,
    edoc.EdocVisitable
{
	/** Default constructor */
	public PropertyValue$Class() {
		//--- Set property 'owner' from 'PropertyValue' ---
		this.owner = null;
		//--- Set property 'fills' from 'PropertyValue' ---
		this.fills = null;
	}


	/** Property 'owner' from 'PropertyValue' */
	protected ComponentUsage owner;
	/** Get property 'owner' from 'PropertyValue' */
	public ComponentUsage getOwner() {
		return owner;
	}
	/** Set property 'owner' from 'PropertyValue' */
	public void setOwner(ComponentUsage owner) { 
		this.owner = owner;
	}

	/** Property 'fills' from 'PropertyValue' */
	protected PropertyDefinition fills;
	/** Get property 'fills' from 'PropertyValue' */
	public PropertyDefinition getFills() {
		return fills;
	}
	/** Set property 'fills' from 'PropertyValue' */
	public void setFills(PropertyDefinition fills) { 
		this.fills = fills;
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
		String strId = "edoc.ECA.CCA.PropertyValue";
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
			this.owner.getConfiguration().remove(this);
		if (fills != null)
			this.fills.getFilledBy().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		PropertyValue$Class obj = new PropertyValue$Class();
		obj.owner = owner==null ? null : this.owner;
		obj.fills = fills==null ? null : this.fills;
		return obj;
	}

	/** Accept 'edoc.ECA.CCA.PropertyValueVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
