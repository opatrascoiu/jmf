/**
 *
 *  Class PseudoStateKindFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public class PseudoStateKindFactory$Class
	extends edoc.EdocFactory$Class
	implements PseudoStateKindFactory
{
	/** Default factory constructor */
	public PseudoStateKindFactory$Class() {
	}
	public PseudoStateKindFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		PseudoStateKind obj = new PseudoStateKind$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.CCA.PseudoStateKind", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "PseudoStateKindFactory";
	}

	/** Accept 'edoc.ECA.CCA.PseudoStateKindVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
