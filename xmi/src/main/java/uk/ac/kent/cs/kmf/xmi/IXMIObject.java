package uk.ac.kent.cs.kmf.xmi;

import java.util.*;

public interface IXMIObject
	extends IXMIElement
{	
	/** ID */
    public String getID();
    public void setID(String id);

	/** HREF */
    public String getHREF();
    public void setHREF(String href);

	/** LABEL */
    public String getLABEL();
    public void setLABEL(String label);

	/** UUID */
    public String getUUID();
    public void setUUID(String uuid);

	/** Properties */
    public List getProperties();
    public void setProperties(List properties);
    public void add(IXMIProperty property);
    public void delete(IXMIProperty property);

	/** Java object */
    public Object getObject();
    public void setObject(Object object);

}