package test;

import java.io.*;
import java.util.*;

import uk.ac.kent.cs.kmf.util.*;

import library.*;
import library.impl.*;
import org.eclipse.emf.ecore.*;

/**
 * @author dha
 *
 */
public class EvaluateFromConsole {
	public static void main(String[] args) {
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		LibraryPackageImpl.init();
		Map registry = EPackage.Registry.INSTANCE;
		String enterpiseURI = LibraryPackage.eNS_URI;
		EPackage model = (EPackage)registry.get(enterpiseURI);
		Ocl4Emf.InitModel(model, log);

		// Read constraint and evaluate
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter an OCL constraint:");
			String str = br.readLine();
			Object o = Ocl4Emf.processor.evaluate(str);
			System.out.println(o);
		} catch (Exception e) {
			if (Ocl4Emf.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}
}
