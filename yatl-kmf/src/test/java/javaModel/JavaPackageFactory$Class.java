/**
 *
 *  Class JavaPackageFactory$Class.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel;

public class JavaPackageFactory$Class
	extends javaModel.JavaModelFactory$Class
	implements JavaPackageFactory
{
	/** Default factory constructor */
	public JavaPackageFactory$Class() {
	}
	public JavaPackageFactory$Class(javaModel.repository.JavaModelRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		JavaPackage obj = new JavaPackage$Class();
		obj.setId(javaModel.JavaModelFactory$Class.newId());
		repository.addElement("javaModel.JavaPackage", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		JavaPackage obj = new JavaPackage$Class(name);
		obj.setId(javaModel.JavaModelFactory$Class.newId());
		repository.addElement("javaModel.JavaPackage", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "JavaPackageFactory";
	}

	/** Accept 'javaModel.JavaPackageVisitor' */
	public Object accept(javaModel.JavaModelVisitor v, Object data) {
		return v.visit(this, data);
	}
}
