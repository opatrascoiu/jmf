/**
 *
 *  Class DotSelectionExpAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:52
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class DotSelectionExpAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements DotSelectionExpAS$Factory
{
	/** Default factory constructor */
	public DotSelectionExpAS$Factory$Class() {
	}
	public DotSelectionExpAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		DotSelectionExpAS obj = new DotSelectionExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.DotSelectionExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre, String name) {
		DotSelectionExpAS obj = new DotSelectionExpAS$Class(isMarkedPre, name);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.DotSelectionExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "DotSelectionExpAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.expressions.DotSelectionExpAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
