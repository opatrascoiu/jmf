/**
 *
 *  Class OperationCallExpAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:52
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class OperationCallExpAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements OperationCallExpAS$Factory
{
	/** Default factory constructor */
	public OperationCallExpAS$Factory$Class() {
	}
	public OperationCallExpAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		OperationCallExpAS obj = new OperationCallExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.OperationCallExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre) {
		OperationCallExpAS obj = new OperationCallExpAS$Class(isMarkedPre);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.OperationCallExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OperationCallExpAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.expressions.OperationCallExpAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
