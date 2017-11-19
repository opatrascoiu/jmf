package uk.ac.kent.cs.ocl20.bridge4kmf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author Octavian Patrascoiu
 *
 */
public class NamespaceImpl 
	implements Namespace 
{
	/** Construct a Namespace */
	public NamespaceImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace ns, OclProcessor proc) {
		this.processor = proc;
		this.ns = ns;
		bridgeFactory = proc.getBridgeFactory();
	}
	protected OclProcessor processor;

	/** Wrapped UML correspondent */
	uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace ns;
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace getImpl() {
		return ns;
	}
	public void setImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Namespace ns) {
		this.ns = ns;
	}

	/** Search for a given owned element */
	Map ownedElements = new HashMap();
	public ModelElement lookupOwnedElement(String name) {
		ModelElement mel = (ModelElement) ownedElements.get(name);
		if (mel == null) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement me = null;
			if (ns == null) return null;
			Iterator i = ns.getOwnedElement().iterator();
			while (i.hasNext()) {
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement me2 = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.ModelElement)i.next();
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name umlName = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.Name)me2.getName(); 
				if (umlName != null) {
					 String body = umlName.getBody_();
					 if (body != null) {
					 	if (body.equals(name)) {
					 		me = me2;
					 		break;
					 	}
					 }
				}
			}
			if (me == null)
				return null;
			else {
				mel = processor.getBridgeFactory().buildModelElement(me);
				ownedElements.put(name, mel);
			}
		}
		return mel;
	}

	/** Sorrounding Namespace */
	Namespace _namespace = null;
	public Namespace getNamespace() {
		if (ns != null) _namespace = new NamespaceImpl(ns.getNamespace_(), processor);
		return _namespace;
	}
	public void setNamespace(Namespace n) {
		if (ns == null) _namespace = n;
	}

	/** Get Environment without parents */ 
	public Environment getEnvironmentWithoutParents() {
		Environment env = processor.getBridgeFactory().buildEnvironment();
		env.addNamespace(this);
		env.setParent(null);
		return env;
	}

	/** Get Environment with parents */ 
	public Environment getEnvironmentWithParents() {
		if (this.getNamespace() == null) {
			return null;
		} else {
			Environment result = getEnvironmentWithoutParents();
			result.setParent(this.getNamespace().getEnvironmentWithParents());
			return result;
		}
	}

	/** Get name from implementation */
	protected String name = null;
	public String getName() {
		if (ns != null) name = ns.getName().getBody_();
		return name;
	}

	/** Get name from implementation */
	public String getFullName(String sep) {
		return this._namespace.getName().replaceAll("[.]",sep);
	}

	/** Set name */
	public void setName(String name) {
		if (ns == null) this.name = name;
	}

	/** Get delegate */
	public Object getDelegate() {
		return this.getClass();
	}
	
	/** Accept a Semantics Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	/** ToString */
	public String toString() {
		return "Namespace("+getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new NamespaceImpl(ns, processor);
	}
	
	/** A BridgeFactory */
	protected BridgeFactory bridgeFactory = null;
}
