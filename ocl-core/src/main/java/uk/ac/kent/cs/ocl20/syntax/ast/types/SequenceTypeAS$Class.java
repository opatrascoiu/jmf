/**
 *
 *  Class SequenceTypeAS$Class.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.types;

import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitable;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor;

public class SequenceTypeAS$Class
extends
	CollectionTypeAS$Class
implements
	SequenceTypeAS,
    SyntaxVisitable
{
	/** Default constructor */
	public SequenceTypeAS$Class() {
		//--- Set property 'elementType' from 'CollectionTypeAS' ---
		this.elementType = null;
	}


	/** Override toString */
	public String toString() {
		String strId = "ast.types.SequenceTypeAS";
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

	/** Accept 'uk.ac.kent.cs.ocl20.syntax.ast.types.SequenceTypeAS$Visitor' */
	public Object accept(SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}

	/** Clone the object */
	public Object clone() {
		SequenceTypeAS$Class obj = new SequenceTypeAS$Class();
		obj.elementType = elementType==null ? null : this.elementType;
		return obj;
	}
}
