/**
 *
 *  Class SetTypeAS$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.types;

public class SetTypeAS$Class
extends
	CollectionTypeAS$Class
implements
	SetTypeAS,
    ocl.syntax.SyntaxVisitable
{
	/** Default constructor */
	public SetTypeAS$Class() {
		//--- Set property 'propertyContextDeclAS' from 'TypeAS' ---
		this.propertyContextDeclAS = null;
		//--- Set property 'variableDeclarationAS' from 'TypeAS' ---
		this.variableDeclarationAS = null;
		//--- Set property 'operationAS' from 'TypeAS' ---
		this.operationAS = null;
		//--- Set property 'oclMessageArgAS' from 'TypeAS' ---
		this.oclMessageArgAS = null;
		//--- Set property 'collectionTypeAS' from 'TypeAS' ---
		this.collectionTypeAS = null;
		//--- Set property 'elementType' from 'CollectionTypeAS' ---
		this.elementType = null;
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
			return strId+" 'id-"+getId()+"'";
		else
			return strId+" '"+name+"-"+getId()+"'";
	}

	/** Delete the object */
	public void delete() {
		if (propertyContextDeclAS != null)
			this.propertyContextDeclAS.setType(null);
		if (variableDeclarationAS != null)
			this.variableDeclarationAS.setType(null);
		if (operationAS != null)
			this.operationAS.setType(null);
		if (oclMessageArgAS != null)
			this.oclMessageArgAS.setType(null);
		if (collectionTypeAS != null)
			this.collectionTypeAS.setElementType(null);
		if (elementType != null)
			this.elementType.setCollectionTypeAS(null);
	}

	/** Clone the object */
	public Object clone() {
		SetTypeAS$Class obj = new SetTypeAS$Class();
		obj.propertyContextDeclAS = propertyContextDeclAS==null ? null : this.propertyContextDeclAS;
		obj.variableDeclarationAS = variableDeclarationAS==null ? null : this.variableDeclarationAS;
		obj.operationAS = operationAS==null ? null : this.operationAS;
		obj.oclMessageArgAS = oclMessageArgAS==null ? null : this.oclMessageArgAS;
		obj.collectionTypeAS = collectionTypeAS==null ? null : this.collectionTypeAS;
		obj.elementType = elementType==null ? null : this.elementType;
		return obj;
	}

	/** Accept 'ocl.syntax.ast.types.SetTypeASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
