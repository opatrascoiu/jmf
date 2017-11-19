/**
 *
 *  Class ClassifierAS$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.types;

public class ClassifierAS$Class
implements
	ClassifierAS,
    ocl.syntax.SyntaxVisitable
{
	/** Default constructor */
	public ClassifierAS$Class() {
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
		//--- Set property 'pathName' from 'ClassifierAS' ---
		this.pathName = new java.util.Vector();
	}
	/** Specialized constructor */
	public ClassifierAS$Class(java.util.List pathName) {
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
		//--- Set property 'pathName' from 'ClassifierAS' ---
		this.pathName = pathName;
	}


	/** Property 'propertyContextDeclAS' from 'TypeAS' */
	protected ocl.syntax.ast.contexts.PropertyContextDeclAS propertyContextDeclAS;
	/** Get property 'propertyContextDeclAS' from 'TypeAS' */
	public ocl.syntax.ast.contexts.PropertyContextDeclAS getPropertyContextDeclAS() {
		return propertyContextDeclAS;
	}
	/** Set property 'propertyContextDeclAS' from 'TypeAS' */
	public void setPropertyContextDeclAS(ocl.syntax.ast.contexts.PropertyContextDeclAS propertyContextDeclAS) { 
		this.propertyContextDeclAS = propertyContextDeclAS;
	}

	/** Property 'variableDeclarationAS' from 'TypeAS' */
	protected ocl.syntax.ast.contexts.VariableDeclarationAS variableDeclarationAS;
	/** Get property 'variableDeclarationAS' from 'TypeAS' */
	public ocl.syntax.ast.contexts.VariableDeclarationAS getVariableDeclarationAS() {
		return variableDeclarationAS;
	}
	/** Set property 'variableDeclarationAS' from 'TypeAS' */
	public void setVariableDeclarationAS(ocl.syntax.ast.contexts.VariableDeclarationAS variableDeclarationAS) { 
		this.variableDeclarationAS = variableDeclarationAS;
	}

	/** Property 'operationAS' from 'TypeAS' */
	protected ocl.syntax.ast.contexts.OperationAS operationAS;
	/** Get property 'operationAS' from 'TypeAS' */
	public ocl.syntax.ast.contexts.OperationAS getOperationAS() {
		return operationAS;
	}
	/** Set property 'operationAS' from 'TypeAS' */
	public void setOperationAS(ocl.syntax.ast.contexts.OperationAS operationAS) { 
		this.operationAS = operationAS;
	}

	/** Property 'oclMessageArgAS' from 'TypeAS' */
	protected ocl.syntax.ast.expressions.OclMessageArgAS oclMessageArgAS;
	/** Get property 'oclMessageArgAS' from 'TypeAS' */
	public ocl.syntax.ast.expressions.OclMessageArgAS getOclMessageArgAS() {
		return oclMessageArgAS;
	}
	/** Set property 'oclMessageArgAS' from 'TypeAS' */
	public void setOclMessageArgAS(ocl.syntax.ast.expressions.OclMessageArgAS oclMessageArgAS) { 
		this.oclMessageArgAS = oclMessageArgAS;
	}

	/** Property 'collectionTypeAS' from 'TypeAS' */
	protected CollectionTypeAS collectionTypeAS;
	/** Get property 'collectionTypeAS' from 'TypeAS' */
	public CollectionTypeAS getCollectionTypeAS() {
		return collectionTypeAS;
	}
	/** Set property 'collectionTypeAS' from 'TypeAS' */
	public void setCollectionTypeAS(CollectionTypeAS collectionTypeAS) { 
		this.collectionTypeAS = collectionTypeAS;
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
	}

	/** Clone the object */
	public Object clone() {
		ClassifierAS$Class obj = new ClassifierAS$Class();
		obj.propertyContextDeclAS = propertyContextDeclAS==null ? null : this.propertyContextDeclAS;
		obj.variableDeclarationAS = variableDeclarationAS==null ? null : this.variableDeclarationAS;
		obj.operationAS = operationAS==null ? null : this.operationAS;
		obj.oclMessageArgAS = oclMessageArgAS==null ? null : this.oclMessageArgAS;
		obj.collectionTypeAS = collectionTypeAS==null ? null : this.collectionTypeAS;
		obj.pathName = pathName==null ? null : (java.util.List)((java.util.Vector)this.pathName).clone();
		return obj;
	}

	/** Accept 'ocl.syntax.ast.types.ClassifierASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
