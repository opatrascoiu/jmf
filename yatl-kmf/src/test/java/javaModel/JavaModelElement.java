/**
 *
 *  Class JavaModelElement.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel;

public interface JavaModelElement
	extends JavaModelVisitable
{
	/** Get the id */
	public String getId();
	/** Set the id */
	public void setId(String id);

	/** Delete */
	public void delete();
	/** Override toString */
	public String toString();
}
