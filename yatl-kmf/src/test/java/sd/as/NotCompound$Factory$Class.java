/**
 *
 *  Class NotCompound$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:01:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public class NotCompound$Factory$Class
	extends sd.SdFactory$Class
	implements NotCompound$Factory
{
	/** Default factory constructor */
	public NotCompound$Factory$Class() {
	}
	public NotCompound$Factory$Class(sd.repository.SdRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		NotCompound obj = new NotCompound$Class();
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.NotCompound", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		NotCompound obj = new NotCompound$Class(name);
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.NotCompound", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "NotCompound_Factory";
	}

	/** Accept 'sd.as.NotCompound$Visitor' */
	public Object accept(sd.SdVisitor v, Object data) {
		return v.visit(this, data);
	}
}