package test;

import java.io.*;
import java.util.*;


import uk.ac.kent.cs.kmf.util.*;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public class GenerateCodeFromConsole {
	public static void main(String[] args) {
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		Ocl4Kmf.InitModel("src/library.xmi", new FileLog("init.log"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			//--- Read input line ---
			System.out.println("Enter an OCL constraint:");
			String str = br.readLine();
			//--- Generate code for input string ---
			List res = Ocl4Kmf.processor.generate(str, "", log);
			//--- Output results ---
			Iterator i = res.iterator();
			while (i.hasNext()) {
				Object obj = i.next();
				Pair pair = (Pair)obj;
				System.out.println("Code: ");
				System.out.print(pair.getSecond());				
				System.out.println("Result in: ");
				System.out.println("\t"+pair.getFirst());
			}
		} catch (Exception e) {
			if (Ocl4Kmf.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}
}
