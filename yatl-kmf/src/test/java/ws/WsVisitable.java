/**
 *
 *  Class WsVisitable.java
 *
 *  Generated by KMFStudio at 09 March 2004 13:29:40
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package ws;

public interface WsVisitable
{
	/** Accept the visitor */
	public Object accept(WsVisitor v, Object obj);
}
