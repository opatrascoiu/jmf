/**
 *
 *  Class CollectionKindAS$Class.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions;

public class CollectionKindAS$Class
implements
    CollectionKindAS,
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxVisitable
{
	/** The 'SET' enumerator */
	public static final CollectionKindAS SET = new CollectionKindAS$Class();
	/** The 'BAG' enumerator */
	public static final CollectionKindAS BAG = new CollectionKindAS$Class();
	/** The 'SEQUENCE' enumerator */
	public static final CollectionKindAS SEQUENCE = new CollectionKindAS$Class();
	/** The 'COLLECTION' enumerator */
	public static final CollectionKindAS COLLECTION = new CollectionKindAS$Class();
	/** The 'ORDERED_SET' enumerator */
	public static final CollectionKindAS ORDERED_SET = new CollectionKindAS$Class();

	/** Default constructors */
	public CollectionKindAS$Class() {
	}

	/** Overrride toString */
	public String toString() {
		String res = "CollectionKindAS";
		if (this == SET) res += "::SET";
		if (this == BAG) res += "::BAG";
		if (this == SEQUENCE) res += "::SEQUENCE";
		if (this == COLLECTION) res += "::COLLECTION";
		if (this == ORDERED_SET) res += "::ORDERED_SET";
		return res;
	}
	/** Clone the object */
	public Object clone() {
		CollectionKindAS$Class obj = new CollectionKindAS$Class();
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
