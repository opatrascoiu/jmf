/**
 *
 *  Class Author.java
 *
 *  Generated by KMFStudio at 29 June 2003 12:59:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package library;

import java.util.*;

public interface Author
extends
    LibraryElement
{

	/** Get the 'name' from 'Author' */
	public String getName();
	/** Set the 'name' from 'Author' */
	public void setName(String name);

	/** Get the 'books' from 'Author' */
	public Set getBooks();
	/** Set the 'books' from 'Author' */
	public void setBooks(Set books);

	/** Get the 'library' from 'Author' */
	public Library getLibrary();
	/** Set the 'library' from 'Author' */
	public void setLibrary(Library library);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
