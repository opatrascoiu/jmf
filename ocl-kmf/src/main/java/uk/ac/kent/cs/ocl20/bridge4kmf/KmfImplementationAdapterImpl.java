package uk.ac.kent.cs.ocl20.bridge4kmf;

import java.util.*;
import java.lang.reflect.*;

import uk.ac.kent.cs.kmf.common.*;
import uk.ac.kent.cs.kmf.util.Naming;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.standard.lib.OclUndefined;
import uk.ac.kent.cs.ocl20.standard.types.OclAnyTypeImpl;
import uk.ac.kent.cs.ocl20.synthesis.*;

/**
 * @author Octavian Patrascoiu
 *
 */
public class KmfImplementationAdapterImpl implements ModelImplementationAdapter {
	public static KmfImplementationAdapterImpl INSTANCE = new KmfImplementationAdapterImpl();
	
	/** Compute getter name from property name */
	public String getGetterName(String property_name) {
		return "get"+property_name.substring(0,1).toUpperCase()+property_name.substring(1,property_name.length());
	}

	public String getSetterName(String property_name) {
		return "set"+property_name.substring(0,1).toUpperCase()+property_name.substring(1,property_name.length());
	}

	/** Compute property name from getter name */
	public String getModelPropertyName(String getter) {
		String result = getter.substring(3, getter.length());
		return result.substring(0, 1).toLowerCase() + result.substring(1, result.length());
	}

	/** Get the value of an enumeration literal */
	protected String getFullEnumerationName(Enumeration_ enum_) {
		return Naming.getFullClassifierName(((EnumerationImpl)enum_).enum_)+"$Class";
	}
	public Object getEnumLiteralValue(EnumLiteral enumLit) {
		//--- Get enumeration name ---
		String fullClassName = getFullEnumerationName(enumLit.getEnumeration());
		//--- Find class ---
		try {
			//--- Get the class ---
			Class enumClass = Class.forName(fullClassName);
			//--- Get the field ---
			Field field = enumClass.getField(enumLit.getName());
			Object obj = enumClass.newInstance(); 
			Object value = field.get(obj);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* Computes the string to be printed by the code generator */
	public String getEnumLiteralValue(String enum_, String enumLit) {
		return enum_+"$Class"+"."+enumLit;
	}

	// Model element
	public boolean OclModelElement_equalTo(Object o1, Object o2) {
		return o1.equals(o2);
	}
	public boolean OclModelElement_oclIsNew(Object o1) {
		return false;
	}
	public boolean OclModelElement_oclIsUndefined(Object o1) {
		if (o1 == null) return true;
		if (o1 instanceof OclUndefined) return true;
		return false;
	}
	
//	public OclBoolean oclIsKindOf(Classifier type) {
//		if (type instanceof BooleanType) return adapter.Boolean(true); 
//		if (type.getClass() == OclAnyTypeImpl.class) return adapter.Boolean(true);;
//		return adapter.Boolean(false);
//	}
//
//	public OclBoolean oclIsTypeOf(Classifier type) {
//		if (type instanceof BooleanType) return adapter.Boolean(true); 
//		return adapter.Boolean(false);
//	}
//
//	public Object oclAsType(Classifier type) {
//		if (type instanceof BooleanType) return this; 
//		if (type.getClass() == OclAnyTypeImpl.class) return this;
//		return adapter.Undefined();
//	}

	protected Class getInterface(Class cls) {
		if (cls.isInterface()) return cls;
		String name = cls.getName();
		int i = name.lastIndexOf("$");
		if (i != -1) name = name.substring(0, i);
		try {
			return Class.forName(name);
		} catch (Exception e) {
			return cls;
		}
	}
	protected Class getJavaClass(Object o) {
		if (o instanceof OclModelElementTypeImpl) {
			OclModelElementType type = (OclModelElementTypeImpl)o;
			String classPathName = type.getFullName(".");
			try {
				return getInterface(Class.forName(classPathName));
			} catch(Exception e) {
				return getInterface(o.getClass());
			}
		} else {
			return getInterface(o.getClass());
		}
	}
	public boolean OclModelElement_oclIsKindOf(Object o1, Object o2) {
		if (o2 instanceof OclAnyTypeImpl && ((OclAnyTypeImpl)o2).getClass() == OclAnyTypeImpl.class) 
			return true;
		// Get the Java Class for the interface
		Class c1 = getJavaClass(o1);
		Class c2 = getJavaClass(o2); 
		boolean flag = c2.isAssignableFrom(c1); 
		return flag;
	}
	public boolean OclModelElement_oclIsTypeOf(Object o1, Object o2) {
		// Get the Java Class for the interface
		Class c1 = getJavaClass(o1);
		Class c2 = getJavaClass(o2); 
		boolean flag = c1 == c2; 
		return flag;
	}
	public Object OclModelElement_oclAsType(Object o1, Object o2) {
		if (OclModelElement_oclIsKindOf(o1, o2)) return o1;
		else return null;
	}
	protected Warehouse warehouse = new WarehouseImpl(new OutputStreamLog(System.out));
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public Set OclModelElement_allInstances(Object o) {
		Set result = new LinkedHashSet();
		if (o instanceof String) {
			result.addAll(warehouse.getElements((String)o));
		} else if (o instanceof OclAnyTypeImpl) {
			// Return all
			return new LinkedHashSet(warehouse.getAllElements());
		} else if (o instanceof OclModelElementTypeImpl) {
			// Return all
			return new LinkedHashSet(warehouse.getAllElements());
		}
		return result;
	}
	public Object OclModelElement_createInstance(String pathName) {
		return warehouse.buildElement(pathName);
	}
	public void OclModelElement_deleteInstance(Object obj) {
		// Invoke delete
		try {
			Class cls = obj.getClass();
			Method method = cls.getMethod("delete", new Class[]{});
			method.invoke(obj, new Object[]{});
		} catch(Exception e) {
			e.printStackTrace();
		}
		// Remove it from repository
		String key = obj.toString();
		int pos = key.indexOf('\'');
		if (pos != -1) key = key.substring(0, pos).trim();
		warehouse.removeElement(key, obj);
	}

	//--- Enumeration
	public boolean EnumLiteral_equalTo(Object e1, Object e2) {
		if (e1 instanceof EnumLiteral &&  e2 instanceof EnumLiteral) {
			EnumLiteral lit1 = (EnumLiteral)e1;
			EnumLiteral lit2 = (EnumLiteral)e2;
			Enumeration_ enum1 = lit1.getEnumeration();
			Enumeration_ enum2 = lit1.getEnumeration();
			if (!enum1.equals(enum2)) return false;
			return lit1.getName().equals(lit2.getName());
		}
		return false;
	}	
	public boolean EnumLiteral_oclIsNew(Object o1) {
		return false;
	}
	public boolean EnumLiteral_oclIsUndefined(Object o1) {
		return o1 == null || o1 instanceof OclUndefined;
	}
	public Object EnumLiteral_oclAsType(Object o1, Object o2) {
		return o1;
	}
	public boolean EnumLiteral_oclIsTypeOf(Object o1, Object o2) {
		if (o1 instanceof OclModelElementType) o1 = ((OclModelElementType)o1).getImplClass();
		if (o2 instanceof OclModelElementType) o2 = ((OclModelElementType)o2).getImplClass(); 
		if (o1 instanceof OclAnyTypeImpl) o1 = ((OclAnyTypeImpl)o1).getImplClass();
		if (o2 instanceof OclAnyTypeImpl) o2 = ((OclAnyTypeImpl)o2).getImplClass(); 
		return o1.getClass() == o2.getClass();
	}
	public boolean EnumLiteral_oclIsKindOf(Object o1, Object o2) {
		if (o1 instanceof OclModelElementType) o1 = ((OclModelElementType)o1).getImplClass();
		if (o2 instanceof OclModelElementType) o2 = ((OclModelElementType)o2).getImplClass(); 
		if (o1 instanceof OclAnyTypeImpl) o1 = ((OclAnyTypeImpl)o1).getImplClass();
		if (o2 instanceof OclAnyTypeImpl) o2 = ((OclAnyTypeImpl)o2).getImplClass(); 
		Class c1 = o1.getClass();
		Class c2 = o2.getClass();
		return c1.isAssignableFrom(c2);
	}
	public Set EnumLiteral_allInstances(Object o1) {
		return new LinkedHashSet();
	}

	// Get the inmplementation 
	public Object getImpl(ModelElement element) {
		return null;
	}
}
