/**
 *
 *  Class CollectionItemAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:52
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public class CollectionItemAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements CollectionItemAS$Factory
{
	/** Default factory constructor */
	public CollectionItemAS$Factory$Class() {
	}
	public CollectionItemAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		CollectionItemAS obj = new CollectionItemAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.expressions.CollectionItemAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "CollectionItemAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.expressions.CollectionItemAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
