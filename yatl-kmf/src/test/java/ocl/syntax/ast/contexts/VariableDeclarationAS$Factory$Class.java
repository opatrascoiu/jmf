/**
 *
 *  Class VariableDeclarationAS$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:00:54
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.contexts;

public class VariableDeclarationAS$Factory$Class
	extends ocl.syntax.SyntaxFactory$Class
	implements VariableDeclarationAS$Factory
{
	/** Default factory constructor */
	public VariableDeclarationAS$Factory$Class() {
	}
	public VariableDeclarationAS$Factory$Class(ocl.syntax.repository.SyntaxRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		VariableDeclarationAS obj = new VariableDeclarationAS$Class();
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.contexts.VariableDeclarationAS", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		VariableDeclarationAS obj = new VariableDeclarationAS$Class(name);
		obj.setId(ocl.syntax.SyntaxFactory$Class.newId());
		repository.addElement("syntax.ast.contexts.VariableDeclarationAS", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "VariableDeclarationAS_Factory";
	}

	/** Accept 'ocl.syntax.ast.contexts.VariableDeclarationAS$Visitor' */
	public Object accept(ocl.syntax.SyntaxVisitor v, Object data) {
		return v.visit(this, data);
	}
}
