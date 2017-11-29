package uk.ac.kent.cs.kmf.util;

import java.util.*;

public class CSFactory
{
    /** Maps types from model to Java default values as Object */
    protected static Map typeToObject = new HashMap();
    static {
    	typeToObject.put("Boolean", new Boolean(false));
    	typeToObject.put("Byte", new Integer(0));
    	typeToObject.put("Character", new Integer(0));
    	typeToObject.put("Double", new Double(0));
    	typeToObject.put("Float", new Float(0));
    	typeToObject.put("Integer", new Integer(0));
    	typeToObject.put("Long", new Long(0));
    	typeToObject.put("Short", new Integer(0));
    	typeToObject.put("String", new String());
    	typeToObject.put("Collection", new Vector());
    	typeToObject.put("List", new Vector());
    	typeToObject.put("Set", new HashSet());
    }
    public static Object createObject(String type) {
    	return typeToObject.get(type);
    }

    /** Maps types from model to Java default values as String */
    protected static Map typeToString = new HashMap();
    static {
    	typeToString.put("bool", "false");
    	typeToString.put("byte", "0");
    	typeToString.put("char", "0");
    	typeToString.put("double", "0");
    	typeToString.put("float", "0.0f");
    	typeToString.put("int", "0");
    	typeToString.put("long", "0");
    	typeToString.put("short", "0");
    	typeToString.put("string", "\"\"");

    	typeToString.put("Boolean", "false");
    	typeToString.put("Byte", "0");
    	typeToString.put("Char", "0");
    	typeToString.put("Double", "0");
    	typeToString.put("Float", "0.0f");
    	typeToString.put("Integer", "0");
    	typeToString.put("Long", "0");
    	typeToString.put("Short", "0");
    	typeToString.put("String", "\"\"");

    	typeToString.put("Collection", "new Vector()");
    	typeToString.put("List", "new Vector()");
    	typeToString.put("Set", "new HashSet()");

    	typeToString.put("UnlimitedInteger", "-1");
    	typeToString.put("UnboundedInteger", "-1");
    	typeToString.put("UnlimitedNatural", "-1");
    }
    public static String createString(String type) {
    	return (String)typeToString.get(type);
    }
}
