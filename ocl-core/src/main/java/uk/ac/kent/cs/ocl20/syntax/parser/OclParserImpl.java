/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.syntax.parser;

import java.io.Reader;
import java.io.StringReader;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;
import uk.ac.kent.cs.ocl20.syntax.ast.ASTFactory;
import uk.ac.kent.cs.ocl20.syntax.ast.contexts.PackageDeclarationAS;

public class OclParserImpl implements OclParser {
  //--- Constructors ---
  public OclParserImpl() {
    //--- Properties
    this.body = new String();
    this.reader = null;
	this.log = null;
    this.root = null;
  }
  public OclParserImpl(String body, ILog log) {
    //--- Properties
    this.body = body;
	this.reader = null;
	this.root = null;
    this.log = log;
  }
  public OclParserImpl(Reader reader, ILog log) {
	//--- Properties
	this.reader = reader;
	this.body = null;
	this.root = null;
	this.log = log;
  }

  /** String to be parsed */
  protected String body;

  /** File to be parsed */
  protected Reader reader;

  /** Syntax tree */
  protected PackageDeclarationAS root;

  /** Parsing log */
  protected ILog log;

  //--- Operations ---
  /** Parse the 'body' string in the 'env' context and return the syntax tree 'root' */
  public PackageDeclarationAS parse(boolean debug) {
  	// Initialise the parser
	if (body == null && reader == null) return null;
  	if (log == null) log = new OutputStreamLog(System.out);
  	
    //--- Create a lexer and a parser ---
    Yylex yylex = new Yylex(reader!=null ? reader : new StringReader(body));
    yylex.setLog(log);
    Yyparse yyparser = new Yyparse(yylex);
    yyparser.setLog(log);
    yyparser.setFactory(new ASTFactory());

    //--- Store the no of errors ---
    int errNo = log.getErrors();
    int warnNo = log.getWarnings();
    
    //--- Parse input ---
	root = null;
	java_cup.runtime.Symbol rootSymbol = null;
    try {
      if (debug) {
//      	Yylex.lexDebug = true;
        rootSymbol = yyparser.debug_parse();
      } else
        rootSymbol = yyparser.parse();
    } catch (Exception e) {
    	rootSymbol = null;
        log.reportError("Parse error " + e);
	} finally {
	}

	//--- Set hasErrors flag
	hasErrors = log.getErrors() > errNo && !log.tooManyViolations();
	hasWarnings = log.getWarnings() > warnNo && !log.tooManyViolations();
	
	//--- Return result ---
	if (rootSymbol != null && rootSymbol.value instanceof PackageDeclarationAS) 
		root = (PackageDeclarationAS)rootSymbol.value;
	return root;
  }

  /** Parse the 'body' string and return the syntax tree 'root' */
  public PackageDeclarationAS parse(String body) {
  	this.body = body;
  	this.log = new OutputStreamLog(System.out);
  	return parse(false);
  }

  /** Parse the 'body' string and return the syntax tree 'root' */
  public PackageDeclarationAS parse(String body, ILog log) {
	this.body = body;
	this.log = log;
  	return parse(false);
  }

  /** Parse the 'body' string and return the syntax tree 'root' */
  public PackageDeclarationAS parse(String body, ILog log, boolean debug) {
	this.body = body;
	this.log = log;
	return parse(debug);
  }

  /** Parse the file 'reader' string and return the syntax tree 'root' */
  public PackageDeclarationAS parse(Reader reader) {
	this.reader = reader;
	this.log = new OutputStreamLog(System.out);
	return parse(false);
  }

  /** Parse the 'body' string and return the syntax tree 'root' */
  public PackageDeclarationAS parse(Reader reader, ILog log) {
	this.reader = reader;
	this.log = log;
	return parse(false);
  }

  /** Parse the 'body' string and return the syntax tree 'root' */
  public PackageDeclarationAS parse(Reader reader, ILog log, boolean debug) {
	this.reader = reader;
	this.log = log;
	return parse(debug);
  }

  /** Check if the parser encountered an error */
  protected boolean hasErrors;
  public boolean hasErrors() {
  	return hasErrors;
  }

  /** Check if the parser encountered a warning */
  protected boolean hasWarnings;
  public boolean hasWarnings() {
	return hasWarnings;
  }
}
