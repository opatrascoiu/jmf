/**
 *
 *  Class IteratorExpAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public class IteratorExpAS$Class
implements
	IteratorExpAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** Default constructor */
	public IteratorExpAS$Class() {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = new Boolean(false);
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'name' from 'LoopExpAS' ---
		this.name = new String();
		//--- Set property 'body' from 'LoopExpAS' ---
		this.body = null;
		//--- Set property 'iterator' from 'LoopExpAS' ---
		this.iterator = null;
		//--- Set property 'result' from 'LoopExpAS' ---
		this.result = null;
		//--- Set property 'source' from 'LoopExpAS' ---
		this.source = null;
	}
	/** Specialized constructor */
	public IteratorExpAS$Class(Boolean isMarkedPre, String name) {
		//--- Set property 'isMarkedPre' from 'OclExpressionAS' ---
		this.isMarkedPre = isMarkedPre;
		//--- Set property 'parent' from 'OclExpressionAS' ---
		this.parent = null;
		//--- Set property 'ifExpAS' from 'OclExpressionAS' ---
		this.ifExpAS = null;
		//--- Set property 'name' from 'LoopExpAS' ---
		this.name = name;
		//--- Set property 'body' from 'LoopExpAS' ---
		this.body = null;
		//--- Set property 'iterator' from 'LoopExpAS' ---
		this.iterator = null;
		//--- Set property 'result' from 'LoopExpAS' ---
		this.result = null;
		//--- Set property 'source' from 'LoopExpAS' ---
		this.source = null;
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

	/** Property 'name' from 'LoopExpAS' */
	protected String name;
	/** Get property 'name' from 'LoopExpAS' */
		public String getName() {
		return name;
	}
	/** Set property 'name' from 'LoopExpAS' */
		public void setName(String name) {
		this.name = name;
	}

	/** Property 'body' from 'LoopExpAS' */
	protected OclExpressionAS body;
	/** Get property 'body' from 'LoopExpAS' */
	public OclExpressionAS getBody() {
		return body;
	}
	/** Set property 'body' from 'LoopExpAS' */
	public void setBody(OclExpressionAS body) { 
		this.body = body;
	}

	/** Property 'iterator' from 'LoopExpAS' */
	protected uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts.VariableDeclarationAS iterator;
	/** Get property 'iterator' from 'LoopExpAS' */
	public uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts.VariableDeclarationAS getIterator() {
		return iterator;
	}
	/** Set property 'iterator' from 'LoopExpAS' */
	public void setIterator(uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts.VariableDeclarationAS iterator) { 
		this.iterator = iterator;
	}

	/** Property 'result' from 'LoopExpAS' */
	protected uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts.VariableDeclarationAS result;
	/** Get property 'result' from 'LoopExpAS' */
	public uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts.VariableDeclarationAS getResult() {
		return result;
	}
	/** Set property 'result' from 'LoopExpAS' */
	public void setResult(uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts.VariableDeclarationAS result) { 
		this.result = result;
	}

	/** Property 'source' from 'LoopExpAS' */
	protected OclExpressionAS source;
	/** Get property 'source' from 'LoopExpAS' */
	public OclExpressionAS getSource() {
		return source;
	}
	/** Set property 'source' from 'LoopExpAS' */
	public void setSource(OclExpressionAS source) { 
		this.source = source;
	}

	/** Override toString */
	public String toString() {
		String strId = "syntax.ast.expressions.IteratorExpAS";
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
		IteratorExpAS$Class obj = new IteratorExpAS$Class();
		obj.isMarkedPre = isMarkedPre==null ? null : this.isMarkedPre;
		obj.parent = parent==null ? null : this.parent;
		obj.ifExpAS = ifExpAS==null ? null : this.ifExpAS;
		obj.name = name==null ? null : this.name;
		obj.body = body==null ? null : this.body;
		obj.iterator = iterator==null ? null : this.iterator;
		obj.result = result==null ? null : this.result;
		obj.source = source==null ? null : this.source;
		return obj;
	}

	/** Accept 'uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.IteratorExpASVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
