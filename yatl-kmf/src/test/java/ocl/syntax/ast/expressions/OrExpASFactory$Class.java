/**
 *
 *  Class OrExpASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class OrExpASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements OrExpASFactory
{
	/** Default factory constructor */
	public OrExpASFactory$Class() {
	}
	public OrExpASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		OrExpAS obj = new OrExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.OrExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre) {
		OrExpAS obj = new OrExpAS$Class(isMarkedPre);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.OrExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OrExpASFactory";
	}

	/** Accept 'ocl.syntax.ast.expressions.OrExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
