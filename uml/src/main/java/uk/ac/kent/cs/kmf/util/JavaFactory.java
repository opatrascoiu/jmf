package uk.ac.kent.cs.kmf.util;

import java.util.*;

public class JavaFactory
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
    	typeToObject.put("StringBuffer", new String());
    	typeToObject.put("Collection", new Vector());
    	typeToObject.put("List", new Vector());
    	typeToObject.put("Set", new TreeSet());
    }
    public static Object createObject(String type) {
    	return typeToObject.get(type);
    }

    /** Maps types from model to Java default values as String */
    protected static Map typeToString = new HashMap();
    static {
    	typeToString.put("Boolean", "new Boolean(false)");
    	typeToString.put("Byte", "new Integer(0)");
    	typeToString.put("Character", "new Integer(0)");
    	typeToString.put("Double", "new Double(0)");
    	typeToString.put("Float", "new Float(0)");
    	typeToString.put("Integer", "new Integer(0)");
    	typeToString.put("Long", "new Long(0)");
    	typeToString.put("Short", "new Integer(0)");
    	typeToString.put("String", "new String()");
    	typeToString.put("StringBuffer", "new String()");
    	typeToString.put("Collection", "new "+Naming.collectionClass+"()");
    	typeToString.put("List", "new "+Naming.listClass+"()");
    	typeToString.put("Set", "new "+Naming.setClass+"()");

    	typeToString.put("UnlimitedInteger", "new Integer(-1)");
    	typeToString.put("UnboundedInteger", "new Integer(-1)");
    	typeToString.put("UnlimitedNatural", "new Integer(-1)");
    }
    public static String createString(String type) {
    	return (String)typeToString.get(type);
    }
}
