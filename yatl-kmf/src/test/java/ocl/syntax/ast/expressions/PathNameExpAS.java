/**
 *
 *  Class PathNameExpAS.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public interface PathNameExpAS
extends
    ocl.syntax.SyntaxElement,
    PrimaryExpAS,
    OclExpressionAS
{

	/** Get the 'pathName' from 'PathNameExpAS' */
	public java.util.List getPathName();
	/** Set the 'pathName' from 'PathNameExpAS' */
	public void setPathName(java.util.List pathName);

	/** Get the id */
	public String getId();
	/** Set the id */
	public void setId(String id);

	/** Override the toString */
	public String toString();

	/** Delete the object */
	public void delete();

	/** Clone the object */
	public Object clone();
}
