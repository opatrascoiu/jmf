/**
 *
 *  Class ConstraintKindAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:54
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.contexts;

public class ConstraintKindAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements ConstraintKindAS$Factory
{
	/** Default factory constructor */
	public ConstraintKindAS$Factory$Class() {
	}
	public ConstraintKindAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		ConstraintKindAS obj = new ConstraintKindAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.contexts.ConstraintKindAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ConstraintKindAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.contexts.ConstraintKindAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
