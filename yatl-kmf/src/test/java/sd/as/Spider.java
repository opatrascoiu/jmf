/**
 *
 *  Class Spider.java
 *
 *  Generated by KMFStudio at 18 February 2004 20:38:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public interface Spider
extends
    sd.SdElement
{

	/** Get the 'habitat' from 'Spider' */
	public java.util.List getHabitat();
	/** Set the 'habitat' from 'Spider' */
	public void setHabitat(java.util.List habitat);

	/** Get the 'unitaryDiagram' from 'Spider' */
	public UnitaryDiagram getUnitaryDiagram();
	/** Set the 'unitaryDiagram' from 'Spider' */
	public void setUnitaryDiagram(UnitaryDiagram unitaryDiagram);

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