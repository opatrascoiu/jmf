/**
 *
 *  Class AssociationCallExpASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class AssociationCallExpASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements AssociationCallExpASFactory
{
	/** Default factory constructor */
	public AssociationCallExpASFactory$Class() {
	}
	public AssociationCallExpASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		AssociationCallExpAS obj = new AssociationCallExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.AssociationCallExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre) {
		AssociationCallExpAS obj = new AssociationCallExpAS$Class(isMarkedPre);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.AssociationCallExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "AssociationCallExpASFactory";
	}

	/** Accept 'ocl.syntax.ast.expressions.AssociationCallExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}