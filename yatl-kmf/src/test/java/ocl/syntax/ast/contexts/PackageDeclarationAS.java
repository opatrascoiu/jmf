/**
 *
 *  Class PackageDeclarationAS.java
 *
 *  Generated by KMFStudio at 17 February 2004 14:38:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ocl.syntax.ast.contexts;

public interface PackageDeclarationAS
extends
    ocl.syntax.SyntaxElement
{

	/** Get the 'pathName' from 'PackageDeclarationAS' */
	public java.util.List getPathName();
	/** Set the 'pathName' from 'PackageDeclarationAS' */
	public void setPathName(java.util.List pathName);

	/** Get the 'contextDecls' from 'PackageDeclarationAS' */
	public java.util.List getContextDecls();
	/** Set the 'contextDecls' from 'PackageDeclarationAS' */
	public void setContextDecls(java.util.List contextDecls);

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