package test;

import java.io.*;

import uk.ac.kent.cs.kmf.util.*;

import library.*;
import library.repository.*;

/**
 * @author Octavian Patrascoiu
 *
 */
public class EvaluateOverModel {
	private static FileWriter fmodel;
	private static PrintWriter omodel;		 
	public static void main(String[] args) {
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		Ocl4Kmf.InitModel("src/library.xmi", new OutputStreamLog(System.out));

		// Init population
		initPopulation();

		// Initialize a reference for model
		try {
			fmodel = new FileWriter("src/test/scripts/model.ocl_result.txt");
			omodel = new PrintWriter(fmodel, true);		 
		} catch (Exception e) {
		}

		check("context library::Library inv: self.books->asSequence()->first().pages > 0");

		// Test OclAny operations
		check("context library::Library inv: self.oclIsUndefined()");
		check("context library::Library inv: self.oclIsNew()");
		check("context library::Library inv: self.oclIsKindOf(OclAny)");
		check("context library::Library inv: self.oclIsKindOf(Boolean)");
		check("context library::Library inv: self.oclIsKindOf(Real)");
		check("context library::Library inv: self.oclIsKindOf(library::Library)");
		check("context library::Library inv: self.oclIsTypeOf(OclAny)");
		check("context library::Library inv: self.oclIsTypeOf(Boolean)");
		check("context library::Library inv: self.oclIsTypeOf(Real)");
		check("context library::Library inv: self.oclIsTypeOf(library::Library)");
		check("context library::Library inv: self.oclAsType(OclAny)");
		check("context library::Library inv: self.oclAsType(Boolean)");
		check("context library::Library inv: self.oclAsType(Real)");
		check("context library::Library inv: self.oclAsType(library::Library)");
		check("context library::Library inv: self.allInstances()");
		check("context library::Library inv: self.books->asSequence()->first().category");
		check("context library::Library inv: self.books->asSequence()->first().category->isEmpty()");
		check("context library::Library inv: self.books->asSequence()->first().category.oclIsUndefined()");

		// Test navigations over the model 
		check("context library::Library inv: self");
		check("context library::Library inv: self.books");
		check("context library::Library inv: self.books->size()");
		check("context library::Library inv: self.books->size() > 100");

		check("context library::Library inv: self.books->asSequence()->first()");
		check("context library::Library inv: self.books->asSequence()->first().title");
		check("context library::Library inv: self.books->asSequence()->first().author");
		check("context library::Library inv: self.books->asSequence()->first().author.name");
		check("context library::Library inv: self.books->asSequence()->first().category");
		check("context library::Library inv: self.books->asSequence()->first().calculatePrice()");

		// With type analysis
		Ocl4Kmf.processor.setDebug(Boolean.TRUE);
		check("context library::Library inv: self.books->size() > 100");
		Ocl4Kmf.processor.setDebug(Boolean.FALSE);

		// Test Enumeration Literals
		check("context library::Library inv: BookCategory::ScienceFiction");
		check("context library::Library inv: BookCategory::Biography");
		check("context library::Library inv: BookCategory::ScienceFiction = BookCategory::ScienceFiction");
		check("context library::Library inv: BookCategory::ScienceFiction = BookCategory::Biography");
		check("context library::Library inv: BookCategory::ScienceFiction <> BookCategory::ScienceFiction");
		check("context library::Library inv: BookCategory::ScienceFiction <> BookCategory::Biography");
		
		// Test let assignments
		check("context library::Library inv: let book:Book = self.books->asSequence()->first() in book");
		check("context library::Library inv:\n"+
				"  let book:Book = self.books->asSequence()->first(),\n"+
		        "      title:String = book.title\n"+
		        "  in title");
		check("context library::Library inv:\n"+
				"  let book:Book = self.books->asSequence()->first(),\n"+
				"      category:String = book.category\n"+
				"  in category");

		// Test comparison between Enum Literal and model property
		check("context library::Library inv:\n"+
			    "  let book:Book = self.books->asSequence()->first(),\n"+
			    "      category:BookCategory = book.category\n"+
			    "  in Tuple{ category:BookCategory = category,\n"+
			    "            comparison:Boolean = (category = BookCategory::Biography)\n"+
			    "          }");

		// Test comparison between model elements
		check("context library::Library inv:\n"+
				"  self.books->asSequence()->first() = self.books->asSequence()->last()"
			  );

		check("context library::Library inv:\n"+
				"  let b1:Book = self.books->asSequence()->first(),\n"+
				"      b2:Book = self.books->asSequence()->last()\n"+
				"  in b1 = b2"
			  );
						
		check("context library::Library inv:\n"+
				"  let b1:Book = self.books->asSequence()->first(),\n"+
				"      b2:Book = self.books->asSequence()->first()\n"+
				"  in b1 = b2"
				);

		// Test multiple invariants.
		check("context library::Library\n"+
				" inv: self.books->size() > 100\n"+
				" inv: self.books->asSequence()->first().category = BookCategory::Biography"
			 );     

		// Test nulls in model
		check("context library::Library inv:\n"+
				"  let book = self.books->any(b|b.title='No Author')\n"+
				"  in book->isEmpty()"
				);		
		check("context library::Library inv:\n"+
				"  let book = self.books->any(b|b.title='No Author')\n"+
				"  in book"
				);
		
		check("context library::Library inv:\n"+
				"  let book = self.books->any(b|b.title='No Author')\n"+
				"  in book.author"
				);

		check("context library::Library inv:\n"+
				"  let book = self.books->any(b|b.title='No Author')\n"+
				"  in book.author.name"
				);

		check("context library::Library inv:\n"+
				"  let b1 = self.books->any(b|b.title='No Author'),\n"+
				"      b2 = self.books->asSequence()->last()\n"+
				"  in b1.author.name = b2.author.name"
				);

	}

	/** Initialize the model */
	static Library lib;
	
	/**
	 *  Evaluate an OCL expression
	 * 
	 * */ 
	static void check(String expr) {
		System.out.println("result of '" + expr +"'");
		omodel.println("result of '" + expr +"'");
		Object o = Ocl4Kmf.processor.evaluate(expr, lib);
		if (o == null) o = "[undefined]";
		System.out.println("is " + o);
		System.out.println();
		omodel.println("is " + o);
		omodel.println();
	}

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
