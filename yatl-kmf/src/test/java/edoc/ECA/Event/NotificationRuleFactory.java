/**
 *
 *  Class NotificationRuleFactory.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public interface NotificationRuleFactory
extends
    edoc.EdocFactory
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(String condition);
}
