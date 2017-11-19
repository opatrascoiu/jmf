package test;

import uk.ac.kent.cs.kmf.util.*;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */

public class GenerateCodeFromFile {
	public static void main(String[] args) {
		args = new String[] {
			"src/library.xmi",
			"src/test/scripts/model.ocl",
			"src/test/scripts/Invariants.java",
			"test.scripts",
		};
		
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		Ocl4Kmf.InitModel(args[0], log);
		
		// Generate code
		CodeGenerator codeGen = new CodeGenerator(Ocl4Kmf.processor,args[1], args[2], args[3], log);
		codeGen.generate();

		// Print the compilation report		
		log.finalReport();
	}
}
