package uk.ac.kent.cs.ocl20.bridge4kmf;

import java.util.*;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.standard.types.OclAnyTypeImpl;
import uk.ac.kent.cs.kmf.util.Naming;

/**
 * @author Octavian Patrascoiu
 *
 */

public class EnumerationImpl 
	extends DataTypeImpl 
	implements Enumeration_ 
{
	/** Construct an Enumeration */
	public EnumerationImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_ enum_, OclProcessor proc) {
		super(proc);
		this.processor = proc;
		this.enum_ = enum_;

		//--- Add operations for OCLANY_TYPE ---
		BridgeFactory bf = super.processor.getBridgeFactory();
		TypeFactory tf = super.processor.getTypeFactory();
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "=", new Classifier[] { this }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "<>", new Classifier[] { this }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "=", new Classifier[] { tf.buildOclAnyType() }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "<>", new Classifier[] { tf.buildOclAnyType() }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsNew", new Classifier[] {}));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsUndefined", new Classifier[] {}));
		// Signature of oclAsType is computed later
		// OCLANY_TYPE.getOperations().add(buildOperation(CLASSIFIER_TYPE, "oclAsType", new Classifier[] { CLASSIFIER_TYPE }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsTypeOf", new Classifier[] { tf.buildClassifier() }));
		super.getOperations().add(bf.buildOperation(tf.buildBooleanType(), "oclIsKindOf", new Classifier[] { tf.buildClassifier() }));
		// Signature of allInstances is computed later
		// OCLANY_TYPE.getOperations().add(buildOperation(buildSetType(CLASSIFIER_TYPE), "allInstances", NONE));
	}
	protected OclProcessor processor;

	/** Wrapped UML correspondent */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_ enum_;
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_ getImpl() {
		return enum_;
	}
	public void setImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Enumeration_ enum_) {
		this.enum_ = enum_;
	}

	/** Check if this (an Enumeration) conforms with t2 */
	public Boolean conformsTo(Classifier t2) {
		if (t2 instanceof Enumeration_) {
			return (getName().equals(t2.getName())) ? Boolean.TRUE : Boolean.FALSE;
		}
		if (t2.getClass() == OclAnyTypeImpl.class) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/** Search for an Enumeration Literal with a given name */
	public Property lookupProperty(String name) {
		return lookupEnumLiteral(name);
	}

	/** Search for an Enumeration Literal with a given name */
	public EnumLiteral lookupEnumLiteral(String name) {
		Iterator i = enum_.getLiterals().iterator();
		uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral res = null;
		while (i.hasNext()) {
			uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral lit = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral)i.next();
			if (lit.getName().getBody_().equals(name)) {
				res = lit; 
				break;
			} 
		}
		return ((KmfBridgeFactoryImpl)processor.getBridgeFactory()).buildEnumLiteral(res, (Enumeration_)this);
	}

	/** Get Enumeration Literals */
	List literals = null;
	public List getLiteral() {
		if (enum_ != null) {
			literals = new Vector();
			Iterator i = enum_.getLiterals().iterator();
			while (i.hasNext()) {
				uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral lit = (uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral)i.next();
				literals.add(processor.getBridgeFactory().buildEnumLiteral(lit));
			}
		}
		return literals;
	}

	/** Set Enumeration Literals */
	public void setLiteral(List literals) {
		if (enum_ == null) this.literals = literals;
	}

	/** Search for an owned element */
	public ModelElement lookupOwnedElement(String name) {
		return lookupEnumLiteral(name);
	}
	
	/** Get name */
	String name = null;
	public String getName() {
		if (enum_ != null) name = Naming.getFullClassifierName(enum_);
		return name;
	}
	
	/** Equals */
	public boolean equals(Object o) {
		if (! (o instanceof Enumeration_)) return false;
		return (this.toString().equals(o.toString()));
	}

	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}
	
	/** ToString */
	public String toString(){
		return getName();
	}

	/** Clone */
	public Object clone() {
		return new EnumerationImpl(enum_, processor);
	}	
}
