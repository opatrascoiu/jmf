/**
 *
 *  Class SetTypeAS.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:49
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax.ast.types;

import uk.ac.kent.cs.ocl20.syntax.SyntaxElement;

public interface SetTypeAS
extends
    SyntaxElement,
    CollectionTypeAS,
    TypeAS
{

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
