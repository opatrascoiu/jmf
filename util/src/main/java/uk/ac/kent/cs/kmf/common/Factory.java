/**
 * @author Octavian Patrascoiu
 *
 **/

package uk.ac.kent.cs.kmf.common;

public interface Factory {
	/** Build an object */
	public Object build();

	/** Get the repository */
	public Repository getRepository();
	/** Set the repository */
	public void setRepository(Repository repository);

	/** Override toString */
	public String toString();
}
