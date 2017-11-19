/**
 *
 *  Class Subscription.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public interface Subscription
extends
    edoc.EdocElement,
    edoc.ECA.CCA.FlowPort,
    edoc.ECA.CCA.Port
{

	/** Get the 'subscriptionClause' from 'Subscription' */
	public String getSubscriptionClause();
	/** Set the 'subscriptionClause' from 'Subscription' */
	public void setSubscriptionClause(String subscriptionClause);

	/** Get the 'domain' from 'Subscription' */
	public String getDomain();
	/** Set the 'domain' from 'Subscription' */
	public void setDomain(String domain);

	/** Get the 'requiredBy' from 'Subscription' */
	public java.util.Set getRequiredBy();
	/** Set the 'requiredBy' from 'Subscription' */
	public void setRequiredBy(java.util.Set requiredBy);

	/** Get the 'rules' from 'Subscription' */
	public java.util.Set getRules();
	/** Set the 'rules' from 'Subscription' */
	public void setRules(java.util.Set rules);

	/** Get the 'heldBy' from 'Subscription' */
	public Subscriber getHeldBy();
	/** Set the 'heldBy' from 'Subscription' */
	public void setHeldBy(Subscriber heldBy);

	/** Get the 'subscribedBy' from 'Subscription' */
	public java.util.Set getSubscribedBy();
	/** Set the 'subscribedBy' from 'Subscription' */
	public void setSubscribedBy(java.util.Set subscribedBy);

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
