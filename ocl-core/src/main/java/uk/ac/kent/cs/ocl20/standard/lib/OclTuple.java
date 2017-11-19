package uk.ac.kent.cs.ocl20.standard.lib;

public interface OclTuple
    extends OclAny
{
	public OclAny property(OclString s);
	public void setProperty(OclString name, Object value);
}
