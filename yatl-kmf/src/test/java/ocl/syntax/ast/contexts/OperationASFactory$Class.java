/**
 *
 *  Class OperationASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.contexts;

public class OperationASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements OperationASFactory
{
	/** Default factory constructor */
	public OperationASFactory$Class() {
	}
	public OperationASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		OperationAS obj = new OperationAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.contexts.OperationAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(java.util.List pathName, String name) {
		OperationAS obj = new OperationAS$Class(pathName, name);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.contexts.OperationAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OperationASFactory";
	}

	/** Accept 'ocl.syntax.ast.contexts.OperationASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
