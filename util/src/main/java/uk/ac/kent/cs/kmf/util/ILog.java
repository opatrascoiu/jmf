package uk.ac.kent.cs.kmf.util;

public interface ILog {
	/** Reset the log */
	public void reset();
	/** Reset the warning and error counters */
	public void resetViolations();
	/** Reset the warning counter */
	public void resetWarnings();
	/** Reset the error counter */
	public void resetErrors();
	/** tooManyViolations */
	public boolean tooManyViolations();
	/** Has warnings or errors */
	public boolean hasViolations();
	/** Has warnings */
	public boolean hasWarnings();
	/** Has errors */
	public boolean hasErrors();
	/** Get warning count */
	public int getWarnings();
	/** Get error count */
	public int getErrors();
	
    /** Report a message */
    public void reportMessage(String message);
    /** Print string */
    public void printMessage(String message);
    /** Report a warning message */
    public void reportWarning(String message);
    public void reportWarning(String message, Exception e);
    /** Report an error message */
    public void reportError(String message);
    public void reportError(String message, Exception e);

    /** Show final report */
    public void finalReport();

	/** Close log */
	public void close();
}
