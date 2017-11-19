package uk.ac.kent.cs.ocl20.semantics.bridge;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.standard.types.TypeConformance;

/**
 * @author dha
 *
 */
public abstract class DataTypeImpl extends ClassifierImpl implements DataType {
	/** Construct a DataType */
	public DataTypeImpl(OclProcessor proc) {
		super(proc);
	}

	/** Check if this (a DataType) conforms with c */
	public Boolean conformsTo(Classifier t2) {
		return TypeConformance.isAssignableTo(this, t2) ? Boolean.TRUE : Boolean.FALSE;
	}

	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	/** ToString */
	public String toString(){
		return "DataType";
	}
}
