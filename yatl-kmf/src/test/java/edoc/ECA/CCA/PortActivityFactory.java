/**
 *
 *  Class PortActivityFactory.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public interface PortActivityFactory
extends
    edoc.EdocFactory
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(String name);
}
