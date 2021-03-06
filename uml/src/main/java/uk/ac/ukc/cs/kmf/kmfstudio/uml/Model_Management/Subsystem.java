/**
 *
 *  Class Subsystem.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:46
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management;

public interface Subsystem
extends
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlElement,
    Package,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.GeneralizableElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Element
{

	/** Get the 'isInstantiable' from 'Subsystem' */
	public Boolean getIsInstantiable();
	/** Set the 'isInstantiable' from 'Subsystem' */
	public void setIsInstantiable(Boolean isInstantiable);

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
