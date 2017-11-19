/**
 *
 *  Class InputGroupFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public class InputGroupFactory$Class
	extends edoc.EdocFactory$Class
	implements InputGroupFactory
{
	/** Default factory constructor */
	public InputGroupFactory$Class() {
	}
	public InputGroupFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		InputGroup obj = new InputGroup$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.BusinessProcessPkg.InputGroup", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, Boolean isSynchronous, Boolean isTransactional, edoc.ECA.CCA.DirectionType direction, edoc.ECA.CCA.Status postCondition) {
		InputGroup obj = new InputGroup$Class(name, isSynchronous, isTransactional, direction, postCondition);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.BusinessProcessPkg.InputGroup", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "InputGroupFactory";
	}

	/** Accept 'edoc.ECA.BusinessProcessPkg.InputGroupVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}