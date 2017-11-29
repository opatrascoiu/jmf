package uk.ac.ukc.cs.kmf.xmi;

import java.io.*;

public interface IWriterAdapter {
	public void indent(PrintWriter out, int indent);
    public String replaceReferences(String s) throws Exception;
    public void printStartElement(PrintWriter out, int indent, String tag, String atts[], boolean flag) throws Exception;
    public void printEndElement(PrintWriter out, int indent, String tag);
    public void printText(PrintWriter out, int indent, String text) throws Exception;
    public void printXMIElement(PrintWriter out, int indent, IXMIElement elem) throws Exception;
}
