/**
 *
 *  Class FalseDiagram$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:01:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public class FalseDiagram$Factory$Class
	extends sd.SdFactory$Class
	implements FalseDiagram$Factory
{
	/** Default factory constructor */
	public FalseDiagram$Factory$Class() {
	}
	public FalseDiagram$Factory$Class(sd.repository.SdRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		FalseDiagram obj = new FalseDiagram$Class();
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.FalseDiagram", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		FalseDiagram obj = new FalseDiagram$Class(name);
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.FalseDiagram", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "FalseDiagram_Factory";
	}

	/** Accept 'sd.as.FalseDiagram$Visitor' */
	public Object accept(sd.SdVisitor v, Object data) {
		return v.visit(this, data);
	}
}
