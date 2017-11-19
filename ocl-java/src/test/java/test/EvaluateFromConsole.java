package test;

import java.io.*;

import uk.ac.kent.cs.kmf.util.*;

/**
 * @author dha
 *
 */
public class EvaluateFromConsole {
	public static void main(String[] args) {
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		library.Library l=new library.Library();
		Ocl4Java.InitModel("library", new OutputStreamLog(System.out));
		Ocl4Java.processor.setDebug(Boolean.TRUE);

		// Read constraint and evaluate
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter an OCL constraint:");
			String str = br.readLine();
			Object o = Ocl4Java.processor.evaluate(str);
			System.out.println(o);
		} catch (Exception e) {
			if (Ocl4Java.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}
}
