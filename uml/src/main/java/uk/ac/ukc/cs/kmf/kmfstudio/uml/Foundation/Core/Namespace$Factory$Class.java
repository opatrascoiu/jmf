/**
 *
 *  Class Namespace$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:55
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Namespace$Factory$Class
	extends UmlFactory$Class
	implements Namespace$Factory
{
	/** Default factory constructor */
	public Namespace$Factory$Class() {
	}
	public Namespace$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Namespace obj = new Namespace$Class();
		repository.addElement("uml.Foundation.Core.Namespace", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification) {
		Namespace obj = new Namespace$Class(name, visibility, isSpecification);
		repository.addElement("uml.Foundation.Core.Namespace", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Namespace_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
