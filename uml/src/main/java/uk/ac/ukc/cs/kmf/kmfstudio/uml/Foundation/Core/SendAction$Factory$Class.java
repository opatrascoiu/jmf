/**
 *
 *  Class SendAction$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:56
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class SendAction$Factory$Class
	extends UmlFactory$Class
	implements SendAction$Factory
{
	/** Default factory constructor */
	public SendAction$Factory$Class() {
	}
	public SendAction$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		SendAction obj = new SendAction$Class();
		repository.addElement("uml.Foundation.Core.SendAction", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name name, uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.VisibilityKind visibility, Boolean isSpecification) {
		SendAction obj = new SendAction$Class(name, visibility, isSpecification);
		repository.addElement("uml.Foundation.Core.SendAction", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "SendAction_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.SendAction$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
