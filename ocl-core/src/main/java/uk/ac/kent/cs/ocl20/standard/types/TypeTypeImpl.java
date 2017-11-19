/**
 * 
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.ocl20.standard.types;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.bridge.BridgeFactory;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeType;
import uk.ac.kent.cs.ocl20.standard.lib.OclType;

/**
 * @author dha
 *
 */
public class TypeTypeImpl extends PrimitiveImpl implements TypeType {

	Classifier cls;
	public Classifier getClassifier() {
		return cls;
	}
	public TypeTypeImpl(OclProcessor proc, Classifier cls) {
		super(proc);
		this.cls = cls;
	}

	public void createOperations(TypeFactory tf) {
		BridgeFactory bf = processor.getBridgeFactory();
		//--- Add operations ---
		getOperations().add(bf.buildOperation(tf.buildBooleanType(), "=", new Classifier[] { this }));
		getOperations().add(bf.buildOperation(tf.buildBooleanType(), "<>", new Classifier[] { this }));
		
		super.createOperations(tf);
	}
	/** Check if this (a String) conforms with t2 */
	public Boolean conformsTo(Classifier t2) {
		return TypeConformance.isAssignableTo(this, t2) ? Boolean.TRUE : Boolean.FALSE;
	}

	/** Accept a Semantic Visitor */ 
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}
	
	/** ToString */
	public String toString(){
		return "TypeType("+cls+")";
	}

	public Object getDelegate() {
		return java.lang.Class.class;
	}

	public Class getImplClass() {
		return OclType.class;
	}


}
