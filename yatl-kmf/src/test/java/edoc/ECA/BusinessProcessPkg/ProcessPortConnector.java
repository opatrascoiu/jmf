/**
 *
 *  Class ProcessPortConnector.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public interface ProcessPortConnector
extends
    edoc.EdocElement,
    edoc.ECA.CCA.PortConnector,
    edoc.ECA.CCA.PortUseage,
    edoc.ECA.CCA.UsageContext,
    edoc.ECA.CCA.Node
{

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