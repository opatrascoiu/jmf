/**
 *
 *  Class ProcessPortConnectorFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public class ProcessPortConnectorFactory$Class
	extends edoc.EdocFactory$Class
	implements ProcessPortConnectorFactory
{
	/** Default factory constructor */
	public ProcessPortConnectorFactory$Class() {
	}
	public ProcessPortConnectorFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		ProcessPortConnector obj = new ProcessPortConnector$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.BusinessProcessPkg.ProcessPortConnector", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		ProcessPortConnector obj = new ProcessPortConnector$Class(name);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.BusinessProcessPkg.ProcessPortConnector", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ProcessPortConnectorFactory";
	}

	/** Accept 'edoc.ECA.BusinessProcessPkg.ProcessPortConnectorVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
