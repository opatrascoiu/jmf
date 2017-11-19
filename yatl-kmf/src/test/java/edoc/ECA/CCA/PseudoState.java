/**
 *
 *  Class PseudoState.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public interface PseudoState
extends
    edoc.EdocElement,
    Node
{

	/** Get the 'kind' from 'PseudoState' */
	public PseudoStateKind getKind();
	/** Set the 'kind' from 'PseudoState' */
	public void setKind(PseudoStateKind kind);

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