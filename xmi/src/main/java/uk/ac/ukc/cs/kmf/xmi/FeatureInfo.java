package uk.ac.ukc.cs.kmf.xmi;

import java.util.*;

public class FeatureInfo
	implements IFeatureInfo
{
    public FeatureInfo()
    {
    }

    protected void clear() {
        xmiName = null;
        className = null;
        namespace = null;
        models = new Vector();
        object = null;
        value = null;
        type = null;
    }

    public String getType() {
        return type;
    }
    public void setType(String s) {
        type = s;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String s) {
        className = s;
    }

    public List getModels() {
        return models;
    }
    public void setModels(List models) {
        this.models = models;
    }

    public Namespace getNamespace() {
        return namespace;
    }
    public void setNamespace(Namespace namespace1) {
        namespace = namespace1;
    }

    public Object getObject() {
        return object;
    }
    public void setObject(Object obj) {
        object = obj;
    }

    public Object getValue() {
        return value;
    }
    public void setValue(Object obj) {
        value = obj;
    }

    public String getXMIName() {
        return xmiName;
    }
    public void setXMIName(String s) {
        xmiName = s;
    }

    protected String xmiName;
    protected String className;
    protected Namespace namespace;
    protected List models;
    protected Object object;
    protected Object value;
    protected String type;
}