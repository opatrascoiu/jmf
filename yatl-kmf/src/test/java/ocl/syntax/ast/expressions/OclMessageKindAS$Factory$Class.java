/**
 *
 *  Class OclMessageKindAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:52
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class OclMessageKindAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements OclMessageKindAS$Factory
{
	/** Default factory constructor */
	public OclMessageKindAS$Factory$Class() {
	}
	public OclMessageKindAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		OclMessageKindAS obj = new OclMessageKindAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.OclMessageKindAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OclMessageKindAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.expressions.OclMessageKindAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}