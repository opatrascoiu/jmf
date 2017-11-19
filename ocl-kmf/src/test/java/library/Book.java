/**
 *
 *  Class Book.java
 *
 *  Generated by KMFStudio at 29 June 2003 12:59:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package library;

public interface Book
extends
    LibraryElement
{

	/** Get the 'title' from 'Book' */
	public String getTitle();
	/** Set the 'title' from 'Book' */
	public void setTitle(String title);

	/** Get the 'pages' from 'Book' */
	public Integer getPages();
	/** Set the 'pages' from 'Book' */
	public void setPages(Integer pages);

	/** Get the 'category' from 'Book' */
	public BookCategory getCategory();
	/** Set the 'category' from 'Book' */
	public void setCategory(BookCategory category);

	/** Get the 'author' from 'Book' */
	public Author getAuthor();
	/** Set the 'author' from 'Book' */
	public void setAuthor(Author author);

	/** Get the 'library' from 'Book' */
	public Library getLibrary();
	/** Set the 'library' from 'Book' */
	public void setLibrary(Library library);

	/** Operation  'calculatePrice' from 'Book' */
	public Double calculatePrice();

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
