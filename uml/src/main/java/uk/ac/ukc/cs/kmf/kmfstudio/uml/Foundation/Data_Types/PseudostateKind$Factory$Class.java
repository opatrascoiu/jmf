/**
 *
 *  Class PseudostateKind$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:57
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class PseudostateKind$Factory$Class
	extends UmlFactory$Class
	implements PseudostateKind$Factory
{
	/** Default factory constructor */
	public PseudostateKind$Factory$Class() {
	}
	public PseudostateKind$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		PseudostateKind obj = new PseudostateKind$Class();
		repository.addElement("uml.Foundation.Data_Types.PseudostateKind", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "PseudostateKind_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.PseudostateKind$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
