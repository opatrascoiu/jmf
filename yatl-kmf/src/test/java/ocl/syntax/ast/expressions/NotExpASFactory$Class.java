/**
 *
 *  Class NotExpASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class NotExpASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements NotExpASFactory
{
	/** Default factory constructor */
	public NotExpASFactory$Class() {
	}
	public NotExpASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		NotExpAS obj = new NotExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.NotExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre) {
		NotExpAS obj = new NotExpAS$Class(isMarkedPre);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.NotExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "NotExpASFactory";
	}

	/** Accept 'ocl.syntax.ast.expressions.NotExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
