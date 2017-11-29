/**
 *
 *  Class ObjectSetExpression$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

public class ObjectSetExpression$Class
extends
	Expression$Class
implements
	ObjectSetExpression,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitable
{
	/** Default constructor */
	public ObjectSetExpression$Class() {
		//--- Set property 'language' from 'Expression' ---
		this.language = null;
		//--- Set property 'body' from 'Expression' ---
		this.body = new String();
	}
	/** Specialized constructor */
	public ObjectSetExpression$Class(Name language, String body) {
		//--- Set property 'language' from 'Expression' ---
		this.language = language;
		//--- Set property 'body' from 'Expression' ---
		this.body = body;
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
		String strId = "uml.Foundation.Data_Types.ObjectSetExpression";
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
	}

	/** Clone the object */
	public Object clone() {
		ObjectSetExpression$Class obj = new ObjectSetExpression$Class();
		obj.language = language==null ? null : (Name)this.language.clone();
		obj.body = body==null ? null : this.body;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ObjectSetExpressionVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
