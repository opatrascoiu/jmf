/**
 *
 *  Class EntityRole.java
 *
 *  Generated by KMFStudio at 09 March 2004 11:42:36
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package edoc.ECA.EntityPkg;

public interface EntityRole
extends
    edoc.EdocElement
{

	/** Get the 'virtualEntity' from 'EntityRole' */
	public Boolean getVirtualEntity();
	/** Set the 'virtualEntity' from 'EntityRole' */
	public void setVirtualEntity(Boolean virtualEntity);

	/** Get the 'parent' from 'EntityRole' */
	public Entity getParent();
	/** Set the 'parent' from 'EntityRole' */
	public void setParent(Entity parent);

	/** Get the 'roleContext' from 'EntityRole' */
	public Entity getRoleContext();
	/** Set the 'roleContext' from 'EntityRole' */
	public void setRoleContext(Entity roleContext);

	/** Get the id */
	public String getId();
	/** Set the id */
	public void setId(String id);

	/** Override the toString */
	public String toString();

	/** Delete the object */
	public void delete();

	/** Clone the object */
	public Object clone();
}