/**
 *
 *  Class PropertyContextDeclAS.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.contexts;

public interface PropertyContextDeclAS
extends
    ocl.syntax.SyntaxElement,
    ContextDeclarationAS
{

	/** Get the 'pathName' from 'PropertyContextDeclAS' */
	public java.util.List getPathName();
	/** Set the 'pathName' from 'PropertyContextDeclAS' */
	public void setPathName(java.util.List pathName);

	/** Get the 'name' from 'PropertyContextDeclAS' */
	public String getName();
	/** Set the 'name' from 'PropertyContextDeclAS' */
	public void setName(String name);

	/** Get the 'type' from 'PropertyContextDeclAS' */
	public ocl.syntax.ast.types.TypeAS getType();
	/** Set the 'type' from 'PropertyContextDeclAS' */
	public void setType(ocl.syntax.ast.types.TypeAS type);

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
