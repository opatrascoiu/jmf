/**
 *
 *  Class ElementImport$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:35
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.ModelManagement;

public class ElementImport$Class
implements
	ElementImport,
    edoc.EdocVisitable
{
	/** Default constructor */
	public ElementImport$Class() {
		//--- Set property 'name' from 'PackageContent' ---
		this.name = null;
		//--- Set property 'namespace_' from 'PackageContent' ---
		this.namespace_ = null;
		//--- Set property 'elementImport' from 'PackageContent' ---
		this.elementImport = new java.util.LinkedHashSet();
		//--- Set property 'modelElement' from 'ElementImport' ---
		this.modelElement = null;
	}
	/** Specialized constructor */
	public ElementImport$Class(String name) {
		//--- Set property 'name' from 'PackageContent' ---
		this.name = name;
		//--- Set property 'namespace_' from 'PackageContent' ---
		this.namespace_ = null;
		//--- Set property 'elementImport' from 'PackageContent' ---
		this.elementImport = new java.util.LinkedHashSet();
		//--- Set property 'modelElement' from 'ElementImport' ---
		this.modelElement = null;
	}


	/** Property 'name' from 'PackageContent' */
	protected String name;
	/** Get property 'name' from 'PackageContent' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'PackageContent' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'namespace_' from 'PackageContent' */
	protected PackageECA namespace_;
	/** Get property 'namespace_' from 'PackageContent' */
	public PackageECA getNamespace_() {
		return namespace_;
	}
	/** Set property 'namespace_' from 'PackageContent' */
	public void setNamespace_(PackageECA namespace_) { 
		this.namespace_ = namespace_;
	}

	/** Property 'elementImport' from 'PackageContent' */
	protected java.util.Set elementImport;
	/** Get property 'elementImport' from 'PackageContent' */
	public java.util.Set getElementImport() {
		return elementImport;
	}
	/** Set property 'elementImport' from 'PackageContent' */
	public void setElementImport(java.util.Set elementImport) { 
		this.elementImport = elementImport;
	}

	/** Property 'modelElement' from 'ElementImport' */
	protected PackageContent modelElement;
	/** Get property 'modelElement' from 'ElementImport' */
	public PackageContent getModelElement() {
		return modelElement;
	}
	/** Set property 'modelElement' from 'ElementImport' */
	public void setModelElement(PackageContent modelElement) { 
		this.modelElement = modelElement;
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
		String strId = "edoc.ECA.ModelManagement.ElementImport";
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
		if (namespace_ != null)
			this.namespace_.getOwnedElement().remove(this);
		java.util.Iterator elementImportIt = this.elementImport.iterator();
		while (elementImportIt.hasNext()) {
			edoc.ECA.ModelManagement.ElementImport elementImportObj = (edoc.ECA.ModelManagement.ElementImport)elementImportIt.next();
			if (elementImportObj != null)
				elementImportObj.setModelElement(null);
		}
		if (modelElement != null)
			this.modelElement.getElementImport().remove(this);
	}

	/** Clone the object */
	public Object clone() {
		ElementImport$Class obj = new ElementImport$Class();
		obj.name = name==null ? null : this.name;
		obj.namespace_ = namespace_==null ? null : this.namespace_;
		obj.elementImport = elementImport==null ? null : (java.util.Set)((java.util.LinkedHashSet)this.elementImport).clone();
		obj.modelElement = modelElement==null ? null : this.modelElement;
		return obj;
	}

	/** Accept 'edoc.ECA.ModelManagement.ElementImportVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
