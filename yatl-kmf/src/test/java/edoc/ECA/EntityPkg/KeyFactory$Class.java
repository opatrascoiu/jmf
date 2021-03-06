/**
 *
 *  Class KeyFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public class KeyFactory$Class
	extends edoc.EdocFactory$Class
	implements KeyFactory
{
	/** Default factory constructor */
	public KeyFactory$Class() {
	}
	public KeyFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Key obj = new Key$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.EntityPkg.Key", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, Boolean primaryKey) {
		Key obj = new Key$Class(name, primaryKey);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.EntityPkg.Key", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "KeyFactory";
	}

	/** Accept 'edoc.ECA.EntityPkg.KeyVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
