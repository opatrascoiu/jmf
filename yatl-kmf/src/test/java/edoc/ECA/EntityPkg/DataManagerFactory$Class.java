/**
 *
 *  Class DataManagerFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public class DataManagerFactory$Class
	extends edoc.EdocFactory$Class
	implements DataManagerFactory
{
	/** Default factory constructor */
	public DataManagerFactory$Class() {
	}
	public DataManagerFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		DataManager obj = new DataManager$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.EntityPkg.DataManager", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, edoc.ECA.CCA.GranularityKind granularity, Boolean isPersistent, String primitiveKind, String primitiveSpec, Boolean networkAccess, Boolean shareable) {
		DataManager obj = new DataManager$Class(name, granularity, isPersistent, primitiveKind, primitiveSpec, networkAccess, shareable);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.EntityPkg.DataManager", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "DataManagerFactory";
	}

	/** Accept 'edoc.ECA.EntityPkg.DataManagerVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
