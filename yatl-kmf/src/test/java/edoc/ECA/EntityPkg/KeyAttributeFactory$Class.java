/**
 *
 *  Class KeyAttributeFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public class KeyAttributeFactory$Class
	extends edoc.EdocFactory$Class
	implements KeyAttributeFactory
{
	/** Default factory constructor */
	public KeyAttributeFactory$Class() {
	}
	public KeyAttributeFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		KeyAttribute obj = new KeyAttribute$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.EntityPkg.KeyAttribute", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "KeyAttributeFactory";
	}

	/** Accept 'edoc.ECA.EntityPkg.KeyAttributeVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
