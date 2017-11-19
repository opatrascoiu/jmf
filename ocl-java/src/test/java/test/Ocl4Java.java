package test;

import java.util.HashSet;
import java.util.Set;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.ocl20.bridge4java.JavaOclProcessorImpl;

/**
 * @author dha
 *
 */
public class Ocl4Java {
	public static JavaOclProcessorImpl processor = null;

	/** 
	 * Initialise the model 
	 * */
	public static void InitModel(String pkgName, ILog log) {
		//--- Load the model ---
		try {
			library.Library l = new library.Library();			Package model = Package.getPackage(pkgName);
			if (model == null) {
				log.reportError("Can't find Package with name "+pkgName);
				return;
			}
			Set models = new HashSet();
			models.add(model);
			processor = new JavaOclProcessorImpl(models, log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
