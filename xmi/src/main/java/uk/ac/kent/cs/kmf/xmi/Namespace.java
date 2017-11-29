package uk.ac.kent.cs.kmf.xmi;

public class Namespace 
	implements INamespace
{
    public Namespace(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getURI() {
        return uri;
    }
    public void setURI(String uri) {
        this.uri = uri;
    }

    public boolean equals(INamespace obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Namespace))
            return false;
        String s = ((Namespace)obj).getPrefix();
        String s1 = ((Namespace)obj).getURI();
        if(s == null && prefix != null)
            return false;
        if(prefix == null && s != null)
            return false;
        if(s1 == null && uri != null)
            return false;
        if(uri == null && s1 != null)
            return false;
        if(s != null && !s.equals(prefix))
            return false;
        return s1 == null || s1.equals(uri);
    }

    protected String prefix;
    protected String uri;
}