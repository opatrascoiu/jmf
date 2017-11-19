package test;

import java.io.*;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.kmf.util.ILog;

/**
 * 
 * @author Octavian Patrascoiu
 *
 */
public class CodeGenerator {
	/**
	 *  Constructor
	 */
	public CodeGenerator(OclProcessor proc, String inputFileName, String outputFileName, String pkgName, ILog log) {
		this.processor = proc;
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.pkgName = pkgName;
		this.log = log;
	}

	public void generate() {
		// Generate the code
		try {
			//--- Prepare input ---
			System.out.println("Reading File - " + inputFileName);
			Reader input = new FileReader(inputFileName);
			//--- Prepare output --- 
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName), true);
			//--- Invoke generation from Ocl4Emf
			this.processor.generate(input, output, pkgName, log);
		} catch (Exception e) {
			if (this.processor.getDebug().booleanValue()) e.printStackTrace();
		}		
	}

	private OclProcessor processor;
	private String inputFileName;
	private String outputFileName;
	private String pkgName;
	private ILog log;
}
