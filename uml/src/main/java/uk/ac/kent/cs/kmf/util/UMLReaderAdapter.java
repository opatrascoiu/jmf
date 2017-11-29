package uk.ac.kent.cs.kmf.util;

import java.util.*;
import java.lang.reflect.*;

import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.Naming;
import uk.ac.kent.cs.kmf.xmi.*;

import uk.ac.ukc.cs.kmf.kmfstudio.uml.repository.*;
import uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.*;

/**
  * This class represents a reader modelImplAdapter that can be used to create metaUML classes
  * from an XMI file.
  */
public class UMLReaderAdapter extends ReaderAdapter implements IReaderAdapter {
    /** 
      * Abstract factory for metaUML concepts 
      */
    protected UmlRepository repository;
    protected ILog log;
    protected boolean debug = false;

    /** 
      * Abstract factory for metaUML concepts 
      */
    public UMLReaderAdapter(UmlRepository r, ILog l) {
        repository = r;
        log = l;
    }

    /** 
      * This method is called everytime a CreateObject event appears during 
      * the XMI file parsing
      */
    public Object createObject(ObjectInfo info) {
		// References to other objects is the XMI framework responsability
		if (info.getIdref() != null) return null;
		// Use the repository to create object
		else {
			// Get a clean xmiName
			String objName = info.getXMIName();
			if (info.getXMIVersion().equals("1.2")) objName = Naming.getUMLPathName(objName);
			objName = Naming.getCleanName(objName);
			// Choose the right way to create the object
			if (objName.equals("Collection_")) return new Vector();
			if (objName.equals("List")) return new Vector();
			if (objName.equals("Set_")) return new HashSet();
			Object obj = repository.buildElement("uml."+objName);
			// Debug info
			if (obj == null) {
				log.reportWarning("Cannot create an instance of '"+objName+"'");
			} else {
				if (debug) log.reportMessage("Create an instance of '"+objName+"'");
			}
			return obj;
        }
    }
    
    /** 
      * This method is called everytime a CreateProperty event appears
      * during XMI parsing
       */
    public Object createProperty(FeatureInfo info) {
    	// Get a clean xmiName
    	String propertyName = Naming.getCleanName(info.getXMIName());
        // References to other objects is the XMI framework responsability
        if (info.getValue() == null) return propertyName;
        // Debug info
        if (debug) {
        	Object object = info.getObject();
        	Object value = info.getValue();
	        String objStr = ""+object.getClass();
    	    String valueStr = (value instanceof String) ? (String)value : ""+value.getClass();
        	String message = "Create property '"+propertyName+"' into '"+objStr+"' with value '"+valueStr+"'";
        	log.reportMessage(message);
        }
        // Set the property
        setPropertyValue(info.getObject(), propertyName, info.getValue());
        return null;
    }

    /** 
      * This method is called everytime a ResolveValue event appears
      * during XMI parsing
      */
    public void resolveValue(Object object, Object propertyName, Object value) {
        // Debug info
        if (debug) {
	        String objStr = ""+object.getClass();
    	    String valueStr = ""+value.getClass();
			String message = "Resolve property '"+propertyName+"' from '"+objStr+"' to '"+valueStr+"'";
        	log.reportMessage(message);
        }
        // Set property
        setPropertyValue(object, (String)propertyName, value);
    }
    
    /** 
      * This method is called everytime a SetProperty event appears
      * during XMI parsing
      */
    void setPropertyValue(Object object, String name, Object value) {
        // Comnpute the property's name
        String propertyName = name;
        if (propertyName.indexOf(".")!=-1) 
        	propertyName = propertyName.substring(propertyName.lastIndexOf(".")+1, propertyName.length());
        // Comnpute the property's type using the return type of get method
        String getterName = Naming.getGetter(propertyName);
        Method getterMethod=null;
        Class propertyType=null;
        try {
            getterMethod = object.getClass().getMethod(getterName, new Class[] {});
            propertyType = getterMethod.getReturnType();
        } catch (Exception e) {
            log.reportWarning("Cannot find property '"+getterName+"'", e);
        }
        // Set the property to the specific value
        String setterName = Naming.getSetter(propertyName);
        try {
            // Property is a Collection
            if (Collection.class.isAssignableFrom(propertyType)) {
                ((Collection)getterMethod.invoke(object, new Object[]{})).add(value);
            // Other type
            } else {
                // Get the set method
                Method setterMethod = object.getClass().getMethod(setterName, new Class[] {propertyType});
            	// Property is a String
            	if (propertyType == String.class) {
                    setterMethod.invoke(object, new Object[]{(String)value});
            	// Property is a String
            	} else if (propertyType == StringBuffer.class) {
                    setterMethod.invoke(object, new Object[]{(String)value});
                // Return type is Boolean
            	} else if (propertyType == Boolean.class) {
                    setterMethod.invoke(object, new Object[]{Boolean.valueOf((String)value)});
                // Return type is Integer
                } else if (propertyType == Integer.class) {
                    setterMethod.invoke(object, new Object[]{Integer.valueOf((String)value)});
                // Return type is Real
                } else if (propertyType == Double.class) {
                    setterMethod.invoke(object, new Object[]{Double.valueOf((String)value)});
                // Return type is a Name
                } else if (propertyType == Name.class) {
                    // Build the name
                    Name n = (Name)repository.buildElement("uml.Foundation.Data_Types.Name");
                    // Set the value
                    n.setBody_(new String((String)value));
                    setterMethod.invoke(object, new Object[]{n});
                // Return type is a Kind enumeration
                } else if (propertyType.getName().endsWith("Kind")) {
                    Object o = getEnum(propertyType, (String)value);
                    setterMethod.invoke(object, new Object[]{o});
                // Other return type
                } else {
                    setterMethod.invoke(object, new Object[]{value});
                }
            }
        } catch (Exception e) {
            String message = "Cannot invoke mutator '"+propertyName+"("+propertyType+")'\n";
	        message += "this = "+object+"\n";
	        if (value != null) message += "argument = '"+value.getClass()+"("+value+")'";
    	    else message += "argument = '(null)'";
    	    	
			log.reportWarning(message, e);
        }
    }

    /** 
      * Get the enumerator that must be set
      */ 
    Object getEnum(Class type, String name) {
        // Get enumerator name
        String enumerator = name.toUpperCase();
        // Get the enumeration name
        String enumeration = type.getName();
        enumeration = Naming.getClassName(enumeration);
        // Get the enumerator value
        try {
            java.lang.Class enumClass = Class.forName(enumeration);
            Field field = enumClass.getField(enumerator);
            return field.get(null);
        } catch (Exception e) {
            log.reportWarning("Cannot find field '"+enumerator+"' in enumeration '"+enumeration+"'", e);
        }
        return null;
    }
}
