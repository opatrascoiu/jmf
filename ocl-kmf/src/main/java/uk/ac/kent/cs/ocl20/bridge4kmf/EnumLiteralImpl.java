package uk.ac.kent.cs.ocl20.bridge4kmf;

import uk.ac.kent.cs.ocl20.OclProcessor;
import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

/**
 * @author Octavian Patrascoiu
 *
 */

public class EnumLiteralImpl implements EnumLiteral {
	/** Construct a Enumeration Literal */
	public EnumLiteralImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral enumLit, Enumeration_ enum_, OclProcessor proc) {
		this.processor = proc;
		this.enumLit = enumLit;
		this.enum_ = enum_;
	}
	protected OclProcessor processor;

	/** Wrapped UML correspondent */
	protected uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral enumLit;
	public uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral getImpl() {
		return enumLit;
	}
	public void setImpl(uk.ac.ukc.cs.kmf.kmfstudio.uml.Foundation.Core.EnumLiteral enumLit) {
		this.enumLit = enumLit;
	}

	/** Enumeration */
	Enumeration_ enum_ = null;
	public Enumeration_ getEnumeration() {
		if (enumLit != null) {
			enum_ = processor.getBridgeFactory().buildEnumeration(enumLit.getEnumeration());
		}
		return enum_;
	}
	public void setEnumeration(Enumeration_ enum_) {
		if (enumLit == null) this.enum_ = enum_;
	}

	/** Name */
	String name = null;
	public String getName() {
		if (enumLit != null) name = enumLit.getName().getBody_();
		return name;
	}
	public void setName(String name) {
		if (enumLit == null) this.name = name;
	}

	/** Type */
	public Classifier getType() {
		return getEnumeration();
	}
	public void setType(Classifier type) {
	}

	/** Get delegate */
	public Object getDelegate() {
		return this.getClass();
	}
	
	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object data) {
		return  v.visit(this, data);
	}

	/** ToString */
	public String toString() {
		return "EnumLiteral("+getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new EnumLiteralImpl(enumLit, enum_, processor);
	}	
}
