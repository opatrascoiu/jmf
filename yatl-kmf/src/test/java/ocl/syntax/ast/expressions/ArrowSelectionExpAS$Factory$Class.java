/**
 *
 *  Class ArrowSelectionExpAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:52
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class ArrowSelectionExpAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements ArrowSelectionExpAS$Factory
{
	/** Default factory constructor */
	public ArrowSelectionExpAS$Factory$Class() {
	}
	public ArrowSelectionExpAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		ArrowSelectionExpAS obj = new ArrowSelectionExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.ArrowSelectionExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre, String name) {
		ArrowSelectionExpAS obj = new ArrowSelectionExpAS$Class(isMarkedPre, name);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.ArrowSelectionExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ArrowSelectionExpAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.expressions.ArrowSelectionExpAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
