/**
 *
 *  Class GranularityKind$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public class GranularityKind$Class
implements
    GranularityKind,
    edoc.EdocVisitable
{
	/** The 'program' enumerator */
	public static final GranularityKind program = new GranularityKind$Class();
	/** The 'owned' enumerator */
	public static final GranularityKind owned = new GranularityKind$Class();
	/** The 'shared' enumerator */
	public static final GranularityKind shared = new GranularityKind$Class();

	/** Default constructors */
	public GranularityKind$Class() {
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
		String res = "GranularityKind";
		if (this == program) res += "::program";
		if (this == owned) res += "::owned";
		if (this == shared) res += "::shared";
		return res;
	}
	/** Clone the object */
	public Object clone() {
		GranularityKind$Class obj = new GranularityKind$Class();
		return obj;
	}
	/** Delete the object */
	public void delete() {
	}

	/** Accept the visitor */
	public Object accept(edoc.EdocVisitor v, Object obj) {
		return v.visit(this, obj);
	}
}
