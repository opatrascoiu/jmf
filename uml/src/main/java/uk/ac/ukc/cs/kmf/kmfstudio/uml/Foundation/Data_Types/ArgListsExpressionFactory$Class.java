/**
 *
 *  Class ArgListsExpressionFactory$Class.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types;

public class ArgListsExpressionFactory$Class
	extends uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class
	implements ArgListsExpressionFactory
{
	/** Default factory constructor */
	public ArgListsExpressionFactory$Class() {
	}
	public ArgListsExpressionFactory$Class(uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.UmlRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		ArgListsExpression obj = new ArgListsExpression$Class();
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Data_Types.ArgListsExpression", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name language, String body) {
		ArgListsExpression obj = new ArgListsExpression$Class(language, body);
		obj.setId(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlFactory$Class.newId());
		repository.addElement("uml.Foundation.Data_Types.ArgListsExpression", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ArgListsExpressionFactory";
	}

	/** Accept 'uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ArgListsExpressionVisitor' */
	public Object accept(uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlVisitor v, Object data) {
		return v.visit(this, data);
	}
}
