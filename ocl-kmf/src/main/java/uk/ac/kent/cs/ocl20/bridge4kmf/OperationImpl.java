package uk.ac.kent.cs.ocl20.bridge4kmf;

import java.util.*;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author Octavian Patrascoiu
 *
 */

public class OperationImpl implements Operation {
	/**
	 * Constructor for Operation$Impl.
	 */
	/** Construct an Operation */
	public OperationImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation op, OclProcessor proc) {
		this.processor = proc;
		this.operation = op;
	}
	protected OclProcessor processor;

	/** Wrapped UML correspondent */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation operation;
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation getImpl() {
		return operation;
	}
	public void setImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Operation operation) {
		this.operation = operation;
	}

	/** Return type */
	Classifier returnType = null;
	public Classifier getReturnType() {
		if (operation != null) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier umlType = null;
			Iterator i = operation.getParameter().iterator();
			while (i.hasNext()) {
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter param = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter)i.next();
				if (param.getKind() == uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Data_Types.ParameterDirectionKind$Class.RETURN) {
					umlType = param.getType();
				}
			}
			returnType = processor.getBridgeFactory().buildClassifier(umlType);
		}
		return returnType;
	}
	public void setReturnType(Classifier cl) {
		if (operation == null) returnType = cl;
	}

	/** Parameter Types */
	List parameterTypes = new Vector();
	public List getParameterTypes() {
		if (operation != null) {
			parameterTypes = new Vector();
			if (parameterTypes == null) {
				if (operation != null) {
					Iterator i = operation.getParameter().iterator();
					while (i.hasNext()) {
						uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter param = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter)i.next();
						parameterTypes.add(processor.getBridgeFactory().buildClassifier(param.getType()));
					}
				}
			}
		}
		return parameterTypes;
	}
	public void setParameterTypes(List l) {
		if (operation == null) parameterTypes = l;
	}

	/** Parameter Names */
	List parameterNames = new Vector();
	public List getParameterNames() {
		if (operation != null) {
			parameterNames = new Vector();
			if (parameterNames == null) {
				if (operation != null) {
					Iterator i = operation.getParameter().iterator();
					while (i.hasNext()) {
						uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter param = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Parameter)i.next();
						parameterNames.add(param.getName().getBody_());
					}
				}
			}
		}
		return parameterNames;
	}
	public void setParameterNames(List l) {
		if (operation == null) parameterNames = l;
	}

	/** Get Name from implementation */
	String name = null;
	public String getName() {
		if (operation != null) name = operation.getName().getBody_();
		return name;
	}

	/** Set name */
	public void setName(String name) {
		if (operation == null) this.name = name;
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
		return new OperationImpl(operation, processor);
	}
}
