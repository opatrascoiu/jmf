/**
 *
 *  Class Association$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:56
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Association$Factory$Class
	extends UmlFactory$Class
	implements Association$Factory
{
	/** Default factory constructor */
	public Association$Factory$Class() {
	}
	public Association$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Association obj = new Association$Class();
		repository.addElement("uml.Foundation.Core.Association", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, Boolean isRoot, Boolean isLeaf, Boolean isAbstract) {
		Association obj = new Association$Class(name, visibility, isSpecification, isRoot, isLeaf, isAbstract);
		repository.addElement("uml.Foundation.Core.Association", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Association_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Association$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
