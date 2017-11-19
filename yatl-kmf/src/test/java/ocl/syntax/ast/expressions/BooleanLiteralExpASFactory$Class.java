/**
 *
 *  Class BooleanLiteralExpASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class BooleanLiteralExpASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements BooleanLiteralExpASFactory
{
	/** Default factory constructor */
	public BooleanLiteralExpASFactory$Class() {
	}
	public BooleanLiteralExpASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		BooleanLiteralExpAS obj = new BooleanLiteralExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.BooleanLiteralExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre, Boolean value) {
		BooleanLiteralExpAS obj = new BooleanLiteralExpAS$Class(isMarkedPre, value);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.BooleanLiteralExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "BooleanLiteralExpASFactory";
	}

	/** Accept 'ocl.syntax.ast.expressions.BooleanLiteralExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
