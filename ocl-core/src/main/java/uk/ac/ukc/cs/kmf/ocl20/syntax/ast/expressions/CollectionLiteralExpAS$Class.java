/**
 *
 *  Class CollectionLiteralExpAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public class CollectionLiteralExpAS$Class
implements
	CollectionLiteralExpAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public CollectionLiteralExpAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'kind' from 'CollectionLiteralExpAS' ---
		this.kind = null;
		//--- Set property 'collectionParts' from 'CollectionLiteralExpAS' ---
		this.collectionParts = new java.util.Vector();
	}
	/** Specialized constructor */
	public CollectionLiteralExpAS$Class(Boolean isMarkedPre, CollectionKindAS kind) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'kind' from 'CollectionLiteralExpAS' ---
		this.kind = kind;
		//--- Set property 'collectionParts' from 'CollectionLiteralExpAS' ---
		this.collectionParts = new java.util.Vector();
	}


	/** Property 'isMarkedPre' from 'OclExpressionAS' */
	protected Boolean isMarkedPre;
	/** Get property 'isMarkedPre' from 'OclExpressionAS' */
		public Boolean getIsMarkedPre() {
		return isMarkedPre;
	}
	/** Set property 'isMarkedPre' from 'OclExpressionAS' */
		public void setIsMarkedPre(Boolean isMarkedPre) {
		this.isMarkedPre = isMarkedPre;
	}

	/** Property 'parent' from 'OclExpressionAS' */
	protected OclExpressionAS parent;
	/** Get property 'parent' from 'OclExpressionAS' */
	public OclExpressionAS getParent() {
		return parent;
	}
	/** Set property 'parent' from 'OclExpressionAS' */
	public void setParent(OclExpressionAS parent) { 
		this.parent = parent;
	}

	/** Property 'ifExpAS' from 'OclExpressionAS' */
	protected IfExpAS ifExpAS;
	/** Get property 'ifExpAS' from 'OclExpressionAS' */
	public IfExpAS getIfExpAS() {
		return ifExpAS;
	}
	/** Set property 'ifExpAS' from 'OclExpressionAS' */
	public void setIfExpAS(IfExpAS ifExpAS) { 
		this.ifExpAS = ifExpAS;
	}

	/** Property 'kind' from 'CollectionLiteralExpAS' */
	protected CollectionKindAS kind;
	/** Get property 'kind' from 'CollectionLiteralExpAS' */
		public CollectionKindAS getKind() {
		return kind;
	}
	/** Set property 'kind' from 'CollectionLiteralExpAS' */
		public void setKind(CollectionKindAS kind) {
		this.kind = kind;
	}

	/** Property 'collectionParts' from 'CollectionLiteralExpAS' */
	protected java.util.List collectionParts;
	/** Get property 'collectionParts' from 'CollectionLiteralExpAS' */
	public java.util.List getCollectionParts() {
		return collectionParts;
	}
	/** Set property 'collectionParts' from 'CollectionLiteralExpAS' */
	public void setCollectionParts(java.util.List collectionParts) { 
		this.collectionParts = collectionParts;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.expressions.CollectionLiteralExpAS";
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
		CollectionLiteralExpAS$Class obj = new CollectionLiteralExpAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.parent = parent==null ? null : this.parent;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		obj.kind = kind==null ? null : (CollectionKindAS)this.kind.clone();
		obj.collectionParts = collectionParts==null ? null : (java.util.List)((java.util.Vector)this.collectionParts).clone();
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.CollectionLiteralExpASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
