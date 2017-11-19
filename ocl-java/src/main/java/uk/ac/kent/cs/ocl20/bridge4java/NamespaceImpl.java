package uk.ac.kent.cs.ocl20.bridge4java;

import java.util.HashMap;
import java.util.Map;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.bridge.Environment;
import uk.ac.kent.cs.ocl20.semantics.bridge.ModelElement;
import uk.ac.kent.cs.ocl20.semantics.bridge.Namespace;

/**
 * @author dha
 *
 */
public class NamespaceImpl implements Namespace {
	protected OclProcessor _processor;
	/** Construct a Namespace */
	public NamespaceImpl(java.lang.Package ns, OclProcessor proc) {
		_processor = proc;
		_ns = ns;
	}

	/** Wrapped Java Package */
	java.lang.Package _ns;
	public java.lang.Package getImpl() {
		return _ns;
	}
	public void setImpl(java.lang.Package enum_) {
		this._ns = enum_;
	}

	/** Lookup owned elements */
	Map _elements = new HashMap();
	public ModelElement lookupOwnedElement(String name) {
		ModelElement mel = (ModelElement) _elements.get(name);
		if (mel == null) {
			String fullName = _ns.getName()+"."+name;
			try {
				java.lang.Class me = null;
				me = Class.forName(fullName);
				mel = _processor.getBridgeFactory().buildModelElement(me);
				_elements.put(name, mel);
				return mel;
			} catch (Exception e) {
			}
			// no class found, look up a package
			try {
				java.lang.Package p = Package.getPackage(fullName);
				mel = _processor.getBridgeFactory().buildNamespace(p);
				_elements.put(name, mel);
				return mel;
			} catch (Exception e) {
			}
		}
		return mel;
	}

	//--- Namespace ---
	Namespace _namespace = null;
	public Namespace getNamespace() {
		if (_namespace == null) {
			String name = _ns.getName();
			int lp = name.indexOf(".");
			if (lp==-1) return null;
			String ns_name = name.substring(0,lp);
			_namespace = new NamespaceImpl(Package.getPackage(ns_name), _processor);
		}
		return _namespace;
	}

	public void setNamespace(Namespace n) {
		_namespace = n;
	}

	/**
	 * @see ocl20.bridge.Namespace#getEnvironmentWithoutParents()
	 */
	public Environment getEnvironmentWithoutParents() {
		Environment env = this._processor.getBridgeFactory().buildEnvironment();
		env.addNamespace(this);
		env.setParent(null);
		return env;
	}

	/**
	 * @see ocl20.bridge.Namespace#getEnvironmentWithParents()
	 */
	public Environment getEnvironmentWithParents() {
		if (this.getNamespace() == null) {
			return null;
		} else {
			Environment result = getEnvironmentWithoutParents();
			result.setParent(this.getNamespace().getEnvironmentWithParents());
			return result;
		}
	}

	/** Name */
	public String getName() {
		return _ns.getName().replaceAll("[.]","::");
	}
	public String getFullName(String sep) {
		return _ns.getName().replaceAll("[.]",sep);
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
		return "Namespace("+getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new NamespaceImpl(_ns, _processor);
	}
}
