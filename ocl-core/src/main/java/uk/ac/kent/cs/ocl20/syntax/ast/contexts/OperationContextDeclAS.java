/**
 *
 *  Class OperationContextDeclAS.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.contexts;

import uk.ac.kent.cs.ocl20.syntax.SyntaxElement;

public interface OperationContextDeclAS
extends
    SyntaxElement,
    ContextDeclarationAS
{

	/** Get the 'operation' from 'OperationContextDeclAS' */
	public OperationAS getOperation();
	/** Set the 'operation' from 'OperationContextDeclAS' */
	public void setOperation(OperationAS operation);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
