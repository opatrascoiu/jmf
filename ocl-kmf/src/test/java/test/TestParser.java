package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import uk.ac.kent.cs.ocl20.syntax.ast.contexts.*;
import uk.ac.kent.cs.kmf.util.*;

/**
 * @author dha
 *
 */
public class TestParser {
	public static void main(String[] args) {
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		Ocl4Kmf.InitModel("src/library.xmi", new FileLog("init.log"));

		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		try {
			System.out.println( "Enter an OCL expression :");
			String str = br.readLine();
			PackageDeclarationAS pd = Ocl4Kmf.processor.parse(str, log);
			if (!log.hasViolations()) {
				System.out.println("Finished - 0 error(s) 0 warning(s)");
			}
		} catch (Exception e) {
			if (Ocl4Kmf.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}
}
