package uk.ac.kent.cs.ocl20.bridge4emf;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

import uk.ac.kent.cs.ocl20.standard.types.*;

/**
 * @author dha
 *
 */
public class EmfTypeFactory
	extends TypeFactoryImpl
{
	/** Construct an Enumeration */
	public EmfTypeFactory(OclProcessor proc) {
		super(proc);
	}
	
	public Classifier buildClassifier(EClass ecl) {
		if (ecl instanceof EEnum)
			return null;
		else if (ecl instanceof EDataType)
			return null;
		else if (ecl instanceof EClass)
			return buildOclModelElementType(ecl);
		return null;
	}
	
	public OclModelElementType buildOclModelElementType(EClass ecl) {
		return new OclModelElementTypeImpl(ecl, processor);
	}

}
