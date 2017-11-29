/**
 *
 *  Class Guard.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:46
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines;

public interface Guard
extends
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Element
{

	/** Get the 'expression' from 'Guard' */
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.BooleanExpression getExpression();
	/** Set the 'expression' from 'Guard' */
	public void setExpression(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.BooleanExpression expression);

	/** Get the 'transition' from 'Guard' */
	public Transition getTransition();
	/** Set the 'transition' from 'Guard' */
	public void setTransition(Transition transition);

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
