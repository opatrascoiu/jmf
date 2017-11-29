package uk.ac.kent.cs.kmf.xmi;

import java.io.*;
import java.util.*;

import uk.ac.kent.cs.kmf.util.*;

public class XMIWriter
	implements IXMIWriter
{
	public XMIWriter() {
		this.writerAdapter =  AdapterFactoryRegister.getAdapterFactory().createWriterAdapter();
	}
    public void write(IXMIFile xmiFile, String xmiFileName, ILog log) throws Exception {
        this.xmiFile = xmiFile;
        this.xmiFileName = xmiFileName;
        out = new PrintWriter(new FileWriter(xmiFileName), true);
        this.log = null;
        write();
    }
    protected void write() throws Exception {
    	// Print XML header
        out.print("<?xml version=\"1.0\"");
        String encoding = xmiFile.getEncoding();
        if(encoding != null && !encoding.equals(""))
            out.print(" encoding=\"" + encoding + "\"");
        out.print("?>");
        out.println();

        // Begin XMI
    	String atts[] = new String[] {
    		"version", xmiFile.getXMIVersion(),
    		"timestamp", xmiFile.getTimestamp(),
    		"verified", ""+xmiFile.getVerified()
    	};
        writerAdapter.printStartElement(out, 0, "XMI", atts, false);
        out.println();

    	// Write the header 
        writeHeader(out, 0);

    	// Write the content
        writeContent(out, 0);

        //  End XMI
		writerAdapter.printEndElement(out, 0, "XMI");
        out.println();
    }

    protected void writeHeader(PrintWriter out, int indent) throws Exception {
        // Print header
        if (xmiFile.hasHeader()) {
        	// Start HEADER
        	writerAdapter.printStartElement(out, indent+1, "XMI.header", new String[] {}, false);
            out.println();
        	// Print documentation
        	if (xmiFile.hasDocumentation()) {
        		writerAdapter.printStartElement(out, indent+2, "XMI.documentation", new String[] {}, false);
           		out.println();
        		if (xmiFile.getOwner()!=null) {
        			writerAdapter.printStartElement(out, indent+3, "XMI.owner", new String[] {}, false);
        			writerAdapter.printText(out, 0, xmiFile.getOwner());
        			writerAdapter.printEndElement(out, 0, "XMI.owner");
            		out.println();
        		}
        		if (xmiFile.getContact()!=null) {
        			writerAdapter.printStartElement(out, indent+3, "XMI.contact", new String[] {}, false);
        			writerAdapter.printText(out, 0, xmiFile.getContact());
        			writerAdapter.printEndElement(out, 0, "XMI.contact");
            		out.println();
        		}
        		if (xmiFile.getLongDescription()!=null) {
        			writerAdapter.printStartElement(out, indent+3, "XMI.longDescription", new String[] {}, false);
        			writerAdapter.printText(out, 0, xmiFile.getLongDescription());
        			writerAdapter.printEndElement(out, 0, "XMI.longDescription");
            		out.println();
        		}
        		if (xmiFile.getShortDescription()!=null) {
        			writerAdapter.printStartElement(out, indent+3, "XMI.shortDescription", new String[] {}, false);
        			writerAdapter.printText(out, 0, xmiFile.getShortDescription());
        			writerAdapter.printEndElement(out, 0, "XMI.shortDescription");
            		out.println();
        		}
        		if (xmiFile.getExporter()!=null) {
        			writerAdapter.printStartElement(out, indent+3, "XMI.exporter", new String[] {}, false);
        			writerAdapter.printText(out, 0, xmiFile.getExporter());
        			writerAdapter.printEndElement(out, 0, "XMI.exporter");
            		out.println();
        		}
        		if (xmiFile.getExporterVersion()!=null) {
        			writerAdapter.printStartElement(out, indent+3, "XMI.exporterVersion", new String[] {}, false);
        			writerAdapter.printText(out, 0, xmiFile.getExporterVersion());
        			writerAdapter.printEndElement(out, 0, "XMI.exporterVersion");
            		out.println();
        		}
        		if (xmiFile.getNotice()!=null) {
        			writerAdapter.printStartElement(out, indent+3, "XMI.notice", new String[] {}, false);
        			writerAdapter.printText(out, 0, xmiFile.getNotice());
        			writerAdapter.printEndElement(out, 0, "XMI.notice");
            		out.println();
        		}
        		writerAdapter.printEndElement(out, indent+2, "XMI.documentation");
           		out.println();
        	}
        	// Print models
        	if (xmiFile.hasModels()) {
        		List list = xmiFile.getModels();
        		Iterator it = list.iterator();
        		while (it.hasNext()) {
        			XMIFile.Model model = (XMIFile.Model)it.next();
        			writerAdapter.printStartElement(out, indent+2, "XMI.model", new String[] {}, false);
        			writerAdapter.printEndElement(out, indent+2, "XMI.model");
           			out.println();
        		}
        	}
        	// Print metamodels
        	if (xmiFile.hasMetamodels()) {
        		List list = xmiFile.getMetamodels();
        		Iterator it = list.iterator();
        		while (it.hasNext()) {
        			XMIFile.Metamodel model = (XMIFile.Metamodel)it.next();
        			writerAdapter.printStartElement(out, indent+2, "XMI.metamodel", new String[] {}, false);
        			writerAdapter.printEndElement(out, indent+2, "XMI.metamodel");
           			out.println();
        		}
        	}
        	// Print metametamodels
        	if (xmiFile.hasMetametamodels()) {
        		List list = xmiFile.getMetametamodels();
        		Iterator it = list.iterator();
        		while (it.hasNext()) {
        			XMIFile.Metametamodel model = (XMIFile.Metametamodel)it.next();
        			writerAdapter.printStartElement(out, indent+2, "XMI.metametamodel", new String[] {}, false);
        			writerAdapter.printEndElement(out, indent+2, "XMI.metametamodel");
           			out.println();
        		}
        	}
        	// Print imports
        	if (xmiFile.hasImports()) {
        		List list = xmiFile.getImports();
        		Iterator it = list.iterator();
        		while (it.hasNext()) {
        			XMIFile.Import model = (XMIFile.Import)it.next();
        			writerAdapter.printStartElement(out, indent+2, "XMI.metametamodel", new String[] {}, false);
        			writerAdapter.printEndElement(out, indent+2, "XMI.metametamodel");
           			out.println();
        		}
        	}
        	// End HEADER
        	writerAdapter.printEndElement(out, indent+1, "XMI.header");
       		out.println();
        }
    }

    protected void writeContent(PrintWriter out, int indent) throws Exception {
    	// Start CONTENT
    	writerAdapter.printStartElement(out, indent+1, "XMI.content", new String[] {}, false);
        out.println();
        // Print topObjects
        Collection objs = xmiFile.getTopObjects();
        Iterator i = objs.iterator();
        while (i.hasNext()) {
        	IXMIElement obj = (IXMIElement)i.next();
        	writerAdapter.printXMIElement(out, indent+1, obj);
        }
    	// End CONTENT
    	writerAdapter.printEndElement(out, indent+1, "XMI.content");
   		out.println();
    }
    
    protected IXMIFile xmiFile;
    protected String xmiFileName;
	protected PrintWriter out;
	protected ILog log;
	protected IWriterAdapter writerAdapter;
}