/**
 * @author Octavian Patrascoiu
 *
 **/

package uk.ac.kent.cs.kmf.common;

public class FactoryImpl
	implements Factory
{
	/** Default constructor */
	public FactoryImpl() {
	}
	/** Specialized constructor */
	public FactoryImpl(Repository repository) {
		this.repository = repository;
	}
	/** Build object */
	public Object build() {
		return null;
	}

	/** The id */
	private static int objId = 0;
	/** Reset the id */
	public static void resetId() {
		objId = 0;
	}
	/** Get a new id */
	public static String newId() {
		return ""+ ++objId;
	}

	/** The repository */
	protected Repository repository;
	/** Get the repository */
	public Repository getRepository() {
		return repository;
	}
	/** Set the repository */
	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	/** Override toString */
	public String toString() {
		return "Factory";
	}
}
