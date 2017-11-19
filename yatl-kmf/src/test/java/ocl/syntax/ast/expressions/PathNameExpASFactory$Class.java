/**
 *
 *  Class PathNameExpASFactory$Class.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class PathNameExpASFactory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements PathNameExpASFactory
{
	/** Default factory constructor */
	public PathNameExpASFactory$Class() {
	}
	public PathNameExpASFactory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		PathNameExpAS obj = new PathNameExpAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.PathNameExpAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(Boolean isMarkedPre, java.util.List pathName) {
		PathNameExpAS obj = new PathNameExpAS$Class(isMarkedPre, pathName);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.PathNameExpAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "PathNameExpASFactory";
	}

	/** Accept 'ocl.syntax.ast.expressions.PathNameExpASVisitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
