/**
 *
 *  Class ConstraintKindAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts;

public class ConstraintKindAS$Class
implements
    ConstraintKindAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** The 'INIT' enumerator */
	public static final ConstraintKindAS INIT = new ConstraintKindAS$Class();
	/** The 'DERIVE' enumerator */
	public static final ConstraintKindAS DERIVE = new ConstraintKindAS$Class();
	/** The 'INV' enumerator */
	public static final ConstraintKindAS INV = new ConstraintKindAS$Class();
	/** The 'DEF' enumerator */
	public static final ConstraintKindAS DEF = new ConstraintKindAS$Class();
	/** The 'PRE' enumerator */
	public static final ConstraintKindAS PRE = new ConstraintKindAS$Class();
	/** The 'POST' enumerator */
	public static final ConstraintKindAS POST = new ConstraintKindAS$Class();
	/** The 'BODY' enumerator */
	public static final ConstraintKindAS BODY = new ConstraintKindAS$Class();

	/** Default constructors */
	public ConstraintKindAS$Class() {
	}

	/** Overrride toString */
	public String toString() {
		String res = "ConstraintKindAS";
		if (this == INIT) res += "::INIT";
		if (this == DERIVE) res += "::DERIVE";
		if (this == INV) res += "::INV";
		if (this == DEF) res += "::DEF";
		if (this == PRE) res += "::PRE";
		if (this == POST) res += "::POST";
		if (this == BODY) res += "::BODY";
		return res;
	}
	/** Clone the object */
	public Object clone() {
		ConstraintKindAS$Class obj = new ConstraintKindAS$Class();
		return obj;
	}
	/** Delete the object */
	public void delete() {
	}

	/** Accept the visitor */
	public Object accept(uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitor v, Object obj) {
		return v.visit(this, obj);
	}
}
