/**
 *
 *  Class EventBasedDataManagerFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.Event;

public class EventBasedDataManagerFactory$Class
	extends edoc.EdocFactory$Class
	implements EventBasedDataManagerFactory
{
	/** Default factory constructor */
	public EventBasedDataManagerFactory$Class() {
	}
	public EventBasedDataManagerFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		EventBasedDataManager obj = new EventBasedDataManager$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.Event.EventBasedDataManager", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, edoc.ECA.CCA.GranularityKind granularity, Boolean isPersistent, String primitiveKind, String primitiveSpec, Boolean networkAccess, Boolean shareable) {
		EventBasedDataManager obj = new EventBasedDataManager$Class(name, granularity, isPersistent, primitiveKind, primitiveSpec, networkAccess, shareable);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.Event.EventBasedDataManager", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "EventBasedDataManagerFactory";
	}

	/** Accept 'edoc.ECA.Event.EventBasedDataManagerVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}