/**
 *
 *  Class Parameter$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:57
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Parameter$Factory$Class
	extends UmlFactory$Class
	implements Parameter$Factory
{
	/** Default factory constructor */
	public Parameter$Factory$Class() {
	}
	public Parameter$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Parameter obj = new Parameter$Class();
		repository.addElement("uml.Foundation.Core.Parameter", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Expression defaultValue, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ParameterDirectionKind kind) {
		Parameter obj = new Parameter$Class(name, visibility, isSpecification, defaultValue, kind);
		repository.addElement("uml.Foundation.Core.Parameter", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Parameter_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
