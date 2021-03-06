/**
 *
 *  Class AndCompoundFactory$Class.java
 *
 *  Generated by KMFStudio at 18 February 2004 20:38:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public class AndCompoundFactory$Class
	extends sd.SdFactory$Class
	implements AndCompoundFactory
{
	/** Default factory constructor */
	public AndCompoundFactory$Class() {
	}
	public AndCompoundFactory$Class(sd.repository.SdRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		AndCompound obj = new AndCompound$Class();
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.AndCompound", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		AndCompound obj = new AndCompound$Class(name);
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.AndCompound", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "AndCompoundFactory";
	}

	/** Accept 'sd.as.AndCompoundVisitor' */
	public Object accept(sd.SdVisitor v, Object data) {
		return v.visit(this, data);
	}
}
