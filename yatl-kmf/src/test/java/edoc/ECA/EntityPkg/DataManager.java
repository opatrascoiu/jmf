/**
 *
 *  Class DataManager.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public interface DataManager
extends
    edoc.EdocElement,
    edoc.ECA.CCA.ProcessComponent,
    edoc.ECA.CCA.PortOwner,
    edoc.ECA.CCA.Composition,
    edoc.ECA.CCA.UsageContext,
    edoc.ECA.CCA.Choreography
{

	/** Get the 'networkAccess' from 'DataManager' */
	public Boolean getNetworkAccess();
	/** Set the 'networkAccess' from 'DataManager' */
	public void setNetworkAccess(Boolean networkAccess);

	/** Get the 'shareable' from 'DataManager' */
	public Boolean getShareable();
	/** Set the 'shareable' from 'DataManager' */
	public void setShareable(Boolean shareable);

	/** Get the 'manages' from 'DataManager' */
	public edoc.ECA.DocumentModel.CompositeData getManages();
	/** Set the 'manages' from 'DataManager' */
	public void setManages(edoc.ECA.DocumentModel.CompositeData manages);

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
