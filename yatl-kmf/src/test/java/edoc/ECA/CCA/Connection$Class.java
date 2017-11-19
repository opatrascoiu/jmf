/**
 *
 *  Class Connection$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public class Connection$Class
implements
	Connection,
    edoc.EdocVisitable
{
	/** Default constructor */
	public Connection$Class() {
		//--- Set property 'owner' from 'AbstractTransition' ---
		this.owner = null;
		//--- Set property 'target' from 'AbstractTransition' ---
		this.target = null;
		//--- Set property 'source' from 'AbstractTransition' ---
		this.source = null;
	}


	/** Property 'owner' from 'AbstractTransition' */
	protected Choreography owner;
	/** Get property 'owner' from 'AbstractTransition' */
	public Choreography getOwner() {
		return owner;
	}
	/** Set property 'owner' from 'AbstractTransition' */
	public void setOwner(Choreography owner) { 
		this.owner = owner;
	}

	/** Property 'target' from 'AbstractTransition' */
	protected Node target;
	/** Get property 'target' from 'AbstractTransition' */
	public Node getTarget() {
		return target;
	}
	/** Set property 'target' from 'AbstractTransition' */
	public void setTarget(Node target) { 
		this.target = target;
	}

	/** Property 'source' from 'AbstractTransition' */
	protected Node source;
	/** Get property 'source' from 'AbstractTransition' */
	public Node getSource() {
		return source;
	}
	/** Set property 'source' from 'AbstractTransition' */
	public void setSource(Node source) { 
		this.source = source;
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
		String strId = "edoc.ECA.CCA.Connection";
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
		Connection$Class obj = new Connection$Class();
		obj.owner = owner==null ? null : this.owner;
		obj.target = target==null ? null : this.target;
		obj.source = source==null ? null : this.source;
		return obj;
	}

	/** Accept 'edoc.ECA.CCA.ConnectionVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}