/**
 *
 *  Class Guard$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:58
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Guard$Factory$Class
	extends UmlFactory$Class
	implements Guard$Factory
{
	/** Default factory constructor */
	public Guard$Factory$Class() {
	}
	public Guard$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Guard obj = new Guard$Class();
		repository.addElement("uml.Behavioral_Elements.State_Machines.Guard", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.BooleanExpression expression) {
		Guard obj = new Guard$Class(name, visibility, isSpecification, expression);
		repository.addElement("uml.Behavioral_Elements.State_Machines.Guard", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Guard_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines.Guard$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
