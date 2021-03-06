/**
 *
 *  Class ProcessFlowPortFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public class ProcessFlowPortFactory$Class
	extends edoc.EdocFactory$Class
	implements ProcessFlowPortFactory
{
	/** Default factory constructor */
	public ProcessFlowPortFactory$Class() {
	}
	public ProcessFlowPortFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		ProcessFlowPort obj = new ProcessFlowPort$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.BusinessProcessPkg.ProcessFlowPort", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, Boolean isSynchronous, Boolean isTransactional, edoc.ECA.CCA.DirectionType direction, edoc.ECA.CCA.Status postCondition, Integer multiplicity_lb, Integer multiplicity_ub) {
		ProcessFlowPort obj = new ProcessFlowPort$Class(name, isSynchronous, isTransactional, direction, postCondition, multiplicity_lb, multiplicity_ub);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.BusinessProcessPkg.ProcessFlowPort", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "ProcessFlowPortFactory";
	}

	/** Accept 'edoc.ECA.BusinessProcessPkg.ProcessFlowPortVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
