package uk.ac.ukc.cs.kmf.xmi;

import java.util.*;

public interface IFeatureInfo {
    public String getType();
    public void setType(String s);

    public String getClassName();
    public void setClassName(String s);

    public List getModels();
    public void setModels(List models);

    public Namespace getNamespace();
    public void setNamespace(Namespace namespace1);

    public Object getObject();
    public void setObject(Object obj);

    public Object getValue();
    public void setValue(Object obj);

    public String getXMIName();
    public void setXMIName(String s);
}
