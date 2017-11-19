package uk.ac.kent.cs.ocl20.standard.types;

import java.util.List;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.SemanticsVisitor;
import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
import uk.ac.kent.cs.ocl20.semantics.bridge.Operation;
import uk.ac.kent.cs.ocl20.semantics.model.types.BagType;
import uk.ac.kent.cs.ocl20.semantics.model.types.CollectionType;
import uk.ac.kent.cs.ocl20.semantics.model.types.OrderedSetType;
import uk.ac.kent.cs.ocl20.semantics.model.types.SequenceType;
import uk.ac.kent.cs.ocl20.semantics.model.types.SetType;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.semantics.model.types.VoidType;
import uk.ac.kent.cs.ocl20.standard.lib.OclOrderedSet;

/**
 * @author dha
 *
 */
public class OrderedSetTypeImpl extends SetTypeImpl implements OrderedSetType {
	/** Construct an OrderedSetType */
	public OrderedSetTypeImpl(Classifier elementType, OclProcessor proc) {
		super(elementType,proc);
	}
	
	/** Set the operations */
	public void createOperations(TypeFactory tf) {
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildBooleanType(), "=", new Classifier[] { this }) );
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildBooleanType(), "<>", new Classifier[] { this }));
		
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "union", new Classifier[] { this }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildBagType(this.getElementType()), "union", new Classifier[] { tf.buildBagType(this.getElementType()) }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildSetType(this.getElementType()), "union", new Classifier[] { tf.buildSetType(this.getElementType()) }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "intersection", new Classifier[] { this }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildSetType(this.getElementType()), "intersection", new Classifier[] { tf.buildBagType(this.getElementType()) }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildSetType(this.getElementType()), "intersection", new Classifier[] { tf.buildSetType(this.getElementType()) }));

		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "append", new Classifier[] {getElementType()}) );
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "prepend", new Classifier[] {getElementType()}) );
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "insertAt", new Classifier[] {tf.buildIntegerType(), getElementType()}) );
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "subOrderedSet", new Classifier[] {tf.buildIntegerType(), tf.buildIntegerType()}) );
		getOperations().add( super.processor.getBridgeFactory().buildOperation(getElementType(), "at", new Classifier[] {tf.buildIntegerType()}) );
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildIntegerType(), "indexOf", new Classifier[] {getElementType()}) );
		getOperations().add( super.processor.getBridgeFactory().buildOperation(getElementType(), "first", new Classifier[] {}) );
		getOperations().add( super.processor.getBridgeFactory().buildOperation(getElementType(), "last", new Classifier[] {}) );

		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildIntegerType(), "count", new Classifier[] { this.getElementType() }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildOrderedSetType(tf.getFlatType(this)), "flatten", new Classifier[] { }));

		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildBagType(this.getElementType()), "asBag", new Classifier[] {} ));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildSequenceType(this.getElementType()), "asSequence", new Classifier[] {} ));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(tf.buildSetType(this.getElementType()), "asSet", new Classifier[] {} ));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "asOrderedSet", new Classifier[] {} ));

		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "select", new Classifier[] { tf.buildBooleanType() }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "select", new Classifier[] { getElementType(), tf.buildBooleanType() }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "reject", new Classifier[] { tf.buildBooleanType() }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "reject", new Classifier[] { getElementType(), tf.buildBooleanType() }));
		// collectNested's signature will be computed latter
		//_operations.put("collectNested", super.processor.getBridgeFactory().buildOperation(Bag(T), "collectNested", null));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "sortedBy", new Classifier[] { tf.buildClassifier() }));
		getOperations().add( super.processor.getBridgeFactory().buildOperation(this, "sortedBy", new Classifier[] { getElementType(), tf.buildClassifier() }));

		super.createOperations(tf);
	}
	
	/** Lookup for an operation */
	public Operation lookupOperation(String name, List types) {
		Operation op = (Operation)super.lookupOperation(name, types);
		if (op == null) {
			if (name.equals("collect")) {
				OrderedSetType ordT = super.processor.getTypeFactory().buildOrderedSetType( (Classifier)types.get(types.size()-1) );
				return super.processor.getBridgeFactory().buildOperation( ordT, "collect", null);
			} else if (name.equals("collectNested")) {
				OrderedSetType ordT = super.processor.getTypeFactory().buildOrderedSetType( (Classifier)types.get(types.size()-1) );
				return super.processor.getBridgeFactory().buildOperation( ordT, "collectNested", null);
			}
		}
		return op;
	}

	/** Check if this (an OrderedSet) conforms to t2 */
	public Boolean conformsTo(Classifier t2) {
		//--- T2 is undefined ---
		if (t2 instanceof VoidType)
			return Boolean.TRUE;
		//--- T2 is OrderedSetType ---
		if (t2 instanceof OrderedSetType)
			return getElementType().conformsTo(((OrderedSetType)t2).getElementType());
		//--- T2 is CollectionType ---
		if (t2 instanceof CollectionType && 
			!(t2 instanceof SetType) && !(t2 instanceof BagType) && !(t2 instanceof SequenceType)) 
			return getElementType().conformsTo(((CollectionType)t2).getElementType());
		//--- Check for parents ---
		else
			return TypeConformance.isAssignableTo(this, t2) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	/** Get the delegate Class */
	public Object getDelegate() {
		return java.util.List.class;
	}

	/** Get the implementation Class */
	public Class getImplClass() {
		return OclOrderedSet.class;
	}

	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}
	
	/** ToString */
	public String toString(){
		return "OrderedSet("+this.getElementType()+")";
	}
}
