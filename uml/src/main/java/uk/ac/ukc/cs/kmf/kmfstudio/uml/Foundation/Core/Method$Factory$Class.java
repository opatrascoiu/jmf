/**
 *
 *  Class Method$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:55
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Method$Factory$Class
	extends UmlFactory$Class
	implements Method$Factory
{
	/** Default factory constructor */
	public Method$Factory$Class() {
	}
	public Method$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Method obj = new Method$Class();
		repository.addElement("uml.Foundation.Core.Method", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ScopeKind ownerScope, Boolean isQuery, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ProcedureExpression body) {
		Method obj = new Method$Class(name, visibility, isSpecification, ownerScope, isQuery, body);
		repository.addElement("uml.Foundation.Core.Method", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Method_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Method$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
