/**
 *
 *  Class DataInvariant$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:35
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.DocumentModel;

public class DataInvariant$Class
implements
	DataInvariant,
    edoc.EdocVisitable
{
	/** Default constructor */
	public DataInvariant$Class() {
		//--- Set property 'expression' from 'DataInvariant' ---
		this.expression = null;
		//--- Set property 'onCommit' from 'DataInvariant' ---
		this.onCommit = null;
		//--- Set property 'constrainedElement' from 'DataInvariant' ---
		this.constrainedElement = null;
	}
	/** Specialized constructor */
	public DataInvariant$Class(String expression, Boolean onCommit) {
		//--- Set property 'expression' from 'DataInvariant' ---
		this.expression = expression;
		//--- Set property 'onCommit' from 'DataInvariant' ---
		this.onCommit = onCommit;
		//--- Set property 'constrainedElement' from 'DataInvariant' ---
		this.constrainedElement = null;
	}


	/** Property 'expression' from 'DataInvariant' */
	protected String expression;
	/** Get property 'expression' from 'DataInvariant' */
		public String getExpression() {
		return expression;
	}
	/** Set property 'expression' from 'DataInvariant' */
		public void setExpression(String expression) {
		this.expression = expression;
	}

	/** Property 'onCommit' from 'DataInvariant' */
	protected Boolean onCommit;
	/** Get property 'onCommit' from 'DataInvariant' */
		public Boolean getOnCommit() {
		return onCommit;
	}
	/** Set property 'onCommit' from 'DataInvariant' */
		public void setOnCommit(Boolean onCommit) {
		this.onCommit = onCommit;
	}

	/** Property 'constrainedElement' from 'DataInvariant' */
	protected DataElement constrainedElement;
	/** Get property 'constrainedElement' from 'DataInvariant' */
	public DataElement getConstrainedElement() {
		return constrainedElement;
	}
	/** Set property 'constrainedElement' from 'DataInvariant' */
	public void setConstrainedElement(DataElement constrainedElement) { 
		this.constrainedElement = constrainedElement;
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
		String strId = "edoc.ECA.DocumentModel.DataInvariant";
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
		if (constrainedElement != null)
			this.constrainedElement.getConstraints().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		DataInvariant$Class obj = new DataInvariant$Class();
		obj.expression = expression==null ? null : this.expression;
		obj.onCommit = onCommit==null ? null : this.onCommit;
		obj.constrainedElement = constrainedElement==null ? null : this.constrainedElement;
		return obj;
	}

	/** Accept 'edoc.ECA.DocumentModel.DataInvariantVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}