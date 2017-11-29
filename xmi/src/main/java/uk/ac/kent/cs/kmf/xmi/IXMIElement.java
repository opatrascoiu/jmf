package uk.ac.kent.cs.kmf.xmi;

public interface IXMIElement {
	/** Namespace */
    public INamespace getNamespace();
    public void setNamespace(INamespace namespace);

	/** Name */
    public String getXMIName();
    public void setXMIName(String name);

    /** Owner */
    public IXMIElement getXMIOwner();
    public void setXMIOwner(IXMIElement owner);
}
