/**
 *
 *  Class ObjectSetExpression$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 July 2003 10:22:57
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository;

public class ObjectSetExpression$Factory$Class
	extends UmlFactory$Class
	implements ObjectSetExpression$Factory
{
	/** Default factory constructor */
	public ObjectSetExpression$Factory$Class() {
	}
	public ObjectSetExpression$Factory$Class(UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		ObjectSetExpression obj = new ObjectSetExpression$Class();
		repository.addElement("uml.Foundation.Data_Types.ObjectSetExpression", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Name language, String body) {
		ObjectSetExpression obj = new ObjectSetExpression$Class(language, body);
		repository.addElement("uml.Foundation.Data_Types.ObjectSetExpression", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ObjectSetExpression_Factory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ObjectSetExpression$Visitor' */
	public Object accept(UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
