/**
 *
 *  Class MultiplicityFactory$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

public class MultiplicityFactory$Class
	extends uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class
	implements MultiplicityFactory
{
	/** Default factory constructor */
	public MultiplicityFactory$Class() {
	}
	public MultiplicityFactory$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Multiplicity obj = new Multiplicity$Class();
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Data_Types.Multiplicity", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "MultiplicityFactory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.MultiplicityVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
