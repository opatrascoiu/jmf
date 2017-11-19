/**
 *
 *  Class CollectionLiteralExpASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class CollectionLiteralExpASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements CollectionLiteralExpASFactory
{
	/** Default factory constructor */
	public CollectionLiteralExpASFactory$Class() {
	}
	public CollectionLiteralExpASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		CollectionLiteralExpAS obj = new CollectionLiteralExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.CollectionLiteralExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre, ocl.syntax.ast.expressions.CollectionKindAS kind) {
		CollectionLiteralExpAS obj = new CollectionLiteralExpAS$Class(isMarkedPre, kind);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.CollectionLiteralExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "CollectionLiteralExpASFactory";
	}

	/** Accept 'ocl.syntax.ast.expressions.CollectionLiteralExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}