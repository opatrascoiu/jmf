package uk.ac.ukc.cs.kmf.xmi;

public interface IReaderAdapter
{
    public abstract Object createObject(ObjectInfo objectinfo);

    public abstract Object createProperty(FeatureInfo featureinfo);

    public abstract void resolveValue(Object obj, Object obj1, Object obj2);

    public abstract void setXMIFile(XMIFile xmifile);
}