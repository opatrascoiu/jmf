/**
 *
 *  Class TupleTypeAS.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:44
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.types;

public interface TupleTypeAS
extends
    ocl.syntax.SyntaxElement,
    TypeAS
{

	/** Get the 'variableDeclarationList' from 'TupleTypeAS' */
	public java.util.List getVariableDeclarationList();
	/** Set the 'variableDeclarationList' from 'TupleTypeAS' */
	public void setVariableDeclarationList(java.util.List variableDeclarationList);

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