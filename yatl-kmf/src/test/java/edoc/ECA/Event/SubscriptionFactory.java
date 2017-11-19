/**
 *
 *  Class SubscriptionFactory.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public interface SubscriptionFactory
extends
    edoc.EdocFactory
{
	/** Default builder */
	public Object build();
	/** Specialized builder */
	public Object build(String name, Boolean isSynchronous, Boolean isTransactional, edoc.ECA.CCA.DirectionType direction, edoc.ECA.CCA.Status postCondition, String subscriptionClause, String domain);
}