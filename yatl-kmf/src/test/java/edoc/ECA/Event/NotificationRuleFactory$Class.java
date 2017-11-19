/**
 *
 *  Class NotificationRuleFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public class NotificationRuleFactory$Class
	extends edoc.EdocFactory$Class
	implements NotificationRuleFactory
{
	/** Default factory constructor */
	public NotificationRuleFactory$Class() {
	}
	public NotificationRuleFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		NotificationRule obj = new NotificationRule$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.Event.NotificationRule", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String condition) {
		NotificationRule obj = new NotificationRule$Class(condition);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.Event.NotificationRule", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "NotificationRuleFactory";
	}

	/** Accept 'edoc.ECA.Event.NotificationRuleVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}