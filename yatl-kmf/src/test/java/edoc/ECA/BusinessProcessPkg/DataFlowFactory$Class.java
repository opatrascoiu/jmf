/**
 *
 *  Class DataFlowFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:37
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.BusinessProcessPkg;

public class DataFlowFactory$Class
	extends edoc.EdocFactory$Class
	implements DataFlowFactory
{
	/** Default factory constructor */
	public DataFlowFactory$Class() {
	}
	public DataFlowFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		DataFlow obj = new DataFlow$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.BusinessProcessPkg.DataFlow", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "DataFlowFactory";
	}

	/** Accept 'edoc.ECA.BusinessProcessPkg.DataFlowVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
