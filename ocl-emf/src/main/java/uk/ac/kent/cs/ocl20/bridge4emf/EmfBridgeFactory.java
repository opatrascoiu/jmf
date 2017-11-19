package uk.ac.kent.cs.ocl20.bridge4emf;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author dha
 *
 */
public class EmfBridgeFactory extends BridgeFactoryImpl {
	/** Constructor */	
	public EmfBridgeFactory(OclProcessor proc) {
		super(proc);
	}
	
	/** Environment */
	public Environment buildEnvironment(EPackage ePkg) {
		Namespace ns = new NamespaceImpl(ePkg, super.processor);
		Environment env = super.buildEnvironment();
		return env.addNamespace(ns);
	}

	/** Namespace */
	public Namespace buildNamespace(Object o) {
		if (o instanceof EPackage)
			return this.buildNamespace((EPackage)o);
		else
			return null;
	}
	public Namespace buildNamespace(EPackage ePkg) {
		return new NamespaceImpl(ePkg, super.processor);
	}

	/** Operation */
	public Operation buildOperation(Classifier ret, String op_name, Classifier params[]) {
		Operation oper = new OperationImpl(null, super.processor);
		oper.setName(op_name);
		oper.setReturnType(ret);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Classifier paramType = (Classifier) params[i];
				//oper.getParameterNames().add(paramName);
				oper.getParameterTypes().add(paramType);
			}
		}
		return oper;
	}

	/** Property */
	public Property buildProperty(Object o) {
		if (o instanceof EStructuralFeature)
			return this.buildProperty((EStructuralFeature)o);
		else
			return null;
	}
	public Property buildProperty(EStructuralFeature sf) {
		return new PropertyImpl(sf, super.processor);
	}
	public Property buildProperty(Classifier ret, String name) {
		Property prop = new PropertyImpl(null, super.processor);
		prop.setName(name);
		prop.setType(ret);
		return prop;
	}

	/** Classifier */
	public Classifier buildClassifier(Object o) {
		if (o instanceof EClassifier)
			return this.buildClassifier((EClassifier)o);
		else
			return null;
	}
	public Classifier buildClassifier(EClassifier cls) {
		return (Classifier) buildModelElement(cls);
	}

	/** Model Element */
	public ModelElement buildModelElement(Object o) {
		if (o instanceof EClass) {
			return buildOclModelElementType((EClass)o);
		} else if (o instanceof EEnum) {
			return buildEnumeration_((EEnum) o);
		} else if (o instanceof EDataType) {
			if (((EDataType)o).getName().equals("EString"))
				return super.processor.getTypeFactory().buildStringType();
			else if (((EDataType)o).getName().equals("EInt"))
				return super.processor.getTypeFactory().buildIntegerType();
			else if (((EDataType)o).getName().equals("EReal"))
				return super.processor.getTypeFactory().buildRealType();
			else if (((EDataType)o).getName().equals("EFloat"))
				return super.processor.getTypeFactory().buildRealType();
			else if (((EDataType)o).getName().equals("EDouble"))
				return super.processor.getTypeFactory().buildRealType();
			else if (((EDataType)o).getName().equals("EBoolean"))
				return super.processor.getTypeFactory().buildBooleanType();
			else
				return null;
		}
		return null;
	}
	public OclModelElementType buildOclModelElementType(Object o) {
		if (o instanceof EClass)
			return buildOclModelElementType((EClass)o);
		else
			return null;
	}
	public OclModelElementType buildOclModelElementType(EClass ecls) {
		return new OclModelElementTypeImpl(ecls, super.processor);
	}
	
	/** Enumeration */
	public Enumeration_ buildEnumeration(Object o) {
		if (o instanceof EEnum)
			return buildEnumeration((EEnum)o);
		else
			return null;
	}
	public Enumeration_ buildEnumeration_(EEnum eenum) {
		return new EnumerationImpl(eenum, super.processor);
	}

	/** Enumeration Literal */
	public EnumLiteral buildEnumLiteral(Object o) {
		if (o instanceof EEnumLiteral)
			return buildEnumLiteral((EEnumLiteral)o);
		else
			return null;
	}
	public EnumLiteral buildEnumLiteral(EEnumLiteral eenumLit, Enumeration_ eenum) {
		return new EnumLiteralImpl(eenumLit, eenum);
	}
}
