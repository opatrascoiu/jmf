/**
 * 
 * @author Octavian Patrascoiu
 *
 */

package uk.ac.kent.cs.ocl20.syntax.parser;

import uk.ac.kent.cs.kmf.util.ILog;

//import uk.ac.ukc.cs.kmf.util.*;

public class ErrorManager {
	// Change the method report_error to display location and the reason
	static StringBuffer getMessage(String message, Object info) {
		// Create the error message
		StringBuffer buffer = new StringBuffer("");
		// Add location
		if (info instanceof java_cup.runtime.Symbol) {
			java_cup.runtime.Symbol symb = ((java_cup.runtime.Symbol) info);
			if (symb.left >= 0) {
				buffer.append((symb.left+1)+":");
				buffer.append((symb.right+1));
				buffer.append(" ");
			}
			buffer.append(message);
			if (symb.value != null && ((String)symb.value).length() != 0) {
				buffer.append(" near '"+symb.value+"'");
			}
		} else {
			// Add message
			buffer.append(message);
		}
		return buffer;
	}

	public static void reportWarning(ILog log, Object info, String message) {
		// Report error
		log.reportWarning(new String(getMessage(message, info)));
	}
	
	public static void reportError(ILog log, Object info, String message) {
		// Report error
		log.reportError(new String(getMessage(message, info)));
	}
	
	// Change the method report_error to display location and the reason
	public static void reportFatalError(ILog log, Object info, String message) {
		log.reportError(new String(getMessage(message, info)));
	}
}
