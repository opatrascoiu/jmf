/**
 *
 *  Class ElementImportFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:35
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.ModelManagement;

public class ElementImportFactory$Class
	extends edoc.EdocFactory$Class
	implements ElementImportFactory
{
	/** Default factory constructor */
	public ElementImportFactory$Class() {
	}
	public ElementImportFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		ElementImport obj = new ElementImport$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.ModelManagement.ElementImport", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		ElementImport obj = new ElementImport$Class(name);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.ModelManagement.ElementImport", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ElementImportFactory";
	}

	/** Accept 'edoc.ECA.ModelManagement.ElementImportVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
