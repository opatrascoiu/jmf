/**
 *
 *  Class BookCategory$Factory$Class.java
 *
 *  Generated by KMFStudio at 29 June 2003 12:59:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package library;

import library.repository.*;

public class BookCategory$Factory$Class
	extends LibraryFactory$Class
	implements BookCategory$Factory
{
	/** Default factory constructor */
	public BookCategory$Factory$Class() {
	}
	public BookCategory$Factory$Class(LibraryRepository repository) {
		this.repository = repository;
	}

	/** Default build method */
	public Object build() {
		BookCategory obj = new BookCategory$Class();
		repository.addElement("library.BookCategory", obj);
		return obj;
	}

	/** Override toString method */
	public String toString() {
		return "BookCategory_Factory";
	}
}
