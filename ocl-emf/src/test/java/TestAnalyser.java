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
public class TestAnalyser {
	public static void main(String[] args) {
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		LibraryPackageImpl.init();
		Map registry = EPackage.Registry.INSTANCE;
		String enterpiseURI = LibraryPackage.eNS_URI;
		EPackage model = (EPackage)registry.get(enterpiseURI);
		Ocl4Emf.InitModel(model, log);
		
		// Input file
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			// Read ocl string
			System.out.println("Enter an OCL expression :");
			String str = br.readLine();
			// Set debug on
 			boolean temp = Ocl4Emf.processor.getDebug().booleanValue(); 
			Ocl4Emf.processor.setDebug(new Boolean(true));
			// Analyse the ocl string
			List cs = Ocl4Emf.processor.analyse(str);			
			// Restore debug
			Ocl4Emf.processor.setDebug(new Boolean(temp));
		} catch (Exception e) {
			if (Ocl4Emf.processor.getDebug().booleanValue()) e.printStackTrace();
		}
	}
}
