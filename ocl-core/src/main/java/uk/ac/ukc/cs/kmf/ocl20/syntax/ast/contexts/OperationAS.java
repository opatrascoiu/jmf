/**
 *
 *  Class OperationAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts;

public interface OperationAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement
{

	/** Get the 'pathName' from 'OperationAS' */
	public java.util.List getPathName();
	/** Set the 'pathName' from 'OperationAS' */
	public void setPathName(java.util.List pathName);

	/** Get the 'name' from 'OperationAS' */
	public String getName();
	/** Set the 'name' from 'OperationAS' */
	public void setName(String name);

	/** Get the 'parameters' from 'OperationAS' */
	public java.util.List getParameters();
	/** Set the 'parameters' from 'OperationAS' */
	public void setParameters(java.util.List parameters);

	/** Get the 'type' from 'OperationAS' */
	public uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS getType();
	/** Set the 'type' from 'OperationAS' */
	public void setType(uk.ac.ukc.cs.kmf.ocl20.syntax.ast.types.TypeAS type);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
