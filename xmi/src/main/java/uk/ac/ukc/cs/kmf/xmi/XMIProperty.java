package uk.ac.ukc.cs.kmf.xmi;

import java.util.*;

public class XMIProperty
	extends XMIElement
    implements IXMIProperty
{
	/** Constructor */
	public XMIProperty(String name) {
		super(name);
		values = new Vector();
	}

	/** Property type */
	public boolean isBasicType() {
		return type == BASIC;
	}
	public boolean isObjectType() {
		return type == OBJECT;
	}
	public boolean isCollectionType() {
		return type == COLLECTION;
	}
    public int getXMIType() {
    	return type;
    }
    public void setXMIType(int type) {
    	this.type = type;
    }

	/** Property value */
    public Object getXMIValue() {
    	return value;
    }
    public void setXMIValue(Object value) {
    	this.value = value;
    }

	/** Property values */
    public List getXMIValues() {
    	return values;
    }
    public void setXMIValues(List values) {
    	this.value = values;
    }
    public void addXMIValue(Object value) {
    	values.add(value);
    }
    
    protected String name;
    protected int type;
    protected Object value;
    protected List values;
    public static final int BASIC = 1; 
    public static final int OBJECT = 2; 
    public static final int COLLECTION = 3; 
}