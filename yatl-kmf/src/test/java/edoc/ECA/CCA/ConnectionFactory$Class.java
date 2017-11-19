/**
 *
 *  Class ConnectionFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public class ConnectionFactory$Class
	extends edoc.EdocFactory$Class
	implements ConnectionFactory
{
	/** Default factory constructor */
	public ConnectionFactory$Class() {
	}
	public ConnectionFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Connection obj = new Connection$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.CCA.Connection", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ConnectionFactory";
	}

	/** Accept 'edoc.ECA.CCA.ConnectionVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}