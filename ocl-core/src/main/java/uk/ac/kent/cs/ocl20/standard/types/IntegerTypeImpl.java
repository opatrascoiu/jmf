package uk.ac.kent.cs.ocl20.standard.types;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.bridge.BridgeFactory;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.model.types.IntegerType;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.standard.lib.OclInteger;

/**
 * @author dha
 *
 */
public class IntegerTypeImpl extends RealTypeImpl implements IntegerType {
	/** Construct a IntegerType */	
	public IntegerTypeImpl(OclProcessor proc) {
		super(proc);
	}

	public void createOperations(TypeFactory tf) {
		BridgeFactory bf = processor.getBridgeFactory();
		//--- Add operations for INTEGER_TYPE ---
		getOperations().add(bf.buildOperation(tf.buildIntegerType(), "-", new Classifier[] {}));
		getOperations().add(bf.buildOperation(tf.buildIntegerType(), "+", new Classifier[] { tf.buildIntegerType() }));
		getOperations().add(bf.buildOperation(tf.buildIntegerType(), "-", new Classifier[] { tf.buildIntegerType() }));
		getOperations().add(bf.buildOperation(tf.buildIntegerType(), "*", new Classifier[] { tf.buildIntegerType() }));
		getOperations().add(bf.buildOperation(tf.buildRealType(), "/", new Classifier[] { tf.buildIntegerType() }));
		getOperations().add(bf.buildOperation(tf.buildIntegerType(), "abs", new Classifier[] {}));
		getOperations().add(bf.buildOperation(tf.buildIntegerType(), "div", new Classifier[] { tf.buildIntegerType() }));
		getOperations().add(bf.buildOperation(tf.buildIntegerType(), "mod", new Classifier[] { tf.buildIntegerType() }));
		getOperations().add(bf.buildOperation(tf.buildIntegerType(), "max", new Classifier[] { tf.buildIntegerType() }));
		getOperations().add(bf.buildOperation(tf.buildIntegerType(), "min", new Classifier[] { tf.buildIntegerType() }));

		super.createOperations(tf);	
	}

	/** Check if this (an IntegerType) conforms to t2 */
	public Boolean conformsTo(Classifier t2) {
		return TypeConformance.isAssignableTo(this, t2) ? Boolean.TRUE : Boolean.FALSE;
	}

	/** Get the delegate Class */	
	public Object getDelegate() {
		return java.lang.Integer.class;
	}

	/** Get the implementation Class */	
	public Class getImplClass() {
		return OclInteger.class;
	}

	/** Accept a Semantic Visitor */	
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}
	
	/** ToString */
	public String toString(){
		return "Integer";
	}
}
