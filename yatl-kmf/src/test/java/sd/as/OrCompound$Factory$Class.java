/**
 *
 *  Class OrCompound$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:01:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public class OrCompound$Factory$Class
	extends sd.SdFactory$Class
	implements OrCompound$Factory
{
	/** Default factory constructor */
	public OrCompound$Factory$Class() {
	}
	public OrCompound$Factory$Class(sd.repository.SdRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		OrCompound obj = new OrCompound$Class();
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.OrCompound", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		OrCompound obj = new OrCompound$Class(name);
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.OrCompound", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "OrCompound_Factory";
	}

	/** Accept 'sd.as.OrCompound$Visitor' */
	public Object accept(sd.SdVisitor v, Object data) {
		return v.visit(this, data);
	}
}
