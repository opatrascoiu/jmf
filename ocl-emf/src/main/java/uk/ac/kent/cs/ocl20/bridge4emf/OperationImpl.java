package uk.ac.kent.cs.ocl20.bridge4emf;

import java.util.*;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author dha
 *
 */
public class OperationImpl implements Operation {
	OclProcessor processor;
	/**
	 * Constructor for Operation$Impl.
	 */
	public OperationImpl(EOperation op, OclProcessor proc) {
		_impl = op;
		this.processor = proc;
	}


	/** Wrapped EOperation */
	EOperation _impl;
	public EOperation getImpl() {
		return _impl;
	}
	public void setImpl(EOperation impl) {
		this._impl = impl;
	}

	//--- Return type ---
	Classifier _returnType=null;
	public Classifier getReturnType() {
		if (_impl==null) return _returnType;
		return this.processor.getBridgeFactory().buildClassifier(_impl.getEType());
	}
	public void setReturnType(Classifier cl) {
		_returnType = cl;
	}

	/** Parameter types */
	List _parameterTypes = null;
	public List getParameterTypes() {
		if (_parameterTypes == null) {
			_parameterTypes = new Vector();
			if (_impl != null) {
				Iterator i = _impl.getEParameters().iterator();
				while (i.hasNext()) {
					EParameter p = (EParameter) i.next();
					_parameterTypes.add(this.processor.getBridgeFactory().buildClassifier(p.getEType()));
				}
			}
		}
		return _parameterTypes;
	}
	public void setParameterTypes(List l) {
		_parameterTypes = l;
	}

	public void setParameterNames(List l) {
	}

	/** Parameter names */
	private List _parameterNames = null;
	public List getParameterNames() {
		if (_parameterNames == null) {
			_parameterNames = new Vector();
			if (_impl != null) {
				Iterator i = _impl.getEParameters().iterator();
				while (i.hasNext()) {
					EParameter p = (EParameter) i.next();
					_parameterNames.add(this.processor.getBridgeFactory().buildClassifier(p.getEType()));
				}
			}
		}
		return _parameterNames;
	}

	/** Name */
	String _name = null;
	public String getName() {
		if (_impl == null)
			return _name;
		return _impl.getName();
	}
	public void setName(String name) {
		_name = name;
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
		return "Operation("+getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new OperationImpl(_impl, processor);
	}
}
