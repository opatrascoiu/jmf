package uk.ac.kent.cs.kmf.xmi;

import java.util.*;

public interface IObjectInfo {
    public String getId();
    public void setId(String s);

    public String getIdref();
    public void setIdref(String s);

    public String getHref();
    public void setHref(String s);

    public String getLabel();
    public void setLabel(String s);

    public List getModels();
    public void setModels(List models);

    public Namespace getNamespace();
    public void setNamespace(Namespace namespace);

    public String getUUID();
    public void setUUID(String s);

    public String getXMIName();
    public void setXMIName(String s);
}
