/**
 *
 *  Class OperationContextDeclAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:54
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.contexts;

public class OperationContextDeclAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements OperationContextDeclAS$Factory
{
	/** Default factory constructor */
	public OperationContextDeclAS$Factory$Class() {
	}
	public OperationContextDeclAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		OperationContextDeclAS obj = new OperationContextDeclAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.contexts.OperationContextDeclAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OperationContextDeclAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.contexts.OperationContextDeclAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
