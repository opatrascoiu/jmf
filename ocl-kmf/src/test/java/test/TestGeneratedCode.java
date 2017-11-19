package test;

import java.io.*; 
import java.util.*;

import library.*;
import library.repository.*;


import uk.ac.kent.cs.kmf.util.*;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public class TestGeneratedCode {
	public static void main(String[] args) {
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		Ocl4Kmf.InitModel("src/library.xmi", new FileLog("init.log"));

		// Init population
		initPopulation();
		
		// Invoke generated code and output the results		
		try {
			// Open output file
			FileWriter fout = new FileWriter("src/test/scripts/invariants_result.txt");
			PrintWriter out = new PrintWriter(fout,true);
		
			// Use the generated code
			List values = test.scripts.Invariants.Library.evaluateAll(lib);
			Iterator i = values.iterator();
			while (i.hasNext()) {
				Object obj = i.next();
				out.println("["+obj+"]");
				System.out.println("["+obj+"]");
			}
			fout.close();
		} catch (Exception e) {
			if (Ocl4Kmf.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}

	static Library lib;
	
	/**
	 *  Initialise the population
	 */
	static void initPopulation() {
		LibraryRepository rep = new LibraryRepository$Class();
		lib = (Library)rep.buildElement("library.Library");
		lib.setName("My Library");

		Author a1 = (Author)rep.buildElement("library.Author");
		a1.setName("J.R.R.Tolkien");

		Author a2 = (Author)rep.buildElement("library.Author");;
		a2.setName("Humphrey Carpenter");

		Book b1 = (Book)rep.buildElement("library.Book");
		b1.setTitle("The Lord of the Rings");
		b1.setPages(new Integer(1000));
		b1.setCategory(BookCategory$Class.ScienceFiction);
		b1.setAuthor(a1);

		Book b2 = (Book)rep.buildElement("library.Book");
		b2.setTitle("J.R.R. Tolkien: A Biography");
		b2.setPages(new Integer(200));
		b2.setCategory(BookCategory$Class.Biography);
		b2.setAuthor(a2);

		Book b3 = (Book)rep.buildElement("library.Book");
		b3.setTitle("No Author");
		b3.setPages(new Integer(10));


		lib.getWriters().add(a1);
		lib.getWriters().add(a2);

		lib.getBooks().add(b1);
		lib.getBooks().add(b2);
		lib.getBooks().add(b3);
	}
}
