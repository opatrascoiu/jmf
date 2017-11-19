package test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.ocl20.bridge4emf.EmfOclProcessorImpl;

/**
 * @author dha
 *
 */
public class Ocl4Emf {
	public static EmfOclProcessorImpl processor = null;

	/** 
	 * Initialise the model 
	 * */
	public static void InitModel(String modelName, ILog log) {		
		// Use reflection to initialize the model
		try {
			// Compute the name of modelNamePackage and modelNamePackageImpl
			String offset = modelName;
			String interfaceName = modelName.substring(0, 1).toUpperCase()+modelName.substring(1, modelName.length())+"Package";
			String className = interfaceName+"Impl";
			String fullInterfaceName = modelName+"."+interfaceName;
			String fullClassName = modelName+".impl."+className;

			// Get the interface and the class
			Class interf = Class.forName(fullInterfaceName);
			Class cls = Class.forName(fullClassName);
			
			// Initialize the model
			// LibraryPackageImpl.init();
			Method init = cls.getMethod("init", new Class[] {});
			init.invoke(null, new Object[] {});
			Map registry = EPackage.Registry.INSTANCE;
			// String enterpiseURI = LibraryPackage.eNS_URI;
			Field field = interf.getField("eNS_URI");
			String enterpiseURI = (String)field.get(null);
			EPackage model = (EPackage)registry.get(enterpiseURI);
			InitModel(model, log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void InitModel(EPackage model, ILog log) {
		Set models = new HashSet();
		models.add(model);
		processor = new EmfOclProcessorImpl(models, log);
	}
}
