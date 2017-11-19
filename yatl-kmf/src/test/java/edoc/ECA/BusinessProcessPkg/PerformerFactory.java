/**
 *
 *  Class PerformerFactory.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public interface PerformerFactory
extends
    edoc.EdocFactory
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(String name, String selectionRule, String creationRule);
}
