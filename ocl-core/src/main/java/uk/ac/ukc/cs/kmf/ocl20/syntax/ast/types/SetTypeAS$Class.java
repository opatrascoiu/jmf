/**
 *
 *  Class SetTypeAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types;

public class SetTypeAS$Class
extends
	CollectionTypeAS$Class
implements
	SetTypeAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public SetTypeAS$Class() {
		//--- Set property 'elementType' from 'CollectionTypeAS' ---
		this.elementType = null;
	}


	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.types.SetTypeAS";
		String name = null;
		try {
			java.lang.Class cls = this.getClass();
			java.lang.reflect.Method method = cls.getMethod("getName", new java.lang.Class[] {});
			name = (String) method.invoke(this, new Object[] {});
			if (name != null && name.length()==0) name = null;
		} catch (Exception e) {
		}
		if (name == null)
			return strId;
		else
			return strId+" '"+name+"'";
	}

	/** Delete the object */

	/** Clone the object */
	public Object clone() {
		SetTypeAS$Class obj = new SetTypeAS$Class();
		obj.elementType = elementType==null ? null : this.elementType;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.SetTypeASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
