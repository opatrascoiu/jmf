/**
 *
 *  Class JavaModelVisitable.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel;

public interface JavaModelVisitable
{
	/** Accept the visitor */
	public Object accept(JavaModelVisitor v, Object obj);
}
