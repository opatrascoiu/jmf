/**
 *
 *  Class Part$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:39
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws.bpel;

public class Part$Class
implements
	Part,
    ws.WsVisitable
{
	/** Default constructor */
	public Part$Class() {
		//--- Set property 'variableSpec' from 'Part' ---
		this.variableSpec = null;
	}


	/** Property 'variableSpec' from 'Part' */
	protected VariableSpec variableSpec;
	/** Get property 'variableSpec' from 'Part' */
	public VariableSpec getVariableSpec() {
		return variableSpec;
	}
	/** Set property 'variableSpec' from 'Part' */
	public void setVariableSpec(VariableSpec variableSpec) { 
		this.variableSpec = variableSpec;
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
		String strId = "ws.bpel.Part";
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
		if (variableSpec != null)
			this.variableSpec.setPart(null);
	}

	/** Clone the object */
	public Object clone() {
		Part$Class obj = new Part$Class();
		obj.variableSpec = variableSpec==null ? null : this.variableSpec;
		return obj;
	}

	/** Accept 'ws.bpel.PartVisitor' */
	public Object accept(ws.WsVisitor v, Object data) {
		return v.visit(this, data);
	}
}