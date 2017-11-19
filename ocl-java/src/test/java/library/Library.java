/*
 * Created on 06-Jun-2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package library;

import java.util.*;

/**
 * @author dha
 *
 */
public class Library {
	public Library() {
		super();
	}
	
	String _name = null;
	public String getName() {return _name;}
	public void setName(String name) {_name = name;	}
	
	public final Book books_elementType=null;
	Set _books = new LinkedHashSet();
	public Set getBooks() {
		return _books;
	}

	Set _writers = new LinkedHashSet();
	public Set getWriters() {
		return _writers;
	}

	public String toString() {
		return "Library '"+_name+"'";
	}
}
