package uk.ac.ukc.cs.kmf.xmi;

public interface INamespace {
    public String getPrefix();
    public void setPrefix(String prefix);

    public String getURI();
    public void setURI(String uri);

    public boolean equals(INamespace obj);
}
