/**
 *
 *  Class Operation$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:55
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Operation$Factory$Class
	extends UmlFactory$Class
	implements Operation$Factory
{
	/** Default factory constructor */
	public Operation$Factory$Class() {
	}
	public Operation$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Operation obj = new Operation$Class();
		repository.addElement("uml.Foundation.Core.Operation", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind ownerScope, Boolean isQuery, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.CallConcurrencyKind concurrency, Boolean isRoot, Boolean isLeaf, Boolean isAbstract, String specification) {
		Operation obj = new Operation$Class(name, visibility, isSpecification, ownerScope, isQuery, concurrency, isRoot, isLeaf, isAbstract, specification);
		repository.addElement("uml.Foundation.Core.Operation", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Operation_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
