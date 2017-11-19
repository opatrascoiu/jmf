package uk.ac.kent.cs.ocl20.synthesis;

import java.util.Set;

import uk.ac.kent.cs.ocl20.semantics.bridge.EnumLiteral;
import uk.ac.kent.cs.ocl20.semantics.bridge.ModelElement;

/**
 * @author dha
 *
 */
public interface ModelImplementationAdapter {
	public Object getImpl(ModelElement me);
	
	public String getGetterName(String property_name);
	public String getSetterName(String property_name);
	public String getModelPropertyName(String string);
	public Object getEnumLiteralValue(EnumLiteral enumLit);
	public String getEnumLiteralValue(String enumName, String enumLit);
	
	public boolean OclModelElement_equalTo(Object o1, Object o2);
	public boolean OclModelElement_oclIsNew(Object o1);
	public boolean OclModelElement_oclIsUndefined(Object o1);
	public Object OclModelElement_oclAsType(Object o1, Object o2);
	public boolean OclModelElement_oclIsTypeOf(Object o1, Object o2);
	public boolean OclModelElement_oclIsKindOf(Object o1, Object o2);
	public Set OclModelElement_allInstances(Object o1);
	
	public boolean EnumLiteral_equalTo(Object e1, Object e2);
	public boolean EnumLiteral_oclIsNew(Object o1);
	public boolean EnumLiteral_oclIsUndefined(Object o1);
	public Object EnumLiteral_oclAsType(Object o1, Object o2);
	public boolean EnumLiteral_oclIsTypeOf(Object o1, Object o2);
	public boolean EnumLiteral_oclIsKindOf(Object o1, Object o2);
	public Set EnumLiteral_allInstances(Object o1);
}
