/**
 *
 *  Class Query.java
 *
 *  Generated by KMFStudio at 22 July 2003 18:50:16
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.yatl.syntax.transformations;

import java.util.List;

import uk.ac.kent.cs.yatl.YatlElement;

public interface Query
extends
    YatlElement,
    NamedElement
{

	/** Get the 'contextDeclList' from 'Query' */
	public List getContextDeclList();
	/** Set the 'contextDeclList' from 'Query' */
	public void setContextDeclList(List contextDeclList);

	/** Get the 'location' from 'Query' */
	public Object getLocation();
	/** Set the 'location' from 'Query' */
	public void setLocation(Object location);

	/** Get the 'namespace_' from 'Query' */
	public Namespace getNamespace_();
	/** Set the 'namespace_' from 'Query' */
	public void setNamespace_(Namespace namespace_);

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
