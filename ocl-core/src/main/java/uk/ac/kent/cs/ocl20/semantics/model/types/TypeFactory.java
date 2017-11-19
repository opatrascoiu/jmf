package uk.ac.kent.cs.ocl20.semantics.model.types;

import uk.ac.kent.cs.ocl20.semantics.bridge.Classifier;
/**
 * @author dha
 *
 */
public interface TypeFactory {
	public Classifier buildClassifier();
	public BagType buildBagType(Classifier elementType);
	public BooleanType buildBooleanType();
	public CollectionType buildCollectionType(Classifier elementType);
	public IntegerType buildIntegerType();
	public OclAnyType buildOclAnyType();
	public OclMessageType buildOclMessageType();
	public OrderedSetType buildOrderedSetType(Classifier elementType);
	public RealType buildRealType();
	public SequenceType buildSequenceType(Classifier elementType);
	public SetType buildSetType(Classifier elementType);
	public StringType buildStringType();
	public TupleType buildTupleType(String[] names, Classifier[] types);
	public VoidType buildVoidType();
//	public TypeType buildTypeType(Classifier type);
	public Classifier getFlatType(Classifier type);
}
