package uk.ac.kent.cs.ocl20.bridge4emf;

import org.eclipse.emf.ecore.EEnumLiteral;

import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

public class EnumLiteralImpl implements EnumLiteral {

	protected EEnumLiteral _eenumLit;
	public EnumLiteralImpl(EEnumLiteral eenumLit, Enumeration_ parent) {
		_eenumLit = eenumLit;
		_enum=parent;
	}

	/** Enumeration */
	Enumeration_ _enum=null;
	public Enumeration_ getEnumeration() {
		return _enum;
	}
	public void setEnumeration(Enumeration_ eenum) {
		_enum = eenum;
	}

	/** Name */
	String _name = null;
	public String getName() {
		if (_name == null) _name = _eenumLit.getName();
		return _name;
	}
	public void setName(String name) {
		_name = name;
	}

	/** Type */
	Classifier type = null;
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
	public String toString(){
		return getName();
	}

	/** Clone */
	public Object clone() {
		return this;
	}
}
