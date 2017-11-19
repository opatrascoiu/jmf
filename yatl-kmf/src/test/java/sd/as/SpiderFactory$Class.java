/**
 *
 *  Class SpiderFactory$Class.java
 *
 *  Generated by KMFStudio at 18 February 2004 20:38:45
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package sd.as;

public class SpiderFactory$Class
	extends sd.SdFactory$Class
	implements SpiderFactory
{
	/** Default factory constructor */
	public SpiderFactory$Class() {
	}
	public SpiderFactory$Class(sd.repository.SdRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Spider obj = new Spider$Class();
		obj.setId(sd.SdFactory$Class.newId());
		repository.addElement("sd.as.Spider", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "SpiderFactory";
	}

	/** Accept 'sd.as.SpiderVisitor' */
	public Object accept(sd.SdVisitor v, Object data) {
		return v.visit(this, data);
	}
}
