/**
 *
 *  Class AssociationClassFactory$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

public class AssociationClassFactory$Class
	extends uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class
	implements AssociationClassFactory
{
	/** Default factory constructor */
	public AssociationClassFactory$Class() {
	}
	public AssociationClassFactory$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		AssociationClass obj = new AssociationClass$Class();
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Core.AssociationClass", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, Boolean isRoot, Boolean isLeaf, Boolean isAbstract, Boolean isActive) {
		AssociationClass obj = new AssociationClass$Class(name, visibility, isSpecification, isRoot, isLeaf, isAbstract, isActive);
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Core.AssociationClass", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "AssociationClassFactory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationClassVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
