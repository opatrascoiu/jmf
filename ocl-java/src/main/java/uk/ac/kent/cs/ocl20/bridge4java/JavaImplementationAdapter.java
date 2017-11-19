package uk.ac.kent.cs.ocl20.bridge4java;

import java.util.*;

import uk.ac.kent.cs.ocl20.semantics.bridge.*;

import uk.ac.kent.cs.ocl20.standard.lib.OclUndefined;
import uk.ac.kent.cs.ocl20.synthesis.*;

/**
 * @author dha
 *
 */
public class JavaImplementationAdapter
	implements ModelImplementationAdapter 
{
	public static JavaImplementationAdapter INSTANCE = new JavaImplementationAdapter();

	public String getGetterName(String property_name) {
		return "get"+property_name.substring(0,1).toUpperCase()+property_name.substring(1,property_name.length());
	}
	
	public String getSetterName(String property_name) {
		return null;
	}

	public String getModelPropertyName(String getter) {
		String result = getter.substring(3, getter.length());
		return result.substring(0, 1).toLowerCase() + result.substring(1, result.length());
	}
	
	public Object getEnumLiteralValue(EnumLiteral enumLit) {
		return ((EnumLiteralImpl)enumLit).getInstance();
	}

	public String getEnumLiteralValue(String enum_, String enumLit) {
		return enum_+"."+enumLit;
	}

	// Model element
	public boolean OclModelElement_equalTo(Object o1, Object o2) {
		return o1.equals(o2);
	}
	public boolean OclModelElement_oclIsNew(Object o1) {
		return false;
	}
	public boolean OclModelElement_oclIsUndefined(Object o1) {
		return o1 == null || o1 instanceof OclUndefined;
	}
	public Object OclModelElement_oclAsType(Object o1, Object o2) {
		return o1;
	}
	public boolean OclModelElement_oclIsTypeOf(Object o1, Object o2) {
		if (o1 instanceof OclModelElementType) o1 = ((OclModelElementType)o1).getImplClass();
		if (o2 instanceof OclModelElementType) o2 = ((OclModelElementType)o2).getImplClass(); 
		return o1.getClass() == o2.getClass();
	}
	public boolean OclModelElement_oclIsKindOf(Object o1, Object o2) {
		Class c1 = o1.getClass();
		Class c2 = o2.getClass();
		return c1.isAssignableFrom(c2);
	}
	public Set OclModelElement_allInstances(Object o1) {
		return new LinkedHashSet();
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
		return o1 == null;
	}
	public Object EnumLiteral_oclAsType(Object o1, Object o2) {
		return o1;
	}
	public boolean EnumLiteral_oclIsTypeOf(Object o1, Object o2) {
		return o1.getClass() == o2.getClass();
	}
	public boolean EnumLiteral_oclIsKindOf(Object o1, Object o2) {
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
