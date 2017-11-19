/* The following code was generated by JFlex 1.3.5 on 06/08/03 16:47 */

/**
 * 
 * @author Octavian Patrascoiu
 *
 */

// Usercode Section
package uk.ac.kent.cs.ocl20.syntax.parser;

import java_cup.runtime.*;
import uk.ac.kent.cs.kmf.util.*;
      

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.3.5
 * on 06/08/03 16:47 from the specification file
 * <tt>file:/G:/home/Code/Java/ocl2_lib/OCLcommon/src/uk/ac/kent/cs/ocl20/syntax/parser/ocl.flex</tt>
 */
class Yylex implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  final public static int YYEOF = -1;

  /** initial size of the lookahead buffer */
  final private static int YY_BUFFERSIZE = 16384;

  /** lexical states */
  final public static int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  final private static String yycmap_packed = 
    "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\6\0\1\15"+
    "\1\20\1\23\1\5\1\14\1\26\1\6\1\11\1\4\12\10\1\16"+
    "\1\27\1\47\1\46\1\50\1\32\1\31\1\7\1\54\1\61\1\7"+
    "\1\13\11\7\1\62\3\7\1\52\1\63\6\7\1\21\1\0\1\24"+
    "\1\17\1\7\1\0\1\40\1\70\1\60\1\42\1\12\1\65\1\55"+
    "\1\66\1\33\1\7\1\67\1\36\1\34\1\41\1\43\1\35\1\56"+
    "\1\44\1\37\1\53\1\57\1\51\1\7\1\45\1\64\1\7\1\22"+
    "\1\30\1\25\uff82\0";

  /** 
   * Translates characters to character classes
   */
  final private static char [] yycmap = yy_unpack_cmap(yycmap_packed);

  /** 
   * Translates a state to a row index in the transition table
   */
  final private static int yy_rowMap [] = { 
        0,    57,    57,   114,    57,   171,   228,   285,   342,   399, 
       57,   456,   513,   570,    57,    57,    57,    57,    57,    57, 
       57,    57,    57,    57,    57,   627,   684,   741,   798,   855, 
      912,   969,  1026,  1083,    57,  1140,  1197,  1254,  1311,  1368, 
     1425,  1482,  1539,  1596,  1653,  1710,  1767,  1824,    57,  1881, 
     1938,    57,  1995,  2052,   456,    57,    57,    57,  2109,  2166, 
     2223,   228,  2280,  2337,  2394,  2451,  2508,  2565,  2622,  2679, 
     2736,   228,  2793,    57,    57,    57,  2850,  2907,  2964,  3021, 
     3078,  3135,  3192,  3249,  3306,  3363,  3420,  3477,  3534,  3591, 
     3648,  3648,  3705,  3762,  3819,  3876,   228,  3933,   228,  3990, 
     4047,   228,   228,   228,   228,  4104,   228,   228,   228,   228, 
     4161,  4218,  4275,   228,  4332,  4389,  4446,  4503,  4560,  4617, 
      228,  4674,  4731,  4788,   228,  4845,  4902,   228,  4959,  5016, 
      228,   228,  5073,  5130,  5187,  5244,  5301,   228,   228,  5358, 
     5415,  5472,  5529,  5586,  5643,  5700,  5757,  5814,  5871,   228, 
     5928,  5985,  6042,  6099,   228,  6156,  6213,  6270,  6327,  6384, 
     6441,   228,   228,   228,  6498,   228,  6555,  6612,  6669,  6726, 
      228,  6783,  6840,  6897,  6954,  7011,  7068,   228,   228,   228, 
      228
  };

  /** 
   * The packed transition table of the DFA (part 0)
   */
  final private static String yy_packed0 = 
    "\1\2\3\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\7\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\1\35\1\7\1\36\1\37"+
    "\1\40\1\41\1\7\1\42\1\43\1\44\1\45\1\7"+
    "\1\46\1\47\1\50\3\7\1\51\1\52\1\53\1\54"+
    "\1\7\1\55\2\7\1\56\76\0\1\57\71\0\1\60"+
    "\41\0\1\61\27\0\2\7\1\0\2\7\17\0\13\7"+
    "\3\0\20\7\10\0\1\10\1\62\2\63\66\0\1\64"+
    "\66\0\2\7\1\0\2\7\17\0\3\7\1\65\2\7"+
    "\1\66\4\7\3\0\20\7\15\67\1\70\53\67\16\0"+
    "\1\71\71\0\1\72\60\0\2\7\1\0\2\7\17\0"+
    "\1\7\1\73\4\7\1\74\4\7\3\0\2\7\1\75"+
    "\11\7\1\76\3\7\7\0\2\7\1\0\2\7\17\0"+
    "\10\7\1\77\2\7\3\0\20\7\7\0\2\7\1\0"+
    "\2\7\17\0\5\7\1\100\2\7\1\101\1\102\1\7"+
    "\3\0\20\7\7\0\2\7\1\0\1\103\1\7\17\0"+
    "\13\7\3\0\20\7\7\0\2\7\1\0\2\7\17\0"+
    "\6\7\1\104\4\7\3\0\20\7\7\0\2\7\1\0"+
    "\2\7\17\0\10\7\1\105\2\7\3\0\20\7\7\0"+
    "\2\7\1\0\1\106\1\7\17\0\1\107\12\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\11\7\1\110"+
    "\1\7\3\0\20\7\7\0\2\7\1\0\2\7\17\0"+
    "\10\7\1\111\2\7\3\0\20\7\46\0\1\112\1\0"+
    "\1\113\66\0\1\114\31\0\2\7\1\0\1\115\1\7"+
    "\17\0\13\7\3\0\20\7\7\0\2\7\1\0\2\7"+
    "\17\0\11\7\1\116\1\7\3\0\15\7\1\117\2\7"+
    "\7\0\2\7\1\0\2\7\17\0\5\7\1\120\5\7"+
    "\3\0\20\7\7\0\2\7\1\0\2\7\17\0\10\7"+
    "\1\121\2\7\3\0\20\7\7\0\2\7\1\0\2\7"+
    "\17\0\10\7\1\122\2\7\3\0\20\7\7\0\2\7"+
    "\1\0\2\7\17\0\11\7\1\123\1\7\3\0\20\7"+
    "\7\0\2\7\1\0\2\7\17\0\13\7\3\0\6\7"+
    "\1\124\11\7\7\0\2\7\1\0\2\7\17\0\5\7"+
    "\1\125\5\7\3\0\20\7\7\0\2\7\1\0\2\7"+
    "\17\0\10\7\1\126\2\7\3\0\20\7\5\57\1\127"+
    "\63\57\1\60\1\130\1\131\66\60\10\0\1\132\66\0"+
    "\1\133\1\0\1\134\3\0\1\133\63\0\2\7\1\0"+
    "\2\7\17\0\4\7\1\135\6\7\3\0\20\7\7\0"+
    "\2\7\1\0\2\7\17\0\7\7\1\136\3\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\2\7\1\137"+
    "\10\7\3\0\20\7\7\0\2\7\1\0\2\7\17\0"+
    "\1\140\12\7\3\0\1\141\17\7\7\0\2\7\1\0"+
    "\1\142\1\7\17\0\13\7\3\0\20\7\7\0\2\7"+
    "\1\0\2\7\17\0\7\7\1\143\3\7\3\0\20\7"+
    "\7\0\2\7\1\0\2\7\17\0\13\7\3\0\7\7"+
    "\1\144\10\7\7\0\2\7\1\0\2\7\17\0\4\7"+
    "\1\145\6\7\3\0\20\7\7\0\2\7\1\0\1\146"+
    "\1\7\17\0\13\7\3\0\20\7\7\0\2\7\1\0"+
    "\2\7\17\0\13\7\3\0\2\7\1\147\15\7\7\0"+
    "\2\7\1\0\2\7\17\0\7\7\1\150\3\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\13\7\3\0"+
    "\2\7\1\151\15\7\7\0\2\7\1\0\2\7\17\0"+
    "\11\7\1\152\1\7\3\0\14\7\1\153\3\7\7\0"+
    "\2\7\1\0\2\7\17\0\13\7\3\0\1\154\17\7"+
    "\7\0\2\7\1\0\2\7\17\0\11\7\1\155\1\7"+
    "\3\0\20\7\7\0\2\7\1\0\2\7\17\0\13\7"+
    "\3\0\2\7\1\156\2\7\1\157\12\7\7\0\2\7"+
    "\1\0\2\7\17\0\13\7\3\0\6\7\1\160\11\7"+
    "\7\0\2\7\1\0\1\161\1\7\17\0\13\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\13\7\3\0"+
    "\4\7\1\162\13\7\7\0\2\7\1\0\2\7\17\0"+
    "\6\7\1\163\4\7\3\0\20\7\7\0\2\7\1\0"+
    "\2\7\17\0\3\7\1\164\7\7\3\0\20\7\7\0"+
    "\2\7\1\0\2\7\17\0\7\7\1\165\3\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\2\7\1\166"+
    "\10\7\3\0\20\7\7\0\2\7\1\0\2\7\17\0"+
    "\3\7\1\167\7\7\3\0\20\7\7\0\2\7\1\0"+
    "\2\7\17\0\7\7\1\170\3\7\3\0\20\7\4\57"+
    "\1\3\1\127\63\57\2\0\1\3\67\0\1\3\77\0"+
    "\1\132\1\0\2\63\65\0\1\134\67\0\2\7\1\0"+
    "\1\171\1\7\17\0\13\7\3\0\20\7\7\0\2\7"+
    "\1\0\2\7\17\0\1\172\1\7\1\173\10\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\3\7\1\174"+
    "\7\7\3\0\20\7\7\0\2\7\1\0\2\7\17\0"+
    "\13\7\3\0\2\7\1\175\15\7\7\0\2\7\1\0"+
    "\2\7\17\0\11\7\1\176\1\7\3\0\20\7\7\0"+
    "\2\7\1\0\2\7\17\0\13\7\3\0\16\7\1\177"+
    "\1\7\7\0\2\7\1\0\2\7\17\0\13\7\3\0"+
    "\2\7\1\200\15\7\7\0\2\7\1\0\2\7\17\0"+
    "\1\201\12\7\3\0\20\7\7\0\2\7\1\0\2\7"+
    "\17\0\13\7\3\0\6\7\1\202\11\7\7\0\2\7"+
    "\1\0\1\203\1\7\17\0\13\7\3\0\20\7\7\0"+
    "\2\7\1\0\2\7\17\0\6\7\1\204\4\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\13\7\3\0"+
    "\2\7\1\205\15\7\7\0\2\7\1\0\2\7\17\0"+
    "\3\7\1\206\7\7\3\0\20\7\7\0\2\7\1\0"+
    "\1\207\1\7\17\0\13\7\3\0\20\7\7\0\2\7"+
    "\1\0\2\7\17\0\3\7\1\210\7\7\3\0\20\7"+
    "\7\0\2\7\1\0\2\7\17\0\4\7\1\211\6\7"+
    "\3\0\20\7\7\0\2\7\1\0\2\7\17\0\13\7"+
    "\3\0\13\7\1\212\4\7\7\0\2\7\1\0\2\7"+
    "\17\0\13\7\3\0\14\7\1\213\3\7\7\0\2\7"+
    "\1\0\2\7\17\0\5\7\1\214\5\7\3\0\20\7"+
    "\7\0\2\7\1\0\2\7\17\0\1\215\12\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\5\7\1\216"+
    "\5\7\3\0\20\7\7\0\2\7\1\0\2\7\17\0"+
    "\5\7\1\217\5\7\3\0\20\7\7\0\2\7\1\0"+
    "\2\7\17\0\13\7\3\0\1\220\17\7\7\0\2\7"+
    "\1\0\1\221\1\7\17\0\13\7\3\0\20\7\7\0"+
    "\2\7\1\0\1\222\1\7\17\0\13\7\3\0\20\7"+
    "\7\0\2\7\1\0\1\223\1\7\17\0\13\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\11\7\1\224"+
    "\1\7\3\0\20\7\7\0\2\7\1\0\1\225\1\7"+
    "\17\0\13\7\3\0\20\7\7\0\2\7\1\0\1\226"+
    "\1\7\17\0\13\7\3\0\20\7\7\0\2\7\1\0"+
    "\2\7\17\0\13\7\3\0\7\7\1\227\10\7\7\0"+
    "\2\7\1\0\1\230\1\7\17\0\13\7\3\0\20\7"+
    "\7\0\2\7\1\0\2\7\17\0\13\7\3\0\2\7"+
    "\1\231\15\7\7\0\2\7\1\0\2\7\17\0\13\7"+
    "\3\0\4\7\1\232\13\7\7\0\2\7\1\0\1\233"+
    "\1\7\17\0\13\7\3\0\20\7\7\0\2\7\1\0"+
    "\2\7\17\0\6\7\1\234\4\7\3\0\20\7\7\0"+
    "\2\7\1\0\2\7\17\0\12\7\1\235\3\0\20\7"+
    "\7\0\2\7\1\0\2\7\17\0\13\7\3\0\7\7"+
    "\1\236\10\7\7\0\2\7\1\0\1\237\1\7\17\0"+
    "\13\7\3\0\20\7\7\0\2\7\1\0\2\7\17\0"+
    "\13\7\3\0\12\7\1\240\5\7\7\0\2\7\1\0"+
    "\2\7\17\0\13\7\3\0\16\7\1\241\1\7\7\0"+
    "\2\7\1\0\2\7\17\0\4\7\1\242\6\7\3\0"+
    "\20\7\7\0\2\7\1\0\1\243\1\7\17\0\13\7"+
    "\3\0\20\7\7\0\2\7\1\0\1\244\1\7\17\0"+
    "\13\7\3\0\20\7\7\0\2\7\1\0\2\7\17\0"+
    "\13\7\3\0\7\7\1\245\10\7\7\0\2\7\1\0"+
    "\2\7\17\0\13\7\3\0\2\7\1\246\15\7\7\0"+
    "\2\7\1\0\2\7\17\0\13\7\3\0\2\7\1\247"+
    "\15\7\7\0\2\7\1\0\2\7\17\0\7\7\1\250"+
    "\3\7\3\0\20\7\7\0\2\7\1\0\2\7\17\0"+
    "\13\7\3\0\13\7\1\251\4\7\7\0\2\7\1\0"+
    "\2\7\17\0\5\7\1\252\5\7\3\0\20\7\7\0"+
    "\2\7\1\0\1\253\1\7\17\0\13\7\3\0\20\7"+
    "\7\0\2\7\1\0\2\7\17\0\1\254\12\7\3\0"+
    "\20\7\7\0\2\7\1\0\2\7\17\0\13\7\3\0"+
    "\1\7\1\255\16\7\7\0\2\7\1\0\2\7\17\0"+
    "\2\7\1\256\10\7\3\0\20\7\7\0\2\7\1\0"+
    "\2\7\17\0\13\7\3\0\4\7\1\257\13\7\7\0"+
    "\2\7\1\0\2\7\17\0\10\7\1\260\2\7\3\0"+
    "\20\7\7\0\2\7\1\0\1\261\1\7\17\0\13\7"+
    "\3\0\20\7\7\0\2\7\1\0\1\262\1\7\17\0"+
    "\13\7\3\0\20\7\7\0\2\7\1\0\1\263\1\7"+
    "\17\0\13\7\3\0\20\7\7\0\2\7\1\0\2\7"+
    "\17\0\6\7\1\264\4\7\3\0\20\7\7\0\2\7"+
    "\1\0\2\7\17\0\13\7\3\0\2\7\1\265\15\7";

  /** 
   * The transition table of the DFA
   */
  final private static int yytrans [] = yy_unpack();


  /* error codes */
  final private static int YY_UNKNOWN_ERROR = 0;
  final private static int YY_ILLEGAL_STATE = 1;
  final private static int YY_NO_MATCH = 2;
  final private static int YY_PUSHBACK_2BIG = 3;

  /* error messages for the codes above */
  final private static String YY_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Internal error: unknown state",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * YY_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private final static byte YY_ATTRIBUTE[] = {
     0,  9,  9,  1,  9,  1,  1,  1,  1,  1,  9,  1,  1,  1,  9,  9, 
     9,  9,  9,  9,  9,  9,  9,  9,  9,  1,  1,  1,  1,  1,  1,  1, 
     1,  1,  9,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0, 
     9,  0,  0,  9,  1,  1,  0,  9,  9,  9,  1,  1,  1,  1,  1,  1, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  9,  9,  9,  1,  1,  1,  1, 
     1,  1,  1,  1,  1,  1,  0,  1,  1,  1,  0,  1,  1,  1,  1,  1, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 
     1,  1,  1,  1,  1
  };

  /** the input device */
  private java.io.Reader yy_reader;

  /** the current state of the DFA */
  private int yy_state;

  /** the current lexical state */
  private int yy_lexical_state = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char yy_buffer[] = new char[YY_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int yy_markedPos;

  /** the textposition at the last state to be included in yytext */
  private int yy_pushbackPos;

  /** the current text position in the buffer */
  private int yy_currentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int yy_startRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int yy_endRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn; 

  /** 
   * yy_atBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean yy_atBOL = true;

  /** yy_atEOF == true <=> the scanner is at the EOF */
  private boolean yy_atEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean yy_eof_done;

  /* user code: */
	// Debug flag
	public static boolean lexDebug = false;
	protected void debug(int type) {
    	if (lexDebug) {
    		log.reportMessage(yyline+":"+yycolumn+" Token "+type+" '"+yytext()+"'");		
    	}
	}

	// Output log
	protected ILog log;
	public void setLog(ILog log) {
		this.log = log;
	}
	
    // Create a new java_cup.runtime.Symbol with information about the current token
    protected Symbol symbol(int type) {
    	debug(type);
        return new Symbol(type, yyline, yycolumn, new String(yytext()));
    }
    protected Symbol symbol(int type, Object value) {
		debug(type);
        return new Symbol(type, yyline, yycolumn, value);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.yy_reader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the split, compressed DFA transition table.
   *
   * @return the unpacked transition table
   */
  private static int [] yy_unpack() {
    int [] trans = new int[7125];
    int offset = 0;
    offset = yy_unpack(yy_packed0, offset, trans);
    return trans;
  }

  /** 
   * Unpacks the compressed DFA transition table.
   *
   * @param packed   the packed transition table
   * @return         the index of the last entry
   */
  private static int yy_unpack(String packed, int offset, int [] trans) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do trans[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] yy_unpack_cmap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 146) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   IOException  if any I/O-Error occurs
   */
  private boolean yy_refill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (yy_startRead > 0) {
      System.arraycopy(yy_buffer, yy_startRead, 
                       yy_buffer, 0, 
                       yy_endRead-yy_startRead);

      /* translate stored positions */
      yy_endRead-= yy_startRead;
      yy_currentPos-= yy_startRead;
      yy_markedPos-= yy_startRead;
      yy_pushbackPos-= yy_startRead;
      yy_startRead = 0;
    }

    /* is the buffer big enough? */
    if (yy_currentPos >= yy_buffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[yy_currentPos*2];
      System.arraycopy(yy_buffer, 0, newBuffer, 0, yy_buffer.length);
      yy_buffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = yy_reader.read(yy_buffer, yy_endRead, 
                                            yy_buffer.length-yy_endRead);

    if (numRead < 0) {
      return true;
    }
    else {
      yy_endRead+= numRead;  
      return false;
    }
  }


  /**
   * Closes the input stream.
   */
  final public void yyclose() throws java.io.IOException {
    yy_atEOF = true;            /* indicate end of file */
    yy_endRead = yy_startRead;  /* invalidate buffer    */

    if (yy_reader != null)
      yy_reader.close();
  }


  /**
   * Closes the current stream, and resets the
   * scanner to read from a new input stream.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>YY_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  final public void yyreset(java.io.Reader reader) throws java.io.IOException {
    yyclose();
    yy_reader = reader;
    yy_atBOL  = true;
    yy_atEOF  = false;
    yy_endRead = yy_startRead = 0;
    yy_currentPos = yy_markedPos = yy_pushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    yy_lexical_state = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  final public int yystate() {
    return yy_lexical_state;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  final public void yybegin(int newState) {
    yy_lexical_state = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  final public String yytext() {
    return new String( yy_buffer, yy_startRead, yy_markedPos-yy_startRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  final public char yycharat(int pos) {
    return yy_buffer[yy_startRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  final public int yylength() {
    return yy_markedPos-yy_startRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void yy_ScanError(int errorCode) {
    String message;
    try {
      message = YY_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = YY_ERROR_MSG[YY_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  private void yypushback(int number)  {
    if ( number > yylength() )
      yy_ScanError(YY_PUSHBACK_2BIG);

    yy_markedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void yy_do_eof() throws java.io.IOException {
    if (!yy_eof_done) {
      yy_eof_done = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int yy_input;
    int yy_action;

    // cached fields:
    int yy_currentPos_l;
    int yy_startRead_l;
    int yy_markedPos_l;
    int yy_endRead_l = yy_endRead;
    char [] yy_buffer_l = yy_buffer;
    char [] yycmap_l = yycmap;

    int [] yytrans_l = yytrans;
    int [] yy_rowMap_l = yy_rowMap;
    byte [] yy_attr_l = YY_ATTRIBUTE;

    while (true) {
      yy_markedPos_l = yy_markedPos;

      boolean yy_r = false;
      for (yy_currentPos_l = yy_startRead; yy_currentPos_l < yy_markedPos_l;
                                                             yy_currentPos_l++) {
        switch (yy_buffer_l[yy_currentPos_l]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          yy_r = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          yy_r = true;
          break;
        case '\n':
          if (yy_r)
            yy_r = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          yy_r = false;
          yycolumn++;
        }
      }

      if (yy_r) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean yy_peek;
        if (yy_markedPos_l < yy_endRead_l)
          yy_peek = yy_buffer_l[yy_markedPos_l] == '\n';
        else if (yy_atEOF)
          yy_peek = false;
        else {
          boolean eof = yy_refill();
          yy_markedPos_l = yy_markedPos;
          yy_buffer_l = yy_buffer;
          if (eof) 
            yy_peek = false;
          else 
            yy_peek = yy_buffer_l[yy_markedPos_l] == '\n';
        }
        if (yy_peek) yyline--;
      }
      yy_action = -1;

      yy_startRead_l = yy_currentPos_l = yy_currentPos = 
                       yy_startRead = yy_markedPos_l;

      yy_state = yy_lexical_state;


      yy_forAction: {
        while (true) {

          if (yy_currentPos_l < yy_endRead_l)
            yy_input = yy_buffer_l[yy_currentPos_l++];
          else if (yy_atEOF) {
            yy_input = YYEOF;
            break yy_forAction;
          }
          else {
            // store back cached positions
            yy_currentPos  = yy_currentPos_l;
            yy_markedPos   = yy_markedPos_l;
            boolean eof = yy_refill();
            // get translated positions and possibly new buffer
            yy_currentPos_l  = yy_currentPos;
            yy_markedPos_l   = yy_markedPos;
            yy_buffer_l      = yy_buffer;
            yy_endRead_l     = yy_endRead;
            if (eof) {
              yy_input = YYEOF;
              break yy_forAction;
            }
            else {
              yy_input = yy_buffer_l[yy_currentPos_l++];
            }
          }
          int yy_next = yytrans_l[ yy_rowMap_l[yy_state] + yycmap_l[yy_input] ];
          if (yy_next == -1) break yy_forAction;
          yy_state = yy_next;

          int yy_attributes = yy_attr_l[yy_state];
          if ( (yy_attributes & 1) == 1 ) {
            yy_action = yy_state; 
            yy_markedPos_l = yy_currentPos_l; 
            if ( (yy_attributes & 8) == 8 ) break yy_forAction;
          }

        }
      }

      // store back cached position
      yy_markedPos = yy_markedPos_l;

      switch (yy_action) {

        case 1: 
        case 11: 
          {  log.reportError("Illegal character '"+yytext()+"'"); return symbol(sym.BAD); }
        case 182: break;
        case 165: 
          {  return symbol(sym.CONTEXT);  }
        case 183: break;
        case 163: 
          {  return symbol(sym.PACKAGE);  }
        case 184: break;
        case 162: 
          {  return symbol(sym.ITERATE);  }
        case 185: break;
        case 161: 
          {  return symbol(sym.IMPLIES);  }
        case 186: break;
        case 154: 
          {  return symbol(sym.DERIVE);  }
        case 187: break;
        case 98: 
          {  return symbol(sym.INT_MOD);  }
        case 188: break;
        case 3: 
          {  return symbol(sym.DIVIDE);  }
        case 189: break;
        case 7: 
          {  return symbol(sym.INTEGER);  }
        case 190: break;
        case 51: 
          {  return symbol(sym.DOT_DOT);  }
        case 191: break;
        case 55: 
          {  return symbol(sym.STRING);  }
        case 192: break;
        case 87: 
        case 88: 
          {  /* just skip what was found, do nothing */  }
        case 193: break;
        case 2: 
          {  /* just skip what was found, do nothing */  }
        case 194: break;
        case 180: 
          {  return symbol(sym.ORDERED_SET);  }
        case 195: break;
        case 179: 
          {  return symbol(sym.COLLECTION);  }
        case 196: break;
        case 178: 
          {  return symbol(sym.ENDPACKAGE);  }
        case 197: break;
        case 177: 
          {  return symbol(sym.TUPLE_TYPE);  }
        case 198: break;
        case 107: 
          {  return symbol(sym.INT_DIVIDE);  }
        case 199: break;
        case 6: 
        case 9: 
        case 25: 
        case 26: 
        case 27: 
        case 28: 
        case 29: 
        case 30: 
        case 31: 
        case 32: 
        case 33: 
        case 37: 
        case 38: 
        case 39: 
        case 40: 
        case 41: 
        case 42: 
        case 43: 
        case 44: 
        case 45: 
        case 52: 
        case 53: 
        case 58: 
        case 60: 
        case 62: 
        case 63: 
        case 64: 
        case 65: 
        case 66: 
        case 67: 
        case 68: 
        case 69: 
        case 70: 
        case 72: 
        case 76: 
        case 77: 
        case 78: 
        case 79: 
        case 80: 
        case 81: 
        case 82: 
        case 83: 
        case 84: 
        case 85: 
        case 92: 
        case 93: 
        case 94: 
        case 95: 
        case 97: 
        case 99: 
        case 100: 
        case 105: 
        case 110: 
        case 111: 
        case 112: 
        case 114: 
        case 115: 
        case 116: 
        case 117: 
        case 118: 
        case 119: 
        case 121: 
        case 122: 
        case 123: 
        case 125: 
        case 126: 
        case 128: 
        case 129: 
        case 132: 
        case 133: 
        case 134: 
        case 135: 
        case 136: 
        case 139: 
        case 140: 
        case 141: 
        case 142: 
        case 143: 
        case 144: 
        case 145: 
        case 146: 
        case 147: 
        case 150: 
        case 151: 
        case 152: 
        case 153: 
        case 155: 
        case 156: 
        case 157: 
        case 158: 
        case 159: 
        case 160: 
        case 164: 
        case 166: 
        case 167: 
        case 168: 
        case 169: 
        case 171: 
        case 172: 
        case 173: 
        case 174: 
        case 175: 
        case 176: 
          {  return symbol(sym.SIMPLE_NAME);  }
        case 200: break;
        case 56: 
          {  return symbol(sym.COLON_COLON);  }
        case 201: break;
        case 170: 
          {  return symbol(sym.SEQUENCE);  }
        case 202: break;
        case 19: 
          {  return symbol(sym.RIGHT_BRA);  }
        case 203: break;
        case 18: 
          {  return symbol(sym.RIGHT_BRK);  }
        case 204: break;
        case 17: 
          {  return symbol(sym.RIGHT_PAR);  }
        case 205: break;
        case 16: 
          {  return symbol(sym.LEFT_BRA);  }
        case 206: break;
        case 15: 
          {  return symbol(sym.LEFT_BRK);  }
        case 207: break;
        case 14: 
          {  return symbol(sym.LEFT_PAR);  }
        case 208: break;
        case 21: 
          {  return symbol(sym.SEMICOLON);  }
        case 209: break;
        case 24: 
          {  return symbol(sym.QUESTION);  }
        case 210: break;
        case 48: 
          {  return symbol(sym.MINUS_GT);  }
        case 211: break;
        case 113: 
          {  return symbol(sym.BAG);  }
        case 212: break;
        case 109: 
          {  return symbol(sym.SET);  }
        case 213: break;
        case 108: 
          {  return symbol(sym.XOR);  }
        case 214: break;
        case 106: 
          {  return symbol(sym.DEF);  }
        case 215: break;
        case 104: 
          {  return symbol(sym.NOT);  }
        case 216: break;
        case 103: 
          {  return symbol(sym.AND);  }
        case 217: break;
        case 102: 
          {  return symbol(sym.LET);  }
        case 218: break;
        case 101: 
          {  return symbol(sym.PRE);  }
        case 219: break;
        case 96: 
          {  return symbol(sym.INV);  }
        case 220: break;
        case 75: 
          {  return symbol(sym.GE);  }
        case 221: break;
        case 13: 
          {  return symbol(sym.UP);  }
        case 222: break;
        case 8: 
          {  return symbol(sym.DOT);  }
        case 223: break;
        case 22: 
          {  return symbol(sym.BAR);  }
        case 224: break;
        case 23: 
          {  return symbol(sym.AT);  }
        case 225: break;
        case 34: 
          {  return symbol(sym.EQ);  }
        case 226: break;
        case 35: 
          {  return symbol(sym.LT);  }
        case 227: break;
        case 36: 
          {  return symbol(sym.GT);  }
        case 228: break;
        case 59: 
          {  return symbol(sym.IN);  }
        case 229: break;
        case 61: 
          {  return symbol(sym.IF);  }
        case 230: break;
        case 71: 
          {  return symbol(sym.OR);  }
        case 231: break;
        case 73: 
          {  return symbol(sym.LE);  }
        case 232: break;
        case 74: 
          {  return symbol(sym.NE);  }
        case 233: break;
        case 149: 
          {  return symbol(sym.FALSE);  }
        case 234: break;
        case 148: 
          {  return symbol(sym.TUPLE);  }
        case 235: break;
        case 138: 
          {  return symbol(sym.ENDIF);  }
        case 236: break;
        case 137: 
          {  return symbol(sym.BODY);  }
        case 237: break;
        case 131: 
          {  return symbol(sym.THEN);  }
        case 238: break;
        case 130: 
          {  return symbol(sym.TRUE);  }
        case 239: break;
        case 127: 
          {  return symbol(sym.POST);  }
        case 240: break;
        case 124: 
          {  return symbol(sym.INIT);  }
        case 241: break;
        case 120: 
          {  return symbol(sym.ELSE);  }
        case 242: break;
        case 89: 
        case 91: 
          {  return symbol(sym.REAL);  }
        case 243: break;
        case 12: 
          {  return symbol(sym.COLON);  }
        case 244: break;
        case 10: 
          {  return symbol(sym.PLUS);  }
        case 245: break;
        case 4: 
          {  return symbol(sym.TIMES);  }
        case 246: break;
        case 5: 
          {  return symbol(sym.MINUS);  }
        case 247: break;
        case 20: 
          {  return symbol(sym.COMMA);  }
        case 248: break;
        case 57: 
          {  return symbol(sym.UP_UP);  }
        case 249: break;
        default: 
          if (yy_input == YYEOF && yy_startRead == yy_currentPos) {
            yy_atEOF = true;
            yy_do_eof();
              { 	return symbol(sym.EOF);
 }
          } 
          else {
            yy_ScanError(YY_NO_MATCH);
          }
      }
    }
  }


}
