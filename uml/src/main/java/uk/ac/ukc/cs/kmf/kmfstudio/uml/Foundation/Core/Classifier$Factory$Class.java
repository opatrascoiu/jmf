/**
 *
 *  Class Classifier$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:56
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Classifier$Factory$Class
	extends UmlFactory$Class
	implements Classifier$Factory
{
	/** Default factory constructor */
	public Classifier$Factory$Class() {
	}
	public Classifier$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Classifier obj = new Classifier$Class();
		repository.addElement("uml.Foundation.Core.Classifier", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, Boolean isRoot, Boolean isLeaf, Boolean isAbstract) {
		Classifier obj = new Classifier$Class(name, visibility, isSpecification, isRoot, isLeaf, isAbstract);
		repository.addElement("uml.Foundation.Core.Classifier", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Classifier_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}