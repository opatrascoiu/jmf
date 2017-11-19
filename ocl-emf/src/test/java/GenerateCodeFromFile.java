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
			"library",
			"src/test/scripts/model.ocl",
			"src/test/scripts/Invariants.java",
			"test.scripts",
		};
		
		// Initialize the model and log
		ILog log = new OutputStreamLog(System.out);
		
		// Generate code
		CodeGenerator codeGen = new CodeGenerator(Ocl4Emf.processor,args[1], args[2], args[3], log);
		codeGen.generate();

		// Print the compilation report		
		log.finalReport();
	}
}
