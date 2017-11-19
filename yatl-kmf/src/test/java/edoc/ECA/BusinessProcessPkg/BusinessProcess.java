/**
 *
 *  Class BusinessProcess.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public interface BusinessProcess
extends
    edoc.EdocElement,
    edoc.ECA.CCA.ProcessComponent,
    edoc.ECA.CCA.PortOwner,
    edoc.ECA.CCA.Composition,
    edoc.ECA.CCA.UsageContext,
    edoc.ECA.CCA.Choreography
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