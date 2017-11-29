package uk.ac.ukc.cs.kmf.xmi;

import java.io.*;
import java.util.*;

import uk.ac.kent.cs.kmf.util.*;

public class WriterAdapter
	implements IWriterAdapter
{
	public void indent(PrintWriter out, int indent) {
		for(int i=0; i<indent; i++) out.print("    ");
	}

    public String replaceReferences(String s) throws Exception {
        String res = new String();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '<') res += "&lt;";
            else if(c == '>') res += "&gt;";
            else if(c == '&') res += "&amp;";
            else if('\uD800' <= c && c < '\uDC00') {
                if(i+1 < s.length()) { 
                	int j = s.charAt(++i);
                	if('\uDC00' <= j && j < '\uE000') {
                		j = (((c - 55296 << 10) + j) - 56320) + 0x10000;
                		res += "&#x";
                		res += Integer.toHexString(j);
                		res += ";";
                	} else {
                    	throw new Exception("Invalid reference: " + Integer.toHexString(c) + " " + Integer.toHexString(j));
                	}
                } else {
                	throw new Exception("Invalid reference: " + Integer.toHexString(c) + " ?");
                }
            } else {
                res += c;
            }
        }
        return res;
    }
	
    public void printStartElement(PrintWriter out, int indent, String tag, String atts[], boolean flag) throws Exception {
        indent(out, indent);
        out.print("<" + tag);
        if(atts != null) {
            for(int i=0; i<atts.length-1; i+=2)
                if(atts[i+1] != null)
                    out.print(" "+atts[i]+"=\""+replaceReferences(atts[i+1])+"\"");
        }
        if(flag) 
            out.print("/");
        out.print(">");
    }

    public void printEndElement(PrintWriter out, int indent, String tag) {
        indent(out, indent);
        out.print("</"+tag+">");
    }

    public void printText(PrintWriter out, int indent, String text) throws Exception {
        indent(out, indent);
        out.print(replaceReferences(text));
    }

    public void printXMIElement(PrintWriter out, int indent, IXMIElement elem) throws Exception {
    	String atts[] = new String[] {};
    	if (elem instanceof IXMIObject) {
    		IXMIObject obj = (IXMIObject)elem;
    		// Start Object
    		atts = new String[] {
    			"xmi.id", obj.getID(), 
    			"xmi.label", obj.getLABEL(), 
    			"xmi.href", obj.getHREF(), 
    			"xmi.uuid", obj.getUUID() 
    		};
    		printStartElement(out, indent+1, elem.getXMIName(), atts, false);
        	out.println();
        	// Print properties
        	Collection props = obj.getProperties();
        	Iterator i = props.iterator();
        	while (i.hasNext()) {
        		IXMIElement e = (IXMIElement)i.next();
        		printXMIElement(out, indent+1, e);
        	}
    		// End Object
    		printEndElement(out, indent+1, elem.getXMIName());
   			out.println();
    	} else if (elem instanceof IXMIProperty) {
    		IXMIProperty prop = (IXMIProperty)elem;
    		// Basic property
    		if (prop.isBasicType()) {
    			// Begin/end property
    			atts = new String[] {
    				"xmi.value", prop.getXMIValue().toString()
    			};
    			printStartElement(out, indent+1, elem.getXMIName(), atts, true);
        		out.println();
        	// User type property
    		} else if (prop.isObjectType()) {
    			// Begin property
    			printStartElement(out, indent+1, elem.getXMIName(), atts, false);
        		out.println();
        		IXMIObject value = (IXMIObject)prop.getXMIValue();
        		if (value == null) {
        		} else {
        			atts = new String[] {
        				"xmi.idref", value.getID()
        			};
    				printStartElement(out, indent+2, value.getXMIName(), atts, true);
   					out.println();
        		}
    			// End Property
    			printEndElement(out, indent+1, elem.getXMIName());
   				out.println();
        	// User type property
    		} else if (prop.isCollectionType()) {
    			// Begin property
    			printStartElement(out, indent+1, elem.getXMIName(), atts, false);
        		out.println();
    			List values = prop.getXMIValues();
    			Iterator i = values.iterator();
    			while (i.hasNext()) {
    				IXMIObject obj = (IXMIObject)i.next();
    				if (obj != null) {
    					Object value = obj.getObject();
    					if (Type.isInstanceofPrimitiveType(value)) {
        					atts = new String[] {
        						"xmi.value", value.toString()
        					};
    					} else {
        					atts = new String[] {
        						"xmi.idref", obj.getID()
        					};
    					}
    					printStartElement(out, indent+2, obj.getXMIName(), atts, true);
   						out.println();
    				}
    			}
    			// End Property
    			printEndElement(out, indent+1, elem.getXMIName());
   				out.println();
    		}
    	} else {
    	}
    }
	
}
