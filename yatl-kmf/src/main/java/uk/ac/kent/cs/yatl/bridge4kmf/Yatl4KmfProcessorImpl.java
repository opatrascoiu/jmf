package uk.ac.kent.cs.yatl.bridge4kmf;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.yatl.YatlProcessor;
import uk.ac.kent.cs.yatl.YatlProcessorImpl;
import uk.ac.kent.cs.yatl.evaluation.YatlInterpreter;
import uk.ac.kent.cs.yatl.evaluation.YatlInterpreterImpl;
import uk.ac.kent.cs.yatl.semantics.YatlSemanticAnalyser;
import uk.ac.kent.cs.yatl.semantics.YatlSemanticAnalyserImpl;
import uk.ac.kent.cs.yatl.syntax.parser.YatlParser;
import uk.ac.kent.cs.yatl.syntax.parser.YatlParserImpl;
import uk.ac.kent.cs.ocl20.OclProcessor;

/**
 * @author Octavian Patrascoiu
 *
 */
public class Yatl4KmfProcessorImpl
	extends YatlProcessorImpl
	implements YatlProcessor
{
	/** Constructor */
	public Yatl4KmfProcessorImpl(OclProcessor oclProcessor) {
		super(oclProcessor);
	}
	public Yatl4KmfProcessorImpl(OclProcessor oclProcessor, ILog log) {
		super(oclProcessor, log);
	}
	
	/** KTL processing elements: parser, semantic analyser, evaluator, and code generator */
	protected YatlParser parser = new YatlParserImpl();
	public YatlParser getParser() {return parser;}
	protected YatlSemanticAnalyser analyser = new YatlSemanticAnalyserImpl(oclProcessor, getLog());
	public YatlSemanticAnalyser getAnalyser() {return analyser;}
	protected YatlInterpreter interpreter = new YatlInterpreterImpl(oclProcessor, getLog());
	public YatlInterpreter getInterpreter() {return interpreter;}
}
