/**
 *
 *  Class BookCategory$Class.java
 *
 *  Generated by KMFStudio at 29 June 2003 12:59:43
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package library;

public class BookCategory$Class
implements
    BookCategory
{
	/** The 'Mystery' enumerator */
	public static final BookCategory Mystery = new BookCategory$Class();
	/** The 'ScienceFiction' enumerator */
	public static final BookCategory ScienceFiction = new BookCategory$Class();
	/** The 'Biography' enumerator */
	public static final BookCategory Biography = new BookCategory$Class();

	/** Default constructors */
	public BookCategory$Class() {
	}

	/** Overrride toString */
	public String toString() {
		String res = "BookCategory";
		if (this == Mystery) res += "::Mystery";
		if (this == ScienceFiction) res += "::ScienceFiction";
		if (this == Biography) res += "::Biography";
		return res;
	}
	/** Clone the object */
	public Object clone() {
		BookCategory$Class obj = new BookCategory$Class();
		return obj;
	}
}