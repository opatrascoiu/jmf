/**
 *
 *  Class Zone$Factory$Class.java
 *
 *  Generated by KMFStudio at 25 November 2003 13:01:02
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public class Zone$Factory$Class
	extends sd.SdFactory$Class
	implements Zone$Factory
{
	/** Default factory constructor */
	public Zone$Factory$Class() {
	}
	public Zone$Factory$Class(sd.repository.SdRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Zone obj = new Zone$Class();
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.Zone", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Zone_Factory";
	}

	/** Accept 'sd.as.Zone$Visitor' */
	public Object accept(sd.SdVisitor v, Object data) {
		return v.visit(this, data);
	}
}
