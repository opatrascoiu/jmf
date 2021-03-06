/**
 *
 *  Class Library$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 June 2003 12:59:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package library;

import library.repository.*;

public class Library$Factory$Class
	extends LibraryFactory$Class
	implements Library$Factory
{
	/** Default factory constructor */
	public Library$Factory$Class() {
	}
	public Library$Factory$Class(LibraryRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		Library obj = new Library$Class();
		repository.addElement("library.Library", obj);
		return obj;
	}
	/** Specialized build method */
	public Object build(String name) {
		Library obj = new Library$Class(name);
		repository.addElement("library.Library", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "Library_Factory";
	}
}
