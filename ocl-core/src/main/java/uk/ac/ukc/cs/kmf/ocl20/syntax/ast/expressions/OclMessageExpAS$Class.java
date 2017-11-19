/**
 *
 *  Class OclMessageExpAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public class OclMessageExpAS$Class
extends
	OclExpressionAS$Class
implements
	OclMessageExpAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public OclMessageExpAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'name' from 'OclMessageExpAS' ---
		this.name = new String();
		//--- Set property 'kind' from 'OclMessageExpAS' ---
		this.kind = null;
		//--- Set property 'target' from 'OclMessageExpAS' ---
		this.target = null;
		//--- Set property 'arguments' from 'OclMessageExpAS' ---
		this.arguments = new java.util.Vector();
	}
	/** Specialized constructor */
	public OclMessageExpAS$Class(Boolean isMarkedPre, String name, OclMessageKindAS kind) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'name' from 'OclMessageExpAS' ---
		this.name = name;
		//--- Set property 'kind' from 'OclMessageExpAS' ---
		this.kind = kind;
		//--- Set property 'target' from 'OclMessageExpAS' ---
		this.target = null;
		//--- Set property 'arguments' from 'OclMessageExpAS' ---
		this.arguments = new java.util.Vector();
	}


	/** Property 'name' from 'OclMessageExpAS' */
	protected String name;
	/** Get property 'name' from 'OclMessageExpAS' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'OclMessageExpAS' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'kind' from 'OclMessageExpAS' */
	protected OclMessageKindAS kind;
	/** Get property 'kind' from 'OclMessageExpAS' */
		public OclMessageKindAS getKind() {
		return kind;
	}
	/** Set property 'kind' from 'OclMessageExpAS' */
		public void setKind(OclMessageKindAS kind) {
		this.kind = kind;
	}

	/** Property 'target' from 'OclMessageExpAS' */
	protected OclExpressionAS target;
	/** Get property 'target' from 'OclMessageExpAS' */
	public OclExpressionAS getTarget() {
		return target;
	}
	/** Set property 'target' from 'OclMessageExpAS' */
	public void setTarget(OclExpressionAS target) { 
		this.target = target;
	}

	/** Property 'arguments' from 'OclMessageExpAS' */
	protected java.util.List arguments;
	/** Get property 'arguments' from 'OclMessageExpAS' */
	public java.util.List getArguments() {
		return arguments;
	}
	/** Set property 'arguments' from 'OclMessageExpAS' */
	public void setArguments(java.util.List arguments) { 
		this.arguments = arguments;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.expressions.OclMessageExpAS";
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
		OclMessageExpAS$Class obj = new OclMessageExpAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.parent = parent==null ? null : this.parent;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		obj.name = name==null ? null : this.name;
		obj.kind = kind==null ? null : (OclMessageKindAS)this.kind.clone();
		obj.target = target==null ? null : this.target;
		obj.arguments = arguments==null ? null : (java.util.List)((java.util.Vector)this.arguments).clone();
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.OclMessageExpASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}