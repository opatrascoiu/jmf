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
		Ocl4Kmf.InitModel("src/library.xmi", new FileLog("init.log"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter an OCL constraint:");
			String str = br.readLine();
			Object o = Ocl4Kmf.processor.evaluate(str);
			System.out.println(o);
		} catch (Exception e) {
			if (Ocl4Kmf.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}
}
