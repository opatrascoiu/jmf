/**
 *
 *  Class JavaPackageElement.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel;

public interface JavaPackageElement
extends
    javaModel.JavaModelElement,
    JavaElement
{

	/** Get the 'javaPackage' from 'JavaPackageElement' */
	public JavaPackage getJavaPackage();
	/** Set the 'javaPackage' from 'JavaPackageElement' */
	public void setJavaPackage(JavaPackage javaPackage);

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
