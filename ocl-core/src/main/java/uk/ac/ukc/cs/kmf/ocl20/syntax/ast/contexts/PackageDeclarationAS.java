/**
 *
 *  Class PackageDeclarationAS.java
 *
 *  Generated by KMFStudio at 13 April 2004 12:05:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.ocl20.syntax.ast.contexts;

public interface PackageDeclarationAS
extends
    uk.ac.ukc.cs.kmf.ocl20.syntax.SyntaxElement
{

	/** Get the 'pathName' from 'PackageDeclarationAS' */
	public java.util.List getPathName();
	/** Set the 'pathName' from 'PackageDeclarationAS' */
	public void setPathName(java.util.List pathName);

	/** Get the 'contextDecls' from 'PackageDeclarationAS' */
	public java.util.List getContextDecls();
	/** Set the 'contextDecls' from 'PackageDeclarationAS' */
	public void setContextDecls(java.util.List contextDecls);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}