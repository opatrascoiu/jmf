package uk.ac.kent.cs.kmf.xmi;

import java.util.*;

public interface IXMIProperty
	extends IXMIElement
{   
	/** Property type */
	public boolean isBasicType();
	public boolean isObjectType();
	public boolean isCollectionType();
    public int getXMIType();
    public void setXMIType(int type);

	/** Property value */
    public Object getXMIValue();
    public void setXMIValue(Object value);

	/** Property values */
    public List getXMIValues();
    public void setXMIValues(List values);
    public void addXMIValue(Object value);
}