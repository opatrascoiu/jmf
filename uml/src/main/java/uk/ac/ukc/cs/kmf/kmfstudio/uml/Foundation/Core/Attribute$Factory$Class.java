/**
 *
 *  Class Attribute$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:55
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Attribute$Factory$Class
	extends UmlFactory$Class
	implements Attribute$Factory
{
	/** Default factory constructor */
	public Attribute$Factory$Class() {
	}
	public Attribute$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Attribute obj = new Attribute$Class();
		repository.addElement("uml.Foundation.Core.Attribute", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind ownerScope, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity multiplicity, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ChangeabilityKind changeability, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind targetScope, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.OrderingKind ordering, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Expression initialValue) {
		Attribute obj = new Attribute$Class(name, visibility, isSpecification, ownerScope, multiplicity, changeability, targetScope, ordering, initialValue);
		repository.addElement("uml.Foundation.Core.Attribute", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Attribute_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Attribute$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
