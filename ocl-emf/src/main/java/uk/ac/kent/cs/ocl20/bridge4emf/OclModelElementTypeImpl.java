package uk.ac.kent.cs.ocl20.bridge4emf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.semantics.model.types.*;
import uk.ac.kent.cs.ocl20.standard.types.*;

/**
 * @author dha
 *
 */
public class OclModelElementTypeImpl 
	extends OclAnyTypeImpl 
	implements OclModelElementType 
{
	/** Construct a Model Element Type */
	public OclModelElementTypeImpl(EClass impl, OclProcessor proc) {
		super(proc);
		_impl = impl;
		List oper = super.getOperations();
		oper.add(proc.getBridgeFactory().buildOperation(proc.getTypeFactory().buildBooleanType(), "=", new Classifier[] { this }));
		oper.add(proc.getBridgeFactory().buildOperation(proc.getTypeFactory().buildBooleanType(), "<>", new Classifier[] { this }));
		List oclAnyOper = ((TypeFactoryImpl)proc.getTypeFactory()).OCLANY_TYPE.getOperations();
		oper.addAll(oclAnyOper);
	}

	/** Wrapped EClass */
	private EClass _impl;
	public EClass getImpl() {
		return _impl;
	}
	public void setImpl(EClass class_) {
		this._impl = class_;
	}

	/** Lookup a property */
	private Map _properties = new HashMap();
	public Property lookupProperty(String name) {
		Property prop = (Property)_properties.get(name);
		if (prop == null) {
			if (_impl instanceof EClass) {
				//String emf_name = Ocl4Emf.adaptor.getGetterName(name);
				// getEStructuralFeature uses names from the model.
				return super.processor.getBridgeFactory().buildProperty(((EClass) _impl).getEStructuralFeature(name));
			} else if (_impl instanceof EEnum) {
			} else if (_impl instanceof EDataType) {
			}
		}
		return prop;
	}

	/** Lookup an operation */
	public Operation lookupOperation(String name, List types) {
		Operation o = super.lookupOperation(name, types);
		if (o==null) {
			Iterator i = _impl.getEAllOperations().iterator();
			while (i.hasNext()) {
				EOperation op = (EOperation) i.next();
				if (op.getName().equals(name))
					return new OperationImpl(op, super.processor);
			}
			return null;
		}
		return o;
	}

	/** Name */
	public String getName() {
		return _impl.getName();
	}

	/** Namespace */
	public Namespace getNamespace() {
		Namespace ns = super.getNamespace();
		if (ns == null) {
			ns = processor.getBridgeFactory().buildNamespace( _impl.getEPackage() );
			super.setNamespace(ns);
		}
		return ns;
	}

	/** Checks if this conforms to c */
	public Boolean conformsTo(Classifier c) {
		if (c instanceof OclAnyType)
			return Boolean.TRUE;
		if (_impl instanceof EClass) {
			if (c instanceof OclModelElementTypeImpl) {
				EClassifier cc = ((OclModelElementTypeImpl) c)._impl;
				EClass self = ((EClass) _impl);
				return new Boolean(self.getEAllSuperTypes().contains(cc));
			}
		} else if (_impl instanceof EEnum) {
		} else if (_impl instanceof EDataType) {
		}
		return null;
	}

	/** Get the delegate Class */
	public Object getDelegate() {
		return this;
	}

	/** Get the implementation Class */
	public Class getImplClass() {
		return _impl.getClass();
	}

	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this, obj);
	}
	
	/** ToString */
	public String toString() {
		return "OclModelElementType("+_impl.getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new OclModelElementTypeImpl(_impl, processor);
	}	
}
