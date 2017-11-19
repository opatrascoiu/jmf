package test;

import java.io.*;
import java.util.*;

import uk.ac.kent.cs.kmf.util.*;

/**
 * @author dha
 *
 */
public class EvaluateFromFiles {
	public static void main(String[] args) {
		args = new String[] {
			"src/test/scripts/booleans.ocl",
			"src/test/scripts/numbers.ocl",
			"src/test/scripts/strings.ocl",
			"src/test/scripts/bags.ocl",
			"src/test/scripts/sets.ocl",
			"src/test/scripts/sequences.ocl",
			"src/test/scripts/orderedSets.ocl"
		};

		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		library.Library l=new library.Library();
		Ocl4Java.InitModel("library", new OutputStreamLog(System.out));
		// Scan every file and evaluate constraints
		try {
			// Initialize a reference for all
			FileWriter fall = new FileWriter("src/test/scripts/all.ocl_result.txt");
			PrintWriter oall = new PrintWriter(fall, true);		 
			for(int i=0; i<args.length; i++) {
				System.out.println("Reading File - " + args[i]);
				FileReader fin = new FileReader(args[i]);
				BufferedReader bin = new BufferedReader(fin);
				FileWriter fout = new FileWriter(args[i]+"_result.txt");
				PrintWriter out = new PrintWriter(fout, true);
				log.resetViolations();
				Object o = null;
				// Scan file for 'context'
				String inv = "";
				while (true) {
					// Read a line
					String line = bin.readLine();
					// EOF reached
					if (line == null) break;
					// Beginning of context
					if (line.startsWith("context ") || line.length() == 0) {
						// Evaluate previous invariant
						inv = inv.trim();
						if (inv.length() != 0) {
							try {
								// Print debug info to reference and console
								System.out.println("result of '" + inv +"'");
								out.println("result of '" + inv +"'");
								oall.println("result of '" + inv +"'");
								o = Ocl4Java.processor.evaluate(inv, null, log);
							} catch (Exception e) {
								o = "[Error]";
								if (Ocl4Java.processor.getDebug().booleanValue()) e.printStackTrace();
							}
							if (o == null) o = "[Error]";
							if (o instanceof List) {
								Iterator j = ((List)o).iterator();
								while (j.hasNext()) {
									// Print debug info to reference and console
									Object obj = "["+j.next()+"]";
									System.out.println(obj);
									out.println(obj);
									oall.println(obj);
								}	
							} else {
								//  Print debug info to reference and console
								out.println(o);
								System.out.println(o);
								oall.println(o);
							}
							inv = "";
						}
						inv += line;
					} else {
						inv += line;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finished.");
	}
}
