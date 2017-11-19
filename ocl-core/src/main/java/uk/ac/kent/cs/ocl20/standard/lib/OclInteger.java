package uk.ac.kent.cs.ocl20.standard.lib;

public interface OclInteger
    extends OclReal
{
	public OclInteger inegate();
	public OclInteger add(OclInteger i2);
	public OclInteger subtract(OclInteger i2);
	public OclInteger multiply(OclInteger i2);
	public OclReal divide(OclInteger i2);
	public OclInteger div(OclInteger i2);
	public OclReal abs();
	public OclInteger mod(OclInteger i2);
	public OclInteger max(OclInteger i2);
	public OclInteger min(OclInteger i2);
}
