/**
 *
 *  Class RealLiteralExpAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:52
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class RealLiteralExpAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements RealLiteralExpAS$Factory
{
	/** Default factory constructor */
	public RealLiteralExpAS$Factory$Class() {
	}
	public RealLiteralExpAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		RealLiteralExpAS obj = new RealLiteralExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.RealLiteralExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre, Double value) {
		RealLiteralExpAS obj = new RealLiteralExpAS$Class(isMarkedPre, value);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.RealLiteralExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "RealLiteralExpAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.expressions.RealLiteralExpAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}