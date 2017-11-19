package uk.ac.kent.cs.ocl20.bridge4java;

import uk.ac.kent.cs.ocl20.semantics.*;
import uk.ac.kent.cs.ocl20.semantics.bridge.*;

public class EnumLiteralImpl implements EnumLiteral {
	/** Construct an Enumeration Literal */
	public EnumLiteralImpl(java.lang.reflect.Field enumLit, Enumeration_ parent) {
		_enumLit = enumLit;
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

	/** Wrapped Java Class */
	protected java.lang.reflect.Field _enumLit;
	public java.lang.reflect.Field getImpl() {
		return _enumLit;
	}
	public void setImpl(java.lang.reflect.Field eenum) {
		this._enumLit = eenum;
	}

	/** Name */
	String _name = null;
	public String getName() {
		if (_name == null) _name = _enumLit.getName();
		return _name;
	}
	public void setName(String name) {
		_name = name;
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
	
	/** Create an instance */
	public Object getInstance() {
		try {
			return _enumLit.get(null);
		} catch (Exception e) {
			return null;
		}
	}

	/** Accept a Semantic Visitor */
	public Object accept(SemanticsVisitor v, Object obj) {
		return v.visit(this,obj);
	}

	/** ToString */
	public String toString() {
		return "EnumLiteral("+getName()+")";
	}

	/** Clone */
	public Object clone() {
		return new EnumLiteralImpl(_enumLit, _enum);
	}
}
