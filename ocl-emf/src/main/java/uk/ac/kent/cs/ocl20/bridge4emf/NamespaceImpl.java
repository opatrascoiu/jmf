package uk.ac.kent.cs.ocl20.bridge4emf;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author dha
 *
 */
public class NamespaceImpl implements Namespace {
	protected OclProcessor processor = null;
	/** Construct a Namespace */
	public NamespaceImpl(EPackage ePkg, OclProcessor proc) {
		this.processor = proc;
		_ePkg = ePkg;
	}

	/** Wrapped EPackage */
	protected EPackage _ePkg;
	public EPackage getImpl() {
		return _ePkg;
	}
	public void setImpl(EPackage pkg) {
		this._ePkg = pkg;
	}

	/** Lookup owned elements */
	Map _elements = new HashMap();
	public ModelElement lookupOwnedElement(String name) {
		ModelElement mel = (ModelElement) _elements.get(name);
		if (mel == null) {
			EClassifier ecl = _ePkg.getEClassifier(name);
			if (ecl == null)
				return null;
			else {
				mel = this.processor.getBridgeFactory().buildClassifier(ecl);
				_elements.put(name, mel);
			}
		}
		return mel;
	}

	//--- Namespace ---
	Namespace _namespace = null;
	public Namespace getNamespace() {
		if (_namespace == null)
			_namespace = new NamespaceImpl(_ePkg.getESuperPackage(), this.processor);
		return _namespace;
	}
	public void setNamespace(Namespace n) {
		_namespace = n;
	}

	/**
	 * @see ocl20.bridge.Namespace#getEnvironmentWithoutParents()
	 */
	public Environment getEnvironmentWithoutParents() {
		Environment env = this.processor.getBridgeFactory().buildEnvironment();
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
		return _ePkg.getName();
	}
	public String getFullName(String sep) {
		String name = "";
		EPackage pkg = _ePkg.getESuperPackage();
		while (pkg != null) {
			if (!name.equals("")) name = sep+name;
			name = pkg.getName()+name;
			pkg = pkg.getESuperPackage();
		}
		if (!name.equals("")) name += sep;
		return name+_ePkg.getName();
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
		return new NamespaceImpl(_ePkg, this.processor);
	}
}
