/**
 *
 *  Class JavaPackage.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel;

public interface JavaPackage
extends
    javaModel.JavaModelElement,
    JavaPackageElement,
    JavaElement
{

	/** Get the 'elements' from 'JavaPackage' */
	public java.util.List getElements();
	/** Set the 'elements' from 'JavaPackage' */
	public void setElements(java.util.List elements);

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
