/**
 *
 *  Class MultiplicityRangeFactory$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

public class MultiplicityRangeFactory$Class
	extends uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class
	implements MultiplicityRangeFactory
{
	/** Default factory constructor */
	public MultiplicityRangeFactory$Class() {
	}
	public MultiplicityRangeFactory$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		MultiplicityRange obj = new MultiplicityRange$Class();
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Data_Types.MultiplicityRange", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Integer lower, Integer upper) {
		MultiplicityRange obj = new MultiplicityRange$Class(lower, upper);
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Data_Types.MultiplicityRange", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "MultiplicityRangeFactory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.MultiplicityRangeVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
