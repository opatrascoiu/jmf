/**
 *
 *  Class XorExpAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:52
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class XorExpAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements XorExpAS$Factory
{
	/** Default factory constructor */
	public XorExpAS$Factory$Class() {
	}
	public XorExpAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		XorExpAS obj = new XorExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.XorExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre) {
		XorExpAS obj = new XorExpAS$Class(isMarkedPre);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.XorExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "XorExpAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.expressions.XorExpAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
