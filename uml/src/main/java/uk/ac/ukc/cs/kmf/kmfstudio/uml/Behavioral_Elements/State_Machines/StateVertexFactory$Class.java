/**
 *
 *  Class StateVertexFactory$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:46
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines;

public class StateVertexFactory$Class
	extends uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class
	implements StateVertexFactory
{
	/** Default factory constructor */
	public StateVertexFactory$Class() {
	}
	public StateVertexFactory$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		StateVertex obj = new StateVertex$Class();
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Behavioral_Elements.State_Machines.StateVertex", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification) {
		StateVertex obj = new StateVertex$Class(name, visibility, isSpecification);
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Behavioral_Elements.State_Machines.StateVertex", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "StateVertexFactory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines.StateVertexVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
