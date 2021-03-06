/**
 *
 *  Class LogicalExpAS.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.expressions;

public interface LogicalExpAS
extends
    ocl.syntax.SyntaxElement,
    OclExpressionAS
{

	/** Get the 'leftOperand' from 'LogicalExpAS' */
	public OclExpressionAS getLeftOperand();
	/** Set the 'leftOperand' from 'LogicalExpAS' */
	public void setLeftOperand(OclExpressionAS leftOperand);

	/** Get the 'rightOperand' from 'LogicalExpAS' */
	public OclExpressionAS getRightOperand();
	/** Set the 'rightOperand' from 'LogicalExpAS' */
	public void setRightOperand(OclExpressionAS rightOperand);

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
