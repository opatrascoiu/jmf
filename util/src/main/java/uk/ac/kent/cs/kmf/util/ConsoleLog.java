package uk.ac.kent.cs.kmf.util;

import javax.swing.*;

public class ConsoleLog extends JTextArea implements ILog {
	/** Constructor */
	public ConsoleLog() {
		super();
	}
	
	/** Reset the log content */
	public void reset() {
        setText("");
		warNo = 0;
		errNo = 0;
	}
	
	/** Reset the violations counter */
	public void resetViolations() {
		warNo = 0;
		errNo = 0;
	}
	/** Reset the warnings counter */
	public void resetWarnings() {
		warNo = 0;
	}
	/** Reset the errors counter */
	public void resetErrors() {
		errNo = 0;
	}
	/** tooManyViolations */
	public boolean tooManyViolations() {
		return errNo > errUpperBound || warNo > warUpperBound;
	}
	/** Has violations */
	public boolean hasViolations() {
		return errNo != 0 || warNo != 0;
	}
	/** Has errors */
	public boolean hasErrors() {
		return errNo != 0;
	}
	/** Has warnings */
	public boolean hasWarnings() {
		return warNo != 0;
	}
	/** Get warning count */
	public int getWarnings() {
		return warNo;
	}
	/** Get error count */
	public int getErrors() {
		return errNo;
	}
	
    /** Handling messages and errors */
    public void reportMessage(String message) {
        append(message+"\n");
    }
    public void printMessage(String message) {
        append(message);
    }
    public void reportWarning(String message) {
        warNo++;
        if (warNo <= warUpperBound) {
            append("Warning: "+message+"\n");
        }
    }
    public void reportWarning(String message, Exception e) {
        warNo++;
        if (warNo <= warUpperBound) {
            append("Warning: "+message);
            if (e != null) append(e.toString());
            append("\n");
        }
    }
    public void reportError(String message) {
        errNo++;
        if (errNo <= errUpperBound) {
            append("Error: "+message+"\n");
        }
    }
    public void reportError(String message, Exception e) {
        errNo++;
        if (errNo <= errUpperBound) {
            append("Error: "+message);
            if (e != null) append(e.toString());
            append("\n");
        }
    }
    
    /** Print final report */
    public void finalReport() {
        if (errNo <= errUpperBound && warNo <= warUpperBound) {
            append("Finished - "+errNo+" error(s) "+warNo+" warning(s)"+"\n");
        } else { 
        	if (errNo > errUpperBound) {
            	append("Finished - Too many errors."+"\n");
        	} 
        	if (warNo > warUpperBound) {
            	append("Finished - Too many warnings."+"\n");
        	}
        }
    }
    
    /** Set upper bounds */
    public void setWarUpperBound(int bound) {
    	warUpperBound = bound;
    }
    public void setErrUpperBound(int bound) {
    	errUpperBound = bound;
    }

	/** Close log */
	public void close() {
	}
       
    /** warning/error counters */
    protected int warNo = 0;
    protected int errNo = 0;
    /** waning/error bounds */
    protected int warUpperBound = 800;
    protected int errUpperBound = 800;
}
