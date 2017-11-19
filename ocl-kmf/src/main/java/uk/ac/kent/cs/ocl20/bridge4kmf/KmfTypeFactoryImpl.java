package uk.ac.kent.cs.ocl20.bridge4kmf;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

import uk.ac.kent.cs.ocl20.standard.types.*;

/**
 * @author dha
 *
 */
public class KmfTypeFactoryImpl
	extends TypeFactoryImpl
{
	/** Construct an Enumeration */
	public KmfTypeFactoryImpl(OclProcessor proc) {
		super(proc);
	}
	
	public Classifier buildClassifier(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Classifier cl) {
		if (cl instanceof uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_)
			return buildOclModelElementType((uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_)cl);
		return null;
	}
	
	public OclModelElementType buildOclModelElementType(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.Class_ cl) {
		return new OclModelElementTypeImpl(cl, processor);
	}

}
