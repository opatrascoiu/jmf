/**
 *
 *  Class EventBasedProcessFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public class EventBasedProcessFactory$Class
	extends edoc.EdocFactory$Class
	implements EventBasedProcessFactory
{
	/** Default factory constructor */
	public EventBasedProcessFactory$Class() {
	}
	public EventBasedProcessFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		EventBasedProcess obj = new EventBasedProcess$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.Event.EventBasedProcess", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "EventBasedProcessFactory";
	}

	/** Accept 'edoc.ECA.Event.EventBasedProcessVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
