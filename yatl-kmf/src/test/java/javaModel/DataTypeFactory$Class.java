/**
 *
 *  Class DataTypeFactory$Class.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel;

public class DataTypeFactory$Class
	extends javaModel.JavaModelFactory$Class
	implements DataTypeFactory
{
	/** Default factory constructor */
	public DataTypeFactory$Class() {
	}
	public DataTypeFactory$Class(javaModel.repository.JavaModelRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		DataType obj = new DataType$Class();
		obj.setId(javaModel.JavaModelFactory$Class.newId());
		repository.addElement("javaModel.DataType", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name, javaModel.DataKind kind) {
		DataType obj = new DataType$Class(name, kind);
		obj.setId(javaModel.JavaModelFactory$Class.newId());
		repository.addElement("javaModel.DataType", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "DataTypeFactory";
	}

	/** Accept 'javaModel.DataTypeVisitor' */
	public Object accept(javaModel.JavaModelVisitor v, Object data) {
		return v.visit(this, data);
	}
}