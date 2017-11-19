/*
 * Created on 06-Jun-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package library;

/**
 * @author dha
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Book {
	public Book() {
	}

	String _title = null;
	public String getTitle() {return _title;}
	public void setTitle(String title) {_title = title;	}
	
	Author _author = null;
	public Author getAuthor() {return _author;}
	public void setAuthor(Author author) {_author = author;	}
	
	int _pages = -1;
	public int getPages() {return _pages;}
	public void setPages(int pages) {_pages = pages;	}

	BookCategory _category = null;
	public BookCategory getCategory() {return _category;}
	public void setCategory(BookCategory category) {_category = category;	}
	
	public String toString() {
		return "Book '"+_title+"'";
	}
}
