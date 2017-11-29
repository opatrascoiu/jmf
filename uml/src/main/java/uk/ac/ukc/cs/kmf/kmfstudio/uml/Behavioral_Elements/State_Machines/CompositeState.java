/**
 *
 *  Class CompositeState.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:47
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines;

public interface CompositeState
extends
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlElement,
    State,
    StateVertex,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Element
{

	/** Get the 'subvertex' from 'CompositeState' */
	public java.util.Set getSubvertex();
	/** Set the 'subvertex' from 'CompositeState' */
	public void setSubvertex(java.util.Set subvertex);

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
