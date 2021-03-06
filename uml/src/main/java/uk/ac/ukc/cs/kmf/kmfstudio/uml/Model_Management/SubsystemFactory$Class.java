/**
 *
 *  Class SubsystemFactory$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:46
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management;

public class SubsystemFactory$Class
	extends uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class
	implements SubsystemFactory
{
	/** Default factory constructor */
	public SubsystemFactory$Class() {
	}
	public SubsystemFactory$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Subsystem obj = new Subsystem$Class();
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Model_Management.Subsystem", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, Boolean isRoot, Boolean isLeaf, Boolean isAbstract, Boolean isInstantiable) {
		Subsystem obj = new Subsystem$Class(name, visibility, isSpecification, isRoot, isLeaf, isAbstract, isInstantiable);
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Model_Management.Subsystem", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "SubsystemFactory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.SubsystemVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
