/**
 *
 *  Class ModelElement$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:56
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class ModelElement$Factory$Class
	extends UmlFactory$Class
	implements ModelElement$Factory
{
	/** Default factory constructor */
	public ModelElement$Factory$Class() {
	}
	public ModelElement$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		ModelElement obj = new ModelElement$Class();
		repository.addElement("uml.Foundation.Core.ModelElement", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification) {
		ModelElement obj = new ModelElement$Class(name, visibility, isSpecification);
		repository.addElement("uml.Foundation.Core.ModelElement", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ModelElement_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
