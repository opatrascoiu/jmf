package uk.ac.kent.cs.ocl20.bridge4emf;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;
import uk.ac.kent.cs.ocl20.semantics.model.types.TypeFactory;
import uk.ac.kent.cs.ocl20.standard.types.OclAnyTypeImpl;

/**
 * @author dha
 *
 */
public class EnumerationImpl 
	extends DataTypeImpl 
	implements Enumeration_ 
{
	protected EEnum _eenum;
	/** Construct an Enumeration */
	public EnumerationImpl(EEnum eenum, OclProcessor proc) {
		super(proc);
		_eenum = eenum;
		
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

	/** Get name */
	String name = null;
	public String getName() {
		if (_eenum != null) {
			name = "";
			EPackage pkg = _eenum.getEPackage();
			while (pkg != null) {
				if (!name.equals("")) name = "."+name;
				name = pkg.getName()+name;
				pkg = pkg.getESuperPackage();
			}
			if (!name.equals("")) name += ".";
			name +=  _eenum.getName();
		} 
		return name;
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
	public EnumLiteral lookupEnumLiteral(String name) {
		return ((EmfBridgeFactory)super.processor.getBridgeFactory()).buildEnumLiteral(_eenum.getEEnumLiteral(name), this);
	}

	/** Search for an Enumeration Literal with a given name */
	public Property lookupProperty(String name) {
		return lookupEnumLiteral(name);
	}

	/** Search for an owned element */
	public ModelElement lookupOwnedElement(String name) {
		return lookupEnumLiteral(name);
	}
	
	/** Get Enumeration Literals */
	List literals = null;
	public List getLiteral() {
		if (_eenum != null) {
			literals = new Vector();
			Iterator i = _eenum.getELiterals().iterator();
			while (i.hasNext()) {
				EEnumLiteral lit = (EEnumLiteral)i.next();
				literals.add(((EmfBridgeFactory)super.processor.getBridgeFactory()).buildEnumLiteral(lit, this));
			}
		}
		return literals;
	}

	/** Set Enumeration Literals */
	public void setLiteral(List literals) {
	}

	/** Accept a Sematics Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}
	
	public String toString(){
		return _eenum.getName();
	}
	
	/** Equals */
	public boolean equals(Object o) {
		if (o instanceof Enumeration_) {
			Enumeration_ eenum = (Enumeration_)o;
			return getName().equals(eenum.getName());
		}
		return false;
	}
}
