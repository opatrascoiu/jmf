package uk.ac.kent.cs.ocl20.bridge4kmf;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author Octavian Patrascoiu
 *
 */
public class KmfBridgeFactoryImpl 
	extends BridgeFactoryImpl 
{
	public KmfBridgeFactoryImpl(OclProcessor proc) {
		super(proc);
	}

	/** Build an Environment from a UML Model */
	public Environment buildEnvironment(uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Model model) {
		return buildEnvironment((uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package)model);
	}

	/** Build an Environment from a UML Package */
	public Environment buildEnvironment(uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package pkg) {
		Namespace ns = new NamespaceImpl(pkg, processor);
		Environment env = super.buildEnvironment();
		return env.addNamespace(ns);
	}

	/** Build a Namespace from a UML Namespace */
	public Namespace buildNamespace(Object o) {
		if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace)
			return buildNamespace((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace)o);
		else
			return null;
	}
	public Namespace buildNamespace(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace ns) {
		return new NamespaceImpl(ns, processor);
	}

	/** Build an Operation from name and types */
	public Operation buildOperation(Classifier ret, String op_name, Classifier params[]) {
		Operation oper = new OperationImpl(null, processor);
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

	/** Build a Property from a UML Structural Feature */
	public Property buildProperty(Object o) {
		if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature)
			return new PropertyImpl((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature)o, processor);
		else if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd)
			return new PropertyImpl((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd)o, processor);
		else
			return null;
	}
	public Property buildProperty(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.StructuralFeature sf) {
		return new PropertyImpl(sf, processor);
	}

	/** Build a Property from a UML Association Feature */
	public Property buildProperty(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.AssociationEnd aend) {
		return new PropertyImpl(aend, processor);
	}

	/** Build a Property from Name and Classifier */
	public Property buildProperty(Classifier ret, String name) {
		Property prop = new PropertyImpl(null, processor);
		prop.setName(name);
		prop.setType(ret);
		return prop;
	}

	/** Build a Classifier from a UML Classifier */
	public Classifier buildClassifier(Object o) {
		if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)
			return buildClassifier((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier)o);
		else
			return null;
	}
	public Classifier buildClassifier(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier cls) {
		Classifier type = null;
		String typeName = cls.getName().getBody_();
		Classifier oclAny = processor.getTypeFactory().buildOclAnyType();
		if (typeName.equals("Boolean")) type = processor.getTypeFactory().buildBooleanType();
		else if (typeName.equals("Integer")) type = processor.getTypeFactory().buildIntegerType(); 
		else if (typeName.equals("Real")) type = processor.getTypeFactory().buildRealType();
		else if (typeName.equals("String")) type = processor.getTypeFactory().buildStringType();
		else if (typeName.equals("Collection")) type = processor.getTypeFactory().buildCollectionType(oclAny);
		else if (typeName.equals("Bag")) type = processor.getTypeFactory().buildBagType(oclAny);
		else if (typeName.equals("List")) type = processor.getTypeFactory().buildSequenceType(oclAny);
		else if (typeName.equals("Sequence")) type = processor.getTypeFactory().buildSequenceType(oclAny);
		else if (typeName.equals("Set")) type = processor.getTypeFactory().buildSetType(oclAny);
		else if (typeName.equals("OrderedSet")) type = processor.getTypeFactory().buildOrderedSetType(oclAny);
		else type = (Classifier)buildModelElement(cls);
		return type;
	}

	/** Build a ModelElement from an Object */
	public ModelElement buildModelElement(Object obj) {
		uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement me = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement)obj;
		Classifier oclAny = processor.getTypeFactory().buildOclAnyType();
		if (me instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package) {
			return buildNamespace((uk.ac.ukc.cs.kmf.kmfstudio.uml.Model_Management.Package)me);
		} else if (me instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_) {
			return buildOclModelElementType((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_)me);
		} else if (me instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_) {
			return buildEnumeration((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_) me);
		} else if (me instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.DataType) {
			String typeName = me.getName().getBody_();
			if (typeName.equals("String"))
				return processor.getTypeFactory().buildStringType();
			else if (typeName.equals("Integer"))
				return processor.getTypeFactory().buildIntegerType();
			else if (typeName.equals("Boolean"))
				return processor.getTypeFactory().buildBooleanType();
			else if (typeName.equals("Double"))
				return processor.getTypeFactory().buildRealType();
			else if (typeName.equals("Real"))
				return processor.getTypeFactory().buildRealType();
			else if (typeName.equals("Collection")) 
				return processor.getTypeFactory().buildCollectionType(oclAny);
			else if (typeName.equals("Bag")) 
				return processor.getTypeFactory().buildBagType(oclAny);
			else if (typeName.equals("List")) 
				return processor.getTypeFactory().buildSequenceType(oclAny);
			else if (typeName.equals("Sequence")) 
				return processor.getTypeFactory().buildSequenceType(oclAny);
			else if (typeName.equals("Set")) 
				return processor.getTypeFactory().buildSetType(oclAny);
			else if (typeName.equals("OrderedSet")) 
				return processor.getTypeFactory().buildOrderedSetType(oclAny);
			else
				return null;
		}
		return null;
	}

	/** Build an ModelElementType from a UML Classifier */
	public OclModelElementType buildOclModelElementType(Object o)  {
		if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_)
			return buildOclModelElementType((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_)o);
		else
			return null;
	}
	public OclModelElementType buildOclModelElementType(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_ cls) {
		return new OclModelElementTypeImpl(cls, processor);
	}
	
	/** Build an Enumeration from a UML Enumeration */
	public Enumeration_ buildEnumeration(Object o) {
		if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_)
			return buildEnumeration((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_)o);
		else
			return null;
	}
	public Enumeration_ buildEnumeration(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_ enum_) {
		return new EnumerationImpl(enum_, processor);
	}

	/** Build a Enumeration Literal from a UML Enumeration Literal */ 
	public EnumLiteral buildEnumLiteral(Object o) {
		if (o instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral)
			return buildEnumLiteral((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral)o, null);
		else
			return null;
	}
	public EnumLiteral buildEnumLiteral(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral enumLit, Enumeration_ enum_) {
		return new EnumLiteralImpl(enumLit, enum_, processor);
	}
}
