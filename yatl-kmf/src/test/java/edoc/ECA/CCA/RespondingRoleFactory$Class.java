/**
 *
 *  Class RespondingRoleFactory$Class.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.CCA;

public class RespondingRoleFactory$Class
	extends edoc.EdocFactory$Class
	implements RespondingRoleFactory
{
	/** Default factory constructor */
	public RespondingRoleFactory$Class() {
	}
	public RespondingRoleFactory$Class(edoc.repository.EdocRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		RespondingRole obj = new RespondingRole$Class();
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.CCA.RespondingRole", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		RespondingRole obj = new RespondingRole$Class(name);
		obj.setId(edoc.EdocFactory$Class.newId());
		repository.addElement("edoc.ECA.CCA.RespondingRole", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "RespondingRoleFactory";
	}

	/** Accept 'edoc.ECA.CCA.RespondingRoleVisitor' */
	public Object accept(edoc.EdocVisitor v, Object data) {
		return v.visit(this, data);
	}
}
