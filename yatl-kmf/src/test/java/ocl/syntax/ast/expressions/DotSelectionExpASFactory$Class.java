/**
 *
 *  Class DotSelectionExpASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class DotSelectionExpASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements DotSelectionExpASFactory
{
	/** Default factory constructor */
	public DotSelectionExpASFactory$Class() {
	}
	public DotSelectionExpASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
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
		return "DotSelectionExpASFactory";
	}

	/** Accept 'ocl.syntax.ast.expressions.DotSelectionExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
