/**
 *
 *  Class IteratorExpASFactory.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public interface IteratorExpASFactory
extends
    ocl.syntax.SyntaxFactory
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(Boolean isMarkedPre, String name);
}
