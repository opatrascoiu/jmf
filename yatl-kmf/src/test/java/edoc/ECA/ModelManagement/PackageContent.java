/**
 *
 *  Class PackageContent.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:35
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.ModelManagement;

public interface PackageContent
extends
    edoc.EdocElement
{

	/** Get the 'name' from 'PackageContent' */
	public String getName();
	/** Set the 'name' from 'PackageContent' */
	public void setName(String name);

	/** Get the 'namespace_' from 'PackageContent' */
	public PackageECA getNamespace_();
	/** Set the 'namespace_' from 'PackageContent' */
	public void setNamespace_(PackageECA namespace_);

	/** Get the 'elementImport' from 'PackageContent' */
	public java.util.Set getElementImport();
	/** Set the 'elementImport' from 'PackageContent' */
	public void setElementImport(java.util.Set elementImport);

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
