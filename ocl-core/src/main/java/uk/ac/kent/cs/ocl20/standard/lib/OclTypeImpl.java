package uk.ac.kent.cs.ocl20.standard.lib;

/**
 * @author dha
 *
 */
public class OclTypeImpl 
	extends OclAnyImpl 
	implements OclType 
{
	protected java.lang.Class cls;

	public OclTypeImpl(Class cls, StdLibAdapter adapter) {
		super(adapter);
		this.cls = cls;
	}

	public Object clone() {
		return new OclTypeImpl(this.cls, super.adapter);
	}

	public boolean equals(Object o) {
		if (o instanceof OclTypeImpl)
			return this.cls == ((OclTypeImpl)o).cls;
		return false;
	}

	public Object getImpl() {
		return cls;
	}

	public void setImpl(Object impl) {
		cls = (java.lang.Class)impl;
	}

	public String toString() {
		return "OclType("+cls.getName()+")";
	}

}
