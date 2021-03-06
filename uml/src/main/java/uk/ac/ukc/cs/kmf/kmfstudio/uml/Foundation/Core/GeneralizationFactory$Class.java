/**
 *
 *  Class GeneralizationFactory$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

public class GeneralizationFactory$Class
	extends uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class
	implements GeneralizationFactory
{
	/** Default factory constructor */
	public GeneralizationFactory$Class() {
	}
	public GeneralizationFactory$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Generalization obj = new Generalization$Class();
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Core.Generalization", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name discriminator) {
		Generalization obj = new Generalization$Class(name, visibility, isSpecification, discriminator);
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Core.Generalization", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "GeneralizationFactory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizationVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
