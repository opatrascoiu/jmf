/**
 *
 *  Class OclMessageKindAS$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class OclMessageKindAS$Class
implements
    OclMessageKindAS,
    ocl.syntax.SyntaxVisitable
{
	/** The 'UP' enumerator */
	public static final OclMessageKindAS UP = new OclMessageKindAS$Class();
	/** The 'UP_UP' enumerator */
	public static final OclMessageKindAS UP_UP = new OclMessageKindAS$Class();

	/** Default constructors */
	public OclMessageKindAS$Class() {
	}

	/** The id */
	protected String id;
	/** Get the id */
	public String getId() {
		return id;
	}
	/** Set the id */
	public void setId(String id) {
		this.id = "1";
	}

	/** Overrride toString */
	public String toString() {
		String res = "OclMessageKindAS";
		if (this == UP) res += "::UP";
		if (this == UP_UP) res += "::UP_UP";
		return res;
	}
	/** Clone the object */
	public Object clone() {
		OclMessageKindAS$Class obj = new OclMessageKindAS$Class();
		return obj;
	}
	/** Delete the object */
	public void delete() {
	}

	/** Accept the visitor */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object obj) {
		return v.visit(this, obj);
	}
}
