/**
 *
 *  Class DataTypeFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:35
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.DocumentModel;

public class DataTypeFactory$Class
	extends edoc.EdocFactory$Class
	implements DataTypeFactory
{
	/** Default factory constructor */
	public DataTypeFactory$Class() {
	}
	public DataTypeFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		DataType obj = new DataType$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.DocumentModel.DataType", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		DataType obj = new DataType$Class(name);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.DocumentModel.DataType", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "DataTypeFactory";
	}

	/** Accept 'edoc.ECA.DocumentModel.DataTypeVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}