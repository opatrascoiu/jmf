/**
 *
 *  Class JavaClassifierFactory$Class.java
 *
 *  Generated by KMFStudio at 22 February 2004 15:13:03
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package javaModel;

public class JavaClassifierFactory$Class
	extends javaModel.JavaModelFactory$Class
	implements JavaClassifierFactory
{
	/** Default factory constructor */
	public JavaClassifierFactory$Class() {
	}
	public JavaClassifierFactory$Class(javaModel.repository.JavaModelRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		JavaClassifier obj = new JavaClassifier$Class();
		obj.setId(javaModel.JavaModelFactory$Class.newId());
		repository.addElement("javaModel.JavaClassifier", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		JavaClassifier obj = new JavaClassifier$Class(name);
		obj.setId(javaModel.JavaModelFactory$Class.newId());
		repository.addElement("javaModel.JavaClassifier", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "JavaClassifierFactory";
	}

	/** Accept 'javaModel.JavaClassifierVisitor' */
	public Object accept(javaModel.JavaModelVisitor v, Object data) {
		return v.visit(this, data);
	}
}
