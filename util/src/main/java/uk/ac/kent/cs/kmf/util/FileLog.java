package uk.ac.kent.cs.kmf.util;

import java.io.*;

public class FileLog implements ILog {
	/** Constructor */
	public FileLog(String fileName) {
		this.fileName = fileName;
		reset();
	}
	
	/** Reset the log content */
	public void reset() {
		if (file != null) file.delete();
		file = new File(fileName);
		try {
			log = new PrintWriter(new FileWriter(file), true);
		} catch (Exception e) {
		}
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
        log.println(message);
    }
    public void printMessage(String message) {
        log.print(message);
    }
    public void reportWarning(String message) {
        warNo++;
        if (warNo <= warUpperBound) {
            log.println("Warning: "+message);
        }
    }
    public void reportWarning(String message, Exception e) {
        warNo++;
        if (warNo <= warUpperBound) {
            log.println("Warning: "+message);
            if (e != null) e.printStackTrace(log);
        }
    }
    public void reportError(String message, Exception e) {
        errNo++;
        if (errNo <= errUpperBound) {
            log.println("Error: "+message);
            if (e != null) e.printStackTrace(log);
        }
    }
    public void reportError(String message) {
        errNo++;
        if (errNo <= errUpperBound) {
            log.println("Error: "+message);
        }
    }
    
    /** Print final report */
    public void finalReport() {
    	log.flush();
        if (errNo <= errUpperBound && warNo <= warUpperBound) {
            log.print("Finished - "+errNo+" error(s) "+warNo+" warning(s)");
        } else { 
        	if (errNo > errUpperBound) {
            	log.print("Finished - Too many errors.");
        	} 
        	if (warNo > warUpperBound) {
            	log.print("Finished - Too many warnings.");
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
		log.close();
	}
    
    /** log file */
    protected String fileName;
    protected File file;
    protected PrintWriter log;
    /** warning/error counters */
    protected int warNo = 0;
    protected int errNo = 0;
    /** waning/error bounds */
    protected int warUpperBound = 800;
    protected int errUpperBound = 800;
}
