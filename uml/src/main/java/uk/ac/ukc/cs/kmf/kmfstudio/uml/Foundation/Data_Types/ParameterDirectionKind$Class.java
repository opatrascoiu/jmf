/**
 *
 *  Class ParameterDirectionKind$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

public class ParameterDirectionKind$Class
implements
    ParameterDirectionKind,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitable
{
	/** The 'IN' enumerator */
	public static final ParameterDirectionKind IN = new ParameterDirectionKind$Class();
	/** The 'OUT' enumerator */
	public static final ParameterDirectionKind OUT = new ParameterDirectionKind$Class();
	/** The 'INOUT' enumerator */
	public static final ParameterDirectionKind INOUT = new ParameterDirectionKind$Class();
	/** The 'RETURN' enumerator */
	public static final ParameterDirectionKind RETURN = new ParameterDirectionKind$Class();

	/** Default constructors */
	public ParameterDirectionKind$Class() {
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
		String res = "ParameterDirectionKind";
		if (this == IN) res += "::IN";
		if (this == OUT) res += "::OUT";
		if (this == INOUT) res += "::INOUT";
		if (this == RETURN) res += "::RETURN";
		return res;
	}
	/** Clone the object */
	public Object clone() {
		ParameterDirectionKind$Class obj = new ParameterDirectionKind$Class();
		return obj;
	}
	/** Delete the object */
	public void delete() {
	}

	/** Accept the visitor */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object obj) {
		return v.visit(this, obj);
	}
}
