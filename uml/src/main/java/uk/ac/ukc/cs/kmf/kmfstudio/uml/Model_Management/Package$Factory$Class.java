/**
 *
 *  Class Package$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:58
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Package$Factory$Class
	extends UmlFactory$Class
	implements Package$Factory
{
	/** Default factory constructor */
	public Package$Factory$Class() {
	}
	public Package$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Package obj = new Package$Class();
		repository.addElement("uml.Model_Management.Package", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, Boolean isRoot, Boolean isLeaf, Boolean isAbstract) {
		Package obj = new Package$Class(name, visibility, isSpecification, isRoot, isLeaf, isAbstract);
		repository.addElement("uml.Model_Management.Package", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Package_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
