/**
 *
 *  Class SyntaxVisitable.java
 *
 *  Generated by KMFStudio at 11 June 2003 13:46:50
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package uk.ac.kent.cs.ocl20.syntax;

public interface SyntaxVisitable
{
	/** Accept the visitor */
	public Object accept(SyntaxVisitor v, Object obj);
}
