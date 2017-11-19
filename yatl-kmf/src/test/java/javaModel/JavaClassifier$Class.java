/**
 *
 *  Class JavaClassifier$Class.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel;

public class JavaClassifier$Class
extends
	JavaPackageElement$Class
implements
	JavaClassifier,
    javaModel.JavaModelVisitable
{
	/** Default constructor */
	public JavaClassifier$Class() {
		//--- Set property 'name' from 'JavaElement' ---
		this.name = null;
		//--- Set property 'javaPackage' from 'JavaPackageElement' ---
		this.javaPackage = null;
		//--- Set property 'sub' from 'JavaClassifier' ---
		this.sub = null;
		//--- Set property 'super_' from 'JavaClassifier' ---
		this.super_ = null;
	}
	/** Specialized constructor */
	public JavaClassifier$Class(String name) {
		//--- Set property 'name' from 'JavaElement' ---
		this.name = name;
		//--- Set property 'javaPackage' from 'JavaPackageElement' ---
		this.javaPackage = null;
		//--- Set property 'sub' from 'JavaClassifier' ---
		this.sub = null;
		//--- Set property 'super_' from 'JavaClassifier' ---
		this.super_ = null;
	}


	/** Property 'sub' from 'JavaClassifier' */
	protected JavaClassifier sub;
	/** Get property 'sub' from 'JavaClassifier' */
	public JavaClassifier getSub() {
		return sub;
	}
	/** Set property 'sub' from 'JavaClassifier' */
	public void setSub(JavaClassifier sub) { 
		this.sub = sub;
	}

	/** Property 'super_' from 'JavaClassifier' */
	protected JavaClassifier super_;
	/** Get property 'super_' from 'JavaClassifier' */
	public JavaClassifier getSuper_() {
		return super_;
	}
	/** Set property 'super_' from 'JavaClassifier' */
	public void setSuper_(JavaClassifier super_) { 
		this.super_ = super_;
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
		String strId = "javaModel.JavaClassifier";
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
		if (javaPackage != null)
			this.javaPackage.getElements().remove(this);
		if (sub != null)
			this.sub.setSuper_(null);
		if (super_ != null)
			this.super_.setSub(null);
	}

	/** Clone the object */
	public Object clone() {
		JavaClassifier$Class obj = new JavaClassifier$Class();
		obj.name = name==null ? null : this.name;
		obj.javaPackage = javaPackage==null ? null : this.javaPackage;
		obj.sub = sub==null ? null : this.sub;
		obj.super_ = super_==null ? null : this.super_;
		return obj;
	}

	/** Accept 'javaModel.JavaClassifierVisitor' */
	public Object accept(javaModel.JavaModelVisitor v, Object data) {
		return v.visit(this, data);
	}
}
