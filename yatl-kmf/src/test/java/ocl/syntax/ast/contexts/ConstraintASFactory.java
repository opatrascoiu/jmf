/**
 *
 *  Class ConstraintASFactory.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.contexts;

public interface ConstraintASFactory
extends
    ocl.syntax.SyntaxFactory
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(String name, ConstraintKindAS kind);
}