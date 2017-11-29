package uk.ac.kent.cs.kmf.xmi;

import java.util.*;

public class ObjectInfo 
	implements IObjectInfo
{
    public ObjectInfo() {
        clear();
        setXMIVersion("1.x");
    }

    public ObjectInfo(String xmiVersion) {
        clear();
        setXMIVersion(xmiVersion);
    }

    protected void clear() {
        xmiName = null;
        id = null;
        uuid = null;
        label = null;
        idref = null;
        href = null;
        namespace = null;
        models = new Vector();
    }

    public String getId() {
        return id;
    }
    public void setId(String s) {
        id = s;
    }

    public String getIdref() {
        return idref;
    }
    public void setIdref(String s) {
        idref = s;
    }

    public String getHref() {
        return href;
    }
    public void setHref(String s) {
        href = s;
    }

    public String getLabel() {
        return label;
    }
    public void setLabel(String s) {
        label = s;
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

    public String getUUID() {
        return uuid;
    }
    public void setUUID(String s) {
        uuid = s;
    }

    public String getXMIName() {
        return xmiName;
    }
    public void setXMIName(String s) {
        xmiName = s;
    }

    public String getXMIVersion() {
        return xmiVersion;
    }
    public void setXMIVersion(String s) {
        xmiVersion = s;
    }

    public String toString() {
        String s = "xmiName: '" + xmiName + "'" + " id: '" + id + "' idref: '" + idref + "'";
        return s;
    }

    protected String id;
    protected String href;
    protected String idref;
    protected String label;
    protected List models;
    protected Namespace namespace;
    protected String uuid;
    protected String xmiName;
    protected String xmiVersion;
}