package uk.ac.kent.cs.ocl20.bridge4java;

import java.util.*;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author dha
 *
 */
public class OperationImpl implements Operation {
	OclProcessor _processor;
	/** Construct an Operation */
	public OperationImpl(java.lang.reflect.Method op, OclProcessor proc) {
		_processor = proc;
		method = op;
	}

	/** Wrapped Java Class */
	protected java.lang.reflect.Method method;
	public java.lang.reflect.Method getImpl() {
		return method;
	}
	public void setImpl(java.lang.reflect.Method method) {
		this.method = method;
	}

	//--- Return type ---
	Classifier _returnType=null;
	public Classifier getReturnType() {
		if (method==null) return _returnType;
		return _processor.getBridgeFactory().buildClassifier(method.getReturnType());
	}
	public void setReturnType(Classifier cl) {
		_returnType = cl;
	}

	/** Parameter types */
	List _parameterTypes = null;
	public List getParameterTypes() {
		if (_parameterTypes == null) {
			_parameterTypes = new ArrayList();
			if (method != null) {
				Iterator i = Arrays.asList(method.getParameterTypes()).iterator();
				while (i.hasNext()) {
					java.lang.Class p = (java.lang.Class) i.next();
					_parameterTypes.add(_processor.getBridgeFactory().buildClassifier(p));
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
			_parameterNames = new ArrayList();
			if (method != null) {
				Iterator i = Arrays.asList(method.getParameterTypes()).iterator();
				while (i.hasNext()) {
					java.lang.Class p = (java.lang.Class) i.next();
					_parameterNames.add("a");
				}
			}
		}
		return _parameterNames;
	}

	/** Get delegate */
	public Object getDelegate() {
		return this.getClass();
	}

	/** Name */
	String _name = null;
	public String getName() {
		if (method == null)
			return _name;
		return method.getName();
	}
	public void setName(String name) {
		_name = name;
	}

	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	/** Clone */
	public Object clone() {
		return new OperationImpl(method, _processor);
	}
}
