/**
 *
 *  Class PackageDeclarationAS.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.contexts;

import java.util.List;

import uk.ac.kent.cs.ocl20.syntax.SyntaxElement;

public interface PackageDeclarationAS
extends
    SyntaxElement
{

	/** Get the 'pathName' from 'PackageDeclarationAS' */
	public List getPathName();
	/** Set the 'pathName' from 'PackageDeclarationAS' */
	public void setPathName(List pathName);

	/** Get the 'contextDecls' from 'PackageDeclarationAS' */
	public List getContextDecls();
	/** Set the 'contextDecls' from 'PackageDeclarationAS' */
	public void setContextDecls(List contextDecls);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
