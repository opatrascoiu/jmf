package uk.ac.ukc.cs.kmf.xmi;

import uk.ac.kent.cs.kmf.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class XMIReader
	implements IXMIReader
{
	public XMIReader() {
	}
    public XMIFile read(String xmiFileName, ILog log) throws Exception {
        this.xmiFileName = xmiFileName;
        this.log = log;
        return read();
    }
    protected XMIFile read() throws Exception {
    	// Create the XML Reader
		XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

		// Create and register the XMI Handler
		XMIFile xmiFile = new XMIFile(xmiFileName);
		XMIHandler handler = new XMIHandler(xmiFile, log);
	    xmlReader.setContentHandler(handler);
	    xmlReader.setErrorHandler(handler);

		//--- Parse the input file ---
		//--- Parse the input file ---
		if (xmiFileName.indexOf(':') != -1 || xmiFileName.startsWith("/") || xmiFileName.startsWith("\\"))
			xmiFileName = "file:///"+xmiFileName;
		else if (xmiFileName.startsWith("file:/"))
			;
		else
			xmiFileName = "file:"+xmiFileName;
		log.reportMessage("Reading resource '"+xmiFileName+"'");
		InputSource inputSource = new InputSource(xmiFileName);
		xmlReader.parse(inputSource);

		//--- Return the result ---
        return xmiFile;
    }

    protected String xmiFileName;
	protected ILog log;
}