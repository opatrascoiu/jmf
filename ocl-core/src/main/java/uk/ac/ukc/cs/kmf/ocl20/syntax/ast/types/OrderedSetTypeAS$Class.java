/**
 *
 *  Class OrderedSetTypeAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types;

public class OrderedSetTypeAS$Class
extends
	CollectionTypeAS$Class
implements
	OrderedSetTypeAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public OrderedSetTypeAS$Class() {
		//--- Set property 'elementType' from 'CollectionTypeAS' ---
		this.elementType = null;
	}


	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.types.OrderedSetTypeAS";
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
		OrderedSetTypeAS$Class obj = new OrderedSetTypeAS$Class();
		obj.elementType = elementType==null ? null : this.elementType;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.OrderedSetTypeASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
