/**
 *
 *  Class EventConditionFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public class EventConditionFactory$Class
	extends edoc.EdocFactory$Class
	implements EventConditionFactory
{
	/** Default factory constructor */
	public EventConditionFactory$Class() {
	}
	public EventConditionFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		EventCondition obj = new EventCondition$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.Event.EventCondition", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String condition) {
		EventCondition obj = new EventCondition$Class(condition);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.Event.EventCondition", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "EventConditionFactory";
	}

	/** Accept 'edoc.ECA.Event.EventConditionVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
