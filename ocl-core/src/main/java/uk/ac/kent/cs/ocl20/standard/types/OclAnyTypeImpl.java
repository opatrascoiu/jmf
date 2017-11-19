package uk.ac.kent.cs.ocl20.standard.types;

import java.util.List;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.bridge.BridgeFactory;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.bridge.DataTypeImpl;
import uk.ac.kent.cs.ocl20.semantics.bridge.Operation;
import uk.ac.kent.cs.ocl20.semantics.model.types.OclAnyType;
import uk.ac.kent.cs.ocl20.semantics.model.types.SetType;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.standard.lib.OclAny;

/**
 * @author dha
 *
 */
public class OclAnyTypeImpl 
       extends	DataTypeImpl 
       implements OclAnyType 
{
	/** Compute an OclAnyType */
	public OclAnyTypeImpl(OclProcessor proc) {
		super(proc);
	}

	public void createOperations(TypeFactory tf) {
		BridgeFactory bf = processor.getBridgeFactory();
		//--- Add operations for OCLANY_TYPE ---
		getOperations().add(bf.buildOperation(tf.buildBooleanType(), "=", new Classifier[] { tf.buildOclAnyType() }));
		getOperations().add(bf.buildOperation(tf.buildBooleanType(), "<>", new Classifier[] { tf.buildOclAnyType() }));
		getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsNew", new Classifier[] {}));
		getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsUndefined", new Classifier[] {}));
		// Signature of oclAsType is computed later
		// OCLANY_TYPE.getOperations().add(buildOperation(CLASSIFIER_TYPE, "oclAsType", new Classifier[] { CLASSIFIER_TYPE }));
		getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsTypeOf", new Classifier[] { tf.buildClassifier() }));
		getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsKindOf", new Classifier[] { tf.buildClassifier() }));
		// Signature of allInstances is computed later
		// OCLANY_TYPE.getOperations().add(buildOperation(buildSetType(CLASSIFIER_TYPE), "allInstances", NONE));
	}
	
	/** Search for an operation */
	public Operation lookupOperation(String name, List types) {
		Operation op = super.lookupOperation(name, types);
		if (op == null) {
			if (name.equals("oclAsType")) {
//				TypeType tt = (TypeType)types.get(0);
//				return super.processor.getBridgeFactory().buildOperation(tt.getClassifier(), "oclAsType", new Classifier[] {super.processor.getTypeFactory().buildClassifier()});
				Classifier type = (Classifier)types.get(0);
				return super.processor.getBridgeFactory().buildOperation(type, "oclAsType", new Classifier[] {type});
			} else if (name.equals("allInstances")) {
				SetType setT = super.processor.getTypeFactory().buildSetType(this);
				return super.processor.getBridgeFactory().buildOperation(setT, "allInstances", null);
			}
		}
		return op;
	}

	/** Check if this (an OclAnyType) conforms to t2 */ 
	public Boolean conformsTo(Classifier t2) {
		return TypeConformance.isAssignableTo(this, t2) ? Boolean.TRUE : Boolean.FALSE;
	}

	/** Get the delegate Class */
	public Object getDelegate() {
		return java.lang.Object.class;
	}

	/** Get the implementation Class */
	public Class getImplClass() {
		return OclAny.class;
	}

	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	/** ToString */
	public String toString(){
		return "OclAny";
	}
}
