/**
 *
 *  Class CompositeDataFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:35
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.DocumentModel;

public class CompositeDataFactory$Class
	extends edoc.EdocFactory$Class
	implements CompositeDataFactory
{
	/** Default factory constructor */
	public CompositeDataFactory$Class() {
	}
	public CompositeDataFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		CompositeData obj = new CompositeData$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.DocumentModel.CompositeData", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		CompositeData obj = new CompositeData$Class(name);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.DocumentModel.CompositeData", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "CompositeDataFactory";
	}

	/** Accept 'edoc.ECA.DocumentModel.CompositeDataVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
