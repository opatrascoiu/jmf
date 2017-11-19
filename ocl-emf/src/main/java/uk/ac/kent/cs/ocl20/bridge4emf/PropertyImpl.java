package uk.ac.kent.cs.ocl20.bridge4emf;


import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author dha
 *
 */
public class PropertyImpl implements Property {
	private OclProcessor processor;
	/**
	 * Constructor for Property$Impl.
	 */
	public PropertyImpl(EStructuralFeature sf, OclProcessor proc) {
		_impl = sf;
		this.processor = proc;
	}

	/** Wrapped EStructuralFeature */
	private EStructuralFeature _impl;
	public EStructuralFeature getImpl() {
		return _impl;
	}
	public void setImpl(EStructuralFeature impl) {
		this._impl = impl;
	}

	/** Get Property type */
	Classifier _type=null;
	public Classifier getType() {
		if (_type == null) {
			EClassifier etype = _impl.getEType();
			Classifier type = this.processor.getBridgeFactory().buildClassifier(etype);
			if (_impl.isMany()) {
				if (_impl.isUnique()) {
					_type = this.processor.getTypeFactory().buildSetType(type);
				} else {
					_type = this.processor.getTypeFactory().buildBagType(type);
				}
			} else {
				_type = type;
			}
		}
		return _type; 
	}

	/** Set type */
	public void setType(Classifier type) {
		_type = type;
	}

	/** Name */
	public String getName() {
		return _impl.getName();
	}
	public void setName(String name) {
	}

	/** Get delegate */
	public Object getDelegate() {
		return this.getClass();
	}
	
	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	/** ToString */
	public String toString() {
		return "Property("+getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new PropertyImpl(_impl, processor);
	}
}
