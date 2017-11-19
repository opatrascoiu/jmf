package test;

import java.io.*;
import java.util.*;

import library.*;
import library.impl.LibraryPackageImpl;
import org.eclipse.emf.ecore.EPackage;

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
		LibraryPackageImpl.init();
		Map registry = EPackage.Registry.INSTANCE;
		String enterpiseURI = LibraryPackage.eNS_URI;
		EPackage model = (EPackage)registry.get(enterpiseURI);
		Ocl4Emf.InitModel(model, log);

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
			if (Ocl4Emf.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}

	static Library lib;
	
	/**
	 *  Initialise the population
	 */
	static void initPopulation() {
		LibraryPackageImpl.init();

		Map registry = EPackage.Registry.INSTANCE;
		String enterpiseURI = LibraryPackage.eNS_URI;
		LibraryPackage libraryPackage = (LibraryPackage) registry.get(enterpiseURI);
		LibraryFactory libraryFactory = libraryPackage.getLibraryFactory();

		lib = libraryFactory.createLibrary();
		lib.setName("My Library");

		Author a1 = libraryFactory.createAuthor();
		a1.setName("J.R.R.Tolkien");

		Author a2 = libraryFactory.createAuthor();
		a2.setName("Humphrey Carpenter");

		Book b1 = libraryFactory.createBook();
		b1.setTitle("The Lord of the Rings");
		b1.setPages(1000);
		b1.setCategory(BookCategory.SCIENCE_FICTION_LITERAL);
		b1.setAuthor(a1);

		Book b2 = libraryFactory.createBook();
		b2.setTitle("J.R.R. Tolkien: A Biography");
		b2.setPages(200);
		b2.setCategory(BookCategory.BIOGRAPHY_LITERAL);
		b2.setAuthor(a2);

		Book b3 = libraryFactory.createBook();
		b3.setTitle("No Author");
		b3.setPages(10);

		lib.getWriters().add(a1);
		lib.getWriters().add(a2);

		lib.getBooks().add(b1);
		lib.getBooks().add(b2);
		lib.getBooks().add(b3);
	}	
}
