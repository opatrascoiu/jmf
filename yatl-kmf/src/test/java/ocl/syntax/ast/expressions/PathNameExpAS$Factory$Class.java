/**
 *
 *  Class PathNameExpAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:52
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class PathNameExpAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements PathNameExpAS$Factory
{
	/** Default factory constructor */
	public PathNameExpAS$Factory$Class() {
	}
	public PathNameExpAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
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
		return "PathNameExpAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.expressions.PathNameExpAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
