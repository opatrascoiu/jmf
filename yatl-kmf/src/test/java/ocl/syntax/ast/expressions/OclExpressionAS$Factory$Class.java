/**
 *
 *  Class OclExpressionAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:52
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class OclExpressionAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements OclExpressionAS$Factory
{
	/** Default factory constructor */
	public OclExpressionAS$Factory$Class() {
	}
	public OclExpressionAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		OclExpressionAS obj = new OclExpressionAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.OclExpressionAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre) {
		OclExpressionAS obj = new OclExpressionAS$Class(isMarkedPre);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.OclExpressionAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OclExpressionAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.expressions.OclExpressionAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
