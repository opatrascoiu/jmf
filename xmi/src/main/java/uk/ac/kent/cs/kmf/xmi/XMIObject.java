package uk.ac.kent.cs.kmf.xmi;

import java.util.*;

public class XMIObject
	extends XMIElement
    implements IXMIObject
{

	/** Constructor */
	public XMIObject(String name, Object object) {
		super(name);
		this.object = object;
		id = object.toString();
		properties = new Vector();
		links = new Vector();
	}

	/** ID */
    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }
	
	/** HREF */
    public String getHREF() {
        return href;
    }
    public void setHREF(String href) {
        this.href = href;
    }

	/** LABEL */
    public String getLABEL() {
        return label;
    }
    public void setLABEL(String label) {
        this.label = label;
    }

	/** UUID */
    public String getUUID() {
        return uuid;
    }
    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

	/** Properties */
    public List getProperties() {
    	return properties;
    }
    public void setProperties(List properties) {
    	this.properties = properties;
    }
    public void add(IXMIProperty property) {
        properties.add(property);
        property.setXMIOwner(this);
    }
    public void delete(IXMIProperty property) {
        properties.remove(property);
    }

	/** Object */
    public Object getObject() {
    	return object;
	}
    public void setObject(Object object) {
    	this.object = object;
    }

	/** Members */
    protected String id;
    protected String uuid;
    protected String label;
    protected String href;
    protected Object object;
    protected List properties;
    protected List links;
}