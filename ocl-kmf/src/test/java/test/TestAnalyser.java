package test;

import java.io.*;
import java.util.*;

import uk.ac.kent.cs.kmf.util.*;

/**
 * @author dha
 *
 */
public class TestAnalyser {
	public static void main(String[] args) {
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		Ocl4Kmf.InitModel("src/library.xmi", new FileLog("init.log"));

		// Input file
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			// Read ocl string
			System.out.println("Enter an OCL expression :");
			String str = br.readLine();
			// Set debug on
 			boolean temp = Ocl4Kmf.processor.getDebug().booleanValue(); 
			Ocl4Kmf.processor.setDebug(new Boolean(true));
			// Analyse the ocl string
			List cs = Ocl4Kmf.processor.analyse(str);			
			// Restore debug
			Ocl4Kmf.processor.setDebug(new Boolean(temp));
		} catch (Exception e) {
			if (Ocl4Kmf.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}
}
