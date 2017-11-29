package uk.ac.kent.cs.kmf.util;

import java.util.*;

public class Type 
{
	//
	// Java section
	//
    /** Primitive types */
    protected static Set javaPrimitiveTypes = new HashSet();
    static {
    	javaPrimitiveTypes.add("void"); 
    	javaPrimitiveTypes.add("boolean"); 
    	javaPrimitiveTypes.add("byte"); 
    	javaPrimitiveTypes.add("char"); 
    	javaPrimitiveTypes.add("short"); 
    	javaPrimitiveTypes.add("int"); 
    	javaPrimitiveTypes.add("long"); 
    	javaPrimitiveTypes.add("float"); 
    	javaPrimitiveTypes.add("double");
    	 
    	javaPrimitiveTypes.add("Object"); 
    	javaPrimitiveTypes.add("Boolean"); 
    	javaPrimitiveTypes.add("Byte"); 
    	javaPrimitiveTypes.add("Character"); 
    	javaPrimitiveTypes.add("Short");
    	javaPrimitiveTypes.add("Integer"); 
    	javaPrimitiveTypes.add("Long"); 
    	javaPrimitiveTypes.add("Float"); 
    	javaPrimitiveTypes.add("Double"); 
    	javaPrimitiveTypes.add("String");
    	javaPrimitiveTypes.add("StringBuffer");
    }
    public static boolean isPrimitiveType(String name) {
    	return javaPrimitiveTypes.contains(name);
    }
    
    /** Collection types */
    protected static Set collectionTypes = new HashSet();
    static {
    	collectionTypes.add("Collection"); 
    	collectionTypes.add("List"); 
    	collectionTypes.add("Set"); 
    }
    public static boolean isCollectionType(String name) {
    	return collectionTypes.contains(name);
    }

    /** Maps model types to Java types */
    protected static Map modelToJava = new HashMap();
    static {
    	modelToJava.put("void", "void");
    	modelToJava.put("boolean", "boolean");
    	modelToJava.put("byte", "byte");
    	modelToJava.put("char", "char");
    	modelToJava.put("double", "double");
    	modelToJava.put("float", "float");
    	modelToJava.put("int", "int");
    	modelToJava.put("long", "long");
    	modelToJava.put("short", "short");

    	modelToJava.put("Object", "Object");
    	modelToJava.put("Boolean", "Boolean");
    	modelToJava.put("Byte", "Byte");
    	modelToJava.put("Character", "Character");
    	modelToJava.put("Double", "Double");
    	modelToJava.put("Float", "Float");
    	modelToJava.put("Integer", "Integer");
    	modelToJava.put("Long", "Long");
    	modelToJava.put("Short", "Short");
    	modelToJava.put("String", "String");
    	modelToJava.put("StringBuffer", "String");

    	modelToJava.put("UnlimitedInteger", "Integer");
    	modelToJava.put("UnboundedInteger", "Integer");
    	modelToJava.put("UnlimitedNatural", "Integer");

    	modelToJava.put("Collection", "java.util.Collection");
    	modelToJava.put("List", "java.util.List");
    	modelToJava.put("Set", "java.util.Set");
    }
    public static String getJavaType(String type) {
    	String javaType = (String)modelToJava.get(type);
    	return javaType;
    }

    /** Check dynamic type */
    public static boolean isInstanceofPrimitiveType(Object obj) {
		return obj instanceof Boolean || obj instanceof Byte || obj instanceof Character ||
	            obj instanceof Double || obj instanceof Float || obj instanceof Integer ||
	            obj instanceof Long || obj instanceof Short || obj instanceof String ||
	            obj instanceof StringBuffer;
	};
    public static boolean isInstanceofCollectionType(Object obj) {
		return obj instanceof java.util.Collection || obj instanceof java.util.List || obj instanceof java.util.Set;
	}
	
	//
	// C# Section
	//
    /** C# Primitive types */
    protected static Set csPrimitiveTypes = new HashSet();
    static {
    	csPrimitiveTypes.add("void"); 
    	csPrimitiveTypes.add("bool"); 
    	csPrimitiveTypes.add("byte"); 
    	csPrimitiveTypes.add("char"); 
    	csPrimitiveTypes.add("short"); 
    	csPrimitiveTypes.add("int"); 
    	csPrimitiveTypes.add("long"); 
    	csPrimitiveTypes.add("float"); 
    	csPrimitiveTypes.add("double");
    	csPrimitiveTypes.add("string");
    }
    public static boolean isCSPrimitiveType(String name) {
    	return csPrimitiveTypes.contains(name);
    }
    
    /** Maps model types to C# types */
    protected static Map modelToCS = new HashMap();
    static {
    	modelToCS.put("void", "void");
    	modelToCS.put("boolean", "bool");
    	modelToCS.put("byte", "byte");
    	modelToCS.put("char", "char");
    	modelToCS.put("double", "double");
    	modelToCS.put("float", "float");
    	modelToCS.put("int", "int");
    	modelToCS.put("long", "long");
    	modelToCS.put("short", "short");

    	modelToCS.put("Object", "object");
    	modelToCS.put("Boolean", "bool");
    	modelToCS.put("Byte", "byte");
    	modelToCS.put("Character", "char");
    	modelToCS.put("Double", "double");
    	modelToCS.put("Float", "float");
    	modelToCS.put("Integer", "int");
    	modelToCS.put("Long", "long");
    	modelToCS.put("Short", "short");
    	modelToCS.put("String", "string");
    	modelToCS.put("StringBuffer", "string");

    	modelToCS.put("UnlimitedInteger", "int");
    	modelToCS.put("UnboundedInteger", "int");
    	modelToCS.put("UnlimitedNatural", "int");

    	modelToCS.put("Collection", "Collection");
    	modelToCS.put("List", "List");
    	modelToCS.put("Set", "Set");
    }
    public static String getCSType(String type) {
    	String csType = (String)modelToCS.get(type);
    	return csType;
    }
}
