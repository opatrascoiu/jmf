package uk.ac.kent.cs.ocl20.bridge4java;

import java.lang.Class;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

import uk.ac.kent.cs.ocl20.standard.types.*;

/**
 * @author Octavian Patrascoiu
 *
 */
public class JavaTypeFactory
	extends TypeFactoryImpl
{
	/** Construct an Enumeration */
	public JavaTypeFactory(OclProcessor proc) {
		super(proc);
	}
	
	public Classifier buildClassifier(Class cls) {
		return buildOclModelElementType(cls);
	}
	
	public OclModelElementType buildOclModelElementType(Class cls) {
		return new OclModelElementTypeImpl(cls, processor);
	}

}
