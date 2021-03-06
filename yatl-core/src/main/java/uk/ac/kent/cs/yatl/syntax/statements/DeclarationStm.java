/**
 *
 *  Class DeclarationStm.java
 *
 *  Generated by KMFStudio at 22 July 2003 18:50:16
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.yatl.syntax.statements;

import java.util.List;

import uk.ac.kent.cs.yatl.YatlElement;

public interface DeclarationStm
extends
    YatlElement,
    Statement
{

	/** Get the 'decls' from 'DeclarationStm' */
	public List getDecls();
	/** Set the 'decls' from 'DeclarationStm' */
	public void setDecls(List decls);

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
