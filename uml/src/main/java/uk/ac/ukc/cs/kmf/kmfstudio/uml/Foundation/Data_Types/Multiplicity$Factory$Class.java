/**
 *
 *  Class Multiplicity$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:57
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class Multiplicity$Factory$Class
	extends UmlFactory$Class
	implements Multiplicity$Factory
{
	/** Default factory constructor */
	public Multiplicity$Factory$Class() {
	}
	public Multiplicity$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Multiplicity obj = new Multiplicity$Class();
		repository.addElement("uml.Foundation.Data_Types.Multiplicity", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Multiplicity_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
