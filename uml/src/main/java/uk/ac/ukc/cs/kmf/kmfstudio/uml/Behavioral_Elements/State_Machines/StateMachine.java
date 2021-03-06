/**
 *
 *  Class StateMachine.java
 *
 *  Generated by KMFStudio at 14 April 2004 22:36:46
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.ukc.cs.kmf.kmfstudio.uml.Behavioral_Elements.State_Machines;

public interface StateMachine
extends
    uk.ac.ukc.cs.kmf.kmfstudio.uml.UmlElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement,
    uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Element
{

	/** Get the 'context' from 'StateMachine' */
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement getContext();
	/** Set the 'context' from 'StateMachine' */
	public void setContext(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement context);

	/** Get the 'transitions' from 'StateMachine' */
	public java.util.Set getTransitions();
	/** Set the 'transitions' from 'StateMachine' */
	public void setTransitions(java.util.Set transitions);

	/** Get the 'top' from 'StateMachine' */
	public State getTop();
	/** Set the 'top' from 'StateMachine' */
	public void setTop(State top);

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
