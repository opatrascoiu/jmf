package test;

import java.io.*;
import java.util.*;

import uk.ac.kent.cs.kmf.util.*;

/**
 * @author dha
 *
 */

public class EvaluateFromFile {
	public static void main(String[] args) {
		args = new String[] {"src/test/scripts/booleans.ocl"};

		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		library.Library l=new library.Library();
		Ocl4Java.InitModel("library", new OutputStreamLog(System.out));
		// Read the files and evaluate each constraint
		try {
			for(int i=0; i<args.length; i++) {
				System.out.println("Reading File - " + args[i]);
				FileReader fin = new FileReader(args[i]);
				BufferedReader bin = new BufferedReader(fin);
				FileWriter fout = new FileWriter(args[i]+"_result.txt");
				PrintWriter out = new PrintWriter(fout,true);
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
								// Print debug info
								System.out.println("result of '" + inv +"'");
								out.println("result of '" + inv +"'");
								o = Ocl4Java.processor.evaluate(inv, null, log);
							} catch (Exception e) {
								o = "[Error]";
								if (Ocl4Java.processor.getDebug().booleanValue()) e.printStackTrace();
							}
							if (o == null) o = "[Error]";
							if (o instanceof List) {
								Iterator j = ((List)o).iterator();
								while (j.hasNext()) {
									Object obj = "["+j.next()+"]";
									// Print result both to console and reference
									System.out.println(obj);
									out.println(obj);
								}	
							} else {
								System.out.println(o);
								out.println(o);
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
			if (Ocl4Java.processor.getDebug().booleanValue()) e.printStackTrace();
		}
		System.out.println("Finished.");
	}
}
