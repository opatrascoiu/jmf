/**
 *
 *  Class Relationship$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:55
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Relationship$Factory$Class
	extends UmlFactory$Class
	implements Relationship$Factory
{
	/** Default factory constructor */
	public Relationship$Factory$Class() {
	}
	public Relationship$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Relationship obj = new Relationship$Class();
		repository.addElement("uml.Foundation.Core.Relationship", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification) {
		Relationship obj = new Relationship$Class(name, visibility, isSpecification);
		repository.addElement("uml.Foundation.Core.Relationship", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Relationship_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Relationship$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
