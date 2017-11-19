package uk.ac.kent.cs.ocl20.semantics.bridge;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.model.types.CollectionType;
import uk.ac.kent.cs.ocl20.semantics.model.types.OclAnyType;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.standard.lib.OclType;


public class ClassifierImpl 
implements 
	Classifier 
{
	/** Construct a Classifier */
	public ClassifierImpl(OclProcessor proc) {
		this.processor = proc;
	}

	protected OclProcessor processor;

	/** Properties field */
	protected List properties = new Vector();
	/** Get Properties field */
	public List getProperties() {
		return properties;
	}
	/** Set Properties field */
	public void setProperties(List properties) {
		this.properties = properties;
	}
	
	/** Search for a property with a given name */
	public Property lookupProperty(String name) {
		Iterator i = getProperties().iterator();
		while (i.hasNext()) {
			Property p = (Property) i.next();
			if (p.getName().equals(name))
				return p;
		}
		return null;
	}

	/** Initializes the operations associated with a type */
	public void createOperations(TypeFactory tf) {
	}
	
	/** Operations field */
	protected List operations = new Vector();
	/** Get Operations field */
	public List getOperations() {
		return operations;
	}
	/** Set Operations field */
	public void setOperations(List operations) {
		this.operations = operations;
	}
	/** Search for an operation with a given name and a list of parameter types */
	public Operation lookupOperation(String name, List types) {
		Operation op = lookupCachedOp(name, types);
		return op;
	}

	/** Search for an operation in local operations */
	protected Operation lookupCachedOp(String name, List types) {
		Iterator i = getOperations().iterator();
		while (i.hasNext()) {
			Operation op = (Operation) i.next();
			String opName = op.getName(); 
			if (opName.equals(name) && typesConform(op.getParameterTypes(), types))
				return op;
		}
		return null;
	}

	/** Check if a list of argument types conforms to a list of paramter types */ 
	protected boolean typesConform(List paramTypes, List argTypes) {
		if (paramTypes.size() != argTypes.size())
			return false;
		for (int i = 0; i < paramTypes.size(); i++) {
			Classifier paramType = (Classifier) paramTypes.get(i);
			Classifier argType = (Classifier) argTypes.get(i);
			if (argType.conformsTo(paramType) == Boolean.FALSE)
				return false;
		}
		return true;
	}

	/** Check if this (a Classifier) conforms to c */
	public Boolean conformsTo(Classifier c) {
		if (this.equals(c))
			return Boolean.TRUE;
		if (c.getClass() == OclAnyType.class && !(this instanceof CollectionType))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	/** Search for a given signal */
	public Signal lookupSignal(String name, List types) {
		return null;
	}

	public String getFullName(String sep) {
		if (getNamespace() == null) return getName();
		String parentName = getNamespace().getFullName(sep);
		if (parentName != null && parentName.length() != 0) 
			return parentName+sep+getName();
		else
			return getName();
	}

	/** Name field */
	protected String name = null;
	/** Get Name field */
	public String getName() {
		return name;
	}
	/** Set Name field */
	public void setName(String name) {
		this.name = name;
	}

	/** Namespace field */
	Namespace namespace;
	/** Get Namespace field */
	public Namespace getNamespace() {
		return namespace;
	}
	/** Set Namespace field */
	public void setNamespace(Namespace namespace) {
		this.namespace = namespace;
	}

	/** Get the current environment without parent */ 
	public Environment getEnvironmentWithoutParents() {
		Environment env = this.processor.getBridgeFactory().buildEnvironment();
		env.addNamespace(this);
		env.setParent(null);
		return env;
	}

	/** Get the current environment with parent */ 
	public Environment getEnvironmentWithParents() {
		if (this.getNamespace() == null) {
			return null;
		} else {
			Environment result = getEnvironmentWithoutParents();
			result.setParent(this.getNamespace().getEnvironmentWithParents());
			return result;
		}
	}

	/** Search for an owned element */ 
	public ModelElement lookupOwnedElement(String name) {
		return null;
	};

	/** Accept a Semantic visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	/** Clone */
	public Object clone() {
		return null;
	}


	/** Get the delegate */
	public Object getDelegate() {
		return java.lang.Class.class;
	}

	/** Get Java Class of the implementation */
	public Class getImplClass() {
		return OclType.class;
	}
}
