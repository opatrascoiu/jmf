/**
 *
 *  Class Usage$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:56
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Usage$Factory$Class
	extends UmlFactory$Class
	implements Usage$Factory
{
	/** Default factory constructor */
	public Usage$Factory$Class() {
	}
	public Usage$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Usage obj = new Usage$Class();
		repository.addElement("uml.Foundation.Core.Usage", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification) {
		Usage obj = new Usage$Class(name, visibility, isSpecification);
		repository.addElement("uml.Foundation.Core.Usage", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Usage_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Usage$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
