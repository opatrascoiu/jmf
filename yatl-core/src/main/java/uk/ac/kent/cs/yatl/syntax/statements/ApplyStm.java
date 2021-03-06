/**
 *
 *  Class ApplyStm.java
 *
 *  Generated by KMFStudio at 22 July 2003 18:50:16
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.yatl.syntax.statements;

import java.util.List;

import uk.ac.kent.cs.yatl.YatlElement;

public interface ApplyStm
extends
    YatlElement,
    Statement
{

	/** Get the 'ruleName' from 'ApplyStm' */
	public String getRuleName();
	/** Set the 'ruleName' from 'ApplyStm' */
	public void setRuleName(String ruleName);

	/** Get the 'args' from 'ApplyStm' */
	public List getArgs();
	/** Set the 'args' from 'ApplyStm' */
	public void setArgs(List args);

	/** Get the 'rule' from 'ApplyStm' */
	public uk.ac.kent.cs.yatl.syntax.transformations.Rule getRule();
	/** Set the 'rule' from 'ApplyStm' */
	public void setRule(uk.ac.kent.cs.yatl.syntax.transformations.Rule rule);

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
