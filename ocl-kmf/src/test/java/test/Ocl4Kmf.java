package test;

import uk.ac.kent.cs.kmf.util.*;
import uk.ac.kent.cs.ocl20.bridge4kmf.KmfOclProcessorImpl;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */

public class Ocl4Kmf {
	public static KmfOclProcessorImpl processor = null;

	/** 
	 * Initialise the model 
	 * */
	public static void InitModel(String xmiFileName, ILog log) {
		//--- Load the model ---
		try {
			processor = new KmfOclProcessorImpl(xmiFileName, log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
