package uk.ac.ukc.cs.kmf.xmi;

public class ReaderAdapter
    implements IReaderAdapter
{
    public ReaderAdapter() {
    }

    public Object createObject(ObjectInfo objectinfo) {
        return null;
    }

    public Object createProperty(FeatureInfo featureinfo) {
    	return null;
    }

    public void resolveValue(Object object, Object propertyName, Object value) {
    }

    public void setXMIFile(XMIFile xmifile) {
        this.xmiFile = xmifile;
    }
    XMIFile xmiFile;
}