package test;

import java.io.*;

import uk.ac.kent.cs.yatl.YatlProcessor;
import uk.ac.kent.cs.yatl.bridge4kmf.Yatl4KmfProcessorImpl;
import uk.ac.kent.cs.yatl.syntax.transformations.Unit;

import uk.ac.kent.cs.ocl20.bridge4kmf.*;

import uk.ac.kent.cs.kmf.util.*;

/**
 * @author Octavian Patrascoiu
 *
 */
public class TestAnalyserFromFile {
	public static void main(String[] args) {
		// Initialize the log
		ILog log = new OutputStreamLog(System.out);
		// Create an OclProcessor
		KmfOclProcessorImpl oclProcessor = new KmfOclProcessorImpl(log);
		oclProcessor.setBridgeFactory(new KmfBridgeFactoryImpl(oclProcessor));		
		// Add models
		Pair umlPair = oclProcessor.loadModel("src/test/scripts/java.xmi", log);
		Pair javaPair = oclProcessor.loadModel("src/test/scripts/UMLModel.xmi", log);
		// Create a KtlProcessor
		YatlProcessor ktlProcessor = new Yatl4KmfProcessorImpl(oclProcessor, log);

		try {
			// Analyse the input file
			FileReader input = new FileReader("src/test/scripts/uml2java.ktl");
			Unit res = ktlProcessor.analyse(input, log, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
