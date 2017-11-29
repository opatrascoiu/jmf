/**
 *
 *  Class PresentationElement$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:46
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

public class PresentationElement$Class
extends
	Element$Class
implements
	PresentationElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitable
{
	/** Default constructor */
	public PresentationElement$Class() {
		//--- Set property 'subject' from 'PresentationElement' ---
		this.subject = new java.util.LinkedHashSet();
	}


	/** Property 'subject' from 'PresentationElement' */
	protected java.util.Set subject;
	/** Get property 'subject' from 'PresentationElement' */
	public java.util.Set getSubject() {
		return subject;
	}
	/** Set property 'subject' from 'PresentationElement' */
	public void setSubject(java.util.Set subject) { 
		this.subject = subject;
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
		String strId = "uml.Foundation.Core.PresentationElement";
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
		java.util.Iterator subjectIt = this.subject.iterator();
		while (subjectIt.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement subjectObj = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement)subjectIt.next();
			subjectObj.getPresentation().remove(this);
			subjectObj.getPresentation().remove(this);
		}
	}

	/** Clone the object */
	public Object clone() {
		PresentationElement$Class obj = new PresentationElement$Class();
		obj.subject = subject==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.subject).clone();
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.PresentationElementVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}