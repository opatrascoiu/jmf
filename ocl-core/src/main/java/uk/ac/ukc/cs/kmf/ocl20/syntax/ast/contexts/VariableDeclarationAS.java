/**
 *
 *  Class VariableDeclarationAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts;

public interface VariableDeclarationAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement
{

	/** Get the 'name' from 'VariableDeclarationAS' */
	public String getName();
	/** Set the 'name' from 'VariableDeclarationAS' */
	public void setName(String name);

	/** Get the 'initExp' from 'VariableDeclarationAS' */
	public uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.OclExpressionAS getInitExp();
	/** Set the 'initExp' from 'VariableDeclarationAS' */
	public void setInitExp(uk.ac.ukc.cs.kmf.ocl20.syntax.ast.expressions.OclExpressionAS initExp);

	/** Get the 'type' from 'VariableDeclarationAS' */
	public uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS getType();
	/** Set the 'type' from 'VariableDeclarationAS' */
	public void setType(uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS type);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
