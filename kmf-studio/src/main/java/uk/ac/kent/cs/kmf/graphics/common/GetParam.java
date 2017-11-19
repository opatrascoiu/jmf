/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.common;

/**
 * An abstraction for getting both Applet and Servlet parameters.
 */
public interface GetParam {
	/**
	 * For custom dataset handlers.
	 * @return 
	 * @param s java.lang.String
	 */
	public void getMyDatasets(String s);
	/**
	 * Returns a Parameter value.
	 * @return java.lang.String
	 * @param s java.lang.String
	 */
	String getParameter(String s);
	/**
	 * Entry point for retrieving Images.
	 * @return Image
	 * @param s java.lang.String
	 */
	public java.awt.Image makeURLImage(String s);
	/**
	 * Creates an InputStream for reading data, labels, etc.
	 * @return java.io.InputStream
	 * @param s java.lang.String
	 */
	java.io.InputStream openURL(String s);
}