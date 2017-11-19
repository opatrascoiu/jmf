package uk.ac.kent.cs.ocl20.standard.lib;

public interface OclString
    extends OclAny
{
	public OclInteger size();
	public OclString concat(OclString string2);
	public OclString substring(OclInteger lower, OclInteger upper);
	public OclInteger toInteger();
	public OclReal toReal();
}
