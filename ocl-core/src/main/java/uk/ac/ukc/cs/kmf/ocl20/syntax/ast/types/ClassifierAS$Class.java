/**
 *
 *  Class ClassifierAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types;

public class ClassifierAS$Class
implements
	ClassifierAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public ClassifierAS$Class() {
		//--- Set property 'pathName' from 'ClassifierAS' ---
		this.pathName = new java.util.Vector();
	}
	/** Specialized constructor */
	public ClassifierAS$Class(java.util.List pathName) {
		//--- Set property 'pathName' from 'ClassifierAS' ---
		this.pathName = pathName;
	}


	/** Property 'pathName' from 'ClassifierAS' */
	protected java.util.List pathName;
	/** Get property 'pathName' from 'ClassifierAS' */
		public java.util.List getPathName() {
		return pathName;
	}
	/** Set property 'pathName' from 'ClassifierAS' */
		public void setPathName(java.util.List pathName) {
		this.pathName = pathName;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.types.ClassifierAS";
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
		ClassifierAS$Class obj = new ClassifierAS$Class();
		obj.pathName = pathName==null ? null : (java.util.List)((java.util.Vector)this.pathName).clone();
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.ClassifierASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
