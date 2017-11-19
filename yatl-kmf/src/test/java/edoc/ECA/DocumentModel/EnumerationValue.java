/**
 *
 *  Class EnumerationValue.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:35
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.DocumentModel;

public interface EnumerationValue
extends
    edoc.EdocElement
{

	/** Get the 'name' from 'EnumerationValue' */
	public String getName();
	/** Set the 'name' from 'EnumerationValue' */
	public void setName(String name);

	/** Get the 'enumeration' from 'EnumerationValue' */
	public Enumeration_ getEnumeration();
	/** Set the 'enumeration' from 'EnumerationValue' */
	public void setEnumeration(Enumeration_ enumeration);

	/** Get the 'initialFor' from 'EnumerationValue' */
	public Enumeration_ getInitialFor();
	/** Set the 'initialFor' from 'EnumerationValue' */
	public void setInitialFor(Enumeration_ initialFor);

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