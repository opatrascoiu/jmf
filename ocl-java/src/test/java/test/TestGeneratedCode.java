package test;

import java.io.*;
import java.util.*;

import library.*;


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
		library.Library l=new library.Library();
		Ocl4Java.InitModel("library", new OutputStreamLog(System.out));

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
			if (Ocl4Java.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}

	static Library lib;
	
	/**
	 *  Initialise the population
	 */
	static void initPopulation() {
		lib = new Library();
		lib.setName("My Library");

		Author a1 = new Author();
		a1.setName("J.R.R.Tolkien");
		lib.getWriters().add(a1);

		Author a2 = new Author();
		a2.setName("Humphrey Carpenter");
		lib.getWriters().add(a2);

		Book b1 = new Book();
		b1.setTitle("The Lord of the Rings");
		b1.setPages(1000);
		b1.setCategory(BookCategory.ScienceFiction);
		b1.setAuthor(a1);

		Book b2 = new Book();
		b2.setTitle("J.R.R. Tolkien: A Biography");
		b2.setPages(200);
		b2.setCategory(BookCategory.Biography);
		b2.setAuthor(a2);

		Book b3 = new Book();
		b3.setTitle("No Author");
		b3.setPages(10);

		lib.getWriters().add(a1);
		lib.getWriters().add(a2);

		lib.getBooks().add(b1);
		lib.getBooks().add(b2);
		lib.getBooks().add(b3);
	}
}
