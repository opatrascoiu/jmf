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
public abstract class BookCategory implements java.util.Enumeration {

	private BookCategory() {
	}

	public static BookCategory ScienceFiction = new BookCategory() {
		public boolean hasMoreElements() {
			return true;
		}
		public Object nextElement() {
			return BookCategory.Biography;
		}
		public String toString() {
			return "BookCategory::ScienceFiction";
		}
	};

	public static BookCategory Biography = new BookCategory() {
		public boolean hasMoreElements() {
			return true;
		}
		public Object nextElement() {
			return null;
		}
		public String toString() {
			return "BookCategory::Biography";
		}
	};

	public static BookCategory Mystery = new BookCategory() {
		public boolean hasMoreElements() {
			return true;
		}
		public Object nextElement() {
			return null;
		}
		public String toString() {
			return "BookCategory::Mystery";
		}
	};
}
