/**
 *
 *  Class ProcessFlowPort.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public interface ProcessFlowPort
extends
    edoc.EdocElement,
    edoc.ECA.CCA.FlowPort,
    edoc.ECA.CCA.Port
{

	/** Get the 'multiplicity_lb' from 'ProcessFlowPort' */
	public Integer getMultiplicity_lb();
	/** Set the 'multiplicity_lb' from 'ProcessFlowPort' */
	public void setMultiplicity_lb(Integer multiplicity_lb);

	/** Get the 'multiplicity_ub' from 'ProcessFlowPort' */
	public Integer getMultiplicity_ub();
	/** Set the 'multiplicity_ub' from 'ProcessFlowPort' */
	public void setMultiplicity_ub(Integer multiplicity_ub);

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