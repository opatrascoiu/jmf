/**
 * 
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.ocl20.standard.types;

import java.util.List;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.bridge.BridgeFactory;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.bridge.ClassifierImpl;
import uk.ac.kent.cs.ocl20.semantics.bridge.Operation;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.semantics.model.types.VoidType;
import uk.ac.kent.cs.ocl20.standard.lib.OclUndefined;

/**
 * @author dha
 *
 */
public class VoidTypeImpl extends ClassifierImpl implements VoidType {
	/** Construct a VoidType */
	public VoidTypeImpl(OclProcessor proc) {
		super(proc);
	}

	public void createOperations(TypeFactory tf) {
		BridgeFactory bf = processor.getBridgeFactory();
		//--- Add operations for OCLVOID_TYPE ---
		getOperations().addAll( tf.buildBooleanType().getOperations() );
		// add others ???
	}

	/** Check if this (a TypeVoid) conforms to t2 */
	public Boolean conformsTo(Classifier t2) {
		return Boolean.TRUE;
	}

	/** Serach for an operation with a given name and signature */
	public Operation lookupOperation(String name, List types) {
		Operation op = super.lookupOperation(name, types);
		if (op==null) {
			//no operation found with this name
			// so return default operation that returns Unfefined.
			op = super.processor.getBridgeFactory().buildOperation(super.processor.getTypeFactory().buildVoidType(), "_default", (Classifier[])types.toArray(new Classifier[]{}) );
		}
		return op;
	}
	
	/** Set element type */
	public void setElementType(Classifier elementType) {
	}

	/** Get element type */
	public Classifier getElementType() {
		return this;
	}

	/** Get the delegate Class */
	public Object getDelegate() {
		return null;
	}

	/** Get the implementation Class */
	public Class getImplClass() {
		return OclUndefined.class;
	}

	/** Accept a Semantic Visitor */ 
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}
	
	/** ToString */
	public String toString(){
		return "VoidType";
	}
}
