package uk.ac.kent.cs.ocl20.standard.lib;

public class OclStateImpl 
	extends OclAnyImpl
{	
	public OclStateImpl(StdLibAdapter adapter) {
		super(adapter);
	}

	public Object getImpl() {
		return null;
	}

	public void setImpl(Object impl) {
	}

	public Object clone() {
		return new OclStateImpl(this.adapter);
	}
	
	public boolean equals(Object s) {
		return this==s;
	}
}
