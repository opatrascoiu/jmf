/**
 *
 *  Class Flow$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public class Flow$Class
extends
	Connection$Class
implements
	Flow,
    edoc.EdocVisitable
{
	/** Default constructor */
	public Flow$Class() {
		//--- Set property 'owner' from 'AbstractTransition' ---
		this.owner = null;
		//--- Set property 'target' from 'AbstractTransition' ---
		this.target = null;
		//--- Set property 'source' from 'AbstractTransition' ---
		this.source = null;
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
		String strId = "edoc.ECA.CCA.Flow";
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
			this.owner.getConnections().remove(this);
		if (target != null)
			this.target.getIncoming().remove(this);
		if (source != null)
			this.source.getOutgoing().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		Flow$Class obj = new Flow$Class();
		obj.owner = owner==null ? null : this.owner;
		obj.target = target==null ? null : this.target;
		obj.source = source==null ? null : this.source;
		return obj;
	}

	/** Accept 'edoc.ECA.CCA.FlowVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}