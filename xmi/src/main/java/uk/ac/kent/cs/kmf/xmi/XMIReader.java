package uk.ac.kent.cs.kmf.xmi;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;

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
    	this.file = new File(xmiFileName);
    	this.reader = new FileReader(file);
        this.log = log;
        return read(reader);
    }
	public XMIFile read(File file, ILog log) throws Exception {
		this.xmiFileName = file.getAbsolutePath();
		this.file = file;
		try {
			this.reader = new FileReader(xmiFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.log = log;
		return read(reader);
	}
	public XMIFile read(InputStream stream, ILog log) throws Exception {
		return read(stream);
	}
    protected XMIFile read(Reader reader) throws Exception {
    	// Create the XML Reader
		XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

		// Create and register the XMI Handler
		XMIFile xmiFile = new XMIFile(xmiFileName);
		XMIHandler handler = new XMIHandler(xmiFile, log);
	    xmlReader.setContentHandler(handler);
	    xmlReader.setErrorHandler(handler);

		//--- Parse the input file ---
		InputSource inputSource = new InputSource(reader);
	    xmlReader.parse(inputSource);

		//--- Return the result ---
        return xmiFile;
    }

	protected XMIFile read(InputStream stream) throws Exception {
		// Create the XML Reader
		XMLReader xmlReader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

		// Create and register the XMI Handler
		XMIFile xmiFile = new XMIFile(xmiFileName);
		XMIHandler handler = new XMIHandler(xmiFile, log);
		xmlReader.setContentHandler(handler);
		xmlReader.setErrorHandler(handler);

		//--- Parse the input file ---
		InputSource inputSource = new InputSource(stream);
		xmlReader.parse(inputSource);

		//--- Return the result ---
		return xmiFile;
	}

	protected Reader reader;
	protected File file;
	protected String xmiFileName;
	protected ILog log;
}