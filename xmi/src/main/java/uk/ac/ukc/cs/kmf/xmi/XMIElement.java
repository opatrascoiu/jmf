package uk.ac.ukc.cs.kmf.xmi;

public class XMIElement 
	implements IXMIElement 
{
	/** Constructor */
	public XMIElement(String name) {
		this.name = name.replaceAll(" ", "_");
	}
	public XMIElement(INamespace namespace, String name) {
		this.namespace = namespace;
		this.name = name.replaceAll(" ", "_");
	}

	/** Namespace */
    public INamespace getNamespace() {
    	return namespace;
	}
    public void setNamespace(INamespace namespace) {
    	this.namespace = namespace;
    }

	/** Name */
    public String getXMIName() {
    	return name;
	}
    public void setXMIName(String name) {
    	this.name = name;
    }

	/** Owner */
    public IXMIElement getXMIOwner() {
    	return owner;
	}
    public void setXMIOwner(IXMIElement owner) {
    	this.owner = owner;
    }

	/** Members */
	protected INamespace namespace;
	protected String name;
	protected IXMIElement owner;
}
