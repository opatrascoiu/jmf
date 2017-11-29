/**
 *
 *  Class StateMachine$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:57
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class StateMachine$Factory$Class
	extends UmlFactory$Class
	implements StateMachine$Factory
{
	/** Default factory constructor */
	public StateMachine$Factory$Class() {
	}
	public StateMachine$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		StateMachine obj = new StateMachine$Class();
		repository.addElement("uml.Behavioral_Elements.State_Machines.StateMachine", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification) {
		StateMachine obj = new StateMachine$Class(name, visibility, isSpecification);
		repository.addElement("uml.Behavioral_Elements.State_Machines.StateMachine", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "StateMachine_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines.StateMachine$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
