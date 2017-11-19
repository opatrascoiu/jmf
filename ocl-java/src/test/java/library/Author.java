package library;

public class Author {
	public Author() {
		super();
	}

	String _name = null;
	public String getName() {return _name;}
	public void setName(String name) {_name = name;	}
	
	public String toString() {
		return "Author '"+_name+"'";
	}
}
