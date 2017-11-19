/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.yatl.syntax.parser;

import java.io.Reader;

import uk.ac.kent.cs.yatl.syntax.transformations.Unit;
import uk.ac.kent.cs.kmf.util.ILog;

public interface YatlParser {
  /** 
   * Parse the given string 
   * without debugging info, 
   * reporting errors on default log: System.out 
   * 
   * The result is null if an error is reported 
   * */
  public Unit parse(String body);

  /** 
   * Parse the given string 
   * without debugging info, 
   * reporting errors on given log 
   * 
   * The result is null if an error is reported
   *  
   * */
  public Unit parse(String body, ILog log);

  /** 
   * Parse the given string 
   * with/without debugging info, 
   * reporting errors on given log
   * 
   * The result is null if an error is reported 
   *  
   * */
  public Unit parse(String body, ILog log, boolean debug);

  /** 
   * Parse the given file 
   * without debugging info, 
   * reporting errors on default log: System.out
   *  
   * The result is null if an error is reported 
   * 
   * */
  public Unit parse(Reader reader);

  /** 
   * Parse the given file 
   * without debugging info, 
   * reporting errors on given log
   *  
   * The result is null if an error is reported 
   * 
   * */
  public Unit parse(Reader reader, ILog log);

  /** 
   * Parse the given file 
   * with/without debugging info, 
   * reporting errors on given log
   * 
   * The result is null if an error is reported
   *  
   * */
  public Unit parse(Reader reader, ILog log, boolean debug);

  /** 
   * Check if the parser encountered an error 
   * 
   * */
  public boolean hasErrors();

  /** 
   * Check if the parser encountered a warning 
   * 
   * */
  public boolean hasWarnings();
}
