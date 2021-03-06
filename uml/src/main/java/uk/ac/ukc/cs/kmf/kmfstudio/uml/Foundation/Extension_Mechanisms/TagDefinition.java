/**
 *
 *  Class TagDefinition.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Extension_Mechanisms;

public interface TagDefinition
extends
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Element
{

	/** Get the 'tagType' from 'TagDefinition' */
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name getTagType();
	/** Set the 'tagType' from 'TagDefinition' */
	public void setTagType(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name tagType);

	/** Get the 'multiplicity' from 'TagDefinition' */
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity getMultiplicity();
	/** Set the 'multiplicity' from 'TagDefinition' */
	public void setMultiplicity(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Multiplicity multiplicity);

	/** Get the 'typedValue' from 'TagDefinition' */
	public java.util.Set getTypedValue();
	/** Set the 'typedValue' from 'TagDefinition' */
	public void setTypedValue(java.util.Set typedValue);

	/** Get the 'owner' from 'TagDefinition' */
	public Stereotype getOwner();
	/** Set the 'owner' from 'TagDefinition' */
	public void setOwner(Stereotype owner);

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
