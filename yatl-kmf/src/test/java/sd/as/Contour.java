/**
 *
 *  Class Contour.java
 *
 *  Generated by KMFStudio at 18 February 2004 20:38:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public interface Contour
extends
    sd.SdElement
{

	/** Get the 'name' from 'Contour' */
	public String getName();
	/** Set the 'name' from 'Contour' */
	public void setName(String name);

	/** Get the 'excludingContourZones' from 'Contour' */
	public java.util.List getExcludingContourZones();
	/** Set the 'excludingContourZones' from 'Contour' */
	public void setExcludingContourZones(java.util.List excludingContourZones);

	/** Get the 'containingContourZones' from 'Contour' */
	public java.util.List getContainingContourZones();
	/** Set the 'containingContourZones' from 'Contour' */
	public void setContainingContourZones(java.util.List containingContourZones);

	/** Get the 'allContourZone' from 'Contour' */
	public java.util.List getAllContourZone();
	/** Set the 'allContourZone' from 'Contour' */
	public void setAllContourZone(java.util.List allContourZone);

	/** Get the 'unitaryDiagrams' from 'Contour' */
	public java.util.List getUnitaryDiagrams();
	/** Set the 'unitaryDiagrams' from 'Contour' */
	public void setUnitaryDiagrams(java.util.List unitaryDiagrams);

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
