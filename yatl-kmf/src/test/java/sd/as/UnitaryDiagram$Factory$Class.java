/**
 *
 *  Class UnitaryDiagram$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:01:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public class UnitaryDiagram$Factory$Class
	extends sd.SdFactory$Class
	implements UnitaryDiagram$Factory
{
	/** Default factory constructor */
	public UnitaryDiagram$Factory$Class() {
	}
	public UnitaryDiagram$Factory$Class(sd.repository.SdRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		UnitaryDiagram obj = new UnitaryDiagram$Class();
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.UnitaryDiagram", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		UnitaryDiagram obj = new UnitaryDiagram$Class(name);
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.UnitaryDiagram", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "UnitaryDiagram_Factory";
	}

	/** Accept 'sd.as.UnitaryDiagram$Visitor' */
	public Object accept(sd.SdVisitor v, Object data) {
		return v.visit(this, data);
	}
}
