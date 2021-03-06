/**
 *
 *  Class ImpliesExpASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class ImpliesExpASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements ImpliesExpASFactory
{
	/** Default factory constructor */
	public ImpliesExpASFactory$Class() {
	}
	public ImpliesExpASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		ImpliesExpAS obj = new ImpliesExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.ImpliesExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre) {
		ImpliesExpAS obj = new ImpliesExpAS$Class(isMarkedPre);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.ImpliesExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ImpliesExpASFactory";
	}

	/** Accept 'ocl.syntax.ast.expressions.ImpliesExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
