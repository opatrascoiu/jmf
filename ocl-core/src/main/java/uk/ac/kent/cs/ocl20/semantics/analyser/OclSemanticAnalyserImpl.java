/**
 * 
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.ocl20.semantics.analyser;

import java.util.HashMap;
import java.util.Map;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;
import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;
import uk.ac.kent.cs.ocl20.semantics.model.contexts.ContextDeclaration;
import uk.ac.kent.cs.ocl20.syntax.SyntaxVisitor;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.ContextDeclarationAS;

public class OclSemanticAnalyserImpl
	implements OclSemanticAnalyser
{
	public OclSemanticAnalyserImpl(OclProcessor proc, SyntaxVisitor semanticAnalyzerVisitor, SemanticsVisitor debugVisitor, ILog log) {
		this.processor = proc;
		this.semanticAnalyzerVisitor = semanticAnalyzerVisitor;
		this.debugVisitor = debugVisitor;
		this.log = log;
	}
	
	protected OclProcessor processor;
	protected SyntaxVisitor semanticAnalyzerVisitor;
	protected SemanticsVisitor debugVisitor=null;
		
	/** Input ast */
	protected ContextDeclarationAS decl;
	
	/** Output log */
	protected ILog log;
	
	/** Environment */
	protected Environment env;
	
	/** Semantic analysis */
	protected ContextDeclaration analyse() {
		//--- Check context
		if (decl == null) return null;
		if (env == null) env = processor.getBridgeFactory().buildEnvironment();
		if (log == null) log = new OutputStreamLog(System.out);
		
	    //--- Store the no of errors  ---
	    int errNo = log.getErrors();

		//--- Semantic analysis ---
		Map context = new HashMap();
		context.put("env", env);
		context.put("log", log);
		ContextDeclaration contextDecl = (ContextDeclaration)decl.accept(semanticAnalyzerVisitor, context);

		//--- Set hasErrors flag --
		hasErrors = log.getErrors() > errNo && !log.tooManyViolations();
		
		//--- Return semantic tree ---		
		return contextDecl; 
	}

	/** Semantic analysis */
	public ContextDeclaration analyse(ContextDeclarationAS decl, Environment env) {
		//--- Set local variables ---
		this.decl = decl;
		this.env = env;
		this.log = new OutputStreamLog(System.out);

		//--- Analyse the input ---
		return analyse();
	}

	/** Semantic analysis */
	public ContextDeclaration analyse(ContextDeclarationAS decl, Environment env, ILog log) {
		//--- Set local variables ---
		this.decl = decl;
		this.env = env;
		this.log = log;

		//--- Analyse the input ---
		return analyse();
	}

	/** Semantic analysis */
	public ContextDeclaration analyse(ContextDeclarationAS decl, Environment env, ILog log, boolean debugFlag) {
		//--- Set local variables ---
		this.decl = decl;
		this.env = env;
		this.log = log;
		
		//--- Analyse the input ---
		ContextDeclaration contextDecl = analyse();

		//--- Debug info ---
		if (debugFlag) {
			//--- Scan each constraint and display the expression body ---
			String exprStr = (String)contextDecl.accept(debugVisitor, "");
			//--- Report message ---
			log.reportMessage("OCL Model:");
			log.reportMessage(exprStr);
		}
		return contextDecl;
	}

	/** Check if the analyser encountered errors */
	protected boolean hasErrors;
	public boolean hasErrors() {
		return hasErrors;
	}
}

