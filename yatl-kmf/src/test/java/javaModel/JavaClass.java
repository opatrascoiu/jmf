/**
 *
 *  Class JavaClass.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel;

public interface JavaClass
extends
    javaModel.JavaModelElement,
    JavaClassifier,
    JavaPackageElement,
    JavaElement
{

	/** Get the 'fields' from 'JavaClass' */
	public java.util.List getFields();
	/** Set the 'fields' from 'JavaClass' */
	public void setFields(java.util.List fields);

	/** Get the 'methods' from 'JavaClass' */
	public java.util.List getMethods();
	/** Set the 'methods' from 'JavaClass' */
	public void setMethods(java.util.List methods);

	/** Get the 'sources' from 'JavaClass' */
	public java.util.Set getSources();
	/** Set the 'sources' from 'JavaClass' */
	public void setSources(java.util.Set sources);

	/** Get the 'implements_' from 'JavaClass' */
	public java.util.Set getImplements_();
	/** Set the 'implements_' from 'JavaClass' */
	public void setImplements_(java.util.Set implements_);

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
