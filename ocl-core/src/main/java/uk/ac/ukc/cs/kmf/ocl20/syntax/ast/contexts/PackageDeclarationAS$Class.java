/**
 *
 *  Class PackageDeclarationAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts;

public class PackageDeclarationAS$Class
implements
	PackageDeclarationAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public PackageDeclarationAS$Class() {
		//--- Set property 'pathName' from 'PackageDeclarationAS' ---
		this.pathName = new java.util.Vector();
		//--- Set property 'contextDecls' from 'PackageDeclarationAS' ---
		this.contextDecls = new java.util.Vector();
	}
	/** Specialized constructor */
	public PackageDeclarationAS$Class(java.util.List pathName) {
		//--- Set property 'pathName' from 'PackageDeclarationAS' ---
		this.pathName = pathName;
		//--- Set property 'contextDecls' from 'PackageDeclarationAS' ---
		this.contextDecls = new java.util.Vector();
	}


	/** Property 'pathName' from 'PackageDeclarationAS' */
	protected java.util.List pathName;
	/** Get property 'pathName' from 'PackageDeclarationAS' */
		public java.util.List getPathName() {
		return pathName;
	}
	/** Set property 'pathName' from 'PackageDeclarationAS' */
		public void setPathName(java.util.List pathName) {
		this.pathName = pathName;
	}

	/** Property 'contextDecls' from 'PackageDeclarationAS' */
	protected java.util.List contextDecls;
	/** Get property 'contextDecls' from 'PackageDeclarationAS' */
	public java.util.List getContextDecls() {
		return contextDecls;
	}
	/** Set property 'contextDecls' from 'PackageDeclarationAS' */
	public void setContextDecls(java.util.List contextDecls) { 
		this.contextDecls = contextDecls;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.contexts.PackageDeclarationAS";
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
		PackageDeclarationAS$Class obj = new PackageDeclarationAS$Class();
		obj.pathName = pathName==null ? null : (java.util.List)((java.util.Vector)this.pathName).clone();
		obj.contextDecls = contextDecls==null ? null : (java.util.List)((java.util.Vector)this.contextDecls).clone();
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts.PackageDeclarationASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
