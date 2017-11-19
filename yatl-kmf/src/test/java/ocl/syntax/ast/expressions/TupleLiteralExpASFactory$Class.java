/**
 *
 *  Class TupleLiteralExpASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class TupleLiteralExpASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements TupleLiteralExpASFactory
{
	/** Default factory constructor */
	public TupleLiteralExpASFactory$Class() {
	}
	public TupleLiteralExpASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		TupleLiteralExpAS obj = new TupleLiteralExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.TupleLiteralExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre) {
		TupleLiteralExpAS obj = new TupleLiteralExpAS$Class(isMarkedPre);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.TupleLiteralExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "TupleLiteralExpASFactory";
	}

	/** Accept 'ocl.syntax.ast.expressions.TupleLiteralExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}