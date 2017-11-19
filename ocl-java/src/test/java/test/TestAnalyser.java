package test;

import java.io.*;
import java.util.*;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;

/**
 * @author dha
 *
 */
public class TestAnalyser {
	public static void main(String[] args) {
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		library.Library l=new library.Library();
		Ocl4Java.InitModel("library", new OutputStreamLog(System.out));

		// Input file
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			// Read ocl string
			System.out.println("Enter an OCL expression :");
			String str = br.readLine();
			// Set debug on
 			boolean temp = Ocl4Java.processor.getDebug().booleanValue(); 
			Ocl4Java.processor.setDebug(new Boolean(true));
			// Analyse the ocl string
			List cs = Ocl4Java.processor.analyse(str);			
			// Restore debug
			Ocl4Java.processor.setDebug(new Boolean(temp));
		} catch (Exception e) {
			if (Ocl4Java.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}
}
